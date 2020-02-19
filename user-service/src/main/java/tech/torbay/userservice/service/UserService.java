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
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.PredefinedTags;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.ClientBuildingRepoRepository;
import tech.torbay.userservice.repository.ClientContractRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
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

	public List<Map<String, Object>> getSearchResults(Map<String, Object> requestData) {
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
//					for (Iterator<Project> subProject = tagContainedProjects.iterator() ; subProject.hasNext() ; ) {
//						Project pro = subProject.next();
//						if(project.getProjectId() == pro.getProjectId()) {
//							subProject.remove();
//						}
//					}
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
//					for (Iterator<VendorOrganisation> subVendorOrg = tagContainedVendors.iterator() ; subVendorOrg.hasNext() ; ) {
//						VendorOrganisation org = subVendorOrg.next();
//						if(vendorOrg.getVendorOrganisationId() == org.getVendorOrganisationId()) {
//							subVendorOrg.remove();
//						}
//					}
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
						HashSet<InternalMessage> uniqueInternalMessages = new HashSet(internalMessages);
						
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
						HashSet<ExternalMessage> uniqueExternalMessages = new HashSet(externalMessages);
						
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
			
			case 8:{
				List<ClientContract> allConstantBasedContracts = getAllConstantsBasedContractsSearchResults(actualKeyword);
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
				List<ClientBuildingRepository> allTypeBasedBuildingRepositories = getAllTypeBasedRepositorySearchResults(actualKeyword);
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
	
	private List<ClientContract> getAllConstantsBasedContractsSearchResults(String keyword) {
		// TODO Auto-generated method stub
		
		List<ClientContract> allTypeConstantsBasedContracts =  new ArrayList();
		
		switch(keyword.toLowerCase()) {
			case "auto":
			case "auto renewal":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByRenewalType(Constants.RenewalType.AUTO.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "manual":
			case "manual renewal":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByRenewalType(Constants.RenewalType.MANUAL.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "yes":
			case "including gst":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByGstAvailablity(Constants.Availability.AVAILABLE.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "no":
			case "without gst":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByGstAvailablity(Constants.Availability.NOT_AVAILABLE.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "weekly":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCostTermUnits(Constants.CostTermUnits.WEEKLY.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "monthly":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCostTermUnits(Constants.CostTermUnits.MONTHLY.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "yearly":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCostTermUnits(Constants.CostTermUnits.YEARLY.getValue());
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "year":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCancellationUnits(Constants.CostTermUnits.YEARLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByTermUnits(Constants.CostTermUnits.YEARLY.getValue());
				List<ClientContract> contracts2 = clientContractRepository.findAllByCostTermUnits(Constants.CostTermUnits.YEARLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				allTypeConstantsBasedContracts.addAll(contracts2);
				break;
			}
			case "month":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCancellationUnits(Constants.CostTermUnits.MONTHLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByTermUnits(Constants.CostTermUnits.MONTHLY.getValue());
				List<ClientContract> contracts2 = clientContractRepository.findAllByCostTermUnits(Constants.CostTermUnits.MONTHLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				allTypeConstantsBasedContracts.addAll(contracts2);
				break;
			}
			case "week":
			{
				
				List<ClientContract> contracts = clientContractRepository.findAllByCostTermUnits(Constants.CostTermUnits.WEEKLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				break;
			}
			case "years":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCancellationUnits(Constants.CostTermUnits.YEARLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByTermUnits(Constants.CostTermUnits.YEARLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				break;
			}
			case "months":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCancellationUnits(Constants.CostTermUnits.MONTHLY.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByTermUnits(Constants.CostTermUnits.MONTHLY.getValue());
				
				allTypeConstantsBasedContracts.addAll(contracts);
				allTypeConstantsBasedContracts.addAll(contracts1);
				break;
			}
			case "day":
			case "days":
			{
				List<ClientContract> contracts = clientContractRepository.findAllByCancellationUnits(Constants.TermUnits.DAYS.getValue());
				List<ClientContract> contracts1 = clientContractRepository.findAllByTermUnits(Constants.TermUnits.DAYS.getValue());
				
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
	

	private List<ClientBuildingRepository> getAllTypeBasedRepositorySearchResults(String keyword) {
		// TODO Auto-generated method stub
		
		List<ClientBuildingRepository> allTypeBasedBuildingRepositories =  new ArrayList();
		
		switch(keyword.toLowerCase()) {
			case "occupied":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByTenantStatus(Constants.TenantStatus.OWNER_OCCUPIED.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				break;
			}
			case "owner":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByTenantStatus(Constants.TenantStatus.OWNER_OCCUPIED.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByPersonTenantType(Constants.PersonTenantType.OWNER.getValue());
				
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				break;
			}
			case "vacant":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByTenantStatus(Constants.TenantStatus.VACANT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				break;
			}
			
			case "leased":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByTenantStatus(Constants.TenantStatus.LEASED.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				break;
			}
			case "other":
			{
				List<ClientBuildingRepository> buildingRepositoriesByTenant = clientBuildingRepoRepository.findAllByTenantStatus(Constants.TenantStatus.OTHER.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByPersonTenantType(Constants.PersonTenantType.OTHER.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByLien = clientBuildingRepoRepository.findAllByLienType(Constants.LienType.OTHER.getValue());
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByUnitType(Constants.UnitType.OTHER.getValue());
				
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByTenant);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByLien);
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "occupant":
			{
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByPersonTenantType(Constants.PersonTenantType.OCCUPANT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				break;
			}
			case "tenant":
			{
				List<ClientBuildingRepository> buildingRepositoriesByUnit = clientBuildingRepoRepository.findAllByPersonTenantType(Constants.PersonTenantType.TENANT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByUnit);
				break;
			}
			case "yes":
			{
				List<ClientBuildingRepository> buildingRepositoriesByLien = clientBuildingRepoRepository.findAllByLienType(Constants.LienType.YES.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByLien);
				break;
			}
			case "no":
			{
				List<ClientBuildingRepository> buildingRepositoriesByLien = clientBuildingRepoRepository.findAllByLienType(Constants.LienType.NO.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByLien);
				break;
			}
			case "locker":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByUnitType(Constants.UnitType.LOCKER.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "residential":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByUnitType(Constants.UnitType.RESIDENTIAL_UNIT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "parking":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByUnitType(Constants.UnitType.PARKING_UNIT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "commercial":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByUnitType(Constants.UnitType.COMMERCIAL_UNIT.getValue());
				allTypeBasedBuildingRepositories.addAll(buildingRepositoriesByPersonTenantType);
				break;
			}
			case "common":
			{
				List<ClientBuildingRepository> buildingRepositoriesByPersonTenantType = clientBuildingRepoRepository.findAllByUnitType(Constants.UnitType.COMMON_ELEMENT.getValue());
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

}
