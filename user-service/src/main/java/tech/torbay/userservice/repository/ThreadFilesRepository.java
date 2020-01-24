package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ThreadFiles;

@Repository
public interface ThreadFilesRepository extends JpaRepository<ThreadFiles, Integer> {

    List<ThreadFiles> findAll();
    
    ThreadFiles findOneById(Integer Id);

	List<ThreadFiles> findAllByThreadId(Integer threadId);
	
}
