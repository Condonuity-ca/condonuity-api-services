package tech.torbay.projectservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import tech.torbay.projectservice.constants.Constants;
import tech.torbay.projectservice.constants.Constants.ProjectSortBy;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.VendorBid;
import tech.torbay.projectservice.repository.ProjectQARepository;
import tech.torbay.projectservice.repository.ProjectRepository;
import tech.torbay.projectservice.repository.VendorBidRepository;

@Component
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	VendorBidRepository vendorBidRepository;
	@Autowired
	ProjectQARepository projectQARepository;

	public List<Project> findAll() {
//		// TODO Auto-generated method stub
		return Lists.newArrayList(projectRepository.findAll());
	}

	public Project findByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return projectRepository.findByProjectId(projectId);
	}

	public Project createProject(Project project) {
		// TODO Auto-generated method stub
		try {
		return projectRepository.save(project);
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public VendorBid createProjectBid(VendorBid vendorBid) {
		// TODO Auto-generated method stub
		return vendorBidRepository.save(vendorBid);
	}
	
	public VendorBid findByBidId(Integer vendorBidId) {
		// TODO Auto-generated method stub
		return vendorBidRepository.findOneById(vendorBidId);
	}

	public List<Project> getAllProjects(ProjectSortBy past, Integer id) {
		// TODO Auto-generated method stub
		return projectRepository.findAllByClientOrganisationIdAndStatus(id, past.getValue());
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
		return projectRepository.findAll();
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
		vendorBid.setBidStatus(Constants.ProjectPostType.PUBLISHED.getValue());
		return vendorBidRepository.save(vendorBid);
	}
}

