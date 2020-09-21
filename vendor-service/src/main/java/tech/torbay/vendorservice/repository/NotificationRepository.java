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
    		" condonuitydev.project_awards pa on " + 
    		" ( pa.id = nt.notification_category_id and pa.vendor_organisation_id = (?1) and " + 
    		"( notification_category_type = 6) )", nativeQuery = true)
	List<Notification> findAllProjectAwardsNotifications(Integer vendorOrganisationId);
    
    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.vendor_project_interests vpi on " + 
    		" ( vpi.project_id = nt.notification_category_id and vpi.vendor_organisation_id = (?1) and " + 
    		"( notification_category_type = 1 or notification_category_type = 2 or notification_category_type = 3 ) )", nativeQuery = true)
	List<Notification> findAllProjectInterestNotifications(Integer vendorOrganisationId);

    @Query(value =" select nt.* from condonuitydev.notification nt" + 
    		" inner join " + 
    		" condonuitydev.project_reviews_ratings prr on " + 
    		" ( prr.id = nt.notification_category_id and prr.vendor_organisation_id = (?1) and " + 
    		" notification_category_type = 7 )", nativeQuery = true)
	List<Notification> findAllReviewRatingNotifications(Integer vendorOrganisationId);

}
