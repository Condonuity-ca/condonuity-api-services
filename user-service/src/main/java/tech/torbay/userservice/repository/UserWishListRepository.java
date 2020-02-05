package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

@Repository
public interface UserWishListRepository extends JpaRepository<UserWishList, Integer> {

    List<UserWishList> findAll();
    
    List<UserWishList> findByFavouriteOrgIdAndFavouriteUserType(Integer favouriteOrgId, Integer favouriteUserType);
    
    List<UserWishList> findByWisherOrgIdAndWisherUserType(Integer wisherOrgId, Integer wisherUserType);
    
    List<UserWishList> findByWisherOrgIdAndWisherUserTypeAndFavouriteOrgIdAndFavouriteUserType(Integer wisherOrgId, Integer wisherUserType, Integer favouriteOrgId, Integer favouriteUserType);

    UserWishList save(UserWishList vendorUser);

}
