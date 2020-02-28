package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.VendorProjectInterests;

@Repository
public interface VendorProjectInterestsRepository extends JpaRepository<VendorProjectInterests, Integer> {

    List<VendorProjectInterests> findAll();

    VendorProjectInterests findOneById (Integer id);

	List<VendorProjectInterests> findByProjectId(Integer projectId);
	
	@Query(value="SELECT COUNT(b.project_id) FROM condonuitydev.vendor_project_interests b WHERE project_id = (?1) AND interest_status = 1", nativeQuery = true)
	Integer getProjectInterestCount(Integer projectId);

	VendorProjectInterests findByProjectIdAndVendorOrganisationId(Integer projectId, Integer vendorOrganisationId);

	List<VendorProjectInterests> findByVendorId(Integer id);

}
