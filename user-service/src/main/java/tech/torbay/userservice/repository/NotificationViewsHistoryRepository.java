package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.NotificationViewsHistory;

@Repository
public interface NotificationViewsHistoryRepository extends JpaRepository<NotificationViewsHistory, Integer> {

    List<NotificationViewsHistory> findAll();
    
    NotificationViewsHistory findOneById(Integer id);
    
    List<NotificationViewsHistory> findByOrganisationIdAndUserIdAndUserTypeAndNotificationId(Integer organisationId, Integer userId, Integer userType, Integer notificationId);
    
    List<NotificationViewsHistory> findByOrganisationIdAndUserIdAndUserType(Integer organisationId, Integer userId, Integer userType);
    
    List<NotificationViewsHistory> findByUserIdAndUserTypeAndNotificationId(Integer userId, Integer userType, Integer notificationId);

}
