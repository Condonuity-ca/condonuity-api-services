package tech.torbay.vendorservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.VendorMemberships;

@Repository
public interface VendorMembershipsRepository extends JpaRepository<VendorMemberships, Integer> {

    List<VendorMemberships> findAll();
    
    VendorMemberships findOneById(Integer id);
    
    VendorMemberships save(VendorMemberships vendorMemberships);

	List<VendorMemberships> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT membership_name FROM vendor_memberships vm " + 
			"WHERE vm.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorMemberships(Integer vendorOrganisationId);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM vendor_memberships WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
