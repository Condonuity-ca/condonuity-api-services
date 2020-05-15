package tech.torbay.projectservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.ProjectAwards;
import tech.torbay.projectservice.entity.VendorBid;

@Repository
public interface ProjectAwardsRepository extends JpaRepository<ProjectAwards, Integer> {

    List<ProjectAwards> findAll();

    ProjectAwards findOneById (Integer id);
    
    ProjectAwards findByProjectId (Integer projectId);

}
