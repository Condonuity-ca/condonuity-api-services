package tech.torbay.userservice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationPayment;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.repository.ClientAmenitiesRepository;
import tech.torbay.userservice.repository.ClientOrganisationPaymentRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientUserRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	ClientAmenitiesRepository clientAmenitiesRepository;
	@Autowired
	ClientOrganisationPaymentRepository clientOrganisationPaymentRepository;
	@Autowired
//	ClientOrganisationPaymentRepository clientOrganisationPaymentRepository;

	public List<ClientUser> getAllClientUsers() {
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

	public ClientUser getClientUserById(Integer userId) {
		// TODO Auto-generated method stub
		return clientUserRepository.findByClientId(userId);
	}

	public List<ClientOrganisation> getAllCorporateAccounts(Integer clientUserId) {
		// TODO Auto-generated method stub
		return null/* clientUserRepository.findClientOrganisationByClientId() */;
	}

	public ClientUser saveClient(ClientUser client) {
		// TODO Auto-generated method stub
		return clientUserRepository.save(client);
	}

	public ClientOrganisation updateOrganisation(ClientOrganisation clientOrg) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.save(clientOrg);
	}

	public ClientOrganisation getOrganisationById(Integer id) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.findByClientOrganisationId(id);
	}

	public List<ClientAmenities> getAmenitiesByOrgId(Integer orgId) {
		// TODO Auto-generated method stub
		return clientAmenitiesRepository.findAllByClientOrganisationId(orgId);
	}

	public List<ClientUser> getAllClientsByOrganisation(Integer orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClientOrganisationPayment> getPaymentBillingDetails(Integer orgId) {
		// TODO Auto-generated method stub
		return clientOrganisationPaymentRepository.findAllByOrganisationId(orgId);
	}

	public Object updateAmenities(ClientAmenities amenitiesInfo) {
		// TODO Auto-generated method stub
		return clientAmenitiesRepository.save(amenitiesInfo);
	}

	public List<ClientOrganisation> getAllClientOrganisations() {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.findAll();
	}
}

