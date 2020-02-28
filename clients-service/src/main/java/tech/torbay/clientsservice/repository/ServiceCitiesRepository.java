package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ServiceCities;

@Repository
public interface ServiceCitiesRepository extends JpaRepository<ServiceCities, Integer> {

    List<ServiceCities> findAll();
    
    ServiceCities findOneById(Integer id);
    
    ServiceCities save(ServiceCities serviceCities);

}
