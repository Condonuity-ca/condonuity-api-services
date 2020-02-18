package tech.torbay.messageservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.InternalMessage;

@Repository
public interface ExternalMessageRepository extends JpaRepository<ExternalMessage, Integer> {

    List<ExternalMessage> findAll();

    ExternalMessage save(ExternalMessage internalMessage);
    
    ExternalMessage findOneById(Integer id);
    
    List<ExternalMessage> findAllBySourceOrganisationIdAndSourceUserType(Integer sourceOrganisationId, Integer sourceUserType);
	
}
