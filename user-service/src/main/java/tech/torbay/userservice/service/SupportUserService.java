package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.userservice.repository.CommentFilesRepository;
import tech.torbay.userservice.repository.ExternalMessageCommentRepository;
import tech.torbay.userservice.repository.ExternalMessageRepository;
import tech.torbay.userservice.repository.InternalMessageCommentRepository;
import tech.torbay.userservice.repository.InternalMessageRepository;
import tech.torbay.userservice.repository.ThreadFilesRepository;
import tech.torbay.userservice.entity.ExternalMessage;
import tech.torbay.userservice.entity.ExternalMessageComment;
import tech.torbay.userservice.repository.VendorBidRepository;
import tech.torbay.userservice.repository.VendorProjectInterestsRepository;
import tech.torbay.userservice.Utils.Utils;
import tech.torbay.userservice.constants.Constants.DeleteStatus;
import tech.torbay.userservice.constants.Constants.ThreadType;
import tech.torbay.userservice.constants.Constants.UserType;
import tech.torbay.userservice.entity.CommentFiles;
import tech.torbay.userservice.entity.InternalMessage;
import tech.torbay.userservice.entity.InternalMessageComment;
import tech.torbay.userservice.entity.ThreadFiles;
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.entity.Amenities;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationProfileImages;
import tech.torbay.userservice.entity.ClientTask;
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientUserTasks;
import tech.torbay.userservice.entity.PredefinedTags;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.ServiceCities;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorOrganisationProfileImages;
import tech.torbay.userservice.entity.VendorProjectInterests;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.AmenitiesRepository;
import tech.torbay.userservice.repository.ClientAmenitiesRepository;
import tech.torbay.userservice.repository.ClientAssociationRepository;
import tech.torbay.userservice.repository.ClientBuildingRepoRepository;
import tech.torbay.userservice.repository.ClientContractRepository;
import tech.torbay.userservice.repository.ClientOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientTaskCommentsRepository;
import tech.torbay.userservice.repository.ClientTaskRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.ClientUserTasksRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.ServiceCitiesRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
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
	
	public boolean updateOrganisationActivationStatus(Integer organisationId, Integer userType, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		
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
			if(ClientOrganisationObj != null) {
				clientAssociationRepository.setDeleteStatusByClientOrganisationId(activeStatus, organisationId);
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
				
				vendorUserRepository.setDeleteStatusByVendorOrganisationId(activeStatus, organisationId);
				return true;
			}
		}
		return false;
	}


	public boolean updateUserActivationStatus(Integer userId, Integer organisationId, Integer userType,
			Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		if(userType == UserType.CLIENT.getValue()) {
			int isUpdated = clientAssociationRepository.setDeleteStatusByClientIdAndClientOrganisationId(activeStatus, userId, organisationId);
						
			if(isUpdated > 0) {
				return true;
			}
		} else if(userType == UserType.VENDOR.getValue()) {
			int isUpdated = vendorUserRepository.setDeleteStatusByVendorIdAndVendorOrganisationId(activeStatus, userId, organisationId);
			
			if(isUpdated > 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean updateReviewActivationStatus(Integer reviewRatingId, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = projectReviewRatingRepository.setStatusById(activeStatus, reviewRatingId);
						
		if(isUpdated > 0) {
			return true;
		}
		return false;
	}


	public boolean updateClientCorporationInformation(Integer clientOrganisationId, String corporationName, String corporationNumber,
			Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = clientOrganisationRepository.setOrganisationNameAndCorporateNumberByClientOrganisationId(corporationName, corporationNumber, clientOrganisationId);
		
		if(isUpdated > 0) {
			return true;
		}
		return false;
	}


	public boolean updateProjectActivationStatus(Integer projectId, Integer deleteStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = projectRepository.setDeleteStatusByProjectId(deleteStatus, projectId);
		
		if(isUpdated > 0) {
			return true;
		}
		return false;
	}


	public boolean updateExternalMessageActivationStatus(Integer externalMessageId, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = externalMessageRepository.setDeleteStatusById(activeStatus, externalMessageId);
		
		if(isUpdated > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateExternalMessageCommentActivationStatus(Integer externalMessageCommentId, Integer activeStatus, Integer supportUserId) {
		// TODO Auto-generated method stub
		int isUpdated = externalMessageCommentRepository.setDeleteStatusById(activeStatus, externalMessageCommentId);
		
		if(isUpdated > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUserProfile(Integer userId, Integer organisationId, Integer userType,
			String firstName, String lastName, Integer userRole, Integer clientUserType, Integer supportUserId) {
		// TODO Auto-generated method stub
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
		List<VendorCategoryRatings> vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationId(vendorOrgId);
		
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
}
