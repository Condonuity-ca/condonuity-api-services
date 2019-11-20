package tech.torbay.userservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "user_account")
public class User {

    public User() {

    }

    public User(Integer id, String username, String password, int userId, int userType, int userRole) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.userType = userType;
        this.userRole = userRole;
    }
    @Id
    private Integer id = 0;
    private String username = "";
    private String password = "";
    private Integer userId = 0;
    private Integer userType = 0;
    private Integer userRole = 0;

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
    
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", userId=" + userId + 
        		", userType=" + userType + ", userRole=" + userRole +"]";
    }
}