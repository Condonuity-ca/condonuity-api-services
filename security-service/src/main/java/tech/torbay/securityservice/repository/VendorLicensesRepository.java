package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.VendorLicenses;

import java.util.List;

@Repository
public interface VendorLicensesRepository extends JpaRepository<VendorLicenses, Integer> {

    List<VendorLicenses> findAll();
    
    VendorLicenses findOneById(Integer id);
    
    VendorLicenses save(VendorLicenses vendorLicenses);

	List<VendorLicenses> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT license_name FROM vendor_licenses vl " + 
			"WHERE vl.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorLicenses(Integer vendorOrganisationId);
}
