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

	VendorCategoryRatings findByClientIdAndClientOrganisationIdAndProjectIdAndRatingCategory(Integer clientId, Integer clientOrganisationId, Integer projectId, Integer ratingCategory);
	
	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientIdAndClientOrganisationId(Integer vendorId, Integer clientId, Integer clientOrganisationId);
	
	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientIdAndClientOrganisationIdAndProjectId(Integer vendorId, Integer clientId, Integer clientOrganisationId, Integer projectId);

	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientOrganisationId(Integer vendorOrgId, Integer clientOrganisationId);

	List<VendorCategoryRatings> findByVendorOrganisationIdAndClientOrganisationIdAndProjectId(Integer vendorOrgId, Integer clientOrganisationId, Integer projectId);
	
	List<VendorCategoryRatings> findAllByReviewRatingId(Integer reviewRatingId);

}
