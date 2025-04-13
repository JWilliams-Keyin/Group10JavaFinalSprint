package org.keyin.workoutclasses;

/*
The workoutClass represents a classes that can be offered at the gym.
Trainers can create, update and delete workout classes.
Members can view and enroll in workout classes.
 */
public class WorkoutClass {

    // Attributes
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId;

    // Constructors
    public WorkoutClass(String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }

    public WorkoutClass(int workoutClassId, String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassId = workoutClassId;
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }

    // Getters & Setters
    public int getWorkoutClassId() {
        return workoutClassId;
    }

    public void setWorkoutClassId(int workoutClassId) {
        this.workoutClassId = workoutClassId;
    }

    public String getWorkoutClassType() {
        return workoutClassType;
    }

    public void setWorkoutClassType(String workoutClassType) {
        this.workoutClassType = workoutClassType;
    }

    public String getWorkoutClassDescription() {
        return workoutClassDescription;
    }

    public void setWorkoutClassDescription(String workoutClassDescription) {
        this.workoutClassDescription = workoutClassDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    // Methods
    @Override
    public String toString() {
        return "Workout Class ID: " + workoutClassId +
                ", Type: " + workoutClassType +
                ", Description: " + workoutClassDescription +
                ", Trainer ID: " + trainerId;
    }
}