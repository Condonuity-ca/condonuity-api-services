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
    
    List<ProjectReviewRating> findAllByVendorOrganisationIdAndStatus(Integer vendorOrganisationId, Integer activeStatus);
    
    List<ProjectReviewRating> findAllByClientIdAndClientOrganisationId(Integer clientId, Integer clientOrganisationId);

    @Query(value = "SELECT rr.* " + 
    		"FROM condonuitydev.project_reviews_ratings rr " + 
    		"LEFT JOIN condonuitydev.vendor_organisation vo " + 
    		"ON ( rr.vendor_organisation_id = vo.vendor_organisation_id) " + 
    		"LEFT JOIN condonuitydev.projects pro " + 
    		"ON ( rr.project_id = pro.project_id) " + 
    		"where ( rr.client_id = (?1) and rr.client_organisation_id = (?2)) and " + 
    		"concat (rr.rating, '.', rr.review_comments, '.', rr.reply_comments, " + 
    		"'.', rr.created_at, '.', rr.modified_date) " +
    		"LIKE (?3) or "+
    		"concat ( vo.company_name, " + 
    		"'.', vo.legal_name, '.', vo.contact_person, '.', vo.address, '.', vo.city, " + 
    		"'.', vo.province, '.', vo.email, '.', vo.website, '.', vo.logo_name )" +
    		"LIKE (?3) or " + 
    		"concat ( pro.project_name, '.', pro.bid_end_date, '.', pro.project_start_date, " + 
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

	@Query(value = "SELECT rr.* " + 
    		"FROM condonuitydev.project_reviews_ratings rr " + 
    		"LEFT JOIN condonuitydev.vendor_organisation vo " + 
    		"ON ( rr.vendor_organisation_id = vo.vendor_organisation_id) " + 
    		"LEFT JOIN condonuitydev.projects pro " + 
    		"ON ( rr.project_id = pro.project_id) " + 
    		"where " + 
    		"concat (rr.rating LIKE (?1) or rr.review_comments LIKE (?1) or rr.reply_comments LIKE (?1) or " + 
    		"rr.created_at LIKE (?1) or rr.modified_date  LIKE (?1)) " +
    		"or "+
    		"concat ( vo.company_name LIKE (?1) or " + 
    		"vo.legal_name LIKE (?1) or vo.contact_person LIKE (?1) or vo.address LIKE (?1) or vo.city LIKE (?1) or " + 
    		"vo.province LIKE (?1) or vo.email LIKE (?1) or vo.website LIKE (?1) or vo.logo_name LIKE (?1) )" +
    		"or " + 
    		"concat ( pro.project_name LIKE (?1) or pro.bid_end_date LIKE (?1) or pro.project_start_date LIKE (?1) or " + 
    		"pro.project_completion_deadline LIKE (?1) or pro.estimated_budget LIKE (?1) or pro.duration LIKE (?1) or " + 
    		"pro.description LIKE (?1) or pro.special_conditions LIKE (?1) or pro.city LIKE (?1)) " , nativeQuery = true)
	List<ProjectReviewRating> findAllByKeyword(String keyword);

	List<ProjectReviewRating> findAllByVendorOrganisationId(Integer vendorOrganisationId);

	List<ProjectReviewRating> findAllByClientOrganisationId(Integer clientOrganisationId);
}
