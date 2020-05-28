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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.constants.Constants.UserType;
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
		Integer userId = Integer.parseInt(String.valueOf(requestData.get("userId")));
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
	
}
