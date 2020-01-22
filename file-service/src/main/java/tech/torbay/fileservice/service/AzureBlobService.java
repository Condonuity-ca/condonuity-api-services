package tech.torbay.fileservice.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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
import tech.torbay.fileservice.entity.ClientRegistrationFiles;
import tech.torbay.fileservice.entity.ProjectFiles;
import tech.torbay.fileservice.entity.UserProfileImages;
import tech.torbay.fileservice.entity.VendorOrganisationProfileImages;
import tech.torbay.fileservice.repository.BidFilesRepository;
import tech.torbay.fileservice.repository.ClientRegistrationFilesRepository;
import tech.torbay.fileservice.repository.ProjectFilesRepository;
import tech.torbay.fileservice.repository.UserProfileImagesRepository;
import tech.torbay.fileservice.repository.VendorOrganisationProfileImagesRepository;

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

	
	// client organisation registration multiple file upload
	public List<URI> uploads(Integer clientId, Integer clientOrganisationId, String containerName, List<MultipartFile> multipartFiles) {
		// TODO Auto-generated method stub
		List<URI> uris = new ArrayList();
		
		for(MultipartFile multipartFile : multipartFiles) {
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			URI uri = uploads(containerName, multipartFile, uuid.toString()+"."+extension);
			uris.add(uri);
			
			String blobName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			ClientRegistrationFiles clientRegistrationFile = new ClientRegistrationFiles();
			clientRegistrationFile.setClientUserId(clientId);
			clientRegistrationFile.setClientOrganisationId(clientOrganisationId);
			clientRegistrationFile.setContainerName(containerName);
			clientRegistrationFile.setBlobName(blobName);
			clientRegistrationFile.setFileType(fileType);
			clientRegistrationFile.setFileUrl(uri.toString());
			
			clientRegistrationFilesRepository.save(clientRegistrationFile);
			
		}
		
		return uris;
	}
	
	public URI uploadClientRegistrationFiles(Integer clientId, Integer clientOrganisationId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			URI uri = uploads(containerName, multipartFile, uuid.toString()+"."+extension);
			
			String blobName = uuid.toString();
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			ClientRegistrationFiles clientRegistrationFile = new ClientRegistrationFiles();
			clientRegistrationFile.setClientUserId(clientId);
			clientRegistrationFile.setClientOrganisationId(clientOrganisationId);
			clientRegistrationFile.setContainerName(containerName);
			clientRegistrationFile.setBlobName(blobName);
			clientRegistrationFile.setFileName(fileName);
			clientRegistrationFile.setFileType(fileType);
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
	
	
	public URI uploadProjectFiles(Integer projectId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			URI uri = uploads(containerName, multipartFile, uuid.toString()+"."+extension);
			
			String blobName = uuid.toString();
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			ProjectFiles projectFiles = new ProjectFiles();
			projectFiles.setProjectId(projectId);
			projectFiles.setContainerName(containerName);
			projectFiles.setBlobName(blobName);
			projectFiles.setFileName(fileName);
			projectFiles.setFileType(fileType);
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

	public URI uploadBidFiles(Integer bidId, String containerName, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		try {
			createContainer(containerName);
			UUID uuid = UUID.randomUUID();
			String extension = Files.getFileExtension(multipartFile.getOriginalFilename());
			URI uri = uploads(containerName, multipartFile, uuid.toString()+"."+extension);
			
			String blobName = uuid.toString();
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			BidFiles bidFiles = new BidFiles();
			bidFiles.setBidId(bidId);
			bidFiles.setContainerName(containerName);
			bidFiles.setBlobName(blobName);
			bidFiles.setFileName(fileName);
			bidFiles.setFileType(fileType);
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
			URI uri = uploads(containerName, multipartFile, uuid.toString()+"."+extension);
			
			String blobName = uuid.toString();
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			UserProfileImages userProfileImages = userProfileImagesRepository.findByUserIdAndUserType(userId, userType);
			
			if(userProfileImages != null) {
				userProfileImages.setContainerName(containerName);
				userProfileImages.setBlobName(blobName);
				userProfileImages.setFileName(fileName);
				userProfileImages.setFileType(fileType);
				userProfileImages.setFileUrl(uri.toString());
			} else {
				userProfileImages = new UserProfileImages();
				userProfileImages.setUserType(userType);;
				userProfileImages.setUserId(userId);;
				userProfileImages.setContainerName(containerName);
				userProfileImages.setBlobName(blobName);
				userProfileImages.setFileName(fileName);
				userProfileImages.setFileType(fileType);
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
			URI uri = uploads(containerName, multipartFile, uuid.toString()+"."+extension);
			
			String blobName = uuid.toString();
			String fileName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			VendorOrganisationProfileImages vendorOrganisationProfileImages = vendorOrganisationProfileImagesRepository.findByVendorOrganisationId(organisationId);
			
			if(vendorOrganisationProfileImages != null) {
				vendorOrganisationProfileImages.setContainerName(containerName);
				vendorOrganisationProfileImages.setBlobName(blobName);
				vendorOrganisationProfileImages.setFileName(fileName);
				vendorOrganisationProfileImages.setFileType(fileType);
				vendorOrganisationProfileImages.setFileUrl(uri.toString());
			} else {
				vendorOrganisationProfileImages = new VendorOrganisationProfileImages();
				vendorOrganisationProfileImages.setVendorOrganisationId(organisationId);;
				vendorOrganisationProfileImages.setContainerName(containerName);
				vendorOrganisationProfileImages.setBlobName(blobName);
				vendorOrganisationProfileImages.setFileName(fileName);
				vendorOrganisationProfileImages.setFileType(fileType);
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

}
