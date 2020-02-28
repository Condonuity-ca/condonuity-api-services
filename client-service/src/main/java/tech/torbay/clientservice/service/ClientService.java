package tech.torbay.clientservice.service;

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

import tech.torbay.clientservice.Utils.Utils;
import tech.torbay.clientservice.constants.Constants;
import tech.torbay.clientservice.constants.Constants.TaskStatus;
import tech.torbay.clientservice.constants.Constants.UserAccountStatus;
import tech.torbay.clientservice.entity.ClientAmenities;
import tech.torbay.clientservice.entity.ClientAssociation;
import tech.torbay.clientservice.entity.ClientBuildingRepository;
import tech.torbay.clientservice.entity.ClientContract;
import tech.torbay.clientservice.entity.ClientOrganisation;
import tech.torbay.clientservice.entity.ClientRegistrationFiles;
import tech.torbay.clientservice.entity.ClientTask;
import tech.torbay.clientservice.entity.ClientTaskComments;
import tech.torbay.clientservice.entity.ClientUser;
import tech.torbay.clientservice.entity.ClientUserTasks;
import tech.torbay.clientservice.entity.OrganisationPayment;
import tech.torbay.clientservice.entity.Project;
import tech.torbay.clientservice.entity.ProjectReviewRating;
import tech.torbay.clientservice.entity.UserProfileImages;
import tech.torbay.clientservice.entity.UserWishList;
import tech.torbay.clientservice.entity.VendorCategoryRatings;
import tech.torbay.clientservice.entity.VendorOrganisation;
import tech.torbay.clientservice.repository.ClientAmenitiesRepository;
import tech.torbay.clientservice.repository.ClientAssociationRepository;
import tech.torbay.clientservice.repository.ClientBuildingRepoRepository;
import tech.torbay.clientservice.repository.ClientContractRepository;
import tech.torbay.clientservice.repository.ClientOrganisationRepository;
import tech.torbay.clientservice.repository.ClientRegistrationFilesRepository;
import tech.torbay.clientservice.repository.ClientTaskCommentsRepository;
import tech.torbay.clientservice.repository.ClientTaskRepository;
import tech.torbay.clientservice.repository.ClientUserRepository;
import tech.torbay.clientservice.repository.ClientUserTasksRepository;
import tech.torbay.clientservice.repository.OrganisationPaymentRepository;
import tech.torbay.clientservice.repository.ProjectRepository;
import tech.torbay.clientservice.repository.ProjectReviewRatingRepository;
import tech.torbay.clientservice.repository.UserProfileImagesRepository;
import tech.torbay.clientservice.repository.UserWishListRepository;
import tech.torbay.clientservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.clientservice.repository.VendorOrganisationRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	ClientAmenitiesRepository clientAmenitiesRepository;
	@Autowired
	OrganisationPaymentRepository clientOrganisationPaymentRepository;
	@Autowired
	ClientAssociationRepository clientAssociationRepository;
//	@Autowired
//	ClientOrganisationPaymentRepository clientOrganisationPaymentRepository;
	@Autowired
	UserWishListRepository userWishListRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;
	@Autowired
	ProjectReviewRatingRepository projectReviewRatingRepository;
	@Autowired
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ClientRegistrationFilesRepository clientRegistrationFilesRepository;
	@Autowired
	UserProfileImagesRepository userProfileImagesRepository;
	@Autowired
	ClientContractRepository clientContractRepository;
	@Autowired
	ClientTaskRepository clientTaskRepository;
	@Autowired
	ClientTaskCommentsRepository clientTaskCommentsRepository;
	@Autowired
	ClientUserTasksRepository clientUserTasksRepository;
	@Autowired
	ClientBuildingRepoRepository clientBuildingRepoRepository;

	public List<ClientUser> getAllClientUsers() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(clientUserRepository.findAll());
	}

	public ClientUser findByEmail(String email) {
		// TODO Auto-generated method stub
		ClientUser client = clientUserRepository.findByEmail(email);
		return client;
	}

	public ClientUser addClientOrgAccountAssociation(ClientUser clientUser) {
		// TODO Auto-generated method stub
		return /* clientUser */null;
	}

	public ClientUser addClient(ClientUser clientUser) {
		// TODO Auto-generated method stub
		return clientUserRepository.save(clientUser);
	}

	public ClientOrganisation addClientOrganisation(ClientOrganisation clientorganisation) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.save(clientorganisation);
	}

	public Object getClientUserById(Integer userId) {
		// TODO Auto-generated method stub
		ClientUser clientUser = clientUserRepository.findByClientId(userId);
		
		
		ObjectMapper oMapper = new ObjectMapper();
        // object -> Map
        Map<String, Object> map = oMapper.convertValue(clientUser, Map.class);
        
        UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(clientUser.getClientId(), Constants.UserType.CLIENT.getValue());
        
        try {
        map.put("profileImageURL",userProfileImage.getFileUrl());
        } catch(Exception exp) {
        	exp.printStackTrace();
        }
//        map.put("",""); blobName
        
        return map;
	}

	public List<Object> getAllCorporateAccounts(Integer clientUserId) {
		
		// TODO Auto-generated method stub
		try {
			
			List<ClientAssociation> clientOrgIds = clientAssociationRepository.findClientOrganisationIdByClientId(clientUserId);
			System.out.print(clientOrgIds.toString());
			
			List<Integer> ids = clientOrgIds.stream()
                    .map(ClientAssociation::getClientOrganisationId).collect(Collectors.toList());
			
//			List<Integer> ids = new ArrayList();
//			ids.add(0);
			List<ClientOrganisation> clientUserOrganisations = clientOrganisationRepository.findByClientOrganisationId(ids );
			
			ClientUser clientUser = clientUserRepository.findByClientId(clientUserId);
			Integer clientPrimaryOrgId = clientUser.getPrimaryOrgId(); 
			
			List<Object> clientOrgs = new ArrayList();
			for(ClientOrganisation clientOrg : clientUserOrganisations) {
				ObjectMapper oMapper = new ObjectMapper();
		        // object -> Map
		        Map<String, Object> map = oMapper.convertValue(clientOrg, Map.class);
				if(clientOrg.getClientOrganisationId() == clientPrimaryOrgId) {
					map.put("isPrimary", "true");
				} else {
					map.put("isPrimary","false");
				}
				
				ClientAssociation clientAssociate = clientAssociationRepository.findByClientIdAndClientOrganisationId(clientUserId, clientOrg.getClientOrganisationId());
		        
//		        if(clientAssociate.getUserAccountStatus() == Constants.UserAccountStatus.INACTIVE.getValue() 
//		        		&& clientAssociate.getAccountVerificationStatus() == Constants.VerificationStatus.VERIFIED.getValue() ) {
//			        // No need to add - user account deleted
//		        } else {
//		        	
//		        } // Need to check this flow
		        
//		        if(clientAssociate.getUserAccountStatus() != Constants.UserAccountStatus.INACTIVE.getValue()) { 
				// Front end need to handle inactive organisation in list
		        	map.put("clientUserType", clientAssociate.getClientUserType());
			        map.put("userRole", clientAssociate.getUserRole());
			        map.put("userAccountStatus", clientAssociate.getUserAccountStatus());
			        map.put("userActiveFrom", clientAssociate.getCreatedAt());
			        map.put("userActiveTo", clientAssociate.getUserInactiveDate());
			        clientOrgs.add(map);
//		        }
		        
				
			}
			
			return clientOrgs;
		
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}

	public ClientUser saveClient(ClientUser client) {
		// TODO Auto-generated method stub
		
		ClientUser clientObj = clientUserRepository.findByClientId(client.getClientId());
		
		clientObj.setCity(client.getCity());
		clientObj.setPhone(client.getPhone());
		clientObj.setFirstName(client.getFirstName());
		clientObj.setLastName(client.getLastName());
		clientObj.setLegalName(client.getLegalName());
		clientObj.setCountryCode(client.getCountryCode());
		
		return clientUserRepository.save(clientObj);
	}

	public ClientOrganisation updateOrganisation(ClientOrganisation clientOrg) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.save(clientOrg);
	}

	public ClientOrganisation getOrganisationById(Integer id) {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.findByClientOrganisationId(id);
	}

	public List<ClientAmenities> getAmenitiesByOrgId(Integer clientOrgId) {
		// TODO Auto-generated method stub
		return clientAmenitiesRepository.findAllByClientOrganisationId(clientOrgId);
	}

	public List<Object> getAllClientsByOrganisation(Integer clientOrgId) {
		// TODO Auto-generated method stub
		try {
			
			List<ClientAssociation> clientIds = clientAssociationRepository.findClientIdByClientOrganisationId(clientOrgId);
			System.out.print(clientIds.toString());
			
			List<Integer> ids = clientIds.stream()
                    .map(ClientAssociation::getClientId).collect(Collectors.toList());
//			List<Integer> ids = new ArrayList();
//			ids.add(0);
			
			List<ClientUser> clientUsers = clientUserRepository.findByClientId(ids);
			
			List<Object> clientList = new ArrayList();
			
			for(ClientUser clientObj : clientUsers) {
				ObjectMapper oMapper = new ObjectMapper();
		        // object -> Map
		        Map<String, Object> map = oMapper.convertValue(clientObj, Map.class);
		        
		        ClientAssociation clientAssociate = clientAssociationRepository.findByClientIdAndClientOrganisationId(clientObj.getClientId(), clientOrgId);
		        
//		        if(clientAssociate.getUserAccountStatus() == Constants.UserAccountStatus.INACTIVE.getValue() 
//		        		&& clientAssociate.getAccountVerificationStatus() == Constants.VerificationStatus.VERIFIED.getValue() ) {
//			        // No need to add - user account deleted
//		        } else {
//		        	
//		        } // Need to check this flow
		        
		        if(clientAssociate.getUserAccountStatus() != Constants.UserAccountStatus.INACTIVE.getValue()) {
		        	map.put("clientUserType", clientAssociate.getClientUserType());
			        map.put("userRole", clientAssociate.getUserRole());
			        map.put("userAccountStatus", clientAssociate.getUserAccountStatus());
			        clientList.add(map);
		        }
			}
			
			return clientList;
		
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public List<OrganisationPayment> getPaymentBillingDetails(Integer orgId) {
		// TODO Auto-generated method stub
//		return clientOrganisationPaymentRepository.findAllByOrganisationId(orgId);
		List paymentDetails = new ArrayList();
		paymentDetails.add(new OrganisationPayment());
		return paymentDetails;
	}

	public ClientAmenities updateAmenities(ClientAmenities amenitiesInfo) {
		// TODO Auto-generated method stub
		
		try {
			System.out.print(amenitiesInfo.toString());
			ClientAmenities amenity = clientAmenitiesRepository.save(amenitiesInfo);
			return amenity;
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}

	public List<ClientOrganisation> getAllClientOrganisations() {
		// TODO Auto-generated method stub
		return clientOrganisationRepository.findAll();
	}

	public List<Object> getAllClientOrganisationsByVendorOrgId(Integer vendorOrgId) {
		// TODO Auto-generated method stub
		
		List<ClientOrganisation> clientOrgsAll = clientOrganisationRepository.findAll();
		
		List<Object> clientOrganisations = new ArrayList();
		
		for(ClientOrganisation clientOrg : clientOrgsAll) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(clientOrg, Map.class);
	        
	        List<UserWishList> userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(vendorOrgId, Constants.UserType.VENDOR.getValue(), clientOrg.getClientOrganisationId(), Constants.UserType.CLIENT.getValue() );
	        
	        map.put("isPreferred", "false");
	        
	        if(userWish != null) {
	        	map.put("isPreferred", "true");
	        }
			clientOrganisations.add(map);
		}
		
    			
		return clientOrganisations;
	}

	public UserWishList addVendorAsFavourite(UserWishList userWishList) {
		// TODO Auto-generated method stub
		
		userWishList.setWisherUserType(Constants.UserType.CLIENT.getValue());
		userWishList.setFavouriteUserType(Constants.UserType.VENDOR.getValue());
		
		return userWishListRepository.save(userWishList);
	}

	public boolean rateVendorByCategory(Map<String, Object> ratings) {
		// TODO Auto-generated method stub
		
		try {
			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			List vendorCategoryRatingsObj = mapper.convertValue(ratings.get("categoryRatings"), List.class);
			
			List<Map<String,Object>> vendorCategoryRatings = (List<Map<String,Object>>) vendorCategoryRatingsObj;
			Integer clientId = (Integer) ratings.get("clientId");
			Integer clientOrganisationId = (Integer) ratings.get("clientOrganisationId");
			String reviewComments = (String) ratings.get("reviewComments");
			String overAllRating = (String) ratings.get("overAllRating");
			
			
			Integer projectId = null;
			Integer vendorOrganisationId = null;
//			try {
//				projectId = (Integer) ratings.get("projectId");
//			} catch(Exception exp) {
//				exp.printStackTrace();
//			}
			
			try {
				vendorOrganisationId = (Integer) ratings.get("vendorOrganisationId");
			} catch(Exception exp) {
				exp.printStackTrace();
			}
			
			// check adding overall rating and review comments API
			ProjectReviewRating projectRRObj = new ProjectReviewRating();
			projectRRObj.setClientId(clientId);
			projectRRObj.setClientOrganisationId(clientOrganisationId);
			projectRRObj.setVendorOrganisationId(vendorOrganisationId);
//			projectRRObj.setProjectId(projectId);// no need only vendor rated here
//			projectRRObj.setVendorId(vendorId); - required when reply to a comment
//			projectRRObj.setReplyComments(replyComments); - required when reply to a client comment
			projectRRObj.setReviewComments(reviewComments);;
			projectRRObj.setRating(overAllRating);
			
//			return projectReviewRatingRepository.setReplyComments(projectReviewRating.getId(), projectReviewRating.getReplyComments());
			ProjectReviewRating projectRR = projectReviewRatingRepository.save(projectRRObj);
			
			// add category ratings
			for(Map<String,Object> rating : vendorCategoryRatings) {
				
				VendorCategoryRatings vendorRating = new VendorCategoryRatings();
				
				Integer ratingCategory = (Integer) rating.get("ratingCategory");
				Float ratingValue = Float.valueOf(String.valueOf(rating.get("rating")));
				
				vendorRating.setReviewRatingId(projectRR.getId());
				vendorRating.setClientId(clientId);
				vendorRating.setClientOrganisationId(clientOrganisationId);
				vendorRating.setProjectId(projectId);				
				vendorRating.setVendorOrganisationId(vendorOrganisationId);
				vendorRating.setRatingCategory(ratingCategory);
				vendorRating.setRating(ratingValue);
				
				VendorCategoryRatings vendorRate = vendorCategoryRatingsRepository.findByClientIdAndClientOrganisationIdAndProjectIdAndRatingCategory(clientId, clientOrganisationId, projectId, ratingCategory);
				
				if(vendorRate != null) {
					vendorRating.setId(vendorRate.getId());
					VendorCategoryRatings rate = vendorCategoryRatingsRepository.save(vendorRating);
				} else {
					VendorCategoryRatings rate = vendorCategoryRatingsRepository.save(vendorRating);
					if(rate == null) {
						return false;
					}
				}
				
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
		
		return true;
	}

	public ClientUser updateClientPrimaryOrganisation(Integer clientUserId, Integer clientOrgId) {
		// TODO Auto-generated method stub
		ClientUser clientUser = clientUserRepository.findByClientId(clientUserId);
		clientUser.setPrimaryOrgId(clientOrgId);
		
		return clientUserRepository.save(clientUser);
	}

	public Object getClientUserByIdAndClientOrgId(Integer id, Integer clientOrgId) {
		// TODO Auto-generated method stub
		
		ClientUser clientUser = clientUserRepository.findByClientId(id);
		
		ObjectMapper oMapper = new ObjectMapper();
        // object -> Map
        Map<String, Object> clientObj = oMapper.convertValue(clientUser, Map.class);
        
        ClientAssociation clientAssociate = clientAssociationRepository.findByClientIdAndClientOrganisationId(id, clientOrgId);
        
        clientObj.put("clientUserType", clientAssociate.getClientUserType());
        clientObj.put("userRole", clientAssociate.getUserRole());
        clientObj.put("userAccountStatus", clientAssociate.getUserAccountStatus());

        return clientObj;
	}

	public Object deleteClientUserById(Integer id, Integer clientOrgId) {
		// TODO Auto-generated method stub
		
		ClientAssociation clientAssociate = clientAssociationRepository.findByClientIdAndClientOrganisationId(id, clientOrgId);
//		clientAssociate.setAccountVerificationStatus(); --> Need to check , is it has changes?
		clientAssociate.setUserAccountStatus(UserAccountStatus.INACTIVE.getValue());
		
		String userInactiveDate = Utils.getDateTime();
		
		clientAssociate.setUserInactiveDate(userInactiveDate);
		
		return clientAssociationRepository.save(clientAssociate);
	}

	public Object saveClientUserRole(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		Integer clientUserId = Integer.parseInt(String.valueOf(requestData.get("clientUserId")));
		String firstName = String.valueOf(requestData.get("firstName"));
		String lastName = String.valueOf(requestData.get("lastName"));
    	Integer clientOrgId = Integer.parseInt(String.valueOf(requestData.get("clientOrgId")));
    	Integer userRole = Integer.parseInt(String.valueOf(requestData.get("userRole")));
    	Integer clientUserType = Integer.parseInt(String.valueOf(requestData.get("clientUserType")));
    	
    	ClientAssociation clientAssociate = clientAssociationRepository.findByClientIdAndClientOrganisationId(clientUserId, clientOrgId);
    	
    	if(clientAssociate != null) {
    	clientAssociate.setClientUserType(clientUserType);
    	clientAssociate.setUserRole(userRole);
    	
    	clientAssociationRepository.save(clientAssociate);
    	
    	ClientUser client = clientUserRepository.findByClientId(clientUserId);
    	
    	client.setFirstName(firstName);
    	client.setLastName(lastName);
    	
    	clientUserRepository.save(client);
    	
    	return clientAssociate;
    	
    	} else 
    		return null;
    	
	}

	//if client user based reviews required
//	public List<Map<String, Object>> getAllClientReviews(Integer clientId, Integer clientOrganisationId) {
//		// TODO Auto-generated method stub
//		
//		List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByClientIdAndClientOrganisationId(clientId, clientOrganisationId);
//		
//		List<Map<String, Object>> clientReviews = new ArrayList();
//		
//		for(ProjectReviewRating projectReviewRating : projectReviewsForVendors) {
//			
//			Integer vendorOrganisationId = projectReviewRating.getVendorOrganisationId();
//			
//			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
//			Project project = projectRepository.findByProjectId(projectReviewRating.getProjectId());
//			
//			Map<String,Object> projectReview = new HashMap();
//			
//			projectReview.put("rating", getVendorCategoryRatingsByClientIdAndClientOrgId(vendorOrganisationId, clientId, clientOrganisationId, projectReviewRating.getProjectId()));
//			projectReview.put("reviewDate", projectReviewRating.getCreatedAt());
//			projectReview.put("vendorOrganisation", vendorOrganisation);
//			projectReview.put("project", project);
//			
//			clientReviews.add(projectReview);
//		}
//		
//		return clientReviews;
//	}
	
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
			projectReview.put("rating", projectReviewRating.getRating());
			projectReview.put("reviewDate", projectReviewRating.getCreatedAt());
			projectReview.put("reviewComments", projectReviewRating.getReviewComments());
			projectReview.put("replyComments", projectReviewRating.getReplyComments());
			projectReview.put("vendorOrganisation", vendorOrganisation);
			projectReview.put("vendorCategoryRating", getVendorCategoryRatings(projectReviewRating.getId()));
			
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
	
	private List<Map<String, Object>> getVendorCategoryRatings(Integer reviewRatingId) {
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
	
	//if client user based reviews required
//	private Double getVendorCategoryRatingsByClientIdAndClientOrgId(Integer vendorOrgId, Integer clientId, Integer clientOrganisationId, Integer projectId) {
//		// TODO Auto-generated method stub
//		
//		List<VendorCategoryRatings> vendorRatings = new ArrayList();
//		
//		if(projectId == null || projectId == 0) {
//			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientIdAndClientOrganisationId(vendorOrgId, clientId, clientOrganisationId);
//		} else {
//			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientIdAndClientOrganisationIdAndProjectId(vendorOrgId, clientId, clientOrganisationId, projectId);
//		}
//		
//        try {
//        	double sumCategoryResponsiveness = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//        	double sumCategoryProfessionalism = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//        	double sumCategoryAccuracy = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//        	double sumCategoryQuality = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//
//        	long responsivenessCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).count();
//        	long professionalismCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).count();
//        	long accuracyCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).count();
//        	long qualityCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).count();
//        	
//        	
//        	double overAllRating = (sumCategoryResponsiveness/responsivenessCount * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100) +
//        			(sumCategoryProfessionalism/responsivenessCount * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100) +
//        			(sumCategoryAccuracy/accuracyCount * Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100) +
//        			(sumCategoryQuality/qualityCount * Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
//        	
//        	return overAllRating;
//        	
//        } catch(Exception exp) {
//        	exp.printStackTrace();
//        	return 0d;
//        }
//	}

//	private Double getVendorCategoryRatingsByClientOrgId(Integer vendorOrgId, Integer clientOrganisationId, Integer projectId) {
//		// TODO Auto-generated method stub
//		
//		List<VendorCategoryRatings> vendorRatings = new ArrayList();
//		
//		if(projectId == null || projectId == 0) {
//			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientOrganisationId(vendorOrgId, clientOrganisationId);
//		} else {
//			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientOrganisationIdAndProjectId(vendorOrgId, clientOrganisationId, projectId);
//		}
//		
//        try {
//        	double sumCategoryResponsiveness = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//        	double sumCategoryProfessionalism = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//        	double sumCategoryAccuracy = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//        	double sumCategoryQuality = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
//
//        	long responsivenessCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).count();
//        	long professionalismCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).count();
//        	long accuracyCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).count();
//        	long qualityCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).count();
//        	
//        	
//        	double overAllRating = (sumCategoryResponsiveness/responsivenessCount * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100) +
//        			(sumCategoryProfessionalism/responsivenessCount * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100) +
//        			(sumCategoryAccuracy/accuracyCount * Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100) +
//        			(sumCategoryQuality/qualityCount * Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
//        	
//        	return overAllRating;
//        	
//        } catch(Exception exp) {
//        	exp.printStackTrace();
//        	return 0d;
//        }
//	}

	public List<Map<String, Object>> getClientRegistrationFiles(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		List<ClientRegistrationFiles> clientRegistrationFiles = clientRegistrationFilesRepository.findAllByClientOrganisationId(clientOrganisationId);
		
		List<Map<String, Object>> files = new ArrayList();
		for(ClientRegistrationFiles registrationFile : clientRegistrationFiles) {
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

	public ClientContract addClientContract(ClientContract clientContract) {
		// TODO Auto-generated method stub
		
		clientContract.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());
		return clientContractRepository.save(clientContract);
	}

	public ClientContract updateClientContract(ClientContract clientContract) {
		// TODO Auto-generated method stub
		try {
			ClientContract clientContractObj = clientContractRepository.findOneById(clientContract.getId());
			
			if(clientContractObj != null) {
				
				clientContractObj.setVendorName(clientContract.getVendorName());
				clientContractObj.setServices(clientContract.getServices());
				clientContractObj.setTerm(clientContract.getTerm());
				clientContractObj.setTermUnits(clientContract.getTermUnits());
				clientContractObj.setSignedDate(clientContract.getSignedDate());
				clientContractObj.setExpiryDate(clientContract.getExpiryDate());
				clientContractObj.setRenewalType(clientContract.getRenewalType());
				clientContractObj.setCost(clientContract.getCost());
				clientContractObj.setCostTermUnits(clientContract.getCostTermUnits());
				clientContractObj.setGstAvailablity(clientContract.getGstAvailablity());
				clientContractObj.setCancellationTerm(clientContract.getCancellationTerm());
				clientContractObj.setCancellationUnits(clientContract.getCancellationUnits());
				clientContractObj.setExpectedIncrease(clientContract.getExpectedIncrease());
				clientContractObj.setNotes(clientContract.getNotes());
				
				
				return clientContractRepository.save(clientContractObj);
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public boolean deleteClientContract(Integer contractId) {
		// TODO Auto-generated method stub
		try {
			ClientContract clientContract = clientContractRepository.findOneById(contractId);
			
			if(clientContract != null) {
				clientContract.setStatus(Constants.UserAccountStatus.INACTIVE.getValue());
				if(clientContractRepository.save(clientContract) != null) {
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

	public List<ClientContract> getClientContracts(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		return clientContractRepository.findAllByClientOrganisationId(clientOrganisationId);
	}

	public ClientTask addClientTask(Map<String, Object> clientTaskData) {
		// TODO Auto-generated method stub
		try {
			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			ClientTask clientTask = mapper.convertValue(clientTaskData.get("clientTask"), ClientTask.class);
			String assignee = (String) clientTaskData.get("assignee");
			
			clientTask.setModifiedBy(clientTask.getCreatedBy());
			clientTask.setStatus(UserAccountStatus.ACTIVE.getValue());
			
			ClientTask clientTaskObj = clientTaskRepository.save(clientTask); 
			
			if(clientTask.getIsOther() == Constants.Availability.AVAILABLE.getValue()) {
				// don't save assignee Ids records
			} else {
				// check and store client Ids assigned to
				if(assignee != null && assignee.length() > 0) {
					List<String> clientUsers = Arrays.asList(assignee.split(","));
					for(String clientUser: clientUsers) {
						ClientUserTasks clientUserTask = new ClientUserTasks();
						clientUserTask.setClientId(Integer.parseInt(clientUser));
						clientUserTask.setTaskId(clientTaskObj.getId());
						
						clientUserTasksRepository.save(clientUserTask);
					}
				}
			}
			return clientTaskObj;
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public ClientTaskComments addClientTaskComments(ClientTaskComments taskComment) {
		// TODO Auto-generated method stub
		return clientTaskCommentsRepository.save(taskComment);
	}

	public List<Map<String, Object>> getClientTasks(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		 List<Map<String, Object>> allClientTasks = new ArrayList();
		 
		 // to get all tasks under all status
		 List<ClientTask> clientTasks = clientTaskRepository.findAllByClientOrganisationId(clientOrganisationId);
		 
		 try {
			 for(ClientTask clientTask : clientTasks) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientTask, Map.class);
			        
			        if(clientTask.getIsOther() == Constants.Availability.AVAILABLE.getValue()) {
			        	// only other name available
			        	map.put("assignedTo", new ArrayList());
			        } else if(clientTask.getIsOther() == Constants.Availability.NOT_AVAILABLE.getValue()) {			        	
			        	map.put("assignedTo", getClientUsers(clientTask.getId()));
//			        	map.put("assignee",getClientUsers(clientTask.getId()));
			        }
			        
			        ClientUser createdBy = clientUserRepository.findByClientId(clientTask.getCreatedBy());
			        ClientUser modifiedBy = clientUserRepository.findByClientId(clientTask.getModifiedBy());
			        
			        map.put("createdByUser",createdBy.getFirstName()+" "+createdBy.getLastName());
			        map.put("modifiedByUser",modifiedBy.getFirstName()+" "+modifiedBy.getLastName());
			        
			        map.put("comments",getTaskComments(clientTask.getId()));
			        
			        allClientTasks.add(map);
				}
			 
			 return allClientTasks;
			 
		 } catch(Exception exp) {
			 exp.printStackTrace();
		 }
		 
		 return null;
	}

	private List<Map<String, Object>> getTaskComments(int taskId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> allClientTaskComments = new ArrayList();
		 
		List<ClientTaskComments> clientTaskComments = clientTaskCommentsRepository.findAllByTaskId(taskId);
		
		for(ClientTaskComments clientTaskComment : clientTaskComments) {
			ObjectMapper oMapper = new ObjectMapper();
//	        Map<String, Object> map = oMapper.convertValue(clientTaskComment, Map.class);
	        Map<String, Object> map = new HashMap();
	        
	        map.put("id", clientTaskComment.getId());
	        map.put("clientId", clientTaskComment.getClientId());
	        ClientUser clientUserObj = clientUserRepository.findByClientId(clientTaskComment.getClientId());
			if(clientUserObj != null)
	        map.put("clientName", clientUserObj.getFirstName() +" "+ clientUserObj.getLastName());
			map.put("comment", clientTaskComment.getComment());
			map.put("createdAt", clientTaskComment.getCreatedAt());
			
	        allClientTaskComments.add(map);
		}
		
		return allClientTaskComments;
	}

	private List<Map<String, Object>> getClientUsers(int taskId) {
		// TODO Auto-generated method stub
		
		 List<Map<String, Object>> allClientUsers = new ArrayList();
		 
		 List<ClientUserTasks> clientUserTasks = clientUserTasksRepository.findAllByTaskId(taskId);
		
		if(clientUserTasks != null && clientUserTasks.size() > 0) {
			for(ClientUserTasks clientUser: clientUserTasks) {
				
				Map<String, Object> map = new HashMap();
				
				ClientUser clientUserObj = clientUserRepository.findByClientId(clientUser.getClientId());
				if(clientUserObj != null) {
					map.put("clientUserId", clientUserObj.getClientId());
					map.put("clientUserName", clientUserObj.getFirstName() +" "+ clientUserObj.getLastName());
					
					allClientUsers.add(map);
				}
			}
		}
		return allClientUsers;
	}

	public ClientTask updateClientTask(Map<String, Object> clientTaskData) {
		// TODO Auto-generated method stub
		try {
			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			ClientTask clientTask = mapper.convertValue(clientTaskData.get("clientTask"), ClientTask.class);
			String assignee = (String) clientTaskData.get("assignee");
			
			ClientTask clientTaskObj = clientTaskRepository.findOneById(clientTask.getId());
			
			clientTaskObj.setClosureDate(clientTask.getClosureDate());
			clientTaskObj.setPriority(clientTask.getPriority());
			clientTaskObj.setTaskDescription(clientTask.getTaskDescription());
			clientTaskObj.setTaskStatus(clientTask.getTaskStatus());
			clientTaskObj.setIsOther(clientTask.getIsOther());
			clientTaskObj.setOthersName(clientTask.getOthersName());;
			
			if(clientTask.getTaskStatus() == TaskStatus.CLOSED.getValue()) {
				clientTaskObj.setClosureDate(Utils.getDateTime());
			}
			
			clientTaskObj = clientTaskRepository.save(clientTaskObj); 
			
			if(clientTask.getIsOther() == Constants.Availability.AVAILABLE.getValue()) {
				// don't save assignee Ids records
				clientUserTasksRepository.deleteByTaskId(clientTaskObj.getId());
			} else {
				// check and store client Ids assigned to
				if(assignee != null && assignee.length() > 0) {
					// delete old assignee and update new
					clientUserTasksRepository.deleteByTaskId(clientTaskObj.getId());
										
					List<String> clientUsers = Arrays.asList(assignee.split(","));
					for(String clientUser: clientUsers) {
						ClientUserTasks clientUserTask = new ClientUserTasks();
						clientUserTask.setClientId(Integer.parseInt(clientUser));
						clientUserTask.setTaskId(clientTaskObj.getId());
						
						clientUserTasksRepository.save(clientUserTask);
					}
				}
			}
			
			return clientTaskObj;
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public ClientTask deleteClientTask(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer taskId = (Integer) requestData.get("id");
		Integer clientUserId = (Integer) requestData.get("clientUserId");
		
		ClientTask clientTaskObj = clientTaskRepository.findOneById(taskId);
		
		clientTaskObj.setStatus(UserAccountStatus.INACTIVE.getValue());
		clientTaskObj.setModifiedBy(clientUserId);
		
		return clientTaskRepository.save(clientTaskObj);
	}

	public ClientBuildingRepository addClientBuildingRepository(ClientBuildingRepository clientBuildingRepository) {
		// TODO Auto-generated method stub
		
		clientBuildingRepository.setStatus(UserAccountStatus.ACTIVE.getValue());
		clientBuildingRepository.setModifiedBy(clientBuildingRepository.getCreatedBy());
		
		return clientBuildingRepoRepository.save(clientBuildingRepository);
	}

	public ClientBuildingRepository deleteClientBuildingRepository(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer taskId = (Integer) requestData.get("id");
		Integer clientUserId = (Integer) requestData.get("clientUserId");
		
		ClientBuildingRepository clientBuildingRepository = clientBuildingRepoRepository.findOneById(taskId);
		
		clientBuildingRepository.setStatus(UserAccountStatus.INACTIVE.getValue());
		clientBuildingRepository.setModifiedBy(clientUserId);
		
		return clientBuildingRepoRepository.save(clientBuildingRepository);
	}
	
	public ClientBuildingRepository updateClientBuildingRepository(ClientBuildingRepository clientBuildingRepository) {
		// TODO Auto-generated method stub
		
		ClientBuildingRepository clientBuildingRepositoryObj = clientBuildingRepoRepository.findOneById(clientBuildingRepository.getId());
		
		clientBuildingRepository.setClientOrganisationId(clientBuildingRepositoryObj.getClientOrganisationId());
		clientBuildingRepository.setCreatedBy(clientBuildingRepositoryObj.getCreatedBy());
		clientBuildingRepository.setStatus(clientBuildingRepositoryObj.getStatus());
		
		return clientBuildingRepoRepository.save(clientBuildingRepository);
	}

	public List<ClientBuildingRepository> getClientBuildingRepositories(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		return clientBuildingRepoRepository.findAllByClientOrganisationId(clientOrganisationId);
	}
}

