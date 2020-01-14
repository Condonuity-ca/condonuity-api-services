package tech.torbay.userservice.entity;

import java.util.ArrayList;
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
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@JsonInclude(value = Include.NON_NULL)
@Table(name = "projects")

@SqlResultSetMappings({
	  @SqlResultSetMapping(
	      name="marketPlace",
	      entities={
	    		  @EntityResult(entityClass=Project.class)
	    		  },
	      columns={
	    		  @ColumnResult(name="management_company"),
	    		  @ColumnResult(name="first_name"),
	    		  @ColumnResult(name="last_name")
	    		  }
	  )
	})
@NamedNativeQuery(
	    name="Project.MarketPlace", 
	    query="SELECT pro.*, co.management_company, cu.first_name, cu.last_name FROM condonuitydev.projects pro " + 
	    		"INNER JOIN condonuitydev.client_organisation co ON co.client_organisation_id = pro.client_organisation_id " + 
	    		"INNER JOIN condonuitydev.client_user cu ON cu.client_id = pro.client_id WHERE pro.status = 2;", 
	    resultSetMapping="marketPlace")
public class Project {

    public Project() {

    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId = 0;
    @NotNull
	private Integer clientOrganisationId = 0;
    @NotNull
	private Integer clientId = 0;
	@NotNull
	@Size(min=2, message="Project Name should have atleast 2 characters")
    private String projectName = "";
    private Integer projectModifiedBy = 0;
    private String tags = "";
    @NotNull
    private String bidEndDate = "";
    @NotNull
    private String projectStartDate = "";
    @NotNull
    private String projectCompletionDeadline = "";
    @NotEmpty(message = "Project Estimation Budget must not be empty")
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
	
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;
 
    @OneToMany(/* mappedBy = "project", */targetEntity = ProjectProducts.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
	@JoinColumn(name = "project_id")
	private List<ProjectProducts> projectProducts = new ArrayList<>();
    
//	@OneToMany(/* mappedBy = "project", */targetEntity = ProjectTags.class, cascade = CascadeType.ALL,  orphanRemoval = true)
//	@JoinColumn(name = "project_id")
//	private List<ProjectTags> projectTags = new ArrayList<>();
//	
//    public List<ProjectTags> getProjectTags() {
//		return projectTags;
//	}
//
//	public void setProjectTags(List<ProjectTags> projectTags) {
//		this.projectTags = projectTags;
//	}
    
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

	public List<ProjectProducts> getProjectProducts() {
		return projectProducts;
	}

	public void setProjectProducts(List<ProjectProducts> projectProducts) {
		this.projectProducts = projectProducts;
	}

//	public void addProjectProducts(ProjectProducts projectProduct) {
//		projectProducts.add(projectProduct);
//		projectProduct.setProject(this);
//    }
//
//    public void removeProjectProducts(ProjectProducts projectProduct) {
//    	projectProducts.remove(projectProduct);
//    	projectProduct.setProject(null);
//    }
    
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
		return "Project [projectId=" + projectId + ", clientOrganisationId=" + clientOrganisationId + ", clientId="
				+ clientId + ", projectName=" + projectName + ", projectModifiedBy=" + projectModifiedBy + ", tags="
				+ tags + ", bidEndDate=" + bidEndDate + ", projectStartDate=" + projectStartDate
				+ ", projectCompletionDeadline=" + projectCompletionDeadline + ", estimatedBudget=" + estimatedBudget
				+ ", duration=" + duration + ", description=" + description + ", specialConditions=" + specialConditions
				+ ", city=" + city + ", contractType=" + contractType + ", insuranceRequired=" + insuranceRequired
				+ ", postType=" + postType + ", status=" + status + ", awardedBidId=" + awardedBidId + ", createdAt="
				+ createdAt + ", modifiedDate=" + modifiedDate + ", projectProducts=" + projectProducts.toString() + "]";
	}

    
}