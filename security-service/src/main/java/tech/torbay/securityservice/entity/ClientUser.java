package tech.torbay.securityservice.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import tech.torbay.securityservice.constants.Constants;

@Entity
@Table(name="client_user")
public class ClientUser {


    @Id
    private Integer clientId = 0;
    private String email = "";
    private String firstName = "";
    private String LastName = "";
    private String legalName = "";
    private String city = "";
    private String phone = "";
    private String countryCode;
    private Integer userType = Constants.UserType.CLIENT.getValue();
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//    		name = "client_association", joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id"), 
//    		inverseJoinColumns = @JoinColumn(name = "client_organisation_id", referencedColumnName = "client_organisation_id"))
//    private Set<ClientOrganisation> clientOrganisations;
//    
//    
//    public Set<ClientOrganisation> getClientOrganisations() {
//		return clientOrganisations;
//	}
//	public void setClientOrganisations(Set<ClientOrganisation> clientOrganisations) {
//		this.clientOrganisations = clientOrganisations;
//	}
    
//    @OneToMany(mappedBy = "clientUser", cascade = CascadeType.ALL)
//    private Set<ClientAssociation> clientAssociations;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "client_association",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "client_organisation_id")}
    )
    private Set<ClientOrganisation> clientOrganisations = new HashSet<>();
    
    public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getClientId() {
		return clientId;
	}
//	public Set<ClientOrganisation> getClientOrganisations() {
//		return clientOrganisations;
//	}
//	public void setClientOrganisation(Set<ClientOrganisation> clientOrganisations) {
//		this.clientOrganisations = clientOrganisations;
//	}
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
				+ countryCode + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate /*
								 * + ", clientOrganisations=" + clientOrganisations
								 */+ "]";
	}
	
	

	
}
