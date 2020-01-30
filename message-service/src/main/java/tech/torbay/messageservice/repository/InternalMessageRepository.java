package tech.torbay.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.messageservice.entity.InternalMessage;

import java.util.List;

@Repository
public interface InternalMessageRepository extends JpaRepository<InternalMessage, Integer> {

    List<InternalMessage> findAll();

    InternalMessage save(InternalMessage internalMessage);
    
    InternalMessage findOneById(Integer id);
    
    List<InternalMessage> findAllByOrganisationIdAndUserType(Integer organisationId, Integer userType);
	
}
