package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {

    // Constructor

    public Member(String userName, String userPassword, String userEmail, String userPhoneNumber, String userAddress, String userEmergencyContactName, String userEmergencyContactPhoneNumber) {
        super(0, userName, userPassword, userEmail, userPhoneNumber, userAddress, "Member", userEmergencyContactName, userEmergencyContactPhoneNumber);
    }
}