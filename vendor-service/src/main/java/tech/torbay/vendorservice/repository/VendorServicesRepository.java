package tech.torbay.vendorservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.VendorServices;

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