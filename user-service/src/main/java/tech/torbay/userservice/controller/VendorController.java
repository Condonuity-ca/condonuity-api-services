package tech.torbay.userservice.controller;


import java.util.HashMap;
import java.util.List;

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
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorInsurance;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorPortfolio;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.service.VendorService;
import tech.torbay.userservice.statusmessage.ResponseMessage;


@RestController
@RequestMapping("/api")
@Api(value = "Vendor Resource REST Endpoint", description = "Shows the vendor info")
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@ApiOperation(value = "Fetching All vendor users details in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful All Vendor Details")
            }
    )
    @GetMapping("/vendor/users")
    public ResponseEntity<Object> getAllVendorUsers() 
	{
		List<VendorUser> list = vendorService.findAllVendorUsers();
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Vendor users in Condonuity Application fetched successfully");
			response.put("vendors", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value = "Fetch A Vendor Details Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A vendor details fetched successfully")
            }
    )
	@GetMapping("/vendor/org/{id}")
	public ResponseEntity<Object> getOrganisationById(@PathVariable("id") Integer vendorOrganisationId) {
		VendorOrganisation vendorOrganisation = vendorService.getVendorOrganisationById(vendorOrganisationId);
		List<VendorPortfolio> vendorPortfolio = vendorService.getVendorPortfolio(vendorOrganisationId);
		List<VendorInsurance> vendorInsurance = vendorService.getVendorInsurance(vendorOrganisationId);
		
		HashMap<String, Object> response = new HashMap();
		if(vendorOrganisation != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Vendor Organisation details fetched successfully");
			response.put("vendor", vendorOrganisation);
			response.put("vendorPortfolio", vendorPortfolio);
			response.put("vendorInsurances", vendorInsurance);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetch A vendor organisation Users and other information Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A vendor organisation Users and other information fetched successfully")
            }
    )
	@GetMapping("/vendor/org/account/{id}")
	public ResponseEntity<Object> getOrganisationAccountById(@PathVariable("id") Integer id) {
		List<VendorUser> vendorUsers = vendorService.getVendorOrganisationUsersById(id);
		
		HashMap<String, Object> response = new HashMap();
		if(vendorUsers != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Vendor Organisation Account details fetched successfully");
			response.put("vendorUsers", vendorUsers);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetch A Vendor User Details Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A vendor user details fetched successfully")
            }
    )
	@GetMapping("/vendor/user/{id}")
	public ResponseEntity<Object> getVendorUserById(@PathVariable("id") Integer id) {
		VendorUser vendorUser = vendorService.getVendorUserById(id);
		
		HashMap<String, Object> response = new HashMap();
		if(vendorUser != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Vendor user details fetched successfully");
			response.put("vendorUser", vendorUser);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All Vendor Organisation Details in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Vendor organisation details fetched successfully in Condonuity Application")
            }
    )
	@GetMapping("/vendor/orgs")
	public ResponseEntity<Object> getAllVendorOrganisations() {
		List<VendorOrganisation> list = vendorService.getAllVendorOrganisations();
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Vendors in Condonuity Application fetched successfully");
			response.put("vendorOrgs", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Vendor User update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "vendor user details updated successfully")
            }
    )
	@PutMapping("/vendor/user")
	public ResponseEntity<Object> updateVendorUser(@RequestBody VendorUser vendorUser) {
		if(vendorService.updateVendorUser(vendorUser) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor User Details Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Update vendor User Details");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		}
		
	}
	
	@ApiOperation(value = "Vendor update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "vendor organisation details updated successfully")
            }
    )
	@PutMapping("/vendor/org")
	public ResponseEntity<Object> updateVendorOrganisation(@RequestBody VendorOrganisation vendorOrganisation) {
		if(vendorService.updateVendorOrganisation(vendorOrganisation) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Details Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Update Vendor Details");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		}
		
	}
	
	@ApiOperation(value = "Vendor Portfolio Creation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Portfolio Created Successfully"),
                    @ApiResponse(code = 201, message = "Vendor Portfolio Created Successfully")
            }
    )
	@PostMapping("/vendor/org/portfolio/create")
	public ResponseEntity<Object> addVendorPortfolio(
			@RequestBody VendorPortfolio vendorPortfolio) {
		
        VendorPortfolio vendorPortfolioObj= vendorService.addVendorPortfolio(vendorPortfolio);
        if (vendorPortfolioObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create Vendor Portfolio");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Portfolio Created Successfully");
			return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.CREATED);
        }
	}
	
	@ApiOperation(value = "Vendor Portfolio update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "vendor Portfolio details updated successfully")
            }
    )
	@PutMapping("/vendor/org/portfolio")
	public ResponseEntity<Object> updateVendorPortfolio(@RequestBody VendorPortfolio vendorPortfolio) {
		if(vendorService.updateVendorPortfolio(vendorPortfolio) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Portfolio Details Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Update Vendor Portfolio Details");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		}
		
	}
	
	@ApiOperation(value = "Vendor Insurance Creation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Insurance Created Successfully"),
                    @ApiResponse(code = 201, message = "Vendor Insurance Created Successfully")
            }
    )
	@PostMapping("/vendor/org/insurance/create")
	public ResponseEntity<Object> addVendorInsurance(
			@RequestBody VendorInsurance vendorInsurance) {
		
        VendorInsurance vendorInsuranceObj= vendorService.addVendorInsurance(vendorInsurance);
        if (vendorInsuranceObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to create Vendor Insurance");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Insurance Created Successfully");
			return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.CREATED);
        }
	}
	
	@ApiOperation(value = "Vendor Insurance update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "vendor Insurance details updated successfully")
            }
    )
	@PutMapping("/vendor/org/insurance")
	public ResponseEntity<Object> updateVendorInsurance(@RequestBody VendorInsurance vendorInsurance) {
		if(vendorService.updateVendorInsurance(vendorInsurance) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Insurance Details Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Update Vendor Insurance Details");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		}
		
	}
} 