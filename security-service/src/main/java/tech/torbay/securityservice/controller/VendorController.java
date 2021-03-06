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
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.service.VendorService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;


@RestController
@RequestMapping("/api")
@Api(value = "Vendor Resource REST Endpoint", description = "Shows the vendor info")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@ApiOperation(value = "New Vendor Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully New Vendor Registred"),
                    @ApiResponse(code = 201, message = "Successfully New Vendor Registred")
            }
    )
	@PostMapping("/vendor/organisation/register/{vendorUserId}")
	public ResponseEntity<Object> addVendorOrganisation(
			@PathVariable("vendorUserId") Integer vendorUserId, 
			@RequestBody VendorOrganisation vendorOrganisation, UriComponentsBuilder builder) {
		
		// org_id
		
        VendorOrganisation vendorOrg= vendorService.addVendorOrgnisation(vendorUserId, vendorOrganisation);
        if (vendorOrg == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Vendor Organisation Already Exists");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"New Vendor Organisation Created Successfully");
			return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.CREATED);
        }
	}
	
	@ApiOperation(value = "New Vendor User Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successfully New Vendor User Registred"),
                    @ApiResponse(code = 201, message = "Successfully New Vendor User Registred")
            }
    )
	@PostMapping("/vendor/user/register")
	public ResponseEntity<Object> addVendorUser(@RequestBody VendorUser vendorUser, UriComponentsBuilder builder) {
		
		// check vendor user already exist or not
		
        VendorUser vendor_user = vendorService.addVendorUser(vendorUser);
        if (vendor_user == null ) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Vendor User Already Exists");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
        } else {
//        	HttpHeaders headers = new HttpHeaders();
//          headers.setLocation(builder.path("/vendor/user/{id}").buildAndExpand(vendorUser.getVendorId()).toUri());
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"New Vendor User Created Successfully");
        	
        	try {
	        	sendVendorEmailVerification(vendor_user);
	        } catch(Exception exp) {
	        	exp.printStackTrace();
	        }
        	
        	return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.CREATED);	
        }
        
	}
	
	private void sendVendorEmailVerification(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		SecurityAES securityAES = new SecurityAES();
		String content = securityAES.getRegisterEncodedURL(vendorUser.getEmail(), vendorUser.getUserId(), Constants.UserType.VENDOR.getValue());
		
		System.out.println("Sending Email...");
		SpringBootEmail springBootEmail = new SpringBootEmail();
//		springBootEmail.sendEmail(email);
		try {
			springBootEmail.sendEmailWithAttachment(vendorUser.getEmail(), content);
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

	
	@ApiOperation(value = "Vendor existance check with Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A vendor record exist already")
            }
    )
	@GetMapping("/vendor/user/{email}")
	public ResponseEntity<Object> vendorUserExists(@PathVariable("email") String email) {
		VendorUser vendorUser = vendorService.findByEmail(email);
		
		if(vendorUser != null ) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor User Already Exists");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.NOT_FOUND.getValue(),
	        		"RESOURCE_NOT_FOUND",
	        		"Vendor User Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
//	@ApiOperation(value = "Fetching All vendor users details")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "Successful All Vendor Details")
//            }
//    )
//    @GetMapping("/vendor/users")
//    public List<VendorUser> getAllVendorUsers() {
//
//       return vendorService.findAll();   }
	
} 