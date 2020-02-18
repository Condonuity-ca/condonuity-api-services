package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.InternalMessageComment;

@Repository
public interface InternalMessageCommentRepository extends JpaRepository<InternalMessageComment, Integer> {

    List<InternalMessageComment> findAll();

    InternalMessageComment save(InternalMessageComment internalMessageComment);
    
    InternalMessageComment findOneById(Integer id);

	List<InternalMessageComment> findAllByThreadId(Integer threadId);
	
}
