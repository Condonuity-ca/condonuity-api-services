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
@Table(name = "vendor_products")
public class VendorProducts {
	
	public VendorProducts() {

    }
 
    public VendorProducts(Integer vendorOrganisationId, String productName) {
		super();
		this.vendorOrganisationId = vendorOrganisationId;
		this.productName = productName;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer vendorOrganisationId = 0; // one to many
	private String productName = "";
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorProducts )) return false;
        return id != null && id.equals(((VendorProducts) o).getId());
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
		return "VendorProducts [id=" + id + ", vendorOrganisationId=" + vendorOrganisationId + ", productName="
				+ productName + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
