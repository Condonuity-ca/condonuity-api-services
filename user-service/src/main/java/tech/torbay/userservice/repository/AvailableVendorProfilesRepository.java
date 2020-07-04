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

    AvailableVendorProfiles save(VendorOrganisation vendorOrganisation);
	
    AvailableVendorProfiles findByAllocatedVendorOrgId(Integer id);
    
    AvailableVendorProfiles findByVendorProfileId(Integer id);

	@Query(value="Select avp.* FROM condonuitydev.available_vendor_profiles vo where avp.active_status =(?1);", nativeQuery = true)
	List<VendorOrganisation> findAllByActiveStatus(int activeStatus);

}
