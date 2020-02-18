package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.CommentFiles;

@Repository
public interface CommentFilesRepository extends JpaRepository<CommentFiles, Integer> {

    List<CommentFiles> findAll();
    
    CommentFiles findOneById(Integer Id);

	List<CommentFiles> findAllByCommentIdAndThreadType(Integer commentId, Integer threadType);
	
}
