package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.ExternalMessageComment;

@Repository
public interface ExternalMessageCommentRepository extends JpaRepository<ExternalMessageComment, Integer> {

    List<ExternalMessageComment> findAll();

    ExternalMessageComment save(ExternalMessageComment externalMessageComment);
    
    ExternalMessageComment findOneById(Integer id);

	List<ExternalMessageComment> findAllByThreadId(Integer threadId);

	 @Query(value = "SELECT emc.* FROM external_message_comment emc " + 
	    		"  WHERE ( emc.thread_id IN (?1))", nativeQuery = true)
	List<ExternalMessageComment> findAllByThreadId(List<Integer> externalThreadIds);
	
}
