package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.CommentFiles;
import tech.torbay.userservice.entity.SupportUserLogs;

@Repository
public interface SupportUserLogsRepository extends JpaRepository<SupportUserLogs, Integer> {

    List<SupportUserLogs> findAll();
    
    SupportUserLogs findOneById(Integer Id);

	List<SupportUserLogs> findAllBySupportUserId(Integer supportUserId);
	
}
