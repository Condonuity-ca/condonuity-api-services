package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.InternalMessage;

import java.util.List;

@Repository
public interface InternalMessageRepository extends JpaRepository<InternalMessage, Integer> {

    List<InternalMessage> findAll();

    InternalMessage save(InternalMessage internalMessage);
    
    InternalMessage findOneById(Integer id);
    
    List<InternalMessage> findAllByOrganisationIdAndUserType(Integer organisationId, Integer userType);

//    @Query(value="SELECT im.*" + 
//    		"FROM condonuitydev.internal_message im " + 
//    		"INNER JOIN condonuitydev.internal_message_comment imc " + 
//    		"ON ( im.id = imc.thread_id)" + 
//    		"where ( im.organisation_id = (?1) and im.user_type = (?2) ) and " + 
//    		"concat (im.thread_subject, '.', im.thread_description, '.', im.created_at, " + 
//    		"'.', im.modified_date, '.', imc.comment, '.', imc.created_at, '.', imc.modified_date ) " + 
//    		"LIKE (?3)", nativeQuery =true)
//	List<InternalMessage> findAllByOrganisationIdAndUserTypeAndKeyword(
//			Integer clientOrganisationId, 
//			int value,
//			String keyword);
    @Query(value="SELECT DISTINCT im.*" + 
    		"FROM condonuitydev.internal_message im " + 
    		"INNER JOIN condonuitydev.client_user cu ON (im.user_id = cu.client_id) " + 
    		"LEFT JOIN condonuitydev.internal_message_comment imc ON ( im.id = imc.thread_id) " + 
    		"where ( im.organisation_id = (?1) and im.user_type = (?2) ) and " + 
    		"(imc.comment like (?3) or " + 
    		"imc.created_at like (?3) or " + 
    		"imc.modified_date like (?3) or " + 
    		"im.thread_subject like (?3) or " + 
    		"im.thread_description like (?3) or " + 
    		"im.created_at like (?3) or " + 
    		"cu.first_name like (?3) or " + 
    		"cu.last_name like (?3) or " + 
    		"cu.legal_name like (?3))", nativeQuery =true)
	List<InternalMessage> findAllByOrganisationIdAndUserTypeAndKeyword(
			Integer clientOrganisationId, 
			int userType,
			String keyword);
    
    @Query(value="SELECT DISTINCT im.*" + 
    		"FROM condonuitydev.internal_message im " + 
    		"INNER JOIN condonuitydev.vendor_user vu ON (im.user_id = vu.user_id) " + 
    		"LEFT JOIN condonuitydev.internal_message_comment imc ON ( im.id = imc.thread_id) " + 
    		"where ( im.organisation_id = (?1) and im.user_type = (?2) ) and " + 
    		"(imc.comment like (?3) or " + 
    		"imc.created_at like (?3) or " + 
    		"imc.modified_date like (?3) or " + 
    		"im.thread_subject like (?3) or " + 
    		"im.thread_description like (?3) or " + 
    		"im.created_at like (?3) or " + 
    		"vu.first_name like (?3) or " + 
    		"vu.last_name like (?3) or " + 
    		"vu.email like (?3))", nativeQuery =true)
	List<InternalMessage> findAllByOrganisationIdAndUserTypeAndKeywordForVendor(
			Integer vendorOrganisationId, 
			int userType,
			String keyword);
	
}
