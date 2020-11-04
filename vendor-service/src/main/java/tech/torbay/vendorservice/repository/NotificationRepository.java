package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAll();
    
    Notification findOneById(Integer id);

    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.bids bd on " + 
    		" ( bd.project_id = nt.notification_category_id and bd.vendor_org_id = (?1) and " + 
    		"( notification_category_type = 1 or notification_category_type = 2 or notification_category_type = 3 or notification_category_type = 6) )", nativeQuery = true)
	List<Notification> findAllProjectBidsNotifications(Integer vendorOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.bids bd on " + 
    		" ( bd.project_id = nt.notification_category_id and bd.vendor_org_id = (?1) and " + 
    		" notification_category_type = 21 )", nativeQuery = true)
	List<Notification> findAllProjectCancelledNotifications(Integer vendorOrganisationId);

    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.project_awards pa on " + 
    		" ( pa.id = nt.notification_category_id and pa.vendor_organisation_id = (?1) and " + 
    		"( notification_category_type = 6) )", nativeQuery = true)
	List<Notification> findAllProjectAwardsNotifications(Integer vendorOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.vendor_project_interests vpi on " + 
    		" ( vpi.project_id = nt.notification_category_id and vpi.vendor_organisation_id = (?1) ) and " +
    		" ( notification_category_type = 1 or notification_category_type = 2 or notification_category_type = 3 " +
    		" )", nativeQuery = true)
	List<Notification> findAllProjectInterestNotifications(Integer vendorOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.vendor_project_interests vpi on " + 
    		" ( vpi.vendor_organisation_id = (?1) )" +
    		" inner join " + 
    		" condonuitydev.bids bd on " + 
    		" ( bd.project_id = vpi.project_id) " +
//    		" where (nt.organisation_id != (?1) "+ // it will fetch only non-self-vendor-bids , but now all bids info will be fetched
    		" where ( " +
    		" (bd.id = nt.notification_category_id ) and " +
    		" ( notification_category_type = 4 or notification_category_type = 5 or notification_category_type = 6 ) " + 
    		")", nativeQuery = true)
	List<Notification> findAllBidsForVendorInterestedProjectsNotifications(Integer vendorOrganisationId);

    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.project_reviews_ratings prr on " + 
    		" ( prr.id = nt.notification_category_id and prr.vendor_organisation_id = (?1) and " + 
    		" notification_category_type = 7 )", nativeQuery = true)
	List<Notification> findAllReviewRatingNotifications(Integer vendorOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		"where (" + 
    		"( nt.organisation_id = (?1)) and " + 
    		"( nt.user_type = 2 ) and " + 
    		"( notification_category_type = 22 " + 
    		"or notification_category_type = 23 " +
    		"or notification_category_type = 24 " +
    		"or notification_category_type = 25 ) " + 
    		")", nativeQuery = true)
	List<Notification> findAllAccountChangesNotifications(Integer vendorOrganisationId);

    @Query(value ="select nt.* from condonuitydev.notification nt " + 
    		" inner join " + 
    		" condonuitydev.bids bd on " + 
    		" ( bd.project_id = nt.notification_category_id and bd.vendor_org_id = (?1) )" + 
    		"where " + 
    		"( notification_category_type = 26 " + 
    		"or notification_category_type = 27 " + 
    		")", nativeQuery = true)
    List<Notification> findBidEndAlertForProjectBids(Integer vendorOrganisationId);
    
    
    @Query(value ="select nt.* from condonuitydev.notification nt " +
    		"inner join " + 
    		"condonuitydev.bids bd on ( bd.vendor_org_id = (?1) )" + 
    		"inner join " + 
    		"condonuitydev.projects pro " + 
    		" on ( pro.project_id = bd.project_id ) " +
    		"inner join " + 
    		"condonuitydev.bids bid_other on" + 
    		" ( bid_other.project_id = pro.project_id ) " +
    		"where (" + 
    		"( bid_other.id = nt.notification_category_id and nt.organisation_id != (?1) ) and " + 
    		"( notification_category_type = 4 or notification_category_type = 5 or notification_category_type = 6) )", 
    		nativeQuery = true)
	List<Notification> findAllOtherCompetitorBidsForVendorBiddedProjectNotifications(Integer vendorOrganisationId);
    
    @Query(value ="select nt.* from condonuitydev.notification nt " +
    		"inner join " + 
    		"condonuitydev.bids bd on ( bd.vendor_org_id = (?1) )" + 
    		"inner join " + 
    		"condonuitydev.projects pro " + 
    		" on ( pro.project_id = bd.project_id ) " +
    		"inner join " + 
    		"condonuitydev.project_questions pq on" + 
    		" ( pq.project_id = pro.project_id ) " +
    		"where (" + 
    		"( pq.projectqa_id = nt.notification_category_id ) and " + 
    		"( notification_category_type = 31 or notification_category_type = 32) )", 
    		nativeQuery = true)
	List<Notification> findAllOnlyBiddedProjectsQANotifications(Integer vendorOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.vendor_project_interests vpi on " + 
    		" ( vpi.vendor_organisation_id = (?1) )" +
    		" inner join " + 
    		" condonuitydev.project_questions pq on" + 
    		" ( pq.project_id = vpi.project_id ) " +
//    		" where (nt.organisation_id != (?1) "+ // it will fetch only non-self-vendor-bids , but now all bids info will be fetched
    		" where ( " +
    		" (pq.projectqa_id = nt.notification_category_id ) and " +
    		" ( notification_category_type = 31 or notification_category_type = 32 ) " + 
    		")", nativeQuery = true)
	List<Notification> findAllOnlyInterestedProjectsQANotifications(Integer vendorOrganisationId);
}
