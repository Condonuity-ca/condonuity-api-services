package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientOrganisationProfileImages;

@Repository
public interface ClientOrganisationProfileImagesRepository extends JpaRepository<ClientOrganisationProfileImages, Integer> {

    List<ClientOrganisationProfileImages> findAll();
    
    ClientOrganisationProfileImages findOneById(Integer Id);

    ClientOrganisationProfileImages findByClientOrganisationId(Integer organisationId);

    ClientOrganisationProfileImages findByBlobName(String blobName);
	
}
