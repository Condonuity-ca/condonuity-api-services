package tech.torbay.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.VendorBid;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectQARepository extends JpaRepository<ProjectQuestionAnswer, Integer> {

    List<ProjectQuestionAnswer> findAll();

    ProjectQuestionAnswer findByProjectqaId(Integer Id);
    
	List<ProjectQuestionAnswer> findProjectQuestionAnswerByProjectId(Integer id);

//	@Modifying
//	@Query("update ProjectQuestionAnswer PQA set PQA.answer = :answer where PQA.projectqaId = :projectqaId")
//	Integer setAnswerForProjectQuestionAnswer(@Param("answer") String answer, @Param("projectqaId") Integer projectqaId);

//	Integer setAnswerForProjectqaId(String answer, Integer projectqaId);
	
	ProjectQuestionAnswer findOneByProjectqaId(Integer Id);

}
