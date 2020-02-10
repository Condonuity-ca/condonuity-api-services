package tech.torbay.messageservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.messageservice.constants.Constants.APIStatusCode;
import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.InternalMessage;
import tech.torbay.messageservice.entity.InternalMessageComment;
import tech.torbay.messageservice.service.MessageService;
import tech.torbay.messageservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
@Api(value = "Message Resource REST Endpoint", description = "Shows the Message Implementation")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@ApiOperation(value = "Create Thread in Client Internal Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Thread Created successfully")
            }
    )
	@PostMapping("/internal/message/create")
	private ResponseEntity<Object> createThread(@RequestBody InternalMessage internalMessage) {
		// TODO Auto-generated method stub
		
		InternalMessage internalMessageObj = messageService.createThread(internalMessage);
		if (internalMessageObj == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create message thread");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	HashMap<String, Object> response = new HashMap();
	    	response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Message Thread Created Successfully");
			response.put("threadId", String.valueOf(internalMessageObj.getId()));
	    	return new ResponseEntity<Object>(response,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Get Threads from Client Internal Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Threads fetched successfully")
            }
    )
	@GetMapping("/internal/messages/{userType}/{clientOrganisationId}")
	private ResponseEntity<Object> createThread(@PathVariable("userType") Integer userType, @PathVariable("clientOrganisationId") Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		
		List<Map<String,Object>> internalMessages = messageService.getInternalMessages(clientOrganisationId, userType);
		
		HashMap<String, Object> list = new HashMap();
		
		if (internalMessages != null) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Internal Message Threads Fetched Successfully");
			list.put("internalMessages", internalMessages);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Failed to fetch message threads");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Create Thread Comment in Internal Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Thread Comment Posted successfully")
            }
    )
	@PostMapping("/internal/message/comment/create")
	private ResponseEntity<Object> createThreadComment(@RequestBody InternalMessageComment internalMessageComment) {
		// TODO Auto-generated method stub
		
		InternalMessageComment internalMessageCommentObj = messageService.createThreadComment(internalMessageComment);
		if (internalMessageCommentObj == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create message thread");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	HashMap<String, Object> response = new HashMap();
	    	response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Message Thread Comment Created Successfully");
			response.put("threadCommentId", String.valueOf(internalMessageCommentObj.getId()));
	    	return new ResponseEntity<Object>(response,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Create Thread in Client External Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Thread Created successfully")
            }
    )
	@PostMapping("/external/message/create")
	private ResponseEntity<Object> createExternalThread(@RequestBody ExternalMessage externalMessage) {
		// TODO Auto-generated method stub
		
		ExternalMessage externalMessageObj = messageService.createExternalThread(externalMessage);
		if (externalMessageObj == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create message thread");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	HashMap<String, Object> response = new HashMap();
	    	response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Message Thread Created Successfully");
			response.put("threadId", String.valueOf(externalMessageObj.getId()));
	    	return new ResponseEntity<Object>(response,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Get Threads from External Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Threads fetched successfully")
            }
    )
	@GetMapping("/external/messages/{userType}/{organisationId}")
	private ResponseEntity<Object> createExternalThread(@PathVariable("userType") Integer userType, @PathVariable("organisationId") Integer organisationId) {
		// TODO Auto-generated method stub
		
		List<Map<String,Object>> externalMessages = messageService.getExternalMessages(organisationId, userType);
		
		HashMap<String, Object> list = new HashMap();
		
		if (externalMessages != null) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "External Message Threads Fetched Successfully");
			list.put("externalMessages", externalMessages);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Failed to fetch message threads");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Create Thread Comment in External Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Thread Comment Posted successfully")
            }
    )
	@PostMapping("/external/message/comment/create")
	private ResponseEntity<Object> createExternalThreadComment(@RequestBody InternalMessageComment internalMessageComment) {
		// TODO Auto-generated method stub
		
		InternalMessageComment internalMessageCommentObj = messageService.createThreadComment(internalMessageComment);
		if (internalMessageCommentObj == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create message thread");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	HashMap<String, Object> response = new HashMap();
	    	response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Message Thread Comment Created Successfully");
			response.put("threadCommentId", String.valueOf(internalMessageCommentObj.getId()));
	    	return new ResponseEntity<Object>(response,HttpStatus.OK);
	    }
	}
}