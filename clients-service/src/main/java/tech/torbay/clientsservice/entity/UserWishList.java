package tech.torbay.clientsservice.entity;

import javax.persistence.*;

import tech.torbay.clientsservice.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "user_wish_list")
public class UserWishList {

    public UserWishList() {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
	private Integer wisherOrgId = 0;
	private Integer wisherUserId = 0;
	private Integer wisherUserType = 0;
	private Integer favouriteOrgId = 0;
	private Integer favouriteUserType = 0;
    
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

	public Integer getWisherOrgId() {
		return wisherOrgId;
	}

	public void setWisherOrgId(Integer wisherOrgId) {
		this.wisherOrgId = wisherOrgId;
	}

	public Integer getWisherUserId() {
		return wisherUserId;
	}

	public void setWisherUserId(Integer wisherUserId) {
		this.wisherUserId = wisherUserId;
	}

	public Integer getWisherUserType() {
		return wisherUserType;
	}

	public void setWisherUserType(Integer wisherUserType) {
		this.wisherUserType = wisherUserType;
	}

	public Integer getFavouriteOrgId() {
		return favouriteOrgId;
	}

	public void setFavouriteOrgId(Integer favouriteOrgId) {
		this.favouriteOrgId = favouriteOrgId;
	}

	public Integer getFavouriteUserType() {
		return favouriteUserType;
	}

	public void setFavouriteUserType(Integer favouriteUserType) {
		this.favouriteUserType = favouriteUserType;
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
		return "UserWishList [id=" + id + ", wisherOrgId=" + wisherOrgId + ", wisherUserId=" + wisherUserId
				+ ", wisherUserType=" + wisherUserType + ", favouriteOrgId=" + favouriteOrgId + ", favouriteUserType="
				+ favouriteUserType + ", createdAt=" + createdAt + ", modifiedDate=" + modifiedDate + "]";
	}
    
}