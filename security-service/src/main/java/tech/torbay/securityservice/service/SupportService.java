package tech.torbay.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.entity.SupportUser;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.exception.ResourceNotFoundException;
import tech.torbay.securityservice.repository.SupportUserRepository;
import tech.torbay.securityservice.repository.UserRepository;
import tech.torbay.securityservice.repository.VendorOrganisationRepository;
import tech.torbay.securityservice.repository.VendorUserRepository;

@Component
public class SupportService {
	
	@Autowired
	SupportUserRepository supportUserRepository;

	public SupportUser findBySupportUserId(Integer id) {
		// TODO Auto-generated method stub
		return supportUserRepository.findOneById(id);
	}

	public SupportUser addSupportUser(SupportUser supportUser) {
		// TODO Auto-generated method stub
		supportUser.setUserRole(Constants.UserRole.ADMIN.getValue());
		supportUser.setUserType(Constants.UserType.SUPPORT_USER.getValue());
		return supportUserRepository.save(supportUser);
	}

	public SupportUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return supportUserRepository.findByEmail(email);
	}
	
	
}

