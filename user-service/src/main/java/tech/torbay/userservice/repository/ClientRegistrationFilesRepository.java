package tech.torbay.userservice.repository;

import java.util.HashMap;
import java.util.List;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientRegistrationFiles;

@Repository
public interface ClientRegistrationFilesRepository extends JpaRepository<ClientRegistrationFiles, Integer> {

    List<ClientRegistrationFiles> findAll();
    
    ClientRegistrationFiles findOneById(Integer Id);

	List<ClientRegistrationFiles> findAllByClientOrganisationId(Integer clientOrganisationId);
	
}
