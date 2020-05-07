package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorRegistrationFiles;

@Repository
public interface VendorRegistrationFilesRepository extends JpaRepository<VendorRegistrationFiles, Integer> {

    List<VendorRegistrationFiles> findAll();
    
    VendorRegistrationFiles findOneById(Integer Id);

	List<VendorRegistrationFiles> findAllByVendorOrganisationId(Integer vendorOrganisationId);

	VendorRegistrationFiles findByBlobName(String BlobName);
	
}
