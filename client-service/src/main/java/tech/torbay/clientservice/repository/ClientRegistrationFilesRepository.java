package tech.torbay.clientservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientservice.entity.ClientRegistrationFiles;

@Repository
public interface ClientRegistrationFilesRepository extends JpaRepository<ClientRegistrationFiles, Integer> {

    List<ClientRegistrationFiles> findAll();
    
    ClientRegistrationFiles findOneById(Integer Id);

	List<ClientRegistrationFiles> findAllByClientOrganisationId(Integer clientOrganisationId);
	
}