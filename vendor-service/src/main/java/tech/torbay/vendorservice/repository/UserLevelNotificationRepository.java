package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.UserLevelNotification;

@Repository
public interface UserLevelNotificationRepository extends JpaRepository<UserLevelNotification, Integer> {

    List<UserLevelNotification> findAll();

    UserLevelNotification save(UserLevelNotification internalMessage);
    
    UserLevelNotification findOneById(Integer id);
    
    @Query(value = "SELECT * FROM condonuitydev.user_level_notification where " + 
    		"( notification_category_type = 12 or " + 
    		"notification_category_type = 13 or " + 
    		"notification_category_type = 14) and to_organisation_id = (?1) and to_user_type = 2;", nativeQuery = true)
	List<UserLevelNotification> findAllInternalMessagesNotifications(Integer vendorOrganisationId);

    @Query(value = "SELECT * FROM condonuitydev.user_level_notification " + 
    		"INNER JOIN condonuitydev.external_message_organisations emo "+
    		"ON ( emo.external_message_id = notification_category_id and emo.target_user_type = 2 and emo.target_organisation_id = (?1)) " +
    		"where ( notification_category_type = 15 or " + 
    		"notification_category_type = 16 or " + 
    		"notification_category_type = 17) ;", nativeQuery = true)
	List<UserLevelNotification> findAllExternalMessagesNotifications(Integer vendorOrganisationId);

}
