package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientTask;

@Repository
public interface ClientTaskRepository extends JpaRepository<ClientTask, Integer> {

    List<ClientTask> findAll();
	
    @Query(value = "select cc.* from condonuitydev.client_tasks cc where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientTask> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientTask findOneById(Integer id);
    
    ClientTask save(ClientTask clientTask);

}
