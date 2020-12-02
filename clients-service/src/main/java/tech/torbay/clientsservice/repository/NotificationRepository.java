package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAll();
    
    Notification findOneById(Integer id);

//    @Query(value =" select nt.* from condonuitydev.notification nt" + 
//    		" inner join " + 
//    		" condonuitydev.bids bd on " + 
//    		" ( bd.project_id = nt.notification_category_id and bd.vendor_org_id = (?1) and " + 
//    		"( notification_category_type = 1 or notification_category_type = 2 or notification_category_type = 3) )", nativeQuery = true)
//	List<Notification> findAllProjectBidsNotifications(Integer vendorOrganisationId);

//    @Query(value =" select nt.* from condonuitydev.notification nt" + 
//    		" inner join " + 
//    		" condonuitydev.vendor_project_interests vpi on " + 
//    		" ( vpi.project_id = nt.notification_category_id and vpi.vendor_organisation_id = (?1) and " + 
//    		"( notification_category_type = 1 or notification_category_type = 2 or notification_category_type = 3 ) )", nativeQuery = true)
//	List<Notification> findAllProjectInterestNotifications(Integer vendorOrganisationId);

    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"inner join " + 
    		"condonuitydev.projects pro  on ( pro.client_organisation_id = (?1) ) " + 
    		"inner join " + 
    		"condonuitydev.bids bd on ( pro.project_id = bd.project_id ) " + 
    		"where (" + 
    		"( bd.id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 4 " + 
    		"or notification_category_type = 5 " + 
    		"or notification_category_type = 6 " + 
    		"or notification_category_type = 34 ) " + 
    		")", nativeQuery = true)
    List<Notification> findAllProjectBidsNotifications(Integer clientOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt " + 
    		"inner join condonuitydev.projects pro  on pro.client_organisation_id = (?1) " + 
    		"inner join condonuitydev.project_awards pa on nt.notification_category_id = pa.id " + 
    		"where ( pa.project_id = pro.project_id and ( notification_category_type = 6) )", nativeQuery = true)
	List<Notification> findAllProjectAwardsNotifications(Integer clientOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"inner join " + 
    		"condonuitydev.projects pro  on ( pro.client_organisation_id = (?1) ) " + 
    		"where (" + 
    		"( pro.project_id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 1 " + 
    		"or notification_category_type = 2 " + 
    		"or notification_category_type = 3 " + 
    		"or notification_category_type = 33 ) " + 
    		")", nativeQuery = true)
    List<Notification> findAllPostedProjectsForClient(Integer clientOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"inner join " + 
    		"condonuitydev.projects pro  on ( pro.client_organisation_id = (?1) ) " + 
    		"where (" + 
    		"( pro.project_id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 26 " + 
    		"or notification_category_type = 27 ) " + 
    		")", nativeQuery = true)
    List<Notification> findBidEndAlertForProjects(Integer clientOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"inner join " + 
    		"condonuitydev.client_contract cc  on ( cc.client_organisation_id = (?1) ) " + 
    		"where (" + 
    		"( cc.id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 29 " + 
    		"or notification_category_type = 30 ) " + 
    		")", nativeQuery = true)
    List<Notification> findAllContractExpiryAlert(Integer clientOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"where (" + 
    		"( nt.organisation_id = (?1)) and " + 
    		"( nt.user_type = 1 ) and " + 
    		"( notification_category_type = 18 " + 
    		"or notification_category_type = 19 " +
    		"or notification_category_type = 20 " +
    		"or notification_category_type = 21 ) " + 
    		")", nativeQuery = true)
    List<Notification> findAllAccountChangesNotifications(Integer clientOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"inner join " + 
    		"condonuitydev.project_reviews_ratings prr  on ( prr.client_organisation_id = (?1) ) " + 
    		"where (" + 
    		"( prr.id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 8 )" + 
    		")", nativeQuery = true)
	List<Notification> findAllReviewRepliesNotificationsFromVendors(Integer clientOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.projects pro on " + 
    		" ( pro.client_organisation_id = (?1) )" +
    		" inner join " + 
    		" condonuitydev.project_questions pq on" + 
    		" ( pq.project_id = pro.project_id ) " +
    		" where ( " +
    		" (pq.projectqa_id = nt.notification_category_id ) and " +
    		" ( notification_category_type = 31 or notification_category_type = 32 ) " + 
    		")", nativeQuery = true)
	List<Notification> findAllProjectQuestionsAlertNotifications(Integer clientOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"inner join " + 
    		"condonuitydev.projects pro  on ( pro.client_organisation_id = (?1) ) " + 
    		"inner join " + 
    		"condonuitydev.bids bd on ( pro.project_id = bd.project_id ) " + 
    		"where (" + 
    		"( bd.id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 51 ) " + 
    		")", nativeQuery = true)
    List<Notification> findBidWithdrawnAlertNotifications(Integer clientOrganisationId);

    @Query(value =" select nt.* from condonuitydev.notification nt " + 
    		"where ( nt.user_type = 1 and nt.user_id = (?1) and ( notification_category_type = 36 or "
    		+ "notification_category_type = 37 or "
    		+ "notification_category_type = 38 or "
    		+ "notification_category_type = 39 or "
    		+ "notification_category_type = 40 ) )", nativeQuery = true)
	List<Notification> getUserProfileNotifications(Integer clientId);
}
