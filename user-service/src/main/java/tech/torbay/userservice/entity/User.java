package tech.torbay.userservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "user_login")
public class User {

    public User() {

    }

    public User(Integer id, String username, String password, int userId, int userType) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.userType = userType;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private String username = "";
    private String password = "";
    private Integer userId = 0;
    private Integer userType = 0;
    private String termsAcceptedDate = "";
    
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    
    public String getTermsAcceptedDate() {
		return termsAcceptedDate;
	}

	public void setTermsAcceptedDate(String termsAcceptedDate) {
		this.termsAcceptedDate = termsAcceptedDate;
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userId=" + userId
				+ ", userType=" + userType + ", termsAcceptedDate=" + termsAcceptedDate + ", createdAt=" + createdAt
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}