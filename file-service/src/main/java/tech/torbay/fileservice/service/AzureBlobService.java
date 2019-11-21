package tech.torbay.fileservice.service;


import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AzureBlobService {

    private static final Logger logger = LoggerFactory.getLogger(AzureBlobService.class);

    @Autowired
    private CloudBlobClient cloudBlobClient;

    public URI createContainer(String containerName){
        CloudBlobContainer container = null;
        try {
            container = cloudBlobClient.getContainerReference(containerName);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (StorageException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        try {
            container.createIfNotExists(BlobContainerPublicAccessType.OFF, new BlobRequestOptions(), new OperationContext());
        } catch (StorageException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return container.getUri();
    }

    public URI upload(String containerName, MultipartFile multipartFile){
        URI uri = null;
        CloudBlobContainer container = null;
        CloudBlockBlob blob = null;
        try {
            container = cloudBlobClient.getContainerReference(containerName);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (StorageException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        try {
            UUID uuid = UUID.randomUUID();
            blob = container.getBlockBlobReference(uuid.toString());
            blob.upload(multipartFile.getInputStream(), -1);
            uri = blob.getUri();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }

    public List<URI> listBlobs(String containerName){
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

    public void deleteBlob(String containerName, String blobName){
        try {
            CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
            CloudBlockBlob blobToBeDeleted = container.getBlockBlobReference(blobName);
            blobToBeDeleted.deleteIfExists();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }


}
