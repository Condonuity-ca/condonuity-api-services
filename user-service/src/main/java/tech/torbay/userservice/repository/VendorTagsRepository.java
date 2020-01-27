package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorServices;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface VendorTagsRepository extends JpaRepository<VendorTags, Integer> {

    List<VendorTags> findAll();
    
    VendorTags findOneById(Integer id);
    
    VendorTags save(VendorTags vendorTags);

	@Modifying
	@Transactional
	@Query(value ="DELETE FROM vendor_services WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
