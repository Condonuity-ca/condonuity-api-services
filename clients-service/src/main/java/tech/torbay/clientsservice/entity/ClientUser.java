package tech.torbay.clientsservice.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.NaturalId;

import tech.torbay.clientsservice.constants.Constants;

@Entity
@Table(name="client_user")
public class ClientUser {


	public ClientUser() {
	}
	
    public ClientUser(String email) {
    	this.email = email;
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
    private Integer clientId = 0;
	
	@NaturalId
	@Email
    private String email = "";
	
    private String firstName = "";
    private String LastName = "";
    private String legalName = "";
    private String city = "";
    private String phone = "";
    private String countryCode;
    private Integer userType = Constants.UserType.CLIENT.getValue();
    private Integer primaryOrgId = 0;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
    @OneToMany(
            mappedBy = "clientUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
        private List<ClientAssociation> clientOrganisations = new ArrayList<>();
    
//    @OneToMany(mappedBy = "clientUser")
//    private Set<ClientAssociation> clientOrganisations = new HashSet<ClientAssociation>();
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ClientUser clientUser = (ClientUser) o;
        return Objects.equals(clientId, clientUser.clientId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }
//	public List<ClientAssociation> getClientOrganisations() {
//	// TODO Auto-generated method stub 
//	return clientOrganisations;
//}
//
//public void setClientOrganisations(List<ClientAssociation> clientOrganisations) {
//	// TODO Auto-generated method stub
//	this.clientOrganisations = clientOrganisations;
//}
    
//    public Set<ClientAssociation> getClientOrganisations() {
//		return clientOrganisations;
//	}
//
//	public void setClientOrganisations(Set<ClientAssociation> clientOrganisations) {
//		this.clientOrganisations = clientOrganisations;
//	}
    
    

    public Integer getPrimaryOrgId() {
    	return primaryOrgId;
    }
    
    public void setPrimaryOrgId(Integer primaryOrgId) {
    	this.primaryOrgId = primaryOrgId;
    }
    
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
		return "ClientUser [clientId=" + clientId + ", email=" + email + ", firstName=" + firstName + ", LastName="
				+ LastName + ", legalName=" + legalName + ", city=" + city + ", phone=" + phone + ", countryCode="
				+ countryCode + ", userType=" + userType + ", primaryOrgId=" + primaryOrgId + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}
	

}
