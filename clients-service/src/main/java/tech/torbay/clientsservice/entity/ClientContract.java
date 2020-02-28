package tech.torbay.clientsservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "client_contract")
public class ClientContract {
	
	public ClientContract() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int clientOrganisationId;
	private int clientId;
	private String vendorName = "";
	private String services = ""; //store comma separated values into db return the same
	private int term = 0; 
	private int termUnits = 0;//1,2,3
	private String signedDate = "";
	private String expiryDate = "";
	private int renewalType = 0;//1,2
	private String cost = "";
	private int costTermUnits = 0;//1,2,3
	private int gstAvailablity = 0;//1,2
	private String cancellationTerm = "";
	private int cancellationUnits = 0;
	private String expectedIncrease = "";
	private String notes = "";
	private int status = 0;
	
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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getTermUnits() {
		return termUnits;
	}

	public void setTermUnits(int termUntis) {
		this.termUnits = termUntis;
	}

	public String getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(String signedDate) {
		this.signedDate = signedDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getRenewalType() {
		return renewalType;
	}

	public void setRenewalType(int renewalType) {
		this.renewalType = renewalType;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getCostTermUnits() {
		return costTermUnits;
	}

	public void setCostTermUnits(int costTermUnits) {
		this.costTermUnits = costTermUnits;
	}

	public int getGstAvailablity() {
		return gstAvailablity;
	}

	public void setGstAvailablity(int gstAvailablity) {
		this.gstAvailablity = gstAvailablity;
	}

	public String getCancellationTerm() {
		return cancellationTerm;
	}

	public void setCancellationTerm(String cancellationTerm) {
		this.cancellationTerm = cancellationTerm;
	}

	public int getCancellationUnits() {
		return cancellationUnits;
	}

	public void setCancellationUnits(int cancellationUnits) {
		this.cancellationUnits = cancellationUnits;
	}

	public String getExpectedIncrease() {
		return expectedIncrease;
	}

	public void setExpectedIncrease(String expectedIncrease) {
		this.expectedIncrease = expectedIncrease;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		return "ClientContract [id=" + id + ", clientOrganisationId=" + clientOrganisationId + ", clientId=" + clientId
				+ ", vendorName=" + vendorName + ", services=" + services + ", term=" + term + ", termUntis="
				+ termUnits + ", signedDate=" + signedDate + ", expiryDate=" + expiryDate + ", renewalType="
				+ renewalType + ", cost=" + cost + ", costTermUnits=" + costTermUnits + ", gstAvailablity="
				+ gstAvailablity + ", cancellationTerm=" + cancellationTerm + ", cancellationUnits=" + cancellationUnits
				+ ", expectedIncrease=" + expectedIncrease + ", notes=" + notes + ", status=" + status + ", createdAt="
				+ createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

}
