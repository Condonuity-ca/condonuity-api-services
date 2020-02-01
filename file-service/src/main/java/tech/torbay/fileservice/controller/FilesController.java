package tech.torbay.fileservice.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import tech.torbay.fileservice.Utils.RegisterFiles;
import tech.torbay.fileservice.constants.Constants;
import tech.torbay.fileservice.constants.Constants.StatusCode;
import tech.torbay.fileservice.entity.UserProfileImages;
import tech.torbay.fileservice.entity.VendorOrganisationProfileImages;
import tech.torbay.fileservice.service.AzureBlobService;

@RestController
@RequestMapping("/api")
public class FilesController {

	@Autowired
	private AzureBlobService azureBlobService;
	
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

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
	
	@GetMapping("/container/blob/download")
	public @ResponseBody String getBlobFile() {
		return callURL("http://192.168.30.188:8205/api/container/blob/projectfiles/bf887957-8d54-424e-95a5-861397acb43c.jpg");
	}
	
	public static String callSecondURL(String FILE_URL){
        
        System.out.println("Requested URL:" + FILE_URL);
        System.out.println("Path:" + System.getProperty("user.home"));
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
          FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\"+"prakash.jpg")) {
            byte dataBuffer[] = new byte[50];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 50)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("Download Success");
        } catch (IOException e) {
            // handle exception
            System.out.println("Download Error");
        }
        
        return "";
    }
	public static String callURL(String myURL) {
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				try {
				    
				    InputStream input = urlConn.getInputStream();
                
                OutputStream os = new FileOutputStream(System.getProperty("user.home")+"prakash.jpg");
                
                byte[] buffer = new byte[1024];
                int bytesRead;
                //read from is to buffer
                while((bytesRead = input.read(buffer)) !=-1){
                    os.write(buffer, 0, bytesRead);
                }
                input.close();
                //flush OutputStream to write any buffered data to file
                os.flush();
                os.close();
                System.out.println("Download Success");
            } catch (IOException e) {
                e.printStackTrace();
            }
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
 
		return sb.toString();
	}

	@GetMapping("/container/blob/{containerName}/{blobName}")
	public @ResponseBody ResponseEntity<ByteArrayResource> getBlobFile(@PathVariable("containerName") String containerName, @PathVariable("blobName") String blobName) {
	    try {
//	    	if(!azureBlobService.isBlobExists(containerName, blobName)) {
//				Map<String, Object> map = new HashMap<>();
//				map.put("statusCode", StatusCode.REQUEST_FAILED.getValue());
//				map.put("statusMessage", "Failed");
//				map.put("responseMessage", "File not available at specified Container "+containerName+" and Blob location "+blobName);
//				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//			}
	    	
	    	CloudBlockBlob blobFile = azureBlobService.getBlobFile(containerName, blobName);
	    	Map<String, String> fileInformation = azureBlobService.getFileInformation(containerName, blobName);
	    	
	    	String fileName = blobName;
	    	// Try to determine file's content type
	        String contentType = Files.getFileExtension(Files.getFileExtension(blobName));
	         
	    	if(fileInformation != null) {
		    	fileName = fileInformation.get("fileName");
		    	contentType = fileInformation.get("fileType");
	    	}
	    	
	    	InputStream input =  blobFile.openInputStream();
            InputStreamReader inr = new InputStreamReader(input, "UTF-8");
            
            //case1
//            blobFile.download(
//					new FileOutputStream(System.getProperty("user.home") + blobFile.getName()));
            //case2
//            byte[] imageBytes = new byte[blobFile.getStreamWriteSizeInBytes()];
//            input.read(imageBytes, 0, imageBytes.length);
//            input.close();
//            String imageStr = Base64.encodeBase64String(imageBytes);
//            return imageStr;
            
	         // Fallback to the default content type if type could not be determined
	         if (contentType == null) {
	             contentType = "application/octet-stream";
	         }
//
            ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(input));
            long contentLength = resource.contentLength();
            
            
            HttpHeaders header = new HttpHeaders();
	        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
//	        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//	        header.add("Pragma", "no-cache");
//	        header.add("Expires", "0");
	        
	        print("contentLength :"+contentLength);
	        print("fileName :"+fileName);
	        print("header :"+header.toString());
	        print("contentType :"+contentType);
	        print("MediaType.parseMediaType(contentType) :"+MediaType.parseMediaType(contentType));
	        
	        return ResponseEntity.ok()
		             .contentLength(contentLength)
		             .contentType(MediaType.parseMediaType(contentType))
		             .headers(header)
		             .body(resource);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	
	private void print(String string) {
		// TODO Auto-generated method stub
		System.out.print(string);
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

		Map<String, Object> map = new HashMap<>();
		
		if(multipartFiles == null || multipartFiles.length == 0 ) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		List<URI> url = azureBlobService.uploadClientRegistrationFiles(clientId, clientOrganisationId, Constants.Containers.CLIENT_REGISTRATION_FILES.getValue(), multipartFiles);
		
		
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

		Map<String, Object> map = new HashMap<>();
		if(!isValidFileSelected(multipartFiles)) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		List<URI> url = azureBlobService.uploadProjectFiles(projectId, Constants.Containers.PROJECT_FILES.getValue(), multipartFiles);
		
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
	
	private boolean isValidFileSelected(MultipartFile[] multipartFiles) {
		// TODO Auto-generated method stub
		if(multipartFiles == null && multipartFiles.length == 0) {
			return false;
		}
		MultipartFile multipartFile = multipartFiles[0];
		String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
		
		
		if(extension == null || extension.isEmpty() || extension.equals("") || extension.length() == 0) {
			return false;
		}
		return true;
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

		
		Map<String, Object> map = new HashMap<>();
		if(!isValidFileSelected(multipartFiles)) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		List<URI> url = azureBlobService.uploadBidFiles(bidId, Constants.Containers.BID_FILES.getValue(), multipartFiles);
		
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

		Map<String, Object> map = new HashMap<>();
		if(multipartFile == null) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		
		URI url = azureBlobService.uploadProfileImage(userId, userType, Constants.Containers.PROFILE_IMAGES.getValue(), multipartFile);
		
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

		Map<String, Object> map = new HashMap<>();
		if(multipartFile == null) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		URI url = azureBlobService.uploadVendorOrganisationProfileImage(organisationId, Constants.Containers.ORGANISATION_PROFILE_IMAGES.getValue(), multipartFile);
		
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

		Map<String, Object> map = new HashMap<>();
		if(!isValidFileSelected(multipartFiles)) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		List<URI> url = azureBlobService.uploadThreadFiles(threadId, Constants.Containers.MESSAGE_INTERNAL_THREAD_FILES.getValue(), multipartFiles);
		
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

		Map<String, Object> map = new HashMap<>();
		if(multipartFiles == null || multipartFiles.length == 0 ) {
			map.put("statusCode", StatusCode.FILE_NOT_FOUND.getValue());
			map.put("statusMessage", "Failed");
			map.put("responseMessage", "Please select any files to upload");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		List<URI> url = azureBlobService.uploadCommentFiles(commentId, Constants.Containers.MESSAGE_INTERNAL_COMMENT_FILES.getValue(), multipartFiles);
		
		if(url != null) {
			map.put("statusCode", StatusCode.REQUEST_SUCCESS.getValue());
			map.put("statusMessage", "Success");
			map.put("responseMessage", "Message Comment Files Uploaded Successfully");
			map.put("containerName", Constants.Containers.MESSAGE_INTERNAL_COMMENT_FILES.getValue());
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
