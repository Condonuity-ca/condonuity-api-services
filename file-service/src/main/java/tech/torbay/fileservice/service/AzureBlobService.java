package tech.torbay.fileservice.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
import tech.torbay.fileservice.repository.BidFilesRepository;
import tech.torbay.fileservice.repository.ClientRegistrationFilesRepository;
import tech.torbay.fileservice.repository.ProjectFilesRepository;

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

	public URI uploads(String containerName, MultipartFile multipartFile) {
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
			blob = container.getBlockBlobReference(multipartFile.getOriginalFilename());
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
			blobUri = blob.getUri();
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
			return blobToBeDeleted.deleteIfExists();
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

	public List<URI> uploads(String containerName, List<MultipartFile> multipartFiles) {
		// TODO Auto-generated method stub
		
		List<URI> uris = new ArrayList();
		
		for(MultipartFile multiPartFile : multipartFiles) {
			
			
			uris.add(uploads(containerName, multiPartFile));
		}
		
		return uris;
	}

	
	// client organisation registration multiple file upload
	public List<URI> uploads(Integer clientId, Integer clientOrganisationId, String containerName, List<MultipartFile> multipartFiles) {
		// TODO Auto-generated method stub
		List<URI> uris = new ArrayList();
		
		for(MultipartFile multiPartFile : multipartFiles) {
			URI uri = uploads(containerName, multiPartFile);
			uris.add(uri);
			
			String blobName = multiPartFile.getOriginalFilename();
			String fileType = multiPartFile.getContentType();
			
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
			
			URI uri = uploads(containerName, multipartFile);
			
			String blobName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			ClientRegistrationFiles clientRegistrationFile = new ClientRegistrationFiles();
			clientRegistrationFile.setClientUserId(clientId);
			clientRegistrationFile.setClientOrganisationId(clientOrganisationId);
			clientRegistrationFile.setContainerName(containerName);
			clientRegistrationFile.setBlobName(blobName);
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
			
			URI uri = uploads(containerName, multipartFile);
			
			String blobName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			ProjectFiles projectFiles = new ProjectFiles();
			projectFiles.setProjectId(projectId);
			projectFiles.setContainerName(containerName);
			projectFiles.setBlobName(blobName);
			projectFiles.setFileType(fileType);
			projectFiles.setFileUrl(uri.toString());
			
			projectFilesRepository.save(projectFiles);
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
			
			URI uri = uploads(containerName, multipartFile);
			
			String blobName = multipartFile.getOriginalFilename();
			String fileType = multipartFile.getContentType();
			
			BidFiles bidFiles = new BidFiles();
			bidFiles.setBidId(bidId);
			bidFiles.setContainerName(containerName);
			bidFiles.setBlobName(blobName);
			bidFiles.setFileType(fileType);
			bidFiles.setFileUrl(uri.toString());
			
			bidFilesRepository.save(bidFiles);
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

}
