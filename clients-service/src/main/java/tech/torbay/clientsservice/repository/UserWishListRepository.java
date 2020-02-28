package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.UserWishList;

@Repository
public interface UserWishListRepository extends JpaRepository<UserWishList, Integer> {

    List<UserWishList> findAll();
    
    List<UserWishList> findByFavouriteOrgIdAndFavouriteUserType(Integer favouriteOrgId, Integer favouriteUserType);
    
    List<UserWishList> findByWisherOrgIdAndWisherUserType(Integer wisherOrgId, Integer wisherUserType);
    
    List<UserWishList> findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(Integer wisherOrgId, Integer wisherUserType, Integer favouriteOrgId, Integer favouriteUserType);

    UserWishList save(UserWishList vendorUser);

}
