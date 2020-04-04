package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.UserLevelNotification;

@Repository
public interface UserLevelNotificationRepository extends JpaRepository<UserLevelNotification, Integer> {

    List<UserLevelNotification> findAll();

    UserLevelNotification save(UserLevelNotification internalMessage);
    
    UserLevelNotification findOneById(Integer id);
    
	
}
