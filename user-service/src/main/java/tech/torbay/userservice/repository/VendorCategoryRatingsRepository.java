package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

@Repository
public interface VendorCategoryRatingsRepository extends JpaRepository<VendorCategoryRatings, Integer> {

    List<VendorCategoryRatings> findAll();
    
    List<VendorCategoryRatings> findByVendorOrganisationId(Integer vendorId);
    
    VendorCategoryRatings findOneById(Integer id);

    VendorCategoryRatings save(VendorCategoryRatings vendorCategoryRatings);

	List<VendorCategoryRatings> findByProjectId(Integer projectId);

	VendorCategoryRatings findByClientIdAndProjectIdAndRatingCategory(Integer clientId, Integer projectId, Integer ratingCategory);
	
	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientId(Integer vendorId, Integer clientId);
	
	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientIdAndProjectId(Integer vendorId, Integer clientId, Integer projectId);
}
