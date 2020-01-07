package tech.torbay.securityservice.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.apache.catalina.authenticator.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.constants.Constants.UserAccountStatus;
import tech.torbay.securityservice.constants.Constants.UserType;
import tech.torbay.securityservice.constants.Constants.VerificationStatus;
import tech.torbay.securityservice.constants.Token;
import tech.torbay.securityservice.email.SpringBootEmail;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.SupportUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.service.ClientService;
import tech.torbay.securityservice.service.SupportService;
import tech.torbay.securityservice.service.UserService;
import tech.torbay.securityservice.service.VendorService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;
import tech.torbay.securityservice.utils.QueryStringCreator;
import tech.torbay.securityservice.utils.Utils;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping("/users")
//    public List<User> getUsers() {
//
//        logger.info("Processing Users Request");
//
//        List<User> users = userService.findAll();
//
//        logger.info("Processed User Request");
//
//        return users;
//    }
    
    @Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private SupportService supportService;
	
	@ApiOperation(value = "Fetch A User Basic Details in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A User details fetched successfully")
            }
    )
	@GetMapping("/user/{userType}/{id}")
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
	@PostMapping("/user/login")
	public ResponseEntity<Object> getUserByLogin(@RequestBody User user, UriComponentsBuilder builder) {
		User userInfo = userService.Login(user.getUsername(), user.getPassword());
		
		try {
//		String loginInfo = "";
		logger.info("userInfo : "+userInfo);
		
		if(userInfo != null) {
			
			String Token = getAuthToken(user.getUsername(), user.getPassword());
			
			if(userInfo.getUserType() == 1) {
				
				ClientUser clientInfo = clientService.findById(userInfo.getUserId());
				logger.info("clientInfo : "+clientInfo);
				if(clientInfo != null) { 
					//
					
					if(clientService.getAllOrganisationsForClientUser(clientInfo.getClientId()) > 0) {
						
						HashMap<String, Object> list = new HashMap();
						list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
						list.put("statusMessage", "Success");
						list.put("responseMessage", "Client details fetched successfully");
						list.put("userDetails", clientInfo);
						list.put("authToken", Token);
						
						return new ResponseEntity<>(list, HttpStatus.OK);
					} else {
						ResponseMessage responseMessage = new ResponseMessage(
								APIStatusCode.REQUEST_FAILED.getValue(),
				        		"Failed",
				        		"Client Organisation Not registered for this User");
						return new ResponseEntity<>(responseMessage, HttpStatus.OK);
					}
					
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
				VendorOrganisation vendorOrgInfo = new VendorOrganisation();
				if(vendorUserInfo.getVendorOrganisationId() != 0) {
				vendorOrgInfo = vendorService.findByVendorOrgId(vendorUserInfo.getVendorOrganisationId());
				logger.info("vendorOrgInfo"+vendorOrgInfo);
				}
				
					if (vendorUserInfo != null ) {
					
						if(vendorUserInfo.getAccountStatus() == UserAccountStatus.ACTIVE.getValue()) {
							HashMap<String, Object> list = new HashMap();
							list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
							list.put("statusMessage", "Success");
							list.put("responseMessage", "Vendor User details fetched successfully");
							list.put("userDetails", vendorUserInfo);
							list.put("authToken", Token);
							return new ResponseEntity<>(list, HttpStatus.OK);
						} else {
							ResponseMessage responseMessage = new ResponseMessage(
									APIStatusCode.REQUEST_FAILED.getValue(),
					        		"Failed",
					        		"Inactive User Information");
							return new ResponseEntity<>(responseMessage, HttpStatus.OK);
						}
					
					
//					list.put("vendorOrgDetails",vendorOrgInfo);
					
				} 
//					else if(vendorUserInfo != null && vendorUserInfo.getAccountStatus() ==  VerificationStatus.NOT_VERIFIED.getValue()) {
//					HashMap<String, Object> list = new HashMap();
//					list.put("statusMessage", "User need to set New Password");
//					list.put("responseMessage", "Please reset your password");
//					list.put("userDetails", vendorUserInfo);
////					list.put("vendorOrgDetails",vendorOrgInfo);
//					
//					if(vendorUserInfo.getVendorOrganisationId() != 0) {
//						list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
////						list.put("vendorOrgDetails",vendorOrgInfo);
//					} else {
//						list.put("statusCode", APIStatusCode.AUTHENTICATION_FAILED.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
//					}
//					
//					return new ResponseEntity<>(list, HttpStatus.OK);
//				}
			} else if(userInfo.getUserType() == 3) {
				
				SupportUser supportUserInfo = supportService.findBySupportUserId(userInfo.getUserId());
					if (supportUserInfo != null) {
					
					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
					list.put("statusMessage", "Success");
					list.put("responseMessage", "Support User details fetched successfully");
					list.put("userDetails", supportUserInfo);
					list.put("authToken", Token);
					
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
	@GetMapping("/user/verify/{email}")
	public ResponseEntity<Object> userExists(@Valid @PathVariable("email") @Email(message = "Email must be a valid email address") String email) {
		try {
			if(isValid(email)) {
				User user = userService.findByEmail(email);
				QueryStringCreator queryStringCreator = new QueryStringCreator();
				// check email 
				//	- registered or not
				//	- password reseted or not
				//	- active/inactive
				
				if(user != null) {
					if(user.getPassword() == null || user.getPassword().trim().length() == 0) {
						
						// Send Email Alert to Reset Password
						
						HashMap<String, Object> response = new HashMap();
						
						response.put("statusCode", APIStatusCode.AUTHENTICATION_FAILED.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
						response.put("statusMessage", "User need to set New Password");
						response.put("responseMessage", "Please reset your password");
						response.put("action", queryStringCreator.getResetPasswordEncodedURL(user));
						
						return new ResponseEntity<Object>(response, HttpStatus.OK);
					}
//					ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","User Already Exists");
					HashMap<String, Object> response = new HashMap();
					response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
					response.put("statusMessage", "Success");
					response.put("responseMessage", "User Already Exists");
					
					String userName = "";
					if(user.getUserType().equals(tech.torbay.securityservice.constants.Constants.UserType.CLIENT.getValue())) {
						ClientUser clientUser = clientService.findById(user.getUserId());
						userName = clientUser.getFirstName()+" "+clientUser.getLastName();
					} else if(user.getUserType().equals(tech.torbay.securityservice.constants.Constants.UserType.VENDOR.getValue())) {
						VendorUser vendorUser = vendorService.findByVendorUserId(user.getUserId());
						userName = vendorUser.getFirstName()+" "+vendorUser.getLastName();
					} else if(user.getUserType().equals(tech.torbay.securityservice.constants.Constants.UserType.SUPPORT_USER.getValue())) {
						SupportUser supportUser = supportService.findBySupportUserId(user.getUserId());
						userName = supportUser.getFullName();
					}
					
					response.put("username", userName);
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
			} else {
				logger.info("Invalid Email Address");
				ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.BAD_REQUEST.getValue(),"Invalid Email Address","Please check email address");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
			
		} catch(Exception exp) {
			logger.info("User Email Validation Error");
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
	}
	
	public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	@ApiOperation(value = "New User password reset implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "New User password reset successfully")
            }
    )
	@PostMapping("/user/resetPassword")
	public ResponseEntity<Object> resetPassword(@RequestBody Map<String, Object> requestData) {
	    
		try {
			String hash = String.valueOf(requestData.get("hash"));
			String password = String.valueOf(requestData.get("password"));
			Boolean isFirstTimeUser = Boolean.parseBoolean(String.valueOf(requestData.get("isNewUser")));
			
			String decryptedUser = SecurityAES.decrypt(hash);
			Map<String, Object> userData =  Utils.convertJsonToHashMap(decryptedUser);
			
			Integer userType = Integer.parseInt(String.valueOf(userData.get("userType")));
			Integer userId = Integer.parseInt(String.valueOf(userData.get("userId")));
			if(isFirstTimeUser) {
				// store terms and condition datetime
				
				if (userService.resetPassword(userId, userType, password) == null) {
			    	ResponseMessage responseMessage = new ResponseMessage(
			    			APIStatusCode.REQUEST_FAILED.getValue(),
			        		"Failed",
			        		"Failed to reset password");
			    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			    } else {
			    	
			    	try {
			    		userService.updateTermsAcceptedTimestamp(userId, userType);
			    	} catch(Exception exp) {
			    		exp.printStackTrace();
			    	}
			    	
			    	HashMap<String, Object> response = new HashMap();
					
					response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
					response.put("statusMessage", "Success");
					response.put("responseMessage", "Password reset successfully");
					response.put("userId", userId);
					response.put("userType", userType);
					//return userId, userType
					return new ResponseEntity<Object>(response, HttpStatus.OK);
			    }
				//return userId, userType
			} else {
				System.out.println("decrypt hash :"+hash);
				
				return ResetPassword(userId, userType, password);
			} 
			
		} catch(Exception exp) {
			exp.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to reset password");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
	}
	
	private ResponseEntity<Object> ResetPassword(Integer userId, Integer userType, String password) {
		// TODO Auto-generated method stub
		if (userService.resetPassword(userId, userType, password) == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to reset password");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Password reset successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}


	@ApiOperation(value = "Send Sample Welcome Email")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "New User password reset successfully")
			}
			)
	@PostMapping("/user/welcomemail/{email}")
	public ResponseEntity<Object> SendWelcomeEmail(@PathVariable("email") String email) {
		
		logger.info("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendWelcomeEmailWithAttachment(email,"Sample_Welcome_Email"+Utils.getLinkValidityTime());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Done");
		
		//1
//		TLSEmail tlsEmail = new TLSEmail();
//		tlsEmail.main(new String[] {});
//		tlsEmail.sendEmail(tlsEmail.getSession(), "prakash@torbay.tech", "subject", "body");
		//2
//		new SendEmail();
		//3
//		SSLEmail sslEmail = new SSLEmail();
//		sslEmail.sendEmail(sslEmail.getSession(), "prakash.clds@gmail.com", "subject : SSLEmail Testing Subject", "body : SSLEmail Testing Body");
		
		ResponseMessage responseMessage = new ResponseMessage(
    			APIStatusCode.REQUEST_FAILED.getValue(),
        		"Success",
        		"Welcome Mail Sent Successfully");
		return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Decrypt Data in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Decrypted successfully")
            }
    )
	@PostMapping("/secure/decrypt")
	public ResponseEntity<String> decrypt(@RequestBody String strToDecrypt) {
		String decryptedData = SecurityAES.decrypt(strToDecrypt);
		return new ResponseEntity<String>(decryptedData, HttpStatus.OK);
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
	 
	 @ApiOperation(value = "Send Password reset link to User Email")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "Password reset link sent to the User Email")
	            }
	    )
		@GetMapping("/user/forgotpwd/{email}")
		public ResponseEntity<Object> forgotPassword(@Valid @PathVariable("email") @Email(message = "Email must be a valid email address") String email) {
			try {
				if(isValid(email)) {
					User user = userService.findByEmail(email);
					if(user != null) {
						
						HashMap<String, Object> response = new HashMap();
						
						
						try {
				        	sendForgotPasswordResetLink(email, user.getUserId(), user.getUserType());
				        	response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
							response.put("statusMessage", "Success");
							response.put("responseMessage", "Password reset link sent to the User Email");
				        } catch(Exception exp) {
				        	exp.printStackTrace();
				        	response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
							response.put("statusMessage", "Failed");
							response.put("responseMessage", "Failed to send reset link to User Email");
				        	return new ResponseEntity<Object>(response, HttpStatus.OK);
				        }
						
						return new ResponseEntity<Object>(response, HttpStatus.OK);
					} else {
						ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
						return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
					}
				} else {
					logger.info("Invalid Email Address");
					ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.BAD_REQUEST.getValue(),"Invalid Email Address","Please check email address");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
				
			} catch(Exception exp) {
				logger.info("User Email Validation Error");
				ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
		}


	private void sendForgotPasswordResetLink(String email, Integer userId, Integer userType) {
		// TODO Auto-generated method stub
		HashMap<String, Object> userObj = new HashMap();
		
		userObj.put("email", email);
		userObj.put("userId", userId);
		userObj.put("userType", userType);
		
		String responseJsonString = Utils.ClasstoJsonString(userObj);
		
		String encryptUser = SecurityAES.encrypt(responseJsonString);
		
		System.out.println("encrypt hash :"+encryptUser);
		
		String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/create-password?email="+email
				+"&hash="+encryptUser
				+"&expiry="+Utils.getLinkValidityTime(); // AES algorithm
//		System.out.println("contentAES Encrypt->"+content);
//		System.out.println("contentAES Decrypt->"+SecurityAES.decrypt(encryptClientUser));
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendPasswordResetEmailWithAttachment(email, content);
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

	 @ApiOperation(value = "Fetching A Client Details In an Organisation")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "A client details fetched successfully")
	            }
	    )
		@GetMapping("/service/cities")
		public ResponseEntity<Object> getClientUserByIdAndOrgId() {
		
		 List<ServiceCities> serviceCities = userService.findAllServiceCities();
		 
		 HashMap<String, Object> list = new HashMap();
		 if(serviceCities != null) {
		 	list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
		 	list.put("statusMessage", "Success");
		 	list.put("responseMessage", "List of All Service Cities fetched successfully");
		 	list.put("serviceCities",serviceCities);
		 	
		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 } else {
		 	
		 	list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
		 	list.put("statusMessage", "Failed");
		 	list.put("responseMessage", "Database Error");

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 }

		}
}
