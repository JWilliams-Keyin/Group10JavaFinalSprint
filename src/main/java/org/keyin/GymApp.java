package org.keyin;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.memberships.MembershipType;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*
Main application class for the Gym Management System
*/
public class GymApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutClassService = new WorkoutClassService();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Register a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerNewUser(scanner, userService);
                    break;
                case 2:
                    loginUser(scanner, userService, membershipService, workoutClassService);
                    break;
                case 3:
                    System.out.println("Thank you for using the Gym Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    /*
    Register a new user with the system
    */
    private static void registerNewUser(Scanner scanner, UserService userService) {
        System.out.println("\n=== User Registration ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password (min 8 characters): ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter emergency contact name: ");
        String emergencyContactName = scanner.nextLine();

        System.out.print("Enter emergency contact phone number: ");
        String emergencyContactPhone = scanner.nextLine();

        int roleChoice;
        do {
            System.out.println("Select role:");
            System.out.println("1. Admin");
            System.out.println("2. Trainer");
            System.out.println("3. Member");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            roleChoice = scanner.nextInt();
            scanner.nextLine();

            if (roleChoice < 1 || roleChoice > 3) {
                System.out.println("Invalid choice! Please select 1, 2, or 3.");
            }
        } while (roleChoice < 1 || roleChoice > 3);

        User user = null;

        switch (roleChoice) {
            case 1:
                user = new Admin(username, password, email, phoneNumber, address, emergencyContactName, emergencyContactPhone);
                break;
            case 2:
                user = new Trainer(username, password, email, phoneNumber, address, emergencyContactName, emergencyContactPhone);
                break;
            case 3:
                user = new Member(username, password, email, phoneNumber, address, emergencyContactName, emergencyContactPhone);
                break;
        }

        if (user != null) {
            try {
                boolean created = userService.createNewUser(user);
                if (created) {
                    System.out.println("User registered successfully!");
                } else {
                    System.out.println("Failed to register user. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error registering user: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /*
    Login an existing user
    */
    private static void loginUser(Scanner scanner, UserService userService, MembershipService membershipService, WorkoutClassService workoutClassService) {
        System.out.println("\n=== User Login ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.getUserByUserName(username);

            if (user != null && BCryptUtil.checkPassword(password, user.getUserPassword())) {
                System.out.println("Login successful! Welcome, " + user.getUserName() + "!");

                switch (user.getUserRole().toLowerCase()) {
                    case "admin":
                        showAdminMenu(scanner, user, userService, membershipService, workoutClassService);
                        break;
                    case "trainer":
                        showTrainerMenu(scanner, user, userService, membershipService, workoutClassService);
                        break;
                    case "member":
                        showMemberMenu(scanner, user, userService, membershipService, workoutClassService);
                        break;
                    default:
                        System.out.println("Unknown user role: " + user.getUserRole());
                        break;
                }
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
    Display and handle admin menu options
    */
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutClassService) throws SQLException {
        int choice;

        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View all memberships");
            System.out.println("4. View total revenue from memberships");
            System.out.println("5. View all workout classes");
            System.out.println("6. Purchase a membership for yourself");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View all users
                    System.out.println("\n=== All Users ===");
                    userService.getAllUsers();
                    break;
                case 2:
                    // Delete a user
                    System.out.println("\n=== Delete User ===");
                    System.out.print("Enter user ID to delete: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Are you sure you want to delete this user? (y/n): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("y")) {
                        userService.deleteUser(userId);
                        System.out.println("User deleted successfully!");
                    } else {
                        System.out.println("User deletion cancelled.");
                    }
                    break;
                case 3:
                    // View all memberships
                    System.out.println("\n=== All Memberships ===");
                    List<Membership> allMemberships = membershipService.getAllMemberships();

                    if (allMemberships.isEmpty()) {
                        System.out.println("No memberships found.");
                    } else {
                        for (Membership membership : allMemberships) {
                            System.out.println(membership);
                        }
                    }
                    break;
                case 4:
                    // View total revenue
                    List<Membership> memberships = membershipService.getAllMemberships();
                    double totalRevenue = 0.0;

                    for (Membership membership : memberships) {
                        totalRevenue += membership.getMembershipCost();
                    }

                    System.out.println("\n=== Total Membership Revenue ===");
                    System.out.printf("Total Revenue: $%.2f%n", totalRevenue);
                    break;
                case 5:
                    // View all workout classes
                    System.out.println("\n=== All Workout Classes ===");
                    workoutClassService.displayAllWorkoutClasses();
                    break;
                case 6:
                    // Purchase a membership for admin
                    purchaseMembership(scanner, user, membershipService);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 7);
    }

    /*
    Display and handle trainer menu options
    */
    private static void showTrainerMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutClassService) throws SQLException {
        int choice;

        do {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("1. Create a new workout class");
            System.out.println("2. Update a workout class");
            System.out.println("3. Delete a workout class");
            System.out.println("4. View my workout classes");
            System.out.println("5. Purchase a membership");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create a new workout class
                    createWorkoutClass(scanner, user, workoutClassService);
                    break;
                case 2:
                    // Update a workout class
                    updateWorkoutClass(scanner, user, workoutClassService);
                    break;
                case 3:
                    // Delete a workout class
                    deleteWorkoutClass(scanner, user, workoutClassService);
                    break;
                case 4:
                    // View trainer's classes
                    System.out.println("\n=== My Workout Classes ===");
                    // Assuming the userId is stored in the trainerId field
                    workoutClassService.displayClassesByTrainerId(user.getUserId());
                    break;
                case 5:
                    // Purchase a membership
                    purchaseMembership(scanner, user, membershipService);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 6);
    }

    /*
    Create a new workout class
    */
    private static void createWorkoutClass(Scanner scanner, User user, WorkoutClassService workoutClassService) {
        System.out.println("\n=== Create Workout Class ===");

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter class description: ");
        String classDescription = scanner.nextLine();

        // Create a new workout class with the trainer's ID
        WorkoutClass workoutClass = new WorkoutClass(
                classType,
                classDescription,
                user.getUserId()
        );

        try {
            boolean created = workoutClassService.createWorkoutClass(workoutClass);
            if (created) {
                System.out.println("Workout class created successfully!");
            } else {
                System.out.println("Failed to create workout class.");
            }
        } catch (Exception e) {
            System.out.println("Error creating workout class: " + e.getMessage());
        }
    }

    /*
    Update an existing workout class
    */
    private static void updateWorkoutClass(Scanner scanner, User user, WorkoutClassService workoutClassService) throws SQLException {
        System.out.println("\n=== Update Workout Class ===");


        workoutClassService.displayClassesByTrainerId(user.getUserId());

        System.out.print("Enter the ID of the class to update: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        WorkoutClass existingClass = workoutClassService.getWorkoutClassById(classId);

        if (existingClass == null) {
            System.out.println("Workout class not found.");
            return;
        }

        if (existingClass.getTrainerId() != user.getUserId()) {
            System.out.println("You can only update your own classes.");
            return;
        }

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Class Type");
        System.out.println("2. Class Description");
        System.out.print("Enter your choice: ");

        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new class type: ");
                String newType = scanner.nextLine();
                workoutClassService.updateWorkoutClassType(classId, newType);
                System.out.println("Class type updated successfully!");
                break;
            case 2:
                System.out.print("Enter new class description: ");
                String newDescription = scanner.nextLine();
                workoutClassService.updateWorkoutClassDescription(classId, newDescription);
                System.out.println("Class description updated successfully!");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /*
    Delete a workout class
    */
    private static void deleteWorkoutClass(Scanner scanner, User user, WorkoutClassService workoutClassService) throws SQLException {
        System.out.println("\n=== Delete Workout Class ===");

        workoutClassService.displayClassesByTrainerId(user.getUserId());

        System.out.print("Enter the ID of the class to delete: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        WorkoutClass existingClass = workoutClassService.getWorkoutClassById(classId);

        if (existingClass == null) {
            System.out.println("Workout class not found.");
            return;
        }

        if (existingClass.getTrainerId() != user.getUserId()) {
            System.out.println("You can only delete your own classes.");
            return;
        }

        System.out.print("Are you sure you want to delete this class? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            boolean deleted = workoutClassService.deleteWorkoutClass(classId);
            if (deleted) {
                System.out.println("Workout class deleted successfully!");
            } else {
                System.out.println("Failed to delete workout class.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    /*
    Display and handle member menu options
    */
    private static void showMemberMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutClassService) throws SQLException {
        int choice;

        do {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. Browse workout classes");
            System.out.println("2. Enroll in a workout class");
            System.out.println("3. View my membership");
            System.out.println("4. Purchase a membership");
            System.out.println("5. View my total membership expenses");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Browse workout classes
                    System.out.println("\n=== Available Workout Classes ===");
                    workoutClassService.displayAllWorkoutClasses();
                    break;
                case 2:
                    // Enroll in a class
                    enrollInClass(scanner, user, workoutClassService);
                    break;
                case 3:
                    // View membership
                    viewMembershipDetails(user, membershipService);
                    break;
                case 4:
                    // Purchase membership
                    purchaseMembership(scanner, user, membershipService);
                    break;
                case 5:
                    // View total membership expenses
                    viewMembershipExpenses(user, membershipService);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 6);
    }

    /*
    Enroll a member in a workout class
    */
    private static void enrollInClass(Scanner scanner, User user, WorkoutClassService workoutClassService) throws SQLException {
        System.out.println("\n=== Enroll in Workout Class ===");

        // Display available classes
        workoutClassService.displayAllWorkoutClasses();

        System.out.print("Enter the ID of the class to enroll in: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        try {
            boolean enrolled = workoutClassService.enrollMemberInClass(
                    user.getUserId(),
                    classId
            );

            if (enrolled) {
                System.out.println("Enrolled in class successfully!");
            } else {
                System.out.println("Failed to enroll in class.");
            }
        } catch (SQLException e) {
            System.out.println("Error enrolling in class: " + e.getMessage());
        }
    }

    /*
    View a member's membership details
    */
    private static void viewMembershipDetails(User user, MembershipService membershipService) {
        System.out.println("\n=== My Membership Details ===");

        List<Membership> memberships = membershipService.getAllMemberships();
        boolean hasMembership = false;

        for (Membership membership : memberships) {
            if (membership.getMemberID() == user.getUserId()) {
                System.out.println(membership);
                hasMembership = true;
            }
        }

        if (!hasMembership) {
            System.out.println("You don't have any active memberships.");
        }
    }

    /*
    View a member's total membership expenses
     */
    private static void viewMembershipExpenses(User user, MembershipService membershipService) {
        System.out.println("\n=== My Membership Expenses ===");

        List<Membership> memberships = membershipService.getAllMemberships();
        double totalExpenses = 0.0;

        for (Membership membership : memberships) {
            if (membership.getMemberID() == user.getUserId()) {
                totalExpenses += membership.getMembershipCost();
            }
        }

        System.out.printf("Total Expenses: $%.2f%n", totalExpenses);
    }

    /*
    Purchase a new membership
    */
    private static void purchaseMembership(Scanner scanner, User user, MembershipService membershipService) {
        System.out.println("\n=== Purchase Membership ===");
        System.out.println("Available Membership Types:");

        MembershipType[] types = MembershipType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i] + " - $" + types[i].getCost());
        }

        System.out.print("Select membership type (1-" + types.length + "): ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        if (typeChoice < 1 || typeChoice > types.length) {
            System.out.println("Invalid choice!");
            return;
        }

        MembershipType selectedType = types[typeChoice - 1];

        System.out.print("Enter description for this membership: ");
        String description = scanner.nextLine();

        // Generate a random ID for the membership
        int membershipId = (int) (Math.random() * 10000);

        Membership membership = new Membership(
                membershipId,
                selectedType,
                description,
                user.getUserId()
        );

        boolean added = membershipService.addMembership(membership);

        if (added) {
            System.out.println("Membership purchased successfully!");
        } else {
            System.out.println("Failed to purchase membership.");
        }
    }

    /*
    BCrypt password checking
    */
    private static class BCryptUtil {
        public static boolean checkPassword(String plainPassword, String hashedPassword) {
            return org.mindrot.jbcrypt.BCrypt.checkpw(plainPassword, hashedPassword);
        }
    }
}