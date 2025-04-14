package org.keyin.user;

/* Author: Jack Williams
 *  Date: April 7th, 2025
 *  Description: The UserService class is a middleware class used to let the app
 *  communicate with the DAO. This class includes middleware for
 *  all the CRUD operations */

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class UserService {

    private UserDao userDao = new UserDao();

    // Create New User (With Hashed Password)

    public boolean createNewUser(User user){
        if(user == null){
            System.out.println("Please input a valid user");
            return false;
        } else if (user.userPassword.length() < 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(user.getUserPassword(),BCrypt.gensalt(10));

        user.setUserPassword(hashedPassword);

        userDao.createNewUser(user);
        System.out.println("User created successfully");
        return true;
    }

    // Get User by Username

    public User getUserByUserName(String username) throws SQLException {
        if(username == null){
            System.out.println("Please input a username");
            return null;
        }

        return userDao.getUserByUsername(username);

    }

    // Get All Users

    public void getAllUsers() throws SQLException {
        userDao.getAllUsers();
    }

    // Update a User's Username

    public boolean updateUserName(int userId, String newUsername) throws SQLException {
        if(newUsername == null){
            System.out.println("Please input a username");
            return false;
        }

        userDao.updateUserName(userId, newUsername);

        return true;
    }

    // Update a User's Password (hashed)

    public boolean updateUserPassword(int userId, String newPassword) throws SQLException {
        if(newPassword == null){
            System.out.println("Please input a password");
            return false;
        } else if (newPassword.length() < 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(10));

        userDao.updateUserPassword(userId, hashedPassword);

        return true;
    }

    // Update a User's Email

    public boolean updateUserEmail(int userId, String newEmail) throws SQLException {
        if(newEmail == null){
            System.out.println("Please input an email");
            return false;
        }

        userDao.updateUserEmail(userId, newEmail);

        return true;
    }

    // Update a User's Phone Number

    public boolean updatePhoneNum(int userId, String newPhoneNum) throws SQLException {
        if(newPhoneNum == null){
            System.out.println("Please input a phone number");
            return false;
        }

        userDao.updateUserPhoneNum(userId, newPhoneNum);

        return true;
    }

    // Update a User's Address

    public boolean updateUserAddress(int userId, String newAddress) throws SQLException {
        if(newAddress == null){
            System.out.println("Please input an address");
            return false;
        }

        userDao.updateUserAddress(userId, newAddress);

        return true;
    }

    // Update a User's Emergency Contact Name

    public boolean updateUserEmergencyContactName(int userId, String newEmergencyName) throws SQLException {
        if(newEmergencyName == null){
            System.out.println("Please input a name");
            return false;
        }

        userDao.updateUserEmergencyContactName(userId, newEmergencyName);

        return true;
    }

    // Update a User's Emergency Contact Phone Number

    public boolean updateUserEmergencyContactPhoneNum(int userId, String newEmergencyNum) throws SQLException {
        if(newEmergencyNum == null){
            System.out.println("Please input a phone number");
            return false;
        }

        userDao.updateUserEmergencyContactPhoneNum(userId, newEmergencyNum);

        return true;
    }

    // Update a User's Role

    public boolean updateUserRole(int userId, String newRole) throws SQLException {
        if(newRole == null){
            System.out.println("Please input a role");
            return false;
        }

        userDao.updateUserRole(userId, newRole);

        return true;
    }

    // Delete User by ID

    public void deleteUser(int userId) throws SQLException {
        userDao.deleteUser(userId);
    }
}