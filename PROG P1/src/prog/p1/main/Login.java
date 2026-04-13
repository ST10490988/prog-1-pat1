package com.chatapp.models;

/**
 * Login class handles user registration and authentication
 * @author Student
 * @version 1.0
 */
public class Login {
    
    // Instance variables to store user credentials
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    // Stored registered user credentials for login verification
    private static String registeredUsername;
    private static String registeredPassword;
    private static String registeredCellPhone;
    private static String registeredFirstName;
    private static String registeredLastName;
    
    /**
     * Default constructor
     */
    public Login() {
    }
    
    /**
     * Constructor with user details
     */
    public Login(String username, String password, String cellPhoneNumber, 
                 String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /**
     * Checks if username contains underscore and is no more than 5 characters
     * @param username The username to validate
     * @return true if username is correctly formatted, false otherwise
     */
    public boolean checkUserName(String username) {
        // Check if username is null
        if (username == null) {
            return false;
        }
        
        // Check if username contains underscore AND length <= 5
        return username.contains("_") && username.length() <= 5;
    }
    
    /**
     * Checks if password meets complexity requirements:
     * - At least 8 characters long
     * - Contains at least one capital letter
     * - Contains at least one number
     * - Contains at least one special character
     * @param password The password to validate
     * @return true if password meets all requirements, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        // Check if password is null
        if (password == null) {
            return false;
        }
        
        // Check minimum length
        if (password.length() < 8) {
            return false;
        }
        
        // Check for capital letter using regex
        // Reference: Java Regular Expressions documentation (Oracle, 2023)
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
    /**
     * Validates South African cell phone number with international code
     * Reference: Adapted from Stack Overflow - Regex for phone number validation
     * (Stack Overflow, 2022)
     * @param phoneNumber The phone number to validate
     * @return true if phone number is correctly formatted, false otherwise
     */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Check if phone number is null
        if (phoneNumber == null) {
            return false;
        }
        
        // Regular expression for South African cell phone number with +27 prefix
        // Pattern: +27 followed by 9 digits (total 12 characters including +)
        // Reference: ITU-T E.164 international phone number format
        String phoneRegex = "^\\+27[6-8][0-9]{8}$";
        
        return phoneNumber.matches(phoneRegex);
    }
    
    /**
     * Registers a user by validating credentials and storing them
     * @param username The username to register
     * @param password The password to register
     * @param cellPhoneNumber The cell phone number to register
     * @param firstName User's first name
     * @param lastName User's last name
     * @return Registration status message
     */
    public String registerUser(String username, String password, String cellPhoneNumber, 
                               String firstName, String lastName) {
        
        // Validate username
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        // Validate password
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        // Validate cell phone number
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // Store the validated credentials for login purposes
        registeredUsername = username;
        registeredPassword = password;
        registeredCellPhone = cellPhoneNumber;
        registeredFirstName = firstName;
        registeredLastName = lastName;
        
        // Store in instance variables as well
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "User successfully registered.";
    }
    
    /**
     * Verifies login credentials against stored registration data
     * @param username The username entered for login
     * @param password The password entered for login
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String username, String password) {
        // Check if any user has been registered
        if (registeredUsername == null || registeredPassword == null) {
            return false;
        }
        
        // Compare entered credentials with stored credentials
        return registeredUsername.equals(username) && registeredPassword.equals(password);
    }
    
    /**
     * Returns appropriate message based on login success or failure
     * @param username The username entered for login
     * @param password The password entered for login
     * @return Login status message
     */
    public String returnLoginStatus(String username, String password) {
        boolean loginSuccess = loginUser(username, password);
        
        if (loginSuccess) {
            return "Welcome " + registeredFirstName + ", " + registeredLastName + 
                   " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Getters and setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
    
    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}