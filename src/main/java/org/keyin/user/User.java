package org.keyin.user;

import org.mindrot.jbcrypt.BCrypt;

/* Author: Jack Williams
 *  Dates: April 2nd - April 3rd, 2025
 *  Description: The User class is the parent class for all kinds of users.
 *  There are three kinds of users: Member, Trainer, and Admin */

public class User {

    // Attributes

    public int userId;
    public String userName;
    public String userPassword;
    public String userEmail;
    public String userPhoneNumber;
    public String userAddress;
    public String userRole;
    public String userEmergencyContactName;
    public String userEmergencyContactPhoneNumber;

    // Constructors

    public User(int userId, String username, String password, String email, String phoneNumber, String address, String role, String emergencyContactName, String emergencyContactPhoneNumber) {
        this.userId = userId;
        this.userName = username;
        this.userPassword = password;// No hashing here, all done in UserService
        this.userEmail = email;
        this.userPhoneNumber = phoneNumber;
        this.userAddress = address;
        this.userRole = role;
        this.userEmergencyContactName = emergencyContactName;
        this.userEmergencyContactPhoneNumber = emergencyContactPhoneNumber;
    }

    // Getters & Setters

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword; // No Hashing here either
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
