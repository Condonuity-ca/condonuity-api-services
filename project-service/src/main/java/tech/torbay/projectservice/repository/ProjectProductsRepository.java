package tech.torbay.projectservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.ProjectProducts;
import tech.torbay.projectservice.entity.ProjectQuestionAnswer;

@Repository
public interface ProjectProductsRepository extends JpaRepository<ProjectProducts, Integer> {

    List<ProjectProducts> findAll();

    Optional<ProjectProducts> findById(Integer Id);

	void save(List<ProjectProducts> projectProducts);

}
