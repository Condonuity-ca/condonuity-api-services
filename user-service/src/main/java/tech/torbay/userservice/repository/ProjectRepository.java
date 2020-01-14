package tech.torbay.userservice.repository;

import java.util.HashMap;
import java.util.List;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAll();
    
    @Query(name="Project.MarketPlace")
    List<Object[]> findAllProjectsForMarketPlace();

    Project findByProjectId(Integer Id);

	List<Project> findAllByClientOrganisationIdAndStatus(Integer id, int value);
	
	Project findOneByProjectId(Integer projectId);

	@Query("select pro from Project pro where pro.projectId IN (?1)")
	List<Project> getAllVendorProjects(List<Integer> ids);
	
}
