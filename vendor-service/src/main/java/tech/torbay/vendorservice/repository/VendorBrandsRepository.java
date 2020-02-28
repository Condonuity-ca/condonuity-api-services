package tech.torbay.vendorservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.VendorBrands;

@Repository
public interface VendorBrandsRepository extends JpaRepository<VendorBrands, Integer> {

    List<VendorBrands> findAll();
    
    VendorBrands findOneById(Integer id);
    
    VendorBrands save(VendorBrands vendorBrands);

	List<VendorBrands> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT brand_name FROM vendor_brands vb " + 
			"WHERE vb.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorBrands(Integer vendorOrganisationId);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM vendor_brands WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
