package tech.torbay.userservice.entity;

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
@Table(name = "vendor_services_cities")
public class VendorServicesCities {
	
	public VendorServicesCities() {

    }
 
    public VendorServicesCities(Integer vendorOrganisationId, Integer serviceCityId) {
		super();
		this.vendorOrganisationId = vendorOrganisationId;
		this.serviceCityId = serviceCityId;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer vendorOrganisationId = 0; //one to many
	private Integer serviceCityId = 0;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorServicesCities )) return false;
        return id != null && id.equals(((VendorServicesCities) o).getId());
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}

	public Integer getServiceCityId() {
		return serviceCityId;
	}

	public void setServiceCityId(Integer serviceCityId) {
		this.serviceCityId = serviceCityId;
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
		return "VendorServicesCities [id=" + id + ", vendorOrganisationId=" + vendorOrganisationId + ", serviceCityId="
				+ serviceCityId + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
}
