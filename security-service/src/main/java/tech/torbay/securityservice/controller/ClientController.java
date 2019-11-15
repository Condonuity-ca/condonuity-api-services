package tech.torbay.securityservice.controller;


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

import tech.torbay.securityservice.constants.Constants.APIStatusCode;
import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.exception.ResourceNotFoundException;
import tech.torbay.securityservice.repository.ClientUserRepository;
import tech.torbay.securityservice.service.ClientService;
import tech.torbay.securityservice.statusmessage.ResponseMessage;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Client Resource REST Endpoint", description = "Shows the client info")
public class ClientController {

    @Autowired
    ClientUserRepository clientRepository;
    
    @Autowired
    ClientService clientService;

	/*New APIs Structure*/
    
    @ApiOperation(value = "Client user existance check with Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client record exist already")
            }
    )
	@GetMapping("client/user/{email}")
	public ResponseEntity<Object> clientExists(@PathVariable("email") String email) {
		ClientUser client = clientService.findByEmail(email);
		
		// check email 
		//	- registered or not
		//	- password reseted or not
		//	- active/inactive
		
		if(client != null) {
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.REQUEST_SUCCESS.getValue(),"Success","Client Already Exists");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(APIStatusCode.NOT_FOUND.getValue(),"Resource not found error","Client Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
	}
    
	@ApiOperation(value = "New Organisation Exist Client user accept email invitation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client Accepted New Corporate Account Invitation")
            }
    )
	@PostMapping("client/invite/accept")
	public ResponseEntity<Object> acceptInvite(@RequestBody ClientUser client) {
		
		// accept invite requires
		// 1. Already have an client account
		// 2. userId
		// 3. new Org Id differ from previous one --- NEED TO ACCEPT TERMS AND CONDITION
		System.out.println(client.toString());
		
		if(clientService.findByEmail(client.getEmail()) != null) {
			// Already have an client account
			int userId = client.getClientId();
			if (userId != 0 /* && client.getOrganisationId() > 0 - check org parameter */) {
				if(clientService.addClientOrgAccountAssociation(client) != null) {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.REQUEST_SUCCESS.getValue(),
							"Success",
							"Client New Corporate Account Created");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				} else {
					ResponseMessage responseMessage = new ResponseMessage(
							APIStatusCode.NOT_FOUND.getValue(),
							"Resource not found error",
							"Client Record Not Found");
					return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
				}
			} else {
				ResponseMessage responseMessage = new ResponseMessage(
						APIStatusCode.NOT_FOUND.getValue(),
						"Resource not found error",
						"Client Record Not Found");
				return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
			}
		} else {
			// Invite Error
			ResponseMessage responseMessage = new ResponseMessage(
					APIStatusCode.NOT_FOUND.getValue(),
					"Resource not found error",
					"Client Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
	}
	
	@ApiOperation(value = "New Client User Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "New Client User Registred Successfully"),
                    @ApiResponse(code = 201, message = "New Client User Record Created Successfully")
            }
    )
	@PostMapping("client/user/register")
	public ResponseEntity<Object> addUser(@RequestBody ClientUser client, UriComponentsBuilder builder) {
		
		// org_id
		
        ClientUser clientUser = clientService.addClient(client);
        // check already exist or creation failed
        
        if (clientUser == null ) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Client Record Already Exists");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
        } else {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(builder.path("/client/{id}").buildAndExpand(client.getClientId()).toUri());
	        ResponseMessage responseMessage = new ResponseMessage(
	        		APIStatusCode.REQUEST_SUCCESS.getValue(),
	        		"Success",
	        		"New Client Record Created Successfully");
	        return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.CREATED);
        }
	}
	
	// Register a company
	@ApiOperation(value = "New Client Organisation Registration")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful New Client Organisation Registered")
            }
    )
	@PostMapping("client/org/register")
	public ResponseEntity<Object> addClientCompany(@RequestBody ClientOrganisation organisation , UriComponentsBuilder builder) {
		ClientOrganisation clientorganisation = clientService.addClientOrganisation(organisation);
        if (clientorganisation == null) {
        	ResponseMessage responseMessage = new ResponseMessage(
        			APIStatusCode.REQUEST_FAILED.getValue(),
        			"Failed",
        			"Client Organisation Already Exists");
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.CONFLICT);
        } else {
        	HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(organisation.getClientOrganisationId()).toUri());
            ResponseMessage responseMessage = new ResponseMessage(
            		APIStatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Client Organisation Registered for verification");
            return new ResponseEntity<Object>(responseMessage,headers, HttpStatus.CREATED);
        }
        
	}
	
    
}
