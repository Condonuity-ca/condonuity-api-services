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
import tech.torbay.projectservice.constants.Constants.BidPostType;
import tech.torbay.projectservice.constants.Constants.DeleteStatus;
import tech.torbay.projectservice.constants.Constants.ProjectInterestStatus;
import tech.torbay.projectservice.constants.Constants.ProjectPostTo;
import tech.torbay.projectservice.constants.Constants.ProjectPostType;
import tech.torbay.projectservice.constants.Constants.ProjectSortBy;
import tech.torbay.projectservice.constants.Constants.UserType;
import tech.torbay.projectservice.entity.BidFiles;
import tech.torbay.projectservice.entity.ClientOrganisation;
import tech.torbay.projectservice.entity.ClientUser;
import tech.torbay.projectservice.entity.Notification;
import tech.torbay.projectservice.entity.PredefinedTags;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectAwards;
import tech.torbay.projectservice.entity.ProjectFiles;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.ProjectReviewRating;
import tech.torbay.projectservice.entity.ServiceCities;
import tech.torbay.projectservice.entity.UserWishList;
import tech.torbay.projectservice.entity.VendorBid;
import tech.torbay.projectservice.entity.VendorCategoryRatings;
import tech.torbay.projectservice.entity.VendorOrganisation;
import tech.torbay.projectservice.entity.VendorOrganisationProfileImages;
import tech.torbay.projectservice.entity.VendorProjectInterests;
import tech.torbay.projectservice.repository.BidFilesRepository;
import tech.torbay.projectservice.repository.ClientOrganisationRepository;
import tech.torbay.projectservice.repository.ClientUserRepository;
import tech.torbay.projectservice.repository.NotificationRepository;
import tech.torbay.projectservice.repository.PredefinedTagsRepository;
import tech.torbay.projectservice.repository.ProjectAwardFilesRepository;
import tech.torbay.projectservice.repository.ProjectAwardsRepository;
import tech.torbay.projectservice.repository.ProjectFilesRepository;
import tech.torbay.projectservice.repository.ProjectProductsRepository;
import tech.torbay.projectservice.repository.ProjectQARepository;
import tech.torbay.projectservice.repository.ProjectRepository;
import tech.torbay.projectservice.repository.ProjectReviewRatingRepository;
import tech.torbay.projectservice.repository.ServiceCitiesRepository;
import tech.torbay.projectservice.repository.UserWishListRepository;
import tech.torbay.projectservice.repository.VendorBidRepository;
import tech.torbay.projectservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.projectservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.projectservice.repository.VendorOrganisationRepository;
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
	VendorOrganisationRepository vendorOrganisationRepository;
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorCategoryRatingsRepository vendorCategoryRatingsRepository;
	@Autowired
	ProjectFilesRepository projectFilesRepository;
	@Autowired
	BidFilesRepository bidFilesRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	ProjectAwardsRepository projectAwardsRepository;
	@Autowired
	ProjectAwardFilesRepository projectAwardFilesRepository;
	@Autowired
	VendorOrganisationProfileImagesRepository vendorOrganisationProfileImagesRepository;
	@Autowired
	ServiceCitiesRepository servicesCitiesRepository;
	@Autowired
	UserWishListRepository userWishListRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

	public List<Map<String,Object>>  findAllProjects() {
//		// TODO Auto-generated method stub
		
		checkIsProjectsClosed();
		
		List<Object[]> projects = projectRepository.findAllProjectsForMarketPlace();
		
		List<Map<String,Object>> allProjects = new ArrayList();
		
		 projects.stream().forEach((record) -> {
	        Project project = (Project) record[0];
	        String managementCompany = (String) record[1];
	        String condoName = (String) record[2];
	        String condoCity = (String) record[3];
	        String firstName = (String) record[4];
	        String lastName = (String) record[5];
	        
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
				map.put("managementCompany", managementCompany);
				map.put("condoName", condoName);
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//				projectReview.put("condoName", clientOrganisation.getOrganisationName());
				map.put("condoCity", getCityName(clientOrganisation.getCity()));
				map.put("city", getCityName(clientOrganisation.getCity()));
//				map.put("condoCity", getCityName(condoCity));
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
		
		String managementCompany = "";
        String condoName = "";
        String condoCity = "";
        
        ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
        if(clientOrganisation !=null) {
        	managementCompany = clientOrganisation.getManagementCompany();
        	condoName = clientOrganisation.getOrganisationName();
        	condoCity = getCityName(clientOrganisation.getCity());
        }
		
        map.put("managementCompany",managementCompany);
        map.put("condoName",condoName);
        map.put("condoCity",condoCity);
        map.put("city",condoCity);
        
		return map;
	}

	public Project createProject(Project project) {
		// TODO Auto-generated method stub
		try {
			project.setDeleteStatus(DeleteStatus.ACTIVE.getValue());
			ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
			project.setCity(clientOrganisation.getCity());
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
			
//			vendorBid.setVendorProjectDuration(calculateDuration(vendorBid.getVendorProjectDuration(), vendorBid.getVendorStartDate(),vendorBid.getVendorEndDate()));
			// only project startdate and duration given as input
			return vendorBidRepository.save(vendorBid);
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
		
	}
	
	public Object findByBidId(Integer vendorBidId) {
		// TODO Auto-generated method stub
		
		VendorBid vendorBid = vendorBidRepository.findOneById(vendorBidId);
		
		if(vendorBid != null ) {
			ObjectMapper oMapper = new ObjectMapper();
			Map<String, Object> map = oMapper.convertValue(vendorBid, Map.class);
			
			map.put("bidFiles", GetVendorBidFiles(vendorBid.getId()));
			return map;
		}
		
		
		return null;
	}

	public List<Map<String,Object>> getAllProjects(ProjectSortBy past, Integer id) {
		// TODO Auto-generated method stub
		checkIsProjectsClosed();
		
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
			
			ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//			projectReview.put("condoName", clientOrganisation.getOrganisationName());
//			projectReview.put("condoCity", clientOrganisation.getCity());
			project.setCity(getCityName(clientOrganisation.getCity()));
			
			
			Map<String,Object> map = new HashMap<>();
			
			ObjectMapper oMapper = new ObjectMapper();
			
			map = oMapper.convertValue(project, Map.class);
			
			
			map.put("bidCount",vendorBidRepository.getProjectBidsCount(project.getProjectId()));
			map.put("interestCount", vendorProjectInterestsRepository.getProjectInterestCount(project.getProjectId()));
			
			allProjects.add(map);
		}
		
		return allProjects;
	}

	private void checkIsProjectsClosed() {
		// TODO Auto-generated method stub
		projectRepository.updateClosedProjects();
	}

	public ProjectQuestionAnswer createProjectQuestion(ProjectQuestionAnswer projectQA) {
		// TODO Auto-generated method stub
		return projectQARepository.save(projectQA);
	}

	public List<Map<String,Object>> getAllBidsByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> allBids = new ArrayList();
		
		Project project = projectRepository.findByProjectId(projectId);
//		if(project.getStatus().equals(3) || project.getStatus().equals(4) ) {
			List<VendorBid> vendorBids = vendorBidRepository.findVendorBidByProjectId(projectId);
			
			for(VendorBid vendorBid : vendorBids) {
				
				Map<String,Object> map = new HashMap<>();
				
				ObjectMapper oMapper = new ObjectMapper();
				
				map = oMapper.convertValue(vendorBid, Map.class);
				
				VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorBid.getVendorOrgId());
				
				map.put("bidFiles",GetVendorBidFiles(vendorBid.getId()));
				try {
			        String logo = getOrganisationLogo(vendorBid.getVendorOrgId());
			        if(logo != null)
			        	map.put("vendorOrganisationProfileImage",logo);
			        else
			        	map.put("vendorOrganisationProfileImage","");
		        } catch(Exception exp) {
			        	exp.printStackTrace();
		        }
				
				map.put("organisationName",vendorOrganisation.getCompanyName());
				map.put("legalName",vendorOrganisation.getLegalName());
				map.put("rating",getVendorCategoryRatings(vendorBid.getVendorOrgId()));
				
						
				allBids.add(map);
			}
//		}
		
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
	
	public String getOrganisationLogo(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		VendorOrganisationProfileImages vendorOrgProfileImage =  vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(vendorOrganisationId);
		
        if(vendorOrgProfileImage != null)
        	return vendorOrgProfileImage.getFileUrl();
        else
        	return null;
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
		checkIsProjectsClosed();
		
		// TODO Auto-generated method stub
		List<Project> projects = projectRepository.findAll();
		
		for(Project project : projects) {
			List<Integer> ids = Stream.of(project.getTags().split(","))
			        .map(Integer::parseInt)
			        .collect(Collectors.toList());
			
			ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//			projectReview.put("condoName", clientOrganisation.getOrganisationName());
//			projectReview.put("condoCity", clientOrganisation.getCity());
			project.setCity(getCityName(clientOrganisation.getCity()));
			
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

	public VendorBid updateProjectBid(VendorBid vendorBid) {
		// TODO Auto-generated method stub
		try {
//			check, which fields need to update
			return vendorBidRepository.save(vendorBid);
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
	
	public VendorBid pullVendorBid(Integer bidId) {
		// TODO Auto-generated method stub
		VendorBid vendorBid = vendorBidRepository.findOneById(bidId);
		vendorBid.setBidStatus(Constants.BidPostType.PULLED.getValue());
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
			checkIsProjectsClosed();
			
			List<Object[]> vendorBids = vendorBidRepository.findCurrentProjectsByVendorOrgId(vendorOrganisationId);
			
			List<Map<String,Object>> allProjects = new ArrayList();
			
			vendorBids.stream().forEach((record) -> {
		        VendorBid vendorBid = (VendorBid) record[0]; 
		        Project project = (Project) record[1];
		        String managementCompany = (String) record[2];
		        String condoName = (String) record[3];
		        String condoCity = (String) record[4];
		        
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
					projectMap.put("managementCompany", managementCompany);
					projectMap.put("condoName", condoName);
//					projectMap.put("condoCity", getCityName(condoCity));
//					projectMap.put("city", getCityName(condoCity));
					ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
					projectMap.put("condoCity", getCityName(clientOrganisation.getCity()));
					projectMap.put("city", getCityName(clientOrganisation.getCity()));
//					projectMap.put("projectCreatedBy", firstName+" "+lastName);
					projectMap.put("vendorBid", vendorBid);
							
					if(vendorBid.getBidStatus() == BidPostType.PUBLISHED.getValue()) {
					allProjects.add(projectMap);
					}
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
		// Only vendor bided and completed projects only comes under vendor history - (excluded - vendor interests)
		try {
			checkIsProjectsClosed();
			
			List<Object[]> vendorBids = vendorBidRepository.findHistoryProjectsByVendorOrgId(vendorOrganisationId);
			
			List<Map<String,Object>> allProjects = new ArrayList();
			
			vendorBids.stream().forEach((record) -> {
		        VendorBid vendorBid = (VendorBid) record[0]; 
		        Project project = (Project) record[1];
		        String managementCompany = (String) record[2];
		        String condoName = (String) record[3];
		        String condoCity = (String) record[4];
		        
		        Map<String,Object> projectMap = new HashMap<>();
		        Map<String,Object> vendorBidMap = new HashMap<>();
				
				ObjectMapper oMapper = new ObjectMapper();
				
				
				if (project.getStatus() == Constants.ProjectPostType.COMPLETED.getValue() || project.getStatus() == Constants.ProjectPostType.CANCELLED.getValue() /* && check project bid end date, start, completion date */) {
					
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
					projectMap.put("managementCompany", managementCompany);
					projectMap.put("condoName", condoName);
//					projectMap.put("condoCity", getCityName(condoCity));
					ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
					
					projectMap.put("condoCity", getCityName(clientOrganisation.getCity())/*getCityName(condoCity)*/);
					projectMap.put("city", getCityName(clientOrganisation.getCity())/*getCityName(condoCity)*/);
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

	public List<Map<String, Object>> getVendorFavoriteProjects(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		checkIsProjectsClosed();
		
		
		//favorite projects
		List<VendorProjectInterests> vendorProjectInterests =  vendorProjectInterestsRepository.findByVendorOrganisationIdAndInterestStatus(vendorOrganisationId, ProjectInterestStatus.LIKE.getValue());
		
		List<Integer> interestProjectIds = vendorProjectInterests.stream()
                .map(VendorProjectInterests::getProjectId).collect(Collectors.toList());
		
		//bidded and Saved projects
		List<VendorBid> vendorBids = vendorBidRepository.findSavedOrPulledVendorBidByVendorOrgId(vendorOrganisationId);
		
		List<Integer> bidProjectIds = vendorBids.stream()
                .map(VendorBid::getProjectId).collect(Collectors.toList());
		
		List<Integer> projectIds = interestProjectIds;
		projectIds.addAll(bidProjectIds);
		
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
		        ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
		        map.put("managementCompany", clientOrganisation.getManagementCompany());
		        map.put("condoName", clientOrganisation.getOrganisationName());
		        map.put("condoCity", getCityName(clientOrganisation.getCity()));
		        map.put("city", getCityName(clientOrganisation.getCity()));
		        
		        VendorBid vendorBid = vendorBidRepository.findVendorBidByProjectIdAndVendorOrgId(project.getProjectId(), vendorOrganisationId);
		        
				if(vendorBid == null || vendorBid.getBidStatus() == BidPostType.UNPUBLISHED.getValue() || vendorBid.getBidStatus() == BidPostType.PULLED.getValue()) {// once bided and published its moved to current projects until its under favorite
					favoriteProjects.add(map);
				} 
					
					
			}// only vendor bided projects comes under history favorite projects only in favorite list once bided then only comes for future reference
			
		}
		
		return favoriteProjects;
	}

	public List<Map<String, Object>> findAllProjectsForVendorMarketplace(Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		
		checkIsProjectsClosed();
		
		List<Object[]> projects = projectRepository.findAllProjectsForMarketPlace();
//		List<VendorProjectInterests> vendorProjectInterests =  vendorProjectInterestsRepository.findByVendorId(vendorId);
		
		
		List<Map<String,Object>> allProjects = new ArrayList();
		
		 projects.stream().forEach((record) -> {
	        Project project = (Project) record[0];
	        String managementCompany = (String) record[1];
	        String condoName = (String) record[2];
	        String condoCity = (String) record[3];
	        String firstName = (String) record[4];
	        String lastName = (String) record[5];
	        
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
				
				//condoCity has query issue
				// so get by org obj
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
				
				map.put("condoCity", getCityName(clientOrganisation.getCity())/*getCityName(condoCity)*/);
				map.put("city", getCityName(clientOrganisation.getCity())/*getCityName(condoCity)*/);
				map.put("projectCreatedBy", firstName+" "+lastName);
				
				//Vendor Project Interest for All Open Projects
				VendorProjectInterests vendorProjectInterests = vendorProjectInterestsRepository.findByProjectIdAndVendorOrganisationId( project.getProjectId(), vendorOrganisationId);
				
				if(vendorProjectInterests != null && vendorProjectInterests.getInterestStatus() == Constants.ProjectInterestStatus.LIKE.getValue()) {
					map.put("isInterested", true);
				} else {
					map.put("isInterested", false);
				}
				//
				Map<String,Object> vendorBid = GetVendorBidForProject(project.getProjectId(), vendorOrganisationId);
				
				map.put("vendorBid",vendorBid); // null if not placed a bid
				
				//Client Preferred vendor Check
				if(project.getPostType() == ProjectPostTo.ALL.getValue()) {
					allProjects.add(map);
				} else if(project.getPostType() == ProjectPostTo.MARKED.getValue()){
					UserWishList userWish = userWishListRepository.findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(project.getClientOrganisationId(), Constants.UserType.CLIENT.getValue(), vendorOrganisationId, Constants.UserType.VENDOR.getValue() );
					if(userWish != null && userWish.getInterestStatus() == ProjectInterestStatus.LIKE.getValue()) {//check if you found error
						allProjects.add(map);
			        } else {
//			        	map.put("isPreferred", "false");//client not preferred this vendor for project posting
			        }
				}
				
	        }
	        
	        
		 });
		 
		 
		return allProjects;
	}

	public Map<String, Object> getProjectForVendor(Integer projectId, Integer vendorOrganisationId) {
		// TODO Auto-generated method stub
		checkIsProjectsClosed();
		
		Project project = projectRepository.findByProjectId(projectId); 
		
		ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
				
		project.setCity(getCityName(clientOrganisation.getCity()));
		
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
		
		String managementCompany = "";
        String condoName = "";
        String condoCity = "";
        
        if(clientOrganisation !=null) {
        	managementCompany = clientOrganisation.getManagementCompany();
        	condoName = clientOrganisation.getOrganisationName();
        	condoCity = getCityName(clientOrganisation.getCity());
        }
		
        map.put("managementCompany",managementCompany);
        map.put("condoName",condoName);
        map.put("condoCity",condoCity);
        map.put("city",condoCity);
        
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

	public boolean checkIsProjectExists(Integer projectId) {
		// TODO Auto-generated method stub
		return projectRepository.existsById(projectId);
	}
	
	public void sendProjectNotification(Project project, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Changes";
		String subContent = " project made changes";
		switch(notificationType) {
			case 1:{
				message = "New Project";
				subContent = " posted in Marketplace"/*" project with "+project.getTags()*/;
				break;
			}
			case 2 :{
				message = "Changes";
				subContent = " project made changes";
				break;
			}
			case 3 :{
				message = "Expiry";
				subContent = " project bidding expiring on "+project.getBidEndDate();
				break;
			}
		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(project.getProjectId());
		notification.setUserType(UserType.CLIENT.getValue());
		notification.setUserId(project.getClientId());
		notification.setOrganisationId(project.getClientOrganisationId());
		notification.setTitle(message);
		notification.setDescription(message+" - "+project.getProjectName()+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notificationRepository.save(notification);
	}

	public void UpdateProjectStatus() {
		// TODO Auto-generated method stub
		checkIsProjectsClosed();
	}

	public void sendBidNotification(VendorBid vendorBid, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Changes";
		String subContent = " Bid made changes";
		switch(notificationType) {
			case 4 :{
				message = "New Bid";
				subContent = " posted in Marketplace"/*" project with "+project.getTags()*/;
				notification.setUserType(UserType.VENDOR.getValue());
				break;
			}
			case 5 :{
				message = "Changes";
				subContent = " Project Bid made changes";
				notification.setUserType(UserType.VENDOR.getValue());
				break;
			}
			case 6 :{
				message = "Awarded";
				subContent = " project awarded successfully";
				notification.setUserType(UserType.CLIENT.getValue());
				break;
			}
		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(vendorBid.getId());
		
		if(vendorBid.getVendorUser() != null)
		notification.setUserId(vendorBid.getVendorUser().getUserId());
		
		notification.setOrganisationId(vendorBid.getVendorOrgId());
		notification.setTitle(message);
		notification.setDescription(message+" - "+projectRepository.findOneByProjectId(vendorBid.getProjectId()).getProjectName()+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notificationRepository.save(notification);
		
	}

	public void sendReviewRatingNotification(ProjectReviewRating projectReviewRating, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Review";
		String subContent = " review added";
		switch(notificationType) {
			case 7 :{
				message = "New Review";
				subContent = clientOrganisationRepository.findByClientOrganisationId(projectReviewRating.getClientOrganisationId()).getOrganisationName()+" Client organisation added a new review"/*" project with "+project.getTags()*/;
				notification.setUserType(UserType.CLIENT.getValue());
				notification.setUserId(projectReviewRating.getClientId());
				notification.setOrganisationId(projectReviewRating.getClientOrganisationId());
				break;
			}
			case 8 :{
				message = "New Reply";
				String vendorOrgName = vendorOrganisationRepository.findByVendorOrganisationId(projectReviewRating.getVendorOrganisationId()).getCompanyName();
				subContent = vendorOrgName +" Vendor Organisation replied to this review";
				notification.setUserType(UserType.VENDOR.getValue());
				notification.setUserId(projectReviewRating.getVendorId());
				notification.setOrganisationId(projectReviewRating.getVendorOrganisationId());
				break;
			}

		}
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(projectReviewRating.getId());
		
		notification.setTitle(message);
		notification.setDescription(message+" - "+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notificationRepository.save(notification);
	}

	public ProjectAwards projectAwarding(ProjectAwards projectAwards) {
		// TODO Auto-generated method stub
		ProjectAwards projectAwardsObj = projectAwardsRepository.findByProjectId(projectAwards.getProjectId());
		if(projectAwardsObj != null) {
			projectAwardsObj.setAwardedBidId(projectAwards.getAwardedBidId());
			projectAwardsObj.setVendorOrganisationId(projectAwards.getVendorOrganisationId());
			projectAwardsObj.setAwardDate(projectAwards.getAwardDate());
			projectAwardsObj.setTotalPrice(projectAwards.getTotalPrice());
			projectAwardsObj.setComments(projectAwards.getComments());
			projectAwardsObj.setDuration(projectAwards.getDuration());
			projectAwardsObj.setStartDate(projectAwards.getStartDate());
			
			projectAwardsRepository.save(projectAwardsObj);
		} else {
			projectAwardsObj = projectAwardsRepository.save(projectAwards);
		}
		
		VendorBid vendorBid = vendorBidRepository.findOneById(projectAwards.getAwardedBidId());
		vendorBid.setBidStatus(Constants.BidPostType.AWARDED.getValue());
		vendorBidRepository.save(vendorBid);
		
		Project project = projectRepository.findByProjectId(projectAwards.getProjectId());
		project.setAwardedBidId(projectAwards.getAwardedBidId());
		project.setStatus(ProjectPostType.COMPLETED.getValue());//once awarded moved to closed
		projectRepository.save(project);
		
		return projectAwardsObj;
	}

	public void sendProjectAwardNotification(ProjectAwards projectAwardsObj, int notificationType) {
		// TODO Auto-generated method stub
		Notification notification = new Notification();
		String message = "Awarded";
		String subContent = " Bid Awarded";
		notification.setUserType(UserType.VENDOR.getValue());
		
		notification.setNotificationCategoryType(notificationType);
		notification.setNotificationCategoryId(projectAwardsObj.getId());
		
		notification.setUserId(0);
		
		notification.setOrganisationId(projectAwardsObj.getVendorOrganisationId());
		notification.setTitle(message);
		notification.setDescription(message+" - "+projectRepository.findOneByProjectId(projectAwardsObj.getProjectId()).getProjectName()+subContent);
		notification.setStatus(Constants.UserAccountStatus.ACTIVE.getValue());;
		
		notificationRepository.save(notification);
	}

	public Map<String,Object> getAwardedBid(Integer projectId) {
		// TODO Auto-generated method stub
		ProjectAwards projectAwards = projectAwardsRepository.findByProjectId(projectId);
		 Map<String,Object> map = new HashMap();
		if(projectAwards !=null) {
			VendorBid vendorBid = vendorBidRepository.findOneById(projectAwards.getAwardedBidId());
			
			Map<String,Object> mapVendorBid = new HashMap<>();
			
			ObjectMapper oMapper = new ObjectMapper();
			
			mapVendorBid = oMapper.convertValue(vendorBid, Map.class);
			
			VendorOrganisation vendorOrganisation = vendorOrganisationRepository.findByVendorOrganisationId(vendorBid.getVendorOrgId());
			
			mapVendorBid.put("bidFiles",GetVendorBidFiles(vendorBid.getId()));
			try {
		        String logo = getOrganisationLogo(vendorBid.getVendorOrgId());
		        if(logo != null)
		        	mapVendorBid.put("vendorOrganisationProfileImage",logo);
		        else
		        	mapVendorBid.put("vendorOrganisationProfileImage","");
	        } catch(Exception exp) {
		        	exp.printStackTrace();
	        }
			
			mapVendorBid.put("organisationName",vendorOrganisation.getCompanyName());
			mapVendorBid.put("legalName",vendorOrganisation.getLegalName());
			mapVendorBid.put("rating",getVendorCategoryRatings(vendorBid.getVendorOrgId()));
			
			
			 map.put("bid",mapVendorBid);
			 Map<String,Object> awardInfo = new HashMap();
			 awardInfo.put("comments", projectAwards.getComments());
			 awardInfo.put("awardDate", projectAwards.getAwardDate());
			 awardInfo.put("totalPrice", projectAwards.getTotalPrice());
			 awardInfo.put("startDate", projectAwards.getStartDate());
			 awardInfo.put("duration", projectAwards.getDuration());
			 map.put("awardInformation",awardInfo);
			 map.put("awardFiles",projectAwardFilesRepository.findAllByProjectAwardId(projectAwards.getId()));
			 
		}
		 
		 return map;
	}

	public Project inactiveProject(Integer projectId) {
		// TODO Auto-generated method stub
		try {
			Project project = projectRepository.findByProjectId(projectId);
			
			project.setStatus(ProjectPostType.CANCELLED.getValue());
			return projectRepository.save(project);
		} catch (Exception exp) {
			exp.printStackTrace();
			return null;
		}
	}

	public List<Map<String,Object>>  getAllProjectInMarketPlaceForSupportUser() {
//		// TODO Auto-generated method stub
		
		checkIsProjectsClosed();
		
		List<Object[]> projects = projectRepository.findAllProjectsInMarketPlaceForSupportUser();
		
		List<Map<String,Object>> allProjects = new ArrayList();
		
		 projects.stream().forEach((record) -> {
	        Project project = (Project) record[0];
	        String managementCompany = (String) record[1];
	        String condoName = (String) record[2];
	        String condoCity = (String) record[3];
	        String firstName = (String) record[4];
	        String lastName = (String) record[5];
	        
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
				map.put("managementCompany", managementCompany);
				map.put("condoName", condoName);
				ClientOrganisation clientOrganisation = clientOrganisationRepository.findByClientOrganisationId(project.getClientOrganisationId());
//				projectReview.put("condoName", clientOrganisation.getOrganisationName());
				map.put("condoCity", getCityName(clientOrganisation.getCity()));
				map.put("city", getCityName(clientOrganisation.getCity()));
//				map.put("condoCity", getCityName(condoCity));
				map.put("projectCreatedBy", firstName+" "+lastName);
				
						
				allProjects.add(map);
	        }
	        
	        
		 });
		 
		 
		return allProjects;
	}

}

