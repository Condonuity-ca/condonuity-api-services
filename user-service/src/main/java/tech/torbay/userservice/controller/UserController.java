package tech.torbay.userservice.controller;

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
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.StatusCode;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.service.ClientService;
import tech.torbay.userservice.service.UserService;
import tech.torbay.userservice.service.VendorService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

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
		System.out.println(userInfo);
		
		if(userInfo != null) {
			if(userInfo.getUserType() == 1) {
				
				ClientUser clientInfo = clientService.findById(userInfo.getUserId());
	//			return new ResponseEntity<>(clientInfo, HttpStatus.OK);
				
				// get User Registration Status
				int registrationStatus = 20/*clientService.getClientRegistrationStatus(clientInfo.getClientId())*/; 
				
				
				System.out.println(clientInfo);
				
				System.out.println("registrationStatus : "+registrationStatus);
				clientInfo.setUserRole(null);
				clientInfo.setUserId(null);// user clientId instead of userId
				if(registrationStatus == Constants.StatusCode.USER_ACTIVE.getValue()) {
					HashMap<String, Object> list = new HashMap();
					clientInfo.setPassword(null);
					list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
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
					
				} else if(clientInfo.getPassword().toString() == null || clientInfo.getPassword().trim().length() == 0) {
					clientInfo.setPassword(null);
					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", StatusCode.RESET_PASSWORD.getValue());
					list.put("statusMessage", "User need to set New Password");
					list.put("responseMessage", "Please reset your password");
					list.put("userDetails", clientInfo);
					return new ResponseEntity<>(list, HttpStatus.OK);
				} else if(registrationStatus == Constants.StatusCode.USER_INACTIVE.getValue()) {
					ResponseMessage responseMessage = new ResponseMessage(
			        		StatusCode.REQUEST_FAILED.getValue(),
			        		"Failed",
			        		"Invalid Credentials");
					return new ResponseEntity<>(responseMessage, HttpStatus.OK);
				}
				
			} else if(userInfo.getUserType() == 2) {
				
				VendorUser vendorUserInfo = vendorService.findByVendorUserId(userInfo.getUserId());
				System.out.println(vendorUserInfo);
				VendorOrganisation vendorOrgInfo = new VendorOrganisation();
				if(vendorUserInfo.getVendorId() != 0) {
				vendorOrgInfo = vendorService.findByVendorOrgId(vendorUserInfo.getVendorId());
				System.out.println(vendorOrgInfo);
				}
				
				vendorUserInfo.setPassword(null);
				vendorUserInfo.setCreatedDate(null);
				
				if(vendorUserInfo.getStatus() == Constants.StatusCode.USER_ACTIVE.getValue()) {
					
					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
					list.put("statusMessage", "Success");
					list.put("responseMessage", "Vendor details fetched successfully");
					list.put("vendorUserDetails", vendorUserInfo);
					list.put("vendorOrgDetails",vendorOrgInfo);
					
					return new ResponseEntity<>(list, HttpStatus.OK);
				} else if(vendorUserInfo.getStatus() == Constants.StatusCode.USER_REGISTER_INPROGRESS.getValue()) {
					HashMap<String, Object> list = new HashMap();
					list.put("statusMessage", "User need to set New Password");
					list.put("responseMessage", "Please reset your password");
					list.put("userDetails", vendorUserInfo);
					list.put("vendorDetails",vendorOrgInfo);
					
					if(vendorUserInfo.getVendorId() != 0) {
						list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
						list.put("vendorDetails",vendorOrgInfo);
					} else {
						list.put("statusCode", StatusCode.RESET_PASSWORD.getValue());
					}
					
					return new ResponseEntity<>(list, HttpStatus.OK);
				}
			}
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
	        		StatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Invalid Credentials");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		ResponseMessage responseMessage = new ResponseMessage(
        		StatusCode.REQUEST_FAILED.getValue(),
        		"Failed",
        		"Invalid Credentials");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		} catch(Exception exp) {
			exp.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
	        		StatusCode.SERVER_ERROR.getValue(),
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
			ResponseMessage responseMessage = new ResponseMessage(StatusCode.REQUEST_SUCCESS.getValue(),"Success","User Already Exists");
			
			if(user.getPassword() == null || user.getPassword().trim().length() == 0) {
				
				// Send Email Alert to Reset Password
				
				HashMap<String, Object> response = new HashMap();
				
				response.put("statusCode", StatusCode.RESET_PASSWORD.getValue());
				response.put("statusMessage", "User need to set New Password");
				response.put("responseMessage", "Please reset your password");
				response.put("action", getEncodedURL(user));
				
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(StatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
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
	
//	@ApiOperation(value = "Returns New user Registration success message")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "Successful New user Registration")
//            }
//    )
//	@PostMapping("user")
//	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder) {
//        boolean flag = userService.addUser(user);
//        if (flag == false) {
//        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//	}
	
//	@ApiOperation(value = "Returns user update success message")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "user details updated successfully")
//            }
//    )
//	@PutMapping("user")
//	public ResponseEntity<User> updateUser(@RequestBody User user) {
//		userService.updateUser(user);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
	
//	@ApiOperation(value = "Returns success message for user inactive")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "A user inactive successfully")
//            }
//    )
//	@DeleteMapping("user/{id}")
//	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
//		userService.deleteUser(id);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}	
	
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
	        		StatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to reset password");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	        		StatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Password reset successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	    
	}

}
