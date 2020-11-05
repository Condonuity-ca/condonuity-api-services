package tech.torbay.clientsservice.service;

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

import tech.torbay.clientsservice.Utils.Utils;
import tech.torbay.clientsservice.constants.Constants;
import tech.torbay.clientsservice.constants.Constants.TaskStatus;
import tech.torbay.clientsservice.constants.Constants.UserAccountStatus;
import tech.torbay.clientsservice.constants.Constants.UserType;
import tech.torbay.clientsservice.entity.Amenities;
import tech.torbay.clientsservice.entity.ClientAmenities;
import tech.torbay.clientsservice.entity.ClientAssociation;
import tech.torbay.clientsservice.entity.ClientBuildingRepository;
import tech.torbay.clientsservice.entity.ClientContract;
import tech.torbay.clientsservice.entity.ClientOrganisation;
import tech.torbay.clientsservice.entity.ClientRegistrationFiles;
import tech.torbay.clientsservice.entity.ClientTask;
import tech.torbay.clientsservice.entity.ClientTaskComments;
import tech.torbay.clientsservice.entity.ClientUser;
import tech.torbay.clientsservice.entity.ClientUserTasks;
import tech.torbay.clientsservice.entity.ExternalMessageComment;
import tech.torbay.clientsservice.entity.Notification;
import tech.torbay.clientsservice.entity.NotificationViewsHistory;
import tech.torbay.clientsservice.entity.OrganisationPayment;
import tech.torbay.clientsservice.entity.Project;
import tech.torbay.clientsservice.entity.ProjectAwards;
import tech.torbay.clientsservice.entity.ProjectReviewRating;
import tech.torbay.clientsservice.entity.UserLevelNotification;
import tech.torbay.clientsservice.entity.UserProfileImages;
import tech.torbay.clientsservice.entity.UserWishList;
import tech.torbay.clientsservice.entity.VendorCategoryRatings;
import tech.torbay.clientsservice.entity.VendorOrganisation;
import tech.torbay.clientsservice.entity.VendorUser;
import tech.torbay.clientsservice.repository.AmenitiesRepository;
import tech.torbay.clientsservice.repository.ClientAmenitiesRepository;
import tech.torbay.clientsservice.repository.ClientAssociationRepository;
import tech.torbay.clientsservice.repository.ClientBuildingRepoRepository;
import tech.torbay.clientsservice.repository.ClientContractRepository;
import tech.torbay.clientsservice.repository.ClientOrganisationRepository;
import tech.torbay.clientsservice.repository.ClientRegistrationFilesRepository;
import tech.torbay.clientsservice.repository.ClientTaskCommentsRepository;
import tech.torbay.clientsservice.repository.ClientTaskRepository;
import tech.torbay.clientsservice.repository.ClientUserRepository;
import tech.torbay.clientsservice.repository.ClientUserTasksRepository;
import tech.torbay.clientsservice.repository.ExternalMessageCommentRepository;
import tech.torbay.clientsservice.repository.NotificationRepository;
import tech.torbay.clientsservice.repository.NotificationViewsHistoryRepository;
import tech.torbay.clientsservice.repository.OrganisationPaymentRepository;
import tech.torbay.clientsservice.repository.ProjectAwardsRepository;
import tech.torbay.clientsservice.repository.ProjectRepository;
import tech.torbay.clientsservice.repository.ProjectReviewRatingRepository;
import tech.torbay.clientsservice.repository.UserLevelNotificationRepository;
import tech.torbay.clientsservice.repository.UserProfileImagesRepository;
import tech.torbay.clientsservice.repository.UserWishListRepository;
import tech.torbay.clientsservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.clientsservice.repository.VendorOrganisationRepository;
import tech.torbay.clientsservice.repository.VendorUserRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	ClientAmenitiesRepository clientAmenitiesRepository;
	@Autowired
	AmenitiesRepository amenitiesRepository;
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
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	UserLevelNotificationRepository userLevelNotificationRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	NotificationViewsHistoryRepository notificationViewsHistoryRepository;
	@Autowired
	ExternalMessageCommentRepository externalMessageCommentRepository;
	@Autowired
	ProjectAwardsRepository projectAwardsRepository;

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

	public boolean updateAmenities(Integer clientOrgId, List<ClientAmenities> clientAmenities) {
		// TODO Auto-generated method stub
		try {
			clientAmenitiesRepository.deleteByClientOrganisationId(clientOrgId);
			
			for(ClientAmenities clientAmenity : clientAmenities) {
				
				clientAmenity.setClientOrganisationId(clientOrgId);
				clientAmenitiesRepository.save(clientAmenity);
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Object> getAllClientOrganisations() {
		// TODO Auto-generated method stub
		List<ClientOrganisation> clientOrgsAll = clientOrganisationRepository.findAll();
		
		List<Object> clientOrganisations = new ArrayList();
		
		for(ClientOrganisation clientOrg : clientOrgsAll) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(clientOrg, Map.class);
	        
	        List<Map<String, Object>> amenitiesInfo = getAmenitiesByOrgId(clientOrg.getClientOrganisationId());
	        map.put("amenities", amenitiesInfo);
			clientOrganisations.add(map);
		}
		
    			
		return clientOrganisations;
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
	        
	        List<Map<String, Object>> amenitiesInfo = getAmenitiesByOrgId(clientOrg.getClientOrganisationId());
	        map.put("amenities", amenitiesInfo);
	        
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
			projectRRObj.setStatus(UserAccountStatus.ACTIVE.getValue());
			
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
				VendorCategoryRatings vendorRate = null;
				if(projectId != null) {
					vendorRate = vendorCategoryRatingsRepository.findByClientIdAndClientOrganisationIdAndProjectIdAndRatingCategory(clientId, clientOrganisationId, projectId, ratingCategory);
				}
				
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

	public boolean updateReview(Map<String, Object> ratings) {
		// TODO Auto-generated method stub
		
		try {
			
			final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
			List vendorCategoryRatingsObj = mapper.convertValue(ratings.get("categoryRatings"), List.class);
			
			List<Map<String,Object>> vendorCategoryRatings = (List<Map<String,Object>>) vendorCategoryRatingsObj;
			Integer reviewId = (Integer) ratings.get("reviewId");
			String reviewComments = (String) ratings.get("reviewComments");
			String overAllRating = (String) ratings.get("overAllRating");
			
			
			Integer projectId = null;
			Integer vendorOrganisationId = null;
			
			
			ProjectReviewRating projectRRObj = projectReviewRatingRepository.findOneById(reviewId);
			if(projectRRObj != null) {
				projectRRObj.setReviewComments(reviewComments);;
				projectRRObj.setRating(overAllRating);
				ProjectReviewRating projectRR = projectReviewRatingRepository.save(projectRRObj);
			} else {
				return false;
			}
			// add category ratings
			for(Map<String,Object> rating : vendorCategoryRatings) {
				
				Integer ratingCategory = (Integer) rating.get("ratingCategory");
				Float ratingValue = Float.valueOf(String.valueOf(rating.get("rating")));
				
				VendorCategoryRatings vendorRate = vendorCategoryRatingsRepository.findByReviewRatingIdAndRatingCategory(reviewId, ratingCategory);
				
				if(vendorRate != null) {
					vendorRate.setRatingCategory(ratingCategory);
					vendorRate.setRating(ratingValue);
					vendorRate.setId(vendorRate.getId());
					VendorCategoryRatings rate = vendorCategoryRatingsRepository.save(vendorRate);
				} else {
					return false;
				}
				
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean deleteReview(Integer reviewId) {
		
		try {
			ProjectReviewRating projectRRObj = projectReviewRatingRepository.findOneById(reviewId);
			if(projectRRObj != null) {
				projectRRObj.setStatus(UserAccountStatus.INACTIVE.getValue());
				ProjectReviewRating projectRR = projectReviewRatingRepository.save(projectRRObj);
			} else {
				return false;
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
	
	public List<Map<String, Object>> getAllClientReviews(Integer clientId, Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		
		List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByClientOrganisationIdAndStatus(clientOrganisationId, UserAccountStatus.ACTIVE.getValue());
		
		List<Map<String, Object>> clientReviews = new ArrayList();
		
		for(ProjectReviewRating projectReviewRating : projectReviewsForVendors) {
			
			Integer vendorOrganisationId = projectReviewRating.getVendorOrganisationId();
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
			
			
			Map<String,Object> projectReview = new HashMap();
			
			// no need calculate overall rating, there is a input for overall rating , and we are gonna retrieve all category ratings to so we dont need calculation here, previouly added only because of UI has only one rating view
//			projectReview.put("rating", getVendorCategoryRatingsByClientOrgId(vendorOrganisationId, clientOrganisationId, projectReviewRating.getProjectId()));
			if(clientId.equals(projectReviewRating.getClientId())) {
				projectReview.put("isEditable", true);
			} else {
				projectReview.put("isEditable", false);
			}
			
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

	public List<Map<String,Object>> getClientNotifications(Integer clientId, Integer clientOrganisationId) {
		// TODO Auto-generated method stub
				List<Notification> filteredNotifications = new ArrayList<Notification>();
				
//				List<Notification> notifications = notificationRepository.findAll();
//				List<Notification> notifications = notificationViewsHistoryRepository.findAll();
				
				List<Notification> projectBidsNotifications = notificationRepository.findAllProjectBidsNotifications(clientOrganisationId);
				List<UserLevelNotification> internalMessagesNotifications = userLevelNotificationRepository.findAllInternalMessagesNotifications(clientOrganisationId); 
				List<UserLevelNotification> externalMessagesNotifications = userLevelNotificationRepository.findAllExternalMessagesNotifications(clientOrganisationId); 
			
				List<Integer> externalThreadIds = new ArrayList();
				List<Integer> externalMessageIds = new ArrayList();
						
				for(UserLevelNotification ecternalMessageNotification : externalMessagesNotifications) {
					externalThreadIds.add(ecternalMessageNotification.getNotificationCategoryId());
				}
				if(externalThreadIds!= null && externalThreadIds.size() > 0) {
					List<ExternalMessageComment> externalMessageComments = externalMessageCommentRepository.findAllByThreadId(externalThreadIds);
					for(ExternalMessageComment externalMessageComment : externalMessageComments) {
						externalMessageIds.add(externalMessageComment.getId());
					}
				} 
				if(externalMessageIds != null && externalMessageIds.size() > 0) {
					List<UserLevelNotification> externalMessageCommentsNotifications = userLevelNotificationRepository.findAllExternalMessageCommentsNotifications(externalMessageIds);
					internalMessagesNotifications.addAll(externalMessageCommentsNotifications);
				}
				
				 
				List<UserLevelNotification> taskNotifications = userLevelNotificationRepository.findAllTaskNotifications(clientOrganisationId, clientId); 
				//1.all projects for client notification
				//2.bid end alert
				//3.reply for client comment
				//4.annual contract alert
				//5.account changes
				//6.project question Alert
				//7.bid withdrawn alert
				List<Notification> allPostedProjectsForClientNotifications = notificationRepository.findAllPostedProjectsForClient(clientOrganisationId);
				List<Notification> bidEndAlertNotifications = notificationRepository.findBidEndAlertForProjects(clientOrganisationId);
				List<Notification> annualAlertNotifications = notificationRepository.findAllContractExpiryAlert(clientOrganisationId);
				List<Notification> accountChangesNotifications = notificationRepository.findAllAccountChangesNotifications(clientOrganisationId);
				List<Notification> reviewRepliesFromVendorNotifications = notificationRepository.findAllReviewRepliesNotificationsFromVendors(clientOrganisationId);
				List<Notification> projectQuestionsAlertNotifications = notificationRepository.findAllProjectQuestionsAlertNotifications(clientOrganisationId);
				List<Notification> bidWithdrawnAlertNotifications = notificationRepository.findBidWithdrawnAlertNotifications(clientOrganisationId);
				
				internalMessagesNotifications.addAll(taskNotifications);
				internalMessagesNotifications.addAll(externalMessagesNotifications);
				
				for (UserLevelNotification userLevelNotification : internalMessagesNotifications) {
					Notification notification = new Notification();
					notification.setId(userLevelNotification.getId());
					notification.setNotificationCategoryId(userLevelNotification.getNotificationCategoryId());
					notification.setNotificationCategoryType(userLevelNotification.getNotificationCategoryType());
					notification.setTitle(userLevelNotification.getTitle());
					notification.setDescription(userLevelNotification.getDescription());
					notification.setUserId(userLevelNotification.getFromUserId());
					notification.setUserType(userLevelNotification.getFromUserType());
					notification.setCreatedAt(userLevelNotification.getCreatedAt());
					notification.setModifiedDate(userLevelNotification.getModifiedDate());
					
					filteredNotifications.add(notification);
				}
				
				for(int index = 0; index < bidEndAlertNotifications.size(); index++) {
					if(bidEndAlertNotifications.get(index).getNotificationCategoryType()==26)
						bidEndAlertNotifications.get(index).setDescription(bidEndAlertNotifications.get(index).getDescription().replace("that may interests you will", "is set to"));
					if(bidEndAlertNotifications.get(index).getNotificationCategoryType()==27)
						bidEndAlertNotifications.get(index).setDescription(bidEndAlertNotifications.get(index).getDescription().replace("that may interests you has reached the bidding deadline", "has reached its biding deadline. Contractors will no longer be permitted to submit bids for this project."));
				}
				
				for(int index = 0; index < allPostedProjectsForClientNotifications.size(); index++) {
					Notification notification = allPostedProjectsForClientNotifications.get(index);
					if(notification.getNotificationCategoryType() == Constants.NotificationType.PROJECT_CREATE.getValue()) {
						ClientUser clientUser = clientUserRepository.findByClientId(notification.getUserId());
						Project project = projectRepository.findByProjectId(notification.getNotificationCategoryId());
						String userFirstName = clientUser.getFirstName();
						String userLastName = clientUser.getLastName();
						String userName = userFirstName+" "+userLastName;
						String title = "New project posting";
						String message = "New project posting: User "+userName+" posted a new project in Marketplace: Project "+project.getProjectName()+" (Project ID: "+project.getProjectId()+").";
						allPostedProjectsForClientNotifications.get(index).setTitle(title);
						allPostedProjectsForClientNotifications.get(index).setDescription(message);
					}
				}
				
				filteredNotifications.addAll(projectBidsNotifications);
				filteredNotifications.addAll(allPostedProjectsForClientNotifications);
				filteredNotifications.addAll(bidEndAlertNotifications);
				filteredNotifications.addAll(annualAlertNotifications);
				filteredNotifications.addAll(accountChangesNotifications);
				filteredNotifications.addAll(reviewRepliesFromVendorNotifications);
				filteredNotifications.addAll(projectQuestionsAlertNotifications);
				
				
				List<Notification> uniqueNotifications = filteredNotifications.stream().distinct().collect(Collectors.toList());
				
				List<Map<String,Object>> clientNotifications = new ArrayList();
				
				for(Notification notification : uniqueNotifications) {
					ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
					Map<String,Object> mapNotification = mapper.convertValue(notification, Map.class);
					
					String sendorFirstName = "";
					String sendorLastName = "";
					String sendorOrganisationName = "";
					String sendorLegalCompanyName = "";
					
					if(notification.getUserId() == 0) {
						if(notification.getUserType() == Constants.UserType.CLIENT.getValue() && notification.getOrganisationId() != 0) {
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(notification.getOrganisationId());
							
							sendorOrganisationName = clientOrganisation.getOrganisationName();
							sendorLegalCompanyName = clientOrganisation.getManagementCompany();
						} else if(notification.getUserType() == Constants.UserType.VENDOR.getValue() && notification.getOrganisationId() != 0) {
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(notification.getOrganisationId());
							sendorOrganisationName = vendorOrganisation.getCompanyName();
							sendorLegalCompanyName = vendorOrganisation.getLegalName();
						}
					} else {
						if(notification.getUserType() == Constants.UserType.CLIENT.getValue() && notification.getUserId() != 0) {
							ClientUser clientUser = clientUserRepository.findByClientId(notification.getUserId());
							
							sendorFirstName = clientUser.getFirstName();
							sendorLastName = clientUser.getLastName();
						} else if(notification.getUserType() == Constants.UserType.VENDOR.getValue() && notification.getUserId() != 0) {
							VendorUser vendorUser = vendorUserRepository.findByUserId(notification.getUserId());
							sendorFirstName = vendorUser.getFirstName();
							sendorLastName = vendorUser.getLastName();
						}
					}
					
					mapNotification.put("senderFirstName", sendorFirstName);
					mapNotification.put("senderLastName", sendorLastName);
					mapNotification.put("senderOrganisationName", sendorOrganisationName);
					mapNotification.put("senderLegalCompanyName", sendorLegalCompanyName);
					mapNotification.put("isViewed",false);
					
					List<NotificationViewsHistory> notificationViewsHistoryByOrganisation = notificationViewsHistoryRepository.findByOrganisationIdAndUserIdAndUserTypeAndNotificationId(clientOrganisationId, clientId, UserType.CLIENT.getValue(), notification.getId());
//					List<NotificationViewsHistory> notificationViewsHistoryByUser = notificationViewsHistoryRepository.findByUserIdAndUserTypeAndNotificationId(clientId, UserType.CLIENT.getValue(), notification.getId());
					int count = 0;
					if(notificationViewsHistoryByOrganisation != null) {
						for(NotificationViewsHistory notificationViews : notificationViewsHistoryByOrganisation) {
							if(notificationViews.getNotificationId().equals(notification.getId())) {
								mapNotification.put("isViewed",true);
								count++;
							}
						}
					}
					
//					if(notificationViewsHistoryByUser != null)
//					for(NotificationViewsHistory notificationViews : notificationViewsHistoryByUser) {
//						if(notificationViews.getNotificationId() == notification.getId()) {
//							mapNotification.put("isViewed",true);
//							count++;
//						}
//					}
					clientNotifications.add(mapNotification);
				}
				// Refer - getClientReadNotifications
//				for(Map<String,Object> notification : clientNotifications) {
//					if(String.valueOf(notification.get("isViewed")).equals("false")) {
//						NotificationViewsHistory notificationView = new NotificationViewsHistory();
//						notificationView.setNotificationId(Integer.parseInt(notification.get("id").toString()));
//						notificationView.setOrganisationId(clientOrganisationId);
//						notificationView.setUserId(clientId);
//						notificationView.setUserType(UserType.CLIENT.getValue());
//						
//						notificationViewsHistoryRepository.save(notificationView);
//					}
//				}
				
				return clientNotifications;
	}
	
	public List<Map<String,Object>> getClientReadNotifications(Integer clientId, Integer clientOrganisationId) {
		// TODO Auto-generated method stub
				List<Notification> filteredNotifications = new ArrayList<Notification>();
				
//				List<Notification> notifications = notificationRepository.findAll();
//				List<Notification> notifications = notificationViewsHistoryRepository.findAll();
				
				List<Notification> projectBidsNotifications = notificationRepository.findAllProjectBidsNotifications(clientOrganisationId);
				List<UserLevelNotification> internalMessagesNotifications = userLevelNotificationRepository.findAllInternalMessagesNotifications(clientOrganisationId); 
				List<UserLevelNotification> externalMessagesNotifications = userLevelNotificationRepository.findAllExternalMessagesNotifications(clientOrganisationId); 
				List<UserLevelNotification> taskNotifications = userLevelNotificationRepository.findAllTaskNotifications(clientOrganisationId, clientId); 
				//1.all projects for client notification
				//2.bid end alert
				//3.reply for client comment
				//4.annual contract alert
				//5.account changes
				//6.project question Alert
				List<Notification> allPostedProjectsForClientNotifications = notificationRepository.findAllPostedProjectsForClient(clientOrganisationId);
				List<Notification> bidEndAlertNotifications = notificationRepository.findBidEndAlertForProjects(clientOrganisationId);
				List<Notification> annualAlertNotifications = notificationRepository.findAllContractExpiryAlert(clientOrganisationId);
				List<Notification> accountChangesNotifications = notificationRepository.findAllAccountChangesNotifications(clientOrganisationId);
				List<Notification> reviewRepliesFromVendorNotifications = notificationRepository.findAllReviewRepliesNotificationsFromVendors(clientOrganisationId);
				List<Notification> projectQuestionsAlertNotifications = notificationRepository.findAllProjectQuestionsAlertNotifications(clientOrganisationId);
				
				
				for(int index = 0; index < projectBidsNotifications.size(); index++) {
					if(projectBidsNotifications.get(index).getNotificationCategoryType() == 6) {//BID WON LOSE
						ProjectAwards projectAwards = projectAwardsRepository.findOneById(projectBidsNotifications.get(index).getNotificationCategoryId());
						Project project = projectRepository.findByProjectId(projectAwards.getProjectId());
						VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(projectAwards.getVendorOrganisationId()); 
						String title = "Project award";
						String message = "Project award: Project "+project.getProjectName()+" awarded (Project ID: "+project.getProjectId()+") to Contractor "+vendorOrganisation.getCompanyName();
						projectBidsNotifications.get(index).setTitle(title);
						projectBidsNotifications.get(index).setDescription(message);
					}
				}
				
				internalMessagesNotifications.addAll(taskNotifications);
				internalMessagesNotifications.addAll(externalMessagesNotifications);
				for (UserLevelNotification userLevelNotification : internalMessagesNotifications) {
					Notification notification = new Notification();
					notification.setId(userLevelNotification.getId());
					notification.setNotificationCategoryId(userLevelNotification.getNotificationCategoryId());
					notification.setNotificationCategoryType(userLevelNotification.getNotificationCategoryType());
					notification.setTitle(userLevelNotification.getTitle());
					notification.setDescription(userLevelNotification.getDescription());
					notification.setUserId(userLevelNotification.getFromUserId());
					notification.setUserType(userLevelNotification.getFromUserType());
					notification.setCreatedAt(userLevelNotification.getCreatedAt());
					notification.setModifiedDate(userLevelNotification.getModifiedDate());
					
					filteredNotifications.add(notification);
				}
				
				filteredNotifications.addAll(projectBidsNotifications);
				filteredNotifications.addAll(allPostedProjectsForClientNotifications);
				filteredNotifications.addAll(bidEndAlertNotifications);
				filteredNotifications.addAll(annualAlertNotifications);
				filteredNotifications.addAll(accountChangesNotifications);
				filteredNotifications.addAll(reviewRepliesFromVendorNotifications);
				filteredNotifications.addAll(projectQuestionsAlertNotifications);
				
				
				List<Notification> uniqueNotifications = filteredNotifications.stream().distinct().collect(Collectors.toList());
				
				List<Map<String,Object>> clientNotifications = new ArrayList();
				
				for(Notification notification : uniqueNotifications) {
					ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
					Map<String,Object> mapNotification = mapper.convertValue(notification, Map.class);
					
					String sendorFirstName = "";
					String sendorLastName = "";
					String sendorOrganisationName = "";
					String sendorLegalCompanyName = "";
					
					if(notification.getUserId() == 0) {
						if(notification.getUserType() == Constants.UserType.CLIENT.getValue() && notification.getOrganisationId() != 0) {
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(notification.getOrganisationId());
							
							sendorOrganisationName = clientOrganisation.getOrganisationName();
							sendorLegalCompanyName = clientOrganisation.getManagementCompany();
						} else if(notification.getUserType() == Constants.UserType.VENDOR.getValue() && notification.getOrganisationId() != 0) {
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(notification.getOrganisationId());
							sendorOrganisationName = vendorOrganisation.getCompanyName();
							sendorLegalCompanyName = vendorOrganisation.getLegalName();
						}
					} else {
						if(notification.getUserType() == Constants.UserType.CLIENT.getValue() && notification.getUserId() != 0) {
							ClientUser clientUser = clientUserRepository.findByClientId(notification.getUserId());
							
							sendorFirstName = clientUser.getFirstName();
							sendorLastName = clientUser.getLastName();
						} else if(notification.getUserType() == Constants.UserType.VENDOR.getValue() && notification.getUserId() != 0) {
							VendorUser vendorUser = vendorUserRepository.findByUserId(notification.getUserId());
							sendorFirstName = vendorUser.getFirstName();
							sendorLastName = vendorUser.getLastName();
						}
					}
					
					mapNotification.put("senderFirstName", sendorFirstName);
					mapNotification.put("senderLastName", sendorLastName);
					mapNotification.put("senderOrganisationName", sendorOrganisationName);
					mapNotification.put("senderLegalCompanyName", sendorLegalCompanyName);
					mapNotification.put("isViewed",false);
					
					List<NotificationViewsHistory> notificationViewsHistoryByOrganisation = notificationViewsHistoryRepository.findByOrganisationIdAndUserIdAndUserTypeAndNotificationId(clientOrganisationId, clientId, UserType.CLIENT.getValue(), notification.getId());
//					List<NotificationViewsHistory> notificationViewsHistoryByUser = notificationViewsHistoryRepository.findByUserIdAndUserTypeAndNotificationId(clientId, UserType.CLIENT.getValue(), notification.getId());
					int count = 0;
					if(notificationViewsHistoryByOrganisation != null) {
						for(NotificationViewsHistory notificationViews : notificationViewsHistoryByOrganisation) {
							if(notificationViews.getNotificationId().equals(notification.getId())) {
								mapNotification.put("isViewed",true);
								count++;
							}
						}
					}
					
//					if(notificationViewsHistoryByUser != null)
//					for(NotificationViewsHistory notificationViews : notificationViewsHistoryByUser) {
//						if(notificationViews.getNotificationId() == notification.getId()) {
//							mapNotification.put("isViewed",true);
//							count++;
//						}
//					}
					clientNotifications.add(mapNotification);
				}
				for(Map<String,Object> notification : clientNotifications) {
					if(String.valueOf(notification.get("isViewed")).equals("false")) {
						NotificationViewsHistory notificationView = new NotificationViewsHistory();
						notificationView.setNotificationId(Integer.parseInt(notification.get("id").toString()));
						notificationView.setOrganisationId(clientOrganisationId);
						notificationView.setUserId(clientId);
						notificationView.setUserType(UserType.CLIENT.getValue());
						
						notificationViewsHistoryRepository.save(notificationView);
					}
				}
				
				return clientNotifications;
	}

}

