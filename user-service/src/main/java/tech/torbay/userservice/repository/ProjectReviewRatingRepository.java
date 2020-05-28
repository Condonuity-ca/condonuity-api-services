package tech.torbay.userservice.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.torbay.userservice.entity.ProjectReviewRating;

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
    
    List<ProjectReviewRating> findAllByVendorOrganisationId(Integer vendorOrganisationId);
    
    List<ProjectReviewRating> findAllByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrganisationId);

    @Query(value = "SELECT rr.* " + 
    		"FROM condonuitydev.project_reviews_ratings rr " + 
    		"LEFT JOIN condonuitydev.vendor_organisation vo " + 
    		"ON ( rr.vendor_organisation_id = vo.vendor_organisation_id) " + 
    		"LEFT JOIN condonuitydev.projects pro " + 
    		"ON ( rr.project_id = pro.project_id) " + 
    		"where ( rr.client_id = (?1) and rr.client_organisation_id = (?2)) and " + 
    		"concat (rr.rating, '.', rr.review_comments, '.', rr.reply_comments, " + 
    		"'.', rr.created_at, '.', rr.modified_date, '.', vo.company_name, " + 
    		"'.', vo.legal_name, '.', vo.contact_person, '.', vo.address, '.', vo.city, " + 
    		"'.', vo.province, '.', vo.email, '.', vo.website, '.', vo.logo_name, " + 
    		"'.', pro.project_name, '.', pro.bid_end_date, '.', pro.project_start_date, '.', " + 
    		"'.', pro.project_completion_deadline, '.', pro.estimated_budget, '.', pro.duration, " + 
    		"'.', pro.description, '.', pro.special_conditions, '.', pro.city) " + 
    		"LIKE (?3)", nativeQuery = true)
	List<ProjectReviewRating> findAllByClientIdAndClientOrganisationIdAndKeyword(
			Integer clientUserId,
			Integer clientOrganisationId, 
			String keyword);

	List<ProjectReviewRating> findAllByClientOrganisationIdAndStatus(Integer clientOrganisationId, Integer status);

	@Modifying
	@Transactional
	@Query(value="UPDATE condonuitydev.project_reviews_ratings SET status = (?1) WHERE id=(?2);", nativeQuery = true)
	int setStatusById(Integer status, Integer reviewRatingId);

}
