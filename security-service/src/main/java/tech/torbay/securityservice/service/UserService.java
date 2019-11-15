package tech.torbay.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.exception.ResourceNotFoundException;
import tech.torbay.securityservice.repository.UserRepository;
import tech.torbay.securityservice.repository.VendorOrganisationRepository;
import tech.torbay.securityservice.repository.VendorUserRepository;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(userRepository.findAll());
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(email);
		System.out.println(user.toString());
		return user;
	}

	public User findByIdAndUserType(Integer id, Integer userType) {
		// TODO Auto-generated method stub
		return userRepository.findByUserIdAndUserType(id, userType);
	}

	public User resetPassword(User user) {
		// TODO Auto-generated method stub
		user = userRepository.findByUserIdAndUserType(user.getUserId(), user.getUserType());
		if( user == null) 
		{
			new ResourceNotFoundException("User", "userId", user.getUserId());
		}
		return userRepository.save(user);
	}

	public User Login(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(username, password);
	}
}

