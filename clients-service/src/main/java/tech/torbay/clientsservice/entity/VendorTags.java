package tech.torbay.clientsservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "vendor_tags")
public class VendorTags {
	
	public VendorTags() {

    }
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id = 0;
//    private Integer vendorId = 0;//one to many
	private Integer tagId = 0;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "modified_date", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
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
		return "VendorTags [id=" + id + ", tagId=" + tagId + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorTags )) return false;
        return id != null && id.equals(((VendorTags) o).getId());
    }
}
