package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientTask;

@Repository
public interface ClientTaskRepository extends JpaRepository<ClientTask, Integer> {

    List<ClientTask> findAll();
	
    @Query(value = "select cc.* from condonuitydev.client_tasks cc where client_organisation_id = (?1) and status = 1", nativeQuery = true)	
	List<ClientTask> findAllByClientOrganisationId(Integer clientOrganisationId);
    
    ClientTask findOneById(Integer id);
    
    ClientTask save(ClientTask clientTask);

    @Query(value = "SELECT ct.* " + 
    		"FROM condonuitydev.client_tasks ct " + 
    		"INNER JOIN condonuitydev.client_user cu " + 
    		"ON (ct.created_by = cu.client_id or ct.modified_by = cu.client_id) " + 
    		"LEFT JOIN condonuitydev.client_user_tasks cut " + 
    		"ON ( ct.id = cut.task_id) \r\n" + 
    		"LEFT JOIN condonuitydev.client_user cu1 ON (cut.client_id = cu1.client_id) " + 
    		"LEFT JOIN condonuitydev.client_task_comments ctc " + 
    		"ON ( ct.id = ctc.task_id) " + 
    		"LEFT JOIN condonuitydev.client_user cu2 ON (ctc.client_id = cu2.client_id) " +
    		"where ( ct.client_organisation_id = (?1) and status = 1 ) and " + 
    		"concat (ct.task_description, '.', ct.closure_date, " + 
    		"'.', ct.others_name, '.', ct.created_at, '.', ct.modified_date) " + 
    		"LIKE (?2) or concat(ctc.comment, '.', ctc.created_at, '.', ctc.modified_date) " + 
    		"LIKE (?2) or concat(cu.first_name, '.', cu.last_name, '.', cu.legal_name) " + 
    		"LIKE (?2) or concat(cu1.first_name, '.', cu1.last_name, '.', cu1.legal_name) " + 
    		"LIKE (?2) or concat(cu2.first_name, '.', cu2.last_name, '.', cu2.legal_name) "
    		+ "LIKE (?2)", nativeQuery = true)
	List<ClientTask> findAllByClientOrganisationIdAndKeyword(Integer clientOrganisationId, String keyword);

	List<ClientTask> findAllByClientOrganisationIdAndPriority(int clientOrganisationId, int priority);

	List<ClientTask> findAllByClientOrganisationIdAndTaskStatus(int clientOrganisationId, int taskStatus);

}
