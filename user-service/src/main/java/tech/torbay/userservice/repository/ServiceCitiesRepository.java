package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ServiceCities;
import tech.torbay.userservice.entity.VendorUser;

@Repository
public interface ServiceCitiesRepository extends JpaRepository<ServiceCities, Integer> {

    List<ServiceCities> findAll();
    
    ServiceCities findOneById(Integer id);
    
    ServiceCities save(ServiceCities vendorUser);

}
