package com.epde.rt.dto;

import com.epde.rt.exception.InvalidEnumValueException;
import com.epde.rt.model.users.enums.AppUserGender;
import com.epde.rt.model.users.enums.AppUserRole;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AppUserDto {
    private Long userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userGender;

    private String userAddress;

    private String userContactNumber;

    private String username;

    private String password;

    private String userRole;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDateOfBirth;

    public void validateUserGender() {
        try {
            AppUserGender.valueOf(userGender);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid Gender: " + userGender);
        }
    }

    public void validateUserRole() {
        try {
            AppUserRole.valueOf(userRole);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid Role: " + userRole);
        }
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

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
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
        return "AppUserDto{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userContactNumber='" + userContactNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userDateOfBirth=" + userDateOfBirth +
                '}';
    }
}
