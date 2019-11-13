package tech.torbay.userservice.entity;

import javax.persistence.*;

import tech.torbay.userservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "organisation")
public class ClientOrganisation {

    public ClientOrganisation() {

    }
    
    @Id
    private Integer organisationId = 0;
    private Integer userType = Constants.UserType.CLIENT.getValue();
    private Integer userRole = Constants.UserRole.NOT_AVAILABLE.getValue();
	private String organisationName = "";
    private String managementCompany = "";
    private String corporateNumber = "";
    private String registrationDate = "";
    private String adminEmail = "";
    private String generalEmail = "";
    private String managementEmail = "";
    private String boardEmail = "";
    private String address = "";
    private String city = "";
    private String province = "";
    private String postalCode = "";
    private int countryCode = 0;
    private String phoneNumber = "";
    private String faxNumber = "";
    private int units = 0;
    private int votingUnits = 0;
    private String managerName = "";
    private String managerEmail = "";
    private String managerPhone = "";
    private String createdAt = "";
//    private ClientAmenities amenities;
    
	@Override
	public String toString() {
		return "Organisation [organisationId=" + organisationId + ", userType=" + userType + ", userRole=" + userRole
				+ ", organisationName=" + organisationName + ", managementCompany=" + managementCompany
				+ ", corporateNumber=" + corporateNumber + ", registrationDate=" + registrationDate + ", adminEmail="
				+ adminEmail + ", generalEmail=" + generalEmail + ", managementEmail=" + managementEmail
				+ ", boardEmail=" + boardEmail + ", address=" + address + ", city=" + city + ", province=" + province
				+ ", postalCode=" + postalCode + ", countryCode=" + countryCode + ", phoneNumber=" + phoneNumber
				+ ", faxNumber=" + faxNumber + ", units=" + units + ", votingUnits=" + votingUnits + ", managerName="
				+ managerName + ", managerEmail=" + managerEmail + ", managerPhone=" + managerPhone + ", createdAt="
				+ createdAt /* + ", amenities=" + amenities */+ "]";
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
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
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
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
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
}