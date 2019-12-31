package tech.torbay.securityservice.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.service.UserService;
import tech.torbay.securityservice.service.VendorService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;
import tech.torbay.securityservice.utils.QueryStringCreator;
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
			@RequestBody VendorOrganisation vendorOrganisation, UriComponentsBuilder builder) {
		
		String decryptedUser = SecurityAES.decrypt(hash);

		System.out.println("decrypt hash :"+hash);
		
		Map<String, Object> userData;
		try {
			userData = Utils.convertJsonToHashMap(decryptedUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Failed to Parse Request - Bad Request");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
        VendorOrganisation vendorOrg= vendorService.addVendorOrgnisation(Integer.parseInt(String.valueOf(userData.get("userId"))), vendorOrganisation);
        if (vendorOrg == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create Vendor Organisation");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"New Vendor Organisation Created Successfully");
			return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.OK);
        }
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
		
		if(vendorService.findByEmail(vendorUser.getEmail()) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Vendor User Already Exist");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			
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
			Map<String, Object> userData =  Utils.convertJsonToHashMap(decryptedUser);
			
			Integer vendorUserId = Integer.parseInt(String.valueOf(userData.get("userId")));
			String email = String.valueOf(userData.get("email"));
			Integer organisationId = Integer.parseInt(String.valueOf(userData.get("organisationId")));
			
			VendorUser vendorUser = vendorService.findByVendorUserId(vendorUserId);
			
			if(vendorUser != null ) {
				
				vendorUser.setAccountVerificationStatus(Constants.VerificationStatus.VERIFIED.getValue());
				vendorUser.setAccountStatus(Constants.UserAccountStatus.ACTIVE.getValue());
				
				vendorUser = vendorService.saveVendorUser(vendorUser);
				
				if(vendorUser != null ) {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.REQUEST_SUCCESS.getValue(),
			        		"Success",
			        		"Vendor User Account Verified Successfully");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.REQUEST_FAILED.getValue(),
			        		"Failed",
			        		"Vendor User Account Verification Failed");
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
					APIStatusCode.BAD_REQUEST.getValue(),
					"Request Failed",
					"Invalid Request Error");
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
		
		if(existVendorUserObj != null ) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor User Already Exists");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			List<VendorUser> vendorUsers = vendorService.getAllVendorUsersInOrganisation(vendorUser.getVendorOrganisationId());
			if(vendorUsers.size() < Constants.MAX_USER_COUNT) {
				vendorUser.setUserType(Constants.UserType.VENDOR.getValue());
				VendorUser vendor_user = vendorService.createVendorUser(vendorUser);
				VendorOrganisation vendorOrg = vendorService.getVendorOrganisationById(vendorUser.getVendorOrganisationId());
				
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
							APIStatusCode.NOT_FOUND.getValue(),
			        		"RESOURCE_NOT_FOUND",
			        		"Vendor User Account Creation Failed");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
			} else {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.REQUEST_SUCCESS.getValue(),
		        		"Success",
		        		"Maximum of "+Constants.MAX_USER_COUNT+" Vendor User Added in this Organisation");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
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
		
		String responseJsonString = Utils.ClasstoJsonString(vendorUser);
		String encryptVendorUser = SecurityAES.encrypt(responseJsonString);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/register-organization?email="+vendorUser.getEmail()+"&hash="+ encryptVendorUser; // AES algorithm
		
		
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
		
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		
		String encryptUser = SecurityAES.encrypt(responseJsonString);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/accept-invite?email="+vendorUser.getEmail()+"&userType="+Constants.UserType.VENDOR.getValue()+"&hash="+ encryptUser;
		
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

} 