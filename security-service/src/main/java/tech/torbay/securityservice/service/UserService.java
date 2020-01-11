package tech.torbay.securityservice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.entity.PredefinedTags;
import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.exception.ResourceNotFoundException;
import tech.torbay.securityservice.repository.PredefinedTagsRepository;
import tech.torbay.securityservice.repository.ServiceCitiesRepository;
import tech.torbay.securityservice.repository.UserRepository;
import tech.torbay.securityservice.repository.VendorOrganisationRepository;
import tech.torbay.securityservice.repository.VendorUserRepository;

@Component
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ServiceCitiesRepository serviceCitiesRepository;
	@Autowired
	PredefinedTagsRepository predefinedTagsRepository;
	
	@Autowired
    private BCryptPasswordEncoder encoder;
	
	public List<User> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(userRepository.findAll());
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(email);
		return user;
	}

	public User findByIdAndUserType(Integer id, Integer userType) {
		// TODO Auto-generated method stub
		return userRepository.findByUserIdAndUserType(id, userType);
	}
	
	
	
	public User resetPassword(Integer userId, Integer userType, String password) {
		// TODO Auto-generated method stub
		
		// New User used to reset password after accept invite
		
		User userObj = userRepository.findByUserIdAndUserType(userId, userType);
		if( userObj == null) 
		{
			new ResourceNotFoundException("User", "userId", userId);
		}
//		user.setPassword(/* SecurityAES.encrypt( */user.get("password")/* ) */);
		userObj.setPassword(password);
		return userRepository.save(userObj);
	}

	public User Login(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(username, /* SecurityAES.encrypt( */password/* ) */);
	}

	public List<ServiceCities> findAllServiceCities() {
		// TODO Auto-generated method stub
		return serviceCitiesRepository.findAll();
	}

	public void updateTermsAcceptedTimestamp(Integer userId, Integer userType) {
		// TODO Auto-generated method stub
		User userObj = userRepository.findByUserIdAndUserType(userId, userType);
		if( userObj != null) {
			
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone("Europe/London"));

			String termsAcceptedDate = df.format(date);
			
			userObj.setTermsAcceptedDate(termsAcceptedDate);
		}
		
		userRepository.save(userObj);
	}

	public List<PredefinedTags> findAllPredefinedTags() {
		// TODO Auto-generated method stub
		return predefinedTagsRepository.findAll();
	}
}

