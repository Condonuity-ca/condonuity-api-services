package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.UserAccountStatus;
import tech.torbay.userservice.entity.OrganisationPayment;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.UserProfileImages;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorInsurance;
import tech.torbay.userservice.entity.VendorLicenses;
import tech.torbay.userservice.entity.VendorMemberships;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorOrganisationProfileImages;
import tech.torbay.userservice.entity.VendorPortfolio;
import tech.torbay.userservice.entity.VendorProducts;
import tech.torbay.userservice.entity.VendorServices;
import tech.torbay.userservice.entity.VendorServicesCities;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.UserProfileImagesRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorBrandsRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorInsuranceRepository;
import tech.torbay.userservice.repository.VendorLicensesRepository;
import tech.torbay.userservice.repository.VendorMembershipsRepository;
import tech.torbay.userservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorPortfolioRepository;
import tech.torbay.userservice.repository.VendorProductsRepository;
import tech.torbay.userservice.repository.VendorServicesCitiesRepository;
import tech.torbay.userservice.repository.VendorServicesRepository;
import tech.torbay.userservice.repository.VendorUserRepository;
import tech.torbay.userservice.entity.ClientUser;

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
	@Autowired
	VendorOrganisationProfileImagesRepository vendorOrganisationProfileImagesRepository;

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

	public Object getVendorUserById(Integer userId) {
		// TODO Auto-generated method stub
		VendorUser vendorUser = vendorUserRepository.findByUserId(userId);
		
		ObjectMapper oMapper = new ObjectMapper();
        // object -> Map
        Map<String, Object> map = oMapper.convertValue(vendorUser, Map.class);
        
        UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(vendorUser.getUserId(), Constants.UserType.VENDOR.getValue());
        
        map.put("profileImageURL",userProfileImage.getFileUrl());
//        map.put("",""); blobName
        
        return map;
	}

	public Object getVendorOrganisationById(Integer vendorOrganisationId) {
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
	
	public String getOrganisationLogo(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		VendorOrganisationProfileImages vendorOrgProfileImage =  vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(vendorOrganisationId);
		
        if(vendorOrgProfileImage != null)
        	return vendorOrgProfileImage.getFileUrl();
        else
        	return null;
	}

	private Object getVendorReviewsRatings(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		 try {
			 List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByVendorOrganisationId(vendorOrganisationId);
			 
			 List<Object> vendorAllReviews = new ArrayList();
			 
			 for (ProjectReviewRating vendorReviewsForProject : projectReviewsForVendors) {
				 ObjectMapper objMapper = new ObjectMapper();
				 Map<String, Object> mappedObj = objMapper.convertValue(vendorReviewsForProject, Map.class);
				 ClientUser clientUser = clientUserRepository.findByClientId(vendorReviewsForProject.getClientId());
				 mappedObj.put("clientName",clientUser.getFirstName()+" "+clientUser.getLastName());
				 VendorUser vendorUser = vendorUserRepository.findByUserId(vendorReviewsForProject.getVendorId());
				 if(vendorUser != null) {
					 mappedObj.put("vendorName",vendorUser.getFirstName() +" "+ vendorUser.getLastName());
				 } else {
					 mappedObj.put("vendorName","");
				 }
				 mappedObj.put("categoryRating",getDetailedRatingForReview(vendorReviewsForProject.getId()));
				 
				 vendorAllReviews.add(mappedObj);
			 }
			 
			 return vendorAllReviews;
		 } catch(Exception exp){
			 exp.printStackTrace();
			 return null;
		 }
	}

	private Object getDetailedRatingForReview(Integer reviewRatingId) {
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

	private Object getVendorDetailedRatings(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorCategoryRatings> vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationId(vendorOrganisationId);
		
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
	        try {
		        String logo = getOrganisationLogo(vendorOrg.getVendorOrganisationId());
		        if(logo != null)
		        	map.put("vendorProfileImageUrl", logo);
		        else
		        	map.put("vendorProfileImageUrl", "");
	        } catch(Exception exp) {
		        	exp.printStackTrace();
	        }
	        vendorOrganisations.add(map);
		}
		
		return vendorOrganisations;
	}

	public VendorUser updateVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		
		VendorUser vendorUserObj = vendorUserRepository.findByUserId(vendorUser.getUserId());
		
		vendorUserObj.setFirstName(vendorUser.getFirstName());
		vendorUserObj.setLastName(vendorUser.getLastName());
		return vendorUserRepository.save(vendorUserObj);
	}

	public Object updateVendorOrganisation(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		try {
			
			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
			VendorInsurance vendorInsurance = mapper.convertValue(vendorOrganisationData.get("insurance"), VendorInsurance.class);
			
			String serviceCities = String.valueOf(vendorOrganisationData.get("serviceCities")); // ids - 1,2,3
			String services = String.valueOf(vendorOrganisationData.get("services")); // string - abc,abc,acbc
			String licenses = String.valueOf(vendorOrganisationData.get("licenses")); // string - abc,abc,acbc
			String products = String.valueOf(vendorOrganisationData.get("products")); // string - abc,abc,acbc
			String brands = String.valueOf(vendorOrganisationData.get("brands")); // string - abc,abc,acbc
			String memberships = String.valueOf(vendorOrganisationData.get("memberships")); // string - abc,abc,acbc
			
			vendorOrganisation = vendorOrganisationRepository.save(vendorOrganisation);
			
			
			if(vendorOrganisation != null) {
				
				// remove old records
				// 1 delete service cities
				// 2 delete services
				// 3 delete licenses
				// 4 delete products
				// 5 delete brands
				// 6 delete memberships
				vendorServicesCitiesRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorServicesRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorProductsRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorBrandsRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorLicensesRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorMembershipsRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				
				// 0 add insurance
				// 1 add service cities
				// 2 add services
				// 3 add licenses
				// 4 add products
				// 5 add brands
				// 6 add memberships
				
				vendorInsurance.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorInsuranceRepository.save(vendorInsurance);
				
				//1
				
			    List<String> cities = Arrays.asList(serviceCities.split(","));
			    for(String city: cities) {
			    	vendorServicesCitiesRepository.save(new VendorServicesCities(vendorOrganisation.getVendorOrganisationId(),Integer.parseInt(city)));
			    }
			    
			    List<String> vservices = Arrays.asList(services.split(","));
			    for(String service: vservices) {
			    	vendorServicesRepository.save(new VendorServices(vendorOrganisation.getVendorOrganisationId(),service));
			    }
			    
			    List<String> vproducts = Arrays.asList(products.split(","));
			    for(String product: vproducts) {
			    	vendorProductsRepository.save(new VendorProducts(vendorOrganisation.getVendorOrganisationId(),product));
			    }
			    
			    List<String> vbrands = Arrays.asList(brands.split(","));
			    for(String brand: vbrands) {
			    	vendorBrandsRepository.save(new VendorBrands(vendorOrganisation.getVendorOrganisationId(),brand));
			    }
			    
			    List<String> vlicenses = Arrays.asList(licenses.split(","));
			    for(String license: vlicenses) {
			    	vendorLicensesRepository.save(new VendorLicenses(vendorOrganisation.getVendorOrganisationId(),license,"",""));
			    }
			    
			    List<String> vmemberships = Arrays.asList(memberships.split(","));
			    for(String membership: vmemberships) {
			    	vendorMembershipsRepository.save(new VendorMemberships(vendorOrganisation.getVendorOrganisationId(),membership,""));
			    }
			    
				return vendorOrganisation;
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
		
		return vendorOrganisationData;
	}

	public List<VendorUser> getVendorOrganisationUsersById(Integer id) {
		// TODO Auto-generated method stub
//		return vendorUserRepository.findByVendorOrganisationIdAndAccountStatus(id, Constants.UserAccountStatus.ACTIVE.getValue());
		
		
		
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
	        
	        List<UserWishList> userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(clientOrgId, Constants.UserType.CLIENT.getValue(), vendorOrg.getVendorOrganisationId(), Constants.UserType.VENDOR.getValue() );
	        
	        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
	        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
	        } else {
	        	map.put("vendorTags","");
	        }
	        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
	        if(userWish != null) {
	        	map.put("isPreferred", "true");
	        }
	        try {
		        String logo = getOrganisationLogo(vendorOrg.getVendorOrganisationId());
		        if(logo != null)
		        	map.put("vendorProfileImageUrl", logo);
		        else
		        	map.put("vendorProfileImageUrl", "");
	        } catch(Exception exp) {
		        	exp.printStackTrace();
	        }
	        vendorOrganisations.add(map);
		}
    			
		return vendorOrganisations;
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
	
	public List<OrganisationPayment> getPaymentBillingDetails(Integer orgId) {
		// TODO Auto-generated method stub
		List paymentDetails = new ArrayList();
		paymentDetails.add(new OrganisationPayment());
		return paymentDetails;
	}

	public VendorUser deleteVendorUserById(Integer id) {
		// TODO Auto-generated method stub
		VendorUser vendorUser = vendorUserRepository.findByUserId(id);
		vendorUser.setAccountStatus(UserAccountStatus.INACTIVE.getValue());
		return vendorUserRepository.save(vendorUser);
	}

	public Object updateVendorUserRole(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer vendorUserId = Integer.parseInt(String.valueOf(requestData.get("userId")));
    	Integer userRole = Integer.parseInt(String.valueOf(requestData.get("userRole")));
    	String firstName = String.valueOf(requestData.get("firstName"));
    	String lastName = String.valueOf(requestData.get("lastName"));
    	
    	VendorUser vendorUser = vendorUserRepository.findByUserId(vendorUserId);
    	
    	vendorUser.setUserRole(userRole);
    	vendorUser.setFirstName(firstName);
    	vendorUser.setLastName(lastName);;
    	
		return vendorUserRepository.save(vendorUser);
	}

	public Object updateVendorOrganisationCompany(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		try {
			Integer vendorOrganisationId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("vendorOrganisationId"))); 
			String companyName = String.valueOf(vendorOrganisationData.get("companyName")); 
			String description = String.valueOf(vendorOrganisationData.get("description")); 
			String serviceCities = String.valueOf(vendorOrganisationData.get("serviceCities")); 
			String tags = String.valueOf(vendorOrganisationData.get("tags"));
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);

			List<String> tagsList = Arrays.asList(tags.split(","));
			List<VendorTags> vendorTags = new ArrayList();
			for(String tag: tagsList) {
				VendorTags tagId = new VendorTags();
				tagId.setTagId(Integer.parseInt(tag));
				vendorTags.add(tagId);
				
				System.out.print(vendorTags);
		    }
			vendorOrganisation.setCompanyName(companyName);
			vendorOrganisation.setDescription(description);
			vendorOrganisation.setVendorTags(vendorTags);
			
			vendorOrganisationRepository.save(vendorOrganisation);
			
			try {
				vendorServicesCitiesRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				List<String> cities = Arrays.asList(serviceCities.split(","));
			    for(String city: cities) {
			    	vendorServicesCitiesRepository.save(new VendorServicesCities(vendorOrganisationId,Integer.parseInt(city)));
			    }
			} catch(Exception exp) {
				exp.printStackTrace();
			}
		    
		    return vendorOrganisation;
		    
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	public Object updateVendorOrganisationCompanySale(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		try {
			Integer vendorOrganisationId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("vendorOrganisationId"))); 
			String employeesCount = String.valueOf(vendorOrganisationData.get("employeesCount")); 
			String establishedDate = String.valueOf(vendorOrganisationData.get("establishedDate")); 
			String annualRevenue = String.valueOf(vendorOrganisationData.get("annualRevenue")); 
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);

			vendorOrganisation.setEmployeesCount(Integer.parseInt(employeesCount));
			vendorOrganisation.setEstablishedDate(establishedDate);
			vendorOrganisation.setAnnualRevenue(annualRevenue);
			
			vendorOrganisationRepository.save(vendorOrganisation);
			
		    return vendorOrganisation;
		    
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public Object updateVendorOrganisationCompanyContact(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		try {
			Integer vendorOrganisationId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("vendorOrganisationId"))); 
			String legalName = String.valueOf(vendorOrganisationData.get("legalName")); 
			String address = String.valueOf(vendorOrganisationData.get("address")); 
			String province = String.valueOf(vendorOrganisationData.get("province")); 
			String city = String.valueOf(vendorOrganisationData.get("city")); 
			String postalCode = String.valueOf(vendorOrganisationData.get("postalCode")); 
			String phoneNumber = String.valueOf(vendorOrganisationData.get("phoneNumber")); 
			String email = String.valueOf(vendorOrganisationData.get("email")); 
			String faxNumber = String.valueOf(vendorOrganisationData.get("faxNumber")); 
			String website = String.valueOf(vendorOrganisationData.get("website")); 
			String contactPerson = String.valueOf(vendorOrganisationData.get("contactPerson")); 
			String contactPersonPhone = String.valueOf(vendorOrganisationData.get("contactPersonPhone")); 
			String contactPersonEmail = String.valueOf(vendorOrganisationData.get("contactPersonEmail")); 
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);

			vendorOrganisation.setLegalName(legalName);
			vendorOrganisation.setAddress(address);
			vendorOrganisation.setProvince(province);
			vendorOrganisation.setCity(city);
			vendorOrganisation.setPostalCode(postalCode);
			vendorOrganisation.setPhoneNumber(phoneNumber);
			vendorOrganisation.setEmail(email);
			vendorOrganisation.setFaxNumber(faxNumber);
			vendorOrganisation.setWebsite(website);
			vendorOrganisation.setContactPerson(contactPerson);
			vendorOrganisation.setContactPersonPhone(contactPersonPhone);
			vendorOrganisation.setContactPersonEmail(contactPersonEmail);
			
			vendorOrganisationRepository.save(vendorOrganisation);
			
		    return vendorOrganisation;
		    
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	public Object updateVendorInsuranceAndAllServices(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		try {
			Integer vendorOrganisationId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("vendorOrganisationId"))); 
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);

			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			VendorInsurance vendorInsurance = mapper.convertValue(vendorOrganisationData.get("insurance"), VendorInsurance.class);
			
			String services = String.valueOf(vendorOrganisationData.get("services")); // string - abc,abc,acbc
			String licenses = String.valueOf(vendorOrganisationData.get("licenses")); // string - abc,abc,acbc
			String products = String.valueOf(vendorOrganisationData.get("products")); // string - abc,abc,acbc
			String brands = String.valueOf(vendorOrganisationData.get("brands")); // string - abc,abc,acbc
			String memberships = String.valueOf(vendorOrganisationData.get("memberships")); // string - abc,abc,acbc
			
			if(vendorOrganisation != null) {
				
				// remove old records
				// 1 delete service cities -NA
				// 2 delete services
				// 3 delete licenses
				// 4 delete products
				// 5 delete brands
				// 6 delete memberships
				vendorServicesRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorProductsRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorBrandsRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorLicensesRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorMembershipsRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				
				// 0 add insurance
				// 1 add service cities - NA
				// 2 add services
				// 3 add licenses
				// 4 add products
				// 5 add brands
				// 6 add memberships
				
				vendorInsurance.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				vendorInsuranceRepository.save(vendorInsurance);
				
				//1
				
//			    List<String> cities = Arrays.asList(serviceCities.split(","));
//			    for(String city: cities) {
//			    	vendorServicesCitiesRepository.save(new VendorServicesCities(vendorOrganisation.getVendorOrganisationId(),Integer.parseInt(city)));
//			    }
			    
			    List<String> vservices = Arrays.asList(services.split(","));
			    for(String service: vservices) {
			    	vendorServicesRepository.save(new VendorServices(vendorOrganisation.getVendorOrganisationId(),service));
			    }
			    
			    List<String> vproducts = Arrays.asList(products.split(","));
			    for(String product: vproducts) {
			    	vendorProductsRepository.save(new VendorProducts(vendorOrganisation.getVendorOrganisationId(),product));
			    }
			    
			    List<String> vbrands = Arrays.asList(brands.split(","));
			    for(String brand: vbrands) {
			    	vendorBrandsRepository.save(new VendorBrands(vendorOrganisation.getVendorOrganisationId(),brand));
			    }
			    
			    List<String> vlicenses = Arrays.asList(licenses.split(","));
			    for(String license: vlicenses) {
			    	vendorLicensesRepository.save(new VendorLicenses(vendorOrganisation.getVendorOrganisationId(),license,"",""));
			    }
			    
			    List<String> vmemberships = Arrays.asList(memberships.split(","));
			    for(String membership: vmemberships) {
			    	vendorMembershipsRepository.save(new VendorMemberships(vendorOrganisation.getVendorOrganisationId(),membership,""));
			    }
			    
				return vendorOrganisation;
			} else {
				return null;
			}

		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
}

