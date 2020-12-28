package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;

import java.util.List;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUser, Integer> {

    List<VendorUser> findAll();
    
    VendorUser findByEmail(String email);

	@SuppressWarnings("unchecked")
	VendorUser save(VendorUser vendorUser);

	VendorUser findByUserId(Integer userId);

	@Query(value = "select vu.* from vendor_user vu where vu.vendor_organisation_id = (?1)  AND ( vu.account_status = 1 OR vu.account_status = 0 )", nativeQuery = true)//only active users
	List<VendorUser> findAllByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "select vu.* from vendor_user vu where vu.vendor_organisation_id = (?1)  AND delete_status = 1 and ( vu.account_status = 1 OR vu.account_status = 0 )", nativeQuery = true)//only active, invited users
	List<VendorUser> findAllActiveAndInvitedVendorUsers(Integer vendorOrganisationId);
}
