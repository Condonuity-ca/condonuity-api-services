package tech.torbay.securityservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "client_organisation_amenities")
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
	private String amenitiesName = "";
	private Integer amenitiesType = 0;
	private Integer amenitiesCount = 0;
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAmenities )) return false;
        return id != null && id.equals(((ClientAmenities) o).getId());
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAmenitiesName() {
		return amenitiesName;
	}
	public void setAmenitiesName(String amenitiesName) {
		this.amenitiesName = amenitiesName;
	}
	public Integer getAmenitiesType() {
		return amenitiesType;
	}
	public void setAmenitiesType(Integer amenitiesType) {
		this.amenitiesType = amenitiesType;
	}
	public Integer getAmenitiesCount() {
		return amenitiesCount;
	}
	public void setAmenitiesCount(Integer amenitiesCount) {
		this.amenitiesCount = amenitiesCount;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String created_at) {
		this.createdAt = created_at;
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
				+ ", amenitiesName=" + amenitiesName
				+ ", amenitiesType=" + amenitiesType + ", amenitiesCount=" + amenitiesCount + ", createdAt="
				+ createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
}
