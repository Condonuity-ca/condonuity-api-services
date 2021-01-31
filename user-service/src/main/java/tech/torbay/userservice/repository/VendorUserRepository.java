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
	
	@Query(value="SELECT vu.* FROM vendor_user vu where vendor_organisation_id = (?1) and account_status != 2 and delete_status != 2", nativeQuery = true)
	List<VendorUser> findByVendorOrganisationId(Integer id); // accountstatus = not equal to inactive
	
	@Modifying
	@Transactional
	@Query(value="UPDATE vendor_user SET delete_status = (?1) WHERE vendor_organisation_id=(?2) ", nativeQuery = true)
	int setDeleteStatusByVendorOrganisationId(Integer activeStatus, Integer organisationId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE vendor_user SET account_status = (?1) WHERE vendor_organisation_id=(?2) ", nativeQuery = true)
	int setAccountStatusByVendorOrganisationId(Integer activeStatus, Integer organisationId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE vendor_user SET delete_status = (?1) WHERE user_id=(?2) AND vendor_organisation_id = (?3)", nativeQuery = true)
	int setDeleteStatusByVendorIdAndVendorOrganisationId(Integer activeStatus, Integer userId, Integer vendorOrganisationId);

	@Modifying
	@Transactional
	@Query(value="UPDATE vendor_user SET first_name = (?1) , last_name = (?2), user_role = (?3) WHERE user_id=(?4) AND vendor_organisation_id = (?5)", nativeQuery = true)
	int setFirstNameAndLastNameAndUserRoleByVendorIdAndVendorOrganisationId(String firstName, String lastName, Integer userRole, Integer userId,
			Integer organisationId);
	
	@Query(value = "select vu.* from vendor_user vu where vu.vendor_organisation_id = (?1)  AND ( vu.account_status = 1 OR vu.account_status = 0 )", nativeQuery = true)//only active users
	List<VendorUser> findAllByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "select vu.* from vendor_user vu where vu.vendor_organisation_id = (?1)  AND ( vu.account_status = 1 )", nativeQuery = true)//only active users
	List<VendorUser> findAllActiveUsersByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT vu.* FROM vendor_user vu where " + 
			"concat (vu.first_name, '.', vu.last_name, '.', vu.email, '.', vu.created_at, '.', vu.modified_date)" + 
			" LIKE (?1) "
			, nativeQuery = true)
	List<VendorUser> findAllByKeyword(String keyword);

	List<VendorUser> findAllByDeleteStatus(int value);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE vendor_user SET delete_status = (?1) WHERE user_id=(?2)", nativeQuery = true)
	int setDeleteStatusByVendorId(Integer activeStatus, Integer userId);

	@Query(value = "select vu.* from vendor_user vu where ( vu.vendor_organisation_id = (?1)  AND  vu.account_status = (?2) AND vu.delete_status = (?3) )", nativeQuery = true)
	List<VendorUser> findUsersByVendorOrganisationIdAndActiveStatusAndDeleteStatus(Integer organisationId, int activeStatus,
			int deleteStatus);

	@Query(value = "select vu.* from vendor_user vu where ( vu.account_status = 1 AND vu.account_verification_status = 1 AND vu.delete_status = 1 )", nativeQuery = true)
	List<VendorUser> findAllActiveVendorUserByAccountAndVerificationAndDeleteStatus();

}
