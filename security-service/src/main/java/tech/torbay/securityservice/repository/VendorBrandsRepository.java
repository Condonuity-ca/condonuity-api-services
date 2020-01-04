package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.VendorBrands;

import java.util.List;

@Repository
public interface VendorBrandsRepository extends JpaRepository<VendorBrands, Integer> {

    List<VendorBrands> findAll();
    
    VendorBrands findOneById(Integer id);
    
    VendorBrands save(VendorBrands vendorBrands);

	List<VendorBrands> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT brand_name FROM vendor_brands vb " + 
			"WHERE vb.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorBrands(Integer vendorOrganisationId);
}
