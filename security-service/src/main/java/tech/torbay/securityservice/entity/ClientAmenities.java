package tech.torbay.securityservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.torbay.securityservice.constants.Constants.DeleteStatus;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "client_organization_amenities")
public class ClientAmenities {
	
	public ClientAmenities() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private Integer id = 0;
	
	@ManyToOne
    @JoinColumn(name = "client_organisation_id")
	private ClientOrganisation clientOrganisation;
	
//	private int clientOrganisationId = 0; // one to many
	private Integer amenityId = 0;
	
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

	public Integer getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(Integer amenityId) {
		this.amenityId = amenityId;
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
		return "ClientAmenities [id=" + id + ", amenityId="
				+ amenityId + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}
	
}