package tech.torbay.clientsservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name="user_level_notification")
public class UserLevelNotification {


	public UserLevelNotification() {
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id = 0;
	
	private Integer notificationCategoryType = 0;
    private Integer notificationCategoryId = 0;
    private String title = "";
    private String description = "";
    private Integer fromUserId = 0;
    private Integer fromOrganisationId = 0;
    private Integer fromUserType = 0;
    private Integer toUserId = 0;
    private Integer toOrganisationId = 0;
    private Integer toUserType = 0;
    private Integer status = 0;

	@CreatedDate
    private String createdAt;
    
    @LastModifiedDate
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

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getFromOrganisationId() {
		return fromOrganisationId;
	}

	public void setFromOrganisationId(Integer fromOrganisationId) {
		this.fromOrganisationId = fromOrganisationId;
	}

	public Integer getFromUserType() {
		return fromUserType;
	}

	public void setFromUserType(Integer fromUserType) {
		this.fromUserType = fromUserType;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getToOrganisationId() {
		return toOrganisationId;
	}

	public void setToOrganisationId(Integer toOrganisationId) {
		this.toOrganisationId = toOrganisationId;
	}

	public Integer getToUserType() {
		return toUserType;
	}

	public void setToUserType(Integer toUserType) {
		this.toUserType = toUserType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
    public boolean equals(Object obj) {
        if (obj instanceof UserLevelNotification) {
            return ((UserLevelNotification) obj).id == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

	@Override
	public String toString() {
		return "UserLevelNotification [id=" + id + ", notificationCategoryType=" + notificationCategoryType
				+ ", notificationCategoryId=" + notificationCategoryId + ", title=" + title + ", description="
				+ description + ", fromUserId=" + fromUserId + ", fromOrganisationId=" + fromOrganisationId
				+ ", fromUserType=" + fromUserType + ", toUserId=" + toUserId + ", toOrganisationId=" + toOrganisationId
				+ ", toUserType=" + toUserType + ", status=" + status + ", createdAt=" + createdAt + ", modifiedDate="
				+ modifiedDate + "]";
	}
    
    
}
