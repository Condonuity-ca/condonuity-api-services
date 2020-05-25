package tech.torbay.messageservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.ExternalMessageOrganisations;

@Repository
public interface ExternalMessageOrganisationsRepository extends JpaRepository<ExternalMessageOrganisations, Integer> {

    List<ExternalMessageOrganisations> findAll();

    ExternalMessageOrganisations save(ExternalMessageOrganisations internalMessage);
    
    ExternalMessageOrganisations findOneById(Integer id);
    
    List<ExternalMessageOrganisations> findAllByExternalMessageId(Integer externalMessageId);
	
}
