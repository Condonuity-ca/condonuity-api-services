package tech.torbay.projectservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bid_results_views_history")
public class BidResultsViewsHistory {


	public BidResultsViewsHistory() {
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = 0;
	
	private Integer clientId = 0;
    private Integer clientOrganisationId = 0;
    private Integer projectId = 0;
    private Integer bidId = 0;

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

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}

	public void setClientOrganisationId(Integer clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
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
		return "BidResultsViewsHistory [id=" + id + ", clientId=" + clientId 
				+ ", clientOrganisationId=" + clientOrganisationId + ", projectId=" + projectId + ", bidId=" + bidId
				+ ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
}
