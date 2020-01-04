package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorMemberships;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;

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
	
	@Query(value ="DELETE FROM vendor_memberships WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
