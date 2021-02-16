package tech.torbay.securityservice.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.securityservice.config.ConfigProperties;
import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.constants.Constants.DeleteStatus;
import tech.torbay.securityservice.constants.Constants.OrganisationAccountStatus;
import tech.torbay.securityservice.constants.Constants.UserAccountStatus;
import tech.torbay.securityservice.constants.Constants.UserType;
import tech.torbay.securityservice.email.SpringBootEmail;
import tech.torbay.securityservice.entity.Amenities;
import tech.torbay.securityservice.entity.ClientAssociation;
import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.PredefinedTags;
import tech.torbay.securityservice.entity.RegistrationLogs;
import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.SupportUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.UserInviteLogs;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.exception.BadRequestException;
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
	@Autowired
    private ConfigProperties configProperties;

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
		Utils.getLinkValidityTime();
		if(user == null) {
			new BadRequestException("Bad Request", "Failed", "Request Format Error");
		}

		User userInfo = userService.Login(user.getUsername(), user.getPassword());
		logger.info("userInfo : "+userInfo);
		try {
			if(userInfo != null) {
				if(userInfo.getIncorrectAttempt() > Constants.MAX_INCORRECT_LOGIN_ATTEMPT_COUNT) {
					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", APIStatusCode.ACCOUNT_BLOCKED_BY_MAX_INCORRECT_LOGIN_ATTEMPT.getValue());
					list.put("statusMessage", "Failed");
					list.put("responseMessage", "User Account Blocked Due to Maximum Incorrect Login Attempt");
					list.put("userId", userInfo.getUserId());
					list.put("userType", userInfo.getUserType());

					return new ResponseEntity<>(list, HttpStatus.OK);
				}
				String Token = getAuthToken(user.getUsername(), userInfo.getPassword());
				userService.updateLoginAttemptSuccess(user.getUsername());
				if(userInfo.getUserType() == 1) {

					ClientUser clientInfo = clientService.findById(userInfo.getUserId());
					if(clientInfo != null ) {

						if(clientInfo.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
							HashMap<String, Object> list = new HashMap();
							list.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
							list.put("statusMessage", "Failed");
							list.put("responseMessage", "User Account Deleted");
							list.put("userId", clientInfo.getClientId());
							list.put("userType", UserType.CLIENT.getValue());

							return new ResponseEntity<>(list, HttpStatus.OK);
						}
						int organisationSize = clientService.getAllOrganisationsForClientUser(clientInfo.getClientId());
						if(organisationSize > 0) {
							// check client has single organisation and its under verification or not
							if(organisationSize == 1) { // check organisation under verification alert from this check
								if(clientService.checkSingleOrganisationAccountVerificationStatus(clientInfo.getClientId())) {
									HashMap<String, Object> list = new HashMap();
									list.put("statusCode", APIStatusCode.REGISTRATION_UNDER_REVIEW.getValue());
									list.put("statusMessage", "Failed");
									list.put("responseMessage", "Registration Under Review");
									list.put("userId", clientInfo.getClientId());
									list.put("userType", UserType.CLIENT.getValue());

									return new ResponseEntity<>(list, HttpStatus.OK);
								}
							}

								if(clientService.checkIsClientActiveAtlestOneAccount(clientInfo.getClientId())) {
									if(clientService.checkIsClientAccountActive(clientInfo.getClientId())) {
										clientInfo = clientService.checkIsPrimaryOrganisationActive(clientInfo);
										HashMap<String, Object> list = new HashMap();
										list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
										list.put("statusMessage", "Success");
										list.put("responseMessage", "Client details fetched successfully");
										list.put("userDetails", clientInfo);
										list.put("userProfileImage", userService.getUserProfileImage(clientInfo.getClientId(), UserType.CLIENT.getValue()));
										list.put("authToken", Token);

										return new ResponseEntity<>(list, HttpStatus.OK);
									} else {
//										ResponseMessage responseMessage = new ResponseMessage(
//												APIStatusCode.INACTIVE_USER.getValue(),
//								        		"Failed",
//								        		"User Account Deleted");
//										return new ResponseEntity<>(responseMessage, HttpStatus.OK);
										HashMap<String, Object> list = new HashMap();
										list.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
										list.put("statusMessage", "Failed");
										list.put("responseMessage", "User Account Deleted or Inactive");
										list.put("userId", clientInfo.getClientId());
										list.put("userType", UserType.CLIENT.getValue());

										return new ResponseEntity<>(list, HttpStatus.OK);
									}

								} else {
									HashMap<String, Object> list = new HashMap();
									list.put("statusCode", APIStatusCode.NO_ACTIVE_ORGANISATION_FOUND.getValue());
									list.put("statusMessage", "Failed");
									list.put("responseMessage", "There is no active Organisation for this Client");
									list.put("userDetails", clientInfo);
									list.put("userProfileImage", userService.getUserProfileImage(clientInfo.getClientId(), UserType.CLIENT.getValue()));
									list.put("authToken", Token);

									return new ResponseEntity<>(list, HttpStatus.OK);
								}
						} else {
//								ResponseMessage responseMessage = new ResponseMessage(
//										APIStatusCode.CLIENT_ORGANISATION_NOT_FOUND.getValue(),
//						        		"Failed",
//						        		"Client Organisation Not registered for this User");
								// proceed option for client organisation registration
								// or
								// send registration mail again to proceed registration

							/*Hash Generation*/
							HashMap<String, Object> userObj = new HashMap();

							userObj.put("email", userInfo.getUsername());
							userObj.put("userId", userInfo.getUserId());
							userObj.put("userType", userInfo.getUserType());

							String responseJsonString = Utils.ClasstoJsonString(userObj);
							String encryptClientUserHash = SecurityAES.encrypt(responseJsonString);


							HashMap<String, Object> list = new HashMap();
							list.put("statusCode", APIStatusCode.ORGANISATION_NOT_FOUND.getValue());
							list.put("statusMessage", "Success");
							list.put("responseMessage", "Client Organisation Not registered for this User");
							list.put("userDetails", clientInfo);
//							list.put("authToken", Token);
							list.put("hash", encryptClientUserHash);
							return new ResponseEntity<>(list, HttpStatus.OK);
						}
					} else {
						ResponseMessage responseMessage = new ResponseMessage(
								APIStatusCode.NOT_FOUND.getValue(),
				        		"Failed",
				        		"User Record Not Found");
						return new ResponseEntity<>(responseMessage, HttpStatus.OK);
					}
				} else if(userInfo.getUserType() == 2) {
					VendorUser vendorUserInfo = vendorService.findByVendorUserId(userInfo.getUserId());

					if (vendorUserInfo != null ) {

						if(vendorUserInfo.getVendorOrganisationId() != null && vendorUserInfo.getVendorOrganisationId()  > 0) {
							VendorOrganisation vendorOrgInfo = vendorService.findByVendorOrgId(vendorUserInfo.getVendorOrganisationId());
							if(vendorOrgInfo.getActiveStatus() == UserAccountStatus.INVITED.getValue()) {
								HashMap<String, Object> list = new HashMap();
								list.put("statusCode", APIStatusCode.REGISTRATION_UNDER_REVIEW.getValue());
								list.put("statusMessage", "Failed");
								list.put("responseMessage", "Registration Under Review");
								list.put("userId", vendorUserInfo.getUserId());
								list.put("userType", vendorUserInfo.getUserType());
								return new ResponseEntity<>(list, HttpStatus.OK);
							}
							if(vendorOrgInfo.getDeleteStatus() == DeleteStatus.ACTIVE.getValue()) {
								if(vendorOrgInfo.getActiveStatus() == OrganisationAccountStatus.ACTIVE.getValue()) {
									if(vendorUserInfo.getAccountStatus() == UserAccountStatus.ACTIVE.getValue()) {
										if(vendorUserInfo.getDeleteStatus() == DeleteStatus.ACTIVE.getValue()) {
											HashMap<String, Object> list = new HashMap();
											list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
											list.put("statusMessage", "Success");
											list.put("responseMessage", "Vendor User details fetched successfully");
											list.put("userDetails", vendorUserInfo);
											list.put("userProfileImage", userService.getUserProfileImage(vendorUserInfo.getUserId(), UserType.VENDOR.getValue()));
											list.put("authToken", Token);
											return new ResponseEntity<>(list, HttpStatus.OK);
										} else {
//											ResponseMessage responseMessage = new ResponseMessage(
//													APIStatusCode.INACTIVE_USER.getValue(),
//									        		"Failed",
//									        		"User Account Deleted");
//											return new ResponseEntity<>(responseMessage, HttpStatus.OK);

											HashMap<String, Object> list = new HashMap();
											list.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
											list.put("statusMessage", "Failed");
											list.put("responseMessage", "User Account Deleted");
											list.put("userId", vendorUserInfo.getUserId());
											list.put("userType", vendorUserInfo.getUserType());
											return new ResponseEntity<>(list, HttpStatus.OK);
										}

									} else {
//										ResponseMessage responseMessage = new ResponseMessage(
//												APIStatusCode.INACTIVE_USER.getValue(),
//								        		"Failed",
//								        		"Inactive User Information");
//										return new ResponseEntity<>(responseMessage, HttpStatus.OK);
										HashMap<String, Object> list = new HashMap();
										list.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
										list.put("statusMessage", "Failed");
										list.put("responseMessage", "Inactive User Information");
										list.put("userId", vendorUserInfo.getUserId());
										list.put("userType", vendorUserInfo.getUserType());
										return new ResponseEntity<>(list, HttpStatus.OK);
									}

								 } else {
									 	HashMap<String, Object> list = new HashMap();
										list.put("statusCode", APIStatusCode.NO_ACTIVE_ORGANISATION_FOUND.getValue());
										list.put("statusMessage", "Failed");
										list.put("responseMessage", "There is no active Organisation for this Vendor");
										list.put("userDetails", vendorUserInfo);
										list.put("userProfileImage", userService.getUserProfileImage(vendorUserInfo.getUserId(), UserType.CLIENT.getValue()));
										list.put("authToken", Token);

										return new ResponseEntity<>(list, HttpStatus.OK);
								 }

						} else {
							//organisation account deleted
							HashMap<String, Object> list = new HashMap();
							list.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
							list.put("statusMessage", "Failed");
							list.put("responseMessage", "Inactive Organisation Information");
							list.put("userId", vendorUserInfo.getUserId());
							list.put("userType", vendorUserInfo.getUserType());
							list.put("organisationId", vendorUserInfo.getVendorOrganisationId());
							return new ResponseEntity<>(list, HttpStatus.OK);
							}
						} else {
//							ResponseMessage responseMessage = new ResponseMessage(
//									APIStatusCode.VENDOR_ORGANISATION_NOT_FOUND.getValue(),
//					        		"Failed",
//					        		"Vendor Organisation Not registered for this User");
							// proceed option for Vendor organisation registration
							// or
							// send registration mail again to proceed registration

 							/*Hash Generation*/
							HashMap<String, Object> userObj = new HashMap();

							userObj.put("email", userInfo.getUsername());
							userObj.put("userId", userInfo.getUserId());
							userObj.put("userType", userInfo.getUserType());

							String responseJsonString = Utils.ClasstoJsonString(userObj);
							String encryptVendorUserHash = SecurityAES.encrypt(responseJsonString);

							HashMap<String, Object> list = new HashMap();
							list.put("statusCode", APIStatusCode.ORGANISATION_NOT_FOUND.getValue());
							list.put("statusMessage", "Success");
							list.put("responseMessage", "Vendor Organisation Not registered for this User");
							list.put("userDetails", vendorUserInfo);
//							list.put("authToken", Token);
							list.put("hash", encryptVendorUserHash);
							return new ResponseEntity<>(list, HttpStatus.OK);
						}

	//					list.put("vendorOrgDetails",vendorOrgInfo);
					} else {
						ResponseMessage responseMessage = new ResponseMessage(
								APIStatusCode.NOT_FOUND.getValue(),
				        		"Failed",
				        		"User Record Not Found");
						return new ResponseEntity<>(responseMessage, HttpStatus.OK);
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
					} else {
						ResponseMessage responseMessage = new ResponseMessage(
								APIStatusCode.NOT_FOUND.getValue(),
				        		"Failed",
				        		"User Record Not Found");
						return new ResponseEntity<>(responseMessage, HttpStatus.OK);
					}
				}
			} else {
//				ResponseMessage responseMessage = new ResponseMessage(
//						APIStatusCode.AUTHENTICATION_FAILED.getValue(),
//		        		"Failed",
//		        		"Invalid Credentials");
//				return new ResponseEntity<>(responseMessage, HttpStatus.OK);
				User userObj = userService.findByEmail(user.getUsername());
				HashMap<String, Object> list = new HashMap();
				list.put("statusCode", APIStatusCode.AUTHENTICATION_FAILED.getValue());
				list.put("statusMessage", "Failed");
				list.put("responseMessage", "Invalid Credentials");
				list.put("userId", userObj.getUserId());
				list.put("userType", userObj.getUserType());

				userService.updateIncorrectLoginAttempt(user.getUsername());

				if(userObj.getIncorrectAttempt() > Constants.MAX_INCORRECT_LOGIN_ATTEMPT_COUNT) {
					list = new HashMap();
					list.put("statusCode", APIStatusCode.ACCOUNT_BLOCKED_BY_MAX_INCORRECT_LOGIN_ATTEMPT.getValue());
					list.put("statusMessage", "Failed");
					list.put("responseMessage", "User Account Blocked Due to Maximum Incorrect Login Attempt");
					list.put("userId", userObj.getUserId());
					list.put("userType", userObj.getUserType());

					return new ResponseEntity<>(list, HttpStatus.OK);
				}

				return new ResponseEntity<>(list, HttpStatus.OK);
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.SERVER_ERROR.getValue(),
	        		"Failed",
	        		"Server Error");
			return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ResponseMessage responseMessage = new ResponseMessage(
				APIStatusCode.SERVER_ERROR.getValue(),
        		"Failed",
        		"Server Error");
		return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
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
					if(user.getUserType() == UserType.CLIENT.getValue()) {

						ClientUser clientUser = clientService.findById(user.getUserId());
						if(clientUser != null && clientUser.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
							HashMap<String, Object> response = new HashMap();
							response.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
							response.put("statusMessage", "User Account Deleted");
							response.put("responseMessage", "User Account Deleted from System By Admin");
							response.put("userId", user.getUserId());
							response.put("userType", user.getUserType());
							return new ResponseEntity<Object>(response, HttpStatus.OK);
						}

						List<ClientAssociation> clientAssociations = clientService.findClientAssociationById(user.getUserId());
						if(clientAssociations == null) {
							//no need any action
							if(user.getPassword() == null || user.getPassword().trim().length() == 0) {

								// Send Email Alert to Reset Password

								HashMap<String, Object> response = new HashMap();

								response.put("statusCode", APIStatusCode.RESET_PASSWORD.getValue());
								response.put("statusMessage", "User need to set New Password");
								response.put("responseMessage", "Please reset your password");
								response.put("userId", user.getUserId());
								response.put("userType", user.getUserType());
//								response.put("action", queryStringCreator.getResetPasswordEncodedURL(user));
//								Action
								try {
//									sendForgotPasswordResetLink(email, user.getUserId(), user.getUserType());// no need
								} catch(Exception exp) {
									exp.printStackTrace();
								}

								return new ResponseEntity<Object>(response, HttpStatus.OK);

							}
						} else {
							if(clientAssociations.size() > 0) {
								if(clientAssociations.size() == 1) {
									ClientAssociation clientAssociation = clientAssociations.get(0);
									if(clientAssociation.getAccountVerificationStatus() == UserAccountStatus.INVITED.getValue() && clientAssociation.getUserAccountStatus() == UserAccountStatus.INACTIVE.getValue()) {
										ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","User Record Not Found");
										return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
									}
//									else if () {
//										//no action required for now
//									}
								} else {
									//no need any action
								}
							} //no action required
						}
					} else if(user.getUserType() == UserType.VENDOR.getValue()) {
						VendorUser vendorUser = vendorService.findByVendorUserId(user.getUserId());
						if(vendorUser != null && vendorUser.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
							HashMap<String, Object> response = new HashMap();
							response.put("statusCode", APIStatusCode.INACTIVE_USER.getValue());
							response.put("statusMessage", "User Account Deleted");
							response.put("responseMessage", "User Account Deleted from System By Admin");
							response.put("userId", user.getUserId());
							response.put("userType", user.getUserType());
							return new ResponseEntity<Object>(response, HttpStatus.OK);
						}

						if(user.getPassword() == null || user.getPassword().trim().length() == 0) {

							// Send Email Alert to Reset Password

							HashMap<String, Object> response = new HashMap();

							response.put("statusCode", APIStatusCode.RESET_PASSWORD.getValue());
							response.put("statusMessage", "User need to set New Password");
							response.put("responseMessage", "Please reset your password");
							response.put("userId", user.getUserId());
							response.put("userType", user.getUserType());
//							response.put("action", queryStringCreator.getResetPasswordEncodedURL(user));
//							Action
							try {
//								sendForgotPasswordResetLink(email, user.getUserId(), user.getUserType());// no need
							} catch(Exception exp) {
								exp.printStackTrace();
							}

							return new ResponseEntity<Object>(response, HttpStatus.OK);

						}
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

			String firstName = "";
			String lastName = "";
			String phone = "";
			int organisationId = 0;
			try {
				firstName = String.valueOf(requestData.get("firstName"));
			} catch(Exception exp) {
				exp.printStackTrace();
				firstName = "";
			}
			try {
				lastName = String.valueOf(requestData.get("lastName"));
			} catch(Exception exp) {
				exp.printStackTrace();
				lastName = "";
			}
			try {
				phone = String.valueOf(requestData.get("phone"));
			} catch(Exception exp) {
				exp.printStackTrace();
				phone = "";
			}
			try {
				organisationId = Integer.parseInt(String.valueOf(requestData.get("organisationId")));
			} catch(Exception exp) {
				exp.printStackTrace();
			}

			Boolean isFirstTimeUser = Boolean.parseBoolean(String.valueOf(requestData.get("isNewUser")));

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

			Integer userType = Integer.parseInt(String.valueOf(userData.get("userType")));
			Integer userId = Integer.parseInt(String.valueOf(userData.get("userId")));
			if(isFirstTimeUser) {
				// store terms and condition datetime

				if (userService.resetPassword(userId, userType, password, firstName, lastName, phone) == null) {
			    	ResponseMessage responseMessage = new ResponseMessage(
			    			APIStatusCode.LINK_EXPIRED.getValue(),
			        		"Failed",
			        		"Invite Link Expired");
			    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			    } else {

			    	try {
			    		userService.updateTermsAcceptedTimestamp(userId, userType, organisationId, hash);
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
		if (userService.resetExistPassword(userId, userType, password) == null) {
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
			springBootEmail.sendWelcomeEmailWithAttachment(email,"Sample_Welcome_Email"+Utils.getLinkValidityTime(), configProperties.getBaseURL());
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
							if(user.getUserType() == UserType.CLIENT.getValue()) {
								if(!clientService.checkIsClientAccountActive(user.getUserId())) {
									response.put("statusCode", APIStatusCode.INACTIVE_USER.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
									response.put("statusMessage", "Failed");
									response.put("responseMessage", "Client User Account Deleted");
						        	return new ResponseEntity<Object>(response, HttpStatus.OK);
								}
							} else if(user.getUserType() ==  UserType.VENDOR.getValue()) {
								VendorUser vendorUserInfo = vendorService.findByVendorUserId(user.getUserId());
								if(vendorUserInfo.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
									response.put("statusCode", APIStatusCode.INACTIVE_USER.getValue()/*StatusCode.RESET_PASSWORD.getValue()*/);
									response.put("statusMessage", "Failed");
									response.put("responseMessage", "Vendor User Account Deleted");
						        	return new ResponseEntity<Object>(response, HttpStatus.OK);
								}
							}
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

		//String content = "http://condonuityappdev.eastus2.cloudapp.azure.com/register/create-password?email="+email
//		String content = "http://condonuityuat.canadacentral.cloudapp.azure.com/register/create-password?email="+email
		String content = configProperties.getBaseURL()+"register/create-password?email="+email
				+"&hash="+encryptUser
				+"&expiry="+Utils.getLinkValidityTime(); // AES algorithm
//		System.out.println("contentAES Encrypt->"+content);
//		System.out.println("contentAES Decrypt->"+SecurityAES.decrypt(encryptClientUser));

		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendPasswordResetEmailWithAttachment(email, content, configProperties.getBaseURL());
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

	 @ApiOperation(value = "Fetching All Service cities implementation")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "All Service cities fetched successfully")
	            }
	    )
		@GetMapping("/service/cities")
		public ResponseEntity<Object> getAllServiceCities() {

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

	 @ApiOperation(value = "Fetching All Service Province and cities implementation")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "All Service Province and cities fetched successfully")
	            }
	    )
		@GetMapping("/service/provinces")
		public ResponseEntity<Object> getAllProvinceCities() {

		 HashMap<String, Object> serviceCities = userService.findAllProvinceCities();

		 HashMap<String, Object> list = new HashMap();
		 if(serviceCities != null) {
		 	list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
		 	list.put("statusMessage", "Success");
		 	list.put("responseMessage", "List of All Service Province and Cities fetched successfully");
		 	list.put("serviceCities",serviceCities.entrySet().toArray());

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 } else {

		 	list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
		 	list.put("statusMessage", "Failed");
		 	list.put("responseMessage", "Database Error");

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 }

		}

	 @ApiOperation(value = "Fetching App Data Implementation")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "App Data fetched successfully")
	            }
	    )
		@GetMapping("/app/data")
		public ResponseEntity<Object> getAppData() {

//		 List<ServiceCities> serviceCities = userService.findAllServiceCities();
		 HashMap<String, Object> serviceProvincesCities = userService.findAllProvinceCities();
		 List<PredefinedTags> predefinedTags = userService.findAllPredefinedTags();
		 List<Amenities> amenities = userService.findAllAmenities();

		 HashMap<String, Object> list = new HashMap();
		 if(serviceProvincesCities != null) {
		 	list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
		 	list.put("statusMessage", "Success");
		 	list.put("responseMessage", "List of All App Data fetched successfully");
//		 	list.put("serviceCities",serviceCities);
		 	list.put("serviceProvinceCities",serviceProvincesCities.entrySet().toArray());
		 	list.put("predefinedTags",predefinedTags);
		 	list.put("amenities",amenities);

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 } else {

		 	list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
		 	list.put("statusMessage", "Failed");
		 	list.put("responseMessage", "Database Error");

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 }

		}

	 @ApiOperation(value = "Fetching All Client Amenities implementation")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "All Client Amenities fetched successfully")
	            }
	    )
		@GetMapping("/client/amenities")
		public ResponseEntity<Object> getAllAmenities() {

		 List<Amenities> amenities = userService.findAllAmenities();

		 HashMap<String, Object> list = new HashMap();
		 if(amenities != null) {
		 	list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
		 	list.put("statusMessage", "Success");
		 	list.put("responseMessage", "List of Client Amenities fetched successfully");
		 	list.put("amenities",amenities);

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 } else {

		 	list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
		 	list.put("statusMessage", "Failed");
		 	list.put("responseMessage", "Database Error");

		 	return new ResponseEntity<Object>(list, HttpStatus.OK);
		 }

		}

		@PostMapping("/password/encrypt/{password}")
		public ResponseEntity<Object> getEncryptPassword(@PathVariable("password") String password) {

			System.out.println("default "+dateTimeToDate("2020-08-03 6:45:34",TimeZone.getTimeZone("UTC")));
			System.out.println("TimeZone.getDefault() "+TimeZone.getDefault());
		 	return new ResponseEntity<Object>(SecurityAES.encrypt(password), HttpStatus.OK);
		}

		private static String dateTimeToDate(String timestamp, TimeZone timeZone) {
			 Date date;
			try {
				date = parseDateTime(timestamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			//ZoneId zoneId = ZoneId.of("UTC+08:00");
			//TimeZone timeZone = TimeZone.getTimeZone(zoneId);
			TimeZone timeZoneReq = TimeZone.getTimeZone("Asia/Calcutta");

			 return date.toInstant().atZone(timeZone.toZoneId()).withZoneSameInstant(timeZoneReq.toZoneId()).toString();
		}

		private static Date parseDateTime(String timestamp) throws ParseException {
			// TODO Auto-generated method stub
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp);
		}


		@PostMapping("/password/decrypt/{password}")
		public ResponseEntity<Object> getDecryptPassword(@PathVariable("password") String password) {

		 	return new ResponseEntity<Object>(SecurityAES.decrypt(password), HttpStatus.OK);
		}

		@ApiOperation(value = "Client Already registered an Organisation or Not check")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "Client Organisation Duplicate registration check")
	            }
	    )
		@PostMapping("/user/org/register/hash")
		public ResponseEntity<Object> checkDuplicateRegistration(
				@RequestBody Map<String, Object> requestData) {

			try {
				String hash = String.valueOf(requestData.get("hash"));

				String decryptedUser = SecurityAES.decrypt(hash);

				System.out.println("decrypt hash :"+hash);

				Map<String, Object> userData;

				userData = Utils.convertJsonToHashMap(decryptedUser);
				String expiry = String.valueOf(userData.get("expiry"));
				Integer userId = Integer.parseInt(String.valueOf(userData.get("userId")));
				Integer userType = Integer.parseInt(String.valueOf(userData.get("userType")));

				if (Utils.checkLinkIsExpired(expiry)) {
					ResponseMessage responseMessage = new ResponseMessage(
		        			APIStatusCode.LINK_EXPIRED.getValue(),
		        			"Failed",
		        			"Registration/Invite Link Expired");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
				}
				List<RegistrationLogs> registrationLogs = userService.checkRegistrationLog(userId, userType, hash);
				if(registrationLogs != null && registrationLogs.size() > 0) {
					ResponseMessage responseMessage = new ResponseMessage(
		        			APIStatusCode.REQUEST_FAILED.getValue(),
		        			"Failed",
		        			"User Already Registered Organisation using Hash");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(
		        			APIStatusCode.REQUEST_SUCCESS.getValue(),
		        			"Success",
		        			"User Not Registered any Organisation using Hash");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.BAD_REQUEST.getValue(),
	        			"Failed",
	        			"Failed to Parse Request - Bad Request");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			}

		}

		@ApiOperation(value = "Client Already Accepted the Invite or Not check")
	    @ApiResponses(
	            value = {
	                    @ApiResponse(code = 200, message = "Client Invite Expiry check")
	            }
	    )
		@PostMapping("/user/invite/hash")
		public ResponseEntity<Object> checkDuplicateInviteAccept(
				@RequestBody Map<String, Object> requestData) {

			try {
				String hash = String.valueOf(requestData.get("hash"));

				String decryptedUser = SecurityAES.decrypt(hash);

				System.out.println("decrypt hash :"+hash);

				Map<String, Object> userData;

				userData = Utils.convertJsonToHashMap(decryptedUser);
				String expiry = String.valueOf(userData.get("expiry"));
				Integer userId = Integer.parseInt(String.valueOf(userData.get("userId")));
				Integer userType = Integer.parseInt(String.valueOf(userData.get("userType")));
				Integer organisationId = Integer.parseInt(String.valueOf(userData.get("organisationId")));

				if(userType == UserType.CLIENT.getValue()) {
					ClientOrganisation clientOrganisation = clientService.getClientOrganisationById(organisationId);

					if(clientOrganisation != null && clientOrganisation.getActiveStatus() == DeleteStatus.INACTIVE.getValue()) {
						ResponseMessage responseMessage = new ResponseMessage(
			        			APIStatusCode.LINK_EXPIRED.getValue(),
			        			"Failed",
			        			"Invite Link Expired");
	        			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
					}

					ClientUser clientUser = clientService.getClientUserById(userId);

					if(clientUser != null && clientUser.getDeleteStatus() == DeleteStatus.INACTIVE.getValue()) {
						ResponseMessage responseMessage = new ResponseMessage(
			        			APIStatusCode.LINK_EXPIRED.getValue(),
			        			"Failed",
			        			"Invite Link Expired");
	        			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
					}

					ClientAssociation clientAssociation = clientService.findClientAssociation(userId, organisationId);

					if(clientAssociation != null && ( clientAssociation.getDeleteStatus() == DeleteStatus.INACTIVE.getValue() || clientAssociation.getUserAccountStatus() == DeleteStatus.INACTIVE.getValue())) {
						ResponseMessage responseMessage = new ResponseMessage(
			        			APIStatusCode.LINK_EXPIRED.getValue(),
			        			"Failed",
			        			"Invite Link Expired");
	        			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
					}
				} else if(userType == UserType.VENDOR.getValue()) {

					VendorOrganisation vendorOrganisation = vendorService.findByVendorOrgId(organisationId);
					if(vendorOrganisation != null && vendorOrganisation.getActiveStatus() == DeleteStatus.INACTIVE.getValue()) {
						ResponseMessage responseMessage = new ResponseMessage(
			        			APIStatusCode.LINK_EXPIRED.getValue(),
			        			"Failed",
			        			"Invite Link Expired");
	        			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
					}

					VendorUser vendorUser = vendorService.findByVendorUserId(userId);
					if(vendorUser != null && vendorUser.getAccountStatus() == DeleteStatus.INACTIVE.getValue()) {
						ResponseMessage responseMessage = new ResponseMessage(
			        			APIStatusCode.LINK_EXPIRED.getValue(),
			        			"Failed",
			        			"Invite Link Expired");
	        			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
					}
				}

				if (Utils.checkLinkIsExpired(expiry)) {
					ResponseMessage responseMessage = new ResponseMessage(
		        			APIStatusCode.LINK_EXPIRED.getValue(),
		        			"Failed",
		        			"Invite Link Expired");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
				}
				List<UserInviteLogs> inviteLogs = clientService.checkInviteLogs(userId, userType, organisationId, hash);
				if(inviteLogs != null && inviteLogs.size() > 0) {
					ResponseMessage responseMessage = new ResponseMessage(
		        			APIStatusCode.REQUEST_FAILED.getValue(),
		        			"Failed",
		        			"User Already Accepted an Invitation using this Hash");
		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
				} else {
//					ResponseMessage responseMessage = new ResponseMessage(
//		        			APIStatusCode.REQUEST_SUCCESS.getValue(),
//		        			"Success",
//		        			"User not used this hash for accepting an invite");
//		        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
					boolean isPasswordRequired = false;
					User user = userService.findByIdAndUserType(userId, userType);
					if(user.getPassword() != null && user.getPassword().trim().length() > 0) {
						isPasswordRequired = false;
					} else {
						isPasswordRequired = true;
					}

					HashMap<String, Object> list = new HashMap();
					list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
				 	list.put("statusMessage", "Success");
				 	list.put("responseMessage", "User not used this hash for accepting an invite");
				 	list.put("isPasswordRequired",isPasswordRequired);

				 	return new ResponseEntity<Object>(list, HttpStatus.OK);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.BAD_REQUEST.getValue(),
	        			"Failed",
	        			"Failed to Parse Request - Bad Request");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
			}

		}
}
