package tech.torbay.projectservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.BidFiles;

@Repository
public interface BidFilesRepository extends JpaRepository<BidFiles, Integer> {

    List<BidFiles> findAll();
    
    BidFiles findOneById(Integer Id);

	List<BidFiles> findAllByBidId(Integer bidId);
	
}
