package org.keyin.user;

//*
// This is the parent class for all users, There are 3 types of users: Trainer, Member, and Admin
//
// *//
public class User {

    // Attributes

    public String username;
    public String password;
    public String email;
    public int phoneNumber;
    public String address;
    public String role;

    // Constructors

    public User(String username, String password, String email, int phoneNumber, String address, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    // Getters & Setters


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    // Methods
    
    @Override
    public String toString() {
        return 
                "User: " + username + 
                ", Password: " + password + 
                ", Email: " + email + 
                ", Phone Number: " + phoneNumber + 
                ", Address: " + address + 
                ", Role: " + role;
    }
}
