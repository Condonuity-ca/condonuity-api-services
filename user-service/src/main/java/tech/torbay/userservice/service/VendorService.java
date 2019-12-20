package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorInsurance;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorPortfolio;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorInsuranceRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorPortfolioRepository;
import tech.torbay.userservice.repository.VendorUserRepository;

@Component
public class VendorService {
	
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	VendorPortfolioRepository vendorPortfolioRepository;
	@Autowired
	VendorInsuranceRepository vendorInsuranceRepository;
	@Autowired
	UserWishListRepository userWishListRepository;
	@Autowired
	PredefinedTagsRepository predefinedTagsRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;
	@Autowired
	ProjectReviewRatingRepository projectReviewRatingRepository;
	@Autowired
	ClientUserRepository clientUserRepository;

	public List<VendorUser> findAllVendorUsers() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(vendorUserRepository.findAll());
	}
	
	public VendorUser findVendorUserByEmail(String email) {
		// TODO Auto-generated method stub
		VendorUser client = vendorUserRepository.findByEmail(email);
		return client;
	}

	public VendorUser addVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		return vendorUserRepository.save(vendorUser);
	}

	public VendorOrganisation addVendorOrgnisation(VendorOrganisation vendorOrganisation) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.save(vendorOrganisation);
	}

	public VendorUser getVendorUserById(Integer userId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findByUserId(userId);
	}

	public Object getVendorOrganisationById(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		try {
			VendorOrganisation vendorOrg = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
			
			
			ObjectMapper objMapper = new ObjectMapper();
	        Map<String, Object> mappedObj = objMapper.convertValue(vendorOrg, Map.class);
	        
	        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
	        	mappedObj.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
	        } else {
	        	mappedObj.put("vendorTags","");
	        }
	        mappedObj.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
	        mappedObj.put("detailedRating",getVendorDetailedRatings(vendorOrg.getVendorOrganisationId()));
	        mappedObj.put("reviewsRatings",getVendorReviewsRatings(vendorOrg.getVendorOrganisationId()));
	        
			return mappedObj;
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null/* vendorOrganisationRepository.findByVendorOrganisationId(id) */;
	}

	private Object getVendorReviewsRatings(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		 try {
			 List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByVendorOrganisationId(vendorOrganisationId);
			 
			 List<Object> vendorAllReviews = new ArrayList();
			 
			 for (ProjectReviewRating vendorReviewsForProject : projectReviewsForVendors) {
				 ObjectMapper objMapper = new ObjectMapper();
				 Map<String, Object> mappedObj = objMapper.convertValue(vendorReviewsForProject, Map.class);
				 
				 mappedObj.put("clientName",clientUserRepository.findByClientId(vendorReviewsForProject.getClientId()).getLegalName());
				 mappedObj.put("vendorName",vendorUserRepository.findByUserId(vendorReviewsForProject.getVendorId()).getLegalName());
				 
				 vendorAllReviews.add(mappedObj);
			 }
			 
			 return vendorAllReviews;
		 } catch(Exception exp){
			 exp.printStackTrace();
			 return null;
		 }
	}

	private Object getVendorDetailedRatings(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorCategoryRatings> vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationId(vendorOrganisationId);
		
        try {
        	double sumCategory1 = vendorRatings.stream().filter(o -> o.getRatingCategory() == 1).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategory2 = vendorRatings.stream().filter(o -> o.getRatingCategory() == 2).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategory3 = vendorRatings.stream().filter(o -> o.getRatingCategory() == 3).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategory4 = vendorRatings.stream().filter(o -> o.getRatingCategory() == 4).mapToDouble(VendorCategoryRatings::getRating).sum();
	        
        	Map<String, Object> mappedObj = new HashMap<String, Object>();
        	
        	mappedObj.put("onTimeDelivery", sumCategory1);
        	mappedObj.put("attitude", sumCategory2);
        	mappedObj.put("maintanance", sumCategory3);
        	mappedObj.put("perfection", sumCategory4);
        	
        	return mappedObj;
	        
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return null;
        }
        
	}

	private Double getVendorCategoryRatings(Integer vendorOrgId) {
		// TODO Auto-generated method stub
		List<VendorCategoryRatings> vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationId(vendorOrgId);
		
        try {
        	double sum = vendorRatings.stream().filter(o -> o.getRating() > 0).mapToDouble(VendorCategoryRatings::getRating).sum();
	        
	        if(sum >0) {
	        	double rating = sum/vendorRatings.size();
		       
	        	return rating; 
	        }
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return 0d;
        }
        
        
        return 0d;
        
	}

	public List<Object> getAllVendorOrganisations() {
		// TODO Auto-generated method stub
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAll();
		
		List<Object> vendorOrganisations = new ArrayList();
		
		for(VendorOrganisation vendorOrg : vendorOrgsAll) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
	        
	        
	        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
	        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
	        } else {
	        	map.put("vendorTags","");
	        }
	        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
	        vendorOrganisations.add(map);
		}
		
		return vendorOrganisations;
	}

	public VendorUser updateVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		return vendorUserRepository.save(vendorUser);
	}

	public VendorOrganisation updateVendorOrganisation(VendorOrganisation vendorOrganisation) {
		// TODO Auto-generated method stub
		return vendorOrganisationRepository.save(vendorOrganisation);
	}

	public List<VendorUser> getVendorOrganisationUsersById(Integer id) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findByVendorOrganisationId(id);
	}

	public List<VendorPortfolio> getVendorPortfolio(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return vendorPortfolioRepository.findByVendorOrganisationId(vendorOrganisationId);
	}

	public VendorPortfolio addVendorPortfolio(VendorPortfolio vendorPortfolio) {
		// TODO Auto-generated method stub
		return vendorPortfolioRepository.save(vendorPortfolio);
	}
	
	public VendorPortfolio updateVendorPortfolio(VendorPortfolio vendorPortfolio) {
		// TODO Auto-generated method stub
		return vendorPortfolioRepository.save(vendorPortfolio);
	}
	
	public List<VendorInsurance> getVendorInsurance(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return vendorInsuranceRepository.findByVendorOrganisationId(vendorOrganisationId);
	}

	public VendorInsurance addVendorInsurance(VendorInsurance vendorPortfolio) {
		// TODO Auto-generated method stub
		return vendorInsuranceRepository.save(vendorPortfolio);
	}
	
	public VendorInsurance updateVendorInsurance(VendorInsurance vendorPortfolio) {
		// TODO Auto-generated method stub
		return vendorInsuranceRepository.save(vendorPortfolio);
	}

	public List<VendorPortfolio> sortVendorPortfolio(Integer orgId, Integer sortBy) {
		// TODO Auto-generated method stub
		
		switch(sortBy) {
			case 1: {//Constants.PortfolioSortBy.ASC
				return vendorPortfolioRepository.findByVendorOrganisationIdOrderByProjectNameAsc(orgId);
			}
			case 2: {//Constants.PortfolioSortBy.DESC
				return vendorPortfolioRepository.findByVendorOrganisationIdOrderByProjectNameDesc(orgId);
			}
			case 3: {//Constants.PortfolioSortBy.DATE
				return vendorPortfolioRepository.findByVendorOrganisationIdOrderByCreatedAtAsc(orgId);
			}
			case 4: {//Constants.PortfolioSortBy.COST
				return vendorPortfolioRepository.findByVendorOrganisationIdOrderByCostAsc(orgId);
			}
			case 5: {//Constants.PortfolioSortBy.DURATION
				return vendorPortfolioRepository.findByVendorOrganisationIdOrderByDurationAsc(orgId); // check - ?
			}
			
		}
		
		return null;
	}

	public List<VendorOrganisation> sortVendorOrganisations(Integer userId, Integer userType, Integer sortBy) {
		// TODO Auto-generated method stub
		switch(sortBy) {
		case 1: {//Constants.VendorSortBy.ASC
//			return vendorOrganisationRepository.findAllOrderByCompanyNameAsc();
		}
		case 2: {//Constants.VendorSortBy.DESC
//			return vendorOrganisationRepository.findAllOrderByCompanyNameDesc();
		}
		case 3: {//Constants.VendorSortBy.NEAREST
//			return vendorOrganisationRepository.findByAllByLocationAsc(orgId);
		}
		case 4: {//Constants.VendorSortBy.HIGHEST_RATING
//			return vendorOrganisationRepository.findByAllOrderByRatingAsc(orgId);
		}
		case 5: {//Constants.VendorSortBy.PREFERRED
//			return vendorOrganisationRepository.findByAllOrderByUserIdAndUserType(orgId); // check - ?
		}
	}
		return null;
	}

	public List<Object> getAllVendorOrganisationsByClientOrgId(Integer clientOrgId) {
		// TODO Auto-generated method stub
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAll();
		
		List<Object> vendorOrganisations = new ArrayList();
		
		for(VendorOrganisation vendorOrg : vendorOrgsAll) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
	        map.put("isPreferred", "false");
	        
	        UserWishList userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(clientOrgId, Constants.UserType.CLIENT.getValue(), vendorOrg.getVendorOrganisationId(), Constants.UserType.VENDOR.getValue() );
	        
	        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
	        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
	        } else {
	        	map.put("vendorTags","");
	        }
	        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
	        if(userWish != null) {
	        	map.put("isPreferred", "true");
	        }
	        vendorOrganisations.add(map);
		}
		
    			
		return vendorOrganisations;
	}

	public UserWishList addClientAsFavourite(UserWishList userWishList) {
		// TODO Auto-generated method stub
		
		userWishList.setWisherUserType(Constants.UserType.VENDOR.getValue());
		userWishList.setFavouriteUserType(Constants.UserType.CLIENT.getValue());
		
		return userWishListRepository.save(userWishList);
	}

	public String getVendorTags(List<VendorTags> vendorTags) {
		// TODO Auto-generated method stub
		try {
			
			List<Integer> ids = vendorTags.stream().map(VendorTags::getId).collect(Collectors.toList());	
			
	        String tags = predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(","));
	        
	        return tags;
			
		} catch(Exception exp){
			exp.printStackTrace();
			return null;
		}
	}

}

