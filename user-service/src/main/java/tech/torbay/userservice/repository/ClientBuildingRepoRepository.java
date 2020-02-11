package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientTask;

@Repository
public interface ClientBuildingRepoRepository extends JpaRepository<ClientBuildingRepository, Integer> {

    List<ClientBuildingRepository> findAll();
	
    @Query(value = "select br.* from condonuitydev.client_building_repository br where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientBuildingRepository> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientBuildingRepository findOneById(Integer id);
    
    ClientBuildingRepository save(ClientBuildingRepository clientBuildingRepository);

}
