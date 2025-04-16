package org.keyin.memberships;

public enum MembershipType {
    MONTHLY(30.0, false, "Basic monthly membership with access to gym facilities."),
    YEARLY(300.0, false, "Basic annual membership with access to gym facilities."),
    MONTHLY_PREMIUM(50.0, true, "Premium monthly membership with access to gym and pool facilities."),
    YEARLY_PREMIUM(500.0, true, "Premium annual membership with access to gym and pool facilities.");

    private final double cost;
    private final boolean includesWorkoutAccess;
    private final String description;

    MembershipType(double cost, boolean includesWorkoutAccess, String description) {
        this.cost = cost;
        this.includesWorkoutAccess = includesWorkoutAccess;
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public boolean includesWorkoutAccess() {
        return includesWorkoutAccess;
    }
    public String getDescription() {
        return description;
    }
}
