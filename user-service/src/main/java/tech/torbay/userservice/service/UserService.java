package tech.torbay.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.UserRepository;

@Component
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;

	public Object resetPassword(Integer userId, Integer userType, String password) {
		// TODO Auto-generated method stub
		User userObj = userRepository.findByUserIdAndUserType(userId, userType);
		if( userObj == null) 
		{
			new ResourceNotFoundException("User", "userId", userId);
		}
//		user.setPassword(/* SecurityAES.encrypt( */user.get("password")/* ) */);
		userObj.setPassword(password);
		return userRepository.save(userObj);
	}

}
