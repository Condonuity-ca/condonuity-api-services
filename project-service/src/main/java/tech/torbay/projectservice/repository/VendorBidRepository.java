package tech.torbay.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.VendorBid;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorBidRepository extends JpaRepository<VendorBid, Integer> {

    List<VendorBid> findAll();

	VendorBid findOneById (Integer vendorBidId);

	@Query(value="SELECT b.* FROM bids b WHERE project_id = (?1) AND ( bid_status = 2 or bid_status = 3)", nativeQuery = true)
	List<VendorBid> findVendorBidByProjectId(Integer id);
	
	@Query(value="SELECT COUNT(b.project_id) FROM bids b WHERE project_id = (?1) AND ( bid_status = 2 or bid_status = 3)", nativeQuery = true)
	Integer getProjectBidsCount(Integer projectId);
	
	@Query(value="SELECT b.* FROM bids b WHERE project_id = (?1) AND ( bid_status = 2 or bid_status = 3)", nativeQuery = true)
	List<VendorBid> getProjectLiveBids(Integer projectId);

	@Query(name="VendorBid.CurrentProject")
	List<Object[]> findCurrentProjectsByVendorOrgId(Integer vendorOrganisationId);

	@Query(name="VendorBid.HistoryProject")
	List<Object[]> findHistoryProjectsByVendorOrgId(Integer vendorOrganisationId);
	
	VendorBid findVendorBidByProjectIdAndVendorOrgId(Integer projectId, Integer vendorOrgId);
	
	@Query(value="SELECT b.* FROM bids b WHERE vendor_org_id = (?1) AND ( bid_status = 1 or bid_status = 4)", nativeQuery = true)
	List<VendorBid> findSavedOrPulledVendorBidByVendorOrgId(Integer vendorOrgId);
	
	@Query(value="SELECT b.* FROM bids b WHERE project_id = (?1) AND ( bid_status = 1 or bid_status = 4)", nativeQuery = true)
	List<VendorBid> findSavedORwithdrawnVendorBidByProjectId(Integer id);
}
