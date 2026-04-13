package com.chatapp.ui;

import com.chatapp.models.Login;
import java.util.Scanner;

/**
 * Main application class for the Chat App
 * Handles user interaction through console
 * @author Student
 * @version 1.0
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Login loginSystem = new Login();
    
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    WELCOME TO THE CHAT APPLICATION     ");
        System.out.println("=========================================\n");
        
        boolean exitProgram = false;
        
        while (!exitProgram) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Register New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. Exit Application");
            System.out.print("Please select an option (1-3): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "3":
                    exitProgram = true;
                    System.out.println("Thank you for using the Chat Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Handles user registration process
     */
    private static void registerUser() {
        System.out.println("\n--- USER REGISTRATION ---");
        
        // Get user details
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Username (must contain _ and max 5 characters): ");
        String username = scanner.nextLine();
        
        System.out.println("Password Requirements:");
        System.out.println("- At least 8 characters");
        System.out.println("- At least one capital letter");
        System.out.println("- At least one number");
        System.out.println("- At least one special character");
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter Cell Phone Number (format: +27831234567): ");
        String cellPhone = scanner.nextLine();
        
        // Attempt registration
        String registrationResult = loginSystem.registerUser(username, password, cellPhone, 
                                                            firstName, lastName);
        
        System.out.println("\n" + registrationResult);
        
        if (registrationResult.equals("User successfully registered.")) {
            System.out.println("\nYou may now login using your credentials.");
        }
    }
    
    /**
     * Handles user login process
     */
    private static void loginUser() {
        System.out.println("\n--- USER LOGIN ---");
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        // Attempt login
        String loginStatus = loginSystem.returnLoginStatus(username, password);
        
        System.out.println("\n" + loginStatus);
        
        if (loginStatus.contains("Welcome")) {
            showLoggedInMenu();
        }
    }
    
    /**
     * Displays menu options for logged-in users
     */
    private static void showLoggedInMenu() {
        boolean backToMain = false;
        
        while (!backToMain) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. View Profile");
            System.out.println("2. Logout");
            System.out.print("Please select an option (1-2): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.println("\n--- PROFILE INFORMATION ---");
                    System.out.println("Username: " + loginSystem.getUsername());
                    System.out.println("Cell Phone: " + loginSystem.getCellPhoneNumber());
                    break;
                case "2":
                    backToMain = true;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1 or 2.");
            }
        }
    }
}