package tech.torbay.securityservice.controller;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.constants.Constants.UserType;
import tech.torbay.securityservice.email.SpringBootEmail;
import tech.torbay.securityservice.entity.ClientAssociation;
import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.repository.ClientUserRepository;
import tech.torbay.securityservice.service.ClientService;
import tech.torbay.securityservice.service.UserService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;
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
	public ResponseEntity<Object> acceptInvite(@RequestBody Map<String, Object> requestData) {
		try {
			
			String hash = String.valueOf(requestData.get("hash"));
			
			String decryptedUser = SecurityAES.decrypt(hash);
			
			Map<String, Object> userData;
			try {
				userData = Utils.convertJsonToHashMap(decryptedUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.BAD_REQUEST.getValue(),
	        			"Failed",
	        			"Failed to Parse Request - Bad Request");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			}
			
			Integer clientId = Integer.parseInt(String.valueOf(userData.get("userId")));
			String email = String.valueOf(userData.get("email"));
			Integer organisationId = Integer.parseInt(String.valueOf(userData.get("organisationId")));
			
			// accept invite requires
			// 1. Already have an client account
			// 2. userId
			// 3. new Org Id differ from previous one --- NEED TO ACCEPT TERMS AND CONDITION
			
			if(clientService.findByEmail(email) != null) {
				// Already have an client account
				if (clientId != 0 /* && client.getOrganisationId() > 0 - check org parameter */) {
					if(clientService.updateClientUserVerificationStatus(organisationId, clientId) != null) {
						
						/*
						 * if(clientService.getAllOrganisationsForClientUser(clientId) > 1) {
						 * Exist
						 * } else {
						 * New
						 * }
						 */
						ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Client User Account Verified for an Organisation");
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
			
		} catch(Exception exp) {
			exp.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
					"Request Failed",
					"Failed to accept invite");
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
//		ClientUser cuObj = clientService.findByEmail(client.getEmail());
		User user = userService.findByEmail(client.getEmail());
		if( user != null ) {
			
			HashMap<String, Object> list = new HashMap();
			if(user.getUserType() == UserType.CLIENT.getValue() & clientService.getAllOrganisationsForClientUser(user.getUserId()) == 0) {
				list.put("isNew",true);
			} else {
				list.put("isNew",false);
			}
			
			list.put("statusCode", APIStatusCode.CONFLICT.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "User Record Already Exists");
			list.put("userId",user.getUserId());
			list.put("userType",user.getUserType());
			
        	return new ResponseEntity<Object>(list,HttpStatus.OK);
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
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
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
	
	@ApiOperation(value = "Add new Client in an Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "New Client User Added in an Organisation Successfully"),
                    @ApiResponse(code = 201, message = "New Client User Record Created with in an Organisation Successfully")
            }
    )
	@PostMapping("/client/user/create")
	public ResponseEntity<Object> addClientUser(@RequestBody Map<String, Object> requestData, UriComponentsBuilder builder) {
		// if exist send email invite only
		// else create user and send email invite
		
		Integer organisationId = Integer.parseInt(String.valueOf(requestData.get("organisationId")));
		String email = String.valueOf(requestData.get("email"));
		String firstName =String.valueOf(requestData.get("firstName"));
		String lastName = String.valueOf(requestData.get("lastName"));
		Integer userRole = Integer.parseInt(String.valueOf(requestData.get("userRole")));
		Integer clientUserType = Integer.parseInt(String.valueOf(requestData.get("clientUserType")));
		
		ClientUser clientUserObj = new ClientUser();
		clientUserObj.setEmail(email);
		clientUserObj.setFirstName(firstName);
		clientUserObj.setLastName(lastName);;
		clientUserObj.setUserType(Constants.UserType.CLIENT.getValue());
		
		ClientOrganisation clientOrg = clientService.getClientOrganisationById(organisationId);
		
		ClientUser existClient = clientService.findByEmail(email);
		List<ClientAssociation> clientUsers = clientService.getAllClientUsersInOrganisation(organisationId);
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
				
				if(clientUsers.size() < Constants.MAX_USER_COUNT) {
					
					//add- new client org associate with invite status
					clientService.addClientOrgAccountAssociation(organisationId, clientUserType, userRole, existClient, Constants.UserAccountStatus.INVITED.getValue(), Constants.VerificationStatus.NOT_VERIFIED.getValue());
					
					sendExistClientUserInviteEmail(clientOrg.getOrganisationName(), existClient , organisationId, clientUserType, userRole);
					
					HttpHeaders headers = new HttpHeaders();
			        ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Exist Client Invite Sent Successfully");
			        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.MAX_USERS_COUNT_ERROR.getValue(),"Failed","Maximum of "+Constants.MAX_USER_COUNT+" Client User Added in this Organisation");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
				}
				
				
			} catch(Exception exp) {
				exp.printStackTrace();
				ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Registering Invite to Exist Client User Failed");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			}
		} else {
			User existUser= userService.findByEmail(email);
			if(existUser != null && existUser.getUserType() == UserType.VENDOR.getValue()) {
				HashMap<String, Object> list = new HashMap();
				
				list.put("statusCode", APIStatusCode.CONFLICT.getValue());
				list.put("statusMessage", "Failed");
				list.put("responseMessage", "Vendor User Record Already Exists");
				list.put("userId",existUser.getUserId());
				list.put("userType",existUser.getUserType());
				
	        	return new ResponseEntity<Object>(list,HttpStatus.OK);
			}
			
			if(existUser == null && clientUsers.size() < Constants.MAX_USER_COUNT) {
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
				        sendNewClientUserInviteEmail(clientOrg.getOrganisationName(), clientUser , organisationId, clientUserType, userRole);
				        
				        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.CREATED);
		        	} catch(Exception exp) {
		        		exp.printStackTrace();
		        		ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Registering New Client User Failed");
			        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		        	}
		        }
			} else {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.MAX_USERS_COUNT_ERROR.getValue(),
		        		"Failed",
		        		"Maximum of "+Constants.MAX_USER_COUNT+" Client User Added in this Organisation");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
			
		}
			
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
			@RequestParam("hash") String hash,
			@RequestBody ClientOrganisation organisation ,
			UriComponentsBuilder builder) {
		
		String decryptedUser = SecurityAES.decrypt(hash);

		System.out.println("decrypt hash :"+hash);
		
		Map<String, Object> userData;
		try {
			userData = Utils.convertJsonToHashMap(decryptedUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.BAD_REQUEST.getValue(),
        			"Failed",
        			"Failed to Parse Request - Bad Request");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
		return registerClientOrganisation(Integer.parseInt(String.valueOf(userData.get("userId"))), organisation);
        
	}
	
	@ApiOperation(value = "New Client Organisation Registration For Existing Client")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful New Client Organisation Registered For Existing Client")
            }
    )
	@PostMapping("/client/org/multiple/register/") // its reusable for new and existing client organisation registration
	private ResponseEntity<Object> registerClientOrganisation(Integer clientId, ClientOrganisation organisation) {
		// TODO Auto-generated method stub
		if(clientService.checkOrganisationNameAvailable(organisation.getOrganisationName())) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.CONFLICT.getValue(),
        			"Failed",
        			"Client Organisation Name Already Exist");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
		ClientOrganisation clientorganisation = clientService.addClientOrganisation(clientId, organisation);
        if (clientorganisation == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Failed to Register Client Organisation");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HttpHeaders headers = new HttpHeaders();
//            headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(organisation.getClientOrganisationId()).toUri());
            
            HashMap<String, Object> list = new HashMap();
			
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Organisation Registered for verification");
			list.put("clientOrganisationId",clientorganisation.getClientOrganisationId());
			list.put("clientId",clientId);
			User userInfo = userService.findByIdAndUserType(clientId, UserType.CLIENT.getValue());
			try {
				list.put("authToken",getAuthToken(userInfo.getUsername(), userInfo.getPassword()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	return new ResponseEntity<Object>(list,HttpStatus.OK);
            
        }
	}
	
	private String getAuthToken(String username, String password) throws JsonParseException, JsonMappingException, IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	     
	    final String url = "http://127.0.0.1:8762/auth";
	    URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    AppUser user = new AppUser( username, password);
	    
	    
//	    HttpEntity<String> request = new HttpEntity<>(userJsonObj, headers);
	    
	    
	    ResponseEntity<String> result = restTemplate.postForEntity(uri, user/*headers*/, String.class);
	     
	    //Verify request succeed
//	    Assert.assertEquals(200, result.getStatusCodeValue());
	    
	    ObjectMapper objectMapper = new ObjectMapper();
        Token tokenObj = objectMapper.readValue(result.getBody(), Token.class);
	    
		return tokenObj.token;
	}

	private static class AppUser {
	    private String username, password;
	
	    public AppUser(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }
	
	    public String getUsername() {
	        return username;
	    }
	
	    public void setUsername(String username) {
	        this.username = username;
	    }
	
	    public String getPassword() {
	        return password;
	    }
	
	    public void setPassword(String password) {
	        this.password = password;
	    }
	
	}
	
	private static class Token {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
	
	@ApiOperation(value = "New Client Organisation Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful New Client Organisation Registered")
            }
    )
	@PostMapping("/client/org/register/complete")
	public ResponseEntity<Object> completeClientCompanyRegistration(
			@RequestParam("clientId") Integer clientId,
			@RequestBody ClientOrganisation organisation ,
			UriComponentsBuilder builder) {
		
		return registerClientOrganisation(clientId, organisation);
		
	}
	private void sendClientEmailVerification(ClientUser clientUser) {
		// TODO Auto-generated method stub
//		String content = securityAES.getRegisterEncodedURL(clientUser.getEmail(), clientUser.getClientId(), Constants.UserType.CLIENT.getValue()); // query format request with Base64 Encryption
//		System.out.println("content->"+content);
		
		HashMap<String, Object> userObj = new HashMap();
		
		userObj.put("email", clientUser.getEmail());
		userObj.put("userId", clientUser.getClientId());
		userObj.put("userType", Constants.UserType.CLIENT.getValue());
		
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		String encryptClientUser = SecurityAES.encrypt(responseJsonString);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/register-organization?email="+clientUser.getEmail()
		+"&hash="+ encryptClientUser
		+"&expiry="+Utils.getLinkValidityTime(); // AES algorithm
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

	private void sendExistClientUserInviteEmail(String organisationName, ClientUser clientUser, Integer organisationId, Integer clientUserType,
			Integer userRole) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> userObj = new HashMap();
		
		userObj.put("email", clientUser.getEmail());
		userObj.put("userId", clientUser.getClientId());
		userObj.put("userType", Constants.UserType.CLIENT.getValue());
		userObj.put("organisationId", organisationId);
		userObj.put("organisationName", organisationName);
		
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		
		String encryptUser = SecurityAES.encrypt(responseJsonString);
		
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/client-accept-invite?email="+clientUser.getEmail()
		+"&userType="+Constants.UserType.CLIENT.getValue()
		+"&hash="+ encryptUser
		+"&expiry="+Utils.getLinkValidityTime();
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendInviteAcceptEmailWithAttachment(clientUser.getEmail(), clientUser.getFirstName()+" "+clientUser.getLastName(), organisationName , content);
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

	//Need to change like Registration flow
	private void sendNewClientUserInviteEmail(String organisationName, ClientUser clientUser, Integer organisationId, Integer clientUserType,
			Integer userRole) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> userObj = new HashMap();
		
		userObj.put("email", clientUser.getEmail());
		userObj.put("userId", clientUser.getClientId());
		userObj.put("userType", Constants.UserType.CLIENT.getValue());
		userObj.put("organisationId", organisationId);
		userObj.put("organisationName", organisationName);
		
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		
		String encryptUser = SecurityAES.encrypt(responseJsonString);
		
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/accept-invite?email="+clientUser.getEmail()
		+"&userType="+Constants.UserType.CLIENT.getValue()
		+"&hash="+ encryptUser
		+"&expiry="+Utils.getLinkValidityTime();
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//			springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendInviteAcceptEmailWithAttachment(clientUser.getEmail(), clientUser.getFirstName()+" "+clientUser.getLastName(), organisationName , content);
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
		
	@ApiOperation(value = "Vendor Registration Email Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Registration Email Implementation")
            }
    )
	@PostMapping("/client/user/registration/email")
	public ResponseEntity<Object> vendorUserExists(@RequestBody ClientUser client) {
		ClientUser clientUser = clientService.findById(client.getClientId());
		
		if(clientUser != null ) {
			sendClientEmailVerification(clientUser);
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Email Sent Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.NOT_FOUND.getValue(),
	        		"RESOURCE_NOT_FOUND",
	        		"Vendor User Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
	}
}
