package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.UserLevelNotification;

@Repository
public interface UserLevelNotificationRepository extends JpaRepository<UserLevelNotification, Integer> {

    List<UserLevelNotification> findAll();

    UserLevelNotification save(UserLevelNotification internalMessage);
    
    UserLevelNotification findOneById(Integer id);

    @Query(value = "SELECT * FROM condonuitydev.user_level_notification where " + 
    		"( notification_category_type = 12 or " + 
    		"notification_category_type = 13 or " + 
    		"notification_category_type = 14 or " + 
    		"notification_category_type = 15 or " + 
    		"notification_category_type = 16 or " + 
    		"notification_category_type = 17) and to_organisation_id = (?1) and to_user_type = 1;", nativeQuery = true)
	List<UserLevelNotification> findAllMessagesNotifications(Integer clientOrganisationId);

    @Query(value = "SELECT * FROM condonuitydev.user_level_notification where " + 
    		"( notification_category_type = 9 or " + 
    		"notification_category_type = 10 or " +
    		"notification_category_type = 11 ) and to_organisation_id = (?1) and ( to_user_id = (?2) or to_user_id = 0 ) and to_user_type = 1" 
    		, nativeQuery = true)
	List<UserLevelNotification> findAllTaskNotifications(Integer clientOrganisationId, Integer clientId);
    
}
