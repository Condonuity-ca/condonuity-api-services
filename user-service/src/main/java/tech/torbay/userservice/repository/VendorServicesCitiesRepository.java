package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorBrands;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorServices;
import tech.torbay.userservice.entity.VendorServicesCities;
import tech.torbay.userservice.entity.VendorUser;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

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

	@Modifying
	@Transactional 
	@Query(value ="delete from vendor_services_cities WHERE vendor_organisation_id = (?1)", nativeQuery = true)
	void deleteByVendorOrganisationId(Integer vendorOrganisationId);
}
