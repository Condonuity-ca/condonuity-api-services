package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientAmenities;

@Repository
public interface ClientAmenitiesRepository extends JpaRepository<ClientAmenities, Integer> {

    List<ClientAmenities> findAll();

    List<ClientAmenities> findAllByClientOrganisationId(Integer id);
    
//    ClientAmenities findById(Integer id);
    
	ClientAmenities save(ClientAmenities clientAmenities);
}
