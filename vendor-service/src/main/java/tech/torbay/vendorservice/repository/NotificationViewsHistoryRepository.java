package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.NotificationViewsHistory;

@Repository
public interface NotificationViewsHistoryRepository extends JpaRepository<NotificationViewsHistory, Integer> {

    List<NotificationViewsHistory> findAll();
    
    NotificationViewsHistory findOneById(Integer id);

}
