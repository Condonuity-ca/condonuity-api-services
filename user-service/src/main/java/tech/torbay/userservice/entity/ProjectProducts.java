package tech.torbay.userservice.entity;

import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
//@JsonInclude(value = Include.NON_NULL)
@Table(name = "project_products")
public class ProjectProducts {

    public ProjectProducts() {

    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id = 0;
//    private Integer projectId = 0;//one to many
//    @NotEmpty(message = "Description must not be empty")
    @Column(name = "description")
    public String description = "";
//    @Min(value = 1, message = "Quantity must be greater than 1")
    @Column(name = "quantity")
    public Integer quantity = 0;
//    @NotEmpty(message = "Unit must not be empty")
    @Column(name = "unit")
    public String unit = "";
    
    
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
		id = id;
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
		return "ProjectProducts [Id=" + id + ", projectId=" /* + projectId */ + ", description=" + description + ", quantity="
				+ quantity + ", unit=" + unit + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectProducts )) return false;
        return id != null && id.equals(((ProjectProducts) o).getId());
    }
	
}