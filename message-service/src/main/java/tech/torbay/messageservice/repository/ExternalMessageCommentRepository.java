package tech.torbay.messageservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.messageservice.entity.ExternalMessageComment;

@Repository
public interface ExternalMessageCommentRepository extends JpaRepository<ExternalMessageComment, Integer> {

    List<ExternalMessageComment> findAll();

    ExternalMessageComment save(ExternalMessageComment externalMessageComment);
    
    ExternalMessageComment findOneById(Integer id);

	List<ExternalMessageComment> findAllByThreadId(Integer threadId);
	
}