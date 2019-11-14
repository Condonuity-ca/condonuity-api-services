package tech.torbay.userservice.entity;

import javax.persistence.*;

import tech.torbay.userservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "vendor_organisation")
public class VendorOrganisation {

    public VendorOrganisation() {

    }
    @Id
	private Integer vendorOrganisationId = 0;
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
	private String phoneNumber = "";
    private String faxNumber = "";
    private String website = "";
    private String expertiseCategory = "";
    private String logoName = "";
    private String address = "";
    private String city = "";
    private String province = "";
    private String postalCode = "";
    private String countryCode = "";
    private String vendorServicesCities = "";
    private String vendorServices = "";
    private String products = "";
    private String brands = "";
    private String licenses = "";
    private String memberships = "";
	private String createdAt = "";
	private String modifiedDate = "";
	
	
    @Override
	public String toString() {
		return "VendorOrganisation [vendorOrganisationId=" + vendorOrganisationId + ", userType=" + userType
				+ ", legalName=" + legalName + ", companyName=" + companyName + ", establishedDate=" + establishedDate
				+ ", searchTerms=" + searchTerms + ", employeesCount=" + employeesCount + ", annualRevenue="
				+ annualRevenue + ", description=" + description + ", contactPerson=" + contactPerson
				+ ", contactPersonEmail=" + contactPersonEmail + ", contactPersonPhone=" + contactPersonPhone
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", faxNumber=" + faxNumber + ", website="
				+ website + ", expertiseCategory=" + expertiseCategory + ", logoName=" + logoName + ", address="
				+ address + ", city=" + city + ", province=" + province + ", postalCode=" + postalCode
				+ ", countryCode=" + countryCode + ", vendorServicesCities=" + vendorServicesCities
				+ ", vendorServices=" + vendorServices + ", products=" + products + ", brands=" + brands + ", licenses="
				+ licenses + ", memberships=" + memberships + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}
    
	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}
	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
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
	public String getVendorServicesCities() {
		return vendorServicesCities;
	}
	public void setVendorServicesCities(String vendorServicesCities) {
		this.vendorServicesCities = vendorServicesCities;
	}
	public String getVendorServices() {
		return vendorServices;
	}
	public void setVendorServices(String vendorServices) {
		this.vendorServices = vendorServices;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getLicenses() {
		return licenses;
	}
	public void setLicenses(String licenses) {
		this.licenses = licenses;
	}
	public String getMemberships() {
		return memberships;
	}
	public void setMemberships(String memberships) {
		this.memberships = memberships;
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
	
}