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
@Table(name = "vendor_memberships")
public class VendorMemberships {
	
	public VendorMemberships() {

    }
 
    public VendorMemberships(Integer vendorOrganisationId, String membershipName, String membershipProvider) {
		super();
		this.vendorOrganisationId = vendorOrganisationId;
		this.membershipName = membershipName;
		this.membershipProvider = membershipProvider;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer vendorOrganisationId = 0; // one to many
	private String membershipName = "";
	private String membershipProvider = "";
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorMemberships )) return false;
        return id != null && id.equals(((VendorMemberships) o).getId());
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

	public String getMembershipName() {
		return membershipName;
	}

	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}

	public String getMembershipProvider() {
		return membershipProvider;
	}

	public void setMembershipProvider(String membershipProvider) {
		this.membershipProvider = membershipProvider;
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
		return "VendorMemberships [id=" + id + ", vendorOrganisationId=" + vendorOrganisationId + ", membershipName="
				+ membershipName + ", membershipProvider=" + membershipProvider + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
