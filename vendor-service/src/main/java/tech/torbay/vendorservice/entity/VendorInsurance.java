package tech.torbay.vendorservice.entity;

import javax.persistence.*;

import tech.torbay.vendorservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "insurance")
public class VendorInsurance {

    public VendorInsurance() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insuranceId = 0;
    private Integer vendorOrganisationId = 0;
    private Integer insuranceAvailability = 0;
    private String insuranceCompany = "";
    private String insuranceLiability = "";
    private String insurancePolicyExpiryDate = "";
    private Integer insuranceBonded = 0;
    private String insuranceNumber = "";
    
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorOrganisationId) {
		this.vendorOrganisationId = vendorOrganisationId;
	}

	public Integer getInsuranceAvailability() {
		return insuranceAvailability;
	}

	public void setInsuranceAvailability(Integer insuranceAvailability) {
		this.insuranceAvailability = insuranceAvailability;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getInsuranceLiability() {
		return insuranceLiability;
	}

	public void setInsuranceLiability(String insuranceLiability) {
		this.insuranceLiability = insuranceLiability;
	}

	public String getInsurancePolicyExpiryDate() {
		return insurancePolicyExpiryDate;
	}

	public void setInsurancePolicyExpiryDate(String insurancePolicyExpiryDate) {
		this.insurancePolicyExpiryDate = insurancePolicyExpiryDate;
	}

	public Integer getInsuranceBonded() {
		return insuranceBonded;
	}

	public void setInsuranceBonded(Integer insuranceBonded) {
		this.insuranceBonded = insuranceBonded;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
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
		return "VendorInsurance [insuranceId=" + insuranceId + ", vendorOrganisationId=" + vendorOrganisationId
				+ ", insuranceAvailability=" + insuranceAvailability + ", insuranceCompany=" + insuranceCompany
				+ ", insuranceLiability=" + insuranceLiability + ", insurancePolicyExpiryDate="
				+ insurancePolicyExpiryDate + ", insuranceBonded=" + insuranceBonded + ", insuranceNumber="
				+ insuranceNumber + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
	
}