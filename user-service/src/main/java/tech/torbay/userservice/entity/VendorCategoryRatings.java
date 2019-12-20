package tech.torbay.userservice.entity;

import javax.persistence.*;

import tech.torbay.userservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "vendor_category_ratings")
public class VendorCategoryRatings {

    public VendorCategoryRatings() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer clientId = 0;
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

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
		return "VendorCategoryRatings [id=" + id + ", clientId=" + clientId + ", vendorOrganisationId=" + vendorOrganisationId + ", projectId="
				+ projectId + ", rating=" + rating + ", ratingCategory=" + ratingCategory + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}