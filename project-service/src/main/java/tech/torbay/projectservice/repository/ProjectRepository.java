package tech.torbay.projectservice.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAll();
    
    
    @Query(value="SELECT pro.*, co.management_company, cu.first_name, cu.last_name FROM condonuitydev.projects pro " + 
    		"INNER JOIN condonuitydev.client_organisation co ON co.client_organisation_id = pro.client_organisation_id " + 
    		"INNER JOIN condonuitydev.client_user cu ON cu.client_id = pro.client_id;", nativeQuery = true)
    List<Project> findAllProjectsForMarketPlace();

    Project findByProjectId(Integer Id);

	List<Project> findAllByClientOrganisationIdAndStatus(Integer id, int value);
	
	Project findOneByProjectId(Integer projectId);
    
}
