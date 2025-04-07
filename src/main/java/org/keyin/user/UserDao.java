package org.keyin.user;

import org.keyin.database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao {

    // Create New User

    public void createNewUser(User user) {
        String sql = """
                INSERT INTO public.users(username, email, password, role, sector)
                \tVALUES (?, ?, ?, ?, ?, ?, ?, ?);""";

        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUserName());
            psmt.setString(2, user.getUserPassword());
            psmt.setString(3, user.getUserEmail());
            psmt.setString(4, user.getUserPhoneNumber());
            psmt.setString(5, user.getUserAddress());
            psmt.setString(6, user.getUserEmergencyContactName());
            psmt.setString(7, user.getUserEmergencyContactPhoneNumber());
            psmt.setString(8, user.getUserRole());
            psmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }

    }

    // Get User by Username

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE userName = ?";
        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("userName"),
                            rs.getString("userPassword"),
                            rs.getString("userEmail"),
                            rs.getString("userPhoneNum"),
                            rs.getString("userAddress"),
                            rs.getString("userRole"),
                            rs.getString("userEmergencyContactName"),
                            rs.getString("userEmergencyContactPhoneNum")
                    );
                }
            }
        }

        return null;
    }

    // Get All Users

    public void getAllUsers() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            rs = pstmt.executeQuery();
            System.out.println("All users");
            while (rs.next()) {
                int user_id = rs.getInt("userId");
                String username = rs.getString("userName");
                String password = rs.getString("userPassword");
                String email = rs.getString("userEmail");
                String phoneNum = rs.getString("userPhoneNum");
                String address = rs.getString("userAddress");
                String emergencyName = rs.getString("userEmergencyContactName");
                String emergencyNum = rs.getString("userEmergencyContactPhoneNum");
                String role = rs.getString("userRole");

                System.out.println();
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phoneNum);
                System.out.println("Address: " + address);
                System.out.println("Emergency Contact Name: " + emergencyName);
                System.out.println("Emergency Contact Phone Number: " + emergencyNum);
                System.out.println("Role: " + role);
                System.out.println();

            }
        }
    }

    // Update Username

    public void updateUserName(int id, String newName) {
        String sql = "UPDATE users\n" +
                    "\tSET userName = '" + newName + "'\n" +
                    "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Password

    public void updateUserPassword(int id, String newPassword) {
        String sql = "UPDATE users\n" +
                "\tSET userPassword = '" + BCrypt.hashpw(newPassword, BCrypt.gensalt(10)) + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Email

    public void updateUserEmail(int id, String newEmail) {
        String sql = "UPDATE users\n" +
                "\tSET userEmail = '" + newEmail + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Phone Number

    public void updateUserPhoneNum(int id, String newPhoneNum) {
        String sql = "UPDATE users\n" +
                "\tSET userPhoneNum = '" + newPhoneNum + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Address

    public void updateUserAddress(int id, String newAddress) {
        String sql = "UPDATE users\n" +
                "\tSET userAddress = '" + newAddress + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Emergency Contact Name

    public void updateUserEmergencyContactName(int id, String newEmergencyName) {
        String sql = "UPDATE users\n" +
                "\tSET userEmergencyContactName = '" + newEmergencyName + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Emergency Contact Phone Number

    public void updateUserEmergencyContactPhoneNum(int id, String newEmergencyNum) {
        String sql = "UPDATE users\n" +
                "\tSET userEmergencyContactPhoneNum = '" + newEmergencyNum + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Role

    public void updateUserRole(int id, String newRole) {
        String sql = "UPDATE users\n" +
                "\tSET userRole = '" + newRole + "'\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Delete User

    public void deleteUser(int id) {
        String sql = "DELETE FROM users\n" +
                "\tWHERE userId = '" + id + "';";

        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
