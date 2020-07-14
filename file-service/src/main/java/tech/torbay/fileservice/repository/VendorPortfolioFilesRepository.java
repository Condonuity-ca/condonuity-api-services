package tech.torbay.fileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.fileservice.entity.VendorPortfolioFiles;

@Repository
public interface VendorPortfolioFilesRepository extends JpaRepository<VendorPortfolioFiles, Integer> {

    List<VendorPortfolioFiles> findAll();
    
    VendorPortfolioFiles findOneById(Integer Id);

	List<VendorPortfolioFiles> findAllByVendorPortfolioId(Integer vendorPortfolioFiles);

	VendorPortfolioFiles findByBlobName(String blobName);
	
}
