package tech.torbay.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.repository.VendorOrganisationRepository;
import tech.torbay.securityservice.repository.VendorUserRepository;

@Component
public class VendorService {
	
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;

	public List<VendorUser> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(vendorUserRepository.findAll());
	}
	
	public VendorOrganisation findByVendorOrgId(Integer id) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.findByVendorOrganisationId(id);
	}

	public VendorUser findByEmail(String email) {
		// TODO Auto-generated method stub
		VendorUser client = vendorUserRepository.findByEmail(email);
		System.out.println(client.toString());
		return client;
	}

	public VendorUser addVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		return vendorUserRepository.save(vendorUser);
	}

	public VendorOrganisation addVendorOrgnisation(VendorOrganisation vendorOrganisation) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.save(vendorOrganisation);
	}

	public VendorUser findByVendorUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
}

