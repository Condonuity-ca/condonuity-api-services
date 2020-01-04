package tech.torbay.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.VendorServicesCities;

import java.util.HashMap;
import java.util.List;

@Repository
public interface VendorServicesCitiesRepository extends JpaRepository<VendorServicesCities, Integer> {

    List<VendorServicesCities> findAll();
    
    VendorServicesCities findOneById(Integer id);
    
    VendorServicesCities save(VendorServicesCities vendorServices);

	List<VendorServicesCities> findByVendorOrganisationId(Integer vendorOrganisationId);

	@Query(value = "SELECT city_name FROM vendor_services_cities vsc " + 
			"INNER JOIN service_cities sc ON sc.id = vsc.service_city_id " + 
			"WHERE vsc.vendor_organisation_id = (?1)", nativeQuery = true)
	List<String> getServiceCities(Integer vendorOrganisationId);
}
