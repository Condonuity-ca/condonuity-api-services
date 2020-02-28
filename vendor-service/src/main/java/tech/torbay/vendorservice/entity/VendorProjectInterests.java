package tech.torbay.vendorservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.torbay.vendorservice.constants.Constants.ProjectInterestStatus;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "vendor_project_interests")
public class VendorProjectInterests {
	
	public VendorProjectInterests() {

    }
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
    private Integer projectId = 0;
    private Integer vendorOrganisationId = 0;
	private Integer vendorId = 0;
	private Integer interestStatus = ProjectInterestStatus.LIKE.getValue();
    
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

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}
	
	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public Integer getInterestStatus() {
		return interestStatus;
	}

	public void setInterestStatus(Integer interestStatus) {
		this.interestStatus = interestStatus;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorProjectInterests )) return false;
        return id != null && id.equals(((VendorProjectInterests) o).getId());
    }

	@Override
	public String toString() {
		return "VendorProjectInterests [id=" + id + ", projectId=" + projectId + ", vendorOrganisationId="
				+ vendorOrganisationId + ", vendorId=" + vendorId + ", interestStatus=" + interestStatus
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
