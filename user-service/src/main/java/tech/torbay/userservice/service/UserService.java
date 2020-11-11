package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.userservice.Utils.Utils;
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.ProjectInterestStatus;
import tech.torbay.userservice.constants.Constants.ProjectPostTo;
import tech.torbay.userservice.constants.Constants.ProjectPostType;
import tech.torbay.userservice.constants.Constants.ThreadType;
import tech.torbay.userservice.constants.Constants.UserAccountStatus;
import tech.torbay.userservice.constants.Constants.UserType;
import tech.torbay.userservice.constants.Constants.VendorRatingCategoryPercentage;
import tech.torbay.userservice.entity.Amenities;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationProfileImages;
import tech.torbay.userservice.entity.ClientTask;
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientUserTasks;
import tech.torbay.userservice.entity.CommentFiles;
import tech.torbay.userservice.entity.ExternalMessage;
import tech.torbay.userservice.entity.ExternalMessageComment;
import tech.torbay.userservice.entity.ExternalMessageOrganisations;
import tech.torbay.userservice.entity.InternalMessage;
import tech.torbay.userservice.entity.InternalMessageComment;
import tech.torbay.userservice.entity.PredefinedTags;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.ServiceCities;
import tech.torbay.userservice.entity.ThreadFiles;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.UserProfileImages;
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
import tech.torbay.userservice.repository.ClientBuildingRepoRepository;
import tech.torbay.userservice.repository.ClientContractRepository;
import tech.torbay.userservice.repository.ClientOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientTaskCommentsRepository;
import tech.torbay.userservice.repository.ClientTaskRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.ClientUserTasksRepository;
import tech.torbay.userservice.repository.CommentFilesRepository;
import tech.torbay.userservice.repository.ExternalMessageCommentRepository;
import tech.torbay.userservice.repository.ExternalMessageOrganisationsRepository;
import tech.torbay.userservice.repository.ExternalMessageRepository;
import tech.torbay.userservice.repository.InternalMessageCommentRepository;
import tech.torbay.userservice.repository.InternalMessageRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.ServiceCitiesRepository;
import tech.torbay.userservice.repository.ThreadFilesRepository;
import tech.torbay.userservice.repository.UserProfileImagesRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorBidRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorProjectInterestsRepository;
import tech.torbay.userservice.repository.VendorTagsRepository;
import tech.torbay.userservice.repository.VendorUserRepository;

@Component
public class UserService {
	
	
	@Autowired
	ClientService clientService;
	
	
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
	@Autowired
	ClientBuildingRepoRepository clientBuildingRepoRepository;
	/*Messages-START*/
	@Autowired
	InternalMessageRepository internalMessageRepository;
	@Autowired
	InternalMessageCommentRepository internalMessageCommentRepository;
	@Autowired
	ThreadFilesRepository threadFilesRepository;
	@Autowired
	CommentFilesRepository commentFilesRepository;
	@Autowired
	ExternalMessageRepository externalMessageRepository;
	@Autowired
	ExternalMessageCommentRepository externalMessageCommentRepository;
	/*Messages-END*/
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	ClientContractRepository clientContractRepository;
	@Autowired
	ProjectReviewRatingRepository projectReviewRatingRepository;
	@Autowired
	ClientTaskRepository clientTaskRepository;
	@Autowired
	ClientTaskCommentsRepository clientTaskCommentsRepository;
	@Autowired
	ClientUserTasksRepository clientUserTasksRepository;
	@Autowired
	VendorBidRepository vendorBidRepository;
	@Autowired
	VendorProjectInterestsRepository vendorProjectInterestsRepository;
	@Autowired
	ClientOrganisationProfileImagesRepository clientOrganisationProfileImagesRepository;
	@Autowired
	VendorOrganisationProfileImagesRepository vendorOrganisationProfileImagesRepository;
	@Autowired
	ExternalMessageOrganisationsRepository externalMessageOrganisationsRepository;
	@Autowired
	UserProfileImagesRepository userProfileImagesRepository;
	@Autowired
	ServiceCitiesRepository servicesCitiesRepository;
	@Autowired
	ClientOrganisationProfileImagesRepository clientOrganisationProfileImageRepository;
	@Autowired
	ClientAmenitiesRepository clientAmenitiesRepository;
	@Autowired
	AmenitiesRepository amenitiesRepository;
	
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(email);
		return user;
	}
	
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

	public List<Map<String, Object>> getClientSearchResults(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
		String actualKeyword = String.valueOf(requestData.get("keyword"));
		Integer clientUserId = Integer.parseInt(String.valueOf(requestData.get("clientUserId")));
		Integer clientOrganisationId = Integer.parseInt(String.valueOf(requestData.get("clientOrganisationId")));
		
		String keyword = "%"+actualKeyword+"%";
		
		List<Map<String, Object>> result = new ArrayList();
		
		List<Integer> projectStatusCodes = new ArrayList();
		switch(searchType) {
			case 1:
				projectStatusCodes.add(ProjectPostType.UNPUBLISHED.getValue());//status = 1 or status = 2 - current projects status
				projectStatusCodes.add(ProjectPostType.PUBLISHED.getValue());
				
				return getProjects(Constants.SearchType.CURRENT_PROJECTS.getValue(), actualKeyword, keyword, clientOrganisationId, projectStatusCodes);
			case 2:{
				projectStatusCodes.add(ProjectPostType.COMPLETED.getValue());//status = 3 or status = 4 - current projects status
				projectStatusCodes.add(ProjectPostType.TERMINATED.getValue());
				
				return getProjects(Constants.SearchType.HISTORY_PROJECTS.getValue(), actualKeyword, keyword, clientOrganisationId, projectStatusCodes);
			}
			case 4:{
				// check contract type
				// check keyword has project tags
				
				projectStatusCodes.add(ProjectPostType.PUBLISHED.getValue());//status = 2 - published projects status
				
				List<Object[]> projects = projectRepository.findAllProjectsForMarketPlaceByKeyword(keyword, projectStatusCodes);
				
				return getProjectsBundleForClientMarketplace(projects);
			}
			case 5:{
				// check keyword has vendor tags				
				List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(keyword);
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
			        result.add(getVendorOrganisationObj(clientOrganisationId, vendorOrg));
				}
		    	
				if(tagContainedVendors != null && tagContainedVendors.size() > 0) {
					for(VendorOrganisation vendorOrg : tagContainedVendors) {
						result.add(getVendorOrganisationObj(clientOrganisationId, vendorOrg));
					}
				}
				
				HashSet<Map<String, Object>> resultSet = new HashSet(result);
				result.clear();
				result.addAll(resultSet);
				
				Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
		            
		            @Override
		            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
		                Integer o1 = (Integer) e1.get("vendorOrganisationId");
		                Integer o2 = (Integer) e2.get("vendorOrganisationId");
		                return o1.compareTo(o2);
		            }
		        };
				 
				Collections.sort(result, valueComparator);
				
				return result;
			}
			case 6:{
				
				List<ClientOrganisation> clientOrganisations = clientOrganisationRepository.findAllByKeyword(keyword);
				for(ClientOrganisation clientOrganisation : clientOrganisations) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientOrganisation, Map.class);
			        
			        if(clientOrganisation.getCity() != null ) {
			        	try {
			        		Integer city = Integer.parseInt(clientOrganisation.getCity());
			        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
			        		map.put("city",serviceCity.getCityName());
			        	} catch(Exception exp) {
			        		map.put("city","");
			        	}
			        	
			        } else {
			        	map.put("city","");
					}
			        List<Map<String, Object>> amenitiesInfo = clientService.getAmenitiesByOrgId(clientOrganisation.getClientOrganisationId());
			        map.put("amenities", amenitiesInfo);
			        map.put("organisationLogo", getOrganisationLogo(clientOrganisation.getClientOrganisationId(), UserType.CLIENT.getValue()));
			        result.add(map);
				}
				
		        
				return result;
			}
			case 7:{
				try {
					List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeyword(clientOrganisationId, Constants.UserType.CLIENT.getValue(), keyword);
					HashSet<InternalMessage> uniqueInternalMessages = new HashSet(internalMessages);// add distinct
					
					for(InternalMessage internalMessage : internalMessages) {
						Map<String,Object> map = new HashMap<>();
						ObjectMapper oMapper = new ObjectMapper();
						map = oMapper.convertValue(internalMessage, Map.class);
						map.remove("userId");	
						map.remove("organisationId");	
						
						Map<String,Object> user = new HashMap<>();
						Map<String,Object> organisation = new HashMap<>();
						
						UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(internalMessage.getUserId(), internalMessage.getUserType());
						
						if(internalMessage.getUserType() == Constants.UserType.CLIENT.getValue()) {
							
							ClientUser clientUser = clientUserRepository.findByClientId(internalMessage.getUserId());
							user.put("userId",clientUser.getClientId());
							user.put("firstName",clientUser.getFirstName());
							user.put("lastName",clientUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(internalMessage.getOrganisationId());
							organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
							organisation.put("managementCompany",clientOrganisation.getManagementCompany());
							organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
							organisation.put("organisationName",clientOrganisation.getOrganisationName());
							organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
							
						} else if(internalMessage.getUserType() == Constants.UserType.VENDOR.getValue()) {
							
							VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessage.getUserId());
							user.put("userId",vendorUser.getUserId());
							user.put("firstName",vendorUser.getFirstName());
							user.put("lastName",vendorUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(internalMessage.getOrganisationId());
							organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
							organisation.put("legalName",vendorOrganisation.getLegalName());
							organisation.put("organisationName",vendorOrganisation.getCompanyName());
							organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
							organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
						}
						
						
						map.put("user",user);
						map.put("organisation",organisation);
						
						List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
						List<Map<String,Object>> allFiles = new ArrayList();
						for(ThreadFiles threadFile : threadFiles) {
							Map<String,Object> file = new HashMap<>();
							file.put("id", threadFile.getId());
							file.put("fileName", threadFile.getFileName());
							file.put("fileType", threadFile.getFileType());
							file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
							file.put("blobName", threadFile.getBlobName());
							file.put("containerName", threadFile.getContainerName());
//							file.put("fileUrl", commentFile.getFileUrl());
							file.put("createdAt", threadFile.getCreatedAt());
//							file.put("modifiedDate", commentFile.getModifiedDate());
							allFiles.add(file);
						}
						ClientUser createdBy = clientUserRepository.findByClientId(internalMessage.getUserId());
						 
						map.put("createdByUser", createdBy.getFirstName()+" "+createdBy.getLastName()); // this created by user search is missing
						map.put("files",allFiles);
						map.put("comments",getInternalThreadComments(internalMessage.getId(), UserType.CLIENT.getValue(), clientUserId));
						
						result.add(map);
					}
				} catch(Exception exp) {
					return null;
				}
				
				return result;
			}
			case 8:{ 
				// - changes
				// 1. add username based search both source , target - completed as well messages - ?
				// 2. add user, org details both source and target - completed as well messages - ?
				try {
					List<ExternalMessage> externalMessages = externalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeyword(clientOrganisationId, Constants.UserType.CLIENT.getValue(), keyword);
					HashSet<ExternalMessage> uniqueExternalMessages = new HashSet(externalMessages);// add distinct
					
					for(ExternalMessage externalMessage : uniqueExternalMessages) {
						
						Map<String,Object> map = new HashMap<>();
						ObjectMapper oMapper = new ObjectMapper();
						map = oMapper.convertValue(externalMessage, Map.class);
						map.remove("sourceOrganisationId");	
						map.remove("sourceUserId");	
						map.remove("targetOrganisationId");
						map.remove("targetUserType");
						
						Map<String,Object> user = new HashMap<>();
						Map<String,Object> organisation = new HashMap<>();
						
						UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessage.getSourceUserId(), externalMessage.getSourceUserType());
						
						if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
							
							ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
							user.put("userId",clientUser.getClientId());
							user.put("firstName",clientUser.getFirstName());
							user.put("lastName",clientUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
							organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
							organisation.put("managementCompany",clientOrganisation.getManagementCompany());
							organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
							organisation.put("organisationName",clientOrganisation.getOrganisationName());
							organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
							
						} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
							
							VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
							user.put("userId",vendorUser.getUserId());
							user.put("firstName",vendorUser.getFirstName());
							user.put("lastName",vendorUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
							organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
							organisation.put("legalName",vendorOrganisation.getLegalName());
							organisation.put("organisationName",vendorOrganisation.getCompanyName());
							organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
							organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
						}
						
						
						map.put("fromUser",user);
						map.put("fromOrganisation",organisation);
						
						map.put("targetOrganisations",getExternalMessageTargetOrganisations(externalMessage.getId()));
						
						List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(externalMessage.getId(), ThreadType.EXTERNAL.getValue());
						List<Map<String,Object>> allFiles = new ArrayList();
						for(ThreadFiles threadFile : threadFiles) {
							Map<String,Object> file = new HashMap<>();
							file.put("id", threadFile.getId());
							file.put("fileName", threadFile.getFileName());
							file.put("fileType", threadFile.getFileType());
							file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
							file.put("blobName", threadFile.getBlobName());
							file.put("containerName", threadFile.getContainerName());
//							file.put("fileUrl", threadFile.getFileUrl());
							file.put("createdAt", threadFile.getCreatedAt());
//							file.put("modifiedDate", threadFile.getModifiedDate());
							allFiles.add(file);
						}
						
						
						/*if okay use SQL Result Set mapping query here when require optimization*/
						if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
							ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
							
							map.put("createdByUser",clientUser.getFirstName() +" "+ clientUser.getLastName());
							map.put("sourceOrganisationName",clientOrganisation.getOrganisationName());
							
						} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
							VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
							
							map.put("createdByUser",vendorUser.getFirstName() +" "+ vendorUser.getLastName());
							map.put("sourceOrganisationName",vendorOrganisation.getCompanyName());
						}
						
						
						map.put("files",allFiles);
						map.put("comments",getExternalThreadComments(externalMessage.getId(), UserType.CLIENT.getValue(), clientUserId));
						
						result.add(map);
					}
				} catch(Exception exp) {
					return null;
				}
				
				return result;
			}
			case 9:{
				List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByClientIdAndClientOrganisationIdAndKeyword(clientUserId, clientOrganisationId, keyword);
				
				for(ProjectReviewRating projectReviewRating : projectReviewsForVendors) {
					
					Integer vendorOrganisationId = projectReviewRating.getVendorOrganisationId();
					
					VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorOrganisationId);
					Project project = projectRepository.findByProjectId(projectReviewRating.getProjectId());
					
					Map<String,Object> projectReview = new HashMap();
					
					projectReview.put("rating", getVendorCategoryRatingsByClientIdAndClientOrgId(vendorOrganisationId, clientUserId, clientOrganisationId, projectReviewRating.getProjectId()));
					projectReview.put("reviewComments", projectReviewRating.getReviewComments());
					projectReview.put("replyComments", projectReviewRating.getReplyComments());
					projectReview.put("reviewDate", projectReviewRating.getCreatedAt());
					projectReview.put("vendorOrganisation", vendorOrganisation);
					projectReview.put("project", project);
					
					result.add(projectReview);
				}
				return result;
			}
			case 10:{
				List<ClientTask> clientTasks = clientTaskRepository.findAllByClientOrganisationIdAndKeyword(clientOrganisationId, keyword);
				HashSet<ClientTask> clientTasksSet = new HashSet(clientTasks);// no need added distinct
				
				List<ClientTask> clientTasksByPriorityAndStatus = getTasksByStatusAndPriority(clientOrganisationId, actualKeyword);
				
				 try {
					 for(ClientTask clientTask : clientTasksSet) {
					        Map<String, Object> map = getClientTask(clientTask);
					        result.add(map);
						}
					 
					 for(ClientTask clientTask : clientTasksByPriorityAndStatus) {
					        Map<String, Object> map = getClientTask(clientTask);
					        result.add(map);
						}
					 
					 
					 	HashSet<Map<String, Object>> resultSet = new HashSet(result);
						result.clear();
						result.addAll(resultSet);
						
			             
						Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
				            
				            @Override
				            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
				                Integer o1 = (Integer) e1.get("id");
				                Integer o2 = (Integer) e2.get("id");
				                return o1.compareTo(o2);
				            }
				        };
						 
						Collections.sort(result, valueComparator);
					 
				 } catch(Exception exp) {
					 exp.printStackTrace();
				 }
				return result;
			}
			case 11:{
				List<ClientContract> allConstantBasedContracts = getAllConstantsBasedContractsSearchResults(clientOrganisationId, actualKeyword);
				HashSet<ClientContract> allConstantBasedContractsSet = new HashSet(allConstantBasedContracts);
				
				List<ClientContract> clientContracts = clientContractRepository.findAllByClientOrganisationIdAndKeyword(clientOrganisationId, keyword);
				for(ClientContract clientContract : clientContracts) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientContract, Map.class);
			        result.add(map);
				}
				// term_units, cancellation, gst, renewal, cost_term
				
				for(ClientContract clientContract : allConstantBasedContractsSet) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientContract, Map.class);
			        result.add(map);
				}
				
				HashSet<Map<String, Object>> resultSet = new HashSet(result);
				result.clear();
				result.addAll(resultSet);
				
	             
				Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
		            
		            @Override
		            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
		                Integer o1 = (Integer) e1.get("id");
		                Integer o2 = (Integer) e2.get("id");
		                return o1.compareTo(o2);
		            }
		        };
				 
				Collections.sort(result, valueComparator);
				
				return result;
			}
			case 12:{
				// check clientId - createdBy, ModifiedBy - completed using query
				// check tenant_status, person_tenant_type, unit_type, lien_type
				List<ClientBuildingRepository> allTypeBasedBuildingRepositories = getAllTypeBasedRepositorySearchResults(clientOrganisationId, actualKeyword);
				HashSet<ClientBuildingRepository> allTypeBasedBuildingRepositoriesSet = new HashSet(allTypeBasedBuildingRepositories);
				 
				List<ClientBuildingRepository> buildingRepositories = clientBuildingRepoRepository.findAllWithInnerJoinByKeyword(clientOrganisationId, keyword);
				for(ClientBuildingRepository buildingRepository : buildingRepositories) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(buildingRepository, Map.class);
			        result.add(map);
				}
				
				for(ClientBuildingRepository buildingRepository : allTypeBasedBuildingRepositoriesSet) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(buildingRepository, Map.class);
			        result.add(map);
				}
				
				HashSet<Map<String, Object>> resultSet = new HashSet(result);
				result.clear();
				result.addAll(resultSet);
				
	             
				Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
		            
		            @Override
		            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
		                Integer o1 = (Integer) e1.get("id");
		                Integer o2 = (Integer) e2.get("id");
		                return o1.compareTo(o2);
		            }
		        };
				 
				Collections.sort(result, valueComparator);
				 
				return result;
			}
		}
		
		return null;
	}
	
	private List<Map<String, Object>> getProjects(int searchType, String actualKeyword, String keyword, Integer clientOrganisationId, List<Integer> projectStatusCodes) {
		List<Map<String, Object>> result = new ArrayList();
		
		List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(keyword);
		ArrayList<Project> tagContainedProjects = new ArrayList();
		if(tags != null && tags.size() > 0) {
			for(PredefinedTags tag : tags) {
				List<Project> filteredProjects = new ArrayList();
				List<Project> projects = projectRepository.findAllByTagKeyword(clientOrganisationId, String.valueOf("%"+tag.getTagId()+"%"), projectStatusCodes);
				for(Project project : projects) {
					try {
					List<String> tagIds = Arrays.asList(project.getTags().split(","));
					for(String tagId : tagIds) {
						if(Integer.parseInt(tagId) == tag.getTagId()) {
							filteredProjects.add(project);
						}
					}
					} catch(Exception exp) {
						exp.printStackTrace();
					}
				}
				tagContainedProjects.addAll(new ArrayList<>(filteredProjects));
			}
		}
		List<Project> projects = new ArrayList();
		switch(searchType) {
			case 1:{
				projects = projectRepository.findAllCurrentByKeyword(clientOrganisationId, keyword, projectStatusCodes);
				break;
			}
			case 2:{
				projects = projectRepository.findAllHistoryByKeyword(clientOrganisationId, keyword, projectStatusCodes);
				break;
			}
			case 4:{
				projects = projectRepository.findAllMarketplaceByKeyword(keyword, projectStatusCodes);
				break;
			}
		}
		
		for(Project project : projects) {
			result.add(getProjectObject(project));
		}
		
		if(tagContainedProjects != null && tagContainedProjects.size() > 0) {
			for(Project project : tagContainedProjects) {
				result.add(getProjectObject(project));
			}
		}
		
		HashSet<Map<String, Object>> resultSet = new HashSet(result);
		result.clear();
		result.addAll(resultSet);
		
		Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
            
            @Override
            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
                Integer o1 = (Integer) e1.get("projectId");
                Integer o2 = (Integer) e2.get("projectId");
                return o1.compareTo(o2);
            }
        };
        
        Collections.sort(result, valueComparator);
        
		return result;
	}
	
	private List<Map<String,Object>> getExternalMessageTargetOrganisations(Integer externalMessageId) {
		// TODO Auto-generated method stub
		List<ExternalMessageOrganisations> TargetOrgs = externalMessageOrganisationsRepository.findAllByExternalMessageId(externalMessageId);
		
		List<Map<String,Object>> targetOrganisations = new ArrayList<>();
		
		for(ExternalMessageOrganisations extTargetOrg : TargetOrgs) {
			
			Map<String,Object> targetOrg = new HashMap<>();
			
			targetOrg.put("userType",extTargetOrg.getTargetUserType());
			
			if(extTargetOrg.getTargetUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(extTargetOrg.getTargetOrganisationId());
				targetOrg.put("organisationId",clientOrganisation.getClientOrganisationId());
				targetOrg.put("managementCompany",clientOrganisation.getManagementCompany());
				targetOrg.put("corporateNumber",clientOrganisation.getCorporateNumber());
				targetOrg.put("organisationName",clientOrganisation.getOrganisationName());
				targetOrg.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
				
			} else if(extTargetOrg.getTargetUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(extTargetOrg.getTargetOrganisationId());
				targetOrg.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				targetOrg.put("legalName",vendorOrganisation.getLegalName());
				targetOrg.put("organisationName",vendorOrganisation.getCompanyName());
				targetOrg.put("organisationLogoName",vendorOrganisation.getLogoName());
				targetOrg.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
			}
			
			targetOrganisations.add(targetOrg);
		}
		
		return targetOrganisations;
	}

	public String getClientOrganisationLogo(Integer id) {
		// TODO Auto-generated method stub
		ClientOrganisationProfileImages clientOrganisationProfileImages = clientOrganisationProfileImagesRepository.findByClientOrganisationId(id);
		
        if(clientOrganisationProfileImages != null)
        	return clientOrganisationProfileImages.getFileUrl();
        else
        	return null;
	}
	
	public String getVendorOrganisationLogo(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		VendorOrganisationProfileImages vendorOrgProfileImage =  vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(vendorOrganisationId);
		
        if(vendorOrgProfileImage != null)
        	return vendorOrgProfileImage.getFileUrl();
        else
        	return null;
	} 

	private Map<String, Object> getClientTask(ClientTask clientTask) {
		// TODO Auto-generated method stub
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(clientTask, Map.class);
        
        //old
//        if(clientTask.getIsOther() == Constants.Availability.AVAILABLE.getValue()) {
//        	// only other name available
//        	map.put("assignedTo", new ArrayList());
//        } else if(clientTask.getIsOther() == Constants.Availability.NOT_AVAILABLE.getValue()) {			        	
//        	map.put("assignedTo", getClientUsers(clientTask.getId()));
////        	map.put("assignee",getClientUsers(clientTask.getId()));
//        }
//        
//        ClientUser createdBy = clientUserRepository.findByClientId(clientTask.getCreatedBy());
//        ClientUser modifiedBy = clientUserRepository.findByClientId(clientTask.getModifiedBy());
//        
//        map.put("createdByUser",createdBy.getFirstName()+" "+createdBy.getLastName());
//        map.put("modifiedByUser",modifiedBy.getFirstName()+" "+modifiedBy.getLastName());
//        
//        map.put("comments",getTaskComments(clientTask.getId()));
        //new
        List<Map<String, Object>> assignedClientUsers =new ArrayList();
        if(clientTask.getIsOther() == Constants.TaskUsers.BOTH_USER_AND_NONUSER.getValue() || clientTask.getIsOther() == Constants.TaskUsers.OTHER_USER_ONLY.getValue()) {
        	// only other name available
//        	map.put("assignedTo", new ArrayList());
        	String othersNames = clientTask.getOthersName();
        	List<String> othersUsers = Arrays.asList(othersNames.split(","));
        	List<Map<String, Object>> otherClientUsers =new ArrayList();
        	for(String othersUser: othersUsers) {
				
				Map<String, Object> other = new HashMap();
				other.put("clientUserId", "");
				other.put("clientUserName", othersUser);
				otherClientUsers.add(other);
			}
        	assignedClientUsers.addAll(otherClientUsers);
        } //need to check both cases
        if(clientTask.getIsOther() == Constants.TaskUsers.BOTH_USER_AND_NONUSER.getValue() || clientTask.getIsOther() == Constants.TaskUsers.CLIENT_USER_ONLY.getValue()) {			        	
//        	map.put("assignedTo", getClientUsers(clientTask.getId()));
        	assignedClientUsers.addAll(getClientUsers(clientTask.getId()));
        }
        
        map.put("assignedTo", assignedClientUsers);
        ClientUser createdBy = clientUserRepository.findByClientId(clientTask.getCreatedBy());
        ClientUser modifiedBy = clientUserRepository.findByClientId(clientTask.getModifiedBy());
        
        map.put("createdByUser",createdBy.getFirstName()+" "+createdBy.getLastName());
        map.put("modifiedByUser",modifiedBy.getFirstName()+" "+modifiedBy.getLastName());
        
        map.put("comments",getTaskComments(clientTask.getId()));
        map.remove("isOther");
        map.remove("othersName");
        
        return map;
	}

	private List<ClientTask> getTasksByStatusAndPriority(Integer clientOrganisationId, String keyword) {
		// TODO Auto-generated method stub
		List<ClientTask> allTasksByStatusAndPriority =  new ArrayList();
		
		switch(keyword.toLowerCase()) {
			case "highest":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndPriority(clientOrganisationId,Constants.Priority.HIGHEST.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "high":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndPriority(clientOrganisationId,Constants.Priority.HIGHEST.getValue());
				List<ClientTask> tasks1 = clientTaskRepository.findAllByClientOrganisationIdAndPriority(clientOrganisationId,Constants.Priority.HIGH.getValue());
				
				allTasksByStatusAndPriority.addAll(tasks);
				allTasksByStatusAndPriority.addAll(tasks1);
				break;
			}
			case "medium":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndPriority(clientOrganisationId,Constants.Priority.MEDIUM.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "low":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndPriority(clientOrganisationId,Constants.Priority.LOW.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "open":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.OPEN.getValue());
				List<ClientTask> tasks1 = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.RE_OPENED.getValue());
				
				allTasksByStatusAndPriority.addAll(tasks);
				allTasksByStatusAndPriority.addAll(tasks1);
				break;
			}
			case "deferred":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.DEFERRED.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "hold":
			case "on hold":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.ON_HOLD.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "progress":
			case "in progress":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.IN_PROGRESS.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "closed":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.CLOSED.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
			case "opened":
			case "re opened":
			{
				List<ClientTask> tasks = clientTaskRepository.findAllByClientOrganisationIdAndTaskStatus(clientOrganisationId,Constants.TaskStatus.RE_OPENED.getValue());
				allTasksByStatusAndPriority.addAll(tasks);
				break;
			}
		}
		
		return allTasksByStatusAndPriority;
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
	
	private Double getVendorCategoryRatingsByClientIdAndClientOrgId(Integer vendorOrgId, Integer clientId, Integer clientOrganisationId, Integer projectId) {
		// TODO Auto-generated method stub
		
		List<VendorCategoryRatings> vendorRatings = new ArrayList();
		
		if(projectId == null || projectId == 0) {
			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientIdAndClientOrganisationId(vendorOrgId, clientId, clientOrganisationId);
		} else {
			vendorRatings = vendorCategoryRatingsRepository.findByVendorOrganisationIdAndClientIdAndClientOrganisationIdAndProjectId(vendorOrgId, clientId, clientOrganisationId, projectId);
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
	
	private List<ClientContract> getAllConstantsBasedContractsSearchResults(Integer clientOrganisationId, String keyword) {
		// TODO Auto-generated method stub
		
		List<ClientContract> allTypeConstantsBasedContracts =  new ArrayList();
		
		switch(keyword.toLowerCase()) {
			case "auto":
			case "auto renewal":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndRenewalType(clientOrganisationId, Constants.RenewalType.AUTO.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "manual":
			case "manual renewal":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndRenewalType(clientOrganisationId, Constants.RenewalType.MANUAL.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "yes":
			case "including gst":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndGstAvailablity(clientOrganisationId, Constants.Availability.AVAILABLE.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "no":
			case "without gst":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndGstAvailablity(clientOrganisationId, Constants.Availability.NOT_AVAILABLE.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "weekly":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCostTermUnits(clientOrganisationId, Constants.CostTermUnits.WEEKLY.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "monthly":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCostTermUnits(clientOrganisationId, Constants.CostTermUnits.MONTHLY.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "yearly":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCostTermUnits(clientOrganisationId, Constants.CostTermUnits.YEARLY.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "year":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCancellationUnits(clientOrganisationId, Constants.CostTermUnits.YEARLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByClientOrganisationIdAndTermUnits(clientOrganisationId, Constants.CostTermUnits.YEARLY.getValue());
				List<ClientContract> contracts2 = clientContractRepository.findAllByClientOrganisationIdAndCostTermUnits(clientOrganisationId, Constants.CostTermUnits.YEARLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				allTypeConstantsBasedContracts.addAll(contracts2);
				break;
			}
			case "month":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCancellationUnits(clientOrganisationId, Constants.CostTermUnits.MONTHLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByClientOrganisationIdAndTermUnits(clientOrganisationId, Constants.CostTermUnits.MONTHLY.getValue());
				List<ClientContract> contracts2 = clientContractRepository.findAllByClientOrganisationIdAndCostTermUnits(clientOrganisationId, Constants.CostTermUnits.MONTHLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				allTypeConstantsBasedContracts.addAll(contracts2);
				break;
			}
			case "week":
			{
				
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCostTermUnits(clientOrganisationId, Constants.CostTermUnits.WEEKLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "years":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCancellationUnits(clientOrganisationId, Constants.CostTermUnits.YEARLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByClientOrganisationIdAndTermUnits(clientOrganisationId, Constants.CostTermUnits.YEARLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				break;
			}
			case "months":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCancellationUnits(clientOrganisationId, Constants.CostTermUnits.MONTHLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByClientOrganisationIdAndTermUnits(clientOrganisationId, Constants.CostTermUnits.MONTHLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				break;
			}
			case "day":
			case "days":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByClientOrganisationIdAndCancellationUnits(clientOrganisationId, Constants.TermUnits.DAYS.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByClientOrganisationIdAndTermUnits(clientOrganisationId, Constants.TermUnits.DAYS.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				break;
			}
		}
		
		return allTypeConstantsBasedContracts;
	}
	
	private List<Map<String,Object>> getExternalThreadComments(Integer threadId, Integer userType, Integer userId) {
		// TODO Auto-generated method stub
		List<ExternalMessageComment> externalMessageComments = externalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(ExternalMessageComment externalMessageComment : externalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessageComment, Map.class);
			map.remove("userId");
			
			Map<String,Object> user = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessageComment.getUserId(), externalMessageComment.getUserType());
			
			if(externalMessageComment.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(externalMessageComment.getUserId());
				if(userId == clientUser.getClientId() && userType == externalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessageComment.getOrganisationId());
				user.put("organisationId",clientOrganisation.getClientOrganisationId());
				user.put("organisationName",clientOrganisation.getOrganisationName());
				
				
			} else if(externalMessageComment.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessageComment.getUserId());
				if(userId == vendorUser.getUserId() && userType == externalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessageComment.getOrganisationId());
				user.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				user.put("organisationName",vendorOrganisation.getCompanyName());
				
			}
			map.put("user",user);
			
			List<CommentFiles> commentFiles = commentFilesRepository.findAllByCommentIdAndThreadType(externalMessageComment.getId(), ThreadType.EXTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(CommentFiles commentFile : commentFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", commentFile.getId());
				file.put("fileName", commentFile.getFileName());
				file.put("fileType", commentFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(commentFile.getFileSize())));
				file.put("blobName", commentFile.getBlobName());
				file.put("containerName", commentFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", commentFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			map.put("files","[]"/*allFiles*/);
			allComments.add(map);
		}
		return allComments;
	}
	
	private List<Map<String,Object>> getInternalThreadComments(Integer threadId, Integer userType, Integer userId) {
		// TODO Auto-generated method stub
		List<InternalMessageComment> internalMessageComments = internalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(InternalMessageComment internalMessageComment : internalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessageComment, Map.class);
			map.remove("userId");	
			
			Map<String,Object> user = new HashMap<>();
//			Map<String,Object> organisation = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(internalMessageComment.getUserId(), internalMessageComment.getUserType());
			
			if(internalMessageComment.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(internalMessageComment.getUserId());
				if(userId == clientUser.getClientId() && userType == internalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
			} else if(internalMessageComment.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessageComment.getUserId());
				if(userId == vendorUser.getUserId() && userType == internalMessageComment.getUserType())
					user.put("isCommented",true);
				else
					user.put("isCommented", false);
				
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
			}
			
			
			map.put("user",user);
//			map.put("organisation",organisation);
			
			List<CommentFiles> commentFiles = commentFilesRepository.findAllByCommentIdAndThreadType(internalMessageComment.getId(), ThreadType.INTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(CommentFiles commentFile : commentFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", commentFile.getId());
				file.put("fileName", commentFile.getFileName());
				file.put("fileType", commentFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(commentFile.getFileSize())));
				file.put("blobName", commentFile.getBlobName());
				file.put("containerName", commentFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", commentFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			map.put("files",allFiles);
			allComments.add(map);
		}
		return allComments;
	}

	private List<ClientBuildingRepository> getAllTypeBasedRepositorySearchResults(Integer clientOrganisationId, String keyword) {
		// TODO Auto-generated method stub
		
		List<ClientBuildingRepository> allTypeBasedBuildingRepositories =  new ArrayList();
		
		switch(keyword.toLowerCase()) {
			case "occupied":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByClientOrganisationIdAndTenantStatus(clientOrganisationId, Constants.TenantStatus.OWNER_OCCUPIED.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				break;
			}
			case "owner":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByClientOrganisationIdAndTenantStatus(clientOrganisationId, Constants.TenantStatus.OWNER_OCCUPIED.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByClientOrganisationIdAndPersonTenantType(clientOrganisationId, Constants.PersonTenantType.OWNER.getValue());
				
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				break;
			}
			case "vacant":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByClientOrganisationIdAndTenantStatus(clientOrganisationId, Constants.TenantStatus.VACANT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				break;
			}
			
			case "leased":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByClientOrganisationIdAndTenantStatus(clientOrganisationId, Constants.TenantStatus.LEASED.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				break;
			}
			case "other":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByClientOrganisationIdAndTenantStatus(clientOrganisationId, Constants.TenantStatus.OTHER.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByClientOrganisationIdAndPersonTenantType(clientOrganisationId, Constants.PersonTenantType.OTHER.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByLien = clientBuildingRepoRepository.findAllByClientOrganisationIdAndLienType(clientOrganisationId, Constants.LienType.OTHER.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByClientOrganisationIdAndUnitType(clientOrganisationId, Constants.UnitType.OTHER.getValue());
				
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByLien);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "occupant":
			{
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByClientOrganisationIdAndPersonTenantType(clientOrganisationId, Constants.PersonTenantType.OCCUPANT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				break;
			}
			case "tenant":
			{
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByClientOrganisationIdAndPersonTenantType(clientOrganisationId, Constants.PersonTenantType.TENANT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				break;
			}
			case "yes":
			{
				List<ClientBuildingRepository> buildingRepositoriesByLien = clientBuildingRepoRepository.findAllByClientOrganisationIdAndLienType(clientOrganisationId, Constants.LienType.YES.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByLien);
				break;
			}
			case "no":
			{
				List<ClientBuildingRepository> buildingRepositoriesByLien = clientBuildingRepoRepository.findAllByClientOrganisationIdAndLienType(clientOrganisationId, Constants.LienType.NO.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByLien);
				break;
			}
			case "locker":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByClientOrganisationIdAndUnitType(clientOrganisationId, Constants.UnitType.LOCKER.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "residential":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByClientOrganisationIdAndUnitType(clientOrganisationId, Constants.UnitType.RESIDENTIAL_UNIT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "parking":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByClientOrganisationIdAndUnitType(clientOrganisationId, Constants.UnitType.PARKING_UNIT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "commercial":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByClientOrganisationIdAndUnitType(clientOrganisationId, Constants.UnitType.COMMERCIAL_UNIT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "common":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByClientOrganisationIdAndUnitType(clientOrganisationId, Constants.UnitType.COMMON_ELEMENT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
		}
		
		return allTypeBasedBuildingRepositories;
	}

	private Map<String, Object> getVendorOrganisationObj(Integer clientOrganisationId, VendorOrganisation vendorOrg) {
		// TODO Auto-generated method stub
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
        map.put("isPreferred", "false");
        
        UserWishList userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(clientOrganisationId, Constants.UserType.CLIENT.getValue(), vendorOrg.getVendorOrganisationId(), Constants.UserType.VENDOR.getValue() );
        
        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
        } else {
        	map.put("vendorTags","");
        }
        map.put("city",getCityName(vendorOrg.getCity()));
        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
        if(userWish != null && userWish.getInterestStatus() == ProjectInterestStatus.LIKE.getValue()) {//check if you found error
        	map.put("isPreferred", "true");
        } else {
        	map.put("isPreferred", "false");
        }
        map.put("vendorProfileImageUrl",getOrganisationLogo(vendorOrg.getVendorOrganisationId(), UserType.VENDOR.getValue()));
        
        return map;
	}
	
	private Map<String, Object> getVendorOrganisationObj(VendorOrganisation vendorOrg) {
		// TODO Auto-generated method stub
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(vendorOrg, Map.class);
        map.put("isPreferred", "false");
        
        if(vendorOrg.getVendorTags() != null && vendorOrg.getVendorTags().size() > 0) {
        	map.put("vendorTags",getVendorTags(vendorOrg.getVendorTags()));
        } else {
        	map.put("vendorTags","");
        }
        map.put("city",getCityName(vendorOrg.getCity()));
        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
        map.put("vendorProfileImageUrl",getOrganisationLogo(vendorOrg.getVendorOrganisationId(), UserType.VENDOR.getValue()));
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
        
        ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
		
        map.put("condoName", clientOrganisation.getOrganisationName());
        map.put("condoCity", getCityName(clientOrganisation.getCity()));
        map.put("city", getCityName(clientOrganisation.getCity()));
//		
        
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

	
	public List<Map<String, Object>> getVendorSearchResults(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		Integer searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
		String actualKeyword = String.valueOf(requestData.get("keyword"));
		Integer vendorUserId = Integer.parseInt(String.valueOf(requestData.get("vendorUserId")));
		Integer vendorOrganisationId = Integer.parseInt(String.valueOf(requestData.get("vendorOrganisationId")));
		
		String keyword = "%"+actualKeyword+"%";
		
		List<Map<String, Object>> result = new ArrayList();
		
		List<Integer> projectStatusCodes = new ArrayList();
		
		switch(searchType) {
			case 1:
				projectStatusCodes.add(ProjectPostType.UNPUBLISHED.getValue());//status = 1 or status = 2 - current projects status
				projectStatusCodes.add(ProjectPostType.PUBLISHED.getValue());
				
				List<Object[]> currentprojects = projectRepository.findAllCurrentProjectsForVendorByKeyword(vendorOrganisationId, keyword);
				
				return getProjectsBundle(vendorOrganisationId, currentprojects);
			case 2:
				projectStatusCodes.add(ProjectPostType.COMPLETED.getValue());//status = 3 or status = 4 - current projects status
				projectStatusCodes.add(ProjectPostType.TERMINATED.getValue());
				
				List<Object[]> historyprojects = projectRepository.findAllHistoryProjectsForVendorByKeyword(vendorOrganisationId, keyword);
				
				return getProjectsBundle(vendorOrganisationId, historyprojects);
			case 3:{
				projectStatusCodes.add(ProjectPostType.PUBLISHED.getValue());
				
				List<Object[]> favoriteProjects = projectRepository.findFavouriteProjectsForVendorByKeyword(vendorOrganisationId, keyword, projectStatusCodes);
				
				return getProjectsBundle(vendorOrganisationId, favoriteProjects);
			}
			case 4:{
				// check contract type, posttype, interest
				projectStatusCodes.add(ProjectPostType.PUBLISHED.getValue());//status = 2 - published projects status
				
				List<Object[]> projects = projectRepository.findAllProjectsForMarketPlaceByKeyword(keyword, projectStatusCodes);
				
				return getProjectsBundleForVendorMarketplace(vendorOrganisationId, projects);
			}
			case 5:{
				// check keyword has vendor tags				
				List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(keyword);
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
			        result.add(getVendorOrganisationObj(vendorOrg));
				}
		    	
				if(tagContainedVendors != null && tagContainedVendors.size() > 0) {
					for(VendorOrganisation vendorOrg : tagContainedVendors) {
						result.add(getVendorOrganisationObj(vendorOrg));
					}
				}
				
				HashSet<Map<String, Object>> resultSet = new HashSet(result);
				result.clear();
				result.addAll(resultSet);
				
				Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
		            
		            @Override
		            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
		                Integer o1 = (Integer) e1.get("vendorOrganisationId");
		                Integer o2 = (Integer) e2.get("vendorOrganisationId");
		                return o1.compareTo(o2);
		            }
		        };
				 
				Collections.sort(result, valueComparator);
				
				return result;
			}
			case 6:{
				
				List<ClientOrganisation> clientOrganisations = clientOrganisationRepository.findAllByKeyword(keyword);
				for(ClientOrganisation clientOrganisation : clientOrganisations) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientOrganisation, Map.class);
			        
			        if(clientOrganisation.getCity() != null ) {
			        	try {
			        		Integer city = Integer.parseInt(clientOrganisation.getCity());
			        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
			        		map.put("city",serviceCity.getCityName());
			        	} catch(Exception exp) {
			        		map.put("city","");
			        	}
			        	
			        } else {
			        	map.put("city","");
					}
			        List<Map<String, Object>> amenitiesInfo = clientService.getAmenitiesByOrgId(clientOrganisation.getClientOrganisationId());
			        map.put("amenities", amenitiesInfo);
			        map.put("organisationLogo", getOrganisationLogo(clientOrganisation.getClientOrganisationId(), UserType.CLIENT.getValue()));
			        result.add(map);
				}
				return result;
			}
			case 7:{
				try {
					List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeywordForVendor(vendorOrganisationId, Constants.UserType.VENDOR.getValue(), keyword);
					HashSet<InternalMessage> uniqueInternalMessages = new HashSet(internalMessages);// add distinct
					
					for(InternalMessage internalMessage : internalMessages) {
						Map<String,Object> map = new HashMap<>();
						ObjectMapper oMapper = new ObjectMapper();
						map = oMapper.convertValue(internalMessage, Map.class);
						map.remove("userId");	
						map.remove("organisationId");	
						
						Map<String,Object> user = new HashMap<>();
						Map<String,Object> organisation = new HashMap<>();
						
						UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(internalMessage.getUserId(), internalMessage.getUserType());
						
						if(internalMessage.getUserType() == Constants.UserType.CLIENT.getValue()) {
							
							ClientUser clientUser = clientUserRepository.findByClientId(internalMessage.getUserId());
							user.put("userId",clientUser.getClientId());
							user.put("firstName",clientUser.getFirstName());
							user.put("lastName",clientUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(internalMessage.getOrganisationId());
							organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
							organisation.put("managementCompany",clientOrganisation.getManagementCompany());
							organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
							organisation.put("organisationName",clientOrganisation.getOrganisationName());
							organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
							
						} else if(internalMessage.getUserType() == Constants.UserType.VENDOR.getValue()) {
							
							VendorUser vendorUser = vendorUserRepository.findByUserId(internalMessage.getUserId());
							user.put("userId",vendorUser.getUserId());
							user.put("firstName",vendorUser.getFirstName());
							user.put("lastName",vendorUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(internalMessage.getOrganisationId());
							organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
							organisation.put("legalName",vendorOrganisation.getLegalName());
							organisation.put("organisationName",vendorOrganisation.getCompanyName());
							organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
							organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
						}
						
						
						map.put("user",user);
						map.put("organisation",organisation);
						
						List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
						List<Map<String,Object>> allFiles = new ArrayList();
						for(ThreadFiles threadFile : threadFiles) {
							Map<String,Object> file = new HashMap<>();
							file.put("id", threadFile.getId());
							file.put("fileName", threadFile.getFileName());
							file.put("fileType", threadFile.getFileType());
							file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
							file.put("blobName", threadFile.getBlobName());
							file.put("containerName", threadFile.getContainerName());
//							file.put("fileUrl", threadFile.getFileUrl());
							file.put("createdAt", threadFile.getCreatedAt());
//							file.put("modifiedDate", threadFile.getModifiedDate());
							allFiles.add(file);
						}
						VendorUser createdBy = vendorUserRepository.findByUserId(internalMessage.getUserId());
						 
						map.put("createdByUser", createdBy.getFirstName()+" "+createdBy.getLastName()); // this created by user search is missing
						map.put("files",allFiles);
						map.put("comments",getInternalThreadComments(internalMessage.getId(), UserType.VENDOR.getValue(), vendorUserId));
						
						result.add(map);
					} 
				} catch(Exception exp) {
					return null;
				}
				
				return result;
			}
			case 8:{
				try {
					List<ExternalMessage> externalMessages = externalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeywordForVendor(vendorOrganisationId, Constants.UserType.VENDOR.getValue(), keyword);
					HashSet<ExternalMessage> uniqueExternalMessages = new HashSet(externalMessages);// add distinct
					
					for(ExternalMessage externalMessage : uniqueExternalMessages) {
						
						Map<String,Object> map = new HashMap<>();
						ObjectMapper oMapper = new ObjectMapper();
						map = oMapper.convertValue(externalMessage, Map.class);
						map = oMapper.convertValue(externalMessage, Map.class);
						map.remove("sourceOrganisationId");	
						map.remove("sourceUserId");	
						map.remove("targetOrganisationId");
						map.remove("targetUserType");
						
						Map<String,Object> user = new HashMap<>();
						Map<String,Object> organisation = new HashMap<>();
						
						UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessage.getSourceUserId(), externalMessage.getSourceUserType());
						
						if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
							
							ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
							user.put("userId",clientUser.getClientId());
							user.put("firstName",clientUser.getFirstName());
							user.put("lastName",clientUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
							organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
							organisation.put("managementCompany",clientOrganisation.getManagementCompany());
							organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
							organisation.put("organisationName",clientOrganisation.getOrganisationName());
							organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
							
						} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
							
							VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
							user.put("userId",vendorUser.getUserId());
							user.put("firstName",vendorUser.getFirstName());
							user.put("lastName",vendorUser.getLastName());
							if(userProfileImage != null)
								user.put("profileImageURL",userProfileImage.getFileUrl());
							else
								user.put("profileImageURL","");
							
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
							organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
							organisation.put("legalName",vendorOrganisation.getLegalName());
							organisation.put("organisationName",vendorOrganisation.getCompanyName());
							organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
							organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
						}
						
						
						map.put("fromUser",user);
						map.put("fromOrganisation",organisation);
						
						map.put("targetOrganisations",getExternalMessageTargetOrganisations(externalMessage.getId()));
						
						List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(externalMessage.getId(), ThreadType.EXTERNAL.getValue());
						List<Map<String,Object>> allFiles = new ArrayList();
						for(ThreadFiles threadFile : threadFiles) {
							Map<String,Object> file = new HashMap<>();
							file.put("id", threadFile.getId());
							file.put("fileName", threadFile.getFileName());
							file.put("fileType", threadFile.getFileType());
							file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
							file.put("blobName", threadFile.getBlobName());
							file.put("containerName", threadFile.getContainerName());
//							file.put("fileUrl", threadFile.getFileUrl());
							file.put("createdAt", threadFile.getCreatedAt());
//							file.put("modifiedDate", threadFile.getModifiedDate());
							allFiles.add(file);
						}
						
						
						/*if okay use SQL Result Set mapping query here when require optimization*/
						if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
							ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
							ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
							
							map.put("createdByUser",clientUser.getFirstName() +" "+ clientUser.getLastName());
							map.put("sourceOrganisationName",clientOrganisation.getOrganisationName());
							
						} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
							VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
							VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
							
							map.put("createdByUser",vendorUser.getFirstName() +" "+ vendorUser.getLastName());
							map.put("sourceOrganisationName",vendorOrganisation.getCompanyName());
						}
						
						
						map.put("files",allFiles);
						map.put("comments",getExternalThreadComments(externalMessage.getId(), UserType.VENDOR.getValue(), vendorUserId));
						
						result.add(map);
					}
				} catch(Exception exp) {
					return null;
				}
				return result;
			}
		}
		
		return null;
	}

	private List<Map<String, Object>> getProjectsBundle(Integer vendorOrganisationId, List<Object[]> projects) {
		
		List<Map<String, Object>> result = new ArrayList();
		
		// TODO Auto-generated method stub
		projects.stream().forEach((record) -> {
	        Project project = (Project) record[0];
	        String managementCompany = (String) record[1];
	        String firstName = (String) record[2];
	        String lastName = (String) record[3];
	        String condoName = (String) record[4];
	        
//	        if(project.getStatus() == Constants.ProjectPostType.PUBLISHED.getValue() ) {
	        	List<Integer> ids = Stream.of(project.getTags().trim().split(","))
				        .map(Integer::parseInt)
				        .collect(Collectors.toList());
		        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		       
		        Map<String,Object> map = new HashMap<>();
				ObjectMapper oMapper = new ObjectMapper();
				map = oMapper.convertValue(project, Map.class);
				
				map.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
				map.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId())); 
				map.put("condoName", condoName);
				map.put("projectCreatedBy", firstName+" "+lastName);
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//				projectReview.put("condoName", clientOrganisation.getOrganisationName());
				map.put("condoCity", getCityName(clientOrganisation.getCity()));
				map.put("city", getCityName(clientOrganisation.getCity()));
				VendorProjectInterests vendorProjectInterests = vendorProjectInterestsRepository.findByProjectIdAndVendorOrganisationId( project.getProjectId(), vendorOrganisationId);
				
				if(vendorProjectInterests != null && vendorProjectInterests.getInterestStatus() == Constants.ProjectInterestStatus.LIKE.getValue()) {
					map.put("isInterested", true);
				} else {
					map.put("isInterested", false);
				}
				result.add(map);
//	        }
	        
	        
		 });
		return result;
	}
	
private List<Map<String, Object>> getProjectsBundleForVendorMarketplace(Integer vendorOrganisationId, List<Object[]> projects) {
		
		List<Map<String, Object>> result = new ArrayList();
		
		// TODO Auto-generated method stub
		projects.stream().forEach((record) -> {
	        Project project = (Project) record[0];
	        String managementCompany = (String) record[1];
	        String firstName = (String) record[2];
	        String lastName = (String) record[3];
	        String condoName = (String) record[4];
	        
//	        if(project.getStatus() == Constants.ProjectPostType.PUBLISHED.getValue() ) {
	        	List<Integer> ids = Stream.of(project.getTags().trim().split(","))
				        .map(Integer::parseInt)
				        .collect(Collectors.toList());
		        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		       
		        Map<String,Object> map = new HashMap<>();
				ObjectMapper oMapper = new ObjectMapper();
				map = oMapper.convertValue(project, Map.class);
				
				map.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
				map.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId())); 
				map.put("condoName", condoName);
				map.put("projectCreatedBy", firstName+" "+lastName);
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//				projectReview.put("condoName", clientOrganisation.getOrganisationName());
				map.put("condoCity", getCityName(clientOrganisation.getCity()));
				map.put("city", getCityName(clientOrganisation.getCity()));
				VendorProjectInterests vendorProjectInterests = vendorProjectInterestsRepository.findByProjectIdAndVendorOrganisationId( project.getProjectId(), vendorOrganisationId);
				
				if(vendorProjectInterests != null && vendorProjectInterests.getInterestStatus() == Constants.ProjectInterestStatus.LIKE.getValue()) {
					map.put("isInterested", true);
				} else {
					map.put("isInterested", false);
				}
				
				//Client Preferred vendor Check
				if(project.getPostType() == ProjectPostTo.ALL.getValue()) {
					result.add(map);
				} else if(project.getPostType() == ProjectPostTo.MARKED.getValue()){
					UserWishList userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(project.getClientOrganisationId(), Constants.UserType.CLIENT.getValue(), vendorOrganisationId, Constants.UserType.VENDOR.getValue() );
					if(userWish != null && userWish.getInterestStatus() == ProjectInterestStatus.LIKE.getValue()) {//check if you found error
						result.add(map);
			        } else {
//			        	map.put("isPreferred", "false");//client not preferred this vendor for project posting
			        }
				}
				
//	        }
	        
	        
		 });
		return result;
	}
	
	private List<Map<String, Object>> getProjectsBundleForClientMarketplace(List<Object[]> projects) {
		
		List<Map<String, Object>> result = new ArrayList();
		
		// TODO Auto-generated method stub
		projects.stream().forEach((record) -> {
	        Project project = (Project) record[0];
	        String managementCompany = (String) record[1];
	        String firstName = (String) record[2];
	        String lastName = (String) record[3];
	        String condoName = (String) record[4];
	        
//	        if(project.getStatus() == Constants.ProjectPostType.PUBLISHED.getValue() ) {
	        	List<Integer> ids = Stream.of(project.getTags().trim().split(","))
				        .map(Integer::parseInt)
				        .collect(Collectors.toList());
		        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		       
		        Map<String,Object> map = new HashMap<>();
				ObjectMapper oMapper = new ObjectMapper();
				map = oMapper.convertValue(project, Map.class);
				
				map.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
				map.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId())); 
				map.put("condoName", condoName);
				map.put("projectCreatedBy", firstName+" "+lastName);
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//				projectReview.put("condoName", clientOrganisation.getOrganisationName());
				map.put("condoCity", getCityName(clientOrganisation.getCity()));
				map.put("city", getCityName(clientOrganisation.getCity()));
				result.add(map);
//	        }
	        
	        
		 });
		return result;
	}
	
	private String getCityName(String cityId) {
		// TODO Auto-generated method stub
		if(cityId != null ) {
        	try {	
        		Integer city = Integer.parseInt(cityId);
        		ServiceCities serviceCity = servicesCitiesRepository.findOneById(city);
        		return serviceCity.getCityName();
        	} catch(Exception exp) {
        		return "";
        	}
        	
        } else {
        	return "";
		}
	}

	public Object getSupportUserSearchResults(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
		String actualKeyword = String.valueOf(requestData.get("keyword"));
		
		String keyword = "%"+actualKeyword+"%";
		
		Map<String, Object> resultObj = new HashMap();
		List<Map<String, Object>> resultList = new ArrayList();
		
		List<Integer> projectStatusCodes = new ArrayList();
		
//		ORGANISATION(1),
//		USER(2),
//		PROJECT(3),
//		REVIWES(4),
//		MESSAGES(5);
		//Un-ApprovedSearch- Client - 6
		//Un-ApprovedSearch- Vendor - 7
		switch(searchType) {
			case 1:{
				resultObj.put("clientOrganisations",getClientOrganisationsByKeyword(keyword));
				resultObj.put("vendorOrganisations",getVendorOrganisationsByKeyword(keyword));
				
				return resultObj;
			}
			case 2:{
				resultObj.put("clientUsers",getClientUsersByKeyword(keyword));
				resultObj.put("vendorUsers",getVendorUsersByKeyword(keyword));
				
				return resultObj;
			}
			case 3:{
				//Handled only for Marketplace Projects
				projectStatusCodes.add(ProjectPostType.PUBLISHED.getValue());
				// check contract type
				// check keyword has project tags				
				return getSupportSearchProjects(Constants.SearchType.MARKETPLACE_PROJECTS.getValue(), actualKeyword, keyword,  projectStatusCodes);
			}
			case 4:{
				// use vendor ratings 
				
				return getSearchedReviewsRatings(keyword);
			}
			case 5:{
				return getExternalMessagesSearchResults(keyword);
			}
			case 6:{
				resultObj.put("clientOrganisations",getUnApprovedOrganisationsList(UserType.CLIENT.getValue(), keyword));
				return resultObj;
			}
			case 7:{
				resultObj.put("vendorOrganisations",getUnApprovedOrganisationsList(UserType.VENDOR.getValue(), keyword));
				return resultObj;
			}
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getUnApprovedOrganisationsList(int userType, String keyword) {
		// TODO Auto-generated method stub
		
		Map<String, Object> result = new HashMap();
		
		List<Map<String, Object>>  upApprovedOrganisationsList = new ArrayList();
		
		if(userType == UserType.CLIENT.getValue()) {
			//Get Client Organisations List
			List<ClientOrganisation> unApprovedclientOrgs = clientOrganisationRepository.findUnApprovedOrganisationsByKeyword(keyword);
			
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
		        
		        upApprovedOrganisationsList.add(map);
			}
			
		} else if(userType == UserType.VENDOR.getValue()) {
			//Get Vendor Organisations List
			List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findUnApprovedOrganisationsByKeyword(keyword);
			
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
		        upApprovedOrganisationsList.add(map);
			}
		}
		
		return upApprovedOrganisationsList;
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
	
	private List<Object> getVendorUsersByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<VendorUser> vendorUsers = vendorUserRepository.findAllByKeyword(keyword);
		List<Object> searchedVendorUsers = new ArrayList();
		
		for(VendorUser vendorUser : vendorUsers) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(vendorUser, Map.class);
	        
	        UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(vendorUser.getUserId(), Constants.UserType.VENDOR.getValue());
	        
	        if(userProfileImage != null) {
	        	map.put("profileImageURL",userProfileImage.getFileUrl());
	        } else {
	        	map.put("profileImageURL","");
	        }
	        
	        searchedVendorUsers.add(map);
		}
		
		
		return searchedVendorUsers;
	}

	private List<Object> getClientUsersByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<ClientUser> clientUsers = clientUserRepository.findAllByKeyword(keyword);
		List<Object> searchedClientUsers = new ArrayList();
		
		for(ClientUser clientUser : clientUsers) {
			ObjectMapper oMapper = new ObjectMapper();
	        // object -> Map
	        Map<String, Object> map = oMapper.convertValue(clientUser, Map.class);
	        UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(clientUser.getClientId(), Constants.UserType.CLIENT.getValue());
	        
	        try {
	        	if(userProfileImage != null)
	        		map.put("profileImageURL",userProfileImage.getFileUrl());
	        	else {
	        		map.put("profileImageURL","");
	        	}
	        } catch(Exception exp) {
	        	exp.printStackTrace();
	        }
	        List<Object> orgs = clientService.getAllCorporateAccounts(clientUser.getClientId());
	        
	        map.put("corporateAccounts", orgs);
	        
	        searchedClientUsers.add(map);
		}
		return searchedClientUsers;
	}

	public List<Object> getVendorOrganisationsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<VendorOrganisation> vendorOrgsAll = vendorOrganisationRepository.findAllByKeyword(keyword);
		
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
		        String logo = getVendorOrganisationLogo(vendorOrg.getVendorOrganisationId());
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

	public List<Object> getClientOrganisationsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		List<ClientOrganisation> clientOrgsAll = clientOrganisationRepository.findAllByKeyword(keyword);
		
		List<Object> clientOrganisations = new ArrayList();
		
		for(ClientOrganisation clientOrg : clientOrgsAll) {
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
	        
	        int activeStatus = clientOrg.getActiveStatus();
	        int deleteStatus = clientOrg.getDeleteStatus();
	        
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
	        List<Map<String, Object>> amenitiesInfo = clientService.getAmenitiesByOrgId(clientOrg.getClientOrganisationId());
	        map.put("amenities", amenitiesInfo);
	        String logo = getClientOrganisationLogo(clientOrg.getClientOrganisationId());
	        if(logo != null)
	        	map.put("organisationLogo", logo);
	        else
	        	map.put("organisationLogo", "");
			clientOrganisations.add(map);
		}
		
    			
		return clientOrganisations;
	}

	private List<Map<String, Object>> getSupportSearchProjects(int searchType, String actualKeyword, String keyword, List<Integer> projectStatusCodes) {
		List<Map<String, Object>> result = new ArrayList();
		
		List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(keyword);
		ArrayList<Project> tagContainedProjects = new ArrayList();
		if(tags != null && tags.size() > 0) {
			for(PredefinedTags tag : tags) {
				List<Project> projects = projectRepository.findAllByTagKeyword(String.valueOf("%"+tag.getTagId()+"%"));
				tagContainedProjects.addAll(new ArrayList<>(projects));
			}
		}
		List<Project> projects = new ArrayList();
		switch(searchType) {
//			case 1:{
//				projects = projectRepository.findAllCurrentByKeyword(clientOrganisationId, keyword, projectStatusCodes);
//				break;
//			}
//			case 2:{
//				projects = projectRepository.findAllHistoryByKeyword(clientOrganisationId, keyword, projectStatusCodes);
//				break;
//			}
			case 4:{
				projects = projectRepository.findAllMarketplaceByKeyword(keyword, projectStatusCodes);
//				break;
			}
		}
		
		
		for(Project project : projects) {
			result.add(getProjectObject(project));
		}
		
		if(tagContainedProjects != null && tagContainedProjects.size() > 0) {
			for(Project project : tagContainedProjects) {
				result.add(getProjectObject(project));
			}
		}
		
		HashSet<Map<String, Object>> resultSet = new HashSet(result);
		result.clear();
		result.addAll(resultSet);
		
		Comparator<Map<String, Object>> valueComparator = new Comparator<Map<String, Object>>() {
            
            @Override
            public int compare(Map<String, Object> e1, Map<String, Object> e2) {
                Integer o1 = (Integer) e1.get("projectId");
                Integer o2 = (Integer) e2.get("projectId");
                return o1.compareTo(o2);
            }
        };
        
        Collections.sort(result, valueComparator);
        
		return result;
	}
	
	private Object getSearchedReviewsRatings(String keyword) {
		// TODO Auto-generated method stub
		 try {
			 List<ProjectReviewRating> projectReviewsForVendors = projectReviewRatingRepository.findAllByKeyword(keyword);
			 
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
				 // required Organisation Names so added
				 if(vendorReviewsForProject.getClientOrganisationId() != 0) {
					 ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(vendorReviewsForProject.getClientOrganisationId());
					 mappedObj.put("clientOrganisationName",clientOrganisation.getOrganisationName());
				 } else {
					 mappedObj.put("clientOrganisationName","");
				 }
				 
				 if(vendorReviewsForProject.getVendorOrganisationId() != 0) {
					 VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorReviewsForProject.getVendorOrganisationId());
					 mappedObj.put("vendorOrganisationName",vendorOrganisation.getCompanyName());
				 } else {
					 mappedObj.put("vendorOrganisationName","");
				 }
				 
				 mappedObj.put("categoryRating",getDetailedRatingForReview(vendorReviewsForProject.getId()));
				 List<Map<String, Object>> vendorCategoryRatings = getDetailedRatingForReview(vendorReviewsForProject.getId());
					
					Float overAllRatingCalculation=0.0f;
					for(Map<String,Object> rating : vendorCategoryRatings) {
						
						VendorCategoryRatings vendorRating = new VendorCategoryRatings();
						
						Integer ratingCategory = (Integer) rating.get("ratingCategory");
						Float ratingValue = Float.valueOf(String.valueOf(rating.get("rating")));
						
						switch(ratingCategory) {
							case 1/*VendorRatingCategory.RESPONSIVENESS.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);
								break;
							}
							case 2/*VendorRatingCategory.PROFESSIONALISM.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);
								break;
							}
							case 3/*VendorRatingCategory.ACCURACY.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.ACCURACY.getValue()/100);
								break;
							}
							case 4/*VendorRatingCategory.QUALITY.getValue()*/ :{
								overAllRatingCalculation = overAllRatingCalculation + (ratingValue*VendorRatingCategoryPercentage.QUALITY.getValue()/100);
								break;
							}
						}
					}
					mappedObj.put("rating", overAllRatingCalculation);
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

	private Object getExternalMessagesSearchResults(String keyword) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> resultList = new ArrayList();
		
		try {
			List<ExternalMessage> externalMessages = externalMessageRepository.findAllByKeyword(keyword);
			HashSet<ExternalMessage> uniqueExternalMessages = new HashSet(externalMessages);// add distinct
			
			for(ExternalMessage externalMessage : uniqueExternalMessages) {
				
				Map<String,Object> map = new HashMap<>();
				ObjectMapper oMapper = new ObjectMapper();
				map = oMapper.convertValue(externalMessage, Map.class);
				map = oMapper.convertValue(externalMessage, Map.class);
				map.remove("sourceOrganisationId");	
				map.remove("sourceUserId");	
				map.remove("targetOrganisationId");
				map.remove("targetUserType");
				
				Map<String,Object> user = new HashMap<>();
				Map<String,Object> organisation = new HashMap<>();
				
				UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessage.getSourceUserId(), externalMessage.getSourceUserType());
				
				if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
					
					ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
					user.put("userId",clientUser.getClientId());
					user.put("firstName",clientUser.getFirstName());
					user.put("lastName",clientUser.getLastName());
					if(userProfileImage != null)
						user.put("profileImageURL",userProfileImage.getFileUrl());
					else
						user.put("profileImageURL","");
					
					ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
					organisation.put("organisationId",clientOrganisation.getClientOrganisationId());
					organisation.put("managementCompany",clientOrganisation.getManagementCompany());
					organisation.put("corporateNumber",clientOrganisation.getCorporateNumber());
					organisation.put("organisationName",clientOrganisation.getOrganisationName());
					organisation.put("organisationLogo",getClientOrganisationLogo(clientOrganisation.getClientOrganisationId()));
					
				} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
					
					VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
					user.put("userId",vendorUser.getUserId());
					user.put("firstName",vendorUser.getFirstName());
					user.put("lastName",vendorUser.getLastName());
					if(userProfileImage != null)
						user.put("profileImageURL",userProfileImage.getFileUrl());
					else
						user.put("profileImageURL","");
					
					VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
					organisation.put("organisationId",vendorOrganisation.getVendorOrganisationId());
					organisation.put("legalName",vendorOrganisation.getLegalName());
					organisation.put("organisationName",vendorOrganisation.getCompanyName());
					organisation.put("organisationLogoName",vendorOrganisation.getLogoName());
					organisation.put("organisationLogo",getVendorOrganisationLogo(vendorOrganisation.getVendorOrganisationId()));
				}
				
				
				map.put("fromUser",user);
				map.put("fromOrganisation",organisation);
				
				map.put("targetOrganisations",getExternalMessageTargetOrganisations(externalMessage.getId()));
				
				List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(externalMessage.getId(), ThreadType.EXTERNAL.getValue());
				List<Map<String,Object>> allFiles = new ArrayList();
				for(ThreadFiles threadFile : threadFiles) {
					Map<String,Object> file = new HashMap<>();
					file.put("id", threadFile.getId());
					file.put("fileName", threadFile.getFileName());
					file.put("fileType", threadFile.getFileType());
					file.put("fileSize", Utils.formatFileSize(Long.parseLong(threadFile.getFileSize())));
					file.put("blobName", threadFile.getBlobName());
					file.put("containerName", threadFile.getContainerName());
//					file.put("fileUrl", threadFile.getFileUrl());
					file.put("createdAt", threadFile.getCreatedAt());
//					file.put("modifiedDate", threadFile.getModifiedDate());
					allFiles.add(file);
				}
				
				
				/*if okay use SQL Result Set mapping query here when require optimization*/
				if(externalMessage.getSourceUserType() == Constants.UserType.CLIENT.getValue()) {
					ClientUser clientUser = clientUserRepository.findByClientId(externalMessage.getSourceUserId());
					ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getSourceOrganisationId());
					
					map.put("createdByUser",clientUser.getFirstName() +" "+ clientUser.getLastName());
					map.put("sourceOrganisationName",clientOrganisation.getOrganisationName());
					
				} else if(externalMessage.getSourceUserType() == Constants.UserType.VENDOR.getValue()) {
					VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessage.getSourceUserId());
					VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getSourceOrganisationId());
					
					map.put("createdByUser",vendorUser.getFirstName() +" "+ vendorUser.getLastName());
					map.put("sourceOrganisationName",vendorOrganisation.getCompanyName());
				}
				
				
				map.put("files",allFiles);
				map.put("comments",getExternalThreadCommentsForSupportUser(externalMessage.getId()));
				
				resultList.add(map);
			}
		} catch(Exception exp) {
			return null;
		}
		
		return resultList;
	}
	
	private List<Map<String,Object>> getExternalThreadCommentsForSupportUser(Integer threadId) {
		// TODO Auto-generated method stub
		List<ExternalMessageComment> externalMessageComments = externalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(ExternalMessageComment externalMessageComment : externalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessageComment, Map.class);
			map.remove("userId");
			
			Map<String,Object> user = new HashMap<>();
			
			UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(externalMessageComment.getUserId(), externalMessageComment.getUserType());
			
			if(externalMessageComment.getUserType() == Constants.UserType.CLIENT.getValue()) {
				
				ClientUser clientUser = clientUserRepository.findByClientId(externalMessageComment.getUserId());
				user.put("isCommented", false);
					
				
				user.put("userId",clientUser.getClientId());
				user.put("firstName",clientUser.getFirstName());
				user.put("lastName",clientUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessageComment.getOrganisationId());
				user.put("organisationId",clientOrganisation.getClientOrganisationId());
				user.put("organisationName",clientOrganisation.getOrganisationName());
				
				
			} else if(externalMessageComment.getUserType() == Constants.UserType.VENDOR.getValue()) {
				
				VendorUser vendorUser = vendorUserRepository.findByUserId(externalMessageComment.getUserId());
				user.put("isCommented", false);
				
				user.put("userId",vendorUser.getUserId());
				user.put("firstName",vendorUser.getFirstName());
				user.put("lastName",vendorUser.getLastName());
				if(userProfileImage != null)
					user.put("profileImageURL",userProfileImage.getFileUrl());
				else
					user.put("profileImageURL","");
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessageComment.getOrganisationId());
				user.put("organisationId",vendorOrganisation.getVendorOrganisationId());
				user.put("organisationName",vendorOrganisation.getCompanyName());
				
			}
			map.put("user",user);
			
			List<CommentFiles> commentFiles = commentFilesRepository.findAllByCommentIdAndThreadType(externalMessageComment.getId(), ThreadType.EXTERNAL.getValue());
			List<Map<String,Object>> allFiles = new ArrayList();
			for(CommentFiles commentFile : commentFiles) {
				Map<String,Object> file = new HashMap<>();
				file.put("id", commentFile.getId());
				file.put("fileName", commentFile.getFileName());
				file.put("fileType", commentFile.getFileType());
				file.put("fileSize", Utils.formatFileSize(Long.parseLong(commentFile.getFileSize())));
				file.put("blobName", commentFile.getBlobName());
				file.put("containerName", commentFile.getContainerName());
//				file.put("fileUrl", commentFile.getFileUrl());
				file.put("createdAt", commentFile.getCreatedAt());
//				file.put("modifiedDate", commentFile.getModifiedDate());
				allFiles.add(file);
			}
			map.put("files","[]"/*allFiles*/);
			allComments.add(map);
		}
		return allComments;
	}

	public String getOrganisationLogo(int userId, int userType) {
		try {
			if( userType == UserType.VENDOR.getValue()) {
				VendorOrganisationProfileImages vendorOrgProfileImage =  vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(userId);
				
				if(vendorOrgProfileImage != null)
		        	return vendorOrgProfileImage.getFileUrl();
			} else if(userType == UserType.CLIENT.getValue()) {
				ClientOrganisationProfileImages clientOrgProfileImage =  clientOrganisationProfileImagesRepository.findByClientOrganisationId(userId);
				
				if(clientOrgProfileImage != null)
		        	return clientOrgProfileImage.getFileUrl();
			}
			
	        
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return "";
        }
		
		return "";
	}
	
	public Object getApplicationUserSearchResults(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		String email = String.valueOf(requestData.get("email"));
		String firstName = String.valueOf(requestData.get("firstName"));
		String lastName = String.valueOf(requestData.get("lastName"));
		String phone = "";
		try {
			phone = String.valueOf(requestData.get("phone"));
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		String userType = String.valueOf(requestData.get("userType"));
		
		String emailKeyword = "%"+email+"%";
		String fnameKeyword = "%"+firstName+"%";
		String lnameKeyword = "%"+lastName+"%";
		String phoneKeyword = "%"+phone+"%";
		
		Map<String, Object> resultObj = new HashMap();
		List<Map<String, Object>> resultList = new ArrayList();
		
		List<Integer> projectStatusCodes = new ArrayList();
		
		if(userType.equals("1")) {
			resultObj.put("clientUsers",getClientUsersByKeywords(email, firstName, lastName, phone));
			resultObj.put("vendorUsers","[]");
			return resultObj;
		} else if(userType.equals("2")) {
			resultObj.put("clientUsers","[]");
			resultObj.put("vendorUsers",getVendorUsersByKeywords(email, firstName, lastName));
			return resultObj;
		} else {
			resultObj.put("clientUsers",getClientUsersByKeywords(email, firstName, lastName, phone));
			resultObj.put("vendorUsers",getVendorUsersByKeywords(email, firstName, lastName));
			return resultObj;
		}
	}
	
	private List<Object> getClientUsersByKeywords(String emailKeyword,String fnameKeyword,String lnameKeyword,String phoneKeyword) {
		List<Object> searchedClientUsers = new ArrayList();
		
//		List<ClientUser> clientUsers = clientUserRepository.findAllByDeleteStatus(DeleteStatus.ACTIVE.getValue());
		List<ClientUser> clientUsers = clientUserRepository.findAll();
		
		for(ClientUser clientUser : clientUsers) {
			
	        boolean isEmailContain = false;
	        boolean isFNameContain = false;
	        boolean isLNameContain = false;
	        boolean isPhoneContain = false;
	        int paramCount = 4;
	        if(emailKeyword.trim().length() > 0) {
	        	if(clientUser.getEmail().toLowerCase().contains(emailKeyword.trim().toLowerCase())) {
	        		isEmailContain = true;
	        	}
			} else {
				isEmailContain = true;
				paramCount--;
			}
	        if(fnameKeyword.trim().length() > 0) {
	        	if(clientUser.getFirstName().toLowerCase().contains(fnameKeyword.trim().toLowerCase())) {
	        		isFNameContain = true;
	        	}
			} else {
				isFNameContain = true;
				paramCount--;
			}
	        if(lnameKeyword.trim().length() > 0) {
	        	if(clientUser.getLastName().toLowerCase().contains(lnameKeyword.trim().toLowerCase())) {
	        		isLNameContain = true;
	        	}
			} else {
				isLNameContain = true;
				paramCount--;
			}
	        if(phoneKeyword.trim().length() > 0) {
	        	if(clientUser.getPhone().contains(phoneKeyword.trim())) {
	        		isPhoneContain = true;
	        	}
			} else {
				isPhoneContain = true;
				paramCount--;
			}
	        
	        if(isEmailContain && isFNameContain && isLNameContain && isPhoneContain && paramCount > 0) {
	        	ObjectMapper oMapper = new ObjectMapper();
		        // object -> Map
		        Map<String, Object> map = oMapper.convertValue(clientUser, Map.class);
		        UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(clientUser.getClientId(), Constants.UserType.CLIENT.getValue());
		        
		        try {
		        	if(userProfileImage != null)
		        		map.put("profileImageURL",userProfileImage.getFileUrl());
		        	else {
		        		map.put("profileImageURL","");
		        	}
		        } catch(Exception exp) {
		        	exp.printStackTrace();
		        }
		        List<Object> orgs = clientService.getAllCorporateAccounts(clientUser.getClientId());
		        
		        map.put("corporateAccounts", orgs);

	        	searchedClientUsers.add(map);
	        }
		} 
		return searchedClientUsers;
	}
	
	private List<Object> getVendorUsersByKeywords(String emailKeyword,String fnameKeyword,String lnameKeyword) {
		List<VendorUser> vendorUsers = vendorUserRepository.findAll();
		List<Object> searchedVendorUsers = new ArrayList();
		
		for(VendorUser vendorUser : vendorUsers) {
			boolean isEmailContain = false;
	        boolean isFNameContain = false;
	        boolean isLNameContain = false;
	        int paramCount = 3;
	        if(emailKeyword.trim().length() > 0) {
	        	if(vendorUser.getEmail().toLowerCase().contains(emailKeyword.trim().toLowerCase())) {
	        		isEmailContain = true;
	        	}
			} else {
				isEmailContain = true;
				paramCount--;
			}
	        if(fnameKeyword.trim().length() > 0) {
	        	if(vendorUser.getFirstName().toLowerCase().contains(fnameKeyword.trim().toLowerCase())) {
	        		isFNameContain = true;
	        	}
			} else {
				isFNameContain = true;
				paramCount--;
			}
	        if(lnameKeyword.trim().length() > 0) {
	        	if(vendorUser.getLastName().toLowerCase().contains(lnameKeyword.trim().toLowerCase())) {
	        		isLNameContain = true;
	        	}
			} else {
				isLNameContain = true;
				paramCount--;
			}
	        
	        if(isEmailContain && isFNameContain && isLNameContain && paramCount > 0) {
	        	ObjectMapper oMapper = new ObjectMapper();
		        // object -> Map
		        Map<String, Object> map = oMapper.convertValue(vendorUser, Map.class);
		        
		        UserProfileImages userProfileImage = userProfileImagesRepository.findByUserIdAndUserType(vendorUser.getUserId(), Constants.UserType.VENDOR.getValue());
		        
		        if(userProfileImage != null) {
		        	map.put("profileImageURL",userProfileImage.getFileUrl());
		        } else {
		        	map.put("profileImageURL","");
		        }
		        map.remove("vendorOrganisationId");
		        map.put("vendorOrganisation",vendorOrganisationRepository.findByVendorOrganisationId(vendorUser.getVendorOrganisationId()));
		        
		        searchedVendorUsers.add(map);
	        }
			
		}
		
		return searchedVendorUsers;
	}
}
