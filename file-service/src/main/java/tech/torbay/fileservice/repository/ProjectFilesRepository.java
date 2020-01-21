package tech.torbay.fileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.fileservice.entity.ClientRegistrationFiles;
import tech.torbay.fileservice.entity.ProjectFiles;

@Repository
public interface ProjectFilesRepository extends JpaRepository<ProjectFiles, Integer> {

    List<ProjectFiles> findAll();
    
    ProjectFiles findOneById(Integer Id);

	List<ProjectFiles> findAllByProjectId(Integer projectId);
	
}
