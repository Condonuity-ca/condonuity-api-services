package tech.torbay.userservice.messages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.messages.entity.ClientInternalMessageComment;

@Repository
public interface ClientInternalMessageCommentRepository extends JpaRepository<ClientInternalMessageComment, Integer> {

    List<ClientInternalMessageComment> findAll();

    ClientInternalMessageComment save(ClientInternalMessageComment user);
    
    ClientInternalMessageComment findOneById(Integer id);

	List<ClientInternalMessageComment> findAllByThreadId(Integer threadId);
	
}
