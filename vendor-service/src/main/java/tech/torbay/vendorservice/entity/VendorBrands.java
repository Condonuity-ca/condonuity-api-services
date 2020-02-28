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

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "vendor_brands")
public class VendorBrands {
	
	public VendorBrands() {

    }
 
    public VendorBrands(Integer vendorOrganisationId, String brandName) {
		super();
		this.vendorOrganisationId = vendorOrganisationId;
		this.brandName = brandName;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer vendorOrganisationId = 0; //one to many
	private String brandName = "";
    
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

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
		return "VendorBrands [id=" + id + ", vendorOrganisationId=" + vendorOrganisationId + ", brandName=" + brandName
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorBrands )) return false;
        return id != null && id.equals(((VendorBrands) o).getId());
    }
}
