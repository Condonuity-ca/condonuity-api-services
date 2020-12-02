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

import tech.torbay.userservice.Utils.Utils;
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.DeleteStatus;
import tech.torbay.userservice.constants.Constants.Invalid;
import tech.torbay.userservice.constants.Constants.NotificationType;
import tech.torbay.userservice.constants.Constants.ProjectInterestStatus;
import tech.torbay.userservice.constants.Constants.UserAccountStatus;
import tech.torbay.userservice.constants.Constants.UserType;
import tech.torbay.userservice.constants.Constants.VendorRatingCategoryPercentage;
import tech.torbay.userservice.entity.AvailableVendorProfiles;
import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.Notification;
import tech.torbay.userservice.entity.OrganisationPayment;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.RegistrationLogs;
import tech.torbay.userservice.entity.ServiceCities;
import tech.torbay.userservice.entity.User;
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
import tech.torbay.userservice.entity.VendorPortfolioFiles;
import tech.torbay.userservice.entity.VendorProducts;
import tech.torbay.userservice.entity.VendorRegistrationFiles;
import tech.torbay.userservice.entity.VendorServices;
import tech.torbay.userservice.entity.VendorServicesCities;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.repository.AvailableVendorProfilesRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.NotificationRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.RegistrationLogsRepository;
import tech.torbay.userservice.repository.ServiceCitiesRepository;
import tech.torbay.userservice.repository.UserProfileImagesRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorBrandsRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorInsuranceRepository;
import tech.torbay.userservice.repository.VendorLicensesRepository;
import tech.torbay.userservice.repository.VendorMembershipsRepository;
import tech.torbay.userservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorPortfolioFilesRepository;
import tech.torbay.userservice.repository.VendorPortfolioRepository;
import tech.torbay.userservice.repository.VendorProductsRepository;
import tech.torbay.userservice.repository.VendorRegistrationFilesRepository;
import tech.torbay.userservice.repository.VendorServicesCitiesRepository;
import tech.torbay.userservice.repository.VendorServicesRepository;
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
	@Autowired
	ServiceCitiesRepository servicesCitiesRepository;
	@Autowired
	VendorRegistrationFilesRepository vendorRegistrationFilesRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AvailableVendorProfilesRepository availableVendorProfilesRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	VendorPortfolioFilesRepository vendorPortfolioFilesRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	RegistrationLogsRepository registrationLogsRepository;

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
		vendorOrganisation.setExpertiseCategory("");
		vendorOrganisation.setCountryCode("");
		return vendorOrganisationRepository.save(vendorOrganisation);
	}

	public Object getVendorUserById(Integer userId) {
		// TODO Auto-generated method stub
		VendorUser vendorUser = vendorUserRepository.findByUserId(userId);
		
		ObjectMapper oMapper = new ObjectMapper();
        // object -> Map
        Map<String, Object> map = oMapper.convertValue(vendorUser, Map.class);
        
        map.put("profileImageURL",getUserProfileImageURL(userId));
//        map.put("",""); blobName
        
        return map;
	}
	
	private String getUserProfileImageURL(Integer vendorId) {
		// TODO Auto-generated method stub
		UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(vendorId, Constants.UserType.VENDOR.getValue());
        
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
	
	public VendorOrganisation getVendorOrgById (Integer vendorOrganisationId) {
		return vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
	}

	public VendorUser findByEmail(String email) {
		// TODO Auto-generated method stub
		VendorUser vendor = vendorUserRepository.findByEmail(email);
		return vendor;
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
			 List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByVendorOrganisationIdAndStatus(vendorOrganisationId, DeleteStatus.ACTIVE.getValue());
			 
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
						System.out.println("rating 1 "+ ratingValue);
						switch(ratingCategory) {
							case 1/*VendorRatingCategory.RESPONSIVENESS.getValue()*/ :{
//								System.out.println("overAllRatingCalculation1/1 "+ overAllRatingCalculation);
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);
//								System.out.println("overAllRatingCalculation1/2 "+ overAllRatingCalculation);
								break;
							}
							case 2/*VendorRatingCategory.PROFESSIONALISM.getValue()*/ :{
//								System.out.println("overAllRatingCalculation2/1 "+ overAllRatingCalculation);
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);
//								System.out.println("overAllRatingCalculation2/2 "+ overAllRatingCalculation);
								break;
							}
							case 3/*VendorRatingCategory.ACCURACY.getValue()*/ :{
//								System.out.println("overAllRatingCalculation3/1 "+ overAllRatingCalculation);
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.ACCURACY.getValue()/100);
//								System.out.println("overAllRatingCalculation3/2 "+ overAllRatingCalculation);
								break;
							}
							case 4/*VendorRatingCategory.QUALITY.getValue()*/ :{
//								System.out.println("overAllRatingCalculation4/1 "+ overAllRatingCalculation);
								Float categoryRatingValue = (ratingValue*VendorRatingCategoryPercentage.QUALITY.getValue()/100);
								overAllRatingCalculation = overAllRatingCalculation + categoryRatingValue;
//								System.out.println("categoryRatingValue4 "+ categoryRatingValue);
//								System.out.println("overAllRatingCalculation4/2 "+ overAllRatingCalculation);
								break;
							}
						}
					}
//					System.out.println("overAllRatingCalculation Final "+ overAllRatingCalculation);
					String rating = String.format("%.4f", overAllRatingCalculation);
//					String rating1 = String.format("%.2f", overAllRatingCalculation);
//					System.out.println("rating Final "+ rating);
//					System.out.println("rating1 Final "+ rating1);
					mappedObj.put("rating", rating);
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
        	
        	
        	double overAllRating = ((sumCategoryResponsiveness/responsivenessCount )* Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100) +
        			((sumCategoryProfessionalism/responsivenessCount) * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100) +
        			((sumCategoryAccuracy/accuracyCount) * Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100) +
        			((sumCategoryQuality/qualityCount) * Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
        	
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
	
	public List<VendorUser> getAllVendorUsersInOrganisation(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		return vendorUserRepository.findAllByVendorOrganisationId(vendorOrganisationId);
	}
	
	public List<Object> getAllActiveVendorOrganisations() {
		// TODO Auto-generated method stub
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAllByActiveStatus(UserAccountStatus.ACTIVE.getValue());
		
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
	        List<VendorServicesCities> vendorServicesCities = vendorServicesCitiesRepository.findByVendorOrganisationId(vendorOrg.getVendorOrganisationId());
	        List<String> servicesCities = new ArrayList();
	        for(VendorServicesCities vendorCity : vendorServicesCities) {
	        	ServiceCities serviceCity = servicesCitiesRepository.findOneById(vendorCity.getServiceCityId());
	        	servicesCities.add(serviceCity.getCityName());
	        }
	        map.put("serviceCities",String.join(",", servicesCities));
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
	
	public List<Object> getAllVendorOrganisationsForSupportUser() {
		// TODO Auto-generated method stub
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAllActiveInActiveOrganisations();
		
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
	        List<VendorServicesCities> vendorServicesCities = vendorServicesCitiesRepository.findByVendorOrganisationId(vendorOrg.getVendorOrganisationId());
	        List<String> servicesCities = new ArrayList();
	        for(VendorServicesCities vendorCity : vendorServicesCities) {
	        	ServiceCities serviceCity = servicesCitiesRepository.findOneById(vendorCity.getServiceCityId());
	        	servicesCities.add(serviceCity.getCityName());
	        }
	        map.put("serviceCities",String.join(",", servicesCities));
	        int activeStatus = vendorOrg.getActiveStatus();
	        int deleteStatus = vendorOrg.getDeleteStatus();
	        if( deleteStatus == UserAccountStatus.ACTIVE.getValue()){
	        	 if(activeStatus == UserAccountStatus.INVITED.getValue()) {
	 	        	map.put("accountStatus","Registered");
	 	        } else if(activeStatus == UserAccountStatus.ACTIVE.getValue()){
	 	        	map.put("accountStatus","Active");
	 	        } else if(activeStatus == UserAccountStatus.INACTIVE.getValue()){
	 	        	map.put("accountStatus","Rejected");
	 	        } 
	        } else if ( deleteStatus == UserAccountStatus.INACTIVE.getValue() || activeStatus == UserAccountStatus.INACTIVE.getValue()) {
	        	map.put("accountStatus","Deleted");
	        } else {
	        	map.put("accountStatus","Deleted");
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

	//search by vendor company name 
	public List<Object> getAllVendorOrganisationsForSupportUser(Map<String, Object> requestData) {
		
		int searchType = 0;
		String keyword = "";
		try {
			searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
			keyword = String.valueOf(requestData.get("keyword"));
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		String searchKeyword = "%"+keyword+"%";
		
		List<VendorOrganisation> vendorOrgsAll = new ArrayList<>();
		if(searchType == 1) {
			// TODO Auto-generated method stub
			vendorOrgsAll = vendorOrganisationRepository.findAllActiveInActiveOrganisationsByOrganisationName(searchKeyword);			
		}
		
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
	        List<VendorServicesCities> vendorServicesCities = vendorServicesCitiesRepository.findByVendorOrganisationId(vendorOrg.getVendorOrganisationId());
	        List<String> servicesCities = new ArrayList();
	        for(VendorServicesCities vendorCity : vendorServicesCities) {
	        	ServiceCities serviceCity = servicesCitiesRepository.findOneById(vendorCity.getServiceCityId());
	        	servicesCities.add(serviceCity.getCityName());
	        }
	        map.put("serviceCities",String.join(",", servicesCities));
	        int activeStatus = vendorOrg.getActiveStatus();
	        int deleteStatus = vendorOrg.getDeleteStatus();
	        if( deleteStatus == UserAccountStatus.ACTIVE.getValue()){
	        	 if(activeStatus == UserAccountStatus.INVITED.getValue()) {
	 	        	map.put("accountStatus","Registered");
	 	        } else if(activeStatus == UserAccountStatus.ACTIVE.getValue()){
	 	        	map.put("accountStatus","Active");
	 	        } 
	        } else if ( deleteStatus == UserAccountStatus.INACTIVE.getValue() || activeStatus == UserAccountStatus.INACTIVE.getValue()) {
	        	map.put("accountStatus","Deleted");
	        } else {
	        	map.put("accountStatus","Deleted");
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

	public List<Object> getAllUnApproveRejectVendorOrganisationsForSupportUser(Map<String, Object> requestData) {
		
		int searchType = 0;
		String keyword = "";
		try {
			searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
			keyword = String.valueOf(requestData.get("keyword"));
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		String searchKeyword = "%"+keyword+"%";
		
		List<VendorOrganisation> vendorOrgsAll = new ArrayList<>();
		if(searchType == 1) {
			// TODO Auto-generated method stub
			vendorOrgsAll = vendorOrganisationRepository.findAllUnApproveRejectOrganisationsByOrganisationName(searchKeyword);			
		}
		
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
	        List<VendorServicesCities> vendorServicesCities = vendorServicesCitiesRepository.findByVendorOrganisationId(vendorOrg.getVendorOrganisationId());
	        List<String> servicesCities = new ArrayList();
	        for(VendorServicesCities vendorCity : vendorServicesCities) {
	        	ServiceCities serviceCity = servicesCitiesRepository.findOneById(vendorCity.getServiceCityId());
	        	servicesCities.add(serviceCity.getCityName());
	        }
	        map.put("serviceCities",String.join(",", servicesCities));
	        int activeStatus = vendorOrg.getActiveStatus();
	        int deleteStatus = vendorOrg.getDeleteStatus();
	        if( deleteStatus == UserAccountStatus.ACTIVE.getValue()){
	        	 if(activeStatus == UserAccountStatus.INVITED.getValue()) {
	 	        	map.put("accountStatus","Registered");
	 	        } else if(activeStatus == UserAccountStatus.ACTIVE.getValue()){
	 	        	map.put("accountStatus","Active");
	 	        } 
	        } else if ( deleteStatus == UserAccountStatus.INACTIVE.getValue() || activeStatus == UserAccountStatus.INACTIVE.getValue()) {
	        	map.put("accountStatus","Deleted");
	        } else {
	        	map.put("accountStatus","Deleted");
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
	        
	        List<RegistrationLogs> registrationLogs = registrationLogsRepository.findByUserTypeAndOrganisationId(UserType.VENDOR.getValue(), vendorOrg.getVendorOrganisationId());
			for(RegistrationLogs registrationLogAdmin : registrationLogs) {
				VendorUser vendorUser = vendorUserRepository.findByUserId(registrationLogAdmin.getUserId());
				map.put("adminFirstName", vendorUser.getFirstName());
				map.put("adminLastName", vendorUser.getLastName());
				map.put("adminEmail", vendorUser.getEmail());
			} 
			
	        vendorOrganisations.add(map);
		}
		
		return vendorOrganisations;
	}

	public VendorUser updateVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		
		VendorUser vendorUserObj = vendorUserRepository.findByUserId(vendorUser.getUserId());
		
		int changeType = 0;
		int changeTypeName = 0;
		int changeTypePhone = 0;
		if(!vendorUserObj.getFirstName().equals(vendorUser.getFirstName())) {
			changeTypeName = 1;
		} 
		if(!vendorUserObj.getLastName().equals(vendorUser.getLastName())) {
			changeTypeName = 1;
		} 
		if(!vendorUserObj.getPhone().equals(vendorUser.getPhone())) {
			changeTypePhone = 1;
		}
		
		if(changeTypePhone == 1 && changeTypeName == 1) {
			changeType = NotificationType.USER_NAME_PHONE_CHANGE.getValue();
		} else if(changeTypeName == 1) {
			changeType = NotificationType.USER_NAME_CHANGE.getValue();
		} else if(changeTypePhone == 1) {
			changeType = NotificationType.USER_PHONE_CHANGE.getValue();
		} else {
			changeType = 0;
		}
		
		if(changeType != 0)
		SendUserProfileUpdateAlert(vendorUser, changeType );
		
		vendorUserObj.setFirstName(vendorUser.getFirstName());
		vendorUserObj.setLastName(vendorUser.getLastName());
		vendorUserObj.setPhone(vendorUser.getPhone());
		
		return vendorUserRepository.save(vendorUserObj);
	}

	private void SendUserProfileUpdateAlert(VendorUser vendorUser, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Account Update";
		String subContent = " account updated";
		String modifiedBy = vendorUser.getFirstName()+" "+vendorUser.getLastName();
		notification.setUserType(UserType.VENDOR.getValue());
		notification.setUserId(vendorUser.getUserId());
		notification.setOrganisationId(Invalid.ID.getValue());
		notification.setNotificationCategoryId(vendorUser.getUserId());
		//Update: Your user account information was recently changed: Change type
		message = "Update";
		subContent = "Your user account information was recently changed: ";
		String changeType = "";
		switch(notificationType) {
			case 36 :{
				changeType = "Name";
				break;
			}
			case 37 :{
				changeType = "Phone";
				break;
			}
			case 38 :{
				changeType = "Name and Phone";
				break;
			}
			case 0 :{
				notificationType = 38;
				changeType = "No change";
				break;
			}

		}
		subContent = subContent + changeType;
		notification.setNotificationCategoryType(notificationType);
		notification.setTitle(message);
		notification.setDescription(subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());
		//test
		notificationRepository.save(notification);
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
			String memberships = String.valueOf(vendorOrganisationData.get("memberships")); 
			// string - abc,abc,acbc
			Integer modifiedByUserId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("modifiedByUserId"))); // string - abc,abc,acbc
			
			VendorOrganisation vendorOrg = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
			vendorOrganisation.setActiveStatus(vendorOrg.getActiveStatus());
			vendorOrganisation.setDeleteStatus(vendorOrg.getDeleteStatus());
			vendorOrganisation.setUserType(vendorOrg.getUserType());
			vendorOrganisation.setExpertiseCategory("");
			vendorOrganisation.setCountryCode("");
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
			    
			    SendAccountUpdateAlert(Invalid.ID.getValue(), vendorOrganisation.getVendorOrganisationId(), modifiedByUserId, NotificationType.VENDOR_ORGANISATION_UPDATE.getValue());
			    
				return vendorOrganisation;
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
		
		return vendorOrganisationData;
	}

	public List<Map<String, Object>> getVendorOrganisationUsersById(Integer id) {
		// TODO Auto-generated method stub
//		return vendorUserRepository.findByVendorOrganisationIdAndAccountStatus(id, Constants.UserAccountStatus.ACTIVE.getValue());
		
		List<VendorUser> users = vendorUserRepository.findByVendorOrganisationId(id);
		List<Map<String, Object>> vendorUsers = new ArrayList<>();
		for(VendorUser vendorUser : users) {
			ObjectMapper objMapper = new ObjectMapper();
			Map<String, Object> map = objMapper.convertValue(vendorUser, Map.class);
			
			map.put("userProfileImage",getUserProfileImageURL(vendorUser.getUserId()));
			
			vendorUsers.add(map);
		}
		
		return vendorUsers;
	}

	public List<Map<String, Object>> getVendorPortfolio(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorPortfolio> vendorPortfolios = vendorPortfolioRepository.findByVendorOrganisationIdAndStatus(vendorOrganisationId, DeleteStatus.ACTIVE.getValue());
		List<Map<String, Object>> portfolios = new ArrayList<>();
		
		for (VendorPortfolio vendorPortfolio : vendorPortfolios) {
			ObjectMapper objMapper = new ObjectMapper();
			Map<String, Object> map = objMapper.convertValue(vendorPortfolio, Map.class);
			
			
			
			map.put("portfolioFiles",getVendorPortfolioFiles(vendorPortfolio.getId()));
			
			portfolios.add(map);
		}
		return portfolios;
	}

	public VendorPortfolio addVendorPortfolio(VendorPortfolio vendorPortfolio) {
		// TODO Auto-generated method stub
		vendorPortfolio.setStatus(DeleteStatus.ACTIVE.getValue());
		return vendorPortfolioRepository.save(vendorPortfolio);
	}
	
	public VendorPortfolio updateVendorPortfolio(VendorPortfolio vendorPortfolio) {
		// TODO Auto-generated method stub
		VendorPortfolio portfolio = vendorPortfolioRepository.findOneById(vendorPortfolio.getId());
		vendorPortfolio.setStatus(portfolio.getStatus());
		return vendorPortfolioRepository.save(vendorPortfolio);
	}
	
	public List<VendorInsurance> getVendorInsurance(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorInsurance> vendorinsurances =  vendorInsuranceRepository.findByVendorOrganisationId(vendorOrganisationId);
		
		List<VendorInsurance> singleInsurance = new ArrayList();
		if(vendorinsurances != null && vendorinsurances.size() > 0) {
			if(vendorinsurances.get(0).getInsuranceAvailability() == Constants.Availability.AVAILABLE.getValue()) {
				singleInsurance.add(vendorinsurances.get(0));	
			}
		}
		
		return singleInsurance;
	}

	public VendorInsurance addVendorInsurance(VendorInsurance vendorInsurance) {
		// TODO Auto-generated method stub
		return vendorInsuranceRepository.save(vendorInsurance);
	}
	
	public VendorInsurance updateVendorInsurance(VendorInsurance vendorInsurance) {
		// TODO Auto-generated method stub
		return vendorInsuranceRepository.save(vendorInsurance);
	}

	public List<VendorPortfolio> sortVendorPortfolio(Integer orgId, Integer sortBy) {
		// TODO Auto-generated method stub
		
		switch(sortBy) {
			case 1: {//Constants.PortfolioSortBy.ASC
				return vendorPortfolioRepository.findByVendorOrganisationIdAndStatusOrderByProjectNameAsc(orgId, DeleteStatus.ACTIVE.getValue());
			}
			case 2: {//Constants.PortfolioSortBy.DESC
				return vendorPortfolioRepository.findByVendorOrganisationIdAndStatusOrderByProjectNameDesc(orgId, DeleteStatus.ACTIVE.getValue());
			}
			case 3: {//Constants.PortfolioSortBy.DATE
				return vendorPortfolioRepository.findByVendorOrganisationIdAndStatusOrderByCreatedAtAsc(orgId, DeleteStatus.ACTIVE.getValue());
			}
			case 4: {//Constants.PortfolioSortBy.COST
				return vendorPortfolioRepository.findByVendorOrganisationIdAndStatusOrderByCostAsc(orgId, DeleteStatus.ACTIVE.getValue());
			}
			case 5: {//Constants.PortfolioSortBy.DURATION
				return vendorPortfolioRepository.findByVendorOrganisationIdAndStatusOrderByDurationAsc(orgId, DeleteStatus.ACTIVE.getValue()); // check - ?
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
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAllByActiveStatus(UserAccountStatus.ACTIVE.getValue());
		
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
	        List<VendorServicesCities> vendorServicesCities = vendorServicesCitiesRepository.findByVendorOrganisationId(vendorOrg.getVendorOrganisationId());
	        List<String> servicesCities = new ArrayList();
	        for(VendorServicesCities vendorCity : vendorServicesCities) {
	        	ServiceCities serviceCity = servicesCitiesRepository.findOneById(vendorCity.getServiceCityId());
	        	servicesCities.add(serviceCity.getCityName());
	        }
	        map.put("serviceCities",String.join(",", servicesCities));
	        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
	        if(userWish != null && userWish.getInterestStatus() == ProjectInterestStatus.LIKE.getValue()) {//check if you found error
	        	map.put("isPreferred", "true");
	        } else {
	        	map.put("isPreferred", "false");
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

	public UserWishList addClientAsFavourite(Map<String, Object> userWishList) {
		// TODO Auto-generated method stub
		
		Integer favouriteOrgId = (Integer) userWishList.get("favouriteOrgId");
		Integer wisherOrgId = (Integer) userWishList.get("wisherOrgId");
		Integer wisherUserId = (Integer) userWishList.get("wisherUserId");
		Integer interestStatus = (Integer) userWishList.get("interestStatus");
		
		UserWishList userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(wisherOrgId, Constants.UserType.VENDOR.getValue(), favouriteOrgId, Constants.UserType.CLIENT.getValue());
		if(userWish == null) {
			userWish = new UserWishList();
		} 
		userWish.setWisherUserType(Constants.UserType.VENDOR.getValue());
		userWish.setFavouriteUserType(Constants.UserType.CLIENT.getValue());
		userWish.setFavouriteOrgId(favouriteOrgId);
		userWish.setWisherOrgId(wisherOrgId);
		userWish.setWisherUserId(wisherUserId);
		userWish.setInterestStatus(interestStatus);
		
		return userWishListRepository.save(userWish);
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
	
	public List<OrganisationPayment> getPaymentBillingDetails(Integer orgId) {
		// TODO Auto-generated method stub
		List paymentDetails = new ArrayList();
		paymentDetails.add(new OrganisationPayment());
		return paymentDetails;
	}

	public VendorUser deleteVendorUserById(Integer vendorUserId, Integer modifiedByUserId) {
		// TODO Auto-generated method stub
		VendorUser vendorUser = vendorUserRepository.findByUserId(vendorUserId);
		vendorUser.setAccountStatus(UserAccountStatus.INACTIVE.getValue());
//		vendorUser.setDeleteStatus(UserAccountStatus.INACTIVE.getValue());-Delete status will remove user from system
		vendorUser.setModifiedByUserId(modifiedByUserId);
		VendorUser vendorUserObj = vendorUserRepository.save(vendorUser);
		if(vendorUserObj != null) {
			SendAccountUpdateAlert(vendorUserId, vendorUser.getVendorOrganisationId(), modifiedByUserId, NotificationType.VENDOR_USER_PROFILE_DELETE.getValue());
		}
		return vendorUserObj;
	}
	
	private void SendAccountUpdateAlert(Integer vendorUserId, Integer vendorOrgId, Integer modifiedbyUserId, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Account Update";
		String subContent = " account updated";
		VendorUser vendoruser = vendorUserRepository.findByUserId(vendorUserId);
		VendorUser modifiedbyVendoruser = vendorUserRepository.findByUserId(modifiedbyUserId);
		VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrgId);
		notification.setUserType(UserType.VENDOR.getValue());
		notification.setUserId(vendorUserId);
		notification.setOrganisationId(vendorOrgId);
		
		String userName = modifiedbyVendoruser.getFirstName()+" "+modifiedbyVendoruser.getLastName();
		
		switch(notificationType) {
			case 23 :{//VENDOR_USER_PROFILE_DELETE
				message = "User deleted!";
//				subContent = vendoruser.getFirstName()+" "+vendoruser.getLastName()+" user account deleted from Organisation";
				subContent = "User "+userName+" has deleted User "+vendoruser.getFirstName()+" "+vendoruser.getLastName()+" from the organization's account.";
				notification.setNotificationCategoryId(vendorUserId);
				break;
			}
			case 24 :{//VENDOR_USER_PROFILE_UPDATE
				message = "User account update!";
//				subContent = vendoruser.getFirstName()+" "+vendoruser.getLastName()+" user account updated in our Organisation";
				subContent = "User "+vendoruser.getFirstName()+" "+vendoruser.getLastName()+" account information has been updated by User "+userName;
				notification.setNotificationCategoryId(vendorUserId);
				break;
			}
			case 25 :{//VENDOR_ORGANISATION_UPDATE
				message = "Update";
				
//				subContent = vendorOrganisation.getCompanyName() +" - our organisation profile information updated";
				subContent = "Your organization's profile information was recently edited by User "+userName;
				notification.setNotificationCategoryId(vendorOrgId);
				break;
			}

		}
		
		notification.setNotificationCategoryType(notificationType);
		
		notification.setTitle(message);
		notification.setDescription(subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notificationRepository.save(notification);
	}
	
	public int getUsersCountByOrganisationId(Integer vendorId) {
		// TODO Auto-generated method stub
		VendorUser vendorUser = vendorUserRepository.findByUserId(vendorId);
		List<VendorUser> users = vendorUserRepository.findAllActiveUsersByVendorOrganisationId(vendorUser.getVendorOrganisationId());
		
		return users.size();
	}

	public Object updateVendorUserRole(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer vendorUserId = Integer.parseInt(String.valueOf(requestData.get("userId")));
		Integer userRole = Integer.parseInt(String.valueOf(requestData.get("userRole")));
    	Integer modifiedByUserId = Integer.parseInt(String.valueOf(requestData.get("modifiedByUserId")));
//    	String firstName = String.valueOf(requestData.get("firstName"));
//    	String lastName = String.valueOf(requestData.get("lastName"));
    	
    	VendorUser vendorUser = vendorUserRepository.findByUserId(vendorUserId);
    	
    	vendorUser.setUserRole(userRole);
//    	vendorUser.setFirstName(firstName);
//    	vendorUser.setLastName(lastName);
    	
		VendorUser vendorUserObj = vendorUserRepository.save(vendorUser);
		
		if(vendorUserObj != null) {
			SendAccountUpdateAlert(vendorUserId, vendorUser.getVendorOrganisationId(), modifiedByUserId, NotificationType.VENDOR_USER_PROFILE_UPDATE.getValue());
		}
		
		return vendorUserObj;
	}
	
	public boolean checkOrganisationNameAvailable(Integer vendorOrganisationId, String companyName) {
		// TODO Auto-generated method stub
		
		List<VendorOrganisation> orgs = vendorOrganisationRepository.findByCompanyName(companyName.toLowerCase());
		
		for(VendorOrganisation org : orgs) {
			if(org.getCompanyName().toLowerCase().trim().equals(companyName.toLowerCase()) && !org.getVendorOrganisationId().equals(vendorOrganisationId)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkLegalNameAvailable(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		VendorOrganisation vendorOrganisation = mapper.convertValue(vendorOrganisationData.get("organisation"), VendorOrganisation.class);
		
		List<VendorOrganisation> org = vendorOrganisationRepository.findByLegalName(vendorOrganisation.getLegalName());
		
		if( org != null && org.size() > 0) {
			return true;
		}
		
		return false;
	}

	public Object updateVendorOrganisationCompany(Map<String, Object> vendorOrganisationData) {
		// TODO Auto-generated method stub
		try {
			Integer vendorOrganisationId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("vendorOrganisationId"))); 
			String companyName = String.valueOf(vendorOrganisationData.get("companyName")); 
			String description = String.valueOf(vendorOrganisationData.get("description")); 
			String serviceCities = String.valueOf(vendorOrganisationData.get("serviceCities")); 
			String tags = String.valueOf(vendorOrganisationData.get("tags"));
			Integer modifiedByUserId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("modifiedByUserId")));
			
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
			vendorOrganisation.setExpertiseCategory("");
			vendorOrganisation.setCountryCode("");
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
		    
			SendOrganisationUpdateAlert(vendorOrganisation, modifiedByUserId, Constants.NotificationType.VENDOR_ORGANISATION_UPDATE.getValue());
			
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
			Integer modifiedByUserId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("modifiedByUserId"))); 
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);

			vendorOrganisation.setEmployeesCount(Integer.parseInt(employeesCount));
			vendorOrganisation.setEstablishedDate(establishedDate);
			vendorOrganisation.setAnnualRevenue(annualRevenue);
			vendorOrganisation.setExpertiseCategory("");
			vendorOrganisation.setCountryCode("");
			vendorOrganisationRepository.save(vendorOrganisation);
			
			SendOrganisationUpdateAlert(vendorOrganisation, modifiedByUserId, Constants.NotificationType.VENDOR_ORGANISATION_UPDATE.getValue());
			
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
			Integer modifiedByUserId = Integer.parseInt(String.valueOf(vendorOrganisationData.get("modifiedByUserId"))); 
			
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
			vendorOrganisation.setExpertiseCategory("");
			vendorOrganisation.setCountryCode("");
			
			vendorOrganisationRepository.save(vendorOrganisation);
			
			SendOrganisationUpdateAlert(vendorOrganisation, modifiedByUserId, Constants.NotificationType.VENDOR_ORGANISATION_UPDATE.getValue());
			
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
				// 0 - if insurance availability 0 then it needs to be deleted
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
				
				// 0 delete/add insurance
				// 1 add service cities - NA
				// 2 add services
				// 3 add licenses
				// 4 add products
				// 5 add brands
				// 6 add memberships
				List<VendorInsurance> existingVendorInsurances = vendorInsuranceRepository.findByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
				if(existingVendorInsurances.size() > 0) {
					VendorInsurance singleInsurance = existingVendorInsurances.get(0);
					if(vendorInsurance != null && vendorInsurance.getInsuranceAvailability() == Constants.InsuranceBondAvailability.AVAILABLE.getValue()) {
						singleInsurance.setInsuranceAvailability(vendorInsurance.getInsuranceAvailability());
						singleInsurance.setInsuranceBonded(vendorInsurance.getInsuranceBonded());
						singleInsurance.setInsuranceCompany(vendorInsurance.getInsuranceCompany());
						singleInsurance.setInsuranceLiability(vendorInsurance.getInsuranceLiability());;
						singleInsurance.setInsuranceNumber(vendorInsurance.getInsuranceNumber());
						singleInsurance.setInsurancePolicyExpiryDate(vendorInsurance.getInsurancePolicyExpiryDate());
						
						vendorInsuranceRepository.save(singleInsurance);
					} else if(vendorInsurance != null && vendorInsurance.getInsuranceAvailability() == Constants.InsuranceBondAvailability.NOT_AVAILABLE.getValue()) {
//						vendorInsuranceRepository.deleteByVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
						
						//instead of delete - change empty of insurance data
						singleInsurance.setInsuranceAvailability(vendorInsurance.getInsuranceAvailability());
						singleInsurance.setInsuranceBonded(vendorInsurance.getInsuranceBonded());
						singleInsurance.setInsuranceCompany("");
						singleInsurance.setInsuranceLiability("");
						singleInsurance.setInsuranceNumber(vendorInsurance.getInsuranceNumber());
						singleInsurance.setInsurancePolicyExpiryDate("");
						
						vendorInsuranceRepository.save(singleInsurance);
					}
					
				} else {
					if(vendorInsurance != null ) {
						vendorInsurance.setVendorOrganisationId(vendorOrganisation.getVendorOrganisationId());
						vendorInsuranceRepository.save(vendorInsurance);
					}
				}
				
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

	public List<Map<String, Object>> getVendorRegistrationFiles(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorRegistrationFiles> vendorRegistrationFiles = vendorRegistrationFilesRepository.findAllByVendorOrganisationId(vendorOrganisationId);
		
		List<Map<String, Object>> files = new ArrayList();
		for(VendorRegistrationFiles registrationFile : vendorRegistrationFiles) {
			Map<String, Object> obj = new HashMap<>();
			
			obj.put("id", registrationFile.getId());
			obj.put("fileName", registrationFile.getFileName());
			obj.put("fileType", registrationFile.getFileType());
			obj.put("fileSize", Utils.formatFileSize(Long.parseLong(registrationFile.getFileSize())));
			obj.put("blobName", registrationFile.getBlobName());
			obj.put("containerName", registrationFile.getContainerName());
//			obj.put("fileUrl", registrationFile.getFileUrl());
			obj.put("createdAt", registrationFile.getCreatedAt());
			
			files.add(obj);
		}
		
		return files;
	}
	
	public List<Map<String, Object>> getVendorPortfolioFiles(Integer vendorPortfolioId) {
		// TODO Auto-generated method stub
		List<VendorPortfolioFiles> vendorPortfolioFiles = vendorPortfolioFilesRepository.findAllByVendorPortfolioId(vendorPortfolioId);
		
		List<Map<String, Object>> files = new ArrayList();
		for(VendorPortfolioFiles portfolioFile : vendorPortfolioFiles) {
			Map<String, Object> obj = new HashMap<>();
			
			obj.put("id", portfolioFile.getId());
			obj.put("fileName", portfolioFile.getFileName());
			obj.put("fileType", portfolioFile.getFileType());
			obj.put("fileSize", Utils.formatFileSize(Long.parseLong(portfolioFile.getFileSize())));
			obj.put("blobName", portfolioFile.getBlobName());
			obj.put("containerName", portfolioFile.getContainerName());
//			obj.put("fileUrl", registrationFile.getFileUrl());
			obj.put("createdAt", portfolioFile.getCreatedAt());
			
			files.add(obj);
		}
		
		return files;
	}
	
	public VendorUser createVendorUser(VendorUser vendorUser) {
		// TODO Auto-generated method stub
		try {
			
			if(vendorUserRepository.save(vendorUser) != null){
				
				vendorUser = vendorUserRepository.findByEmail(vendorUser.getEmail());
				
				System.out.println(vendorUser.toString());
				
				User user = new User();
				user.setUserId(vendorUser.getUserId());
				user.setUsername(vendorUser.getEmail());
				user.setUserType(Constants.UserType.VENDOR.getValue());
				
				System.out.println(user.toString());
				
				userRepository.save(user);
				
				return vendorUser;
			} else {
				return null;
			}
			} catch (Exception exp) {
				exp.printStackTrace();
				return null;
			}
	}
	
	public List<Object> getAllVendorProfilesForVendorRegistration() {
		// TODO Auto-generated method stub
				List<AvailableVendorProfiles> vendorOrgsAll = availableVendorProfilesRepository.findAll();
				
				List<Object> vendorOrganisations = new ArrayList();
				
				for(AvailableVendorProfiles vendorOrg : vendorOrgsAll) {
					ObjectMapper oMapper = new ObjectMapper();
			        // object -> Map
			        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
			        
			        
			        if(vendorOrg.getCity() != null && vendorOrg.getCity().length() > 0) {
			        	try {
			        		Integer city = Integer.parseInt(vendorOrg.getCity());
			        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
			        		map.put("city",serviceCity.getCityName());
			        	} catch(Exception exp) {
			        		map.put("city",vendorOrg.getCity());
			        	}
			        	
			        } else {
			        	map.put("city","");
					}
			        
			        int activeStatus = vendorOrg.getActiveStatus();
//			        if( deleteStatus == UserAccountStatus.ACTIVE.getValue()){
//			        	 if(activeStatus == UserAccountStatus.INVITED.getValue()) {
//			 	        	map.put("accountStatus","Registered");
//			 	        } else if(activeStatus == UserAccountStatus.ACTIVE.getValue()){
//			 	        	map.put("accountStatus","Active");
//			 	        } 
//			        } else if ( deleteStatus == UserAccountStatus.INACTIVE.getValue() || activeStatus == UserAccountStatus.INACTIVE.getValue()) {
//			        	map.put("accountStatus","Deleted");
//			        } else {
//			        	map.put("accountStatus","Deleted");
//			        }
			       
			        vendorOrganisations.add(map);
				}
				
				return vendorOrganisations;
	}

	public boolean deleteVendorPortfolio(Integer portfolioId) {
		// TODO Auto-generated method stub
		try {
			VendorPortfolio vendorPortfolio = vendorPortfolioRepository.findOneById(portfolioId);
			
			if(vendorPortfolio != null) {
				vendorPortfolio.setStatus(Constants.UserAccountStatus.INACTIVE.getValue());
				if(vendorPortfolioRepository.save(vendorPortfolio) != null) {
					return true;
				} else {
					return false;
				}
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return false;
	}

	public boolean isInvitedVendor(Integer vendorId) {
		// TODO Auto-generated method stub
		
		VendorUser vendorUser = vendorUserRepository.findByUserId(vendorId);
		if(vendorUser != null && vendorUser.getAccountStatus() == UserAccountStatus.INVITED.getValue() && vendorUser.getAccountVerificationStatus() == UserAccountStatus.INVITED.getValue()) {
			return true;
		}
		return false;
	}
	
	private void SendOrganisationUpdateAlert(VendorOrganisation vendorOrganisation, Integer modifiedByUserId, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Account Update";
		String subContent = " account updated";
		VendorUser modifiedByVendorUser = vendorUserRepository.findByUserId(modifiedByUserId);
		String modifiedBy = modifiedByVendorUser.getFirstName()+" "+modifiedByVendorUser.getLastName();
		notification.setUserType(UserType.VENDOR.getValue());
		notification.setUserId(modifiedByUserId);
		notification.setOrganisationId(vendorOrganisation.getVendorOrganisationId());
		
		switch(notificationType) {
			case 25 :{
				message = "Update";
				subContent = "Your organization's profile information was recently edited by User "+modifiedBy;
				notification.setNotificationCategoryId(vendorOrganisation.getVendorOrganisationId());
				break;
			}

		}
		
		notification.setNotificationCategoryType(notificationType);
		
		notification.setTitle(message);
		notification.setDescription(subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notificationRepository.save(notification);
	}
	
}

