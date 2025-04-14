package org.keyin.memberships;

public enum MembershipType {
    MONTHLY(30.0, false),
    YEARLY(300.0, false),
    MONTHLY_PREMIUM(50.0, true),
    YEARLY_PREMIUM(500.0, true);

    private final double cost;
    private final boolean includesWorkoutAccess;

    MembershipType(double cost, boolean includesWorkoutAccess) {
        this.cost = cost;
        this.includesWorkoutAccess = includesWorkoutAccess;
    }

    public double getCost() {
        return cost;
    }

    public boolean includesWorkoutAccess() {
        return includesWorkoutAccess;
    }
}