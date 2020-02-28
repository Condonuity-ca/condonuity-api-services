package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.VendorUser;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUser, Integer> {

    List<VendorUser> findAll();
    
    VendorUser findByEmail(String email);
    
    VendorUser findByUserId(Integer id);
    
	VendorUser save(VendorUser vendorUser);

	List<VendorUser> findByVendorOrganisationIdAndAccountStatus(Integer id, Integer accountStatus);
	
	@Query(value="SELECT vu.* FROM condonuitydev.vendor_user vu where vendor_organisation_id = (?1) and account_status != 2", nativeQuery = true)
	List<VendorUser> findByVendorOrganisationId(Integer id); // accountstatus = not equal to inactive
}
