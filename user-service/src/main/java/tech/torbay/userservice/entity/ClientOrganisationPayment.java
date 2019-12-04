package tech.torbay.userservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "organisation_payment_billing_details")
public class ClientOrganisationPayment {
	
	public ClientOrganisationPayment() {
	
	}
	@Id
	private int id;
	private int organisationId;
	private String cardNumber = "NA";
	private String nameOnCard = "NA";
	private String expiryDate = "NA";
	private String securityCode = "NA";
	private String streetAddress = "NA";
	private String city = "NA";
	private String province = "NA";
	private String postalCode = "NA";
	private String createdAt = "NA";
	private String modifiedDate = "NA";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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
		return "OrganisationPayment [id=" + id + ", organisationId=" + organisationId + ", cardNumber=" + cardNumber
				+ ", nameOnCard=" + nameOnCard + ", expiryDate=" + expiryDate + ", securityCode=" + securityCode
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", province=" + province + ", postalCode="
				+ postalCode + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
}
