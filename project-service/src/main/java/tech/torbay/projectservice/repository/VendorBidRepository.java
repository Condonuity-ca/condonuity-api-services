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

	List<VendorBid> findVendorBidByProjectId(Integer id);
	
	@Query(value="SELECT COUNT(b.project_id) FROM condonuitydev.bids b WHERE project_id = (?1)", nativeQuery = true)
	Integer getProjectBidsCount(Integer projectId);

}
