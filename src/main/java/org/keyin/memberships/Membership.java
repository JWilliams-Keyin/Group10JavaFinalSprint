package org.keyin.memberships;

public class Membership {


    private int membershipID;
    private MembershipType membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberID;


    public Membership(int membershipID, MembershipType membershipType, String membershipDescription, int memberID) {
        this.membershipID = membershipID;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipType.getCost();
        this.memberID = memberID;
    }


    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }


    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
        this.membershipCost = membershipType.getCost(); // Recalculate cost if type changes
    }


    public String getMembershipDescription() {
        return membershipDescription;
    }

    public void setMembershipDescription(String membershipDescription) {
        this.membershipDescription = membershipDescription;
    }


    public double getMembershipCost() {
        return membershipCost;
    }


    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }


    @Override
    public String toString() {
        return "Membership{" +
                "membershipID=" + membershipID +
                ", membershipType=" + membershipType +
                ", membershipDescription='" + membershipDescription + '\'' +
                ", membershipCost=" + membershipCost +
                ", memberID=" + memberID +
                '}';
    }
}
