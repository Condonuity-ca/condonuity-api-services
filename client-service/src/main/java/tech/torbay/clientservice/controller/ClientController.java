package tech.torbay.clientservice.controller;


import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.torbay.clientservice.entity.Client;
import tech.torbay.clientservice.exception.ResourceNotFoundException;
import tech.torbay.clientservice.repository.ClientRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "Client Resource REST Endpoint", description = "Shows the client info")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @ApiOperation(value = "Fetching All clients details with in a Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful All Client Details")
            }
    )
    @GetMapping("/clients")
    public List<Client> getAllClients() {

        clientRepository.flush();
       return Lists.newArrayList(clientRepository.findAll());
    }

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








}
