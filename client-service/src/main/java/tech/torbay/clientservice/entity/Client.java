package tech.torbay.clientservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import tech.torbay.clientservice.constants.Constants;

import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {


    private Integer userId;

    @Id
    private Integer clientId = 0;
    private String email = "";
    private String username ="";
    private String password = "";
    private Integer organisationId = 0;
    private String firstName = "";
    private String LastName = "";
    private String legalName = "";
    private Integer userType = Constants.UserType.CLIENT.getValue();
    private Integer userRole = Constants.UserRole.NOT_AVAILABLE.getValue();
    private Integer status = 0;
    private String city = "";
    private String phone = "";
    private int country_code =0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @JsonIgnore
    @JsonProperty(value = "organisationId")
    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCountry_code() {
        return country_code;
    }

    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }

    @Override
    public String toString() {
        return "Client{" +
                "userId=" + userId +
                ", clientId=" + clientId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", organisationId=" + organisationId +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", legalName='" + legalName + '\'' +
                ", userType=" + userType +
                ", userRole=" + userRole +
                ", status=" + status +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", country_code=" + country_code +
                '}';
    }
}
