package tech.torbay.projectservice.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.projectservice.constants.Constants;
import tech.torbay.projectservice.constants.Constants.ProjectSortBy;
import tech.torbay.projectservice.entity.PredefinedTags;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.ProjectReviewRating;
import tech.torbay.projectservice.entity.VendorBid;
import tech.torbay.projectservice.repository.PredefinedTagsRepository;
import tech.torbay.projectservice.repository.ProjectProductsRepository;
import tech.torbay.projectservice.repository.ProjectQARepository;
import tech.torbay.projectservice.repository.ProjectRepository;
import tech.torbay.projectservice.repository.ProjectReviewRatingRepository;
import tech.torbay.projectservice.repository.VendorBidRepository;

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
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

	public List<Project> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(projectRepository.findAll());
	}

	public Project findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findByProjectId(projectId); 
		
		List<Integer> ids = Stream.of(project.getTags().split(","))
		        .map(Integer::parseInt)
		        .collect(Collectors.toList());
		
		project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		
		return project;
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
			String projectDuration = period.toString().replace("P", "").replace("Y", " Year ").replace("M", " Months ").replace("D", " Days ");
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

	public List<Project> getAllProjects(ProjectSortBy past, Integer id) {
		// TODO Auto-generated method stub
		
		List<Project> projects = projectRepository.findAllByClientOrganisationIdAndStatus(id, past.getValue());
		
		for(Project project : projects) {
			List<Integer> ids = Stream.of(project.getTags().trim().split(","))
			        .map(Integer::parseInt)
			        .collect(Collectors.toList());
			
			project.setTags(predefinedTagsRepository.findByTagId(ids).stream().collect(Collectors.joining(",")));
		}
		
		return projects;
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
}

