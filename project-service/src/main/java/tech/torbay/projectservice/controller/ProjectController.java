package tech.torbay.projectservice.controller;


import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tech.torbay.projectservice.constants.Constants.ProjectPostType;
import tech.torbay.projectservice.constants.Constants.ProjectSortBy;
import tech.torbay.projectservice.constants.Constants.StatusCode;
import tech.torbay.projectservice.entity.PredefinedTags;
import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.ProjectReviewRating;
import tech.torbay.projectservice.entity.VendorBid;
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
		List<Project> list = projectService.getAllProjects(ProjectSortBy.Current,id);
		
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
	
	@ApiOperation(value = "Fetching All Past Projects details with in a Organisation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Success with All Past Projects Details")
            }
    )
	@GetMapping("/projects/history/client/organisation/{orgId}")
	public ResponseEntity<Object> getHistoryProjects(@PathVariable("orgId") Integer id) {
		List<Project> list = projectService.getAllProjects(ProjectSortBy.Past,id);
		
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
			Project projectObj = projectService.createProject(project);
	        if (projectObj == null) {
	     
	        	ResponseMessage responseMessage = null;
	        	if(project.getStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
	            
	        		responseMessage = new ResponseMessage(
	                		StatusCode.REQUEST_SUCCESS.getValue(),
	                		"Failed",
	            			"Failed to Create Project");
	        	} else if(project.getStatus() == ProjectPostType.PUBLISHED.getValue()){
	        		responseMessage = new ResponseMessage(
	                		StatusCode.REQUEST_SUCCESS.getValue(),
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
	        	ResponseMessage responseMessage = null;
	        	if(project.getStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
	            
	        		responseMessage = new ResponseMessage(
	                		StatusCode.REQUEST_SUCCESS.getValue(),
	                		"Success",
	                		"Project created successfully");
	        	} else if(project.getStatus() == ProjectPostType.PUBLISHED.getValue()){
	        		responseMessage = new ResponseMessage(
	                		StatusCode.REQUEST_SUCCESS.getValue(),
	                		"Success",
	                		"Project posted successfully");
	        		
	        	}
	        	
	        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
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
		
		project = projectService.updateProject(project);
        if (project == null) {
     
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Failed",
        			"Failed to Update Project");
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        } else {
        	ResponseMessage responseMessage = new ResponseMessage(
            		StatusCode.REQUEST_SUCCESS.getValue(),
            		"Success",
            		"Project updated successfully");
        	
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
		VendorBid vendorBidObj = projectService.createProjectBid(vendorBid);
        if (vendorBidObj == null) {
     
        	ResponseMessage responseMessage = null;
        	if(vendorBid.getBidStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
            
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_SUCCESS.getValue(),
                		"Failed",
            			"Failed to Create Project Bid");
        	} else if(vendorBid.getBidStatus() == ProjectPostType.PUBLISHED.getValue()){
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_SUCCESS.getValue(),
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
        	ResponseMessage responseMessage = null;
        	if(vendorBid.getBidStatus() == ProjectPostType.UNPUBLISHED.getValue()) {
            
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_SUCCESS.getValue(),
                		"Success",
                		"Project Bid created successfully");
        	} else if(vendorBid.getBidStatus() == ProjectPostType.PUBLISHED.getValue()){
        		responseMessage = new ResponseMessage(
                		StatusCode.REQUEST_SUCCESS.getValue(),
                		"Success",
                		"Project Bid posted successfully");
        		
        	}
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Client Project Publish Implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Published Successfully")
            }
    )
	@PutMapping("/vendor/bid/publish/{bidId}")
	public ResponseEntity<Object> publishProjectBid(@PathVariable("bidId") Integer bidId) {
		
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
	public ResponseEntity<Object> getOrganisationById(@PathVariable("id") Integer id) {

		Project project = projectService.findByProjectId(id);
		List<VendorBid> projectAllBids = projectService.getAllBidsByProjectId(id);
		List<ProjectQuestionAnswer> projectAllQA = projectService.getAllQAByProjectId(id);
		
		HashMap<String, Object> list = new HashMap();
		
		if(project != null && projectAllBids != null && projectAllQA != null ) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "A Project details fetched successfully");
			list.put("project", project);
			list.put("allBids",projectAllBids);
			list.put("questionAnswers",projectAllQA);
			
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

		VendorBid vendorBid = projectService.findByBidId(id);
//		List<VendorBid> projectAllBids = projectService.getAllBidsByProjectId(id);
//		List<ProjectQuestionAnswer> projectAllQA = projectService.getAllQAByProjectId(id);
		
		HashMap<String, Object> list = new HashMap();
		
		if (vendorBid != null /* && projectAllBids != null && projectAllQA != null */ ) {
			list.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			list.put("statusMessage", "Success");
			list.put("responseMessage", "A Project Bid details fetched successfully");
			list.put("vendorBid", vendorBid);
//			list.put("allBids",projectAllBids);
			
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
        	
        	return new ResponseEntity<Object>(responseMessage,HttpStatus.OK);
        }
        
	}
	
	@ApiOperation(value = "Project Review and Rating Posting implementation")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Project Review and Rating Posted successfully")
            }
    )
	@PostMapping("/projects/review/create")
	public ResponseEntity<Object> postProjectReview(@RequestBody ProjectReviewRating projectReviewRating) {
		projectReviewRating = projectService.postProjectReview(projectReviewRating);
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
		projectReviewRating = projectService.replyProjectReview(projectReviewRating);
        if (projectReviewRating == null) {
     
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
} 