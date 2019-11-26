package tech.torbay.projectservice.entity;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "project_products")
public class ProjectProducts {

    public ProjectProducts() {

    }
 
    @Id
	private Integer Id = 0;
//    private Integer projectId = 0;
    private String description = "";
    private Integer quantity = 0;
    private String unit;
    private String createdAt;
    private String modifiedDate;
    
    @ManyToOne
//    @JoinColumn(name = "projectId", referencedColumnName = "projectId", insertable = false, updatable = false)
    @JoinColumn(name = "project_id")
	private Project project;
    
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getProjectId() {
		return project.getProjectId();
	}
	public void setProjectId(Integer projectId) {
		this.project.setProjectId(projectId);;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
		return "ProjectProducts [Id=" + Id + /* ", projectId=" + projectId + */ ", description=" + description + ", quantity="
				+ quantity + ", unit=" + unit + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
}