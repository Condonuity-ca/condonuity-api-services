package tech.torbay.userservice.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name="notification")
public class Notification {


	public Notification() {
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = 0;
	
	private Integer notificationCategoryType = 0;
    private Integer notificationCategoryId = 0;
    private String title = "";
    private String description = "";
    private Integer userId = 0;
    private Integer organisationId = 0;
    private Integer userType = 0;
    private Integer status = 0;

    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String createdAt;
    
    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    private String modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNotificationCategoryType() {
		return notificationCategoryType;
	}

	public void setNotificationCategoryType(Integer notificationCategoryType) {
		this.notificationCategoryType = notificationCategoryType;
	}

	public Integer getNotificationCategoryId() {
		return notificationCategoryId;
	}

	public void setNotificationCategoryId(Integer notificationCategoryId) {
		this.notificationCategoryId = notificationCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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
	
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", notificationCategoryType=" + notificationCategoryType
				+ ", notificationCategoryId=" + notificationCategoryId + ", title=" + title + ", description="
				+ description + ", userId=" + userId + ", organisationId=" + organisationId + ", userType=" + userType
				+ ", status=" + status + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}

	@Override
    public boolean equals(Object obj) {
        if (obj instanceof Notification) {
            return ((Notification) obj).id == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
