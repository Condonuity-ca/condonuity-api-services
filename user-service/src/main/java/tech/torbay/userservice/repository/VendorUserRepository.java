package tech.torbay.userservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorUser;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUser, Integer> {

    List<VendorUser> findAll();
    
    VendorUser findByEmail(String email);
    
    VendorUser findByUserId(Integer id);
    
	VendorUser save(VendorUser vendorUser);

	List<VendorUser> findByVendorOrganisationIdAndAccountStatus(Integer id, Integer accountStatus);
	
	@Query(value="SELECT vu.* FROM condonuitydev.vendor_user vu where vendor_organisation_id = (?1) and account_status != 2 and delete_status != 2", nativeQuery = true)
	List<VendorUser> findByVendorOrganisationId(Integer id); // accountstatus = not equal to inactive
	
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.vendor_user SET delete_status = (?1) WHERE vendor_organisation_id=(?2) ", nativeQuery = true)
	int setDeleteStatusByVendorOrganisationId(Integer activeStatus, Integer organisationId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.vendor_user SET delete_status = (?1) WHERE user_id=(?2) AND vendor_organisation_id = (?3)", nativeQuery = true)
	int setDeleteStatusByVendorIdAndVendorOrganisationId(Integer activeStatus, Integer userId, Integer vendorOrganisationId);
}
