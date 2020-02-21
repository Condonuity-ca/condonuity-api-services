package tech.torbay.userservice.repository;

import java.util.HashMap;
import java.util.List;
import javax.persistence.SqlResultSetMapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query(value = "SELECT pro.* FROM condonuitydev.projects pro where client_organisation_id = (?1) and " + 
			"concat ( pro.project_name, '.', pro.tags, '.', pro.bid_end_date, " + 
			"'.', pro.project_start_date, '.', pro.project_completion_deadline, '.', pro.estimated_budget, " +
			"'.', pro.duration, '.', pro.description, " + 
			"'.', pro.special_conditions, '.', pro.city, '.', pro.created_at, '.', pro.modified_date ) " + 
			"LIKE (?2)"
			, nativeQuery = true)
	List<Project> findAllByKeyword(Integer clientOrganisationId, String keyword);
	
	//SELECT pro.* FROM condonuitydev.projects pro where client_organisation_id = 1 and  
//	 (pro.project_name LIKE '%remove%' or pro.description LIKE '%remove%' )
	
//	SELECT pro.* FROM condonuitydev.projects pro where client_organisation_id = 1 and concat (pro.project_name, pro.description) like "%remove%";
	
	@Query(value = "SELECT pro.* FROM condonuitydev.projects pro where client_organisation_id = (?1) and " + 
			"pro.tags LIKE (?2)"
			, nativeQuery = true)
	List<Project> findAllByTagKeyword(Integer clientOrganisationId, String keyword);

	@Query(name="Project.MarketPlaceSearch")
	List<Object[]> findAllProjectsForMarketPlaceByKeyword(String keyword);
}
