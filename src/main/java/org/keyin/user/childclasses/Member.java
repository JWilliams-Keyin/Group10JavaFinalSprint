package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {
  
    // Constructor

    public Member(String username, String password, String email, int phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "Member");
    }
}
