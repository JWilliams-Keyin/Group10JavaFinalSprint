package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {

    public void addMembership(Membership membership) {
        String sql = "INSERT INTO memberships (membership_id, membership_type, membership_description, membership_cost, member_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membership.getMembershipID());
            stmt.setString(2, membership.getMembershipType().name());
            stmt.setString(3, membership.getMembershipDescription());
            stmt.setDouble(4, membership.getMembershipCost());
            stmt.setInt(5, membership.getMemberID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to add membership: " + e.getMessage());
        }
    }

    public Membership getMembershipById(int id) {
        String sql = "SELECT * FROM memberships WHERE membership_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Membership(
                        rs.getInt("membership_id"),
                        MembershipType.valueOf(rs.getString("membership_type")),
                        rs.getString("membership_description"),
                        rs.getDouble("membership_cost"),
                        rs.getInt("member_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error getting membership: " + e.getMessage());
        }

        return null;
    }

    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Membership membership = new Membership(
                        rs.getInt("membership_id"),
                        MembershipType.valueOf(rs.getString("membership_type")),
                        rs.getString("membership_description"),
                        rs.getDouble("membership_cost"),
                        rs.getInt("member_id")
                );
                memberships.add(membership);
            }

        } catch (SQLException e) {
            System.out.println("Error getting all memberships: " + e.getMessage());
        }

        return memberships;
    }

    public void updateMembership(Membership membership) {
        String sql = "UPDATE memberships SET membership_type = ?, membership_description = ?, membership_cost = ?, member_id = ? WHERE membership_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membership.getMembershipType().name());
            stmt.setString(2, membership.getMembershipDescription());
            stmt.setDouble(3, membership.getMembershipCost());
            stmt.setInt(4, membership.getMemberID());
            stmt.setInt(5, membership.getMembershipID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to update membership: " + e.getMessage());
        }
    }

    public void deleteMembership(int membershipID) {
        String sql = "DELETE FROM memberships WHERE membership_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, membershipID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to delete membership: " + e.getMessage());
        }
    }
}