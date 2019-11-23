package tech.torbay.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="client_association")
public class ClientAssociation {

    @Id
    private Integer id = 0;
    @Column(name = "client_organisation_id")
    private Integer clientOrganisationId = 0;
    @Column(name = "client_id")
    private Integer clientId = 0;
    private Integer clientUserType = 0;
    private Integer userRole = 0;
    private Integer accountVerificationStatus = 0;
    private Integer userAccountStatus = 0;
    private String createdAt;
    private String modifiedDate;
    
    @ManyToOne
    @MapsId("client_id")
    @JoinColumn(name = "client_id")
    ClientUser clientUser;
 
    @ManyToOne
    @MapsId("organisation_id")
    @JoinColumn(name = "client_organisation_id")
    ClientOrganisation clientOrganisation;
    
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
		return "ClientAssociation [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", clientId=" + clientId
				+ ", clientUserType=" + clientUserType + ", userRole=" + userRole + ", accountVerificationStatus="
				+ accountVerificationStatus + ", userAccountStatus=" + userAccountStatus + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}
    
}
