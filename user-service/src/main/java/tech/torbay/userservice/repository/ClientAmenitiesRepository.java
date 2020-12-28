package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientAmenities;
import tech.torbay.userservice.entity.ClientOrganisation;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface ClientAmenitiesRepository extends JpaRepository<ClientAmenities, Integer> {

    List<ClientAmenities> findAll();

    List<ClientAmenities> findAllByClientOrganisationId(Integer id);
    
//    ClientAmenities findById(Integer id);
    
	ClientAmenities save(ClientAmenities clientAmenities);
	
	@Modifying
	@Transactional 
	@Query(value ="delete from client_organization_amenities WHERE client_organisation_id = (?1)", nativeQuery = true)
	void deleteByClientOrganisationId(Integer clientOrganisationId);
}
