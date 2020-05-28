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
import tech.torbay.userservice.entity.ClientAssociation;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientTask;
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientUserTasks;
import tech.torbay.userservice.entity.PredefinedTags;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorProjectInterests;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.ClientAssociationRepository;
import tech.torbay.userservice.repository.ClientBuildingRepoRepository;
import tech.torbay.userservice.repository.ClientContractRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientTaskCommentsRepository;
import tech.torbay.userservice.repository.ClientTaskRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.ClientUserTasksRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
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
}
