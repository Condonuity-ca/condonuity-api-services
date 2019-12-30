package tech.torbay.userservice.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.controller.UserController;
import tech.torbay.userservice.service.UserService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Existing User password reset implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Existing User password reset successfully")
            }
    )
	@PostMapping("/user/resetExistPassword")
	private ResponseEntity<Object> ResetPassword(@RequestBody Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer userId = Integer.parseInt(String.valueOf(requestData.get("userId")));
		Integer userType =  Integer.parseInt(String.valueOf(requestData.get("userType")));
		String password = String.valueOf(requestData.get("password"));
		
		
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
}
