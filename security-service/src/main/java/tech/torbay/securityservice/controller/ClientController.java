package tech.torbay.securityservice.controller;


import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.email.SpringBootEmail;
import tech.torbay.securityservice.entity.ClientAssociation;
import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.repository.ClientUserRepository;
import tech.torbay.securityservice.service.ClientService;
import tech.torbay.securityservice.service.UserService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;
import tech.torbay.securityservice.utils.QueryStringCreator;
import tech.torbay.securityservice.utils.Utils;

@RestController
@RequestMapping("/api")
@Api(value = "Client Resource REST Endpoint", description = "Shows the client info")
public class ClientController {

    @Autowired
    ClientUserRepository clientRepository;
    
    @Autowired
    ClientService clientService;
    @Autowired
    UserService userService;

	/*New APIs Structure*/
    
    @ApiOperation(value = "Client user existance check with Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client record exist already")
            }
    )
	@GetMapping("/client/user/{email}")
	public ResponseEntity<Object> clientExists(@PathVariable("email") String email) {
		ClientUser client = clientService.findByEmail(email);
		
		// check email 
		//	- registered or not
		//	- password reseted or not
		//	- active/inactive
		
		if(client != null) {
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Client Already Exists");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","Client Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
	}
    
	@ApiOperation(value = "New Organisation Exist Client user accept email invitation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client User Accepted New Corporate Account Invitation")
            }
    )
	@PostMapping("/client/user/invite/accept")
	public ResponseEntity<Object> acceptInvite(
			@RequestParam("organisationId") Integer organisationId, 
			@RequestBody ClientUser client) {
		
		// accept invite requires
		// 1. Already have an client account
		// 2. userId
		// 3. new Org Id differ from previous one --- NEED TO ACCEPT TERMS AND CONDITION
		
		System.out.println(client.toString());
		
		if(clientService.findByEmail(client.getEmail()) != null) {
			// Already have an client account
			int userId = client.getClientId();
			if (userId != 0 /* && client.getOrganisationId() > 0 - check org parameter */) {
				if(clientService.updateClientUserVerificationStatus(organisationId, client.getClientId()) != null) {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.REQUEST_SUCCESS.getValue(),
							"Success",
							"Client User Account Verified for an Organisation");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.NOT_FOUND.getValue(),
							"Resource not found error",
							"Client Record Not Found");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
			} else {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.NOT_FOUND.getValue(),
						"Resource not found error",
						"Client Record Not Found");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
		} else {
			// Invite Error
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.NOT_FOUND.getValue(),
					"Resource not found error",
					"Client Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
	}
	
	@ApiOperation(value = "New Client User Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "New Client User Registered Successfully"),
                    @ApiResponse(code = 201, message = "New Client User Record Created Successfully")
            }
    )
	@PostMapping("/client/user/register")
	public ResponseEntity<Object> registerClientUser(@RequestBody ClientUser client, UriComponentsBuilder builder) {
		
		// org_id
		
		if(clientService.findByEmail(client.getEmail()) != null ) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"User Record Already Exists");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
		} else {
			ClientUser clientUser;
			try {
				clientUser = clientService.registerClient(client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				clientUser = null;
			}
	        // check already exist or creation failed
	        
	        if (clientUser == null ) {
	        	ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.REQUEST_FAILED.getValue(),
	        			"Failed",
	        			"New Client Registration Failed");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
	        } else {
		        HttpHeaders headers = new HttpHeaders();
		        headers.setLocation(builder.path("/client/user/{id}").buildAndExpand(client.getClientId()).toUri());
		        ResponseMessage responseMessage = new ResponseMessage(
		        		APIStatusCode.REQUEST_SUCCESS.getValue(),
		        		"Success",
		        		"New Client Registered Successfully");
		        try {
		        	sendClientEmailVerification(clientUser);
		        } catch(Exception exp) {
		        	exp.printStackTrace();
		        }
		        
		        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.OK);
	        }
		}
	}
	
	private void sendClientEmailVerification(ClientUser clientUser) {
		// TODO Auto-generated method stub
//		String content = securityAES.getRegisterEncodedURL(clientUser.getEmail(), clientUser.getClientId(), Constants.UserType.CLIENT.getValue()); // query format request with Base64 Encryption
//		System.out.println("content->"+content);
		
		String responseJsonString = Utils.ClasstoJsonString(clientUser);
		String encryptClientUser = SecurityAES.encrypt(responseJsonString);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/accept-invite/450?"+ encryptClientUser; // AES algorithm
//		System.out.println("contentAES Encrypt->"+content);
//		System.out.println("contentAES Decrypt->"+SecurityAES.decrypt(encryptClientUser));
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendWelcomeEmailWithAttachment(clientUser.getEmail(), content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

	@ApiOperation(value = "Add new Client in an Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "New Client User Added in an Organisation Successfully"),
                    @ApiResponse(code = 201, message = "New Client User Record Created with in an Organisation Successfully")
            }
    )
	@PostMapping("/client/user/create")
	public ResponseEntity<Object> addClientUser(
			@RequestParam("organisationId") Integer organisationId,
			@RequestParam("clientUserType") Integer clientUserType, 
			@RequestParam("userRole") Integer userRole,
			@RequestBody ClientUser clientUserObj, UriComponentsBuilder builder) {
		
        // check already exist or not 
		// if exist send email invite only
		// else create user and send email invite
		ClientUser existClient = clientService.findByEmail(clientUserObj.getEmail());
		if(existClient != null) {
			try {
//				if Association Not-found/association-verification-pending/user-not-active  Send Invite
//				if(clientService.checkClientOrgAssociationFound(existClient.getClientId(), organisationId)) {
//					
//					
//					// Invite Sent
//					sendNewClientUserInviteEmail(existClient , organisationId, clientUserType, userRole);
//					
//					HttpHeaders headers = new HttpHeaders();
//			        ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Exist Client Invite Sent Successfully");
//			        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.CREATED);
//				} 
//				else {
//					//else return already associated
//					 ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Client Already Associated with Same Organisation");
//					 return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
//				}
				
//				 Invite Sent
				sendNewClientUserInviteEmail(existClient , organisationId, clientUserType, userRole);
				
				HttpHeaders headers = new HttpHeaders();
		        ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Exist Client Invite Sent Successfully");
		        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.OK);
				
			} catch(Exception exp) {
				exp.printStackTrace();
				ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Registering Invite to Exist Client User Failed");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			}
		} else {
			List<ClientAssociation> clientUsers = clientService.getAllClientUsersInOrganisation(organisationId);
			if(clientUsers.size() < Constants.MAX_USER_COUNT) {
				// Add Client and user-org Association 
				ClientUser clientUser = clientService.addClient(organisationId, clientUserType, userRole, clientUserObj);
		        
		        if (clientUser == null ) {
		        	ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Registering New Client User Failed");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		        } else {
		        	try {
		        		HttpHeaders headers = new HttpHeaders();
				        headers.setLocation(builder.path("/client/{id}").buildAndExpand(clientUser.getClientId()).toUri());
				        ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","New Client Record Created Successfully");
				        // Invite Sent
				        sendNewClientUserInviteEmail(clientUser , organisationId, clientUserType, userRole);
				        
				        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.CREATED);
		        	} catch(Exception exp) {
		        		exp.printStackTrace();
		        		ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Registering New Client User Failed");
			        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		        	}
		        }
			} else {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.REQUEST_SUCCESS.getValue(),
		        		"Success",
		        		"Maximum of "+Constants.MAX_USER_COUNT+" Client User Added in this Organisation");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
			
		}
			
	}
	
	//Need to change like Registration flow
	private void sendNewClientUserInviteEmail(ClientUser clientUser, Integer organisationId, Integer clientUserType,
			Integer userRole) {
		// TODO Auto-generated method stub
		QueryStringCreator queryStringCreator = new QueryStringCreator();
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/accept-invite/450?"+ queryStringCreator.getClientUserInviteEncodedURL(clientUser.getEmail(), clientUser.getClientId(), organisationId, clientUserType, userRole);
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendWelcomeEmailWithAttachment(clientUser.getEmail(), content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

	// Register a company
	@ApiOperation(value = "New Client Organisation Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful New Client Organisation Registered")
            }
    )
	@PostMapping("/client/org/register")
	public ResponseEntity<Object> addClientCompany(
			@RequestParam("clientId") Integer clientId,
			@RequestBody ClientOrganisation organisation ,
			UriComponentsBuilder builder) {
		ClientOrganisation clientorganisation = clientService.addClientOrganisation(clientId, organisation);
        if (clientorganisation == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Failed to  Register Client Organisation");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(organisation.getClientOrganisationId()).toUri());
            ResponseMessage responseMessage = new ResponseMessage(
            		APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client Organisation Registered for verification");
            
            
            
            return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.OK);
        }
        
	}
	
    
}
