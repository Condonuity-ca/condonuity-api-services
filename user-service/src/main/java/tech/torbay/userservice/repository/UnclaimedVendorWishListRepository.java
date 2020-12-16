package tech.torbay.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.UnclaimedVendorWishList;

@Repository
public interface UnclaimedVendorWishListRepository extends JpaRepository<UnclaimedVendorWishList, Integer> {

    List<UnclaimedVendorWishList> findAll();
    
    List<UnclaimedVendorWishList> findByClientOrgIdAndClientUserId(Integer clientOrgId, Integer clientuserId);
    
    List<UnclaimedVendorWishList> findByVendorProfileId(Integer vendorProfileId);
    
    UnclaimedVendorWishList findByClientOrgIdAndClientUserIdAndVendorProfileId(Integer clientOrgId, Integer clientuserId, Integer vendorProfileId);
    
    UnclaimedVendorWishList findByClientOrgIdAndVendorProfileId(Integer clientOrgId, Integer vendorProfileId);

    UnclaimedVendorWishList save(UnclaimedVendorWishList unclaimedVendorWishList);

}
