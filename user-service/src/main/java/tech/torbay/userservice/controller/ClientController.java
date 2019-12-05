package tech.torbay.userservice.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import tech.torbay.userservice.constants.Constants.APIStatusCode;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationPayment;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.service.ClientService;
import tech.torbay.userservice.statusmessage.ResponseMessage;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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
    
    @ApiOperation(value = "Fetching A Client Details In an Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client details fetched successfully")
            }
    )
	@GetMapping("/client/user/{id}")
	public ResponseEntity<Object> getClientUserById(@PathVariable("id") Integer id) {
		ClientUser client = clientService.getClientUserById(id);
		
		System.out.println(client);
		
		List<ClientOrganisation> orgs = clientService.getAllCorporateAccounts(id);
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
	
//	@ApiOperation(value = "Fetching All clients details with in Condonuity Application")
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 200, message = "Successful All Client Details")
//            }
//    )
//	@GetMapping("/client/users")
//	public ResponseEntity<Object> getAllClients() {
//		List<ClientUser> list = clientService.getAllClientUsers();
//		
//		HashMap<String, Object> response = new HashMap();
//		if(list != null) {
//			response.put("statusCode", APIStatusCode.REQUEST_SUCCESS.getValue());
//			response.put("statusMessage", "Success");
//			response.put("responseMessage", "Client details fetched successfully");
//			response.put("clients", list);
//			
//			return new ResponseEntity<Object>(response, HttpStatus.OK);
//		} else {
//			response.put("statusCode", APIStatusCode.REQUEST_FAILED.getValue());
//			response.put("statusMessage", "Failed");
//			response.put("responseMessage", "Database Error");
//
//			return new ResponseEntity<Object>(response, HttpStatus.OK);
//		}
//	}
	
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
		List<ClientUser> clients = clientService.getAllClientsByOrganisation(id);
		List<ClientOrganisationPayment> paymentBillingDetails = clientService.getPaymentBillingDetails(id);
		
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
	
    
}
