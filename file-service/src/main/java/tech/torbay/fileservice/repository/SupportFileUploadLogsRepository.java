package tech.torbay.fileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.fileservice.entity.SupportFileUploadLogs;
import tech.torbay.fileservice.entity.VendorRegistrationFiles;

@Repository
public interface SupportFileUploadLogsRepository extends JpaRepository<SupportFileUploadLogs, Integer> {

    List<SupportFileUploadLogs> findAll();
    
    SupportFileUploadLogs findOneById(Integer Id);

	List<SupportFileUploadLogs> findAllByFileId(Integer vendorOrganisationId);

	VendorRegistrationFiles findByBlobName(String BlobName);
	
}
