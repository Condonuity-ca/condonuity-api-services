package tech.torbay.projectservice.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import tech.torbay.projectservice.constants.Constants;

@Entity
@Table(name = "bids")

@SqlResultSetMappings({
	  @SqlResultSetMapping(
	      name="vendorCurrentProjects",
	      entities={
	    		  @EntityResult(entityClass=VendorBid.class),
	    		  @EntityResult(entityClass=Project.class)
	    		  },
	      columns={
	    		  @ColumnResult(name="management_company"),
	    		  @ColumnResult(name="organisation_name"),
	    		  @ColumnResult(name="city")
	    		  }
	  ),
	  @SqlResultSetMapping(
		      name="vendorHistoryProjects",
		      entities={
		    		  @EntityResult(entityClass=VendorBid.class),
		    		  @EntityResult(entityClass=Project.class)
		    		  },
		      columns={
		    		  @ColumnResult(name="management_company"),
		    		  @ColumnResult(name="organisation_name"),
		    		  @ColumnResult(name="city")
		    		  }
		  )
	})
@NamedNativeQuery(
	    name="VendorBid.CurrentProject", 
	    query="SELECT vb.*, pro.*, co.management_company, co.organisation_name, co.city FROM condonuitydev.bids vb " + 
	    		"INNER JOIN condonuitydev.projects pro ON pro.project_id = vb.project_id " + 
	    		"INNER JOIN condonuitydev.client_organisation co ON co.client_organisation_id = pro.client_organisation_id " + 
	    		"WHERE vb.vendor_org_id = (?1) AND pro.status = 2 AND pro.delete_status = 1 "/*Constants.ProjectPostType.PUBLISHED.getValue()*/, 
	    resultSetMapping="vendorCurrentProjects")//AND vb.bid_status = 2-handled under logic


@NamedNativeQuery(
	    name="VendorBid.HistoryProject", 
	    query="SELECT vb.*, pro.*, co.management_company, co.organisation_name, co.city FROM condonuitydev.bids vb " + 
	    		"INNER JOIN condonuitydev.projects pro ON pro.project_id = vb.project_id " + 
	    		"INNER JOIN condonuitydev.client_organisation co ON co.client_organisation_id = pro.client_organisation_id " + 
	    		"WHERE vb.vendor_org_id = (?1) AND ( pro.status = 3 OR pro.status = 4 ) AND pro.delete_status = 1", 
	    resultSetMapping="vendorHistoryProjects")

public class VendorBid {
	
	public VendorBid() {

    }
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    private Integer projectId = 0;
    private Integer vendorOrgId = 0;
//    private Integer vendorUserId = 0;
    private String vendorStartDate;
    private String vendorEndDate;
    private String vendorProjectDuration;
    private String inScope;
	private String outOfScope;
	private Integer isInsuranceAvailable;
	private Integer insuranceId;
    private String preRequisite;
    private String reasonForChoose;
    private String bidPrice;
    private Integer bidStatus;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
    
    @OneToMany(targetEntity = BiddingProducts.class, fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "bidding_id")
    private List<BiddingProducts> biddingProducts;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vendorUserId", referencedColumnName = "userId")
    private VendorUser vendorUser;
    
	public VendorUser getVendorUser() {
		return vendorUser;
	}

	public void setVendorUser(VendorUser vendorUser) {
		this.vendorUser = vendorUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getVendorOrgId() {
		return vendorOrgId;
	}

	public void setVendorOrgId(Integer vendorOrgId) {
		this.vendorOrgId = vendorOrgId;
	}

//	public Integer getVendorUserId() {
//		return vendorUserId;
//	}
//
//	public void setVendorUserId(Integer vendorUserId) {
//		this.vendorUserId = vendorUserId;
//	}

	public String getVendorStartDate() {
		return vendorStartDate;
	}

	public void setVendorStartDate(String vendorStartDate) {
		this.vendorStartDate = vendorStartDate;
	}

	public String getVendorEndDate() {
		return vendorEndDate;
	}

	public void setVendorEndDate(String vendorEndDate) {
		this.vendorEndDate = vendorEndDate;
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

	public Integer getIsInsuranceAvailable() {
		return isInsuranceAvailable;
	}

	public void setIsInsuranceAvailable(Integer isInsuranceAvailable) {
		this.isInsuranceAvailable = isInsuranceAvailable;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
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

	public Integer getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(Integer bidStatus) {
		this.bidStatus = bidStatus;
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

	public List<BiddingProducts> getBiddingProducts() {
		return biddingProducts;
	}

	public void setBiddingProducts(List<BiddingProducts> biddingProducts) {
		this.biddingProducts = biddingProducts;
	}

	@Override
	public String toString() {
		return "VendorBid [id=" + id + ", projectId=" + projectId + ", vendorOrgId=" + vendorOrgId + ", vendorUserId="
		/* + vendorUserId */ + ", vendorStartDate=" + vendorStartDate + ", vendorEndDate=" + vendorEndDate
				+ ", vendorProjectDuration=" + vendorProjectDuration + ", inScope=" + inScope + ", outOfScope="
				+ outOfScope + ", isInsuranceAvailable=" + isInsuranceAvailable + ", insuranceId=" + insuranceId
				+ ", preRequisite=" + preRequisite + ", reasonForChoose=" + reasonForChoose + ", bidPrice=" + bidPrice
				+ ", bidStatus=" + bidStatus + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate
				+ ", biddingProducts=" + biddingProducts + "]";
	}
}
