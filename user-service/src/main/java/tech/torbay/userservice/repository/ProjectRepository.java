package tech.torbay.userservice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Query(value = "SELECT pro.* FROM projects pro where client_organisation_id = (?1) and delete_status = 1 " + 
			"and ( pro.status= 1 or pro.status = 2)  " + 
			"and concat ( pro.project_name, '.', pro.tags, '.', pro.bid_end_date, " + 
			"'.', pro.project_start_date, '.', pro.project_completion_deadline, '.', pro.estimated_budget, " +
			"'.', pro.duration, '.', pro.description, " + 
			"'.', pro.special_conditions, '.', pro.city, '.', pro.created_at, '.', pro.modified_date ) " + 
			"LIKE (?2)"
			, nativeQuery = true)
	List<Project> findAllCurrentByKeyword(Integer clientOrganisationId, String keyword, List<Integer> status);
	
	@Query(value = "SELECT pro.* FROM projects pro where client_organisation_id = (?1) and delete_status = 1 " + 
			"and ( pro.status= 3 or pro.status = 4)  " + 
			"and concat ( pro.project_name, '.', pro.tags, '.', pro.bid_end_date, " + 
			"'.', pro.project_start_date, '.', pro.project_completion_deadline, '.', pro.estimated_budget, " +
			"'.', pro.duration, '.', pro.description, " + 
			"'.', pro.special_conditions, '.', pro.city, '.', pro.created_at, '.', pro.modified_date ) " + 
			"LIKE (?2)"
			, nativeQuery = true)
	List<Project> findAllHistoryByKeyword(Integer clientOrganisationId, String keyword, List<Integer> status);
	
	@Query(value = "SELECT pro.* FROM projects pro where client_organisation_id = (?1) and delete_status = 1 " + 
			"and ( pro.status= 2)  " + 
			"and concat ( pro.project_name, '.', pro.tags, '.', pro.bid_end_date, " + 
			"'.', pro.project_start_date, '.', pro.project_completion_deadline, '.', pro.estimated_budget, " +
			"'.', pro.duration, '.', pro.description, " + 
			"'.', pro.special_conditions, '.', pro.city, '.', pro.created_at, '.', pro.modified_date ) " + 
			"LIKE (?2)"
			, nativeQuery = true)
	List<Project> findAllMarketplaceByKeyword(Integer clientOrganisationId, String keyword, List<Integer> status);
	
	@Query(value = "SELECT pro.* FROM projects pro where delete_status = 1 " + 
			"and ( pro.status= 2)  " + 
			"and concat ( pro.project_name, '.', pro.tags, '.', pro.bid_end_date, " + 
			"'.', pro.project_start_date, '.', pro.project_completion_deadline, '.', pro.estimated_budget, " +
			"'.', pro.duration, '.', pro.description, " + 
			"'.', pro.special_conditions, '.', pro.city, '.', pro.created_at, '.', pro.modified_date ) " + 
			"LIKE (?1)"
			, nativeQuery = true)
	List<Project> findAllMarketplaceByKeyword(String keyword, List<Integer> status);
	
	//SELECT pro.* FROM projects pro where client_organisation_id = 1 and  
//	 (pro.project_name LIKE '%remove%' or pro.description LIKE '%remove%' )
	
//	SELECT pro.* FROM projects pro where client_organisation_id = 1 and concat (pro.project_name, pro.description) like "%remove%";
	
	@Query(value = "SELECT pro.* FROM projects pro where client_organisation_id = (?1) and pro.status IN (?3) and " + 
			"pro.tags LIKE (?2)"
			, nativeQuery = true)
	List<Project> findAllByTagKeyword(Integer clientOrganisationId, String keyword, List<Integer> projectStatusCodes);

	@Query(name="Project.MarketPlaceSearch")
	List<Object[]> findAllProjectsForMarketPlaceByKeyword(String keyword, List<Integer> projectStatusCodes);

	@Query(name="Project.VendorCurrentProjectsSearch")
	List<Object[]> findAllCurrentProjectsForVendorByKeyword(Integer vendorOrganisationId, String keyword);
	
	@Query(name="Project.VendorHistoryProjectsSearch")
	List<Object[]> findAllHistoryProjectsForVendorByKeyword(Integer vendorOrganisationId, String keyword);

	@Query(name="Project.VendorFavoriteProjectsSearch")
	List<Object[]> findFavouriteProjectsForVendorByKeyword(Integer vendorOrganisationId, String keyword, List<Integer> projectStatusCodes);

	@Modifying
	@Transactional
	@Query(value="update projects set delete_status = (?1) where project_id = (?2);", nativeQuery = true)
	int setDeleteStatusByProjectId(Integer deleteStatus, Integer projectId);
	
	@Query(value = "SELECT pro.* FROM projects pro where " + 
			"pro.tags LIKE (?1)"
			, nativeQuery = true)
	List<Project> findAllByTagKeyword(String keyword);
}

