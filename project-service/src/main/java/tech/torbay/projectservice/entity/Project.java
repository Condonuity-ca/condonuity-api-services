package tech.torbay.projectservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "projects")
public class Project {

    public Project() {

    }
 
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectCompletionDeadline() {
		return projectCompletionDeadline;
	}
	public void setProjectCompletionDeadline(String projectCompletionDeadline) {
		this.projectCompletionDeadline = projectCompletionDeadline;
	}
	public String getEstimatedBudget() {
		return estimatedBudget;
	}
	public void setEstimatedBudget(String estimatedBudget) {
		this.estimatedBudget = estimatedBudget;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpecialConditions() {
		return specialConditions;
	}
	public void setSpecialConditions(String specialConditions) {
		this.specialConditions = specialConditions;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getContractType() {
		return contractType;
	}
	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}
	public Integer getInsuranceRequired() {
		return insuranceRequired;
	}
	public void setInsuranceRequired(Integer insuranceRequired) {
		this.insuranceRequired = insuranceRequired;
	}
	public Integer getPostType() {
		return postType;
	}
	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Id
	private Integer projectId = 0;
	private Integer clientOrganisationId = 0;
	private Integer clientId = 0;
    private String projectName = "";
    private Integer projectModifiedBy = 0;
    private String tags = "";
    private String bidEndDate = "";
    private String projectStartDate = "";
    private String projectCompletionDeadline = "";
    private String estimatedBudget = "";
    private String duration = "";
    private String description = "";
    private String specialConditions = "";
    private String city = "";
    private Integer contractType = 0;
    private Integer insuranceRequired = 0;
    private Integer postType = 0;
    private Integer status = 0;
    private Integer awardedBidId = 0;
	private String createdAt = "";
    private String modifiedDate = "";
    
    public Integer getClientOrganisationId() {
		return clientOrganisationId;
	}

	public void setClientOrganisationId(Integer clientOrganisationId) {
		this.clientOrganisationId = clientOrganisationId;
	}

	public Integer getProjectModifiedBy() {
		return projectModifiedBy;
	}

	public void setProjectModifiedBy(Integer projectModifiedBy) {
		this.projectModifiedBy = projectModifiedBy;
	}

	public Integer getAwardedBidId() {
		return awardedBidId;
	}

	public void setAwardedBidId(Integer awardedBidId) {
		this.awardedBidId = awardedBidId;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", clientId=" + clientId + ", clientOrganisationId=" + clientOrganisationId + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + ", projectName=" + projectName + ", tags=" + tags + ", bidEndDate="
				+ bidEndDate + ", projectStartDate=" + projectStartDate + ", projectCompletionDeadline="
				+ projectCompletionDeadline + ", estimatedBudget=" + estimatedBudget + ", duration=" + duration
				+ ", description=" + description + ", specialConditions=" + specialConditions + ", city=" + city
				+ ", contractType=" + contractType + ", insuranceRequired=" + insuranceRequired + ", postType="
				+ postType + ", status=" + status + ", awardedBidId=" + awardedBidId + ", projectModifiedBy=" + projectModifiedBy + "]";
	}
    

    
}