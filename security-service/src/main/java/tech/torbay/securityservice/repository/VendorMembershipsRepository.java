package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.VendorMemberships;

import java.util.List;

@Repository
public interface VendorMembershipsRepository extends JpaRepository<VendorMemberships, Integer> {

    List<VendorMemberships> findAll();
    
    VendorMemberships findOneById(Integer id);
    
    VendorMemberships save(VendorMemberships vendorMemberships);

	List<VendorMemberships> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT membership_name FROM vendor_memberships vm " + 
			"WHERE vm.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorMemberships(Integer vendorOrganisationId);
	
}
