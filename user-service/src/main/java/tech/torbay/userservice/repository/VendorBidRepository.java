package tech.torbay.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.VendorBid;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorBidRepository extends JpaRepository<VendorBid, Integer> {

    List<VendorBid> findAll();

	VendorBid findOneById (Integer vendorBidId);

	@Query(value="SELECT b.* FROM condonuitydev.bids b WHERE project_id = (?1) AND bid_status != 1", nativeQuery = true)
	List<VendorBid> findVendorBidByProjectId(Integer id);
	
	@Query(value="SELECT COUNT(b.project_id) FROM condonuitydev.bids b WHERE project_id = (?1) AND bid_status != 1", nativeQuery = true)
	Integer getProjectBidsCount(Integer projectId);

	@Query(name="VendorBid.CurrentProject")
	List<Object[]> findCurrentProjectsByVendorOrgId(Integer vendorOrganisationId);

	@Query(name="VendorBid.HistoryProject")
	List<Object[]> findHistoryProjectsByVendorOrgId(Integer vendorOrganisationId);
	
	VendorBid findVendorBidByProjectIdAndVendorOrgId(Integer projectId, Integer vendorOrgId);
	
}
