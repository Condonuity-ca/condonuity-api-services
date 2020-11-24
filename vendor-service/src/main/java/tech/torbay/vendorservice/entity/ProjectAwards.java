package tech.torbay.vendorservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
//@JsonInclude(value = Include.NON_NULL)
@Table(name = "project_awards")

public class ProjectAwards {

    public ProjectAwards() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
    @NotNull
	private Integer projectId = 0;
    @NotNull
	private Integer modifiedByUserId = 0;
    @NotNull
    private Integer awardedBidId = 0;
    @NotNull
	private Integer vendorOrganisationId = 0;
    private String comments = "";
    @NotNull
    private String awardDate = "";
    @NotNull
    private String totalPrice = "";
    @NotNull
    private String startDate = "";
    private String duration = "";
	
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

	public Integer getModifiedByUserId() {
		return modifiedByUserId;
	}

	public void setModifiedByUserId(Integer modifiedByUserId) {
		this.modifiedByUserId = modifiedByUserId;
	}

	public Integer getAwardedBidId() {
		return awardedBidId;
	}

	public void setAwardedBidId(Integer awardedBidId) {
		this.awardedBidId = awardedBidId;
	}

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(String awardDate) {
		this.awardDate = awardDate;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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
		return "ProjectAwards [id=" + id + ", projectId=" + projectId + ", modifiedByUserId=" + modifiedByUserId
				+ ", awardedBidId=" + awardedBidId + ", vendorOrganisationId=" + vendorOrganisationId + ", comments="
				+ comments + ", awardDate=" + awardDate + ", totalPrice=" + totalPrice + ", startDate=" + startDate
				+ ", duration=" + duration + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}