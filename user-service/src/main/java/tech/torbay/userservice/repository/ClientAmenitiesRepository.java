package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientOrganisation;

import java.util.List;

@Repository
public interface ClientAmenitiesRepository extends JpaRepository<ClientAmenities, Integer> {

    List<ClientAmenities> findAll();

    List<ClientAmenities> findAllByOrganisationId(Integer id);
    
//    ClientAmenities findById(Integer id);
}
