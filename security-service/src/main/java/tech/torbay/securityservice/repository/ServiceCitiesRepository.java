package tech.torbay.securityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.SupportUser;

@Repository
public interface ServiceCitiesRepository extends JpaRepository<ServiceCities, Integer> {

	List<ServiceCities> findAll();
	
	@Query(value = "select distinct city_province from condonuitydev.service_cities", nativeQuery = true )
    List<String> findAllCityProvinces();
    
	ServiceCities findOneById(Integer id);
}
