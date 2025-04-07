package org.keyin.workoutclasses;

public class WorkoutClass {

    // Attributes

    private String gymClassType;
    private String gymClassDescription;
    private int trainerId;
    private int gymClassCost;

    // Constructors

    public WorkoutClass(String gymClassType, String gymClassDescription, int trainerId, int gymClassCost) {
        this.gymClassType = gymClassType;
        this.gymClassDescription = gymClassDescription;
        this.trainerId = trainerId;
        this.gymClassCost = 20;
    }

    // Getters & Setters
    
    public String getGymClassType() {
        return gymClassType;
    }

    public String getGymClassDescription() {
        return gymClassDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public int getGymClassCost() {
        return gymClassCost;
    }

    public void setGymClassType(String gymClassType) {
        this.gymClassType = gymClassType;
    }

    public void setGymClassDescription(String gymClassDescription) {
        this.gymClassDescription = gymClassDescription;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setGymClassCost(int gymClassCost) {
        this.gymClassCost = gymClassCost;
    }

    // Methods

    @Override
    public String toString() {
        return "Class Type: " + gymClassType +
                ", Class Description: " + gymClassDescription +
                ", Trainer ID: " + trainerId + 
                ", Class Price: $" + gymClassCost;
    }
}
