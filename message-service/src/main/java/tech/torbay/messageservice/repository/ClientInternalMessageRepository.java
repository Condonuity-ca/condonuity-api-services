package tech.torbay.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.messageservice.entity.ClientInternalMessage;

import java.util.List;

@Repository
public interface ClientInternalMessageRepository extends JpaRepository<ClientInternalMessage, Integer> {

    List<ClientInternalMessage> findAll();

    ClientInternalMessage save(ClientInternalMessage user);
    
    ClientInternalMessage findOneById(Integer id);
    
    List<ClientInternalMessage> findAllByClientOrganisationId(Integer id);
	
}
