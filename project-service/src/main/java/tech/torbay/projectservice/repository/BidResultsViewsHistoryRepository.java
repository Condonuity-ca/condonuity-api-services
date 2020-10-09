package tech.torbay.projectservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.BidResultsViewsHistory;

@Repository
public interface BidResultsViewsHistoryRepository extends JpaRepository<BidResultsViewsHistory, Integer> {

    List<BidResultsViewsHistory> findAll();
    
    BidResultsViewsHistory findOneById(Integer id);
    
    BidResultsViewsHistory findByClientIdAndClientOrganisationIdAndProjectIdAndBidId(Integer clientId, 
    		Integer clientOrganisationId, Integer projectId, Integer bidId);

	List<BidResultsViewsHistory> findByProjectIdAndBidId(Integer projectId, Integer bidId);

	BidResultsViewsHistory findByClientIdAndProjectIdAndBidId(Integer clientId, Integer projectId, Integer bidId);

	List<BidResultsViewsHistory> findByClientIdAndProjectId(Integer clientId, Integer projectId);
}
