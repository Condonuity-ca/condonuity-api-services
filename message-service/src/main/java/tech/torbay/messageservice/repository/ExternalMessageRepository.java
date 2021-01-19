package tech.torbay.messageservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.messageservice.entity.ExternalMessage;
import tech.torbay.messageservice.entity.InternalMessage;

@Repository
public interface ExternalMessageRepository extends JpaRepository<ExternalMessage, Integer> {

    List<ExternalMessage> findAll();

    ExternalMessage save(ExternalMessage internalMessage);
    
    ExternalMessage findOneById(Integer id);
    
    @Query(value = "SELECT * FROM external_message em \r\n" + 
    		"left join external_message_organisations emo on (em.id = emo.external_message_id)\r\n" + 
    		"where ( ( source_organisation_id = (?1) and source_user_type = (?2) ) " + 
    		"or  ( emo.target_organisation_id = (?1) and emo.target_user_type = (?2)) ) and delete_status = (?3) ;", nativeQuery = true)
    List<ExternalMessage> findAllByOrganisationIdAndUserTypeAndDeleteStatus(Integer organisationId, Integer userType, Integer deleteStatus);

    @Query(value = "SELECT * FROM external_message em \r\n" + 
    		"left join external_message_organisations emo on (em.id = emo.external_message_id)\r\n" + 
    		"where ( ( source_organisation_id = (?1) and source_user_type = (?2) ) " + 
    		"or  ( emo.target_organisation_id = (?1) and emo.target_user_type = (?2)) ) ;", nativeQuery = true)
	List<ExternalMessage> findAllByOrganisationIdAndUserType(Integer organisationId, Integer userType);
	
}
