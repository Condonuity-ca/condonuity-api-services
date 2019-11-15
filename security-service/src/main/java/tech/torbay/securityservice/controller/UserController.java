package tech.torbay.securityservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.constants.Constants.UserAccountStatus;
import tech.torbay.securityservice.constants.Constants.VerificationStatus;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.service.ClientService;
import tech.torbay.securityservice.service.UserService;
import tech.torbay.securityservice.service.VendorService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public List<User> getUsers() {

        logger.info("Processing Users Request");

        List<User> users = userService.findAll();

        logger.info("Processed User Request");

        return users;
    }
    
    @Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private VendorService vendorService;
	
	
	@ApiOperation(value = "Fetch A User Basic Details in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A User details fetched successfully")
            }
    )
	@GetMapping("user/{userType}/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id, @PathVariable("userType") Integer userType) {
		User user = userService.findByIdAndUserType(id, userType);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Condonuity User Login Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful user Login Details")
            }
    )
//	@PostMapping("user/login")
//	public ResponseEntity<User> getUserByLogin(@RequestBody User user, UriComponentsBuilder builder) {
//		User userInfo = userService.getUserByLogin(user.getUsername(), user.getPassword());
//		
//		if(userInfo.getUserType() == 1) {
//			
//		}
//        return new ResponseEntity<User>(userInfo, HttpStatus.OK);
//	}
	@PostMapping("user/login")
	public ResponseEntity<Object> getUserByLogin(@RequestBody User user, UriComponentsBuilder builder) {
		User userInfo = userService.Login(user.getUsername(), user.getPassword());
		
		try {
//		String loginInfo = "";
		System.out.println("userInfo : "+userInfo);
		
		if(userInfo != null) {
			if(userInfo.getUserType() == 1) {
				
				ClientUser clientInfo = clientService.findById(userInfo.getUserId());
				
				if(clientInfo != null) {
					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
					list.put("statusMessage", "Success");
					list.put("responseMessage", "Client details fetched successfully");
					list.put("userDetails", clientInfo);
					//1
//					TLSEmail tlsEmail = new TLSEmail();
//					tlsEmail.main(new String[] {});
//					tlsEmail.sendEmail(tlsEmail.getSession(), "prakash@torbay.tech", "subject", "body");
					//2
//					new SendEmail();
					//3
//					SSLEmail sslEmail = new SSLEmail();
//					sslEmail.sendEmail(sslEmail.getSession(), "prakash.clds@gmail.com", "subject", "body");
					
					return new ResponseEntity<>(list, HttpStatus.OK);
					
					} /*
						 * else if(clientInfo.getPassword().toString() == null ||
						 * clientInfo.getPassword().trim().length() == 0) {
						 * clientInfo.setPassword(null); HashMap<String, Object> list = new HashMap();
						 * list.put("statusCode", StatusCode.RESET_PASSWORD.getValue());
						 * list.put("statusMessage", "User need to set New Password");
						 * list.put("responseMessage", "Please reset your password");
						 * list.put("userDetails", clientInfo); return new ResponseEntity<>(list,
						 * HttpStatus.OK); }
						 */ /*
							 * else if(registrationStatus == UserAccountStatus.INACTIVE.getValue()) {
							 * ResponseMessage responseMessage = new ResponseMessage(
							 * APIStatusCode.REQUEST_FAILED.getValue(), "Failed", "Invalid Credentials");
							 * return new ResponseEntity<>(responseMessage, HttpStatus.OK); }
							 */
				
			} else if(userInfo.getUserType() == 2) {
				
				VendorUser vendorUserInfo = vendorService.findByVendorUserId(userInfo.getUserId());
//				VendorOrganisation vendorOrgInfo = new VendorOrganisation();
//				if(vendorUserInfo.getVendorOrganisationId() != 0) {
//				vendorOrgInfo = vendorService.findByVendorOrgId(vendorUserInfo.getVendorOrganisationId());
//				System.out.println(vendorOrgInfo);
//				}
				
				if(vendorUserInfo.getAccountStatus() == UserAccountStatus.ACTIVE.getValue()) {
					
					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
					list.put("statusMessage", "Success");
					list.put("responseMessage", "Vendor details fetched successfully");
					list.put("vendorUserDetails", vendorUserInfo);
//					list.put("vendorOrgDetails",vendorOrgInfo);
					
					return new ResponseEntity<>(list, HttpStatus.OK);
				} else if(vendorUserInfo.getAccountStatus() ==  VerificationStatus.NOT_VERIFIED.getValue()) {
					HashMap<String, Object> list = new HashMap();
					list.put("statusMessage", "User need to set New Password");
					list.put("responseMessage", "Please reset your password");
					list.put("vendorUserDetails", vendorUserInfo);
//					list.put("vendorOrgDetails",vendorOrgInfo);
					
					if(vendorUserInfo.getVendorOrganisationId() != 0) {
						list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
//						list.put("vendorDetails",vendorOrgInfo);
					} else {
						list.put("statusCode", APIStatusCode.AUTHENTICATION_FAILED.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
					}
					
					return new ResponseEntity<>(list, HttpStatus.OK);
				}
			}
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Invalid Credentials");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		ResponseMessage responseMessage = new ResponseMessage(
				APIStatusCode.REQUEST_FAILED.getValue(),
        		"Failed",
        		"Invalid Credentials");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} catch(Exception exp) {
			exp.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.SERVER_ERROR.getValue(),
	        		"Failed",
	        		"Invalid Credentials");
			return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@ApiOperation(value = "Returns All User Details as List in Condonuity Application")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "Successful All User Details")
//            }
//    )
//	@GetMapping("users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.findAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "User existance check with Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client record exist already")
            }
    )
	@GetMapping("user/verify/{email}")
	public ResponseEntity<Object> userExists(@PathVariable("email") String email) {
		User user = userService.findByEmail(email);
		
		// check email 
		//	- registered or not
		//	- password reseted or not
		//	- active/inactive
		
		if(user != null) {
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","User Already Exists");
			
			if(user.getPassword() == null || user.getPassword().trim().length() == 0) {
				
				// Send Email Alert to Reset Password
				
				HashMap<String, Object> response = new HashMap();
				
				response.put("statusCode", APIStatusCode.AUTHENTICATION_FAILED.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
				response.put("statusMessage", "User need to set New Password");
				response.put("responseMessage", "Please reset your password");
				response.put("action", getEncodedURL(user));
				
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
	}
	
	public String getEncodedURL(User user) {
		
		String query = UriComponentsBuilder.fromHttpUrl("http://localhost:8787/api/condonuity/user/resetPassword")
				.queryParam(encode("username"), encode(user.getUsername()))
				.queryParam(encode("userId"), encode(String.valueOf(user.getUserId())))
				.queryParam(encode("userType"), encode(String.valueOf(user.getUserType())))
				.toUriString(); 
		
		
		return query;
	}
	
	
	public String encode(String raw) {
	    return Base64.getUrlEncoder()
	            .withoutPadding()
	            .encodeToString(raw.getBytes(StandardCharsets.UTF_8));
	}
	
	@ApiOperation(value = "New User password reset implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "New User password reset successfully")
            }
    )
	@PostMapping("user/resetPassword")
	public ResponseEntity<Object> resetPassword(@RequestBody User user , UriComponentsBuilder builder) {
	    if (userService.resetPassword(user) == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to reset password");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Password reset successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	    
	}

}
