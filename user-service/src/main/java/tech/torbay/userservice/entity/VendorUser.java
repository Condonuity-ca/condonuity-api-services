package tech.torbay.userservice.entity;

import javax.persistence.*;

import tech.torbay.userservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "vendor_user")
public class VendorUser {

    public VendorUser() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId = 0;
	private Integer vendorOrganisationId = 0;
	private Integer userType = Constants.UserType.VENDOR.getValue();
    private Integer userRole = Constants.UserRole.USER.getValue();
    private String legalName = "";
    private String email = "";
	private Integer accountStatus = 0;
	private Integer accountVerificationStatus = 0;
    
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
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
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Integer getAccountVerificationStatus() {
		return accountVerificationStatus;
	}
	public void setAccountVerificationStatus(Integer accountVerificationStatus) {
		this.accountVerificationStatus = accountVerificationStatus;
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
		return "VendorUser [userId=" + userId + ", vendorOrganisationId=" + vendorOrganisationId + ", userType="
				+ userType + ", userRole=" + userRole + ", legalName=" + legalName + ", email=" + email
				+ ", accountStatus=" + accountStatus + ", accountVerificationStatus=" + accountVerificationStatus
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
}