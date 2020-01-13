package tech.torbay.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.VendorCategoryRatings;

import java.util.List;

@Repository
public interface VendorCategoryRatingsRepository extends JpaRepository<VendorCategoryRatings, Integer> {

    List<VendorCategoryRatings> findAll();
    
    List<VendorCategoryRatings> findByVendorOrganisationId(Integer vendorId);
    
    VendorCategoryRatings findOneById(Integer id);

    VendorCategoryRatings save(VendorCategoryRatings vendorCategoryRatings);

	List<VendorCategoryRatings> findByProjectId(Integer projectId);
}
