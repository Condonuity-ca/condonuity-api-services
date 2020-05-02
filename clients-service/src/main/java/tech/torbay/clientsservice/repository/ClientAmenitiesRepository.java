package tech.torbay.clientsservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientAmenities;

@Repository
public interface ClientAmenitiesRepository extends JpaRepository<ClientAmenities, Integer> {

    List<ClientAmenities> findAll();

    List<ClientAmenities> findAllByClientOrganisationId(Integer id);
    
//    ClientAmenities findById(Integer id);
    
	ClientAmenities save(ClientAmenities clientAmenities);
	
	@Modifying
	@Transactional 
	@Query(value ="delete from condonuitydev.client_organization_amenities WHERE client_organisation_id = (?1)", nativeQuery = true)
	void deleteByClientOrganisationId(Integer clientOrganisationId);
}
