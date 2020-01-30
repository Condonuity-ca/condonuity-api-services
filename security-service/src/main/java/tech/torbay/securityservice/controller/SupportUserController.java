package tech.torbay.securityservice.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.email.SpringBootEmail;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.SupportUser;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.service.SupportService;
import tech.torbay.securityservice.service.VendorService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;
import tech.torbay.securityservice.utils.QueryStringCreator;
import tech.torbay.securityservice.utils.Utils;


@RestController
@RequestMapping("/api")
@Api(value = "Support User Resource REST Endpoint", description = "Shows the Support User info")
public class SupportUserController {
	
	@Autowired
	private SupportService supportService;
	
	@ApiOperation(value = "New Support User Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully New Support User Registred"),
                    @ApiResponse(code = 201, message = "Successfully New Support User Registred")
            }
    )
	@PostMapping("/support/user/register")
	public ResponseEntity<Object> addVendorUser(@RequestBody SupportUser supportUser, UriComponentsBuilder builder) {
		
		// check vendor user already exist or not
		
		if(supportService.findByEmail(supportUser.getEmail()) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.CONFLICT.getValue(),
	        		"Failed",
	        		"Support User Already Exist");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		} else {
			SupportUser supportUserObj = supportService.addSupportUser(supportUser);
	        if (supportUserObj == null ) {
	        	ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.REQUEST_FAILED.getValue(),
		        		"Failed",
		        		"Failed to Create Support User");
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	        } else {
//	        	HttpHeaders headers = new HttpHeaders();
//	          headers.setLocation(builder.path("/vendor/user/{id}").buildAndExpand(vendorUser.getVendorId()).toUri());
	        	ResponseMessage responseMessage = new ResponseMessage(
	        			APIStatusCode.REQUEST_SUCCESS.getValue(),
		        		"Success",
		        		"New Support User Created Successfully");
	        	
//	        	try {
//		        	sendVendorEmailVerification(vendor_user);
//		        } catch(Exception exp) {
//		        	exp.printStackTrace();
//		        }
	        	
	        	return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.OK);	
	        }
		}
        
        
	}
	
} 