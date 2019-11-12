package tech.torbay.userservice.entity;

import javax.persistence.*;

import tech.torbay.userservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "vendor")
public class Vendor {

    public Vendor() {

    }
    @Id
	private Integer vendorId = 0;
    private Integer userType = Constants.UserType.VENDOR.getValue();
    private String legalName = "";
    private String companyName = "";
    private String establishedDate = "";
    private String searchTerms = "";
    private Integer employeesCount = 0;
	private String annualRevenue = "";
    private String description = "";
    private String contactPerson ="";
    private String contactPersonEmail ="";
    private String contactPersonPhone ="";
    private String email = "";
    private String adminEmail = "";
	private String phoneNumber = "";
    private String faxNumber = "";
    private String website = "";
    private String expertiseCategory = "";
    private String logoName = "";
    private Integer rating = 0;
    private String address = "";
    private String city = "";
    private String province = "";
    private String postalCode = "";
    private Integer countryCode = 0;
	private String createdDate = "";
    
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
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(String establishedDate) {
		this.establishedDate = establishedDate;
	}
	public String getSearchTerms() {
		return searchTerms;
	}
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	public Integer getEmployeesCount() {
		return employeesCount;
	}
	public void setEmployeesCount(Integer employeesCount) {
		this.employeesCount = employeesCount;
	}
	public String getAnnualRevenue() {
		return annualRevenue;
	}
	public void setAnnualRevenue(String annualRevenue) {
		this.annualRevenue = annualRevenue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
	public String getContactPersonPhone() {
		return contactPersonPhone;
	}
	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getExpertiseCategory() {
		return expertiseCategory;
	}
	public void setExpertiseCategory(String expertiseCategory) {
		this.expertiseCategory = expertiseCategory;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
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
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
    
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", userType=" + userType + ", legalName=" + legalName + ", companyName="
				+ companyName + ", establishedDate=" + establishedDate + ", searchTerms=" + searchTerms
				+ ", employeesCount=" + employeesCount + ", annualRevenue=" + annualRevenue + ", description="
				+ description + ", contactPerson=" + contactPerson + ", contactPersonEmail=" + contactPersonEmail
				+ ", contactPersonPhone=" + contactPersonPhone + ", email=" + email + ", adminEmail=" + adminEmail
				+ ", phoneNumber=" + phoneNumber + ", faxNumber=" + faxNumber + ", website=" + website
				+ ", expertiseCategory=" + expertiseCategory + ", logoName=" + logoName + ", rating=" + rating
				+ ", address=" + address + ", city=" + city + ", province=" + province + ", postalCode=" + postalCode
				+ ", countryCode=" + countryCode + ", createdDate=" + createdDate + "]";
	}

}