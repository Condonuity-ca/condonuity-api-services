package tech.torbay.clientsservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.ClientTaskComments;

@Repository
public interface ClientTaskCommentsRepository extends JpaRepository<ClientTaskComments, Integer> {

    List<ClientTaskComments> findAll();
	
    @Query(value = "select cc.* from condonuitydev.client_task_comments cc where task_id = (?1)", nativeQuery = true)	
	List<ClientTaskComments> findAllByTaskId(Integer taskId);
    
    ClientTaskComments findOneById(Integer id);
    
    ClientTaskComments save(ClientTaskComments clientTask);

    @Query(value = "insert into condonuitydev.client_task_comments tc values (?1)", nativeQuery = true)
	ClientTaskComments saveComment(ClientTaskComments taskComment);

    @Modifying
	@Transactional
    @Query(value = "UPDATE condonuitydev.client_task_comments SET task_id = (?1) WHERE (id =(?2))", nativeQuery = true)
	Integer updateTaskId(Integer taskId, Integer id);

}
