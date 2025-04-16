package org.keyin.workoutclasses;

/* The WorkoutClassDao class communicates with the database to perform SQL queries.
 * This file includes all CRUD operations for workout classes */

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAO {

    // Create New Workout Class
    public void createWorkoutClass(WorkoutClass workoutClass) {
        String sql = "INSERT INTO gymClasses(gymClassType, gymClassDescription, trainerId) VALUES (?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, workoutClass.getWorkoutClassType());
            pstmt.setString(2, workoutClass.getWorkoutClassDescription());
            pstmt.setInt(3, workoutClass.getTrainerId());
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Get Workout Class by ID
    public WorkoutClass getWorkoutClassById(int id) throws SQLException {
        String sql = "SELECT * FROM gymClasses WHERE gymClassId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkoutClass(
                            rs.getInt("gymClassId"),
                            rs.getString("gymClassType"),
                            rs.getString("gymClassDescription"),
                            rs.getInt("trainerId")
                    );
                }
            }
        }

        return null;
    }

    // Get All Workout Classes
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        List<WorkoutClass> workoutClasses = new ArrayList<>();
        String sql = "SELECT * FROM gymClasses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("gymClassId"),
                        rs.getString("gymClassType"),
                        rs.getString("gymClassDescription"),
                        rs.getInt("trainerId")
                );
                workoutClasses.add(workoutClass);
            }
        }

        return workoutClasses;
    }

    // Display All Workout Classes
    public void displayAllWorkoutClasses() throws SQLException {
        String sql = "SELECT * FROM gymClasses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("All Workout Classes");
            while (rs.next()) {
                int classId = rs.getInt("gymClassId");
                String classType = rs.getString("gymClassType");
                String classDescription = rs.getString("gymClassDescription");
                int trainerId = rs.getInt("trainerId");

                System.out.println();
                System.out.println("Class ID: " + classId);
                System.out.println("Class Type: " + classType);
                System.out.println("Class Description: " + classDescription);
                System.out.println("Trainer ID: " + trainerId);
                System.out.println();
            }
        }
    }

    // Get Classes by Trainer ID
    public List<WorkoutClass> getClassesByTrainerId(int trainerId) throws SQLException {
        List<WorkoutClass> trainerClasses = new ArrayList<>();
        String sql = "SELECT * FROM gymClasses WHERE trainerId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    WorkoutClass workoutClass = new WorkoutClass(
                            rs.getInt("gymClassId"),
                            rs.getString("gymClassType"),
                            rs.getString("gymClassDescription"),
                            rs.getInt("trainerId")
                    );
                    trainerClasses.add(workoutClass);
                }
            }
        }

        return trainerClasses;
    }

    // Display Classes by Trainer ID
    public void displayClassesByTrainerId(int trainerId) throws SQLException {
        String sql = "SELECT * FROM gymClasses WHERE trainerId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Classes for Trainer ID: " + trainerId);
                while (rs.next()) {
                    int classId = rs.getInt("gymClassId");
                    String classType = rs.getString("gymClassType");
                    String classDescription = rs.getString("gymClassDescription");

                    System.out.println();
                    System.out.println("Class ID: " + classId);
                    System.out.println("Class Type: " + classType);
                    System.out.println("Class Description: " + classDescription);
                    System.out.println();
                }
            }
        }
    }

    // Update Workout Class Type
    public void updateWorkoutClassType(int id, String newType) {
        String sql = "UPDATE gymClasses SET gymClassType = ? WHERE gymClassId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newType);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Update Workout Class Description
    public void updateWorkoutClassDescription(int id, String newDescription) {
        String sql = "UPDATE gymClasses SET gymClassDescription = ? WHERE gymClassId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newDescription);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Delete Workout Class
    public void deleteWorkoutClass(int id) {
        String sql = "DELETE FROM gymClasses WHERE gymClassId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    // Enroll Member in Class
    public void enrollMemberInClass(int memberId, int classId) throws SQLException {
        String sql = "INSERT INTO classEnrollments(memberId, gymClassId) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, classId);
            pstmt.executeUpdate();
        }
    }


    //Get all classes a user is enrolled
    public List<WorkoutClass> getClassesForMember(int userId) {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = """
        SELECT gc.*
        FROM gymClasses gc
        INNER JOIN classEnrollments ce ON gc.gymClassId = ce.gymClassId
        WHERE ce.memberId = ?
    """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WorkoutClass workoutClass = new WorkoutClass(
                        rs.getInt("gymClassId"),
                        rs.getString("gymClassType"),
                        rs.getString("gymClassDescription"),
                        rs.getObject("trainerId", Integer.class) // ✅ handles null safely
                );
                classes.add(workoutClass);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving enrolled classes: " + e.getMessage());
        }

        return classes;
    }
    // User drops class
    public boolean unenrollMemberFromClass(int userId, int classId) {
        String sql = "DELETE FROM classEnrollments WHERE memberId = ? AND gymClassId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, classId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error unenrolling from class: " + e.getMessage());
            return false;
        }
    }

}