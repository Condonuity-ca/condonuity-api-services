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
@Table(name = "vendor_licenses")
public class VendorLicenses {
	
	public VendorLicenses() {

    }
 
    public VendorLicenses(Integer vendorOrganisationId, String licenseName, String licenseNumber,
			String licenseExpiryDate) {
		super();
		this.vendorOrganisationId = vendorOrganisationId;
		this.licenseName = licenseName;
		this.licenseNumber = licenseNumber;
		this.licenseExpiryDate = licenseExpiryDate;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer vendorOrganisationId = 0; // one to many
	private String licenseName = "";
	private String licenseNumber = "";
	private String licenseExpiryDate = "";
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorLicenses )) return false;
        return id != null && id.equals(((VendorLicenses) o).getId());
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

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseExpiryDate() {
		return licenseExpiryDate;
	}

	public void setLicenseExpiryDate(String licenseExpiryDate) {
		this.licenseExpiryDate = licenseExpiryDate;
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
		return "VendorLicenses [id=" + id + ", vendorOrganisationId=" + vendorOrganisationId + ", licenseName="
				+ licenseName + ", licenseNumber=" + licenseNumber + ", licenseExpiryDate=" + licenseExpiryDate
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
