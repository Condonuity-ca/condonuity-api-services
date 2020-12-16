package tech.torbay.projectservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query(value="select pro.* from condonuitydev.projects pro where pro.delete_status = 1", nativeQuery = true )
    List<Project> findAll();
    
    @Query(name="Project.MarketPlace")
    List<Object[]> findAllProjectsForMarketPlace();

    Project findByProjectId(Integer Id);

	List<Project> findAllByClientOrganisationIdAndStatus(Integer id, int value);
	
	@Query(value="select pro.* from condonuitydev.projects pro where client_organisation_id = (?1) and ( status = 1 or status = 2 ) and pro.delete_status = 1 ORDER BY pro.bid_end_date DESC", nativeQuery = true )
	List<Project> findAllCurrentProjects(Integer id);
	
	@Query(value="select pro.* from condonuitydev.projects pro where client_organisation_id = (?1) and ( status = 3 or status = 4) and pro.delete_status = 1 ORDER BY pro.bid_end_date DESC", nativeQuery = true )
	List<Project> findAllAwardedProjects(Integer id);
	
	Project findOneByProjectId(Integer projectId);

	@Query("select pro from Project pro where pro.projectId IN (?1) and pro.deleteStatus = 1 and pro.status = 2")
	List<Project> getAllVendorProjects(List<Integer> ids);

	@Transactional
	@Modifying
	@Query(value = "Update condonuitydev.projects set status = 3 where bid_end_date < now() and (status = 2);", nativeQuery = true)
	void updateClosedProjects();
	
	@Query(value = "select pro.* from condonuitydev.projects pro where bid_end_date < now() and (status = 2);", nativeQuery = true)
	List<Project> getClosingProjects();
	
	@Query(name="Project.MarketPlaceSupportUser")
    List<Object[]> findAllProjectsInMarketPlaceForSupportUser();
}
