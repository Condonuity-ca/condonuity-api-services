package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorOrganisation;

import java.util.List;

@Repository
public interface VendorOrganisationRepository extends JpaRepository<VendorOrganisation, Integer> {

    List<VendorOrganisation> findAll();

	VendorOrganisation save(VendorOrganisation vendorOrganisation);
	
	VendorOrganisation findByVendorOrgId(Integer id);
}
