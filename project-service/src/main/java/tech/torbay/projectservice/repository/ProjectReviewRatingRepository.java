package tech.torbay.projectservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.torbay.projectservice.entity.ProjectReviewRating;

@Repository
public interface ProjectReviewRatingRepository extends JpaRepository<ProjectReviewRating, Integer> {

    List<ProjectReviewRating> findAll();
    
    @SuppressWarnings("unchecked")
	ProjectReviewRating save(ProjectReviewRating projectReviewRating);
    
    Optional<ProjectReviewRating> findById(@Param("id") Integer id);
    
    ProjectReviewRating findOneById(@Param("id") Integer id);
    
    @Modifying
    @Query("update ProjectReviewRating rr set rr.replyComments = :reply_comments where rr.id = :id")
    ProjectReviewRating setReplyComments(@Param("id") Integer id, @Param("reply_comments") String replyComments);

}
