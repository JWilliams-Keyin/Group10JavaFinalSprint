package org.keyin.user;

import org.mindrot.jbcrypt.BCrypt;

//*
// This is the parent class for all users, There are 3 types of users: Trainer, Member, and Admin
//
// *//
public class User {

    // Attributes

    public String userName;
    public String userPassword;
    public String userEmail;
    public String userPhoneNumber;
    public String userAddress;
    public String userRole;
    public String userEmergencyContactName;
    public String userEmergencyContactPhoneNumber;

    // Constructors

    public User(String username, String password, String email, String phoneNumber, String address, String role, String emergencyContactName, String emergencyContactPhoneNumber) {
        this.userName = username;
        this.userPassword = BCrypt.hashpw(password, String.valueOf(10));
        this.userEmail = email;
        this.userPhoneNumber = phoneNumber;
        this.userAddress = address;
        this.userRole = role;
        this.userEmergencyContactName = emergencyContactName;
        this.userEmergencyContactPhoneNumber = emergencyContactPhoneNumber;
    }

    // Getters & Setters


    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getUserEmergencyContactName() {
        return userEmergencyContactName;
    }

    public String getUserEmergencyContactPhoneNumber() {
        return userEmergencyContactPhoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = BCrypt.hashpw(userPassword, String.valueOf(10));
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setUserEmergencyContactName(String userEmergencyContactName) {
        this.userEmergencyContactName = userEmergencyContactName;
    }

    public void setUserEmergencyContactPhoneNumber(String userEmergencyContactPhoneNumber) {
        this.userEmergencyContactPhoneNumber = userEmergencyContactPhoneNumber;
    }

    // Methods

    @Override
    public String toString() {
        return
                "User: " + userName +
                        ", Password: " + userPassword +
                        ", Email: " + userEmail +
                        ", Phone Number: " + userPhoneNumber +
                        ", Address: " + userAddress +
                        ", Role: " + userRole +
                        ", Emergency Contact Name: " + userEmergencyContactName +
                        ", Emergency Contact Phone Number: " + userEmergencyContactPhoneNumber;
    }
}