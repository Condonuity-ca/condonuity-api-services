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

    @Query(value = "SELECT * FROM user_level_notification where " + 
    		"( notification_category_type = 12 or " + 
    		"notification_category_type = 13 or " + 
    		"notification_category_type = 14) and to_organisation_id = (?1) and to_user_type = 1;", nativeQuery = true)
	List<UserLevelNotification> findAllInternalMessagesNotifications(Integer clientOrganisationId);

    @Query(value = "SELECT * FROM user_level_notification "+
    		"INNER JOIN external_message_organisations emo "+
    		"ON ( emo.external_message_id = notification_category_id and ( ( emo.target_user_type = 1 and emo.target_organisation_id = (?1)) " +
    		"or (from_organisation_id = (?1)  and from_user_type = 1)) )" + 
    		"where " + 
    		"( notification_category_type = 15 or " + 
    		"notification_category_type = 16 or " + 
    		"notification_category_type = 17);", nativeQuery = true)
	List<UserLevelNotification> findAllExternalMessagesNotifications(Integer clientOrganisationId);
    
    @Query(value = "SELECT uln.* FROM user_level_notification uln " + 
    		"  WHERE ( uln.notification_category_type = 17 and uln.notification_category_id IN (?1))", nativeQuery = true)
	List<UserLevelNotification> findAllExternalMessageCommentsNotifications(List<Integer> externalMessageIds);
    
    @Query(value = "SELECT * FROM user_level_notification where " + 
    		"( notification_category_type = 9 or " + 
    		"notification_category_type = 10 or " +
    		"notification_category_type = 11 ) and to_organisation_id = (?1) and to_user_type = 1" 
    		, nativeQuery = true)
	List<UserLevelNotification> findAllTaskNotifications(Integer clientOrganisationId, Integer clientId);
    
}
