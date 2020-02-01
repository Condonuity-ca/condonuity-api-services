package tech.torbay.fileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.fileservice.entity.UserProfileImages;

@Repository
public interface UserProfileImagesRepository extends JpaRepository<UserProfileImages, Integer> {

    List<UserProfileImages> findAll();
    
    UserProfileImages findOneById(Integer Id);

	UserProfileImages findByUserIdAndUserType(Integer userId, Integer userType);

	UserProfileImages findByBlobName(String blobName);
	
}
