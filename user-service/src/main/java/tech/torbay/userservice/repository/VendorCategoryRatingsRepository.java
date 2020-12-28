package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorUser;

import java.util.List;

@Repository
public interface VendorCategoryRatingsRepository extends JpaRepository<VendorCategoryRatings, Integer> {

    List<VendorCategoryRatings> findAll();
    
    @Query(value = "SELECT vcr.* FROM vendor_category_ratings vcr "
    		+ "INNER JOIN project_reviews_ratings prr ON prr.id = vcr.review_rating_id "
    		+ "where vcr.vendor_organisation_id = (?1) and " + 
			"prr.status = (?2)"
			, nativeQuery = true)
    List<VendorCategoryRatings> findByVendorOrganisationIdAndStatus(Integer vendorId, Integer activeStatus);
    
    List<VendorCategoryRatings> findByVendorOrganisationId(Integer vendorId);
    
    VendorCategoryRatings findOneById(Integer id);

    VendorCategoryRatings save(VendorCategoryRatings vendorCategoryRatings);

	List<VendorCategoryRatings> findByProjectId(Integer projectId);

	VendorCategoryRatings findByClientIdAndClientOrganisationIdAndProjectIdAndRatingCategory(Integer clientId, Integer clientOrganisationId, Integer projectId, Integer ratingCategory);
	
	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientIdAndClientOrganisationId(Integer vendorId, Integer clientId, Integer clientOrganisationId);
	
	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientIdAndClientOrganisationIdAndProjectId(Integer vendorId, Integer clientId, Integer clientOrganisationId, Integer projectId);

	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientOrganisationId(Integer vendorOrgId, Integer clientOrganisationId);

	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientOrganisationIdAndProjectId(Integer vendorOrgId, Integer clientOrganisationId, Integer projectId);
	
	List<VendorCategoryRatings> findAllByReviewRatingId(Integer reviewRatingId);
	
	VendorCategoryRatings findByReviewRatingIdAndRatingCategory(Integer reviewRatingId, Integer reviewCategory);

}
