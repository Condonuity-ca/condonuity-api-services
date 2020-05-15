package tech.torbay.fileservice.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import tech.torbay.fileservice.entity.BidFiles;
import tech.torbay.fileservice.entity.ClientOrganisationProfileImages;
import tech.torbay.fileservice.entity.ClientRegistrationFiles;
import tech.torbay.fileservice.entity.CommentFiles;
import tech.torbay.fileservice.entity.ProjectAwardFiles;
import tech.torbay.fileservice.entity.ProjectFiles;
import tech.torbay.fileservice.entity.ThreadFiles;
import tech.torbay.fileservice.entity.UserProfileImages;
import tech.torbay.fileservice.entity.VendorOrganisationProfileImages;
import tech.torbay.fileservice.entity.VendorRegistrationFiles;
import tech.torbay.fileservice.repository.BidFilesRepository;
import tech.torbay.fileservice.repository.ClientOrganisationProfileImagesRepository;
import tech.torbay.fileservice.repository.ClientRegistrationFilesRepository;
import tech.torbay.fileservice.repository.CommentFilesRepository;
import tech.torbay.fileservice.repository.ProjectAwardFilesRepository;
import tech.torbay.fileservice.repository.ProjectFilesRepository;
import tech.torbay.fileservice.repository.ThreadFilesRepository;
import tech.torbay.fileservice.repository.UserProfileImagesRepository;
import tech.torbay.fileservice.repository.VendorOrganisationProfileImagesRepository;
import tech.torbay.fileservice.repository.VendorRegistrationFilesRepository;

@Component
public class AzureBlobService {

	private static final Logger logger = LoggerFactory.getLogger(AzureBlobService.class);

	@Autowired
	private CloudBlobClient cloudBlobClient;
	
	@Autowired
	ClientRegistrationFilesRepository clientRegistrationFilesRepository;
	@Autowired
	ProjectFilesRepository projectFilesRepository;
	@Autowired
	BidFilesRepository bidFilesRepository;
	@Autowired
	UserProfileImagesRepository userProfileImagesRepository;
	@Autowired
	VendorOrganisationProfileImagesRepository vendorOrganisationProfileImagesRepository;
	@Autowired
	ThreadFilesRepository threadFilesRepository;
	@Autowired
	CommentFilesRepository commentFilesRepository;
	@Autowired
	ClientOrganisationProfileImagesRepository clientOrganisationProfileImagesRepository;
	@Autowired
	VendorRegistrationFilesRepository vendorRegistrationFilesRepository;
	@Autowired
	ProjectAwardFilesRepository projectAwardFilesRepository;

	public boolean createContainer(String containerName) {

		try {
			final CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
//			return container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
			return container.createIfNotExists();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (StorageException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public URI uploadBlob(MultipartFile multipartFile, String containerName, String fileName) {

		try {
			final CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			blob.upload(multipartFile.getInputStream(), multipartFile.getSize());
			System.out.println("success upload." + blob.getUri().toString());
			return blob.getUri();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (StorageException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public URI uploads(String containerName, MultipartFile multipartFile, String blobName) {
		URI uri = null;
		CloudBlobContainer container = null;
		CloudBlockBlob blob = null;

		try {
			container = cloudBlobClient.getContainerReference(containerName);
			BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
			containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
			container.uploadPermissions(containerPermissions);
			
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (StorageException e1) {
			e1.printStackTrace();
		}

		try {
			blob = container.getBlockBlobReference(blobName);
			blob.upload(multipartFile.getInputStream(), -1);
			uri = blob.getUri();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (StorageException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uri;
	}

	public List<URI> listBlobs(String containerName) {
		List<URI> uris = new ArrayList<>();
		try {
			CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			for (ListBlobItem blobItem : container.listBlobs()) {
				uris.add(blobItem.getUri());
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (StorageException e) {
			e.printStackTrace();
		}
		return uris;
	}

	public URI getBlob(String containerName, String blobName) {

		URI blobUri = null;
		try {
			CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			CloudBlockBlob blob = container.getBlockBlobReference(blobName);
			if(blob.exists()) {
				blobUri = blob.getUri();
			} else {
				return null;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (StorageException e) {
			e.printStackTrace();
		}

		return blobUri;
	}
	
	public CloudBlockBlob getBlobFile(String containerName, String blobName) {

		URI blobUri = null;
		try {
			CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			CloudBlockBlob blob = container.getBlockBlobReference(blobName);
			if(blob.exists()) {
				return blob;
			} else {
				return null;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (StorageException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean deleteBlob(String containerName, String blobName) {
		try {
			CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			CloudBlockBlob blobToBeDeleted = container.getBlockBlobReference(blobName);
			if(blobToBeDeleted.deleteIfExists()) {
				// file inactive state need to be store or else restrict file delete before this step ?
				return true;
			} else {
				return false;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (StorageException e) {
			e.printStackTrace();
		}
		return false;
	}

	public FileOutputStream downloadFileBlob(String containerName, String blobName) {

		try {
			CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			CloudBlockBlob blob = container.getBlockBlobReference(blobName);

			for (ListBlobItem blobItem : container.listBlobs()) {
				if (blobItem instanceof CloudBlob) {
					CloudBlob cloudBlob = (CloudBlob) blobItem;
					cloudBlob.download(
							new FileOutputStream(System.getProperty("user.home") + cloudBlob.getName()));
				}
			}

		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (StorageException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public boolean isContainerExists(String containerName) {
		// TODO Auto-generated method stub
		try {
			final CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			return container.exists();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (StorageException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	// upload client registration single file	
	public URI uploadClientRegistrationFile(Integer clientId, Integer clientOrganisationId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;

			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			ClientRegistrationFiles clientRegistrationFile = new ClientRegistrationFiles();
			clientRegistrationFile.setClientUserId(clientId);
			clientRegistrationFile.setClientOrganisationId(clientOrganisationId);
			clientRegistrationFile.setContainerName(containerName);
			clientRegistrationFile.setBlobName(blobName);
			clientRegistrationFile.setFileName(fileName);
			clientRegistrationFile.setFileType(fileType);
			clientRegistrationFile.setFileSize(fileSize);
			clientRegistrationFile.setFileUrl(uri.toString());
			
			clientRegistrationFile = clientRegistrationFilesRepository.save(clientRegistrationFile);
			
			if(clientRegistrationFile != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	// upload vendor registration single file	
		public URI uploadVendorRegistrationFile(Integer vendorId, Integer vendorOrganisationId, String containerName, MultipartFile multipartFile) {
			// TODO Auto-generated method stub
			try {
				createContainer(containerName);
				UUID uuid = UUID.randomUUID();
				String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
				String blobName = uuid.toString()+"."+extension;

				URI uri = uploads(containerName, multipartFile, blobName);
				
				String fileName = multipartFile.getOriginalFilename();
				String fileType = multipartFile.getContentType();
				String fileSize = String.valueOf(multipartFile.getSize());
				
				VendorRegistrationFiles vendorRegistrationFile = new VendorRegistrationFiles();
				vendorRegistrationFile.setVendorUserId(vendorId);
				vendorRegistrationFile.setVendorOrganisationId(vendorOrganisationId);
				vendorRegistrationFile.setContainerName(containerName);
				vendorRegistrationFile.setBlobName(blobName);
				vendorRegistrationFile.setFileName(fileName);
				vendorRegistrationFile.setFileType(fileType);
				vendorRegistrationFile.setFileSize(fileSize);
				vendorRegistrationFile.setFileUrl(uri.toString());
				
				vendorRegistrationFile = vendorRegistrationFilesRepository.save(vendorRegistrationFile);
				
				if(vendorRegistrationFile != null) {
					return uri;
				} else {
					return null;
				}
				
			} catch(Exception exp) {
				exp.printStackTrace();
			}
			
			return null;
		}
	
	// client organisation registration multiple file upload
	public List<URI> uploadClientRegistrationFiles(Integer clientId, Integer clientOrganisationId, String containerName, MultipartFile[] multipartFiles) {
		// TODO Auto-generated method stub
		List<URI> uris = new ArrayList();
		
		for(MultipartFile multipartFile : multipartFiles) {
			uris.add(uploadClientRegistrationFile(clientId, clientOrganisationId, containerName, multipartFile));
		}
		
		return uris;
	}
	
	// vendor organisation registration multiple file upload
		public List<URI> uploadVendorRegistrationFiles(Integer vendorId, Integer vendorOrganisationId, String containerName, MultipartFile[] multipartFiles) {
			// TODO Auto-generated method stub
			List<URI> uris = new ArrayList();
			
			for(MultipartFile multipartFile : multipartFiles) {
				uris.add(uploadVendorRegistrationFile(vendorId, vendorOrganisationId, containerName, multipartFile));
			}
			
			return uris;
		}
	
	// single file upload
	public URI uploadProjectFiles(Integer projectId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;

			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			ProjectFiles projectFiles = new ProjectFiles();
			projectFiles.setProjectId(projectId);
			projectFiles.setContainerName(containerName);
			projectFiles.setBlobName(blobName);
			projectFiles.setFileName(fileName);
			projectFiles.setFileType(fileType);
			projectFiles.setFileSize(fileSize);
			projectFiles.setFileUrl(uri.toString());
			
			projectFiles = projectFilesRepository.save(projectFiles);
			if(projectFiles != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	// multiple file upload
	public List<URI> uploadProjectFiles(Integer projectId, String containerName, MultipartFile[] multipartFiles) {
		// TODO Auto-generated method stub
		try {
			List<URI> uris = new ArrayList();
			
			for(MultipartFile multipartFile : multipartFiles) {
				uris.add(uploadProjectFiles(projectId, containerName, multipartFile));
			}
			
			
			return uris;
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	// single file upload
	public URI uploadBidFiles(Integer bidId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;
			
			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			BidFiles bidFiles = new BidFiles();
			bidFiles.setBidId(bidId);
			bidFiles.setContainerName(containerName);
			bidFiles.setBlobName(blobName);
			bidFiles.setFileName(fileName);
			bidFiles.setFileType(fileType);
			bidFiles.setFileSize(fileSize);
			bidFiles.setFileUrl(uri.toString());
			
			bidFiles = bidFilesRepository.save(bidFiles);
			if(bidFiles != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	//multiple file upload
	public List<URI> uploadBidFiles(Integer bidId, String containerName, MultipartFile[] multipartFiles) {
		// TODO Auto-generated method stub
		try {
			
			List<URI> uris = new ArrayList();
			
			for(MultipartFile multipartFile : multipartFiles) {
				uris.add(uploadBidFiles(bidId, containerName, multipartFile));
			}
			return uris;
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public boolean isBlobExists(String containerName, String blobName) {
		// TODO Auto-generated method stub
		try {
			CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
			CloudBlockBlob blob = container.getBlockBlobReference(blobName);
			return blob.exists();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public URI uploadProfileImage(Integer userId, Integer userType, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;
			
			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			UserProfileImages userProfileImages = userProfileImagesRepository.findByUserIdAndUserType(userId, userType);
			
			if(userProfileImages != null) {
				userProfileImages.setContainerName(containerName);
				userProfileImages.setBlobName(blobName);
				userProfileImages.setFileName(fileName);
				userProfileImages.setFileType(fileType);
				userProfileImages.setFileSize(fileSize);
				userProfileImages.setFileUrl(uri.toString());
			} else {
				userProfileImages = new UserProfileImages();
				userProfileImages.setUserType(userType);;
				userProfileImages.setUserId(userId);;
				userProfileImages.setContainerName(containerName);
				userProfileImages.setBlobName(blobName);
				userProfileImages.setFileName(fileName);
				userProfileImages.setFileType(fileType);
				userProfileImages.setFileSize(fileSize);
				userProfileImages.setFileUrl(uri.toString());
			}
			
			
			userProfileImages = userProfileImagesRepository.save(userProfileImages);
			if(userProfileImages != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public URI uploadVendorOrganisationProfileImage(Integer organisationId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;
			
			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			VendorOrganisationProfileImages vendorOrganisationProfileImages = vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(organisationId);
			
			if(vendorOrganisationProfileImages != null) {
				vendorOrganisationProfileImages.setContainerName(containerName);
				vendorOrganisationProfileImages.setBlobName(blobName);
				vendorOrganisationProfileImages.setFileName(fileName);
				vendorOrganisationProfileImages.setFileType(fileType);
				vendorOrganisationProfileImages.setFileSize(fileSize);
				vendorOrganisationProfileImages.setFileUrl(uri.toString());
			} else {
				vendorOrganisationProfileImages = new VendorOrganisationProfileImages();
				vendorOrganisationProfileImages.setVendorOrganisationId(organisationId);;
				vendorOrganisationProfileImages.setContainerName(containerName);
				vendorOrganisationProfileImages.setBlobName(blobName);
				vendorOrganisationProfileImages.setFileName(fileName);
				vendorOrganisationProfileImages.setFileType(fileType);
				vendorOrganisationProfileImages.setFileSize(fileSize);
				vendorOrganisationProfileImages.setFileUrl(uri.toString());
			}
			
			vendorOrganisationProfileImages = vendorOrganisationProfileImagesRepository.save(vendorOrganisationProfileImages);
			if(vendorOrganisationProfileImages != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	public URI uploadClientOrganisationProfileImage(Integer organisationId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;
			
			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			ClientOrganisationProfileImages clientOrganisationProfileImages = clientOrganisationProfileImagesRepository.findByClientOrganisationId(organisationId);
			
			if(clientOrganisationProfileImages != null) {
				clientOrganisationProfileImages.setContainerName(containerName);
				clientOrganisationProfileImages.setBlobName(blobName);
				clientOrganisationProfileImages.setFileName(fileName);
				clientOrganisationProfileImages.setFileType(fileType);
				clientOrganisationProfileImages.setFileSize(fileSize);
				clientOrganisationProfileImages.setFileUrl(uri.toString());
			} else {
				clientOrganisationProfileImages = new ClientOrganisationProfileImages();
				clientOrganisationProfileImages.setClientOrganisationId(organisationId);;
				clientOrganisationProfileImages.setContainerName(containerName);
				clientOrganisationProfileImages.setBlobName(blobName);
				clientOrganisationProfileImages.setFileName(fileName);
				clientOrganisationProfileImages.setFileType(fileType);
				clientOrganisationProfileImages.setFileSize(fileSize);
				clientOrganisationProfileImages.setFileUrl(uri.toString());
			}
			
			ClientOrganisationProfileImages clientOrganisationProfileImagesObj = clientOrganisationProfileImagesRepository.save(clientOrganisationProfileImages);
			if(clientOrganisationProfileImagesObj != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public boolean deleteRegistrationFiles(List<Integer> fileIds) {
		// TODO Auto-generated method stub
		try {
			for(Integer fileId : fileIds) {
				
				ClientRegistrationFiles clientRegistrationFile = clientRegistrationFilesRepository.findOneById(fileId);
				
				String containerName = clientRegistrationFile.getContainerName();
				String blobName = clientRegistrationFile.getBlobName();
				
				if(deleteBlob(containerName, blobName)) {
					// azure file delete 
					clientRegistrationFilesRepository.deleteById(fileId);
				}
			}
			
			return true;
			
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}

	public boolean deleteProjectFiles(List<Integer> fileIds) {
		// TODO Auto-generated method stub
		try {
			for(Integer fileId : fileIds) {
				
				ProjectFiles projectFile = projectFilesRepository.findOneById(fileId);
				
				String containerName = projectFile.getContainerName();
				String blobName = projectFile.getBlobName();
				
				if(deleteBlob(containerName, blobName)) {
					projectFilesRepository.deleteById(fileId);
				}
				
			}
			
			return true;
			
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteBidFiles(List<Integer> fileIds) {
		// TODO Auto-generated method stub
		try {
			for(Integer fileId : fileIds) {
				
				BidFiles bidFile = bidFilesRepository.findOneById(fileId);
				
				String containerName = bidFile.getContainerName();
				String blobName = bidFile.getBlobName();
				
				if(deleteBlob(containerName, blobName)) {
					bidFilesRepository.deleteById(fileId);
				}
				
			}
			
			return true;
			
		} catch(Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	// single file upload
	public URI uploadThreadFiles(Integer threadId, String containerName, MultipartFile multipartFile, Integer threadType) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;
			
			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			ThreadFiles threadFiles = new ThreadFiles();
			threadFiles.setThreadId(threadId);
			threadFiles.setThreadType(threadType);
			threadFiles.setContainerName(containerName);
			threadFiles.setBlobName(blobName);
			threadFiles.setFileName(fileName);
			threadFiles.setFileType(fileType);
			threadFiles.setFileSize(fileSize);
			threadFiles.setFileUrl(uri.toString());
			
			threadFiles = threadFilesRepository.save(threadFiles);
			if(threadFiles != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public List<URI> uploadThreadFiles(Integer bidId, String containerName, MultipartFile[] multipartFiles, Integer threadType) {
		// TODO Auto-generated method stub
		try {
			
			List<URI> uris = new ArrayList();
			
			for(MultipartFile multipartFile : multipartFiles) {
				uris.add(uploadThreadFiles(bidId, containerName, multipartFile, threadType));
			}
			return uris;
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}
	
	public URI uploadCommentFiles(Integer commentId, String containerName, MultipartFile multipartFile, Integer threadType) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			String blobName = uuid.toString()+"."+extension;
			
			URI uri = uploads(containerName, multipartFile, blobName);
			
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			String fileSize = String.valueOf(multipartFile.getSize());
			
			CommentFiles commentFiles = new CommentFiles();
			commentFiles.setCommentId(commentId);
			commentFiles.setThreadType(threadType);
			commentFiles.setContainerName(containerName);
			commentFiles.setBlobName(blobName);
			commentFiles.setFileName(fileName);
			commentFiles.setFileType(fileType);
			commentFiles.setFileSize(fileSize);
			commentFiles.setFileUrl(uri.toString());
			
			commentFiles = commentFilesRepository.save(commentFiles);
			if(commentFiles != null) {
				return uri;
			} else {
				return null;
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public List<URI> uploadCommentFiles(Integer commentId, String containerName, MultipartFile[] multipartFiles, Integer threadType) {
		// TODO Auto-generated method stub
		try {
			
			List<URI> uris = new ArrayList();
			
			for(MultipartFile multipartFile : multipartFiles) {
				uris.add(uploadCommentFiles(commentId, containerName, multipartFile, threadType));
			}
			return uris;
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		return null;
	}

	public Map<String, String> getFileInformation(String containerName, String blobName) {
		// TODO Auto-generated method stub
		
		switch(containerName) {
			case "clientregistrationfiles":{
				ClientRegistrationFiles clientRegistrationFiles =  clientRegistrationFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", clientRegistrationFiles.getFileName());
				map.put("fileType", clientRegistrationFiles.getFileType());
				return map;
			}
			case "vendorregistrationfiles":{
				VendorRegistrationFiles vendorRegistrationFiles =  vendorRegistrationFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", vendorRegistrationFiles.getFileName());
				map.put("fileType", vendorRegistrationFiles.getFileType());
			}
			case "projectfiles":{
				ProjectFiles projectFiles = projectFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", projectFiles.getFileName());
				map.put("fileType", projectFiles.getFileType());
				return map;
			}
			case "bidfiles":{
				BidFiles bidFiles = bidFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", bidFiles.getFileName());
				map.put("fileType", bidFiles.getFileType());
				return map;
			}
			case "profileimages":{
				UserProfileImages userProfileImages = userProfileImagesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", userProfileImages.getFileName());
				map.put("fileType", userProfileImages.getFileType());
				return map;
			}
			case "organisationprofileimages":{
				Map<String, String> map = new HashMap();
				VendorOrganisationProfileImages vendorOrganisationProfileImages = vendorOrganisationProfileImagesRepository.findByBlobName(blobName);
				if (vendorOrganisationProfileImages != null) {
					map.put("fileName", vendorOrganisationProfileImages.getFileName());
					map.put("fileType", vendorOrganisationProfileImages.getFileType());
				} else { 
					ClientOrganisationProfileImages clientOrganisationProfileImages = clientOrganisationProfileImagesRepository.findByBlobName(blobName);
					if (clientOrganisationProfileImages != null) {
						map.put("fileName", clientOrganisationProfileImages.getFileName());
						map.put("fileType", clientOrganisationProfileImages.getFileType());
					} else {
						map.put("fileName", "");
						map.put("fileType", "");
					}
				}
				return map;
			}
			case "internalthreadfiles":
			case "externalthreadfiles":{
				ThreadFiles threadFiles = threadFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", threadFiles.getFileName());
				map.put("fileType", threadFiles.getFileType());
				return map;
			}
			case "internalCommentfiles":
			case "externalCommentfiles":{
				CommentFiles commentFiles = commentFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", commentFiles.getFileName());
				map.put("fileType", commentFiles.getFileType());
				return map;
			}
			case "projectawardfiles":{
				ProjectAwardFiles projectAwardFiles = projectAwardFilesRepository.findByBlobName(blobName);
				Map<String, String> map = new HashMap();
				map.put("fileName", projectAwardFiles.getFileName());
				map.put("fileType", projectAwardFiles.getFileType());
				return map;
			}
		}
		return null;
		
	}
	
	// multiple file upload
		public List<URI> uploadProjectAwardFiles(Integer projectAwardId, String containerName, MultipartFile[] multipartFiles) {
			// TODO Auto-generated method stub
			try {
				List<URI> uris = new ArrayList();
				
				for(MultipartFile multipartFile : multipartFiles) {
					uris.add(uploadProjectAwardFile(projectAwardId, containerName, multipartFile));
				}
				
				
				return uris;
			} catch(Exception exp) {
				exp.printStackTrace();
			}
			
			return null;
		}
		
		// single file upload
		public URI uploadProjectAwardFile(Integer projectAwardId, String containerName, MultipartFile multipartFile) {
			// TODO Auto-generated method stub
			try {
				createContainer(containerName);
				UUID uuid = UUID.randomUUID();
				String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
				String blobName = uuid.toString()+"."+extension;

				URI uri = uploads(containerName, multipartFile, blobName);
				
				String fileName = multipartFile.getOriginalFilename();
				String fileType = multipartFile.getContentType();
				String fileSize = String.valueOf(multipartFile.getSize());
				
				ProjectAwardFiles projectAwardFiles = new ProjectAwardFiles();
				projectAwardFiles.setProjectAwardId(projectAwardId);
				projectAwardFiles.setContainerName(containerName);
				projectAwardFiles.setBlobName(blobName);
				projectAwardFiles.setFileName(fileName);
				projectAwardFiles.setFileType(fileType);
				projectAwardFiles.setFileSize(fileSize);
				projectAwardFiles.setFileUrl(uri.toString());
				
				ProjectAwardFiles projectAwardFilesObj = projectAwardFilesRepository.save(projectAwardFiles);
				if(projectAwardFilesObj != null) {
					return uri;
				} else {
					return null;
				}
				
			} catch(Exception exp) {
				exp.printStackTrace();
			}
			
			return null;
		}
}
