package tech.torbay.securityservice.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import tech.torbay.securityservice.constants.Constants;
import tech.torbay.securityservice.constants.Constants.AllocationStatus;
import tech.torbay.securityservice.constants.Constants.DeleteStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "available_vendor_profiles")
public class AvailableVendorProfiles {

    public AvailableVendorProfiles() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vendorProfileId = 0;
    private Integer userType = Constants.UserType.VENDOR.getValue();
    private Integer allocatedVendorOrgId = 0;
    private String legalName = "";
    private String companyName = "";
    private String establishedDate = "";
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
    private Integer activeStatus = AllocationStatus.NOT_ALLOCATED.getValue();
   
	
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
    public Integer getVendorProfileId() {
		return vendorProfileId;
	}

	public void setVendorProfileId(Integer vendorProfileId) {
		this.vendorProfileId = vendorProfileId;
	}
	
	public Integer getAllocatedVendorOrgId() {
		return allocatedVendorOrgId;
	}

	public void setAllocatedVendorOrgId(Integer allocatedVendorOrgId) {
		this.allocatedVendorOrgId = allocatedVendorOrgId;
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

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "VendorOrganisation [vendorProfileId=" + vendorProfileId + "allocatedVendorOrgId=" + allocatedVendorOrgId +", userType=" + userType
				+ ", legalName=" + legalName + ", companyName=" + companyName + ", establishedDate=" + establishedDate
				+ ", employeesCount=" + employeesCount + ", annualRevenue=" + annualRevenue + ", description="
				+ description + ", contactPerson=" + contactPerson + ", contactPersonEmail=" + contactPersonEmail
				+ ", contactPersonPhone=" + contactPersonPhone + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", faxNumber=" + faxNumber + ", website=" + website + ", expertiseCategory=" + expertiseCategory
				+ ", logoName=" + logoName + ", address=" + address + ", city=" + city + ", province=" + province
				+ ", postalCode=" + postalCode + ", countryCode=" + countryCode + ", activeStatus=" + activeStatus
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

    
//	@OneToMany(targetEntity = VendorServices.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
//   	@JoinColumn(name = "vendor_organisation_id")
//   	private List<VendorServices> vendorServices = new ArrayList<>();
//	
//	@OneToMany(targetEntity = VendorProducts.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
//   	@JoinColumn(name = "vendor_organisation_id")
//   	private List<VendorProducts> vendorProducts = new ArrayList<>();
//	
//	@OneToMany(targetEntity = VendorBrands.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
//   	@JoinColumn(name = "vendor_organisation_id")
//   	private List<VendorBrands> vendorBrands = new ArrayList<>();
//	
//	@OneToMany(targetEntity = VendorLicenses.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
//   	@JoinColumn(name = "vendor_organisation_id")
//   	private List<VendorLicenses> vendorLicenses = new ArrayList<>();
//	
//	@OneToMany(targetEntity = VendorMemberships.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
//   	@JoinColumn(name = "vendor_organisation_id")
//   	private List<VendorMemberships> vendormemberships = new ArrayList<>();
	
	
}