package org.keyin.memberships;

import java.sql.SQLException;
import java.util.List;

public class MembershipService {

    private MembershipDAO membershipDAO = new MembershipDAO();

    public boolean addMembership(Membership membership) {
        if (membership.getMembershipDescription() == null || membership.getMembershipDescription().isEmpty()) {
            System.out.println("❌ Description cannot be empty.");
            return false;
        }

        if (membership.getMembershipCost() < 0) {
            System.out.println("❌ Cost cannot be negative.");
            return false;
        }

        membershipDAO.addMembership(membership);
        return true;
    }

    public Membership getMembershipById(int id) {
        return membershipDAO.getMembershipById(id);
    }

    public List<Membership> getAllMemberships() {
        return membershipDAO.getAllMemberships();
    }

    public boolean updateMembership(Membership membership) {
        if (membership.getMembershipDescription() == null || membership.getMembershipDescription().isEmpty()) {
            System.out.println("❌ Description cannot be empty.");
            return false;
        }

        if (membership.getMembershipCost() < 0) {
            System.out.println("❌ Cost cannot be negative.");
            return false;
        }

        membershipDAO.updateMembership(membership);
        return true;
    }

    public void deleteMembership(int membershipID) {
        membershipDAO.deleteMembership(membershipID);
    }
}
