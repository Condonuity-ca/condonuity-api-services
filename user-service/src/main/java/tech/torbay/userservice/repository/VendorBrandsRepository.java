package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

import javax.transaction.Transactional;

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