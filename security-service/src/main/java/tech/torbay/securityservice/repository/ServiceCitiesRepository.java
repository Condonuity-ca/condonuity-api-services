package tech.torbay.securityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.ServiceCities;
import tech.torbay.securityservice.entity.SupportUser;

@Repository
public interface ServiceCitiesRepository extends JpaRepository<ServiceCities, Integer> {

    List<ServiceCities> findAll();
    
	ServiceCities findOneById(Integer id);
}
