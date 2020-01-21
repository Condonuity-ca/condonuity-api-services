package tech.torbay.fileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.fileservice.entity.BidFiles;

@Repository
public interface BidFilesRepository extends JpaRepository<BidFiles, Integer> {

    List<BidFiles> findAll();
    
    BidFiles findOneById(Integer Id);

	List<BidFiles> findAllByBidId(Integer bidId);
	
}
