package tech.torbay.projectservice.service;

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
import tech.torbay.projectservice.entity.PredefinedTags;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.ProjectReviewRating;
import tech.torbay.projectservice.entity.VendorBid;
import tech.torbay.projectservice.entity.VendorProjectInterests;
import tech.torbay.projectservice.repository.ClientOrganisationRepository;
import tech.torbay.projectservice.repository.PredefinedTagsRepository;
import tech.torbay.projectservice.repository.ProjectProductsRepository;
import tech.torbay.projectservice.repository.ProjectQARepository;
import tech.torbay.projectservice.repository.ProjectRepository;
import tech.torbay.projectservice.repository.ProjectReviewRatingRepository;
import tech.torbay.projectservice.repository.VendorBidRepository;
import tech.torbay.projectservice.repository.VendorProjectInterestsRepository;

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
		
		return map;
	}

	public Project createProject(Project project) {
		// TODO Auto-generated method stub
		try {
		
			project.setDuration(calculateDuration(project.getDuration(), project.getProjectStartDate(), project.getProjectCompletionDeadline()));
			project = projectRepository.save(project);
//			int id = project.getProjectId();
//			logger.info("project id" + id);
			
			return project;
			
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	private String calculateDuration(String emptyDuration, String startingDate, String endingDate) {
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
	
	public VendorBid findByBidId(Integer vendorBidId) {
		// TODO Auto-generated method stub
		return vendorBidRepository.findOneById(vendorBidId);
	}

	public List<Map<String,Object>> getAllProjects(ProjectSortBy past, Integer id) {
		// TODO Auto-generated method stub
		
		List<Project> projects = projectRepository.findAllByClientOrganisationIdAndStatus(id, past.getValue());
		
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

	public List<VendorBid> getAllBidsByProjectId(Integer id) {
		// TODO Auto-generated method stub
		return vendorBidRepository.findVendorBidByProjectId(id);
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
    	Integer vendorId = Integer.parseInt(String.valueOf(requestData.get("vendorId")));
    	Integer interestStatus = Integer.parseInt(String.valueOf(requestData.get("interestStatus")));
    	
    	VendorProjectInterests vendorProjectInterests = new VendorProjectInterests();
    	vendorProjectInterests.setInterestStatus(interestStatus);
    	vendorProjectInterests.setProjectId(projectId);
    	vendorProjectInterests.setVendorId(vendorId);
    	
    	VendorProjectInterests interest = vendorProjectInterestsRepository.findByProjectIdAndVendorId(projectId, vendorId);
    	if( interest == null) {
    		return vendorProjectInterestsRepository.save(vendorProjectInterests);
    	} else {
    		interest.setInterestStatus(interestStatus);
    		return vendorProjectInterestsRepository.save(interest);
    	}
		
	}

	public List<Map<String, Object>> getVendorCurrentProjects(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		try {
			List<Object[]> vendorBids = vendorBidRepository.findByVendorOrgId(vendorOrganisationId);
			
			List<Map<String,Object>> allProjects = new ArrayList();
			
			vendorBids.stream().forEach((record) -> {
		        VendorBid vendorBid = (VendorBid) record[0]; 
		        Project project = (Project) record[1];
		        String companyName = (String) record[2];
		        
		        
		        List<Integer> ids = Stream.of(project.getTags().trim().split(","))
				        .map(Integer::parseInt)
				        .collect(Collectors.toList());
		        project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		       
		        Map<String,Object> projectMap = new HashMap<>();
		        Map<String,Object> vendorBidMap = new HashMap<>();
				
				ObjectMapper oMapper = new ObjectMapper();
				
				projectMap = oMapper.convertValue(project, Map.class);
				
				
				
				try {
					projectMap.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
					projectMap.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId()));
				} catch(Exception exp) {
					exp.printStackTrace();
				}
				projectMap.put("condoName", companyName);
//				projectMap.put("projectCreatedBy", firstName+" "+lastName);
				projectMap.put("vendorBid", vendorBid);
				
				
						
				allProjects.add(projectMap);
			 });
			
			return allProjects;
			
		} catch(Exception exp) {
			exp.printStackTrace();
			
			return null;
		}
		
		
		
	}

}

