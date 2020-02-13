package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.PredefinedTags;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorTagsRepository;

@Component
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	PredefinedTagsRepository predefinedTagsRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	UserWishListRepository userWishListRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;
	@Autowired
	VendorTagsRepository vendorTagsRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	
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

	public List<Map<String, Object>> getSearchResults(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
		String actualKeyword = String.valueOf(requestData.get("keyword"));
		Integer clientUserId = Integer.parseInt(String.valueOf(requestData.get("clientUserId")));
		Integer clientOrganisationId = Integer.parseInt(String.valueOf(requestData.get("clientOrganisationId")));
		
		String keyword = "%"+actualKeyword+"%";
		
		List<Map<String, Object>> result = new ArrayList();
		
		switch(searchType) {
			case 1:
			case 2:{
				
				// check keyword has project tags				
				List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(actualKeyword);
				ArrayList<Project> tagContainedProjects = new ArrayList();
				if(tags != null && tags.size() > 0) {
					for(PredefinedTags tag : tags) {
						List<Project> projects = projectRepository.findAllByKeyword(clientOrganisationId, String.valueOf("%"+tag.getTagId()+"%"));
						tagContainedProjects.addAll(new ArrayList<>(projects));
					}
				}
				
				List<Project> projects = projectRepository.findAllByKeyword(clientOrganisationId, keyword);
				for(Project project : projects) {
					for (Iterator<Project> subProject = tagContainedProjects.iterator() ; subProject.hasNext() ; ) {
						Project pro = subProject.next();
						if(project.getProjectId() == pro.getProjectId()) {
							subProject.remove();
						}
					}
					result.add(getProjectObject(project));
				}
				
				if(tagContainedProjects != null && tagContainedProjects.size() > 0) {
					for(Project project : tagContainedProjects) {
						result.add(getProjectObject(project));
					}
				}
				return result;
			}
			
			case 3:{
				// check keyword has vendor tags				
				List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(actualKeyword);
				List<VendorOrganisation> tagContainedVendors = new ArrayList();
				
				List<Integer> vendorIds = new ArrayList();
				if(tags != null && tags.size() > 0) {
					List<Integer> tagIds = tags.stream().map(PredefinedTags::getTagId).collect(Collectors.toList());	
					
					vendorIds = vendorTagsRepository.findAllByTagId(tagIds);
				}
				
				if(vendorIds != null && vendorIds.size() > 0) {
					tagContainedVendors = vendorOrganisationRepository.findAllByVendorOrganisationId(vendorIds);
				} 
				
				List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAllByKeyword(keyword);
				
				for(VendorOrganisation vendorOrg : vendorOrgsAll) {
					for (Iterator<VendorOrganisation> subVendorOrg = tagContainedVendors.iterator() ; subVendorOrg.hasNext() ; ) {
						VendorOrganisation org = subVendorOrg.next();
						if(vendorOrg.getVendorOrganisationId() == org.getVendorOrganisationId()) {
							subVendorOrg.remove();
						}
					}
			        result.add(getVendorOrganisationObj(clientOrganisationId, vendorOrg));
				}
		    	
				if(tagContainedVendors != null && tagContainedVendors.size() > 0) {
					for(VendorOrganisation vendorOrg : tagContainedVendors) {
						result.add(getVendorOrganisationObj(clientOrganisationId, vendorOrg));
					}
				}
				
				return result;
			}
			case 4:{
				
				List<ClientOrganisation> clientOrganisations = clientOrganisationRepository.findAllByKeyword(keyword);
				for(ClientOrganisation clientOrganisation : clientOrganisations) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientOrganisation, Map.class);
			        result.add(map);
				}
				
		        
				return result;
			}
		}
		
		return null;
	}

	private Map<String, Object> getVendorOrganisationObj(Integer clientOrganisationId, VendorOrganisation vendorOrg) {
		// TODO Auto-generated method stub
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
        map.put("isPreferred", "false");
        
        List<UserWishList> userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(clientOrganisationId, Constants.UserType.CLIENT.getValue(), vendorOrg.getVendorOrganisationId(), Constants.UserType.VENDOR.getValue() );
        
        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
        } else {
        	map.put("vendorTags","");
        }
        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
        if(userWish != null) {
        	map.put("isPreferred", "true");
        }
        
        return map;
	}

	private Map<String, Object> getProjectObject(Project project) {
		// TODO Auto-generated method stub
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(project, Map.class);
//        Map<String, Object> map = new HashMap();
        
        
        List<Integer> ids = Stream.of(project.getTags().trim().split(","))
		        .map(Integer::parseInt)
		        .collect(Collectors.toList());
        
        map.put("tags",predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
        
		return map;
	}
	
	public String getVendorTags(List<VendorTags> vendorTags) {
		// TODO Auto-generated method stub
		try {
			
			List<Integer> ids = vendorTags.stream().map(VendorTags::getTagId).collect(Collectors.toList());	
			
	        String tags = predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(","));
	        
	        return tags;
			
		} catch(Exception exp){
			exp.printStackTrace();
			return null;
		}
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

}
