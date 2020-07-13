package tech.torbay.securityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.securityservice.entity.UserProfileImages;

@Repository
public interface UserProfileImagesRepository extends JpaRepository<UserProfileImages, Integer> {

    List<UserProfileImages> findAll();
    
    UserProfileImages findOneById(Integer Id);

	UserProfileImages findByUserIdAndUserType(Integer userId, Integer userType);
	
}
