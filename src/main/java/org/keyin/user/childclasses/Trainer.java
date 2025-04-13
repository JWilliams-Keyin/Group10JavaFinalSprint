package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Trainer extends User {

    // Constructor

    public Trainer(int userId, String userName, String userPassword, String userEmail, String userPhoneNumber, String userAddress, String userEmergencyContactName, String userEmergencyContactPhoneNumber) {
        super(userId, userName, userPassword, userEmail, userPhoneNumber, userAddress, "Trainer", userEmergencyContactName, userEmergencyContactPhoneNumber);
    }
}
