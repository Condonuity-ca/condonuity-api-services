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
import tech.torbay.userservice.config.SecurityAES;
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.controller.UserController;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.service.UserService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
//	@Scheduled(fixedDelay = 10000)
//	public void run() {
//	    logger.info("Current time is :: " + Calendar.getInstance().getTime());
//	    try {
////			cronJobSch();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	@Scheduled(cron = "0 * 9 * * ?")
//	public void cronJobSch() throws Exception {
//		logger.info("Current time is :: " + Calendar.getInstance().getTime());
//	}
	
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
		String password = SecurityAES.encrypt(String.valueOf(requestData.get("password")));
		
		
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
	
	@ApiOperation(value = "Get Client Search Result Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Search Results Fetched Successfully"),
            }
    )
	@PostMapping("/client/search")
	public ResponseEntity<Object> getClientSearchResults(@RequestBody Map<String, Object> requestData) {
		List<Map<String, Object>> results = userService.getClientSearchResults(requestData);
        if (results == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to fetch search results");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Search Results Fetched Successfully");
			list.put("results", results);
//			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Get Vendor Search Result Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Search Results Fetched Successfully"),
            }
    )
	@PostMapping("/vendor/search")
	public ResponseEntity<Object> getVendorSearchResults(@RequestBody Map<String, Object> requestData) {
		
		List<Map<String, Object>> results = userService.getVendorSearchResults(requestData);
        if (results == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to fetch search results");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Vendor Search Results Fetched Successfully");
			list.put("results", results);
//			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
}
