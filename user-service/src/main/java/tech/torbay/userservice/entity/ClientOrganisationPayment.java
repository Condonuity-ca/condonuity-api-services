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
	private int cardNumber;
	private int nameOnCard;
	private int expiryDate;
	private int securityCode;
	private int streetAddress;
	private int city;
	private int province;
	private int postalCode;
	private int createdAt;
	private int modifiedDate;
	
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
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(int nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public int getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(int expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
	public int getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(int streetAddress) {
		this.streetAddress = streetAddress;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	public int getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(int modifiedDate) {
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
