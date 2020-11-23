package tech.torbay.projectservice.controller;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.projectservice.constants.Constants.NotificationType;
import tech.torbay.projectservice.constants.Constants.ProjectPostType;
import tech.torbay.projectservice.constants.Constants.ProjectSortBy;
import tech.torbay.projectservice.constants.Constants.StatusCode;
import tech.torbay.projectservice.entity.BidResultsViewsHistory;
import tech.torbay.projectservice.entity.PredefinedTags;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectAwards;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.ProjectReviewRating;
import tech.torbay.projectservice.entity.VendorBid;
import tech.torbay.projectservice.entity.VendorProjectInterests;
import tech.torbay.projectservice.exception.BadRequestException;
import tech.torbay.projectservice.service.ProjectService;
import tech.torbay.projectservice.statusmessage.ResponseMessage;


@RestController
@RequestMapping("/api")
@Api(value = "Project Resource REST Endpoint", description = "Shows the Projects info")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@ApiOperation(value = "Fetching All Projects details with in a Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success with All Projects Details")
            }
    )
	@GetMapping("/projects/all/client/organisation/{orgId}")
	public ResponseEntity<Object> getAllProjects(@PathVariable("orgId") Integer id) {
		List<Project> list = projectService.getAllProjects(id);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Projects from Client fetched successfully");
			response.put("projects", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All Current Projects details with in a Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success with Current Projects Details")
            }
    )
	@GetMapping("/projects/current/client/organisation/{orgId}")
	public ResponseEntity<Object> getCurrentProjects(@PathVariable("orgId") Integer id) {
		List<Map<String,Object>> list = projectService.getAllProjects(ProjectSortBy.Current,id);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Current Projects from Client fetched successfully");
			response.put("projects", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All Past Projects details with in a Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success with All Past Projects Details")
            }
    )
	@GetMapping("/projects/history/client/organisation/{orgId}")
	public ResponseEntity<Object> getHistoryProjects(@PathVariable("orgId") Integer id) {
		List<Map<String,Object>> list = projectService.getAllProjects(ProjectSortBy.Past,id);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "All Projects from Client fetched successfully");
			response.put("projects", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Client Project Creation and Posting implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Created/Published successfully")
            }
    )
	@PostMapping("/client/project/create")
	public ResponseEntity<Object> createProject(@Valid @RequestBody Project project) {
		
		try {
			logger.info("projectObj "+ project.toString());
			
//			String projectDuration = projectService.calculateDuration(project.getDuration(), project.getProjectStartDate(), project.getProjectCompletionDeadline());
//			if(projectDuration.startsWith("-")) { // need to check this exception
//				throw new BadRequestException("project creation", "project Duration", projectDuration);
//			}
			
			Project projectObj = projectService.createProject(project);
	        if (projectObj == null) {
	     
	        	ResponseMessage responseMessage = null;
	        	if(project.getStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
	            
	        		responseMessage = new ResponseMessage(
	                		StatusCode.REQUEST_FAILED.getValue(),
	                		"Failed",
	            			"Failed to Create Project");
	        	} else if(project.getStatus() == ProjectPostType.PUBLISHED.getValue()){
	        		responseMessage = new ResponseMessage(
	                		StatusCode.REQUEST_FAILED.getValue(),
	                		"Failed",
	            			"Failed to Post Project");
	        	}
	        	
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	        } else {
			/*
			 * HttpHeaders headers = new HttpHeaders();
			 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
			 * organisation.getOrganisationId()).toUri());
			 */
	        	HashMap<String, Object> response = new HashMap();
	        	if(project.getStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
	            
	        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
	    			response.put("statusMessage", "Success");
	    			response.put("responseMessage", "Project created successfully");
	    			response.put("projectId", projectObj.getProjectId());
	    			
	        	} else if(project.getStatus() == ProjectPostType.PUBLISHED.getValue()){
	        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
	    			response.put("statusMessage", "Success");
	    			response.put("responseMessage", "Project posted successfully");
	    			response.put("projectId", projectObj.getProjectId());
	    			
	    			SendProjectNotification(projectObj, NotificationType.PROJECT_CREATE);
	        	}
	        	
	        	return new ResponseEntity<Object>(response, HttpStatus.OK);
	        }
		} catch(BadRequestException exp) {
			new BadRequestException(exp.getMessage());
		}
		return null;
	}
	
	@ApiOperation(value = "Client Project Creation and Posting implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Created/Published successfully")
            }
    )
	@PostMapping("/client/project/clone/{projectId}")
	public ResponseEntity<Object> cloneProject(@PathVariable Integer projectId) {
		
		try {
//			logger.info("projectObj "+ project.toString());
			
//			String projectDuration = projectService.calculateDuration(project.getDuration(), project.getProjectStartDate(), project.getProjectCompletionDeadline());
//			if(projectDuration.startsWith("-")) { // need to check this exception
//				throw new BadRequestException("project creation", "project Duration", projectDuration);
//			}
			
			Integer newProjectId = projectService.cloneProject(projectId);
	        if (newProjectId == null || newProjectId == 0) {
	     
	        	ResponseMessage responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_FAILED.getValue(),
                		"Failed",
            			"Failed to Clone Project");
	        	
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
	        } else {
			/*
			 * HttpHeaders headers = new HttpHeaders();
			 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
			 * organisation.getOrganisationId()).toUri());
			 */
	        	HashMap<String, Object> response = new HashMap();
//	        	if(project.getStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
	            
	        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
	    			response.put("statusMessage", "Success");
	    			response.put("responseMessage", "Project cloned successfully");
	    			response.put("projectId", newProjectId);
	    			
//	        	} else if(project.getStatus() == ProjectPostType.PUBLISHED.getValue()){
//	        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
//	    			response.put("statusMessage", "Success");
//	    			response.put("responseMessage", "Project posted successfully");
//	    			response.put("projectId", projectObj.getProjectId());
//	    			
//	    			SendProjectNotification(project, NotificationType.PROJECT_CREATE);
//	        	}
	        	
	        	return new ResponseEntity<Object>(response, HttpStatus.OK);
	        }
		} catch(BadRequestException exp) {
			new BadRequestException(exp.getMessage());
		}
		return null;
	}
	
	@ApiOperation(value = "Client Project Update Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Updated Successfully")
            }
    )
	@PutMapping("/client/project")
	public ResponseEntity<Object> updateProject(@RequestBody Project project) {
		
		if(!projectService.checkIsProjectExists(project.getProjectId())) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.NOT_FOUND.getValue(),
            		"Failed",
        			"Project Not Found");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		project = projectService.updateProject(project);
        if (project == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_FAILED.getValue(),
            		"Failed",
        			"Failed to Update Project");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project updated successfully");
        	SendProjectNotification(project, NotificationType.PROJECT_UPDATE);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Client Project Publish Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Published Successfully")
            }
    )
	@PutMapping("/client/project/publish/{projectId}")
	public ResponseEntity<Object> publishProject(@PathVariable("projectId") Integer projectId) {
		
		Project project = projectService.publishProject(projectId);
        if (project == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Publish Project");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project Published successfully");
        	SendProjectNotification(project, NotificationType.PROJECT_CREATE);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Client Project Cancel Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Cancelled Successfully")
            }
    )
	@PutMapping("/client/project/cancel/{projectId}/{clientUserId}")
	public ResponseEntity<Object> cancelProject(@PathVariable("projectId") Integer projectId, @PathVariable("clientUserId") Integer clientUserId) {
		
		Project project = projectService.cancelProject(projectId);
        if (project == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Cancel Project");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project cancelled successfully");
        	SendProjectCancelNotification(project, clientUserId, NotificationType.PROJECT_CANCEL);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Vendor Project Bid Creation and Posting implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Bid Created/Posted successfully")
            }
    )
	@PostMapping("/vendor/project/bid/create")
	public ResponseEntity<Object> createProjectBid(@RequestBody VendorBid vendorBid) {
		
		if(projectService.findvendorBidByVendorIdAndProjectId(vendorBid) != null) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_FAILED.getValue(),
            		"Failed",
        			"Vendor Already placed a Bid for this Project");
			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
//		String vendorProjectDuration = projectService.calculateDuration(vendorBid.getVendorProjectDuration(), vendorBid.getVendorStartDate(),vendorBid.getVendorEndDate());
//		
//		if(vendorProjectDuration.startsWith("-")) { // need to check this exception
//			throw new BadRequestException("bid creation", "Vendor Project Duration", vendorProjectDuration);
//		}
		if(projectService.checkIsProjectBiddingClosed(vendorBid.getProjectId())) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.PROJECT_BID_END_DATE_CROSSED.getValue(),
            		"Failed",
        			"Project Bid End Date Finished");
			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
		VendorBid vendorBidObj = projectService.createProjectBid(vendorBid);
        if (vendorBidObj == null) {
     
        	ResponseMessage responseMessage = null;
        	if(vendorBid.getBidStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
            
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_FAILED.getValue(),
                		"Failed",
            			"Failed to Create Project Bid");
        	} else if(vendorBid.getBidStatus() == ProjectPostType.PUBLISHED.getValue()){
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_FAILED.getValue(),
                		"Failed",
            			"Failed to Post Project Bid");
        		
        	}
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	HashMap<String, Object> response = new HashMap();
        	if(vendorBid.getBidStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
    			response.put("statusMessage", "Success");
    			response.put("responseMessage", "Project Bid created successfully");
    			response.put("bidId", vendorBid.getId());
    			
        	} else if(vendorBid.getBidStatus() == ProjectPostType.PUBLISHED.getValue()){
        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
    			response.put("statusMessage", "Success");
    			response.put("responseMessage", "Project Bid posted successfully");
    			response.put("bidId", vendorBid.getId());
    			SendBidNotification(vendorBid, NotificationType.BID_CREATE);
        	}
        	
        	return new ResponseEntity<Object>(response,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Vendor Project Bid Update implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Bid updated successfully")
            }
    )
	@PutMapping("/vendor/project/bid")
	public ResponseEntity<Object> updateProjectBid(@RequestBody VendorBid vendorBid) {
		
		if(projectService.checkIsProjectBiddingClosed(vendorBid.getProjectId())) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.PROJECT_BID_END_DATE_CROSSED.getValue(),
            		"Failed",
        			"Project Bid End Date Finished");
			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
		VendorBid vendorBidObj = projectService.updateProjectBid(vendorBid);
        if (vendorBidObj == null) {
     
        	ResponseMessage responseMessage = null;
        	if(vendorBid.getBidStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
            
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_FAILED.getValue(),
                		"Failed",
            			"Failed to Update Project Bid");
        	} else if(vendorBid.getBidStatus() == ProjectPostType.PUBLISHED.getValue()){
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_FAILED.getValue(),
                		"Failed",
            			"Failed to Update Project Bid");
        		
        	}
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	HashMap<String, Object> response = new HashMap();
        	if(vendorBid.getBidStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
    			response.put("statusMessage", "Success");
    			response.put("responseMessage", "Project Bid updated successfully");
    			
        	} else if(vendorBid.getBidStatus() == ProjectPostType.PUBLISHED.getValue()){
        		response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
    			response.put("statusMessage", "Success");
    			response.put("responseMessage", "Project Bid updated successfully");
    			SendBidNotification(vendorBid, NotificationType.BID_UPDATE);
        	}
        	
        	return new ResponseEntity<Object>(response,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Fetching All Current Projects details with in a vendor Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Current Projects Details fetched successfully")
            }
    )
	@GetMapping("/projects/current/vendor/organisation/{orgId}")
	public ResponseEntity<Object> getVendorCurrentProjects(@PathVariable("orgId") Integer id) {
		List<Map<String,Object>> list = projectService.getVendorCurrentProjects(id);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Current Projects from Vendor fetched successfully");
			response.put("projects", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All Favorite Projects details with in a vendor Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Favorite Projects Details fetched successfully")
            }
    )
	@GetMapping("/projects/favorite/vendor/organisation/{orgId}")
	public ResponseEntity<Object> getVendorFavoriteProjects(@PathVariable("orgId") Integer id) {
		List<Map<String,Object>> list = projectService.getVendorFavoriteProjects(id);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Favorite Projects from Vendor fetched successfully");
			response.put("projects", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All History Projects details with in a vendor Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All History Projects Details fetched successfully")
            }
    )
	@GetMapping("/projects/history/vendor/organisation/{orgId}")
	public ResponseEntity<Object> getVendorHistoryProjects(@PathVariable("orgId") Integer id) {
		List<Map<String,Object>> list = projectService.getVendorHistoryProjects(id);
		
		HashMap<String, Object> response = new HashMap();
		if(list != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "History Projects from Vendor fetched successfully");
			response.put("projects", list);
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Project Bid Publish Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Bid Published Successfully")
            }
    )
	@PutMapping("/vendor/bid/publish/{bidId}")
	public ResponseEntity<Object> publishProjectBid(@PathVariable("bidId") Integer bidId) {
		
		if(projectService.checkIsProjectBiddingClosedByBidId(bidId)) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.PROJECT_BID_END_DATE_CROSSED.getValue(),
            		"Failed",
        			"Project Bid End Date Finished");
			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
		VendorBid vendorBid = projectService.publishVendorBid(bidId);
        if (vendorBid == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Publish Project Bid");
        	 
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project Bid Published successfully");
        	SendBidNotification(vendorBid, NotificationType.BID_CREATE);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Project Bid Pull Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Bid Pulled Successfully")
            }
    )
	@PutMapping("/vendor/bid/pull/{bidId}")
	public ResponseEntity<Object> pullProjectBid(@PathVariable("bidId") Integer bidId) {
		
		if(projectService.checkIsProjectBiddingClosedByBidId(bidId)) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.PROJECT_BID_END_DATE_CROSSED.getValue(),
            		"Failed",
        			"Project Bid End Date Finished");
			return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		
		VendorBid vendorBid = projectService.pullVendorBid(bidId);
        if (vendorBid == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Pull Project Bid");
        	 
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project Bid Pulled successfully");
        	SendBidNotification(vendorBid, NotificationType.BID_WITHDRAWN);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Fetching A Project Details with All Bids, Questions and Answers")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A Project details fetched successfully")
            }
    )
	@GetMapping("/projects/{id}")
	public ResponseEntity<Object> getProjectById(@PathVariable("id") Integer id) {

		Map<String,Object> project = projectService.findByProjectId(id);
		List<Map<String,Object>> projectAllBids = projectService.getAllBidsByProjectId(id);
		List<ProjectQuestionAnswer> projectAllQA = projectService.getAllQAByProjectId(id);
		Map<String,Object> awardedBid = projectService.getAwardedBid(id);
		
		HashMap<String, Object> list = new HashMap();
		
		if(project != null && projectAllBids != null && projectAllQA != null ) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "A Project details fetched successfully");
			list.put("project", project);
			list.put("allBids",projectAllBids);
			list.put("questionAnswers",projectAllQA);
			if(awardedBid != null) {
				list.put("awardedBid",awardedBid);
			}
			
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All Projects for Marketplace")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Projects for Marketplace fetched successfully")
            }
    )
	@GetMapping("/projects")
	public ResponseEntity<Object> getAllProjectForMarketPlace() {

		List<Map<String,Object>> projects = projectService.findAllProjects();
		
		HashMap<String, Object> list = new HashMap();
		
		if(projects != null) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "All Marketplace Project details fetched successfully");
			list.put("projects", projects);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching All Projects for Vendor Marketplace")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Projects for Vendor Marketplace fetched successfully")
            }
    )
	@GetMapping("/projects/marketplace/{vendorOrganisationId}")
	public ResponseEntity<Object> getAllProjectForVendorMarketPlace(@PathVariable("vendorOrganisationId") Integer vendorOrganisationId) {

		List<Map<String,Object>> projects = projectService.findAllProjectsForVendorMarketplace(vendorOrganisationId);
		
		HashMap<String, Object> list = new HashMap();
		
		if(projects != null) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "All Vendor Marketplace Project details fetched successfully");
			list.put("projects", projects);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching A Project details for Vendor")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A Project details for Vendor fetched successfully")
            }
    )
	@GetMapping("/projects/{projectId}/vendor/{vendorId}")
	public ResponseEntity<Object> getProjectVendorId(@PathVariable("projectId") Integer projectId, @PathVariable("vendorId") Integer vendorId) {

		Map<String,Object> project = projectService.getProjectForVendor(projectId, vendorId);
		
		HashMap<String, Object> list = new HashMap();
		
		if(project != null) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "All Vendor Marketplace Project details fetched successfully");
			list.put("project", project);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Fetching A Project Bid Details with All bidding Products details")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "A Project Bid details fetched successfully")
            }
    )
	@GetMapping("/projects/bid/{id}")
	public ResponseEntity<Object> getProjectBidById(@PathVariable("id") Integer id) {

		Object vendorBid = projectService.findByBidId(id);
//		List<ProjectQuestionAnswer> projectAllQA = projectService.getAllQAByProjectId(id);
		
		HashMap<String, Object> list = new HashMap();
		
		if (vendorBid != null /* && projectAllBids != null && projectAllQA != null */ ) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "A Project Bid details fetched successfully");
			list.put("vendorBid", vendorBid);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Project Question Posting implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Question Posted successfully")
            }
    )
	@PostMapping("/projects/question/create")
	public ResponseEntity<Object> createProjectQuestion(@RequestBody ProjectQuestionAnswer projectQA) {
		projectQA = projectService.createProjectQuestion(projectQA);
        if (projectQA == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Post Project Question");        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project Question Posted Successfully");
        	SendProjectQANotification(projectQA, NotificationType.PROJECT_QUESTION_CREATE);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Project Question Answer implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Question Answered successfully")
            }
    )
	@PutMapping("/projects/question/answer")
	public ResponseEntity<Object> answerProjectQuestion(@RequestBody ProjectQuestionAnswer projectQA) {
		projectQA = projectService.answerProjectQuestion(projectQA);
        if (projectQA == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Answer the Project Question ");        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project Question Answered Successfully");
        	SendProjectQANotification(projectQA, NotificationType.PROJECT_QUESTION_ANSWER);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	private void SendProjectQANotification(ProjectQuestionAnswer projectQA, NotificationType projectQuestionAnswer) {
		// TODO Auto-generated method stub
		projectService.SendProjectQANotification(projectQA,projectQuestionAnswer.getValue());
	}

	@ApiOperation(value = "Project Review and Rating Posting implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Review and Rating Posted successfully")
            }
    )
	@PostMapping("/projects/review/create")
	public ResponseEntity<Object> postProjectReview(@RequestBody ProjectReviewRating projectReviewRating) {
		ProjectReviewRating projectReviewRatingObj = projectService.postProjectReview(projectReviewRating);
        if (projectReviewRating == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Post Review and Ratings");        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project Reviewed and Rated Successfully");
        	SendReviewRatingNotification(projectReviewRatingObj, NotificationType.REVIEW_CREATE);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Reply to a Project Review implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Replied to a Project Review sucessfully")
            }
    )
	@PutMapping("/projects/review/reply")
	public ResponseEntity<Object> replyProjectReview(@RequestBody ProjectReviewRating projectReviewRating) {
		ProjectReviewRating projectReviewRatingObj = projectService.replyProjectReview(projectReviewRating);
        if (projectReviewRatingObj == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Reply to the Project Review");        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Replied to a Project Review Successfully");
        	
        	SendReviewRatingNotification(projectReviewRatingObj, NotificationType.REVIEW_COMMENT);
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        	
        }
        
	}
	
	@ApiOperation(value = "Fetching All Predefined Tags Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Predefined Tags fetched successfully")
            }
    )
	@GetMapping("/projects/tags")
	public ResponseEntity<Object> getAllPredefinedTags() {

		List<PredefinedTags> allPredefinedTags = projectService.getAllPredefinedTags();
		
		HashMap<String, Object> list = new HashMap();
		
		if (allPredefinedTags != null) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Predefined Tags fetched successfully");
			list.put("tags",allPredefinedTags);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Create Project Interest Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Interest Updated successfully")
            }
    )
	@PostMapping("/project/interest")
	public ResponseEntity<Object> createProjectInterest(@RequestBody Map<String, Object> requestData) {

		VendorProjectInterests vendorProjectInterest = projectService.updateVendorProjectInterest(requestData);
		
		HashMap<String, Object> list = new HashMap();
		
		if (vendorProjectInterest != null) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Project Interest Updated successfully");
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Failed to Update Project Interest");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	private void SendProjectNotification(Project project, NotificationType notificationType) {
		// TODO Auto-generated method stub
		projectService.sendProjectNotification(project, notificationType.getValue());
	}
	
	private void SendProjectCancelNotification(Project project,Integer clientUserId, NotificationType notificationType) {
		// TODO Auto-generated method stub
		projectService.sendProjectCancelNotification(project, clientUserId, notificationType.getValue());
	}
	
	private void SendBidNotification(VendorBid vendorBid, NotificationType notificationType) {
		// TODO Auto-generated method stub
		projectService.sendBidNotification(vendorBid, notificationType.getValue());
	}
	
	private void SendReviewRatingNotification(ProjectReviewRating projectReviewRating, NotificationType notificationType) {
		// TODO Auto-generated method stub
		projectService.sendReviewRatingNotification(projectReviewRating, notificationType.getValue());
	}
	
	@ApiOperation(value = "Project Award decision implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Awarded successfully")
            }
    )
	@PostMapping("/projects/award")
	public ResponseEntity<Object> postProjectAward(@RequestBody ProjectAwards projectAwards) {
		ProjectAwards projectAwardsObj = projectService.projectAwarding(projectAwards);
        if (projectAwardsObj == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Awarding Project");        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
		/*
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setLocation(builder.path("/client/org/{id}").buildAndExpand(
		 * organisation.getOrganisationId()).toUri());
		 */
        	HashMap<String, Object> list = new HashMap();
        	list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "Project Awarded successfully");
			list.put("projectAwardId", projectAwardsObj.getId());
			
        	SendProjectAwardNotification(projectAwardsObj, NotificationType.BID_WON_LOSE);
        	return new ResponseEntity<Object>(list,HttpStatus.OK);
        }
	}
	
    private void SendProjectAwardNotification(ProjectAwards projectAwardsObj, NotificationType notificationType) {
		// TODO Auto-generated method stub
		projectService.sendProjectAwardNotification(projectAwardsObj, notificationType.getValue());
	}
     
    @ApiOperation(value = "Client Project Inactive Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Inactivated Successfully")
            }
    )
	@PutMapping("/client/project/inactive/{projectId}")
	public ResponseEntity<Object> inactiveProject(@PathVariable("projectId") Integer projectId) {
		
		if(!projectService.checkIsProjectExists(projectId)) {
			ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.NOT_FOUND.getValue(),
            		"Failed",
        			"Project Not Found");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
		}
		Project project = projectService.inactiveProject(projectId);
        if (project == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_FAILED.getValue(),
            		"Failed",
        			"Failed to delete Project");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project inactivated successfully");
        	SendProjectNotification(project, NotificationType.PROJECT_UPDATE);
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
    
    @ApiOperation(value = "Fetching All Projects for Marketplace")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All Projects for Marketplace fetched successfully")
            }
    )
	@GetMapping("/support/projects")
	public ResponseEntity<Object> getAllProjectInMarketPlaceForSupportUser() {

		List<Map<String,Object>> projects = projectService.getAllProjectInMarketPlaceForSupportUser();
		
		HashMap<String, Object> list = new HashMap();
		if(projects != null) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "All Marketplace Project details fetched successfully");
			list.put("projects", projects);
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} else {
			list.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			list.put("statusMessage", "Failed");
			list.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
    
    @ApiOperation(value = "Client views Vendor Project Bids and saving its views history")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Client views Vendor Project Bids and saving its views")
            }
    )
	@PostMapping("/project/bids/client/views")
	public ResponseEntity<Object> recordClientUserProjectBidsViews(@RequestBody BidResultsViewsHistory bidResultsViewsHistory) {
    	BidResultsViewsHistory bidResultview = projectService.recordClientProjectBidsViews(bidResultsViewsHistory);
		
		HashMap<String, Object> response = new HashMap();
		if(bidResultview != null) {
			response.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			response.put("statusMessage", "Success");
			response.put("responseMessage", "Bid Results View recorded successfully");
			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			response.put("statusMessage", "Failed");
			response.put("responseMessage", "Database Error");

			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
    
	@Scheduled(cron = "0 0 5 * * ?") /* (fixedDelay = 1000 * 60 * 60 * 24) */
	public void run() {
	    logger.info("Current time is :: " + Calendar.getInstance().getTime());
	    try {
//			cronJobSch();
	    	CheckIsProjectBidExpiring();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void CheckIsProjectBidExpiring() {
		// TODO Auto-generated method stub
		projectService.CheckIsProjectBidExpiring();
	}
	
//	@Scheduled(cron = "0 * 9 * * ?")
//	public void cronJobSch() throws Exception {
//		logger.info("Current time is :: " + Calendar.getInstance().getTime());
//	}
} 