package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.AvailableVendorProfiles;
import tech.torbay.userservice.entity.VendorOrganisation;

@Repository
public interface AvailableVendorProfilesRepository extends JpaRepository<AvailableVendorProfiles, Integer> {

    List<AvailableVendorProfiles> findAll();

    AvailableVendorProfiles save(AvailableVendorProfiles availableVendorProfiles);
	
    AvailableVendorProfiles findByAllocatedVendorOrgId(Integer id);
    
    AvailableVendorProfiles findByVendorProfileId(Integer id);

	@Query(value="Select avp.* FROM available_vendor_profiles avp where ( avp.active_status =(?1) and avp.allocated_vendor_org_id = 0);", nativeQuery = true)
	List<AvailableVendorProfiles> findAllByActiveStatus(int activeStatus);

}
