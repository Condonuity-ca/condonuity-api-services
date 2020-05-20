package tech.torbay.securityservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="client_association")
public class ClientAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    @Column(name = "client_organisation_id")
    private Integer clientOrganisationId = 0;
    @Column(name = "client_id")
    private Integer clientId = 0;
    private Integer clientUserType = 0;
    private Integer userRole = 0;
    private Integer accountVerificationStatus = 0;
    private Integer userAccountStatus = 0;
    private Integer deleteStatus = 0;
    private String userInactiveDate = "";
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
//    @ManyToOne
//    @MapsId("client_id")
//    @JoinColumn(name = "client_id")
//    ClientUser clientUser;
// 
//    @ManyToOne
//    @MapsId("client_organisation_id")
//    @JoinColumn(name = "client_organisation_id")
//    ClientOrganisation clientOrganisation;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}
	public void setClientOrganisationId(Integer clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getClientUserType() {
		return clientUserType;
	}
	public void setClientUserType(Integer clientUserType) {
		this.clientUserType = clientUserType;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Integer getAccountVerificationStatus() {
		return accountVerificationStatus;
	}
	public void setAccountVerificationStatus(Integer accountVerificationStatus) {
		this.accountVerificationStatus = accountVerificationStatus;
	}
	public Integer getUserAccountStatus() {
		return userAccountStatus;
	}
	public void setUserAccountStatus(Integer userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}
	public String getUserInactiveDate() {
		return userInactiveDate;
	}
	public void setUserInactiveDate(String userInactiveDate) {
		this.userInactiveDate = userInactiveDate;
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
	
	public Integer getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	@Override
	public String toString() {
		return "ClientAssociation [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", clientId="
				+ clientId + ", clientUserType=" + clientUserType + ", userRole=" + userRole
				+ ", accountVerificationStatus=" + accountVerificationStatus + ", userAccountStatus="
				+ userAccountStatus + ", deleteStatus=" + deleteStatus + ", userInactiveDate=" + userInactiveDate
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
	
}
