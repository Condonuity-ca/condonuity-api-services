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
    private Integer userId = 0;
	private Integer vendorId = 0;
	private Integer userType = Constants.UserType.VENDOR.getValue();
    private Integer userRole = Constants.UserRole.USER.getValue();
    private String legalName = "";
    private String email = "";
    private String username = "";
    private String password = "";
	private Integer status = 0;
    private String createdDate = "";
    
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return "VendorUser [userId=" + userId + ", vendorId=" + vendorId + ", userType=" + userType + ", userRole="
				+ userRole + ", legalName=" + legalName + ", email=" + email + ", username=" + username + ", password="
				+ password + ", status=" + status + ", createdDate=" + createdDate + "]";
	}

	
}