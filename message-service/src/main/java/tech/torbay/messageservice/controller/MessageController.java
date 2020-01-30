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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.messageservice.constants.Constants.APIStatusCode;
import tech.torbay.messageservice.entity.ClientInternalMessage;
import tech.torbay.messageservice.entity.ClientInternalMessageComment;
import tech.torbay.messageservice.service.MessageService;
import tech.torbay.messageservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService messageService;
	
	@ApiOperation(value = "Create Thread in Client Internal Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Thread Created successfully")
            }
    )
	@PostMapping("/thread/create/internal")
	private ResponseEntity<Object> createThread(@RequestBody ClientInternalMessage clientInternalMessage) {
		// TODO Auto-generated method stub
		
		if (messageService.createThread(clientInternalMessage) == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create message thread");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Message Thread Created Successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
	
	@ApiOperation(value = "Get Threads from Client Internal Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Threads fetched successfully")
            }
    )
	@GetMapping("/thread/create/internal/{clientOrganisationId}")
	private ResponseEntity<Object> createThread(@PathVariable("clientOrganisationId") Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		
		List<Map<String,Object>> clientInternalMessages = messageService.getClientInternalMessages(clientOrganisationId);
		
		HashMap<String, Object> list = new HashMap();
		
		if (clientInternalMessages != null) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Internal Message Threads Fetched Successfully");
			list.put("clientInternalMessages", clientInternalMessages);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Failed to fetch message threads");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Create Thread Comment in Client Internal Message Board implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Thread Comment Posted successfully")
            }
    )
	@PostMapping("/thread/create/internal/comment")
	private ResponseEntity<Object> createThreadComment(@RequestBody ClientInternalMessageComment clientInternalMessageComment) {
		// TODO Auto-generated method stub
		
		if (messageService.createThreadComment(clientInternalMessageComment) == null) {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create message thread");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    } else {
	    	ResponseMessage responseMessage = new ResponseMessage(
	    			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Message Thread Comment Created Successfully");
	    	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	    }
	}
}
