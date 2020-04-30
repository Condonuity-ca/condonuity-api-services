package tech.torbay.clientsservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.clientsservice.entity.VendorCategoryRatings;

@Repository
public interface VendorCategoryRatingsRepository extends JpaRepository<VendorCategoryRatings, Integer> {

    List<VendorCategoryRatings> findAll();
    
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

	VendorCategoryRatings findByReviewRatingIdAndRatingCategory(Integer reviewId, Integer ratingCategory);

}
