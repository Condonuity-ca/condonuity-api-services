package tech.torbay.clientsservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor_category_ratings")
public class VendorCategoryRatings {

    public VendorCategoryRatings() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer reviewRatingId = 0;
    private Integer clientId = 0;
    private Integer clientOrganisationId = 0;
    private Integer vendorOrganisationId = 0;
    private Integer projectId = 0;
    private Float rating = 0f;
    private Integer ratingCategory = 0;
    
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

	public Integer getReviewRatingId() {
		return reviewRatingId;
	}

	public void setReviewRatingId(Integer reviewRatingId) {
		this.reviewRatingId = reviewRatingId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}

	public void setClientOrganisationId(Integer clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getRatingCategory() {
		return ratingCategory;
	}

	public void setRatingCategory(Integer ratingCategory) {
		this.ratingCategory = ratingCategory;
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
		return "VendorCategoryRatings [id=" + id + ", reviewRatingId=" + reviewRatingId + ", clientId=" + clientId
				+ ", clientOrganisationId=" + clientOrganisationId + ", vendorOrganisationId=" + vendorOrganisationId
				+ ", projectId=" + projectId + ", rating=" + rating + ", ratingCategory=" + ratingCategory
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}