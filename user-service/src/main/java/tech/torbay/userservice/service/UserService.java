package tech.torbay.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorUserRepository;

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
		return userRepository.save(user);
	}

	public User Login(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(username, password);
	}
}

