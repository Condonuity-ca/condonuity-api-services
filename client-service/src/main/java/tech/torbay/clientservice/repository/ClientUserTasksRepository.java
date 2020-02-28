package tech.torbay.clientservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientservice.entity.ClientUserTasks;

@Repository
public interface ClientUserTasksRepository extends JpaRepository<ClientUserTasks, Integer> {

    List<ClientUserTasks> findAll();
    
    List<ClientUserTasks> findAllByTaskId(Integer taskId);
	
    ClientUserTasks findOneById(Integer id);
    
    ClientUserTasks save(ClientUserTasks clientTask);

	@Modifying
	@Transactional 
	@Query(value ="delete from condonuitydev.client_user_tasks WHERE task_id = (?1)", nativeQuery = true)
	void deleteByTaskId(Integer taskId);
}
