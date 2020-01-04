package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorLicenses;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;

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
	
	@Query(value ="DELETE FROM vendor_licenses WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
