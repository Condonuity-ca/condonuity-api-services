package tech.torbay.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.repository.UserRepository;
import tech.torbay.securityservice.repository.VendorOrganisationRepository;
import tech.torbay.securityservice.repository.VendorUserRepository;

@Component
public class VendorService {
	
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	UserRepository userRepository;

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
		VendorUser vendor = vendorUserRepository.findByEmail(email);
		return vendor;
	}

	public VendorUser addVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		try {
		
		if(vendorUserRepository.save(vendorUser) != null){
			
			vendorUser = vendorUserRepository.findByEmail(vendorUser.getEmail());
			
			System.out.println(vendorUser.toString());
			
			User user = new User();
			user.setUserId(vendorUser.getUserId());
			user.setUsername(vendorUser.getEmail());
			user.setUserType(Constants.UserType.VENDOR.getValue());
			
			System.out.println(user.toString());
			
			userRepository.save(user);
			
			return vendorUser;
		} else {
			return null;
		}
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public VendorOrganisation addVendorOrgnisation(Integer vendorUserId, VendorOrganisation vendorOrganisation) {
		// TODO Auto-generated method stub
		
		// Add new vendor
		// update to vendor admin user after org created
		try {
		vendorOrganisation = vendorOrganisationRepository.save(vendorOrganisation);
		if(vendorOrganisation != null) {
			
			VendorUser vendorUser = vendorUserRepository.findByUserId(vendorUserId);
			vendorUser.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
			
			vendorUserRepository.save(vendorUser);
			
			return vendorOrganisation;
		}
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public VendorUser findByVendorUserId(Integer userId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findByUserId(userId);
	}

	public VendorUser saveVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		return vendorUserRepository.save(vendorUser);
	}

	public VendorUser createVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		try {
			
			if(vendorUserRepository.save(vendorUser) != null){
				
				vendorUser = vendorUserRepository.findByEmail(vendorUser.getEmail());
				
				System.out.println(vendorUser.toString());
				
				User user = new User();
				user.setUserId(vendorUser.getUserId());
				user.setUsername(vendorUser.getEmail());
				user.setUserType(Constants.UserType.VENDOR.getValue());
				
				System.out.println(user.toString());
				
				userRepository.save(user);
				
				return vendorUser;
			} else {
				return null;
			}
			} catch (Exception exp) {
				exp.printStackTrace();
				return null;
			}
	}

	public List<VendorUser> getAllVendorUsersInOrganisation(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findAllByVendorOrganisationId(vendorOrganisationId);
	}
}

