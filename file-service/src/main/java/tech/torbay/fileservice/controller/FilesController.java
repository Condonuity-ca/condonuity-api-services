package tech.torbay.fileservice.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tech.torbay.fileservice.Utils.RegisterFiles;
import tech.torbay.fileservice.constants.Constants;
import tech.torbay.fileservice.constants.Constants.StatusCode;
import tech.torbay.fileservice.service.AzureBlobService;

@RestController
@RequestMapping("/api")
public class FilesController {

	@Autowired
	private AzureBlobService azureBlobService;

	@PostMapping("/container/create")
	public ResponseEntity<Map<String, Object>> createContainer(@RequestBody String containerName) {

		
		if(!azureBlobService.isContainerExists(containerName)) {
			if(azureBlobService.createContainer(containerName)) {
				Map<String, Object> map = new HashMap<>();
				map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
				map.put("statusMessage", "Success");
				map.put("responseMessage", "Container Created Successfully");
				map.put("containerName", containerName);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
				map.put("statusMessage", "Failed");
				map.put("responseMessage", "Failed to create Container");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
			
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Container Already Exists");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		
	}

	@PostMapping("/upload/{containerName}/{fileName}")
	public ResponseEntity<Map<String, Object>> uploadBlob(
			@RequestParam("multipartFile") MultipartFile multipartFile,
			@PathVariable("containerName") String containerName, @PathVariable("fileName") String fileName) {

		URI resourceUri = azureBlobService.uploadBlob(multipartFile, containerName, fileName);
		Map<String, Object> map = new HashMap<>();
		
		if(resourceUri != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "File Uploaded Successfully");
			map.put("resourceURL", resourceUri);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload file");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		
	}

	@GetMapping("/container/blobs/{containerName}")
	public ResponseEntity<Map<String, Object>> getAllBlobsInContainer(@PathVariable("containerName") String containerName) {
		List<URI> uris = azureBlobService.listBlobs(containerName);
		Map<String, Object> map = new HashMap<>();
		if(uris != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "List of Blobs in Container fetched successfully");
			map.put("containerName", containerName);
			map.put("resource", uris);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to fetch blobs in specified Container "+ containerName);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}

	@GetMapping("/container/blob/uri/{containerName}/{blobName}")
	public ResponseEntity<Map<String, Object>> getBlobURI(@PathVariable("containerName") String containerName, @PathVariable("blobName") String blobName) {
		
		if(!azureBlobService.isBlobExists(containerName, blobName)) {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "File not available at specified Container "+containerName+" and Blob location "+blobName);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		URI uri = azureBlobService.getBlob(containerName, blobName);
		
		if(uri != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Blob URI fetched successfully");
			map.put("resource", uri);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to fetch blob URI");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
	}

	// Need to discuss - with Nagaraj
//	@DeleteMapping("/container/bolb/delete/{containerName}/{blobName}")
//	public ResponseEntity<Map<String, Object>> delete(@PathVariable("containerName") String containerName,
//			@PathVariable("blobName") String blobName) {
//		if(azureBlobService.deleteBlob(containerName, blobName)) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
//			map.put("statusMessage", "Success");
//			map.put("responseMessage", "Deleted SuccessFully");
//			map.put("containerName", containerName);
//			map.put("blobName", blobName);
//			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		} else {
//			Map<String, Object> map = new HashMap<>();
//			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
//			map.put("statusMessage", "Failed");
//			map.put("responseMessage", "Failed to delete the blob");
//			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//		}
//		
//	}
	
	
	// Client Registration Document Upload
	
	@PostMapping("/upload/client/registration/{clientOrganisationId}/{clientId}")
	public ResponseEntity<Map<String, Object>> uploadClientRegistrationFile(@PathVariable("clientOrganisationId") Integer clientOrganisationId,
			@PathVariable("clientId") Integer clientId,
			@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) {

		URI url = azureBlobService.uploadClientRegistrationFile(clientId, clientOrganisationId, Constants.Containers.CLIENT_REGISTRATION_FILES.getValue(), multipartFile);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Client Registration File Uploaded Successfully");
			map.put("containerName", Constants.Containers.CLIENT_REGISTRATION_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload file");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	// multiple - client registration
	@PostMapping(value = "/uploads/client/registration/{clientOrganisationId}/{clientId}", consumes = { "multipart/form-data" })
	public ResponseEntity<Map<String, Object>> uploadClientRegistrationFiles(@PathVariable("clientOrganisationId") Integer clientOrganisationId,
			@PathVariable("clientId") Integer clientId,
			@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {

		List<URI> url = azureBlobService.uploadClientRegistrationFiles(clientId, clientOrganisationId, Constants.Containers.CLIENT_REGISTRATION_FILES.getValue(), multipartFiles);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Client Registration File Uploaded Successfully");
			map.put("containerName", Constants.Containers.CLIENT_REGISTRATION_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload file");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	// Project file Upload
	@PostMapping("/upload/project/{projectId}")
	public ResponseEntity<Map<String, Object>> uploadProjectFile(@PathVariable("projectId") Integer projectId,
			@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) {

		URI url = azureBlobService.uploadProjectFiles(projectId, Constants.Containers.PROJECT_FILES.getValue(), multipartFile);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Project File Uploaded Successfully");
			map.put("containerName", Constants.Containers.PROJECT_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload file");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	// multiple project files upload
	@PostMapping("/uploads/project/{projectId}")
	public ResponseEntity<Map<String, Object>> uploadProjectFiles(@PathVariable("projectId") Integer projectId,
			@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {

		List<URI> url = azureBlobService.uploadProjectFiles(projectId, Constants.Containers.PROJECT_FILES.getValue(), multipartFiles);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Project Files Uploaded Successfully");
			map.put("containerName", Constants.Containers.PROJECT_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	//Bid file Upload
	@PostMapping("/upload/bid/{bidId}")
	public ResponseEntity<Map<String, Object>> uploadBidFile(@PathVariable("bidId") Integer bidId,
			@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) {

		URI url = azureBlobService.uploadBidFiles(bidId, Constants.Containers.BID_FILES.getValue(), multipartFile);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Bid File Uploaded Successfully");
			map.put("containerName", Constants.Containers.BID_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload file");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	//multiple bid files upload
	@PostMapping("/uploads/bid/{bidId}")
	public ResponseEntity<Map<String, Object>> uploadBidFiles(@PathVariable("bidId") Integer bidId,
			@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {

		List<URI> url = azureBlobService.uploadBidFiles(bidId, Constants.Containers.BID_FILES.getValue(), multipartFiles);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Bid Files Uploaded Successfully");
			map.put("containerName", Constants.Containers.BID_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	//User Profile Image Upload
	@PostMapping("/upload/user/profile/{userId}/{userType}")
	public ResponseEntity<Map<String, Object>> uploadProfileImage(@PathVariable("userId") Integer userId,
			@PathVariable("userType") Integer userType,
			@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) {

		URI url = azureBlobService.uploadProfileImage(userId, userType, Constants.Containers.PROFILE_IMAGES.getValue(), multipartFile);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Profile Image Uploaded Successfully");
			map.put("containerName", Constants.Containers.PROFILE_IMAGES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload image");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	//Vendor Organisation Profile Image Upload
	@PostMapping("/upload/vendor/organisation/profile/{organisationId}")
	public ResponseEntity<Map<String, Object>> uploadVendorOrganisationProfileImage(@PathVariable("organisationId") Integer organisationId,
			@RequestParam("multipartFile") MultipartFile multipartFile, HttpServletRequest request) {

		URI url = azureBlobService.uploadVendorOrganisationProfileImage(organisationId, Constants.Containers.PROFILE_IMAGES.getValue(), multipartFile);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Vendor Organisation Profile Image Uploaded Successfully");
			map.put("containerName", Constants.Containers.PROFILE_IMAGES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload image");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	//upload thread files
	@PostMapping("/uploads/thread/{threadId}")
	public ResponseEntity<Map<String, Object>> uploadThreadFiles(@PathVariable("threadId") Integer threadId,
			@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {

		List<URI> url = azureBlobService.uploadThreadFiles(threadId, Constants.Containers.MESSAGE_INTERNAL_THREAD_FILES.getValue(), multipartFiles);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Message Internal Thread Files Uploaded Successfully");
			map.put("containerName", Constants.Containers.MESSAGE_INTERNAL_THREAD_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	//upload thread files
	@PostMapping("/uploads/thread/comment/{commentId}")
	public ResponseEntity<Map<String, Object>> uploadCommentFiles(@PathVariable("commentId") Integer commentId,
			@RequestParam("multipartFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {

		List<URI> url = azureBlobService.uploadCommentFiles(commentId, Constants.Containers.MESSAGE_COMMENT_FILES.getValue(), multipartFiles);
		
		Map<String, Object> map = new HashMap<>();
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Message Comment Files Uploaded Successfully");
			map.put("containerName", Constants.Containers.MESSAGE_COMMENT_FILES.getValue());
			map.put("resource", url);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to upload files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
		
//	delete APIs
	
	@DeleteMapping("/delete/client/registration/files}")
	public ResponseEntity<Map<String, Object>> deleteRegistrationFiles(@RequestBody List<Integer> fileIds) {
		
		boolean isDeleted = azureBlobService.deleteRegistrationFiles(fileIds);
		
		if(isDeleted) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Registration Files Deleted SuccessFully");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to delete the registration files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/delete/project/files")
	public ResponseEntity<Map<String, Object>> deleteProjectFiles(@RequestBody List<Integer> fileIds) {
		
		boolean isDeleted = azureBlobService.deleteProjectFiles(fileIds);
		
		if(isDeleted) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Project Files Deleted SuccessFully");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to delete the project files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/delete/bid/files")
	public ResponseEntity<Map<String, Object>> deleteBidFiles(@RequestBody List<Integer> fileIds) {
		
		boolean isDeleted = azureBlobService.deleteBidFiles(fileIds);
		
		if(isDeleted) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Bid Files Deleted SuccessFully");
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Failed to delete the bid files");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
	}
}
