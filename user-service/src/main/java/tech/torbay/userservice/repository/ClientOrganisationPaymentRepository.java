package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientOrganisationPayment;

import java.util.List;

@Repository
public interface ClientOrganisationPaymentRepository extends JpaRepository<ClientOrganisationPayment, Integer> {

    List<ClientOrganisationPayment> findAll();

    List<ClientOrganisationPayment> findAllByOrganisationId(Integer id);
    
//    ClientAmenities findById(Integer id);
}
