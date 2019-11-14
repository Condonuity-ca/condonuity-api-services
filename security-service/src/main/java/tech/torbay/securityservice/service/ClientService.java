package tech.torbay.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.entity.ClientOrganisation;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.repository.ClientOrganisationRepository;
import tech.torbay.securityservice.repository.ClientUserRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;

	public List<ClientUser> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(clientUserRepository.findAll());
	}

	public ClientUser findByEmail(String email) {
		// TODO Auto-generated method stub
		ClientUser client = clientUserRepository.findByEmail(email);
		System.out.println(client.toString());
		return client;
	}

	public ClientUser addClientOrgAccountAssociation(ClientUser clientUser) {
		// TODO Auto-generated method stub
		return /* clientUser */null;
	}

	public ClientUser addClient(ClientUser clientUser) {
		// TODO Auto-generated method stub
		return clientUserRepository.save(clientUser);
	}

	public ClientOrganisation addClientOrganisation(ClientOrganisation clientorganisation) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.save(clientorganisation);
	}

	public ClientUser findById(Integer userId) {
		// TODO Auto-generated method stub
		return clientUserRepository.findByClientId(userId);
	}
}

