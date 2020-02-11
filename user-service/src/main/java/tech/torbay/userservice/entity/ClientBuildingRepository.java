package tech.torbay.userservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_building_repository")
public class ClientBuildingRepository {
	
	public ClientBuildingRepository() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int clientOrganisationId;
	private int createdBy;// clientUserId
	private String unitNumber;
	private int unitType;
	private int tenantStatus;
	private int lienType;
	private String dateOfLien;
	private String firstName;
	private String lastName;
	private int personTenantType;
	private String comments;
	private String contactNumber;
	private String contactEmail;
	private String vehicleMode;
	private String color;
	private String licenseNumber;
	private String petDescription;
	private String petName;
	private String emergencyContactName;
	private String emergencyContactNumber;
	private String emergencyContactEmail;
	private String handicapInfo;
	private int modifiedBy;
	private int status;
		
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientOrganisationId() {
		return clientOrganisationId;
	}

	public void setClientOrganisationId(int clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public int getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(int tenantStatus) {
		this.tenantStatus = tenantStatus;
	}

	public int getLienType() {
		return lienType;
	}

	public void setLienType(int lienType) {
		this.lienType = lienType;
	}

	public String getDateOfLien() {
		return dateOfLien;
	}

	public void setDateOfLien(String dateOfLien) {
		this.dateOfLien = dateOfLien;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPersonTenantType() {
		return personTenantType;
	}

	public void setPersonTenantType(int personTenantType) {
		this.personTenantType = personTenantType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getVehicleMode() {
		return vehicleMode;
	}

	public void setVehicleMode(String vehicleMode) {
		this.vehicleMode = vehicleMode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getPetDescription() {
		return petDescription;
	}

	public void setPetDescription(String petDescription) {
		this.petDescription = petDescription;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}

	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}

	public String getHandicapInfo() {
		return handicapInfo;
	}

	public void setHandicapInfo(String handicapInfo) {
		this.handicapInfo = handicapInfo;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
		return "ClientBuildingRepository [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", createdBy="
				+ createdBy + ", unitNumber=" + unitNumber + ", unitType=" + unitType + ", tenantStatus=" + tenantStatus
				+ ", lienType=" + lienType + ", dateOfLien=" + dateOfLien + ", firstName=" + firstName + ", lastName="
				+ lastName + ", personTenantType=" + personTenantType + ", comments=" + comments + ", contactNumber="
				+ contactNumber + ", contactEmail=" + contactEmail + ", vehicleMode=" + vehicleMode + ", color=" + color
				+ ", licenseNumber=" + licenseNumber + ", petDescription=" + petDescription + ", petName=" + petName
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactNumber="
				+ emergencyContactNumber + ", emergencyContactEmail=" + emergencyContactEmail + ", handicapInfo="
				+ handicapInfo + ", modifiedBy=" + modifiedBy + ", status=" + status + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
