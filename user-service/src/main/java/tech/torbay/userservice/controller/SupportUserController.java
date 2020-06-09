package tech.torbay.userservice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.userservice.Utils.Utils;
import tech.torbay.userservice.config.SecurityAES;
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.constants.Constants.UserType;
import tech.torbay.userservice.email.SpringBootEmail;
import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.service.ClientService;
import tech.torbay.userservice.service.SupportUserService;
import tech.torbay.userservice.service.UserService;
import tech.torbay.userservice.service.VendorService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
public class SupportUserController {
	private static final Logger logger = LoggerFactory.getLogger(SupportUserController.class);
	
	@Autowired
	private SupportUserService supportUserService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private UserService userService;
	@Autowired
	private VendorService vendorService;
	
	@ApiOperation(value = "Organisation Activation / De-Activation implementation by Support User and alerts using Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Organisation Activation / De-Activation implementation")
            }
    )
	@PutMapping("/support/organisation/status")
	private ResponseEntity<Object> UpdateOragnisationActivationStatus(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer organisationId = Integer.parseInt(String.valueOf(requestData.get("organisationId")));
		Integer userType =  Integer.parseInt(String.valueOf(requestData.get("userType")));
		Integer activeStatus =  Integer.parseInt(String.valueOf(requestData.get("activeStatus")));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateOrganisationActivationStatus(organisationId, userType, activeStatus, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update active Status");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Orgnisation Activation Status updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "User Activation / De-Activation implementation by Support User and alerts using Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "User Activation / De-Activation implementation")
            }
    )
	@PutMapping("/support/user/status")
	private ResponseEntity<Object> UpdateUserActivationStatus(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer organisationId = Integer.parseInt(String.valueOf(requestData.get("organisationId")));
		Integer userId = Integer.parseInt(String.valueOf(requestData.get("userId")));
		Integer userType =  Integer.parseInt(String.valueOf(requestData.get("userType")));
		Integer activeStatus =  Integer.parseInt(String.valueOf(requestData.get("activeStatus")));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateUserActivationStatus(userId, organisationId, userType, activeStatus, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update active Status");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"User Activation Status updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Review Activation / De-Activation implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Review Activation / De-Activation implementation")
            }
    )
	@PutMapping("/support/review/status")
	private ResponseEntity<Object> InactiveReview(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer reviewRatingId = Integer.parseInt(String.valueOf(requestData.get("reviewRatingId")));
		Integer activeStatus =  Integer.parseInt(String.valueOf(requestData.get("activeStatus")));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateReviewActivationStatus(reviewRatingId, activeStatus, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update active Status");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Review Activation Status updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Update Client Corporation Name and Number implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Update Client Corporation Name and Number implementation")
            }
    )
	@PutMapping("/support/client/corporation/update")
	private ResponseEntity<Object> UpdateClientCorporationNumber(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer clientOrganisationId = Integer.parseInt(String.valueOf(requestData.get("clientOrganisationId")));
		String corporationName = String.valueOf(requestData.get("corporationName"));
		String corporationNumber =  String.valueOf(requestData.get("corporationNumber"));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateClientCorporationInformation(clientOrganisationId, corporationName, corporationNumber, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update client corporation information");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Client Corporation Information updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Project Activation / De-Activation Implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Activation / De-Activation Implementation")
            }
    )
	@PutMapping("/support/client/project/status")
	private ResponseEntity<Object> UpdateProjectActivationStatus(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
//		Integer clientOrganisationId = Integer.parseInt(String.valueOf(requestData.get("clientOrganisationId")));
		Integer projectId = Integer.parseInt(String.valueOf(requestData.get("projectId")));
		Integer activeStatus = Integer.parseInt(String.valueOf(requestData.get("activeStatus")));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateProjectActivationStatus(/*clientOrganisationId,*/ projectId, activeStatus, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update project active Status");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Project Activation Status updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "External Message Activation / De-Activation Implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "External Message  Activation / De-Activation Implementation")
            }
    )
	@PutMapping("/support/external/message/status")
	private ResponseEntity<Object> UpdateExternalMessageActivationStatus(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer externalMessageId = Integer.parseInt(String.valueOf(requestData.get("externalMessageId")));
		Integer activeStatus = Integer.parseInt(String.valueOf(requestData.get("activeStatus")));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateExternalMessageActivationStatus(externalMessageId, activeStatus, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update external message active Status");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"External Message Activation Status updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "External Message Comment Activation / De-Activation Implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "External Message Comment Activation / De-Activation Implementation")
            }
    )
	@PutMapping("/support/external/message/comment/status")
	private ResponseEntity<Object> UpdateExternalMessageCommentActivationStatus(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer externalMessageCommentId = Integer.parseInt(String.valueOf(requestData.get("externalMessageCommentId")));
		Integer activeStatus = Integer.parseInt(String.valueOf(requestData.get("activeStatus")));
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateExternalMessageCommentActivationStatus(externalMessageCommentId, activeStatus, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update external message active Status");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"External Message Comment Activation Status updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Update User Name implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Update User Name implementation")
            }
    )
	@PutMapping("/support/user/profile/update")
	private ResponseEntity<Object> UpdateUserProfile(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer organisationId = Integer.parseInt(String.valueOf(requestData.get("organisationId")));
		Integer	userId = Integer.parseInt(String.valueOf(requestData.get("userId")));
		Integer userType =  Integer.parseInt(String.valueOf(requestData.get("userType")));
		String firstName =  String.valueOf(requestData.get("firstName"));
		String lastName = String.valueOf(requestData.get("lastName"));
		Integer userRole = Integer.parseInt(String.valueOf(requestData.get("userRole")));
		Integer clientUserType = 0;
		if(userType == UserType.CLIENT.getValue()) {
			clientUserType = Integer.parseInt(String.valueOf(requestData.get("clientUserType")));
		}
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (!supportUserService.updateUserProfile(userId, organisationId, userType, firstName, lastName, userRole, clientUserType, supportUserId)) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update user profile");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"User Profile updated successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Update User Name implementation by Support User")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Update User Name implementation")
            }
    )
	@PostMapping("/support/user/profile/add")
	private ResponseEntity<Object> CreateUserProfile(@RequestBody Map<String, Object> requestData, UriComponentsBuilder builder) {
		// TODO Auto-generated method stub
		
		Integer organisationId = Integer.parseInt(String.valueOf(requestData.get("organisationId")));
		Integer userType =  Integer.parseInt(String.valueOf(requestData.get("userType")));
		String email =  String.valueOf(requestData.get("email"));
		String firstName =  String.valueOf(requestData.get("firstName"));
		String lastName = String.valueOf(requestData.get("lastName"));
		Integer userRole = Integer.parseInt(String.valueOf(requestData.get("userRole")));
		Integer clientUserType = 0;
		if(userType == UserType.CLIENT.getValue()) {
			clientUserType = Integer.parseInt(String.valueOf(requestData.get("clientUserType")));
		}
		Integer supportUserId =  Integer.parseInt(String.valueOf(requestData.get("supportUserId")));
		
		
		if (userType == UserType.CLIENT.getValue()) {
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
//					if Association Not-found/association-verification-pending/user-not-active  Send Invite
//					if(clientService.checkClientOrgAssociationFound(existClient.getClientId(), organisationId)) {
//						
//						
//						// Invite Sent
//						sendNewClientUserInviteEmail(existClient , organisationId, clientUserType, userRole);
//						
//						HttpHeaders headers = new HttpHeaders();
//				        ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Exist Client Invite Sent Successfully");
//				        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.CREATED);
//					} 
//					else {
//						//else return already associated
//						 ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_FAILED.getValue(),"Failed","Client Already Associated with Same Organisation");
//						 return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
//					}
					
//					 Invite Sent
					
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
					ClientUser clientUser = clientService.addClientAndAssociation(organisationId, clientUserType, userRole, clientUserObj);
			        
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
	    } else if(userType == UserType.VENDOR.getValue()){
	    	
	    	VendorUser vendorUser = new VendorUser();
	    	vendorUser.setEmail(email);
	    	vendorUser.setFirstName(firstName);
	    	vendorUser.setLastName(lastName);;
	    	vendorUser.setUserType(Constants.UserType.VENDOR.getValue());
	    	vendorUser.setVendorOrganisationId(organisationId);
//	    	vendorUser.set(organisationId);
			
	    	VendorUser existVendorUserObj = vendorService.findByEmail(vendorUser.getEmail());
			
			if(existVendorUserObj != null ) {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.CONFLICT.getValue(),
		        		"Failed",
		        		"Vendor User Already Exists");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			} else {
				List<VendorUser> vendorUsers = vendorService.getAllVendorUsersInOrganisation(vendorUser.getVendorOrganisationId());
				if(vendorUsers.size() < Constants.MAX_USER_COUNT) {
					vendorUser.setUserType(Constants.UserType.VENDOR.getValue());
					vendorUser.setAccountStatus(Constants.UserAccountStatus.INVITED.getValue());
					VendorUser vendor_user = vendorService.createVendorUser(vendorUser);
					VendorOrganisation vendorOrg = vendorService.getVendorOrgById(vendorUser.getVendorOrganisationId());
					
					if(vendor_user != null ) {
						ResponseMessage responseMessage = new ResponseMessage(
								APIStatusCode.REQUEST_SUCCESS.getValue(),
				        		"Success",
				        		"Vendor User Account Created Successfully");
						// Invite Sent
						sendNewVendorUserInviteEmail(vendor_user , vendorUser.getVendorOrganisationId(), vendorOrg.getCompanyName());
						
						return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
					} else {
						ResponseMessage responseMessage = new ResponseMessage(
								APIStatusCode.REQUEST_FAILED.getValue(),
				        		"Failed",
				        		"Vendor User Account Creation Failed");
						return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
					}
				} else {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.MAX_USERS_COUNT_ERROR.getValue(),
			        		"Failed",
			        		"Maximum of "+Constants.MAX_USER_COUNT+" Vendor User Added in this Organisation");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
			}
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"User Account Creation Failed");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
	    }
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
	
	// Need to change as Registration flow
		private void sendNewVendorUserInviteEmail(VendorUser vendorUser, Integer organisationId, String organisationName) {
			// TODO Auto-generated method stub

			HashMap<String, Object> userObj = new HashMap();
			
			userObj.put("email", vendorUser.getEmail());
			userObj.put("userId", vendorUser.getUserId());
			userObj.put("userType", Constants.UserType.VENDOR.getValue());
			userObj.put("organisationId", organisationId);
			userObj.put("organisationName", organisationName);
			
			String responseJsonString = Utils.ClasstoJsonString(userObj);
			
			String encryptUser = SecurityAES.encrypt(responseJsonString);
			
			String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/accept-invite?email="+vendorUser.getEmail()
			+"&userType="+Constants.UserType.VENDOR.getValue()
			+"&hash="+ encryptUser
			+"&expiry="+Utils.getLinkValidityTime();
			
			System.out.println("Sending Email...");
			SpringBootEmail springBootEmail = new SpringBootEmail();
//				springBootEmail.sendEmail(email);
			try {
				springBootEmail.sendInviteAcceptEmailWithAttachment(vendorUser.getEmail(), vendorUser.getFirstName()+" "+vendorUser.getLastName(),organisationName,content);
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
	
	@ApiOperation(value = "Get List of Client/ Vendor Unapproved Organisatiotn")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Search Results Fetched Successfully"),
            }
    )
	@GetMapping("/support/unapproved/organisations")
	public ResponseEntity<Object> getUnApprovedVendorAndClientList() {
		
		Map<String, Object> results = supportUserService.getUnApprovedVendorAndClientList();
        if (results == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to fetch upapproved organisation list");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
//			if(userType == Constants.UserType.CLIENT.getValue()) {
//				list.put("responseMessage", "UnApproved Client Organisation List Fetched Successfully");
//			} else if(userType == Constants.UserType.VENDOR.getValue()) {
//				list.put("responseMessage", "UnApproved Vendor Organisation List Fetched Successfully");
//			}
			list.put("responseMessage", "Un Approved Organisation List Fetched Successfully");
			list.put("results", results);
//			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
}
