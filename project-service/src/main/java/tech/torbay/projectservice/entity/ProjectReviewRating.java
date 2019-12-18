package tech.torbay.projectservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "project_reviews_ratings")
public class ProjectReviewRating {

	public ProjectReviewRating() {

    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
    private Integer projectId = 0;
    private Integer clientId = 0;
    private Integer vendorId = 0;
    private String rating;
    private String reviewComments;
    private String replyComments;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public String getReplyComments() {
		return replyComments;
	}

	public void setReplyComments(String replyComments) {
		this.replyComments = replyComments;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "ProjectReviewRating [id=" + id + ", projectId=" + projectId + ", clientId=" + clientId
				+ ", vendorId=" + vendorId + ", rating=" + rating + ", reviewComments="
				+ reviewComments + ", replyComments=" + replyComments + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}
    
}
