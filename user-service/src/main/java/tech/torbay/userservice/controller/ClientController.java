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
import tech.torbay.userservice.constants.Constants.NotificationType;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientTask;
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.OrganisationPayment;
import tech.torbay.userservice.entity.UserWishList;
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
    	
    	HashMap<String, Object> list = new HashMap();
    	
    	if(clientService.getUsersCountByOrganisationId(clientOrgId) > 1) {
    		
    		Object client = clientService.deleteClientUserById(clientUserId, clientOrgId);
    		
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
    	} else {
    		list.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Organisation requires atleast 1 Active User");

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
    	Map<String, Object> client = clientService.getClientUserByIdAndClientOrgId(id, clientOrgId);
		
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
		List<Object> list = clientService.getAllClientOrganisations();
		
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
	
	@ApiOperation(value = "Fetching Client Organisation Users details with in Condonuity Application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful All Client User Details")
            }
    )
	@GetMapping("/client/org/users/{clientOrganisationId}")
	public ResponseEntity<Object> getClientOrganisationUsers(@PathVariable("clientOrganisationId") Integer clientOrganisationId) {
		List<Object> clients = clientService.getAllClientsByOrganisation(clientOrganisationId);
		
		HashMap<String, Object> response = new HashMap();
		if(clients != null) {
			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Client Organisation Users details fetched successfully");
			response.put("clientUsers", clients);
			
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
		Object organisation = clientService.getOrganisationById(id);
		System.out.println("organisation : "+organisation.toString());
		List<Map<String, Object>> amenitiesInfo = clientService.getAmenitiesByOrgId(id);
		List<Map<String,Object>> clientRegistrationFiles = clientService.getClientRegistrationFiles(id);
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
			list.put("registrationFiles",clientRegistrationFiles);
			
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
	@PutMapping("/client/org/amenity/{clientOrgId}")
	public ResponseEntity<Object> updateAmenities(@PathVariable("clientOrgId") Integer clientOrgId, @RequestBody List<ClientAmenities> clientAmenities) {
        if (!clientService.updateAmenities(clientOrgId, clientAmenities)) {
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
	@GetMapping("/client/org/account/{orgId}/{clientUserId}")
	public ResponseEntity<Object> getOrganisationAccountById(@PathVariable("orgId") Integer clientOrgId, @PathVariable("clientUserId") Integer clientUserId) {
		List<Object> clients = clientService.getAllClientsByOrganisation(clientOrgId);
		List<OrganisationPayment> paymentBillingDetails = clientService.getPaymentBillingDetails(clientOrgId);
		Map<String, Object> client = clientService.getClientUserByIdAndClientOrgId(clientUserId, clientOrgId);
//		//IF admin get All other users details
//		Clients allUsers = clientService;
		HashMap<String, Object> list = new HashMap();
		
		if (clients != null /* && paymentDetails != null && billingAddress != null */) {
			list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Corporation Users, Payment and Billing Informations fetched successfully");
			list.put("payment_billing_details",paymentBillingDetails);
			list.put("users",clients);
			list.put("clientOrganisationId",clientOrgId);
			list.put("userRole",client.get("userRole"));
			list.put("clientUserInfo",client);
			
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
			@RequestBody Map<String, Object> requestData) {
		
		boolean isRated = clientService.rateVendorByCategory(requestData);
        if (!isRated) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Rate Vendor");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Vendor Rated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Updating Client Review Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client review updated Successfully"),
            }
    )
	@PutMapping("/client/review/update")
	public ResponseEntity<Object> updateClientReview(@RequestBody Map<String, Object> requestData) {
		//if client user based reviews
//		List<Map<String, Object>> clientAllReviews = clientService.getAllClientReviews(clientId, clientOrganisationId);
		boolean isRated = clientService.updateReview(requestData);
        if (!isRated) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Update Review");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Review Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Deleting Client Review Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client review deleted Successfully"),
            }
    )
	@PutMapping("/client/review/inactive/{reviewId}")
	public ResponseEntity<Object> updateClientReview(@PathVariable("reviewId") Integer reviewId) {
		//if client user based reviews
//		List<Map<String, Object>> clientAllReviews = clientService.getAllClientReviews(clientId, clientOrganisationId);
		boolean isRated = clientService.deleteReview(reviewId);
        if (!isRated) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Delete Review");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Review Deleted Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Fetching Client All Submitted Reviews Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client All submitted reviews fetched Successfully"),
            }
    )
	@GetMapping("/client/reviews/{clientId}/{clientOrganisationId}")
	public ResponseEntity<Object> getClientAllMyReviews(
			@PathVariable("clientId") Integer clientId, @PathVariable("clientOrganisationId") Integer clientOrganisationId) {
		//if client user based reviews
//		List<Map<String, Object>> clientAllReviews = clientService.getAllClientReviews(clientId, clientOrganisationId);
		
		// if organisation based reviews
		List<Map<String, Object>> clientAllReviews = clientService.getAllClientReviews(clientId, clientOrganisationId);
		
		
        if (clientAllReviews == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to Fetch Client Reviews");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        
	        HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "All Client Reviews Fetched Successfully");
			list.put("reviews", clientAllReviews);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
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
            ResponseMessage responseMessage = new ResponseMessage(
            		APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client User Primary Organisation Updated successfully");
            return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Adding Client Contract Information Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Contract Added Successfully"),
                    @ApiResponse(code = 201, message = "Client Contract Created Successfully")
            }
    )
	@PostMapping("/client/org/contract/add")
	public ResponseEntity<Object> addClientContract(
			@RequestBody ClientContract clientContract) {
		
		ClientContract clientContractObj = clientService.addClientContract(clientContract);
        if (clientContractObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to add Client Contract");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Contract Added Successfully");
			list.put("contractId", clientContractObj.getId());
			
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Update Client Contract Information Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Contract Updated Successfully"),
            }
    )
	@PutMapping("/client/org/contract")
	public ResponseEntity<Object> updateClientContract(
			@RequestBody ClientContract clientContract) {
		
		ClientContract clientContractObj = clientService.updateClientContract(clientContract);
        if (clientContractObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update Client Contract");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Client Contract Updated Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Update Client Contract Information Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Contract Updated Successfully"),
            }
    )
	@PutMapping("/client/org/contract/inactive/{contractId}")
	public ResponseEntity<Object> deleteClientContract(@PathVariable("contractId") Integer contractId) {
		
		boolean isdeleted = clientService.deleteClientContract(contractId);
        if (!isdeleted) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to delete Client Contract");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"Client Contract Deleted Successfully");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Get Client Contracts Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Contracts Fetched Successfully"),
            }
    )
	@GetMapping("/client/org/contracts/{clientOrgId}")
	public ResponseEntity<Object> getClientContracts(@PathVariable("clientOrgId") Integer clientOrgId) {
		
		List<ClientContract> clientContracts= clientService.getClientContracts(clientOrgId);
        if (clientContracts == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to fetch Client Contracts");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Contracts Fetched Successfully");
			list.put("clientContracts", clientContracts);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Adding Client Task Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Task Added Successfully"),
                    @ApiResponse(code = 201, message = "Client Task Created Successfully")
            }
    )
	@PostMapping("/client/org/task/add")
	public ResponseEntity<Object> addClientTasks(
			@RequestBody Map<String, Object> requestData ) {
		
		ClientTask clientTaskObj = clientService.addClientTask(requestData);
        if (clientTaskObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to add Client Task");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Task Added Successfully");
			list.put("taskId", clientTaskObj.getId());
			SendTaskNotification(clientTaskObj, NotificationType.TASK_CREATE);
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Updating Client Task Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Task Updated Successfully")
            }
    )
	@PutMapping("/client/org/task")
	public ResponseEntity<Object> updateClientTask(
			@RequestBody Map<String, Object> requestData ) {
		
		ClientTask clientTaskObj = clientService.updateClientTask(requestData);
        if (clientTaskObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update Client Task");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Task Updated Successfully");
			list.put("taskId", clientTaskObj.getId());
			
			SendTaskNotification(clientTaskObj, NotificationType.TASK_UPDATE);
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Delete Client Task Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Task Deleted Successfully")
            }
    )
	@PutMapping("/client/org/task/inactive")
	public ResponseEntity<Object> deleteClientTask(
			@RequestBody Map<String, Object> requestData ) {
		
		ClientTask clientTaskObj = clientService.deleteClientTask(requestData);
        if (clientTaskObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to delete Client Task");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Task Deleted Successfully");
			list.put("taskId", clientTaskObj.getId());
			
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Adding Client Task Comment Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Task Comment Added Successfully"),
                    @ApiResponse(code = 201, message = "Client Task Comment Created Successfully")
            }
    )
	@PostMapping("/client/org/task/comment/add")
	public ResponseEntity<Object> addClientTaskComments(@RequestBody ClientTaskComments clientTaskComments) {
		
		ClientTaskComments clientTaskComment = clientService.addClientTaskComments(clientTaskComments);
		if (clientTaskComment == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to add Client Task Comment");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Task Comment Added Successfully");
			list.put("commentId", clientTaskComment.getId());
			SendTaskCommentNotification(clientTaskComment, NotificationType.TASK_COMMENT);
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Get Client Tasks Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Tasks Fetched Successfully"),
            }
    )
	@GetMapping("/client/org/tasks/{clientOrgId}")
	public ResponseEntity<Object> getClientTasks(@PathVariable("clientOrgId") Integer clientOrgId) {
		
		List<Map<String, Object>> clientTasks= clientService.getClientTasks(clientOrgId);
        if (clientTasks == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to fetch Client Tasks");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Tasks Fetched Successfully");
			list.put("clientTasks", clientTasks);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Adding Client Building Repository Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Building Repository Added Successfully"),
                    @ApiResponse(code = 201, message = "Client Building Repository Created Successfully")
            }
    )
	@PostMapping("/client/org/building/repo/add")
	public ResponseEntity<Object> addBuildingRepository(@RequestBody ClientBuildingRepository clientBuildingRepository) {
		
		ClientBuildingRepository clientBuildingRepositoryObj = clientService.addClientBuildingRepository(clientBuildingRepository);
		if (clientBuildingRepositoryObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to add Client Building Repository");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Building Repository Added Successfully");
			list.put("buildingRepositoryId", clientBuildingRepositoryObj.getId());
			
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Updating Client Building Repository Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Building Repository Updated Successfully")
            }
    )
	@PutMapping("/client/org/building/repo")
	public ResponseEntity<Object> updateBuildingRepository(@RequestBody ClientBuildingRepository clientBuildingRepository) {
		
		ClientBuildingRepository clientBuildingRepositoryObj = clientService.updateClientBuildingRepository(clientBuildingRepository);
		if (clientBuildingRepositoryObj == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to update Client Building Repository");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Building Repository Updated Successfully");
			list.put("buildingRepositoryId", clientBuildingRepositoryObj.getId());
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Get Client Building Repositories Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Building Repositories Fetched Successfully"),
            }
    )
	@GetMapping("/client/org/building/repo/{clientOrgId}")
	public ResponseEntity<Object> getClientBuildingRepositories(@PathVariable("clientOrgId") Integer clientOrgId) {
		
		List<ClientBuildingRepository> clientTasks= clientService.getClientBuildingRepositories(clientOrgId);
        if (clientTasks == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to fetch Client Building Repositories");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Building Repositories Fetched Successfully");
			list.put("buildingRepositories", clientTasks);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
        }
	}
	
	@ApiOperation(value = "Delete Client Building Repository Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Building Repository Deleted Successfully")
            }
    )
	@PutMapping("/client/org/building/repo/inactive")
	public ResponseEntity<Object> deleteClientBuildingRepository(
			@RequestBody Map<String, Object> requestData ) {
		
		ClientBuildingRepository clientBuildingRepository = clientService.deleteClientBuildingRepository(requestData);
        if (clientBuildingRepository == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
	        		"Failed",
	        		"Failed to delete Client Building Repository");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
	        HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(builder.path("/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
			
			HashMap<String, Object> list = new HashMap();
	        list.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Client Building Repository Deleted Successfully");
			list.put("buildingRepositoryId", clientBuildingRepository.getId());
			
			return new ResponseEntity<Object>(list,/* headers, */ HttpStatus.OK);
        }
	}
	
	private void SendTaskNotification(ClientTask clientTask, NotificationType notificationType) {
		// TODO Auto-generated method stub
		clientService.sendTaskNotification(clientTask, notificationType.getValue());
	}
	
	private void SendTaskCommentNotification(ClientTaskComments clientTaskComment, NotificationType notificationType) {
		// TODO Auto-generated method stub
		clientService.sendTaskCommentNotification(clientTaskComment, notificationType.getValue());
	}
}
