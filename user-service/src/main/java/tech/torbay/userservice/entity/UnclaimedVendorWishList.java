package tech.torbay.userservice.entity;

import javax.persistence.*;

import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.constants.Constants.ProjectInterestStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "unclaimed_vendor_wish_list")
public class UnclaimedVendorWishList {

    public UnclaimedVendorWishList() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer clientOrgId = 0;
	private Integer clientUserId = 0;
	private Integer vendorProfileId = 0;
	private Integer interestStatus = ProjectInterestStatus.UN_LIKE.getValue();
    
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getClientOrgId() {
		return clientOrgId;
	}

	public void setClientOrgId(Integer clientOrgId) {
		this.clientOrgId = clientOrgId;
	}

	public Integer getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(Integer clientUserId) {
		this.clientUserId = clientUserId;
	}

	public Integer getVendorProfileId() {
		return vendorProfileId;
	}

	public void setVendorProfileId(Integer vendorProfileId) {
		this.vendorProfileId = vendorProfileId;
	}

	public Integer getInterestStatus() {
		return interestStatus;
	}

	public void setInterestStatus(Integer interestStatus) {
		this.interestStatus = interestStatus;
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
		return "UnclaimedVendorWishList [id=" + id + ", clientOrgId=" + clientOrgId + ", clientUserId=" + clientUserId
				+ ", vendorProfileId=" + vendorProfileId + ", interestStatus=" + interestStatus + ", createdAt="
				+ createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
}