package org.keyin.workoutclasses;

/*
The WorkoutClassService class is a middleware class used to let the app
communicate with the DAO. This class includes middleware for
all the CRUD operations related to workout-classes
*/

import java.sql.SQLException;
import java.util.List;

public class WorkoutClassService {

    private WorkoutClassDAO workoutClassDao = new WorkoutClassDAO();

    // Create New Workout Class
    public boolean createWorkoutClass(WorkoutClass workoutClass) {
        if (workoutClass == null) {
            System.out.println("Please input a valid workout class");
            return false;
        } else if (workoutClass.getWorkoutClassType() == null || workoutClass.getWorkoutClassType().isEmpty()) {
            System.out.println("Workout class type cannot be empty");
            return false;
        }

        workoutClassDao.createWorkoutClass(workoutClass);
        System.out.println("Workout class created successfully");
        return true;
    }

    // Get Workout Class by ID
    public WorkoutClass getWorkoutClassById(int classId) throws SQLException {
        return workoutClassDao.getWorkoutClassById(classId);
    }

    // Get All Workout Classes
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        return workoutClassDao.getAllWorkoutClasses();
    }

    // Display All Workout Classes
    public void displayAllWorkoutClasses() throws SQLException {
        workoutClassDao.displayAllWorkoutClasses();
    }

    // Get Classes by Trainer ID
    public List<WorkoutClass> getClassesByTrainerId(int trainerId) throws SQLException {
        return workoutClassDao.getClassesByTrainerId(trainerId);
    }

    // Display Classes by Trainer ID
    public void displayClassesByTrainerId(int trainerId) throws SQLException {
        workoutClassDao.displayClassesByTrainerId(trainerId);
    }

    // Update Workout Class Type
    public boolean updateWorkoutClassType(int classId, String newType) throws SQLException {
        if (newType == null || newType.isEmpty()) {
            System.out.println("Please input a valid workout class type");
            return false;
        }

        WorkoutClass existingClass = workoutClassDao.getWorkoutClassById(classId);
        if (existingClass == null) {
            System.out.println("Workout class with ID " + classId + " not found");
            return false;
        }

        workoutClassDao.updateWorkoutClassType(classId, newType);
        System.out.println("Workout class type updated successfully");
        return true;
    }

    // Update Workout Class Description
    public boolean updateWorkoutClassDescription(int classId, String newDescription) throws SQLException {
        if (newDescription == null) {
            System.out.println("Please input a valid workout class description");
            return false;
        }

        WorkoutClass existingClass = workoutClassDao.getWorkoutClassById(classId);
        if (existingClass == null) {
            System.out.println("Workout class with ID " + classId + " not found");
            return false;
        }

        workoutClassDao.updateWorkoutClassDescription(classId, newDescription);
        System.out.println("Workout class description updated successfully");
        return true;
    }

    // Delete Workout Class
    public boolean deleteWorkoutClass(int classId) throws SQLException {
        WorkoutClass existingClass = workoutClassDao.getWorkoutClassById(classId);
        if (existingClass == null) {
            System.out.println("Workout class with ID " + classId + " not found");
            return false;
        }

        workoutClassDao.deleteWorkoutClass(classId);
        System.out.println("Workout class deleted successfully");
        return true;
    }

    // Enroll Member in Class
    public boolean enrollMemberInClass(int memberId, int classId) throws SQLException {
        try {
            workoutClassDao.enrollMemberInClass(memberId, classId);
            System.out.println("Member enrolled in class successfully");
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("uniqueEnrollment")) {
                System.out.println("Member is already enrolled in this class");
            } else if (e.getMessage().contains("studentFkey")) {
                System.out.println("Member ID does not exist");
            } else if (e.getMessage().contains("gymClassFkey")) {
                System.out.println("Class ID does not exist");
            } else {
                System.out.println("Error enrolling member in class: " + e.getMessage());
            }
            return false;
        }
    }
    // Get all classes a member is enrolled in
    public List<WorkoutClass> getClassesForMember(int userId) {
        return workoutClassDao.getClassesForMember(userId);
    }

    // User drops a class
    public boolean unenrollMemberFromClass(int userId, int classId) {
        return workoutClassDao.unenrollMemberFromClass(userId, classId);
    }


}

