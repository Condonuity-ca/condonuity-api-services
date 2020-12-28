package tech.torbay.userservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ExternalMessage;

@Repository
public interface ExternalMessageRepository extends JpaRepository<ExternalMessage, Integer> {

    List<ExternalMessage> findAll();

    ExternalMessage save(ExternalMessage internalMessage);
    
    ExternalMessage findOneById(Integer id);
    
    List<ExternalMessage> findAllBySourceOrganisationIdAndSourceUserType(Integer sourceOrganisationId, Integer sourceUserType);

    @Query(value="SELECT DISTINCT em.* " + 
    		"FROM external_message em " + 
    		"INNER JOIN external_message_organisations emo " + 
    		"ON " + 
    		"(em.id = emo.external_message_id) " + 
    		"INNER JOIN client_organisation co " + 
    		"ON ( " + 
    		"(em.source_organisation_id = co.client_organisation_id and em.source_user_type = 1) " + 
    		"OR " + 
    		"(emo.target_organisation_id = co.client_organisation_id and emo.target_user_type = 1)" + 
    		") " + 
    		"LEFT JOIN client_user cu " + 
    		"ON ( " + 
    		"(em.source_user_id = cu.client_id and em.source_user_type = 1) " + 
    		") " + 
    		"LEFT JOIN vendor_user vu " + 
    		"ON ( " + 
    		"(em.source_user_id = vu.user_id and em.source_user_type = 2) " + 
    		") " + 
    		"LEFT JOIN external_message_comment emc ON ( em.id = emc.thread_id) " +     		
    		"LEFT JOIN vendor_organisation vo " + 
    		"ON ( " + 
    		"(em.source_organisation_id = vo.vendor_organisation_id and em.source_user_type = 2) " + 
    		"OR " + 
    		"(emo.target_organisation_id = vo.vendor_organisation_id and emo.target_user_type = 2)" + 
    		") " + 
    		"WHERE ( " + 
    		"(em.source_organisation_id = (?1) and em.source_user_type = (?2) ) " + 
    		"OR " + 
    		"(emo.target_organisation_id = (?1) and emo.target_user_type = (?2) ) " + 
    		")" + 
    		"and " + 
    		"(" + 
    		"em.thread_description like (?3) or " + 
    		"em.thread_subject like (?3) or " + 
    		"em.created_at like (?3) or " + 
    		"em.modified_date like (?3) or " + 
    		"co.organisation_name like (?3) or " + 
    		"co.management_company like (?3) or " + 
    		"co.corporate_number like (?3) or " + 
    		"co.registration_date like (?3) or " + 
    		"co.address like (?3) or " + 
    		"co.city like (?3) or " + 
    		"co.province like (?3) or " + 
    		"vo.legal_name like (?3) or " + 
    		"vo.company_name like (?3) or " + 
    		"vo.address like (?3) or " + 
    		"vo.city like (?3) or " + 
    		"vo.province like (?3) or " + 
    		"vo.logo_name like (?3) or " + 
    		"vo.expertise_category like (?3) or " + 
    		"vo.website like (?3) or " + 
    		"cu.first_name like (?3) or " +
    		"cu.last_name like (?3) or " +
    		"cu.legal_name like (?3) or " +
    		"vu.first_name like (?3) or " +
    		"vu.last_name like (?3) or " +
    		"vu.email like (?3) or " +
    		"emc.comment like (?3) or " +
    		"emc.created_at like (?3) or " +
    		"emc.modified_date like (?3)" +
    		")", nativeQuery =true)
	List<ExternalMessage> findAllByOrganisationIdAndUserTypeAndKeyword(
			Integer clientOrganisationId,
			int userType, 
			String keyword);

	@Query(value="SELECT DISTINCT em.* " + 
    		"FROM external_message em " + 
    		"INNER JOIN external_message_organisations emo " + 
    		"ON " + 
    		"(em.id = emo.external_message_id) " + 
    		"INNER JOIN client_organisation co " + 
    		"ON ( " + 
    		"(em.source_organisation_id = co.client_organisation_id and em.source_user_type = 1) " + 
    		"OR " + 
    		"(emo.target_organisation_id = co.client_organisation_id and emo.target_user_type = 1)" + 
    		") " + 
    		"LEFT JOIN client_user cu " + 
    		"ON ( " + 
    		"(em.source_user_id = cu.client_id and em.source_user_type = 1) " + 
    		") " + 
    		"LEFT JOIN vendor_user vu " + 
    		"ON ( " + 
    		"(em.source_user_id = vu.user_id and em.source_user_type = 2) " + 
    		") " + 
    		"LEFT JOIN external_message_comment emc ON ( em.id = emc.thread_id) " +     		
    		"LEFT JOIN vendor_organisation vo " + 
    		"ON ( " + 
    		"(em.source_organisation_id = vo.vendor_organisation_id and em.source_user_type = 2) " + 
    		"OR " + 
    		"(emo.target_organisation_id = vo.vendor_organisation_id and emo.target_user_type = 2)" + 
    		") " + 
    		"WHERE ( " + 
    		"(em.source_organisation_id = (?1) and em.source_user_type = (?2) ) " + 
    		"OR " + 
    		"(emo.target_organisation_id = (?1) and emo.target_user_type = (?2) ) " + 
    		") " + 
    		"and " + 
    		"(" + 
    		"em.thread_description like (?3) or " + 
    		"em.thread_subject like (?3) or " + 
    		"em.created_at like (?3) or " + 
    		"em.modified_date like (?3) or " + 
    		"co.organisation_name like (?3) or " + 
    		"co.management_company like (?3) or " + 
    		"co.corporate_number like (?3) or " + 
    		"co.registration_date like (?3) or " + 
    		"co.address like (?3) or " + 
    		"co.city like (?3) or " + 
    		"co.province like (?3) or " + 
    		"vo.legal_name like (?3) or " + 
    		"vo.company_name like (?3) or " + 
    		"vo.address like (?3) or " + 
    		"vo.city like (?3) or " + 
    		"vo.province like (?3) or " + 
    		"vo.logo_name like (?3) or " + 
    		"vo.expertise_category like (?3) or " + 
    		"vo.website like (?3) or " + 
    		"cu.first_name like (?3) or " +
    		"cu.last_name like (?3) or " +
    		"cu.legal_name like (?3) or " +
    		"vu.first_name like (?3) or " +
    		"vu.last_name like (?3) or " +
    		"vu.email like (?3) or " +
    		"emc.comment like (?3) or " +
    		"emc.created_at like (?3) or " +
    		"emc.modified_date like (?3)" +
    		")", nativeQuery =true)
	List<ExternalMessage> findAllByOrganisationIdAndUserTypeAndKeywordForVendor(
			Integer vendorOrganisationId,
			int userType, 
			String keyword);

	@Modifying
	@Transactional
	@Query(value="update external_message set delete_status = (?1) where id = (?2);", nativeQuery = true)
	int setDeleteStatusById(Integer activeStatus, Integer externalMessageId);

	 @Query(value="SELECT DISTINCT em.* " + 
	    		"FROM external_message em " + 
	    		"INNER JOIN external_message_organisations emo " + 
	    		"ON " + 
	    		"(em.id = emo.external_message_id) " + 
	    		"INNER JOIN client_organisation co " + 
	    		"ON ( " + 
	    		"(em.source_organisation_id = co.client_organisation_id and em.source_user_type = 1) " + 
	    		"OR " + 
	    		"(emo.target_organisation_id = co.client_organisation_id and emo.target_user_type = 1)" + 
	    		") " + 
	    		"LEFT JOIN client_user cu " + 
	    		"ON ( " + 
	    		"(em.source_user_id = cu.client_id and em.source_user_type = 1) " + 
	    		") " + 
	    		"LEFT JOIN vendor_user vu " + 
	    		"ON ( " + 
	    		"(em.source_user_id = vu.user_id and em.source_user_type = 2) " + 
	    		") " + 
	    		"LEFT JOIN external_message_comment emc ON ( em.id = emc.thread_id) " +     		
	    		"LEFT JOIN vendor_organisation vo " + 
	    		"ON ( " + 
	    		"(em.source_organisation_id = vo.vendor_organisation_id and em.source_user_type = 2) " + 
	    		"OR " + 
	    		"(emo.target_organisation_id = vo.vendor_organisation_id and emo.target_user_type = 2)" + 
	    		") " + 
	    		"WHERE " + 
	    		"concat (" + 
	    		"em.thread_description like (?1) or " + 
	    		"em.thread_subject like (?1) or " + 
	    		"em.created_at like (?1) or " + 
	    		"em.modified_date like (?1) or " + 
	    		"co.organisation_name like (?1) or " + 
	    		"co.management_company like (?1) or " + 
	    		"co.corporate_number like (?1) or " + 
	    		"co.registration_date like (?1) or " + 
	    		"co.address like (?1) or " + 
	    		"co.city like (?1) or " + 
	    		"co.province like (?1) or " + 
	    		"vo.legal_name like (?1) or " + 
	    		"vo.company_name like (?1) or " + 
	    		"vo.address like (?1) or " + 
	    		"vo.city like (?1) or " + 
	    		"vo.province like (?1) or " + 
	    		"vo.logo_name like (?1) or " + 
	    		"vo.expertise_category like (?1) or " + 
	    		"vo.website like (?1) or " + 
	    		"cu.first_name like (?1) or " +
	    		"cu.last_name like (?1) or " +
	    		"cu.legal_name like (?1) or " +
	    		"vu.first_name like (?1) or " +
	    		"vu.last_name like (?1) or " +
	    		"vu.email like (?1) or " +
	    		"emc.comment like (?1) or " +
	    		"emc.created_at like (?1) or " +
	    		"emc.modified_date like (?1)" +
	    		")", nativeQuery =true)
		List<ExternalMessage> findAllByKeyword(String keyword);
}
