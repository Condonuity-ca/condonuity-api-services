package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorServices;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface VendorServicesRepository extends JpaRepository<VendorServices, Integer> {

    List<VendorServices> findAll();
    
    VendorServices findOneById(Integer id);
    
    VendorServices save(VendorServices vendorServices);

	List<VendorServices> findByVendorOrganisationId(Integer vendorOrganisationId);
	
	@Query(value = "SELECT service_name FROM vendor_services vs " + 
			"WHERE vs.vendor_organisation_id = (?1)", nativeQuery =true)
	List<String> getVendorServices(Integer vendorOrganisationId);
	
	@Modifying
	@Transactional
	@Query(value ="DELETE FROM vendor_services WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
