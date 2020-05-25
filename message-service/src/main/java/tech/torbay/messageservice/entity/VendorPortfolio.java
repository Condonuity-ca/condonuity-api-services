package tech.torbay.messageservice.entity;

import javax.persistence.*;

import tech.torbay.messageservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "vendor_portfolio")
public class VendorPortfolio {

    public VendorPortfolio() {

    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id = 0;
	private Integer vendorOrganisationId = 0;
    private String projectName = "";
    private String city = "";
    private String description = "";
    private String clientName = "";
    private String date = "";
    private String duration = "";
    private String cost = "";
    
	@Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getVendorOrganisationId() {
		return vendorOrganisationId;
	}

	public void setVendorOrganisationId(Integer vendorId) {
		this.vendorOrganisationId = vendorId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
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
		return "VendorPortfolio [Id=" + Id + ", vendorOrganisationId=" + vendorOrganisationId + ", projectName=" + projectName + ", city="
				+ city + ", description=" + description + ", clientName=" + clientName + ", date=" + date
				+ ", duration=" + duration + ", cost=" + cost + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}
    
	
}