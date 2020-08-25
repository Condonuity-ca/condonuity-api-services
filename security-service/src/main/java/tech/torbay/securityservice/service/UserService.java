package tech.torbay.securityservice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.securityservice.config.SecurityAES;
import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.DeleteStatus;
import tech.torbay.securityservice.constants.Constants.UserAccountStatus;
import tech.torbay.securityservice.entity.Amenities;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.PredefinedTags;
import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.User;
import tech.torbay.securityservice.entity.UserInviteLogs;
import tech.torbay.securityservice.entity.UserProfileImages;
import tech.torbay.securityservice.entity.VendorUser;
import tech.torbay.securityservice.exception.ResourceNotFoundException;
import tech.torbay.securityservice.repository.AmenitiesRepository;
import tech.torbay.securityservice.repository.ClientUserRepository;
import tech.torbay.securityservice.repository.PredefinedTagsRepository;
import tech.torbay.securityservice.repository.ServiceCitiesRepository;
import tech.torbay.securityservice.repository.UserInviteLogsRepository;
import tech.torbay.securityservice.repository.UserProfileImagesRepository;
import tech.torbay.securityservice.repository.UserRepository;
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
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	AmenitiesRepository amenitiesRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	UserProfileImagesRepository userProfileImagesRepository;
	@Autowired
	UserInviteLogsRepository userInviteLogsRepository;

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

	public User resetPassword(Integer userId, Integer userType, String password, String firstName, String lastName,
			String phone) {
		// TODO Auto-generated method stub

		// New User used to reset password after accept invite

		User userObj = userRepository.findByUserIdAndUserType(userId, userType);
		if (userObj == null) {
			new ResourceNotFoundException("User", "userId", userId);
		}
//		userObj.setPassword(encoder.encode( password ) );
		userObj.setPassword(SecurityAES.encrypt(password ) );
//		userObj.setPassword(password);

		try {
			if (userType == Constants.UserType.CLIENT.getValue()) {
				ClientUser clientUser = clientUserRepository.findByClientId(userId);

				clientUser.setFirstName(firstName);
				clientUser.setLastName(lastName);
				clientUser.setPhone(phone);
				clientUser.setDeleteStatus(UserAccountStatus.ACTIVE.getValue());

				clientUserRepository.save(clientUser);
			} else if (userType == Constants.UserType.VENDOR.getValue()) {
				VendorUser vendorUser = vendorUserRepository.findByUserId(userId);

				vendorUser.setFirstName(firstName);
				vendorUser.setLastName(lastName);
				vendorUser.setPhone(phone);
				vendorUser.setAccountVerificationStatus(UserAccountStatus.ACTIVE.getValue());// no need until org register

				//check
				vendorUser.setDeleteStatus(DeleteStatus.ACTIVE.getValue());
				vendorUserRepository.save(vendorUser);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return userRepository.save(userObj);
	}
	
	public User resetExistPassword(Integer userId, Integer userType, String password) {
		// TODO Auto-generated method stub

		// New User used to reset password after accept invite

		User userObj = userRepository.findByUserIdAndUserType(userId, userType);
		if (userObj == null) {
			new ResourceNotFoundException("User", "userId", userId);
		}
//		userObj.setPassword(encoder.encode( password ) );
		userObj.setPassword(SecurityAES.encrypt(password ) );
//		userObj.setPassword(password);


		return userRepository.save(userObj);
	}

	public User Login(String username, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(username, SecurityAES.encrypt(password));
	}

	public List<ServiceCities> findAllServiceCities() {
		// TODO Auto-generated method stub
		return serviceCitiesRepository.findAll();
	}

	public HashMap<String, Object> findAllProvinceCities() {
		// TODO Auto-generated method stub
		List<ServiceCities> serviceCities = serviceCitiesRepository.findAll();
		List<String> provinces = serviceCitiesRepository.findAllCityProvinces();

		HashMap<String, Object> provinceCities = new HashMap();

		for (String province : provinces) {
			List<HashMap<String, Object>> cities = serviceCities.stream()
					.filter(serviceCity -> serviceCity.getCityProvince().equals(province)).map(city -> {
						ObjectMapper oMapper = new ObjectMapper();
						// object -> Map
						HashMap<String, Object> cityObj = oMapper.convertValue(city, HashMap.class);
						cityObj.remove("cityProvince");
						cityObj.remove("createdAt");
						cityObj.remove("modifiedDate");
						cityObj.remove("status");
						return cityObj;
					}).collect(Collectors.toList());

			provinceCities.put(province, cities);
		}

		return provinceCities;
	}

	public void updateTermsAcceptedTimestamp(Integer userId, Integer userType, int organisationId, String hash) {
		// TODO Auto-generated method stub
		User userObj = userRepository.findByUserIdAndUserType(userId, userType);
		if (userObj != null) {

			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// Use Madrid's time zone to format the date in
			df.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE));

			String termsAcceptedDate = df.format(date);

			userObj.setTermsAcceptedDate(termsAcceptedDate);
			
			// insert log for using hash invite accept
			UserInviteLogs userInviteLogs = new UserInviteLogs();
			userInviteLogs.setUserId(userId);
			userInviteLogs.setUserType(userType);
			userInviteLogs.setOrganisationId(organisationId);
			userInviteLogs.setHash(hash);
			userInviteLogsRepository.save(userInviteLogs);
		}

		userRepository.save(userObj);
	}

	public List<PredefinedTags> findAllPredefinedTags() {
		// TODO Auto-generated method stub
		return predefinedTagsRepository.findAll();
	}

	public List<Amenities> findAllAmenities() {
		// TODO Auto-generated method stub
		return amenitiesRepository.findAll();
	}

	public String getUserProfileImage(Integer userId, int userType) {
		// TODO Auto-generated method stub
		UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(userId, userType);
		
		 try {
	        	if(userProfileImage != null) {
	            	return userProfileImage.getFileUrl();
	            } else {
	            	return "";
	            }
	        } catch(Exception exp) {
	        	exp.printStackTrace();
	        	return "";
	        }
	}
}
