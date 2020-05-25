package tech.torbay.messageservice.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.JoinColumn;

import tech.torbay.messageservice.constants.Constants;
import tech.torbay.messageservice.constants.Constants.DeleteStatus;

@Entity(name = "ClientOrganisation")
@Table(name = "client_organisation")
public class ClientOrganisation {

	public ClientOrganisation() {
	}
	
    public ClientOrganisation(String corporateNumber) {
    	this.corporateNumber = corporateNumber;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_organisation_id")
    private Integer clientOrganisationId = 0;
    private Integer userType = Constants.UserType.CLIENT.getValue();
	private String organisationName = "";
    private String managementCompany = "";
    private String corporateNumber = "";
    private String registrationDate = "";
//    private String adminEmail = "";
    private String generalEmail = "";
    private String managementEmail = "";
    private String boardEmail = "";
    private String address = "";
    private String city = "";
    private String province = "";
    private String postalCode = "";
    private String countryCode = "";
    private String phoneNumber = "";
    private String faxNumber = "";
    private int units = 0;
    private int votingUnits = 0;
    private String managerName = "";
    private String managerEmail = "";
    private String managerPhone = "";
    private Integer activeStatus = DeleteStatus.NOT_AVAILABLE.getValue();
    private Integer deleteStatus = DeleteStatus.NOT_AVAILABLE.getValue();
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
	public String getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@OneToMany(
	        mappedBy = "clientOrganisation",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    private List<ClientAssociation> clientUsers = new ArrayList<>();
	
//	@OneToMany(mappedBy = "clientOrganisation")
//    private Set<ClientAssociation> clientUsers = new HashSet<ClientAssociation>();
	
	
	//Getters and setters omitted for brevity
	 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrganisation clientOrg = (ClientOrganisation) o;
        return Objects.equals(clientOrganisationId, clientOrg.clientOrganisationId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(clientOrganisationId);
    }
    
//	public void addClientUser(ClientUser clientUser) {
//	  ClientAssociation clientAssociation = new ClientAssociation(this, clientUser);
//	  clientUsers.add(clientAssociation);
//	  clientUser.getClientOrganisations().add(clientAssociation);
//	}
//	
//	public void removeClientUser(ClientUser clientUser) {
//	  for (Iterator<ClientAssociation> iterator = clientUsers.iterator();
//	       iterator.hasNext(); ) {
//		  ClientAssociation clientAssociation = iterator.next();
//	
//	      if (clientAssociation.getClientOrganisation().equals(this) &&
//	    		  clientAssociation.getClientUser().equals(clientUser)) {
//	          iterator.remove();
//	          clientAssociation.getClientUser().getClientOrganisations().remove(clientAssociation);
//	          clientAssociation.setClientOrganisation(null);
//	          clientAssociation.setClientUser(null);
//	      }
//	  }
//	}

//	public Set<ClientAssociation> getClientUsers() {
//		return clientUsers;
//	}
//
//	public void setClientUsers(Set<ClientAssociation> clientUsers) {
//		this.clientUsers = clientUsers;
//	}

	@Override
	public String toString() {
		return "ClientOrganisation [clientOrganisationId=" + clientOrganisationId + ", userType=" + userType
				+ ", organisationName=" + organisationName + ", managementCompany=" + managementCompany
				+ ", corporateNumber=" + corporateNumber + ", registrationDate=" + registrationDate + ", generalEmail="
				+ generalEmail + ", managementEmail=" + managementEmail + ", boardEmail=" + boardEmail + ", address="
				+ address + ", city=" + city + ", province=" + province + ", postalCode=" + postalCode
				+ ", countryCode=" + countryCode + ", phoneNumber=" + phoneNumber + ", faxNumber=" + faxNumber
				+ ", units=" + units + ", votingUnits=" + votingUnits + ", managerName=" + managerName
				+ ", managerEmail=" + managerEmail + ", managerPhone=" + managerPhone + ", activeStatus=" + activeStatus
				+ ", deleteStatus=" + deleteStatus + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate
				+ ", clientUsers=" + clientUsers + "]";
	}
	
	
	
	public String getGeneralEmail() {
		return generalEmail;
	}


	public void setGeneralEmail(String generalEmail) {
		this.generalEmail = generalEmail;
	}


	public String getManagementEmail() {
		return managementEmail;
	}


	public void setManagementEmail(String managementEmail) {
		this.managementEmail = managementEmail;
	}


	public String getBoardEmail() {
		return boardEmail;
	}


	public void setBoardEmail(String boardEmail) {
		this.boardEmail = boardEmail;
	}


	public String getManagerName() {
		return managerName;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public String getManagerEmail() {
		return managerEmail;
	}


	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}


	public String getManagerPhone() {
		return managerPhone;
	}


	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}


	/*
	 * public ClientAmenities getAmenities() { return amenities; } public void
	 * setAmenities(ClientAmenities amenities) { this.amenities = amenities; }
	 */
	public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}
	public void setClientOrganisationId(Integer organisationId) {
		this.clientOrganisationId = organisationId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getManagementCompany() {
		return managementCompany;
	}
	public void setManagementCompany(String managementCompany) {
		this.managementCompany = managementCompany;
	}
	public String getCorporateNumber() {
		return corporateNumber;
	}
	public void setCorporateNumber(String corporateNumber) {
		this.corporateNumber = corporateNumber;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public int getVotingUnits() {
		return votingUnits;
	}
	public void setVotingUnits(int votingUnits) {
		this.votingUnits = votingUnits;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}	
	
}