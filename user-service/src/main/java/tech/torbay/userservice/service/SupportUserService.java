package tech.torbay.userservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.DeleteStatus;
import tech.torbay.userservice.constants.Constants.OrganisationAccountStatus;
import tech.torbay.userservice.constants.Constants.UserAccountStatus;
import tech.torbay.userservice.constants.Constants.UserType;
import tech.torbay.userservice.constants.Constants.VendorRatingCategoryPercentage;
import tech.torbay.userservice.email.SpringBootEmail;
import tech.torbay.userservice.entity.Amenities;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationProfileImages;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.ServiceCities;
import tech.torbay.userservice.entity.SupportUserLogs;
import tech.torbay.userservice.entity.UserProfileImages;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorOrganisationProfileImages;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.repository.AmenitiesRepository;
import tech.torbay.userservice.repository.ClientAmenitiesRepository;
import tech.torbay.userservice.repository.ClientAssociationRepository;
import tech.torbay.userservice.repository.ClientOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.ExternalMessageCommentRepository;
import tech.torbay.userservice.repository.ExternalMessageRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.ServiceCitiesRepository;
import tech.torbay.userservice.repository.SupportUserLogsRepository;
import tech.torbay.userservice.repository.UserProfileImagesRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.VendorBrandsRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorLicensesRepository;
import tech.torbay.userservice.repository.VendorMembershipsRepository;
import tech.torbay.userservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorProductsRepository;
import tech.torbay.userservice.repository.VendorServicesCitiesRepository;
import tech.torbay.userservice.repository.VendorServicesRepository;
import tech.torbay.userservice.repository.VendorTagsRepository;
import tech.torbay.userservice.repository.VendorUserRepository;

@Component
public class SupportUserService {
	
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	VendorTagsRepository vendorTagsRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	ClientAssociationRepository clientAssociationRepository;
	@Autowired
	ProjectReviewRatingRepository projectReviewRatingRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ExternalMessageRepository externalMessageRepository;
	@Autowired
	ExternalMessageCommentRepository externalMessageCommentRepository;
	@Autowired
	ClientOrganisationProfileImagesRepository clientOrganisationProfileImageRepository;
	@Autowired
	ServiceCitiesRepository servicesCitiesRepository;
	@Autowired
	ClientAmenitiesRepository clientAmenitiesRepository;
	@Autowired
	AmenitiesRepository amenitiesRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;
	@Autowired
	VendorOrganisationProfileImagesRepository vendorOrganisationProfileImagesRepository;
	@Autowired
	PredefinedTagsRepository predefinedTagsRepository;
	@Autowired
	SupportUserLogsRepository supportUserLogsRepository;
	@Autowired
	VendorBrandsRepository vendorBrandsRepository;
	@Autowired
	VendorServicesRepository vendorServicesRepository;
	@Autowired
	VendorServicesCitiesRepository vendorServicesCitiesRepository;
	@Autowired
	VendorProductsRepository vendorProductsRepository;
	@Autowired
	VendorLicensesRepository vendorLicensesRepository;
	@Autowired
	VendorMembershipsRepository vendorMembershipsRepository;
	@Autowired
	UserProfileImagesRepository userProfileImagesRepository;
	
	public boolean updateOrganisationApprovalStatus(Integer organisationId, Integer userType, Integer approvalStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		
		updateLogs(supportUserId, "Organisation", approvalStatus, organisationId, userType);
		
		String emailContentOrganisation = "";
		String emailContentUSER = "";
		if(approvalStatus == OrganisationAccountStatus.APPROVED.getValue()) {
			emailContentOrganisation = Constants.ORGANISATION_ACCOUNT_APPROVAL_ALERT;
		} else if (approvalStatus == OrganisationAccountStatus.REJECTED.getValue()) {
			emailContentOrganisation = Constants.ORGANISATION_ACCOUNT_REJECT_ALERT;
		}
		
		if(approvalStatus == DeleteStatus.ACTIVE.getValue()) {
			emailContentUSER = Constants.USER_ACCOUNT_ACTIVE_ALERT;
		} else if (approvalStatus == DeleteStatus.INACTIVE.getValue()) {
			emailContentUSER = Constants.USER_ACCOUNT_REMOVE_ALERT;
		}
		
		if(userType == UserType.CLIENT.getValue()) {
			ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(organisationId);
			switch(approvalStatus) {
				case 1:{//DeleteStatus.ACTIVE.getValue()
					clientOrganisation.setActiveStatus(OrganisationAccountStatus.APPROVED.getValue());
					break;
				}
				case 2:{//DeleteStatus.INACTIVE.getValue()
					clientOrganisation.setActiveStatus(OrganisationAccountStatus.REJECTED.getValue());
					break;
				}
			}
			
			ClientOrganisation ClientOrganisationObj = clientOrganisationRepository.save(clientOrganisation);
			
			//Update via Email and User as Inactive
			if(ClientOrganisationObj != null) {
				
				SendOrganisationAlertEmailForRemovalFromSystem(ClientOrganisationObj.getManagementEmail(), ClientOrganisationObj.getOrganisationName(), emailContentOrganisation );
				
				clientAssociationRepository.setDeleteStatusByClientOrganisationId(approvalStatus, organisationId);
				
				List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllActiveUsersByClientOrganisationId(organisationId);
				for (ClientAssociation clientAssociation : clientAssociations) {
					
					ClientUser clientUser = clientUserRepository.findByClientId(clientAssociation.getClientId());
					
					clientUser.setDeleteStatus(approvalStatus);
					clientUserRepository.save(clientUser);
					List<String> org = new ArrayList<>();
					org.add(ClientOrganisationObj.getOrganisationName());
					SendUserAlertEmailForRemovalFromSystem(clientUser.getEmail(), clientUser.getFirstName(), clientUser.getLastName(), org, emailContentUSER );
				}
				
				return true;
			}
		} else if(userType == UserType.VENDOR.getValue()) {
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(organisationId);
			switch(approvalStatus) {
				case 1:{//DeleteStatus.ACTIVE.getValue()
					vendorOrganisation.setActiveStatus(OrganisationAccountStatus.APPROVED.getValue());
					break;
				}
				case 2:{//DeleteStatus.INACTIVE.getValue()
					vendorOrganisation.setActiveStatus(OrganisationAccountStatus.REJECTED.getValue());
					break;
				}
			}
			
			VendorOrganisation vendorOrganisationObj = vendorOrganisationRepository.save(vendorOrganisation);
			
			if(vendorOrganisationObj != null) {
				
				SendOrganisationAlertEmailForRemovalFromSystem(vendorOrganisationObj.getEmail(), vendorOrganisationObj.getCompanyName(), emailContentOrganisation );
				
				vendorUserRepository.setDeleteStatusByVendorOrganisationId(approvalStatus, organisationId);
				
				List<VendorUser> vendorUsers = vendorUserRepository.findAllActiveUsersByVendorOrganisationId(organisationId);
				
				for(VendorUser vendorUser : vendorUsers) {
					List<String> org = new ArrayList<>();
					org.add(vendorOrganisationObj.getCompanyName());
					SendUserAlertEmailForRemovalFromSystem(vendorUser.getEmail(), vendorUser.getFirstName(), vendorUser.getLastName(), org, emailContentUSER );
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean updateOrganisationActivationStatus(Integer organisationId, Integer userType, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		
		updateLogs(supportUserId, "Organisation", activeStatus, organisationId, userType);
		
		String emailContentOrganisation = "";
		String emailContentUSER = "";
		if(activeStatus == DeleteStatus.ACTIVE.getValue()) {
			emailContentOrganisation = Constants.ORGANISATION_ACCOUNT_ACTIVE_ALERT;
		} else if (activeStatus == DeleteStatus.INACTIVE.getValue()) {
			emailContentOrganisation = Constants.ORGANISATION_ACCOUNT_REMOVE_ALERT;
		}
		
		if(activeStatus == DeleteStatus.ACTIVE.getValue()) {
			emailContentUSER = Constants.USER_ACCOUNT_ACTIVE_ALERT;
		} else if (activeStatus == DeleteStatus.INACTIVE.getValue()) {
			emailContentUSER = Constants.USER_ACCOUNT_REMOVE_ALERT;
		}
		
		if(userType == UserType.CLIENT.getValue()) {
			ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(organisationId);
			switch(activeStatus) {
				case 1:{//DeleteStatus.ACTIVE.getValue()
					clientOrganisation.setDeleteStatus(DeleteStatus.ACTIVE.getValue());
					break;
				}
				case 2:{//DeleteStatus.INACTIVE.getValue()
					clientOrganisation.setDeleteStatus(DeleteStatus.INACTIVE.getValue());
					break;
				}
			}
			
			ClientOrganisation ClientOrganisationObj = clientOrganisationRepository.save(clientOrganisation);
			
			//Update via Email and User as Inactive
			if(ClientOrganisationObj != null) {
				
				SendOrganisationAlertEmailForRemovalFromSystem(ClientOrganisationObj.getManagementEmail(), ClientOrganisationObj.getOrganisationName(), emailContentOrganisation );
				
				clientAssociationRepository.setDeleteStatusByClientOrganisationId(activeStatus, organisationId);
				
				List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllActiveUsersByClientOrganisationId(organisationId);
				for (ClientAssociation clientAssociation : clientAssociations) {
					
					ClientUser clientUser = clientUserRepository.findByClientId(clientAssociation.getClientId());
					List<String> org = new ArrayList<>();
					org.add(ClientOrganisationObj.getOrganisationName());
					SendUserAlertEmailForRemovalFromSystem(clientUser.getEmail(), clientUser.getFirstName(), clientUser.getLastName(), org, emailContentUSER );
				}
				
				return true;
			}
		} else if(userType == UserType.VENDOR.getValue()) {
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(organisationId);
			switch(activeStatus) {
				case 1:{//DeleteStatus.ACTIVE.getValue()
					vendorOrganisation.setDeleteStatus(DeleteStatus.ACTIVE.getValue());
					break;
				}
				case 2:{//DeleteStatus.INACTIVE.getValue()
					vendorOrganisation.setDeleteStatus(DeleteStatus.INACTIVE.getValue());
					break;
				}
			}
			
			VendorOrganisation vendorOrganisationObj = vendorOrganisationRepository.save(vendorOrganisation);
			
			if(vendorOrganisationObj != null) {
				
				SendOrganisationAlertEmailForRemovalFromSystem(vendorOrganisationObj.getEmail(), vendorOrganisationObj.getCompanyName(), emailContentOrganisation );
				
				vendorUserRepository.setDeleteStatusByVendorOrganisationId(activeStatus, organisationId);
				
				List<VendorUser> vendorUsers = vendorUserRepository.findAllActiveUsersByVendorOrganisationId(organisationId);
				
				for(VendorUser vendorUser : vendorUsers) {
					List<String> org = new ArrayList<>();
					org.add(vendorOrganisationObj.getCompanyName());
					SendUserAlertEmailForRemovalFromSystem(vendorUser.getEmail(), vendorUser.getFirstName(), vendorUser.getLastName(), org, emailContentUSER );
				}
				return true;
			}
		}
		return false;
	}


	private void updateLogs(Integer supportUserId, String category, Integer activeStatus, Integer logCategoryId,
			Integer userType) {
		// TODO Auto-generated method stub
		SupportUserLogs supportUserLogs= new SupportUserLogs();
		supportUserLogs.setSupportUserId(supportUserId);
		supportUserLogs.setLogCategory(category);
		supportUserLogs.setLogCategoryId(String.valueOf(logCategoryId));
		supportUserLogs.setLogUserType(userType);
		String organisationType = "";
		if(userType == UserType.CLIENT.getValue()) {
			organisationType = "Client : ";
		} else if (userType == UserType.VENDOR.getValue()) {
			organisationType = "Vendor : ";
		}
		
		String logs = "";
		if(activeStatus == UserAccountStatus.ACTIVE.getValue()) {
			logs = organisationType + category + " status Activated By Support User (ID : "+supportUserId+")";
		} else if(activeStatus == UserAccountStatus.INACTIVE.getValue()){
			logs = organisationType + category + " status De-Activated By Support User (ID : "+supportUserId+")";
		} else {
			logs = organisationType + category + "updated By Support User (ID : "+supportUserId+")";
		}
		supportUserLogs.setLogs(logs);
		
		supportUserLogsRepository.save(supportUserLogs);
	}


	public boolean updateUserActivationStatus(Integer userId, /* Integer organisationId, */ Integer userType,
			Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		updateLogs(supportUserId, "User", activeStatus, userId, userType);
		String emailContent = "";
		if(activeStatus == DeleteStatus.ACTIVE.getValue()) {
			emailContent = Constants.USER_ACCOUNT_ACTIVE_ALERT;
		} else if (activeStatus == DeleteStatus.INACTIVE.getValue()) {
			emailContent = Constants.USER_ACCOUNT_REMOVE_ALERT;
		}
		
		if(userType == UserType.CLIENT.getValue()) {
			//single-organisation
//			int isUpdated = clientAssociationRepository.setDeleteStatusByClientIdAndClientOrganisationId(activeStatus, userId, organisationId);
			//all-organisations
			int isUpdated = clientAssociationRepository.setDeleteStatusByClientId(activeStatus, userId);
			int isUpdatedUser = clientUserRepository.setDeleteStatusByClientId(activeStatus, userId);
			
			if(isUpdated > 0) {
				ClientUser clientUser = clientUserRepository.findByClientId(userId);
				
				List<String> organisations = new ArrayList();
				organisations = getAllClientOrganisationsByClientId(userId);
				SendUserAlertEmailForRemovalFromSystem(clientUser.getEmail(), clientUser.getFirstName(), clientUser.getLastName(), organisations, emailContent );
				return true;
				
			}
		} else if(userType == UserType.VENDOR.getValue()) {
			int isUpdated = vendorUserRepository.setDeleteStatusByVendorId/* AndVendorOrganisationId */(activeStatus,
					userId/* , organisationId */);
			
			if(isUpdated > 0) {
				VendorUser vendorUser = vendorUserRepository.findByUserId(userId);
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorUser.getVendorOrganisationId());
				List<String> organisations = new ArrayList();
				organisations.add(vendorOrganisation.getCompanyName());
				SendUserAlertEmailForRemovalFromSystem(vendorUser.getEmail(), vendorUser.getFirstName(), vendorUser.getLastName(), organisations, emailContent);
				return true;
			}
		}
		return false;
	}
	
	private List<String> getAllClientOrganisationsByClientId(Integer clientId) {
		// TODO Auto-generated method stub
		List<ClientAssociation> clientAssociations = clientAssociationRepository.findAllByClientId(clientId);
		
		List<String> organisationsList = new ArrayList();
		for (ClientAssociation clientAssociation : clientAssociations) {
			if(clientAssociation.getUserAccountStatus() == Constants.UserAccountStatus.ACTIVE.getValue() ) {
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(clientAssociation.getClientOrganisationId());
				organisationsList.add(clientOrganisation.getOrganisationName());
			}
		}
		
		return organisationsList;
	}


	private void SendUserAlertEmailForRemovalFromSystem(String userEmail,String firstName,String lastName,List<String> organisationName,String content) {
		// TODO Auto-generated method stub
		SpringBootEmail springBootEmail = new SpringBootEmail();
		
		try {
			springBootEmail.sendUserAlertEmailForRemovalFromSystem(userEmail, firstName+" "+lastName, organisationName , content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
	
	private void SendOrganisationAlertEmailForRemovalFromSystem(String userEmail, String organisationName,String content) {
		// TODO Auto-generated method stub
		SpringBootEmail springBootEmail = new SpringBootEmail();
		
		try {
			springBootEmail.sendOrganisationAlertEmailForRemovalFromSystem(userEmail, organisationName , content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}


	public boolean updateReviewActivationStatus(Integer reviewRatingId, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = projectReviewRatingRepository.setStatusById(activeStatus, reviewRatingId);
						
		if(isUpdated > 0) {
			updateLogs(supportUserId, "Review", activeStatus, reviewRatingId, UserType.CLIENT.getValue());
			return true;
		}
		return false;
	}


	public boolean updateClientCorporationInformation(Integer clientOrganisationId, String corporationName, String corporationNumber,
			Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = clientOrganisationRepository.setOrganisationNameAndCorporateNumberByClientOrganisationId(corporationName, corporationNumber, clientOrganisationId);
		
		if(isUpdated > 0) {
			updateLogs(supportUserId, "ClientOrganisation", 0, clientOrganisationId, UserType.CLIENT.getValue());
			return true;
		}
		return false;
	}


	public boolean updateProjectActivationStatus(Integer projectId, Integer deleteStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = projectRepository.setDeleteStatusByProjectId(deleteStatus, projectId);
		
		if(isUpdated > 0) {
			updateLogs(supportUserId, "Projects", deleteStatus, projectId, UserType.CLIENT.getValue());
			return true;
		}
		return false;
	}


	public boolean updateExternalMessageActivationStatus(Integer externalMessageId, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = externalMessageRepository.setDeleteStatusById(activeStatus, externalMessageId);
		
		if(isUpdated > 0) {
			updateLogs(supportUserId, "ExternalMessage", activeStatus, externalMessageId, 0);
			return true;
		}
		return false;
	}
	
	public boolean updateExternalMessageCommentActivationStatus(Integer externalMessageCommentId, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = externalMessageCommentRepository.setDeleteStatusById(activeStatus, externalMessageCommentId);
		
		if(isUpdated > 0) {
			updateLogs(supportUserId, "ExternalMessageComment", activeStatus, externalMessageCommentId, 0);
			return true;
		}
		return false;
	}
	
	public boolean updateUserProfile(Integer userId, Integer organisationId, Integer userType,
			String firstName, String lastName, Integer userRole, Integer clientUserType, Integer supportUserId) {
		// TODO Auto-generated method stub
		
		updateLogs(supportUserId, "UserProfile", 0 , userId, userType);
		
		if(userType == UserType.CLIENT.getValue()) {
			int isUpdated = clientUserRepository.setFirstNameAndLastNameByClientId(firstName, lastName, userId);
			int isUpdatedRoleAndUserType = clientAssociationRepository.setUserRoleAndClientUserTypeByClientIdAndClientOrganisationId(userRole, clientUserType, userId, organisationId);		
			if(isUpdated > 0 && isUpdatedRoleAndUserType > 0) {
				return true;
			}
		} else if(userType == UserType.VENDOR.getValue()) {
			int isUpdated = vendorUserRepository.setFirstNameAndLastNameAndUserRoleByVendorIdAndVendorOrganisationId(firstName, lastName, userRole, userId, organisationId);
			
			if(isUpdated > 0) {
				return true;
			}
			
			
		}
		return false;
	}


	public Map<String, Object> getUnApprovedVendorAndClientList() {
		// TODO Auto-generated method stub
		
		Map<String, Object> result = new HashMap();
		
		List<Map<String, Object>>  upApprovedClientOrganisations = new ArrayList();
		List<Map<String, Object>>  upApprovedVendorOrganisations = new ArrayList();
		
		//Get Client Organisations List
		List<ClientOrganisation> unApprovedclientOrgs = clientOrganisationRepository.findAllByActiveStatus(Constants.OrganisationAccountStatus.REGISTERED.getValue());
		
		for(ClientOrganisation clientOrg : unApprovedclientOrgs) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(clientOrg, Map.class);
	        if(clientOrg.getCity() != null ) {
	        	try {
	        		Integer city = Integer.parseInt(clientOrg.getCity());
	        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
	        		map.put("city",serviceCity.getCityName());
	        	} catch(Exception exp) {
	        		map.put("city","");
	        	}
	        	
	        } else {
	        	map.put("city","");
			}
	        List<Map<String, Object>> amenitiesInfo = getAmenitiesByOrgId(clientOrg.getClientOrganisationId());
	        map.put("amenities", amenitiesInfo);
	        String logo = getOrganisationLogo(clientOrg.getClientOrganisationId());
	        if(logo != null)
	        	map.put("organisationLogo", logo);
	        else
	        	map.put("organisationLogo", "");
	        
	        upApprovedClientOrganisations.add(map);
		}
    	
		//Get Vendor Organisations List
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAllByActiveStatus(Constants.OrganisationAccountStatus.REGISTERED.getValue());
		
		List<Map<String, Object>> vendorOrganisations = new ArrayList();
		
		for(VendorOrganisation vendorOrg : vendorOrgsAll) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
	        
	        
	        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
	        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
	        } else {
	        	map.put("vendorTags","");
	        }
	        if(vendorOrg.getCity() != null ) {
	        	try {
	        		Integer city = Integer.parseInt(vendorOrg.getCity());
	        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
	        		map.put("city",serviceCity.getCityName());
	        	} catch(Exception exp) {
	        		map.put("city","");
	        	}
	        	
	        } else {
	        	map.put("city","");
			}
	        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
	        try {
		        String logo = getVendorOrganisationLogo(vendorOrg.getVendorOrganisationId());
		        if(logo != null)
		        	map.put("vendorProfileImageUrl", logo);
		        else
		        	map.put("vendorProfileImageUrl", "");
	        } catch(Exception exp) {
		        	exp.printStackTrace();
	        }
	        upApprovedVendorOrganisations.add(map);
		}
		
		result.put("clientOrganisations", upApprovedClientOrganisations);
		result.put("vendorOrganisations", upApprovedVendorOrganisations);
		
		return result;
	}
	
	public String getOrganisationLogo(Integer id) {
		// TODO Auto-generated method stub
		ClientOrganisationProfileImages clientOrganisationProfileImages = clientOrganisationProfileImageRepository.findByClientOrganisationId(id);
		
        if(clientOrganisationProfileImages != null)
        	return clientOrganisationProfileImages.getFileUrl();
        else
        	return null;
	}
	
	public List<Map<String, Object>> getAmenitiesByOrgId(Integer clientOrgId) {
		// TODO Auto-generated method stub
		List<ClientAmenities> amenities = clientAmenitiesRepository.findAllByClientOrganisationId(clientOrgId);
		List<Map<String, Object>> clientAmenities = new ArrayList();
		for(ClientAmenities clientAmenity : amenities) {

			Map<String, Object> map = new HashMap<>();
			Amenities amenity = amenitiesRepository.findOneById(clientAmenity.getAmenityId());
			map.put("amenityId",clientAmenity.getAmenityId());
			map.put("amenityName",amenity.getAmenitiesName());
			map.put("amenityLogo",amenity.getLogo());
			clientAmenities.add(map);
		}
		
		return clientAmenities;
	}
	
	public String getVendorOrganisationLogo(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		VendorOrganisationProfileImages vendorOrgProfileImage =  vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(vendorOrganisationId);
		
        if(vendorOrgProfileImage != null)
        	return vendorOrgProfileImage.getFileUrl();
        else
        	return null;
	}
	
	private Double getVendorCategoryRatings(Integer vendorOrgId) {
		// TODO Auto-generated method stub
		List<VendorCategoryRatings> vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndStatus(vendorOrgId, DeleteStatus.ACTIVE.getValue());
		
        try {
        	// Case1
//        	-- Common Over all Rating
//        	double sum = vendorRatings.stream().filter(o -> o.getRating() > 0).mapToDouble(VendorCategoryRatings::getRating).sum();
//	        
//	        if(sum >0) {
//	        	double rating = sum/vendorRatings.size();
//		       
//	        	return rating; 
//	        }
        	
        	//Case2
//        	Overall Rating By Category Percentage
        	double sumCategoryResponsiveness = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryProfessionalism = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryAccuracy = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryQuality = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	
        	System.out.print("sumCategoryResponsiveness "+ sumCategoryResponsiveness);
        	System.out.print("sumCategoryProfessionalism "+ sumCategoryProfessionalism);
        	System.out.print("sumCategoryAccuracy "+ sumCategoryAccuracy);
        	System.out.print("sumCategoryQuality "+ sumCategoryQuality);
        	
        	long responsivenessCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).count();
        	long professionalismCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).count();
        	long accuracyCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).count();
        	long qualityCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).count();
        	
        	
        	double overAllRating = (sumCategoryResponsiveness/responsivenessCount * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100) +
        			(sumCategoryProfessionalism/responsivenessCount * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100) +
        			(sumCategoryAccuracy/accuracyCount * Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100) +
        			(sumCategoryQuality/qualityCount * Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
        	
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue() "+ sumCategoryResponsiveness * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue() "+ sumCategoryProfessionalism * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.ACCURACY.getValue() "+ sumCategoryAccuracy *Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100);
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.QUALITY.getValue() "+ sumCategoryQuality *Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
        	
        	
        	return overAllRating;
        	
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return 0d;
        }
	}
	
	public String getVendorTags(List<VendorTags> vendorTags) {
		// TODO Auto-generated method stub
		try {
			
//			List<Integer> ids = vendorTags.stream().map(VendorTags::getId).collect(Collectors.toList());	
//			
//	        String tags = predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(","));
//	        
//	        return tags;
			List<Integer> ids = vendorTags.stream().map(VendorTags::getTagId).collect(Collectors.toList());	
			
	        
	        List<Object[]> tags = predefinedTagsRepository.findTagsByTagId(ids);
			List<String> allTags = new ArrayList();
			
			tags.stream().forEach((record) -> {
				Integer tagId = (Integer) record[0];
				String tagName = (String) record[1];
		        
		        allTags.add(tagName);
		        });
			String tagsStr = String.join(",", allTags);
			return tagsStr;
		} catch(Exception exp){
			exp.printStackTrace();
			return null;
		}
	}

	public Map<String, Object> GetAllReviewsForVendor(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return getVendorOrganisationById(vendorOrganisationId);
	}
	
	public Map<String, Object> getVendorOrganisationById(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		try {
			VendorOrganisation vendorOrg = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
			
			
			ObjectMapper objMapper = new ObjectMapper();
	        Map<String, Object> mappedObj = objMapper.convertValue(vendorOrg, Map.class);
	        
	        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
	        	mappedObj.put("vendorTags",getVendorTagsWithId(vendorOrg.getVendorTags()));
	        } else {
	        	mappedObj.put("vendorTags","[]");
	        }
	        if(vendorOrg.getCity() != null ) {
	        	try {
	        		Integer city = Integer.parseInt(vendorOrg.getCity());
	        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
	        		mappedObj.put("city",serviceCity.getCityName());
	        	} catch(Exception exp) {
	        		mappedObj.put("city","");
	        	}
	        	
	        } else {
	        	mappedObj.put("city","");
			}
	        mappedObj.put("rating",getVendorCategoryRatings(vendorOrganisationId));
	        mappedObj.put("detailedRating",getVendorDetailedRatings(vendorOrganisationId));
	        mappedObj.put("reviewsRatings",getVendorReviewsRatings(vendorOrganisationId));
	        
	        
	        
	        mappedObj.put("vendorServicesCities",vendorServicesCitiesRepository.getServiceCities(vendorOrganisationId));
	        mappedObj.put("services",vendorServicesRepository.getVendorServices(vendorOrganisationId));
	        mappedObj.put("products",vendorProductsRepository.getVendorProducts(vendorOrganisationId));
	        mappedObj.put("brands",vendorBrandsRepository.getVendorBrands(vendorOrganisationId));
	        mappedObj.put("licenses",vendorLicensesRepository.getVendorLicenses(vendorOrganisationId));
	        mappedObj.put("memberships",vendorMembershipsRepository.getVendorMemberships(vendorOrganisationId));
	        
	        try {
	        String logo = getOrganisationLogo(vendorOrganisationId);
	        if(logo != null)
	        	mappedObj.put("vendorProfileImageUrl", logo);
	        else
	        	mappedObj.put("vendorProfileImageUrl", "");
	        } catch(Exception exp) {
	        	exp.printStackTrace();
	        }
	        
			return mappedObj;
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null/* vendorOrganisationRepository.findByVendorOrganisationId(id) */;
	}
	
	private Object getVendorDetailedRatings(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorCategoryRatings> vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndStatus(vendorOrganisationId, DeleteStatus.ACTIVE.getValue());
		
        try {
        	double sumCategoryResponsiveness = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryProfessionalism = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryAccuracy = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryQuality = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
	        
        	Map<String, Object> mappedObj = new HashMap<String, Object>();
        	long responsivenessCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).count();
        	long professionalismCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).count();
        	long accuracyCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).count();
        	long qualityCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).count();
        	
//        	mappedObj.put("responsiveness", sumCategoryResponsiveness/responsivenessCount);
//        	mappedObj.put("professionalism", sumCategoryProfessionalism/professionalismCount);
//        	mappedObj.put("accuracy", sumCategoryAccuracy/accuracyCount);
//        	mappedObj.put("quality", sumCategoryQuality/qualityCount);
        	mappedObj.put("responsiveness", (sumCategoryResponsiveness/responsivenessCount) );
        	mappedObj.put("professionalism", (sumCategoryProfessionalism/professionalismCount) );
        	mappedObj.put("accuracy", (sumCategoryAccuracy/accuracyCount) );
        	mappedObj.put("quality", (sumCategoryQuality/qualityCount) );
//        	(sumCategoryResponsiveness/responsivenessCount * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100) +
// 			(sumCategoryProfessionalism/responsivenessCount * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100) +
// 			(sumCategoryAccuracy/accuracyCount * Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100) +
// 			(sumCategoryQuality/qualityCount * Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
        	return mappedObj;
	        
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return null;
        }
        
	}
	
	private Object getVendorTagsWithId(List<VendorTags> vendorTags) {
		// TODO Auto-generated method stub
		try {
			
			List<Integer> ids = vendorTags.stream().map(VendorTags::getTagId).collect(Collectors.toList());	
			
	        
	        List<Object[]> tags = predefinedTagsRepository.findTagsByTagId(ids);
			List<Map<String,Object>> allTags = new ArrayList();
			
			tags.stream().forEach((record) -> {
				Integer tagId = (Integer) record[0];
				String tagName = (String) record[1];
				
				
				Map<String,Object> map = new HashMap<>();
		        map.put("tagId", tagId);
		        map.put("tagName", tagName);
		        
		        
		        allTags.add(map);
		        });
			
			return allTags;
			
		} catch(Exception exp){
			exp.printStackTrace();
			return null;
		}
	}

	private Object getVendorReviewsRatings(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		 try {
			 // fetch all for view delete/active reviews for support user
			 List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByVendorOrganisationId(vendorOrganisationId);
			 
			 List<Object> vendorAllReviews = new ArrayList();
			 
			 for (ProjectReviewRating vendorReviewsForProject : projectReviewsForVendors) {
				 ObjectMapper objMapper = new ObjectMapper();
				 Map<String, Object> mappedObj = objMapper.convertValue(vendorReviewsForProject, Map.class);
				 ClientUser clientUser = clientUserRepository.findByClientId(vendorReviewsForProject.getClientId());
				 mappedObj.put("clientName",clientUser.getFirstName()+" "+clientUser.getLastName());
				 
				 UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(clientUser.getClientId(), Constants.UserType.VENDOR.getValue());
			        if(userProfileImage != null) {
			        	mappedObj.put("clientUserProfileImage",userProfileImage.getFileUrl());
			        } else {
			        	mappedObj.put("clientUserProfileImage","");
			        }
			        
				 ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(vendorReviewsForProject.getClientOrganisationId());
				 if(clientOrganisation != null) {
					 mappedObj.put("clientOrganisationName",clientOrganisation.getOrganisationName());
					 mappedObj.put("clientManagementCompany",clientOrganisation.getManagementCompany());
				 }
				 VendorUser vendorUser = vendorUserRepository.findByUserId(vendorReviewsForProject.getVendorId());
				 if(vendorUser != null) {
					 mappedObj.put("vendorName",vendorUser.getFirstName() +" "+ vendorUser.getLastName());
				 } else {
					 mappedObj.put("vendorName","");
				 }
				 //not required Organisation Names for now UI perspective so not added
				 mappedObj.put("categoryRating",getDetailedRatingForReview(vendorReviewsForProject.getId()));
				 List<Map<String, Object>> vendorCategoryRatings = getDetailedRatingForReview(vendorReviewsForProject.getId());
					
					Float overAllRatingCalculation=0.0f;
					for(Map<String,Object> rating : vendorCategoryRatings) {
						
						VendorCategoryRatings vendorRating = new VendorCategoryRatings();
						
						Integer ratingCategory = (Integer) rating.get("ratingCategory");
						Float ratingValue = Float.valueOf(String.valueOf(rating.get("rating")));
						switch(ratingCategory) {
							case 1/*VendorRatingCategory.RESPONSIVENESS.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);
								break;
							}
							case 2/*VendorRatingCategory.PROFESSIONALISM.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);
								break;
							}
							case 3/*VendorRatingCategory.ACCURACY.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.ACCURACY.getValue()/100);
								break;
							}
							case 4/*VendorRatingCategory.QUALITY.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.QUALITY.getValue()/100);
								break;
							}
						}
					}
					mappedObj.put("rating", overAllRatingCalculation);
				 vendorAllReviews.add(mappedObj);
			 }
			 
			 return vendorAllReviews;
		 } catch(Exception exp){
			 exp.printStackTrace();
			 return null;
		 }
	}

	private List<Map<String,Object>> getDetailedRatingForReview(Integer reviewRatingId) {
		// TODO Auto-generated method stub4
		List<VendorCategoryRatings> vendorCategoryRatings = vendorCategoryRatingsRepository.findAllByReviewRatingId(reviewRatingId);
		
		List<Map<String,Object>> categoryRating = new ArrayList();
		
		for(VendorCategoryRatings vendorCategoryRating : vendorCategoryRatings){
			Map<String,Object> map = new HashMap();
			map.put("ratingCategory", vendorCategoryRating.getRatingCategory());
			map.put("rating", vendorCategoryRating.getRating());
			categoryRating.add(map);
		}
		return categoryRating;
	}

	public List<Map<String, Object>> GetAllReviewsForClient(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		return getAllClientReviews(clientOrganisationId);
	}

	public List<Map<String, Object>> getAllClientReviews(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		
		List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByClientOrganisationId(clientOrganisationId);
		
		List<Map<String, Object>> clientReviews = new ArrayList();
		
		for(ProjectReviewRating projectReviewRating : projectReviewsForVendors) {
			
			Integer vendorOrganisationId = projectReviewRating.getVendorOrganisationId();
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
			
			
			Map<String,Object> projectReview = new HashMap();
			
			// no need calculate overall rating, there is a input for overall rating , and we are gonna retrieve all category ratings to so we dont need calculation here, previouly added only because of UI has only one rating view
//			projectReview.put("rating", getVendorCategoryRatingsByClientOrgId(vendorOrganisationId, clientOrganisationId, projectReviewRating.getProjectId()));
//			if(clientId.equals(projectReviewRating.getClientId())) {
//				projectReview.put("isEditable", true);
//			} else {
//				projectReview.put("isEditable", false);
//			}
			projectReview.put("isEditable", true);//for support user, its editable
			
			projectReview.put("id", projectReviewRating.getId());
			projectReview.put("rating", projectReviewRating.getRating());
			System.out.println("rating:1->"+projectReviewRating.getRating());
			projectReview.put("reviewDate", projectReviewRating.getCreatedAt());
			projectReview.put("reviewComments", projectReviewRating.getReviewComments());
			projectReview.put("replyComments", projectReviewRating.getReplyComments());
			
			ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(clientOrganisationId);
			ClientUser clientUser = clientUserRepository.findByClientId(projectReviewRating.getClientId());
			projectReview.put("condoName", clientOrganisation.getOrganisationName());
			projectReview.put("condoCity", clientOrganisation.getCity());
			projectReview.put("clientUserFirstName", clientUser.getFirstName());
			projectReview.put("clientUserLastName", clientUser.getLastName());
			projectReview.put("vendorOrganisation", vendorOrganisation);
			
			List<Map<String, Object>> vendorCategoryRatings = getVendorCategoryRatingsForClientReview(projectReviewRating.getId());
			projectReview.put("vendorCategoryRating", vendorCategoryRatings);
			System.out.println("vendorCategoryRating:1->"+vendorCategoryRatings);
			Float overAllRatingCalculation=0.0f;
			for(Map<String,Object> rating : vendorCategoryRatings) {
				
				VendorCategoryRatings vendorRating = new VendorCategoryRatings();
				
				Integer ratingCategory = (Integer) rating.get("ratingCategory");
				Float ratingValue = Float.valueOf(String.valueOf(rating.get("rating")));
				
				switch(ratingCategory) {
					case 1/*VendorRatingCategory.RESPONSIVENESS.getValue()*/ :{
						overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);
						System.out.println("(ratingValue*VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);:1->"+(ratingValue*VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100));
						System.out.println("overAllRatingCalculation:1->"+overAllRatingCalculation);
						break;
					}
					case 2/*VendorRatingCategory.PROFESSIONALISM.getValue()*/ :{
						overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);
						System.out.println("(ratingValue*VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);:1->"+(ratingValue*VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100));
						System.out.println("overAllRatingCalculation:1->"+overAllRatingCalculation);
						break;
					}
					case 3/*VendorRatingCategory.ACCURACY.getValue()*/ :{
						overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.ACCURACY.getValue()/100);
						System.out.println("(ratingValue*VendorRatingCategoryPercentage.ACCURACY.getValue()/100);:1->"+(ratingValue*VendorRatingCategoryPercentage.ACCURACY.getValue()/100));
						System.out.println("overAllRatingCalculation:1->"+overAllRatingCalculation);
						break;
					}
					case 4/*VendorRatingCategory.QUALITY.getValue()*/ :{
						overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.QUALITY.getValue()/100);
						System.out.println("(ratingValue*VendorRatingCategoryPercentage.QUALITY.getValue()/100);:1->"+(ratingValue*VendorRatingCategoryPercentage.QUALITY.getValue()/100));
						System.out.println("overAllRatingCalculation:1->"+overAllRatingCalculation);
						break;
					}
				}
			}
			projectReview.put("rating", overAllRatingCalculation);
			System.out.println("rating:2->"+overAllRatingCalculation);
			Integer projectId = projectReviewRating.getProjectId();
			if(projectId != null &&  projectId > 0) {
				Project project = projectRepository.findByProjectId(projectReviewRating.getProjectId());
				projectReview.put("project", project);
			} else {
				projectReview.put("project", null);
			}
			
			clientReviews.add(projectReview);
		}
		
		return clientReviews;
	}
	
	private List<Map<String, Object>> getVendorCategoryRatingsForClientReview(Integer reviewRatingId) {
		// TODO Auto-generated method stub
		List<VendorCategoryRatings> vendorCategoryRatings = vendorCategoryRatingsRepository.findAllByReviewRatingId(reviewRatingId);
		
		List<Map<String, Object>> allCategoryRatings = new ArrayList();
		
		for (VendorCategoryRatings vendorCategoryRating :vendorCategoryRatings) {
			Map<String,Object> categoryRating = new HashMap();
			
			categoryRating.put("rating", vendorCategoryRating.getRating());
			categoryRating.put("ratingCategory", vendorCategoryRating.getRatingCategory());
			categoryRating.put("createdAt", vendorCategoryRating.getCreatedAt());
			allCategoryRatings.add(categoryRating);
		}
		
		return allCategoryRatings;
	}

}
