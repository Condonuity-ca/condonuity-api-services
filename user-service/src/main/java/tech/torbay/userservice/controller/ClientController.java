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

import tech.torbay.userservice.constants.Constants.StatusCode;
import tech.torbay.userservice.entity.Client;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.ClientRepository;
import tech.torbay.userservice.service.ClientService;
import tech.torbay.userservice.status.message.ResponseMessage;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Client Resource REST Endpoint", description = "Shows the client info")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    
    @Autowired
    ClientService clientService;

    @ApiOperation(value = "Fetching All clients details with in a Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful All Client Details")
            }
    )
    @GetMapping("/clients")
    public List<Client> getAllClients() {

       return clientService.findAll();   }


    @ApiOperation(value = "Fetching Single Client by Id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Client Details")
            }
    )
    @GetMapping("/clients/{clientId}")
    public Client getClientById(@PathVariable(value = "clientId") Integer clientId) {

        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "clientId", clientId));
    }

    @ApiOperation(value = "Client details Update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "client details updated successfully")
            }
    )
    @PutMapping("/clients/{clientId}")
    public Client updateClient(@PathVariable(value = "clientId") Integer clientId,
                                               @Valid @RequestBody Client toUpdateClient) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", clientId));


            client.setLegalName(toUpdateClient.getLegalName());
            client.setFirstName(toUpdateClient.getFirstName());
            client.setLastName(toUpdateClient.getLastName());
            client.setUserType(toUpdateClient.getUserType());
            client.setCity(toUpdateClient.getCity());
            client.setPhone(toUpdateClient.getPhone());
            client.setCountry_code(toUpdateClient.getCountry_code());

        Client updatedClient = clientRepository.save(client);
        return updatedClient;
    }


	/*New APIs Structure*/
    
    @ApiOperation(value = "Client user existance check with Email")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A client record exist already")
            }
    )
	@GetMapping("client/{email}")
	public ResponseEntity<Object> clientExists(@PathVariable("email") String email) {
		Client client = clientService.findByEmail(email);
		
		// check email 
		//	- registered or not
		//	- password reseted or not
		//	- active/inactive
		
		if(client != null) {
			ResponseMessage responseMessage = new ResponseMessage(StatusCode.REQUEST_SUCCESS.getValue(),"Success","Client Already Exists");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		} else {
			ResponseMessage responseMessage = new ResponseMessage(StatusCode.NOT_FOUND.getValue(),"Resource not found error","Client Record Not Found");
			return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
		}
		
	}
	
    
}
