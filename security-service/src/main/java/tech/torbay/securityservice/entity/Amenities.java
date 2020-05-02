package tech.torbay.securityservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "amenities")
public class Amenities {
	
	public Amenities() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	private Integer id = 0;
	
	private String amenitiesName = "";
	private String logo = "";
	private Integer status = 0;
	
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amenities )) return false;
        return id != null && id.equals(((Amenities) o).getId());
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


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
		return "Amenities [id=" + id + ", amenitiesName=" + amenitiesName + ", logo=" + logo + ", status=" + status
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
}
