package tech.torbay.projectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.Project;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;
import tech.torbay.projectservice.entity.VendorBid;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectQARepository extends JpaRepository<ProjectQuestionAnswer, Integer> {

    List<ProjectQuestionAnswer> findAll();

    ProjectQuestionAnswer findByProjectQAId (Integer Id);

	List<ProjectQuestionAnswer> findProjectQuestionAnswerByProjectId(Integer id);

}
