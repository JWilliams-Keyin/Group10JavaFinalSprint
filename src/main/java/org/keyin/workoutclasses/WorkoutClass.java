package org.keyin.workoutclasses;

public class WorkoutClass {

    // Attributes

    private String gymClassType;
    private String gymClassDescription;
    private int trainerId;
    
    // Constructors
    
    public WorkoutClass(String gymClassType, String gymClassDescription, int trainerId) {
        this.gymClassType = gymClassType;
        this.gymClassDescription = gymClassDescription;
        this.trainerId = trainerId;
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

    public void setGymClassType(String gymClassType) {
        this.gymClassType = gymClassType;
    }

    public void setGymClassDescription(String gymClassDescription) {
        this.gymClassDescription = gymClassDescription;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
    
    // Methods
    
    @Override
    public String toString() {
        return "Class Type: " + gymClassType + 
                ", Class Description: " + gymClassDescription +
                ", Trainer ID: " + trainerId;
    }
}
