package com.epde.rt.model.users;


import com.epde.rt.model.users.enums.AppUserGender;
import com.epde.rt.model.users.enums.AppUserRole;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AppUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private AppUserGender userGender;

    private String userAddress;

    private String userContactNumber;

    private String username;

    private String password;

    private AppUserRole userRole;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDateOfBirth;

    public AppUsers() {
    }

    public AppUsers(Long userId, String userFirstName, String userLastName, String userEmail, AppUserGender userGender, String userAddress, String userContactNumber, String username, String password, AppUserRole userRole, Date userDateOfBirth) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userContactNumber = userContactNumber;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userDateOfBirth = userDateOfBirth;
    }

    public AppUsers(String userFirstName, String userLastName, String userEmail, AppUserGender userGender, String userAddress, String userContactNumber, String username, String password, AppUserRole userRole, Date userDateOfBirth) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userContactNumber = userContactNumber;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userDateOfBirth = userDateOfBirth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public AppUserGender getUserGender() {
        return userGender;
    }

    public void setUserGender(AppUserGender userGender) {
        this.userGender = userGender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
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

    public AppUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(AppUserRole userRole) {
        this.userRole = userRole;
    }

    public Date getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(Date userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    @Override
    public String toString() {
        return "AppUsers{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userGender=" + userGender +
                ", userAddress='" + userAddress + '\'' +
                ", userContactNumber='" + userContactNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", userDateOfBirth=" + userDateOfBirth +
                '}';
    }
}
