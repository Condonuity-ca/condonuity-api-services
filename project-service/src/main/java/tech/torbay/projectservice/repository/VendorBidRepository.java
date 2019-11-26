package tech.torbay.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.VendorBid;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorBidRepository extends JpaRepository<VendorBid, Integer> {

    List<VendorBid> findAll();

	VendorBid findByBidId (Integer vendorBidId);
	
	VendorBid findOneByBidId (Integer vendorBidId);

	List<VendorBid> findVendorBidByProjectId(Integer id);

}
