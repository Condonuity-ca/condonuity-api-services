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

	@Query(value = "select vu from condonuitydev.vendor_user vu where vendor_organisation_id = (?1)  AND account_status = 1 AND account_status = 0", nativeQuery = true)//only active users
	List<VendorUser> findAllByVendorOrganisationId(Integer vendorOrganisationId);
}
