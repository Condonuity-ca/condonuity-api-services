package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.securityservice.entity.ClientUser;
import tech.torbay.securityservice.entity.SupportUser;
import tech.torbay.securityservice.entity.VendorOrganisation;
import tech.torbay.securityservice.entity.VendorUser;

import java.util.List;

@Repository
public interface SupportUserRepository extends JpaRepository<SupportUser, Integer> {

    List<SupportUser> findAll();
    
    SupportUser findByEmail(String email);

	@SuppressWarnings("unchecked")
	SupportUser save(SupportUser vendorUser);

//	SupportUser findById(Integer id);
	
	SupportUser findOneById(Integer id);
}
