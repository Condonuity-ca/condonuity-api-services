package tech.torbay.projectservice.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VendorBid {
	
	public VendorBid() {

    }
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bidId = 0;
    private Integer projectId = 0;
    private Integer vendorId = 0;
    private Integer clientId = 0;
    private String vendorStartDate;
    private String vendorCompletionDate;
    private String vendorProjectDuration;
    private String inScope;
	private String outOfScope;
    private String preRequisite;
    private String reasonForChoose;
    private String bidPrice;
    private int bidStatus;

    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
//    private List<BiddingProducts> biddingProducts;
    
    
    @Override
	public String toString() {
		return "VendorBid [Id=" + bidId + ", projectId=" + projectId + ", vendorId=" + vendorId + ", clientId=" + clientId
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + ", vendorStartDate="
				+ vendorStartDate + ", vendorCompletionDate=" + vendorCompletionDate + ", vendorProjectDuration="
				+ vendorProjectDuration + ", inScope=" + inScope + ", outOfScope=" + outOfScope + ", preRequisite="
				+ preRequisite + ", reasonForChoose=" + reasonForChoose + ", bidPrice=" + bidPrice
				+ ", bidsStatus=" + bidStatus + "]";
	}
    
	public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer id) {
		bidId = id;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
	public String getVendorStartDate() {
		return vendorStartDate;
	}
	public void setVendorStartDate(String vendorStartDate) {
		this.vendorStartDate = vendorStartDate;
	}
	public String getVendorCompletionDate() {
		return vendorCompletionDate;
	}
	public void setVendorCompletionDate(String vendorCompletionDate) {
		this.vendorCompletionDate = vendorCompletionDate;
	}
	public String getVendorProjectDuration() {
		return vendorProjectDuration;
	}
	public void setVendorProjectDuration(String vendorProjectDuration) {
		this.vendorProjectDuration = vendorProjectDuration;
	}
	public String getInScope() {
		return inScope;
	}
	public void setInScope(String inScope) {
		this.inScope = inScope;
	}
	public String getOutOfScope() {
		return outOfScope;
	}
	public void setOutOfScope(String outOfScope) {
		this.outOfScope = outOfScope;
	}
	public String getPreRequisite() {
		return preRequisite;
	}
	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}
	public String getReasonForChoose() {
		return reasonForChoose;
	}
	public void setReasonForChoose(String reasonForChoose) {
		this.reasonForChoose = reasonForChoose;
	}
	public String getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}
	public int getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(int bidStatus) {
		this.bidStatus = bidStatus;
	}
    
}
