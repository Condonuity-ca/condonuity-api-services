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
import tech.torbay.securityservice.constants.Constants.DeleteStatus;
import tech.torbay.securityservice.constants.Constants.NotificationType;
import tech.torbay.securityservice.constants.Constants.UserAccountStatus;
import tech.torbay.securityservice.constants.Constants.UserRole;
import tech.torbay.securityservice.constants.Constants.UserType;
import tech.torbay.securityservice.email.SpringBootEmail;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.service.UserService;
import tech.torbay.securityservice.service.VendorService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;
import tech.torbay.securityservice.utils.Utils;


@RestController
@RequestMapping("/api")
@Api(value = "Vendor Resource REST Endpoint", description = "Shows the vendor info")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "New Vendor Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully New Vendor Registred"),
                    @ApiResponse(code = 201, message = "Successfully New Vendor Registred")
            }
    )
	@PostMapping("/vendor/organisation/register")
	public ResponseEntity<Object> addVendorOrganisation(
			@RequestParam("hash") String hash, 
			@RequestBody Map<String, Object> vendorOrganisationData, UriComponentsBuilder builder) {
		
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
		
		return registerVendorOrganisation(Integer.parseInt(String.valueOf(userData.get("userId"))), vendorOrganisationData);
	}
	
	private ResponseEntity<Object> registerVendorOrganisation(Integer vendorId, Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		
		if(vendorService.checkOrganisationNameIsEmpty(vendorOrganisationData)) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.FIELD_VALIDATION_ERROR.getValue(),
        			"Failed",
        			"Vendor Organisation Name Field Is Empty");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		if(vendorService.checkLegalNameIsEmpty(vendorOrganisationData)) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.FIELD_VALIDATION_ERROR.getValue(),
        			"Failed",
        			"Vendor Organisation Legal Name Field Is Empty");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		if(vendorService.checkOrganisationNameAvailable(vendorOrganisationData)) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.CONFLICT.getValue(),
        			"Failed",
        			"Vendor Organisation Name Already Exist");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		if(vendorService.checkLegalNameAvailable(vendorOrganisationData)) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.CONFLICT.getValue(),
        			"Failed",
        			"Vendor Organisation Legal Name Already Exist");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
        VendorOrganisation vendorOrg= vendorService.addVendorOrgnisation(vendorId, vendorOrganisationData);
        if (vendorOrg == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create Vendor Organisation");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        VendorUser vendorUser = vendorService.findByVendorUserId(vendorId);
	        if(vendorUser != null) {
	        	sendVendorOrganisationVerificationPendingAlert(vendorUser.getEmail());
	        }
			 HashMap<String, Object> list = new HashMap();
				
				list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
				list.put("statusMessage", "Success");
				list.put("responseMessage", "New Vendor Organisation Created Successfully");
				list.put("vendorOrganisationId",vendorOrg.getVendorOrganisationId());
				list.put("vendorId",vendorId);
				User userInfo = userService.findByIdAndUserType(vendorId, UserType.VENDOR.getValue());
				try {
					list.put("authToken",getAuthToken(userInfo.getUsername(), userInfo.getPassword()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return new ResponseEntity<Object>(list,HttpStatus.OK);
        }
	}
	
	private void sendVendorOrganisationVerificationPendingAlert(String email) {
		// TODO Auto-generated method stub
		
//		String content = "Thank you for registering with Condonuity. Currently our team is reviewing your account details. You would get a confirmation once the account review is completed successfully..!"; 
		String content = "Your registration is successful and our team is reviewing your account. You will recieve an email notification once your account is activated"; 
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
		try {
			springBootEmail.sendRegistrationAlertForVerificationPending(email, content);
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
	
	@ApiOperation(value = "New Vendor Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully New Vendor Registred"),
                    @ApiResponse(code = 201, message = "Successfully New Vendor Registred")
            }
    )
	@PostMapping("/vendor/organisation/register/complete")
	public ResponseEntity<Object> completeVendorOrganisationRegistration(
			@RequestParam("vendorId") Integer vendorId, 
			@RequestBody Map<String, Object> vendorOrganisationData, UriComponentsBuilder builder) {
		
		return registerVendorOrganisation(vendorId, vendorOrganisationData);
	}

	@ApiOperation(value = "New Vendor User Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully New Vendor User Registred"),
                    @ApiResponse(code = 201, message = "Successfully New Vendor User Registred")
            }
    )
	@PostMapping("/vendor/user/register")
	public ResponseEntity<Object> addVendorUser(@RequestBody VendorUser vendorUser, UriComponentsBuilder builder) {
		
		// check vendor user already exist or not
		User userObj = userService.findByEmail(vendorUser.getEmail());
		if( userObj != null) {
			
			HashMap<String, Object> list = new HashMap();
			list.put("isNew",false);
			list.put("statusCode", APIStatusCode.CONFLICT.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "User Record Already Exists");
			list.put("userId",userObj.getUserId());
			list.put("userType",userObj.getUserType());
			
        	return new ResponseEntity<Object>(list,HttpStatus.OK);
			
		} else {
			VendorUser vendor_user = vendorService.addVendorUser(vendorUser);
	        if (vendor_user == null ) {
	        	ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.REQUEST_FAILED.getValue(),
		        		"Failed",
		        		"Failed to Create Vendor User");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	        } else {
//	        	HttpHeaders headers = new HttpHeaders();
//	          headers.setLocation(builder.path("/vendor/user/{id}").buildAndExpand(vendorUser.getVendorId()).toUri());
	        	ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.REQUEST_SUCCESS.getValue(),
		        		"Success",
		        		"New Vendor User Created Successfully");
	        	
	        	try {
		        	sendVendorEmailVerification(vendor_user);
		        	vendorService.SendAccountUpdateAlert(vendor_user, vendor_user.getVendorOrganisationId(), NotificationType.VENDOR_USER_PROFILE_INVITE.getValue());
		        } catch(Exception exp) {
		        	exp.printStackTrace();
		        }
	        	
	        	return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.OK);	
	        }
		}
        
        
	}
	
	@ApiOperation(value = "Vendor existance check with Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A vendor record exist already")
            }
    )
	@GetMapping("/vendor/user/{email}")
	public ResponseEntity<Object> vendorUserExists(@PathVariable("email") String email) {
		VendorUser vendorUser = vendorService.findByEmail(email);
		
		if(vendorUser != null ) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor User Already Exists");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.NOT_FOUND.getValue(),
	        		"RESOURCE_NOT_FOUND",
	        		"Vendor User Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "New Organisation Vendor user accept email invitation and verification takes place")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor User Account Invitation Verified")
            }
    )
	@PostMapping("/vendor/user/invite/accept")
	public ResponseEntity<Object> VendorUserVerified(@RequestBody Map<String, Object> requestData) {
		
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
			
			
			Integer vendorUserId = Integer.parseInt(String.valueOf(userData.get("userId")));
			String email = String.valueOf(userData.get("email"));
			Integer organisationId = Integer.parseInt(String.valueOf(userData.get("organisationId")));
			
			VendorUser vendorUser = vendorService.findByVendorUserId(vendorUserId);
			
			if(vendorUser != null ) {
				
				vendorUser = vendorService.saveVendorUser(vendorUser, hash);
				
				if(vendorUser != null ) {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.REQUEST_SUCCESS.getValue(),
			        		"Success",
			        		"Vendor User Account Verified Successfully");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.LINK_EXPIRED.getValue(),
			        		"Failed",
			        		"Invite Link Expired");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
				
			} else {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.NOT_FOUND.getValue(),
		        		"RESOURCE_NOT_FOUND",
		        		"Vendor User Account does not Exist");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
					"Request Failed",
					"Vendor User Account Verification Failed");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
		
	}
	
	@ApiOperation(value = "New Organisation Vendor user email invitation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor User Account Created and Invitation Sent")
            }
    )
	@PostMapping("/vendor/user/invite/register")
	public ResponseEntity<Object> SendVendorUserInvitation(@RequestBody VendorUser vendorUser) {
		
		
		VendorUser existVendorUserObj = vendorService.findByEmail(vendorUser.getEmail());
		
		VendorUser vendorUserObj = vendorService.findByEmail(vendorUser.getEmail());
		if(vendorUserObj != null && vendorUserObj.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
			HashMap<String, Object> response = new HashMap();
			response.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
			response.put("statusMessage", "User Account Deleted");
			response.put("responseMessage", "User Account Deleted from System By Admin");
			response.put("userId", vendorUserObj.getUserId());
			response.put("userType", UserType.VENDOR.getValue());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		
		if(vendorUser.getVendorOrganisationId() == null || vendorUser.getVendorOrganisationId() == 0) {
			HashMap<String, Object> response = new HashMap();
			response.put("statusCode", APIStatusCode.BAD_REQUEST.getValue());
			response.put("statusMessage", "Invalid Organisation Details");
			response.put("responseMessage", "Invalid Organisation Details");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		
		if(existVendorUserObj != null ) {
			
			if(existVendorUserObj.getVendorOrganisationId() == null || existVendorUserObj.getVendorOrganisationId() == 0) {
				//not yet registered
				vendorUser.setUserId(existVendorUserObj.getUserId());
//				Common invite logic added for existing
//				vendorService.SendVendorNewRExistingUserInviteAlert(vendorUser, NotificationType.VENDOR_USER_PROFILE_INVITE.getValue());
				return SendExistVendorUserInvitation(vendorUser);
			} else {
			
				if(existVendorUserObj.getVendorOrganisationId() != null && existVendorUserObj.getVendorOrganisationId()>0 ) {
					VendorOrganisation vendorOrg = vendorService.findByVendorOrgId(existVendorUserObj.getVendorOrganisationId());
					//registered but not yet approved by admin
					if(vendorOrg.getActiveStatus() == UserAccountStatus.INVITED.getValue()) {
						vendorUser.setUserId(existVendorUserObj.getUserId());
						//Common invite logic added for existing
//						vendorService.SendVendorNewRExistingUserInviteAlert(vendorUser, NotificationType.VENDOR_USER_PROFILE_INVITE.getValue());
						return SendExistVendorUserInvitation(vendorUser);
					} else if(existVendorUserObj.getAccountStatus() == UserAccountStatus.INACTIVE.getValue() 
							&& existVendorUserObj.getDeleteStatus() == DeleteStatus.ACTIVE.getValue()) {
						// Newly added need to check - if client flow ok or not
						// Existing User - if inactive can re-invite by any vendor organisation flow newly added
						vendorUser.setUserId(existVendorUserObj.getUserId());
//						Common invite logic added for existing
//						vendorService.SendVendorNewRExistingUserInviteAlert(vendorUser, NotificationType.VENDOR_USER_PROFILE_INVITE.getValue());
						return SendExistVendorUserInvitation(vendorUser);
					}
				}
					
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.CONFLICT.getValue(),
		        		"Failed",
		        		"Vendor User Already Exists");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
		} else {
			User existUser= userService.findByEmail(vendorUser.getEmail());
			if(existUser != null && existUser.getUserType() == UserType.CLIENT.getValue()) {
				HashMap<String, Object> list = new HashMap();
				
				list.put("statusCode", APIStatusCode.CONFLICT.getValue());
				list.put("statusMessage", "Failed");
				list.put("responseMessage", "Client User Record Already Exists");
				list.put("userId",existUser.getUserId());
				list.put("userType",existUser.getUserType());
				
	        	return new ResponseEntity<Object>(list,HttpStatus.OK);
			}

			List<VendorUser> vendorUsers = vendorService.getAllVendorUsersInOrganisation(vendorUser.getVendorOrganisationId());
			if(vendorUsers.size() < Constants.MAX_USER_COUNT) {
				vendorUser.setUserType(Constants.UserType.VENDOR.getValue());
				vendorUser.setAccountStatus(Constants.UserAccountStatus.INVITED.getValue());
				vendorUser.setAccountVerificationStatus(Constants.UserAccountStatus.INVITED.getValue());
				vendorUser.setDeleteStatus(Constants.DeleteStatus.ACTIVE.getValue());
				int users = vendorService.getActiveOrInvitedVendorUsers(vendorUser.getVendorOrganisationId());
		        if(users == 0) {
		        	vendorUser.setUserRole(UserRole.ADMIN.getValue());
		        }
				VendorUser vendor_user = vendorService.createVendorUser(vendorUser);
				VendorOrganisation vendorOrg = vendorService.getVendorOrganisationById(vendorUser.getVendorOrganisationId());
				
				if(vendor_user != null ) {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.REQUEST_SUCCESS.getValue(),
			        		"Success",
			        		"Vendor User Account Created Successfully");
					// Invite Sent
					sendNewVendorUserInviteEmail(vendor_user , vendorUser.getVendorOrganisationId(), vendorOrg.getCompanyName());
					vendorService.SendVendorNewRExistingUserInviteAlert(vendor_user, NotificationType.VENDOR_USER_PROFILE_INVITE.getValue());
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
	}
	
	public ResponseEntity<Object> SendExistVendorUserInvitation(@RequestBody VendorUser vendorUser) {
		
		List<VendorUser> vendorUsers = vendorService.getAllVendorUsersInOrganisation(vendorUser.getVendorOrganisationId());
		if(vendorUsers.size() < Constants.MAX_USER_COUNT) {
			vendorUser.setUserType(Constants.UserType.VENDOR.getValue());
			vendorUser.setAccountStatus(Constants.UserAccountStatus.INVITED.getValue());
			vendorUser.setAccountVerificationStatus(Constants.UserAccountStatus.INVITED.getValue());
			vendorUser.setDeleteStatus(Constants.DeleteStatus.ACTIVE.getValue());
			int users = vendorService.getActiveOrInvitedVendorUsers(vendorUser.getVendorOrganisationId());
	        if(users == 0) {
	        	vendorUser.setUserRole(UserRole.ADMIN.getValue());
	        }
			VendorUser vendor_user = vendorService.updateVendorUser(vendorUser);
			VendorOrganisation vendorOrg = vendorService.getVendorOrganisationById(vendorUser.getVendorOrganisationId());
			
			if(vendor_user != null ) {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.REQUEST_SUCCESS.getValue(),
		        		"Success",
		        		"Vendor User Invited Successfully");
				// Invite Sent
				sendNewVendorUserInviteEmail(vendor_user , vendorUser.getVendorOrganisationId(), vendorOrg.getCompanyName());
				vendorService.SendVendorNewRExistingUserInviteAlert(vendor_user, NotificationType.VENDOR_USER_PROFILE_INVITE.getValue());
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
	
//	@ApiOperation(value = "Fetching All vendor users details")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "Successful All Vendor Details")
//            }
//    )
//    @GetMapping("/vendor/users")
//    public List<VendorUser> getAllVendorUsers() {
//
//       return vendorService.findAll();   }
	
	private void sendVendorEmailVerification(VendorUser vendorUser) {
		// TODO Auto-generated method stub
//		String content = securityAES.getRegisterEncodedURL(vendorUser.getEmail(), vendorUser.getUserId(), Constants.UserType.VENDOR.getValue());
		
		HashMap<String, Object> userObj = new HashMap();
		
		userObj.put("email", vendorUser.getEmail());
		userObj.put("userId", vendorUser.getUserId());
		userObj.put("userType", Constants.UserType.VENDOR.getValue());
		userObj.put("expiry", Utils.getLinkValidityTime());
		
//		String responseJsonString = Utils.ClasstoJsonString(vendorUser);
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		String encryptVendorUser = SecurityAES.encrypt(responseJsonString);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/register-organization?email="+vendorUser.getEmail()
		+"&hash="+ encryptVendorUser
		+"&expiry="+Utils.getLinkValidityTime(); // AES algorithm
		
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendWelcomeEmailWithAttachment(vendorUser.getEmail(), content);
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
		userObj.put("expiry", Utils.getLinkValidityTime());
		
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		
		String encryptUser = SecurityAES.encrypt(responseJsonString);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/accept-invite?email="+vendorUser.getEmail()
		+"&userType="+Constants.UserType.VENDOR.getValue()
		+"&hash="+ encryptUser
		+"&expiry="+Utils.getLinkValidityTime();
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//			springBootEmail.sendEmail(email);
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

	@ApiOperation(value = "Vendor Registration Email Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Registration Email Implementation")
            }
    )
	@PostMapping("/vendor/user/registration/email")
	public ResponseEntity<Object> resendRegistrationEmail(@RequestBody VendorUser vendor) {
		VendorUser vendorUser = vendorService.findByVendorUserId(vendor.getUserId());
		
		if(vendorUser != null ) {
			sendVendorEmailVerification(vendorUser);
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
	
	@ApiOperation(value = "Fetching All Vendor Available Profiles in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Vendor profiles fetched successfully in Condonuity Application")
            }
    )
	@GetMapping("/vendor/available/profiles")
	public ResponseEntity<Object> getAllVendorProfilesForVendorRegistration() {
		List<Object> list = vendorService.getAllVendorProfilesForVendorRegistration();
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Vendor Profiles in Condonuity Application fetched successfully");
			response.put("vendorProfiles", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
} 