package tech.torbay.vendorservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.vendorservice.entity.ProjectAwards;

@Repository
public interface ProjectAwardsRepository extends JpaRepository<ProjectAwards, Integer> {

    List<ProjectAwards> findAll();

    ProjectAwards findOneById (Integer id);
    
    ProjectAwards findByProjectId (Integer projectId);

}