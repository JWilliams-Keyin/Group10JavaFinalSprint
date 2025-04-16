package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Admin extends User {

    // Constructor

    public Admin(String userName, String userPassword, String userEmail, String userPhoneNumber, String userAddress, String userEmergencyContactName, String userEmergencyContactPhoneNumber) {
        super(0, userName, userPassword, userEmail, userPhoneNumber, userAddress, "Admin", userEmergencyContactName, userEmergencyContactPhoneNumber);
    }
}
