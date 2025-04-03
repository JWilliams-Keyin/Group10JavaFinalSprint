package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Admin extends User {

    // Constructor

    public Admin(String username, String password, String email, String phoneNumber, String address, String emergencyContactName, String emergencyContactPhoneNumber) {
        super(username, password, email, phoneNumber, address, "Admin", emergencyContactName, emergencyContactPhoneNumber);
    }
}
