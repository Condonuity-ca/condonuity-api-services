package tech.torbay.projectservice.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.projectservice.Utils.Utils;
import tech.torbay.projectservice.constants.Constants;
import tech.torbay.projectservice.constants.Constants.ProjectSortBy;
import tech.torbay.projectservice.entity.BidFiles;
import tech.torbay.projectservice.entity.ClientUser;
import tech.torbay.projectservice.entity.PredefinedTags;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectFiles;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.ProjectReviewRating;
import tech.torbay.projectservice.entity.VendorBid;
import tech.torbay.projectservice.entity.VendorCategoryRatings;
import tech.torbay.projectservice.entity.VendorProjectInterests;
import tech.torbay.projectservice.entity.VendorUser;
import tech.torbay.projectservice.exception.BadRequestException;
import tech.torbay.projectservice.exception.ResourceNotFoundException;
import tech.torbay.projectservice.repository.BidFilesRepository;
import tech.torbay.projectservice.repository.ClientOrganisationRepository;
import tech.torbay.projectservice.repository.ClientUserRepository;
import tech.torbay.projectservice.repository.PredefinedTagsRepository;
import tech.torbay.projectservice.repository.ProjectFilesRepository;
import tech.torbay.projectservice.repository.ProjectProductsRepository;
import tech.torbay.projectservice.repository.ProjectQARepository;
import tech.torbay.projectservice.repository.ProjectRepository;
import tech.torbay.projectservice.repository.ProjectReviewRatingRepository;
import tech.torbay.projectservice.repository.VendorBidRepository;
import tech.torbay.projectservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.projectservice.repository.VendorProjectInterestsRepository;
import tech.torbay.projectservice.repository.VendorUserRepository;

@Component
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ProjectProductsRepository projectProductsRepository;
	@Autowired
	VendorBidRepository vendorBidRepository;
	@Autowired
	ProjectQARepository projectQARepository;
	@Autowired
	ProjectReviewRatingRepository projectReviewRatingRepository;
	@Autowired
	PredefinedTagsRepository predefinedTagsRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	VendorProjectInterestsRepository vendorProjectInterestsRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;
	@Autowired
	ProjectFilesRepository projectFilesRepository;
	@Autowired
	BidFilesRepository bidFilesRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

	public List<Map<String,Object>>  findAllProjects() {
//		// TODO Auto-generated method stub
		
		List<Object[]> projects = projectRepository.findAllProjectsForMarketPlace();
		
		List<Map<String,Object>> allProjects = new ArrayList();
		
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
				
						
				allProjects.add(map);
	        }
	        
	        
		 });
		 
		 
		return allProjects;
	}

	public Map<String,Object> findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findByProjectId(projectId); 
		
		List<Integer> ids = Stream.of(project.getTags().split(","))
		        .map(Integer::parseInt)
		        .collect(Collectors.toList());
		
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
		
		Map<String,Object> map = new HashMap<>();
		
		ObjectMapper oMapper = new ObjectMapper();
		
		map = oMapper.convertValue(project, Map.class);
		
		map.put("tags",allTags);
		
		List<Integer> openByIds = new ArrayList(); 
		
		openByIds.add(project.getClientId());
		openByIds.add(project.getProjectModifiedBy());
		
		List<ClientUser> projectModifiedByClients = clientUserRepository.findByClientId(openByIds); 
		
		List<String> names = new ArrayList();
		
		for(ClientUser user : projectModifiedByClients) {
			
			names.add(user.getFirstName()+" "+user.getLastName());
		}
		
		map.put("openBy",String.join(",",names));

		map.put("projectFiles",GetProjectFiles(projectId));
		
		return map;
	}

	public Project createProject(Project project) {
		// TODO Auto-generated method stub
		try {
		
			project.setDuration(calculateDuration(project.getDuration(), project.getProjectStartDate(), project.getProjectCompletionDeadline()));
			Project projectObj = projectRepository.save(project);
//			int id = projectObj.getProjectId();
//			logger.info("project id" + id);
			
			return projectObj;
			
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public String calculateDuration(String emptyDuration, String startingDate, String endingDate) {
		// TODO Auto-generated method stub
		
		try {
			LocalDate startDate = LocalDate.parse(startingDate);
			LocalDate endDate = LocalDate.parse(endingDate);
			Period period = Period.between(startDate, endDate); 
			String projectDuration = period.toString().replace("P", "").replace("Y", " Year ").replace("M", " Months ").replace("D", " Days");
			
			return projectDuration;
		} catch(Exception exp) {
			logger.error("Exception :"+exp);
		}
		return emptyDuration;
		
	}

	public VendorBid createProjectBid(VendorBid vendorBid) {
		// TODO Auto-generated method stub
		try {
			
			vendorBid.setVendorProjectDuration(calculateDuration(vendorBid.getVendorProjectDuration(), vendorBid.getVendorStartDate(),vendorBid.getVendorEndDate()));
			
			return vendorBidRepository.save(vendorBid);
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
	}
	
	public Object findByBidId(Integer vendorBidId) {
		// TODO Auto-generated method stub
		
		VendorBid vendorBid = vendorBidRepository.findOneById(vendorBidId);
		
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(vendorBid, Map.class);
		
		map.put("bidFiles", GetVendorBidFiles(vendorBid.getId()));
		
		return map;
	}

	public List<Map<String,Object>> getAllProjects(ProjectSortBy past, Integer id) {
		// TODO Auto-generated method stub
		
		List<Project> projects = new ArrayList();
		
		switch(past.getValue()) {
			case 0: {
//				All - 0
//				break;
			}
			case 1:{
//				Current - 1-unpublished and 2-published
				projects = projectRepository.findAllCurrentProjects(id);
				break;
			}
			case 2:{
//				Past - 
				projects = projectRepository.findAllAwardedProjects(id);
				break;
			}
		}
		
		
		List<Map<String,Object>> allProjects = new ArrayList();
		
		for(Project project : projects) {
			List<Integer> ids = Stream.of(project.getTags().trim().split(","))
			        .map(Integer::parseInt)
			        .collect(Collectors.toList());
			
			project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
			
			
			Map<String,Object> map = new HashMap<>();
			
			ObjectMapper oMapper = new ObjectMapper();
			
			map = oMapper.convertValue(project, Map.class);
			
			
			map.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
			map.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId()));
			
					
			allProjects.add(map);
		}
		
		return allProjects;
	}

	public ProjectQuestionAnswer createProjectQuestion(ProjectQuestionAnswer projectQA) {
		// TODO Auto-generated method stub
		return projectQARepository.save(projectQA);
	}

	public List<Map<String,Object>> getAllBidsByProjectId(Integer id) {
		// TODO Auto-generated method stub
		
		List<VendorBid> vendorBids = vendorBidRepository.findVendorBidByProjectId(id);
		
		List<Map<String,Object>> allBids = new ArrayList();
		
		for(VendorBid vendorBid : vendorBids) {
			
			Map<String,Object> map = new HashMap<>();
			
			ObjectMapper oMapper = new ObjectMapper();
			
			map = oMapper.convertValue(vendorBid, Map.class);
			
			
			map.put("rating",getVendorCategoryRatings(vendorBid.getVendorOrgId()));
			
					
			allBids.add(map);
		}
		
		return allBids;
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
        	
        	if(vendorRatings == null) {
        		return 0d;
        	}
        	
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
        	
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue() "+ sumCategoryResponsiveness/responsivenessCount * Constants.VendorRatingCategoryPercentage.RESPONSIVENESS.getValue()/100);
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue() "+ sumCategoryProfessionalism/responsivenessCount * Constants.VendorRatingCategoryPercentage.PROFESSIONALISM.getValue()/100);
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.ACCURACY.getValue() "+ sumCategoryAccuracy/accuracyCount *Constants.VendorRatingCategoryPercentage.ACCURACY.getValue()/100);
        	System.out.print("sumCategoryResponsiveness/Constants.VendorRatingCategoryPercentage.QUALITY.getValue() "+ sumCategoryQuality/qualityCount *Constants.VendorRatingCategoryPercentage.QUALITY.getValue()/100);
        	
        	
        	return overAllRating;
        	
        } catch(Exception exp) {
        	exp.printStackTrace();
        	return 0d;
        }
	}

	public List<ProjectQuestionAnswer> getAllQAByProjectId(Integer id) {
		// TODO Auto-generated method stub
		return projectQARepository.findProjectQuestionAnswerByProjectId(id);
	}

	public ProjectQuestionAnswer answerProjectQuestion(ProjectQuestionAnswer projectQA) {
		// TODO Auto-generated method stub
		
		ProjectQuestionAnswer qaObj = projectQARepository.findOneByProjectqaId(projectQA.getProjectqaId());
		qaObj.setAnswer(projectQA.getAnswer());
		qaObj.setClientUserId(projectQA.getClientUserId());
		System.out.println(qaObj.toString());
		
		return projectQARepository.save(qaObj);
	}

	public List<Project> getAllProjects(Integer id) {
		// TODO Auto-generated method stub
		List<Project> projects = projectRepository.findAll();
		
		for(Project project : projects) {
			List<Integer> ids = Stream.of(project.getTags().split(","))
			        .map(Integer::parseInt)
			        .collect(Collectors.toList());
			
			project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		}
		
		return projects;
	}

	public Project updateProject(Project project) {
		// TODO Auto-generated method stub
		try {
			return projectRepository.save(project);
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public Project publishProject(Integer projectId) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findOneByProjectId(projectId);
		project.setStatus(Constants.ProjectPostType.PUBLISHED.getValue());
		
		return projectRepository.save(project);
	}

	public VendorBid publishVendorBid(Integer bidId) {
		// TODO Auto-generated method stub
		VendorBid vendorBid = vendorBidRepository.findOneById(bidId);
		vendorBid.setBidStatus(Constants.BidPostType.PUBLISHED.getValue());
		return vendorBidRepository.save(vendorBid);
	}

	public ProjectReviewRating postProjectReview(ProjectReviewRating projectReviewRating) {
		// TODO Auto-generated method stub
		return projectReviewRatingRepository.save(projectReviewRating);
	}

	public ProjectReviewRating replyProjectReview(ProjectReviewRating projectReviewRating) {
		// TODO Auto-generated method stub
		ProjectReviewRating projectRRObj = projectReviewRatingRepository.findOneById(projectReviewRating.getId());
		projectRRObj.setReplyComments(projectReviewRating.getReplyComments());
		projectRRObj.setVendorId(projectReviewRating.getVendorId());
		
//		return projectReviewRatingRepository.setReplyComments(projectReviewRating.getId(), projectReviewRating.getReplyComments());
		return projectReviewRatingRepository.save(projectRRObj);
	}

	public List<PredefinedTags> getAllPredefinedTags() {
		// TODO Auto-generated method stub
		return predefinedTagsRepository.findAll();
	}

	public VendorProjectInterests updateVendorProjectInterest(Map<String, Object> requestData) {
		// TODO Auto-generated method stub
		
		Integer projectId = Integer.parseInt(String.valueOf(requestData.get("projectId")));
    	Integer vendorOrganisationId = Integer.parseInt(String.valueOf(requestData.get("vendorOrganisationId")));
    	Integer vendorId = Integer.parseInt(String.valueOf(requestData.get("vendorId")));
    	Integer interestStatus = Integer.parseInt(String.valueOf(requestData.get("interestStatus")));
    	
    	VendorProjectInterests vendorProjectInterests = new VendorProjectInterests();
    	vendorProjectInterests.setInterestStatus(interestStatus);
    	vendorProjectInterests.setProjectId(projectId);
    	vendorProjectInterests.setVendorId(vendorId);
    	vendorProjectInterests.setVendorOrganisationId(vendorOrganisationId);
    	
    	VendorProjectInterests interest = vendorProjectInterestsRepository.findByProjectIdAndVendorOrganisationId(projectId, vendorOrganisationId);
    	if( interest == null) {
    		return vendorProjectInterestsRepository.save(vendorProjectInterests);
    	} else {
    		interest.setInterestStatus(interestStatus);
    		interest.setVendorId(vendorId);
    		return vendorProjectInterestsRepository.save(interest);
    	}
		
	}

	public List<Map<String, Object>> getVendorCurrentProjects(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		try {
			List<Object[]> vendorBids = vendorBidRepository.findCurrentProjectsByVendorOrgId(vendorOrganisationId);
			
			List<Map<String,Object>> allProjects = new ArrayList();
			
			vendorBids.stream().forEach((record) -> {
		        VendorBid vendorBid = (VendorBid) record[0]; 
		        Project project = (Project) record[1];
		        String companyName = (String) record[2];
		        
		        Map<String,Object> projectMap = new HashMap<>();
		        Map<String,Object> vendorBidMap = new HashMap<>();
				
				ObjectMapper oMapper = new ObjectMapper();
				
				if (project.getStatus() == Constants.ProjectPostType.PUBLISHED.getValue() /* && check project bid end date, start, completion date */) {
					
					List<Integer> ids = Stream.of(project.getTags().trim().split(","))
					        .map(Integer::parseInt)
					        .collect(Collectors.toList());
			        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
					
					projectMap = oMapper.convertValue(project, Map.class);
					
					try {
//						projectMap.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
//						projectMap.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId()));
					} catch(Exception exp) {
						exp.printStackTrace();
					}
					projectMap.put("condoName", companyName);
//					projectMap.put("projectCreatedBy", firstName+" "+lastName);
					projectMap.put("vendorBid", vendorBid);
							
					allProjects.add(projectMap);
				}
				
			 });
			
			return allProjects;
			
		} catch(Exception exp) {
			exp.printStackTrace();
			
			return null;
		}
		
	}
	
	public List<Map<String, Object>> getVendorHistoryProjects(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		try {
			List<Object[]> vendorBids = vendorBidRepository.findHistoryProjectsByVendorOrgId(vendorOrganisationId);
			
			List<Map<String,Object>> allProjects = new ArrayList();
			
			vendorBids.stream().forEach((record) -> {
		        VendorBid vendorBid = (VendorBid) record[0]; 
		        Project project = (Project) record[1];
		        String companyName = (String) record[2];
		        
		        Map<String,Object> projectMap = new HashMap<>();
		        Map<String,Object> vendorBidMap = new HashMap<>();
				
				ObjectMapper oMapper = new ObjectMapper();
				
				
				if (project.getStatus() == Constants.ProjectPostType.COMPLETED.getValue() || project.getStatus() == Constants.ProjectPostType.TERMINATED.getValue() /* && check project bid end date, start, completion date */) {
					
					List<Integer> ids = Stream.of(project.getTags().trim().split(","))
					        .map(Integer::parseInt)
					        .collect(Collectors.toList());
			        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
					
					projectMap = oMapper.convertValue(project, Map.class);
					
					try {
//						projectMap.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
//						projectMap.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId()));
					} catch(Exception exp) {
						exp.printStackTrace();
					}
					projectMap.put("condoName", companyName);
//					projectMap.put("projectCreatedBy", firstName+" "+lastName);
					projectMap.put("vendorBid", vendorBid);
							
					allProjects.add(projectMap);
				}
					
				
				
				
			 });
			
			return allProjects;
			
		} catch(Exception exp) {
			exp.printStackTrace();
			
			return null;
		}
		
	}

	public List<Map<String, Object>> getVendorFavoriteProjects(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<VendorProjectInterests> vendorProjectInterests =  vendorProjectInterestsRepository.findByVendorOrganisationId(vendorOrganisationId);
		
		List<Integer> projectIds = vendorProjectInterests.stream()
                .map(VendorProjectInterests::getProjectId).collect(Collectors.toList());
		
		List<Project> allInterestedProjects = new ArrayList();
		if(projectIds != null && projectIds.size() > 0) {
			allInterestedProjects = projectRepository.getAllVendorProjects(projectIds);
		}
		
		List<Map<String,Object>> favoriteProjects = new ArrayList();
		
		for(Project project : allInterestedProjects) {
			
			if (project.getStatus() == Constants.ProjectPostType.PUBLISHED.getValue()) {
				
				List<Integer> ids = Stream.of(project.getTags().trim().split(","))
				        .map(Integer::parseInt)
				        .collect(Collectors.toList());
		        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		       
				ObjectMapper oMapper = new ObjectMapper();
		        // object -> Map
		        Map<String, Object> map = oMapper.convertValue(project, Map.class);
		        
		        List<VendorBid> vendorBid = vendorBidRepository.findVendorBidByProjectId(project.getProjectId());
		        
//				if(vendorBid == null || vendorBid.size() == 0) {// once bidded its moved to current projects
					favoriteProjects.add(map);
//				}
			}// only vendor bided projects comes under history favorite projects only in favorite list once bided then only comes for future reference
			
		}
		
		return favoriteProjects;
	}

	public List<Map<String, Object>> findAllProjectsForVendorMarketplace(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		List<Object[]> projects = projectRepository.findAllProjectsForMarketPlace();
//		List<VendorProjectInterests> vendorProjectInterests =  vendorProjectInterestsRepository.findByVendorId(vendorId);
		
		
		List<Map<String,Object>> allProjects = new ArrayList();
		
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
				
						
				allProjects.add(map);
	        }
	        
	        
		 });
		 
		 
		return allProjects;
	}

	public Map<String, Object> getProjectForVendor(Integer projectId, Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findByProjectId(projectId); 
		
		List<Integer> ids = Stream.of(project.getTags().split(","))
		        .map(Integer::parseInt)
		        .collect(Collectors.toList());
		List<Object[]> tags = new ArrayList();
		if(ids != null && ids.size() > 0) {
			tags = predefinedTagsRepository.findTagsByTagId(ids);
		}
		List<Map<String,Object>> allTags = new ArrayList();
		
		tags.stream().forEach((record) -> {
			Integer tagId = (Integer) record[0];
			String tagName = (String) record[1];
			
			
			Map<String,Object> map = new HashMap<>();
	        map.put("tagId", tagId);
	        map.put("tagName", tagName);
	        
	        
	        allTags.add(map);
	        });
		
		Map<String,Object> map = new HashMap<>();
		
		ObjectMapper oMapper = new ObjectMapper();
		
		map = oMapper.convertValue(project, Map.class);
		
		map.put("tags",allTags);
		
		Map<String,Object> vendorBid = GetVendorBidForProject(projectId, vendorOrganisationId);
		
		map.put("vendorBid",vendorBid); // null if not placed a bid
		map.put("questionAnswers",projectQARepository.findProjectQuestionAnswerByProjectId(projectId));
		VendorProjectInterests vendorProjectInterests = vendorProjectInterestsRepository.findByProjectIdAndVendorOrganisationId( projectId, vendorOrganisationId);
		
		if(vendorProjectInterests != null && vendorProjectInterests.getInterestStatus() == Constants.ProjectInterestStatus.LIKE.getValue()) {
			map.put("isInterested", true);
		} else {
			map.put("isInterested", false);
		}
		
		map.put("projectFiles",GetProjectFiles(projectId));
		
		return map;
	}

	private List<Map<String, Object>> GetProjectFiles(Integer projectId) {
		// TODO Auto-generated method stub
		List<ProjectFiles> projectFiles = projectFilesRepository.findAllByProjectId(projectId);
		
		List<Map<String, Object>> files = new ArrayList();
		for(ProjectFiles projectFile : projectFiles) {
			Map<String, Object> obj = new HashMap<>();
			
			obj.put("id", projectFile.getId());
			obj.put("fileName", projectFile.getFileName());
			obj.put("fileType", projectFile.getFileType());
			obj.put("fileSize", Utils.formatFileSize(Long.parseLong(projectFile.getFileSize())));
			obj.put("blobName", projectFile.getBlobName());
			obj.put("containerName", projectFile.getContainerName());
//			obj.put("fileUrl", projectFile.getFileUrl());
			obj.put("createdAt", projectFile.getCreatedAt());
			
			files.add(obj);
		}
		
		return files;
		
//		return projectFiles;
	}
	
	private Map<String, Object> GetVendorBidForProject(Integer projectId, Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		VendorBid vendorBid = vendorBidRepository.findVendorBidByProjectIdAndVendorOrgId(projectId, vendorOrganisationId);
		
		if(vendorBid != null) {
			ObjectMapper oMapper = new ObjectMapper();
			Map<String, Object> map = oMapper.convertValue(vendorBid, Map.class);
			
			map.put("bidFiles", GetVendorBidFiles(vendorBid.getId()));
			
			return map;
		} else {
			return null;
		}
		
	}

	private Object GetVendorBidFiles(Integer bidId) {
		// TODO Auto-generated method stub
		List<BidFiles> bidFiles = bidFilesRepository.findAllByBidId(bidId);
		
		List<Map<String, Object>> files = new ArrayList();
		for(BidFiles bidFile : bidFiles) {
			Map<String, Object> obj = new HashMap<>();
			
			obj.put("id", bidFile.getId());
			obj.put("fileName", bidFile.getFileName());
			obj.put("fileType", bidFile.getFileType());
			obj.put("fileSize", bidFile.getFileSize());
			obj.put("blobName", bidFile.getBlobName());
			obj.put("containerName", bidFile.getContainerName());
//			obj.put("fileUrl", bidFile.getFileUrl());
			obj.put("createdAt", bidFile.getCreatedAt());
			
			files.add(obj);
		}
		
		return files;
	}

	public VendorBid findvendorBidByVendorIdAndProjectId(VendorBid vendorBid) {
		// TODO Auto-generated method stub
		return vendorBidRepository.findVendorBidByProjectIdAndVendorOrgId(vendorBid.getProjectId(), vendorBid.getVendorOrgId());
	}

	public void UpdateProjectStatus() {
		// TODO Auto-generated method stub
		
	}

	public boolean checkIsProjectBiddingClosed(Integer projectId) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findByProjectId(projectId);
		
		return Utils.checkDateCrossed(project.getBidEndDate());
	}

	public boolean checkIsProjectBiddingClosedByBidId(Integer bidId) {
		// TODO Auto-generated method stub
		VendorBid vendorBid = vendorBidRepository.findOneById(bidId);
		
		return checkIsProjectBiddingClosed(vendorBid.getProjectId());
	}

}

