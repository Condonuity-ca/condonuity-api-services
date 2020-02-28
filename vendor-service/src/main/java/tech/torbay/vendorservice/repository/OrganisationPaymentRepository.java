package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.OrganisationPayment;

@Repository
public interface OrganisationPaymentRepository extends JpaRepository<OrganisationPayment, Integer> {

    List<OrganisationPayment> findAll();

    List<OrganisationPayment> findAllByOrganisationId(Integer id);
    
//    ClientAmenities findById(Integer id);
}
