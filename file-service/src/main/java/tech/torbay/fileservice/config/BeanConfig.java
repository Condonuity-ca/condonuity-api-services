package tech.torbay.fileservice.config;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.file.CloudFileClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.servlet.MultipartConfigElement;

@Configuration
public class BeanConfig {

	@Autowired
	private Environment environment;

	@Bean
	public CloudFileClient cloudFileClient() throws URISyntaxException, StorageException, InvalidKeyException {
		CloudStorageAccount storageAccount = CloudStorageAccount
				.parse(environment.getProperty("azure.storage.ConnectionString"));
		return storageAccount.createCloudFileClient();
	}

	@Bean
	public CloudBlobClient cloudBlobClient() throws URISyntaxException, StorageException, InvalidKeyException {
		CloudStorageAccount storageAccount = CloudStorageAccount
				.parse(environment.getProperty("azure.storage.ConnectionString"));
		return storageAccount.createCloudBlobClient();
	}

	@Bean
	public CloudBlobContainer testBlobContainer() throws URISyntaxException, StorageException, InvalidKeyException {
		return cloudBlobClient().getContainerReference(environment.getProperty("azure.storage.container.name"));
	}

//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//	    MultipartConfigFactory factory = new MultipartConfigFactory();
//	    factory.setMaxFileSize(DataSize.ofBytes(100000000L));
//	    factory.setMaxRequestSize(DataSize.ofBytes(100000000L));
//	    return factory.createMultipartConfig();
//	}
	
//	@Bean
//	public MultipartResolver multipartResolver() {
//	    CommonsMultipartResolver multipartResolver
//	      = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(5242880);
//	    return multipartResolver;
//	}
}
