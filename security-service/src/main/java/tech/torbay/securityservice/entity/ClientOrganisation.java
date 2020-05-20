package tech.torbay.securityservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.DeleteStatus;

@Entity
@Table(name = "client_organisation")
public class ClientOrganisation {

    public ClientOrganisation() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Integer units = 0;
    private Integer votingUnits = 0;
    private String managerName = "";
    private String managerEmail = "";
    private String managerPhone = "";
    private Integer activeStatus = DeleteStatus.NOT_AVAILABLE.getValue();
    private Integer deleteStatus = DeleteStatus.NOT_AVAILABLE.getValue();
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
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

	@Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientOrganisation", targetEntity=ClientAmenities.class, orphanRemoval = true)
//	private List<ClientAmenities> amenities = new ArrayList<>();
    
	@OneToMany(/* mappedBy = "clientOrganisation", */ targetEntity = ClientAmenities.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE,  orphanRemoval = true)
   	@JoinColumn(name = "client_organisation_id")
   	private List<ClientAmenities> clientAmenities = new ArrayList<>();
//    
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientOrganisation", targetEntity=ClientAssociation.class, orphanRemoval = true)
//	private List<ClientAssociation> clientAssociations = new ArrayList<>();
    
//    @OneToMany(mappedBy = "clientOrganisation", cascade = CascadeType.ALL)
//	private Set<ClientAssociation> clientAssociations;
    
//    @ManyToMany(mappedBy = "clientOrganisations", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private Set<ClientUser> clientUsers = new HashSet<>();

    
	public String getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	public List<ClientAmenities> getClientAmenities() {
		return clientAmenities;
	}


	public void setClientAmenities(List<ClientAmenities> clientAmenities) {
		this.clientAmenities = clientAmenities;
	}


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
				+ ", clientAmenities=" + clientAmenities + "]";
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
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public Integer getVotingUnits() {
		return votingUnits;
	}
	public void setVotingUnits(Integer votingUnits) {
		this.votingUnits = votingUnits;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}	
}