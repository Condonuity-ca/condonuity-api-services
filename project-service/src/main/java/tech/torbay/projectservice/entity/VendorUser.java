package tech.torbay.projectservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import tech.torbay.projectservice.constants.Constants;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "vendor_user")
public class VendorUser {

    public VendorUser() {

    }
    @Id
    private Integer userId = 0;
	private Integer vendorOrganisationId = 0;
	private Integer userType = Constants.UserType.VENDOR.getValue();
    private Integer userRole = Constants.UserRole.USER.getValue();
    private String legalName = "";
    private String email = "";
	private Integer accountStatus = null;
	private Integer accountVerificationStatus = null;
    
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt = null;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate = null;
    
    
    @OneToOne(mappedBy = "vendorUser", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private VendorBid vendorBid;
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}
	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAccountStatus() {
		return null/* accountStatus */;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Integer getAccountVerificationStatus() {
		return null/* accountVerificationStatus */;
	}
	public void setAccountVerificationStatus(Integer accountVerificationStatus) {
		this.accountVerificationStatus = accountVerificationStatus;
	}
	public String getCreatedAt() {
		return null/* createdAt */;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getModifiedDate() {
		return null/* modifiedDate */;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Override
	public String toString() {
		return "VendorUser [userId=" + userId + ", vendorOrganisationId=" + vendorOrganisationId + ", userType="
				+ userType + ", userRole=" + userRole + ", legalName=" + legalName + ", email=" + email
				+ ", accountStatus=" + accountStatus + ", accountVerificationStatus=" + accountVerificationStatus
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
}