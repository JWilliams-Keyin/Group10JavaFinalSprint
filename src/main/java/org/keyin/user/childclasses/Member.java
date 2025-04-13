package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {

    // Constructor

    public Member(int userId, String userName, String userPassword, String userEmail, String userPhoneNumber, String userAddress, String userEmergencyContactName, String userEmergencyContactPhoneNumber) {
        super(userId, userName, userPassword, userEmail, userPhoneNumber, userAddress, "Member", userEmergencyContactName, userEmergencyContactPhoneNumber);
    }
}
