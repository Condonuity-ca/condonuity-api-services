package tech.torbay.userservice.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.controller.SupportUserController;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.service.SupportUserService;
import tech.torbay.userservice.service.UserService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
public class SupportUserController {
	private static final Logger logger = LoggerFactory.getLogger(SupportUserController.class);
	
	@Autowired
	private SupportUserService supportUserService;
	
//	@ApiOperation(value = "Organisation Activation / De-Activation implementation by Support User and alerts using Email")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "Organisation Activation / De-Activation implementation")
//            }
//    )
//	@PostMapping("/support/")
//	private ResponseEntity<Object> ResetPassword(@RequestBody Map<String, Object> requestData) {
//		// TODO Auto-generated method stub
//		
//		Integer or = Integer.parseInt(String.valueOf(requestData.get("userId")));
//		Integer userType =  Integer.parseInt(String.valueOf(requestData.get("userType")));
//		String password = String.valueOf(requestData.get("password"));
//		
//		
//		if (supportUserService.resetPassword(userId, userType, password) == null) {
//	    	ResponseMessage responseMessage = new ResponseMessage(
//	    			APIStatusCode.REQUEST_FAILED.getValue(),
//	        		"Failed",
//	        		"Failed to reset password");
//	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
//	    } else {
//	    	ResponseMessage responseMessage = new ResponseMessage(
//	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
//	        		"Success",
//	        		"Password reset successfully");
//	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
//	    }
//	}
}
