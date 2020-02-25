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
import tech.torbay.userservice.constants.Constants.ThreadType;
import tech.torbay.userservice.entity.CommentFiles;
import tech.torbay.userservice.entity.InternalMessage;
import tech.torbay.userservice.entity.InternalMessageComment;
import tech.torbay.userservice.entity.ThreadFiles;
import tech.torbay.userservice.constants.Constants;
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
		
		switch(searchType) {
			case 1:
			case 2:{
				// check contract type
				// check keyword has project tags				
				List<PredefinedTags> tags = predefinedTagsRepository.findAllByTagName(actualKeyword);
				ArrayList<Project> tagContainedProjects = new ArrayList();
				if(tags != null && tags.size() > 0) {
					for(PredefinedTags tag : tags) {
						List<Project> projects = projectRepository.findAllByTagKeyword(clientOrganisationId, String.valueOf("%"+tag.getTagId()+"%"));
						tagContainedProjects.addAll(new ArrayList<>(projects));
					}
				}
				
				List<Project> projects = projectRepository.findAllByKeyword(clientOrganisationId, keyword);
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
			case 4:{
				
				List<ClientOrganisation> clientOrganisations = clientOrganisationRepository.findAllByKeyword(keyword);
				for(ClientOrganisation clientOrganisation : clientOrganisations) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientOrganisation, Map.class);
			        result.add(map);
				}
				
		        
				return result;
			}
			case 5:{ 
				// - changes
				// 1. add username based search both source , target - completed as well messages - ?
				// 2. add user, org details both source and target - completed as well messages - ?
				try {
					Integer threadType = Integer.parseInt(String.valueOf(requestData.get("threadType")));
					
					if(threadType == Constants.ThreadType.INTERNAL.getValue()) {
						List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeyword(clientOrganisationId, Constants.UserType.CLIENT.getValue(), keyword);
						HashSet<InternalMessage> uniqueInternalMessages = new HashSet(internalMessages);// add distinct
						
						for(InternalMessage internalMessage : internalMessages) {
							Map<String,Object> map = new HashMap<>();
							ObjectMapper oMapper = new ObjectMapper();
							map = oMapper.convertValue(internalMessage, Map.class);
							
							List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
							List<Map<String,Object>> allFiles = new ArrayList();
							for(ThreadFiles threadFile : threadFiles) {
								Map<String,Object> file = new HashMap<>();
								file.put("id", threadFile.getId());
								file.put("fileName", threadFile.getFileName());
								file.put("fileType", threadFile.getFileType());
								file.put("fileUrl", threadFile.getFileUrl());
								file.put("createdAt", threadFile.getCreatedAt());
								file.put("modifiedDate", threadFile.getModifiedDate());
								allFiles.add(file);
							}
							ClientUser createdBy = clientUserRepository.findByClientId(internalMessage.getUserId());
							 
							map.put("createdByUser", createdBy.getFirstName()+" "+createdBy.getLastName()); // this created by user search is missing
							map.put("files",allFiles);
							map.put("comments",getInternalThreadComments(internalMessage.getId()));
							
							result.add(map);
						}
					} else if(threadType == Constants.ThreadType.EXTERNAL.getValue()) {
						List<ExternalMessage> externalMessages = externalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeyword(clientOrganisationId, Constants.UserType.CLIENT.getValue(), keyword);
						HashSet<ExternalMessage> uniqueExternalMessages = new HashSet(externalMessages);// add distinct
						
						for(ExternalMessage externalMessage : uniqueExternalMessages) {
							
							Map<String,Object> map = new HashMap<>();
							ObjectMapper oMapper = new ObjectMapper();
							map = oMapper.convertValue(externalMessage, Map.class);
							
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
//								file.put("fileUrl", threadFile.getFileUrl());
								file.put("createdAt", threadFile.getCreatedAt());
//								file.put("modifiedDate", threadFile.getModifiedDate());
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
							
							
							if(externalMessage.getTargetUserType() == Constants.UserType.CLIENT.getValue()) {
								ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getTargetOrganisationId());
								
								map.put("destinationOrganisationName",clientOrganisation.getOrganisationName());
								
							} else if(externalMessage.getTargetUserType() == Constants.UserType.VENDOR.getValue()) {
								VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getTargetOrganisationId());
								
								map.put("destinationOrganisationName",vendorOrganisation.getCompanyName());
							}
							
							map.put("files",allFiles);
							map.put("comments",getExternalThreadComments(externalMessage.getId()));
							
							result.add(map);
						}
					}
				} catch(Exception exp) {
					return null;
				}
				
				return result;
			}
			case 6:{
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
			case 7:{
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
			case 8:{
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
			case 9:{
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
	
	private Map<String, Object> getClientTask(ClientTask clientTask) {
		// TODO Auto-generated method stub
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(clientTask, Map.class);
        
        if(clientTask.getIsOther() == Constants.Availability.AVAILABLE.getValue()) {
        	// only other name available
        	map.put("assignedTo", new ArrayList());
        } else if(clientTask.getIsOther() == Constants.Availability.NOT_AVAILABLE.getValue()) {			        	
        	map.put("assignedTo", getClientUsers(clientTask.getId()));
//        	map.put("assignee",getClientUsers(clientTask.getId()));
        }
        
        ClientUser createdBy = clientUserRepository.findByClientId(clientTask.getCreatedBy());
        ClientUser modifiedBy = clientUserRepository.findByClientId(clientTask.getModifiedBy());
        
        map.put("createdByUser",createdBy.getFirstName()+" "+createdBy.getLastName());
        map.put("modifiedByUser",modifiedBy.getFirstName()+" "+modifiedBy.getLastName());
        
        map.put("comments",getTaskComments(clientTask.getId()));
        
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
	
	private List<Map<String,Object>> getExternalThreadComments(Integer threadId) {
		// TODO Auto-generated method stub
		List<ExternalMessageComment> externalMessageComments = externalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(ExternalMessageComment externalMessageComment : externalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(externalMessageComment, Map.class);
			
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
	
	private List<Map<String,Object>> getInternalThreadComments(Integer threadId) {
		// TODO Auto-generated method stub
		List<InternalMessageComment> internalMessageComments = internalMessageCommentRepository.findAllByThreadId(threadId);
		
		List<Map<String,Object>> allComments = new ArrayList();
		
		for(InternalMessageComment internalMessageComment : internalMessageComments) {
			
			Map<String,Object> map = new HashMap<>();
			ObjectMapper oMapper = new ObjectMapper();
			map = oMapper.convertValue(internalMessageComment, Map.class);
			
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
        map.put("rating",getVendorCategoryRatings(vendorOrg.getVendorOrganisationId()));
        
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

	
	public List<Map<String, Object>> getVendorSearchResults(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		Integer searchType = Integer.parseInt(String.valueOf(requestData.get("searchType")));
		String actualKeyword = String.valueOf(requestData.get("keyword"));
		Integer vendorUserId = Integer.parseInt(String.valueOf(requestData.get("vendorUserId")));
		Integer vendorOrganisationId = Integer.parseInt(String.valueOf(requestData.get("vendorOrganisationId")));
		
		String keyword = "%"+actualKeyword+"%";
		
		List<Map<String, Object>> result = new ArrayList();
		
		switch(searchType) {
			case 1:{
				List<Object[]> projects = projectRepository.findAllProjectsForVendorByKeyword(vendorOrganisationId, keyword);
				
				return getProjectsBundle(vendorOrganisationId, projects);
			}
			case 2:{
				// check contract type, posttype, interest
				
				List<Object[]> projects = projectRepository.findAllProjectsForMarketPlaceByKeyword(keyword);
				
				return getProjectsBundle(vendorOrganisationId, projects);
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
			case 4:{
				
				List<ClientOrganisation> clientOrganisations = clientOrganisationRepository.findAllByKeyword(keyword);
				for(ClientOrganisation clientOrganisation : clientOrganisations) {
					ObjectMapper oMapper = new ObjectMapper();
			        Map<String, Object> map = oMapper.convertValue(clientOrganisation, Map.class);
			        result.add(map);
				}
				return result;
			}
			case 5:{
				try {
					Integer threadType = Integer.parseInt(String.valueOf(requestData.get("threadType")));
					
					if(threadType == Constants.ThreadType.INTERNAL.getValue()) {
						List<InternalMessage> internalMessages = internalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeywordForVendor(vendorOrganisationId, Constants.UserType.VENDOR.getValue(), keyword);
						HashSet<InternalMessage> uniqueInternalMessages = new HashSet(internalMessages);// add distinct
						
						for(InternalMessage internalMessage : internalMessages) {
							Map<String,Object> map = new HashMap<>();
							ObjectMapper oMapper = new ObjectMapper();
							map = oMapper.convertValue(internalMessage, Map.class);
							
							List<ThreadFiles> threadFiles = threadFilesRepository.findAllByThreadIdAndThreadType(internalMessage.getId(), ThreadType.INTERNAL.getValue());
							List<Map<String,Object>> allFiles = new ArrayList();
							for(ThreadFiles threadFile : threadFiles) {
								Map<String,Object> file = new HashMap<>();
								file.put("id", threadFile.getId());
								file.put("fileName", threadFile.getFileName());
								file.put("fileType", threadFile.getFileType());
								file.put("fileUrl", threadFile.getFileUrl());
								file.put("createdAt", threadFile.getCreatedAt());
								file.put("modifiedDate", threadFile.getModifiedDate());
								allFiles.add(file);
							}
							VendorUser createdBy = vendorUserRepository.findByUserId(internalMessage.getUserId());
							 
							map.put("createdByUser", createdBy.getFirstName()+" "+createdBy.getLastName()); // this created by user search is missing
							map.put("files",allFiles);
							map.put("comments",getInternalThreadComments(internalMessage.getId()));
							
							result.add(map);
						}
					} else if(threadType == Constants.ThreadType.EXTERNAL.getValue()) {
						List<ExternalMessage> externalMessages = externalMessageRepository.findAllByOrganisationIdAndUserTypeAndKeywordForVendor(vendorOrganisationId, Constants.UserType.CLIENT.getValue(), keyword);
						HashSet<ExternalMessage> uniqueExternalMessages = new HashSet(externalMessages);// add distinct
						
						for(ExternalMessage externalMessage : uniqueExternalMessages) {
							
							Map<String,Object> map = new HashMap<>();
							ObjectMapper oMapper = new ObjectMapper();
							map = oMapper.convertValue(externalMessage, Map.class);
							
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
//								file.put("fileUrl", threadFile.getFileUrl());
								file.put("createdAt", threadFile.getCreatedAt());
//								file.put("modifiedDate", threadFile.getModifiedDate());
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
							
							
							if(externalMessage.getTargetUserType() == Constants.UserType.CLIENT.getValue()) {
								ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(externalMessage.getTargetOrganisationId());
								
								map.put("destinationOrganisationName",clientOrganisation.getOrganisationName());
								
							} else if(externalMessage.getTargetUserType() == Constants.UserType.VENDOR.getValue()) {
								VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(externalMessage.getTargetOrganisationId());
								
								map.put("destinationOrganisationName",vendorOrganisation.getCompanyName());
							}
							
							map.put("files",allFiles);
							map.put("comments",getExternalThreadComments(externalMessage.getId()));
							
							result.add(map);
						}
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
	        String condoName = (String) record[1];
	        String firstName = (String) record[2];
	        String lastName = (String) record[3];
	        
	        if(project.getStatus() == Constants.ProjectPostType.PUBLISHED.getValue() ) {
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
				
				VendorProjectInterests vendorProjectInterests = vendorProjectInterestsRepository.findByProjectIdAndVendorOrganisationId( project.getProjectId(), vendorOrganisationId);
				
				if(vendorProjectInterests != null && vendorProjectInterests.getInterestStatus() == Constants.ProjectInterestStatus.LIKE.getValue()) {
					map.put("isInterested", true);
				} else {
					map.put("isInterested", false);
				}
				result.add(map);
	        }
	        
	        
		 });
		return result;
	}

}
