package tech.torbay.userservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "client_organisation_amenities")
public class ClientAmenities {
	
	public ClientAmenities() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private Integer id = 0;
	
//	@ManyToOne
//    @JoinColumn(name = "client_organisation_id")
//	private ClientOrganisation clientOrganisation;
	
	private Integer clientOrganisationId = 0;
	private String amenitiesName = "";
	private String amenitiesType = "";
	private Integer amenitiesCount = 0;
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClientOrganisationId() {
		return clientOrganisationId/* clientOrganisation.getClientOrganisationId() */;
	}
	public void setClientOrganisationId(int clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId/*.setClientOrganisationId(clientOrganisationId)*/;
	}
	public String getAmenitiesName() {
		return amenitiesName;
	}
	public void setAmenitiesName(String amenitiesName) {
		this.amenitiesName = amenitiesName;
	}
	public String getAmenitiesType() {
		return amenitiesType;
	}
	public void setAmenitiesType(String amenitiesType) {
		this.amenitiesType = amenitiesType;
	}
	public int getAmenitiesCount() {
		return amenitiesCount;
	}
	public void setAmenitiesCount(int amenitiesCount) {
		this.amenitiesCount = amenitiesCount;
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
		return "ClientAmenities [id=" + id
				+ ", clientOrganisationId=" + clientOrganisationId + ", amenitiesName=" + amenitiesName
				+ ", amenitiesType=" + amenitiesType + ", amenitiesCount=" + amenitiesCount + ", createdAt="
				+ createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
}
