package tech.torbay.userservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ExternalMessageComment;

@Repository
public interface ExternalMessageCommentRepository extends JpaRepository<ExternalMessageComment, Integer> {

    List<ExternalMessageComment> findAll();

    ExternalMessageComment save(ExternalMessageComment externalMessageComment);
    
    ExternalMessageComment findOneById(Integer id);

	List<ExternalMessageComment> findAllByThreadId(Integer threadId);

	@Modifying
	@Transactional
	@Query(value="update condonuitydev.external_message_comment set delete_status = (?1) where id = (?2);", nativeQuery = true)
	int setDeleteStatusById(Integer activeStatus, Integer externalMessageCommentId);
	
	 @Query(value = "SELECT emc.* FROM condonuitydev.external_message_comment emc " + 
	    		"  WHERE ( emc.thread_id IN (?1))", nativeQuery = true)
	List<ExternalMessageComment> findAllByThreadId(List<Integer> externalThreadIds);
}
