package tech.torbay.fileservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.fileservice.entity.ProjectAwardFiles;

@Repository
public interface ProjectAwardFilesRepository extends JpaRepository<ProjectAwardFiles, Integer> {

    List<ProjectAwardFiles> findAll();
    
    ProjectAwardFiles findOneById(Integer Id);

	List<ProjectAwardFiles> findAllByProjectAwardId(Integer projectAwardId);

	ProjectAwardFiles findByBlobName(String blobName);
	
}
