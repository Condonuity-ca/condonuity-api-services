package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorUser;

@Repository
public interface VendorUserRepository extends JpaRepository<VendorUser, Integer> {

    List<VendorUser> findAll();
    
    VendorUser findByEmail(String email);
    
    VendorUser findByUserId(Integer id);
    
	VendorUser save(VendorUser vendorUser);

	List<VendorUser> findByVendorOrganisationIdAndAccountStatus(Integer id, Integer accountStatus);
	
	List<VendorUser> findByVendorOrganisationId(Integer id);
}
