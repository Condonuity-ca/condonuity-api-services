package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationPayment;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.repository.ClientAmenitiesRepository;
import tech.torbay.userservice.repository.ClientAssociationRepository;
import tech.torbay.userservice.repository.ClientOrganisationPaymentRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;

@Component
public class ClientService {
	
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	ClientAmenitiesRepository clientAmenitiesRepository;
	@Autowired
	ClientOrganisationPaymentRepository clientOrganisationPaymentRepository;
	@Autowired
	ClientAssociationRepository clientAssociationRepository;
//	@Autowired
//	ClientOrganisationPaymentRepository clientOrganisationPaymentRepository;
	@Autowired
	UserWishListRepository userWishListRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;

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

	public ClientUser getClientUserById(Integer userId) {
		// TODO Auto-generated method stub
		return clientUserRepository.findByClientId(userId);
	}

	public List<ClientOrganisation> getAllCorporateAccounts(Integer clientUserId) {
		
		// TODO Auto-generated method stub
		try {
			
			List<ClientAssociation> clientOrgIds = clientAssociationRepository.findClientOrganisationIdByClientId(clientUserId);
			System.out.print(clientOrgIds.toString());
			
			List<Integer> ids = clientOrgIds.stream()
                    .map(ClientAssociation::getClientOrganisationId).collect(Collectors.toList());
			
//			List<Integer> ids = new ArrayList();
//			ids.add(0);
			List<ClientOrganisation> clientUserOrganisations = clientOrganisationRepository.findByClientOrganisationId(ids );
			
			return clientUserOrganisations;
		
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}

	public ClientUser saveClient(ClientUser client) {
		// TODO Auto-generated method stub
		return clientUserRepository.save(client);
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

	public List<ClientUser> getAllClientsByOrganisation(Integer clientOrgId) {
		// TODO Auto-generated method stub
		try {
			
			List<ClientAssociation> clientIds = clientAssociationRepository.findClientIdByClientOrganisationId(clientOrgId);
			System.out.print(clientIds.toString());
			
			List<Integer> ids = clientIds.stream()
                    .map(ClientAssociation::getClientId).collect(Collectors.toList());
//			List<Integer> ids = new ArrayList();
//			ids.add(0);
			
			List<ClientUser> clientUsers = clientUserRepository.findByClientId(ids);
			
			return clientUsers;
		
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public List<ClientOrganisationPayment> getPaymentBillingDetails(Integer orgId) {
		// TODO Auto-generated method stub
//		return clientOrganisationPaymentRepository.findAllByOrganisationId(orgId);
		List paymentDetails = new ArrayList();
		paymentDetails.add(new ClientOrganisationPayment());
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

	public boolean rateVendorByCategory(List<VendorCategoryRatings> vendorCategoryRatings) {
		// TODO Auto-generated method stub
		
		try {
			for(VendorCategoryRatings vendorRating : vendorCategoryRatings) {
			    
				VendorCategoryRatings rate = vendorCategoryRatingsRepository.save(vendorRating);
				
				if(rate == null) {
					return false;
				}
			}
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
		
		return true;
	}
}

