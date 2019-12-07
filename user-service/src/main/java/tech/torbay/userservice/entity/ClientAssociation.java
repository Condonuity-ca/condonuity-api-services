package tech.torbay.userservice.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name="ClientAssociation")
@Table(name="client_association")
public class ClientAssociation {

//	@EmbeddedId
//    private ClientAssociateId id;
	
	@Id
	@GeneratedValue
	private Integer id;
    @Column(name = "client_organisation_id")
    private Integer clientOrganisationId = 0;
    @Column(name = "client_id")
    private Integer clientId = 0;
    private Integer clientUserType = 0;
    private Integer userRole = 0;
    private Integer accountVerificationStatus = 0;
    private Integer userAccountStatus = 0;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("clientId")
//    @JoinColumn(name = "client_id", insertable = false, updatable = false )
//    ClientUser clientUser;
//// 
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("clientOrganisationId")
//    @JoinColumn(name = "client_organisation_id", insertable = false, updatable = false )
//    ClientOrganisation clientOrganisation;
    
//    public ClientOrganisation getClientOrganisation() {
//		// TODO Auto-generated method stub
//		return clientOrganisation;
//	}
//	public ClientUser getClientUser() {
//		// TODO Auto-generated method stub
//		return clientUser;
//	}
//	public void setClientOrganisation(ClientOrganisation clientOrganisation) {
//		// TODO Auto-generated method stub
//		this.clientOrganisation = clientOrganisation; 
//	}
//	public void setClientUser(ClientUser clientUser) {
//		// TODO Auto-generated method stub
//		this.clientUser = clientUser; 
//	}
    
	public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}
	public void setClientOrganisationId(Integer clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public Integer getId() {
		return id;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return "ClientAssociation [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", clientUserId="
				+ clientId + ", clientUserType=" + clientUserType + ", userRole=" + userRole
				+ ", accountVerificationStatus=" + accountVerificationStatus + ", userAccountStatus="
				+ userAccountStatus + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
	public ClientAssociation() {
		// TODO Auto-generated constructor stub
	}
//	public ClientAssociation(ClientOrganisation clientOrganisation, ClientUser clientUser) {
//        this.clientOrganisation = clientOrganisation;
//        this.clientUser = clientUser;
//        this.id = new ClientAssociateId(clientOrganisation.getClientOrganisationId(), clientUser.getClientId());
//    }
//	
//	@Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
// 
//        if (o == null || getClass() != o.getClass())
//            return false;
// 
//        ClientAssociation that = (ClientAssociation) o;
//        return Objects.equals(clientOrganisation, that.clientOrganisation) &&
//               Objects.equals(clientUser, that.clientUser);
//    }
// 
//    @Override
//    public int hashCode() {
//        return Objects.hash(clientOrganisation, clientUser);
//    }
    
}
