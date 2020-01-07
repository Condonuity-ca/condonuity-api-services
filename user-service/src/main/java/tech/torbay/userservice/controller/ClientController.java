package tech.torbay.userservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.OrganisationPayment;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.service.ClientService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

@RestController
@RequestMapping("/api")
@Api(value = "Client Resource REST Endpoint", description = "Shows the client info")
public class ClientController {

    @Autowired
    ClientService clientService;

//    @ApiOperation(value = "Client details Update Implementation")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "client details updated successfully")
//            }
//    )
//    @PutMapping("/clients/{clientId}")
//    public ClientUser updateClientUser(@PathVariable(value = "clientId") Integer clientId,
//                                               @Valid @RequestBody ClientUser toUpdateClient) {
//
//        ClientUser client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));
//
//
//            client.setLegalName(toUpdateClient.getLegalName());
//            client.setFirstName(toUpdateClient.getFirstName());
//            client.setLastName(toUpdateClient.getLastName());
////            client.setUserType(toUpdateClient.getUserType());
//            client.setCity(toUpdateClient.getCity());
//            client.setPhone(toUpdateClient.getPhone());
//            client.setCountryCode(toUpdateClient.getCountryCode());
//
//        ClientUser updatedClient = clientRepository.save(client);
//        return updatedClient;
//    }


	/*New APIs Structure*/
    
    @ApiOperation(value = "Fetching A Client Details with All Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client details fetched successfully")
            }
    )
	@GetMapping("/client/user/{id}")
	public ResponseEntity<Object> getClientUserById(@PathVariable("id") Integer id) {
    	Object client = clientService.getClientUserById(id);
		
		List<Object> orgs = clientService.getAllCorporateAccounts(id);
//		NotificationSettings notifications = clientService.getNotificationSettings();
		
		HashMap<String, Object> list = new HashMap();
		if(client != null) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client details fetched successfully");
			list.put("corporateAccounts",orgs);
			list.put("client",client);
			
			//selectedOrganisation Details
			
//			HashMap<String, Object> clientInfo = new HashMap();
//			clientInfo.put("clientOrganisation", orgs.getOrganisationList().get(0));
//			clientInfo.put("client", client);

//			list.put("notificationSettings", notifications);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
    
    @ApiOperation(value = "Update Client As Inactive")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client details updated successfully")
            }
    )
	@PutMapping("/client/user/inactive")
	public ResponseEntity<Object> deletetClientUserById(@RequestBody Map<String, Object> requestData) {
    	
    	Integer clientUserId = Integer.parseInt(String.valueOf(requestData.get("clientUserId")));
    	Integer clientOrgId = Integer.parseInt(String.valueOf(requestData.get("clientOrgId")));
    	
    	
    	Object client = clientService.deleteClientUserById(clientUserId, clientOrgId);
		
		HashMap<String, Object> list = new HashMap();
		if(client != null) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client User deleted successfully");
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
    @ApiOperation(value = "Fetching A Client Details In an Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client details fetched successfully")
            }
    )
	@GetMapping("/client/user/{id}/{clientOrgId}")
	public ResponseEntity<Object> getClientUserByIdAndOrgId(@PathVariable("id") Integer id, @PathVariable("clientOrgId") Integer clientOrgId) {
    	Object client = clientService.getClientUserByIdAndClientOrgId(id, clientOrgId);
		
		System.out.println(client);
		
		List<Object> orgs = clientService.getAllCorporateAccounts(id);
//		NotificationSettings notifications = clientService.getNotificationSettings();
		
		HashMap<String, Object> list = new HashMap();
		if(client != null) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client details fetched successfully");
			list.put("client",client);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All client Organisation details with in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful All Client Details")
            }
    )
	@GetMapping("/client/orgs")
	public ResponseEntity<Object> getAllClientOrganisations() {
		List<ClientOrganisation> list = clientService.getAllClientOrganisations();
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Client Organisations details fetched successfully");
			response.put("clientOrganisations", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All client Organisation details with in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful All Client Details")
            }
    )
	@GetMapping("/client/orgs/{vendorOrgId}")
	public ResponseEntity<Object> getAllClientOrganisationsByVendorOrgId(@PathVariable("vendorOrgId") Integer vendorOrgId) {
		List<Object> list = clientService.getAllClientOrganisationsByVendorOrgId(vendorOrgId);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Client Organisations details fetched successfully");
			response.put("clientOrganisations", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Client details Update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "client details updated successfully")
            }
    )
	@PutMapping("/client/user")
	public ResponseEntity<Object> updateClientUser(@RequestBody ClientUser client) {
		if(clientService.saveClient(client) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client details updated successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
            		"Failed",
            		"Client details failed to update");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Client details Update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "client details updated successfully")
            }
    )
	@PutMapping("/client/user/role")
	public ResponseEntity<Object> updateUserRoleAndPosition(@RequestBody Map<String, Object> requestData) {
		
		
		
		if(clientService.saveClientUserRole(requestData) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client details updated successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
            		"Failed",
            		"Client details failed to update");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Client Organisation update implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "client organisation details updated successfully")
            }
    )
	@PutMapping("/client/org")
	public ResponseEntity<Object> updateOrganisation(@RequestBody ClientOrganisation clientOrg) {
		if(clientService.updateOrganisation(clientOrg) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client Organisation Details Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		} else {
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.REQUEST_FAILED.getValue(),
            		"Failed",
            		"Client Organisation Update Failed");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);	
		}
	}
	
	@ApiOperation(value = "Fetching A Client Organisation , Other Info and Amenities Informations")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A Client Organisation details fetched successfully")
            }
    )
	@GetMapping("/client/org/{id}")
	public ResponseEntity<Object> getOrganisationById(@PathVariable("id") Integer id) {
		ClientOrganisation organisation = clientService.getOrganisationById(id);
		System.out.println("organisation : "+organisation.toString());
		List<ClientAmenities> amenitiesInfo = clientService.getAmenitiesByOrgId(id);
//		System.out.println("amenitiesInfo : "+amenitiesInfo.toString());
//		//IF admin get All other users details
//		Clients allUsers = clientService;
		HashMap<String, Object> list = new HashMap();
		
		if (organisation != null /* && amenitiesInfo != null */) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Organisation details fetched successfully");
			list.put("organisation", organisation);
			list.put("clientAmenities",amenitiesInfo);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Client Organisation Amenities Information Update implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Organisation Amenities Information Updated successfully")
            }
    )
	@PutMapping("/client/org/amenity")
	public ResponseEntity<Object> updateAmenities(@RequestBody ClientAmenities amenitiesInfo) {
		System.out.println(amenitiesInfo);
        if (clientService.updateAmenities(amenitiesInfo) == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Failed to Update Organisation Amenities Information");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
            ResponseMessage responseMessage = new ResponseMessage(
            		APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client Organisation Amenities Information Updated successfully");
            return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Fetching A Client Corporation , Payment and Billing Informations")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Corporation , Payment and Billing Informations fetched successfully")
            }
    )
	@GetMapping("/client/org/account/{orgId}")
	public ResponseEntity<Object> getOrganisationAccountById(@PathVariable("orgId") Integer id) {
		List<Object> clients = clientService.getAllClientsByOrganisation(id);
		List<OrganisationPayment> paymentBillingDetails = clientService.getPaymentBillingDetails(id);
		
//		//IF admin get All other users details
//		Clients allUsers = clientService;
		HashMap<String, Object> list = new HashMap();
		
		if (clients != null /* && paymentDetails != null && billingAddress != null */) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Corporation Users, Payment and Billing Informations fetched successfully");
			list.put("payment_billing_details",paymentBillingDetails);
			list.put("users",clients);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Adding Preferenced Vendor Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Preferenced Vendor Added Successfully"),
                    @ApiResponse(code = 201, message = "Preferenced Vendor Created Successfully")
            }
    )
	@PostMapping("/client/org/preference/add")
	public ResponseEntity<Object> addClientAsFavourite(
			@RequestBody UserWishList userWishList) {
		
		UserWishList userWishListObj = clientService.addVendorAsFavourite(userWishList);
        if (userWishListObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to add Vendor Preference");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Preferenced Vendor Added Successfully");
			return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.OK);
        }
	}
    
	@ApiOperation(value = "Client Rates Vendor by category")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Vendor Rated Successfully"),
            }
    )
	@PostMapping("/client/rate/vendor")
	public ResponseEntity<Object> rateVendorByCategory(
			@RequestBody List<VendorCategoryRatings> vendorCategoryRatings) {
		
		boolean isRated = clientService.rateVendorByCategory(vendorCategoryRatings);
        if (!isRated) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Rate Vendor");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Rated Successfully");
			return new ResponseEntity<Object>(responseMessage, /* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Setting Client User Primary Organisation Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client User Primary Organisation Updated successfully")
            }
    )
	@PutMapping("/client/{clientUserId}/primary/{clientOrgId}")
	public ResponseEntity<Object> updateClientPrimaryOrganisation(@PathVariable("clientUserId") Integer clientUserId , @PathVariable("clientOrgId") Integer clientOrgId) {
        if (clientService.updateClientPrimaryOrganisation(clientUserId, clientOrgId) == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Failed to Update Client User Primary Organisation");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
            ResponseMessage responseMessage = new ResponseMessage(
            		APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client User Primary Organisation Updated successfully");
            return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
}
