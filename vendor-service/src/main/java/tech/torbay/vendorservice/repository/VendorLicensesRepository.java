package tech.torbay.vendorservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.VendorLicenses;

@Repository
public interface VendorLicensesRepository extends JpaRepository<VendorLicenses, Integer> {

    List<VendorLicenses> findAll();
    
    VendorLicenses findOneById(Integer id);
    
    VendorLicenses save(VendorLicenses vendorLicenses);

	List<VendorLicenses> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT license_name FROM vendor_licenses vl " + 
			"WHERE vl.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorLicenses(Integer vendorOrganisationId);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM vendor_licenses WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
