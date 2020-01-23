package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.userservice.Utils;
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.UserAccountStatus;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientRegistrationFiles;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.OrganisationPayment;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.UserProfileImages;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorInsurance;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.repository.ClientAmenitiesRepository;
import tech.torbay.userservice.repository.ClientAssociationRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientRegistrationFilesRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.OrganisationPaymentRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.UserProfileImagesRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;

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
        
        map.put("profileImageURL",userProfileImage.getFileUrl());
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
	        
	        UserWishList userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(vendorOrgId, Constants.UserType.VENDOR.getValue(), clientOrg.getClientOrganisationId(), Constants.UserType.CLIENT.getValue() );
	        
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
			Integer projectId = (Integer) ratings.get("projectId");
			Integer vendorOrganisationId = (Integer) ratings.get("vendorOrganisationId");
			
			for(Map<String,Object> rating : vendorCategoryRatings) {
				
				VendorCategoryRatings vendorRating = new VendorCategoryRatings();
				
				Integer ratingCategory = (Integer) rating.get("ratingCategory");
				Float ratingValue = Float.valueOf(String.valueOf(rating.get("rating")));
				
				vendorRating.setClientId(clientId);
				vendorRating.setProjectId(projectId);				
				vendorRating.setVendorOrganisationId(vendorOrganisationId);
				vendorRating.setRatingCategory(ratingCategory);
				vendorRating.setRating(ratingValue);
				
				VendorCategoryRatings vendorRate = vendorCategoryRatingsRepository.findByClientIdAndProjectIdAndRatingCategory(clientId, projectId, ratingCategory);
				
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

	public List<Map<String, Object>> getAllClientReviews(Integer clientId) {
		// TODO Auto-generated method stub
		
		List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByClientId(clientId);
		
		List<Map<String, Object>> clientReviews = new ArrayList();
		
		for(ProjectReviewRating projectReviewRating : projectReviewsForVendors) {
			
			Integer vendorOrganisationId = projectReviewRating.getVendorOrganisationId();
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
			Project project = projectRepository.findByProjectId(projectReviewRating.getProjectId());
			
			Map<String,Object> projectReview = new HashMap();
			
			projectReview.put("rating", getVendorCategoryRatingsByClientId(vendorOrganisationId, clientId, projectReviewRating.getProjectId()));
			projectReview.put("reviewDate", projectReviewRating.getCreatedAt());
			projectReview.put("vendorOrganisation", vendorOrganisation);
			projectReview.put("project", project);
			
			clientReviews.add(projectReview);
		}
		
		return clientReviews;
	}
	
	private Double getVendorCategoryRatingsByClientId(Integer vendorOrgId, Integer clientId, Integer projectId) {
		// TODO Auto-generated method stub
		
		List<VendorCategoryRatings> vendorRatings = new ArrayList();
		
		if(projectId == null || projectId == 0) {
			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientId(vendorOrgId, clientId);
		} else {
			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientIdAndProjectId(vendorOrgId, clientId, projectId);
		}
		
        try {
        	double sumCategoryResponsiveness = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryProfessionalism = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryAccuracy = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();
        	double sumCategoryQuality = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).mapToDouble(VendorCategoryRatings::getRating).sum();

        	long responsivenessCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.RESPONSIVENESS.getValue()).count();
        	long professionalismCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.PROFESSIONALISM.getValue()).count();
        	long accuracyCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.ACCURACY.getValue()).count();
        	long qualityCount = vendorRatings.stream().filter(o -> o.getRatingCategory() == Constants.VendorRatingCategory.QUALITY.getValue()).count();
        	
        	
        	double overAllRating = (sumCategoryResponsiveness/responsivenessCount * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100) +
        			(sumCategoryProfessionalism/responsivenessCount * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100) +
        			(sumCategoryAccuracy/accuracyCount * Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100) +
        			(sumCategoryQuality/qualityCount * Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
        	
        	return overAllRating;
        	
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return 0d;
        }
	}

	public List<Map<String, Object>> getClientRegistrationFiles(Integer clientOrganisationId) {
		// TODO Auto-generated method stub
		List<ClientRegistrationFiles> clientRegistrationFiles = clientRegistrationFilesRepository.findAllByClientOrganisationId(clientOrganisationId);
		
		List<Map<String, Object>> files = new ArrayList();
		for(ClientRegistrationFiles registrationFile : clientRegistrationFiles) {
			Map<String, Object> obj = new HashMap<>();
			
			obj.put("id", registrationFile.getId());
			obj.put("fileName", registrationFile.getFileName());
			obj.put("fileType", registrationFile.getFileType());
			obj.put("fileUrl", registrationFile.getFileUrl());
			obj.put("createdAt", registrationFile.getCreatedAt());
			
			files.add(obj);
		}
		
		return files;
	}


}

