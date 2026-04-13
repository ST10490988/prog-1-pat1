package com.chatapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Login class methods
 * @author Student
 * @version 1.0
 */
public class LoginTest {
    
    private Login login;
    
    @BeforeEach
    public void setUp() {
        login = new Login();
    }
    
    // ========== USERNAME TESTS ==========
    
    @Test
    public void testCheckUserName_CorrectFormat_ReturnsTrue() {
        // Test Data: "kyl_1"
        String testUsername = "kyl_1";
        
        boolean result = login.checkUserName(testUsername);
        
        assertTrue(result, "Username with underscore and <=5 characters should return true");
    }
    
    @Test
    public void testCheckUserName_NoUnderscore_ReturnsFalse() {
        // Test Data: "kyle!!!!!!!"
        String testUsername = "kyle!!!!!!!";
        
        boolean result = login.checkUserName(testUsername);
        
        assertFalse(result, "Username without underscore should return false");
    }
    
    @Test
    public void testCheckUserName_TooLong_ReturnsFalse() {
        // Test Data: username longer than 5 characters
        String testUsername = "kyle_12";
        
        boolean result = login.checkUserName(testUsername);
        
        assertFalse(result, "Username longer than 5 characters should return false");
    }
    
    // ========== PASSWORD TESTS ==========
    
    @Test
    public void testCheckPasswordComplexity_MeetsRequirements_ReturnsTrue() {
        // Test Data: "Ch&&sec@ke99!"
        String testPassword = "Ch&&sec@ke99!";
        
        boolean result = login.checkPasswordComplexity(testPassword);
        
        assertTrue(result, "Password meeting all requirements should return true");
    }
    
    @Test
    public void testCheckPasswordComplexity_SimplePassword_ReturnsFalse() {
        // Test Data: "password"
        String testPassword = "password";
        
        boolean result = login.checkPasswordComplexity(testPassword);
        
        assertFalse(result, "Simple password without complexity should return false");
    }
    
    @Test
    public void testCheckPasswordComplexity_NoCapitalLetter_ReturnsFalse() {
        // Test Data: "ch&&sec@ke99!"
        String testPassword = "ch&&sec@ke99!";
        
        boolean result = login.checkPasswordComplexity(testPassword);
        
        assertFalse(result, "Password without capital letter should return false");
    }
    
    @Test
    public void testCheckPasswordComplexity_NoNumber_ReturnsFalse() {
        // Test Data: "Ch&&sec@ke!!"
        String testPassword = "Ch&&sec@ke!!";
        
        boolean result = login.checkPasswordComplexity(testPassword);
        
        assertFalse(result, "Password without number should return false");
    }
    
    @Test
    public void testCheckPasswordComplexity_NoSpecialChar_ReturnsFalse() {
        // Test Data: "Chsecake99"
        String testPassword = "Chsecake99";
        
        boolean result = login.checkPasswordComplexity(testPassword);
        
        assertFalse(result, "Password without special character should return false");
    }
    
    @Test
    public void testCheckPasswordComplexity_TooShort_ReturnsFalse() {
        // Test Data: "Ch&&9!"
        String testPassword = "Ch&&9!";
        
        boolean result = login.checkPasswordComplexity(testPassword);
        
        assertFalse(result, "Password shorter than 8 characters should return false");
    }
    
    // ========== PHONE NUMBER TESTS ==========
    
    @Test
    public void testCheckCellPhoneNumber_CorrectFormat_ReturnsTrue() {
        // Test Data: "+27838968976"
        String testPhone = "+27838968976";
        
        boolean result = login.checkCellPhoneNumber(testPhone);
        
        assertTrue(result, "Correctly formatted SA cell number should return true");
    }
    
    @Test
    public void testCheckCellPhoneNumber_IncorrectFormat_ReturnsFalse() {
        // Test Data: "08966553"
        String testPhone = "08966553";
        
        boolean result = login.checkCellPhoneNumber(testPhone);
        
        assertFalse(result, "Incorrectly formatted phone number should return false");
    }
    
    @Test
    public void testCheckCellPhoneNumber_NoInternationalCode_ReturnsFalse() {
        // Test Data: "0838968976"
        String testPhone = "0838968976";
        
        boolean result = login.checkCellPhoneNumber(testPhone);
        
        assertFalse(result, "Phone number without +27 prefix should return false");
    }
    
    // ========== REGISTRATION TESTS WITH ASSERTEQUALS ==========
    
    @Test
    public void testRegisterUser_SuccessfulRegistration() {
        // Test Data
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        String result = login.registerUser(username, password, phone, firstName, lastName);
        
        assertEquals("User successfully registered.", result);
    }
    
    @Test
    public void testRegisterUser_InvalidUsername() {
        // Test Data: "kyle!!!!!!!"
        String username = "kyle!!!!!!!";
        String password = "Ch&&sec@ke99!";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        String expectedMessage = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        
        String result = login.registerUser(username, password, phone, firstName, lastName);
        
        assertEquals(expectedMessage, result);
    }
    
    @Test
    public void testRegisterUser_InvalidPassword() {
        // Test Data: "password"
        String username = "kyl_1";
        String password = "password";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        String expectedMessage = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        
        String result = login.registerUser(username, password, phone, firstName, lastName);
        
        assertEquals(expectedMessage, result);
    }
    
    @Test
    public void testRegisterUser_InvalidPhoneNumber() {
        // Test Data: "08966553"
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "08966553";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        String expectedMessage = "Cell phone number incorrectly formatted or does not contain international code.";
        
        String result = login.registerUser(username, password, phone, firstName, lastName);
        
        assertEquals(expectedMessage, result);
    }
    
    // ========== LOGIN TESTS WITH ASSERTTRUE/ASSERTFALSE ==========
    
    @Test
    public void testLoginUser_SuccessfulLogin_ReturnsTrue() {
        // First register a user
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        login.registerUser(username, password, phone, firstName, lastName);
        
        // Test login with correct credentials
        boolean result = login.loginUser(username, password);
        
        assertTrue(result, "Login should succeed with correct credentials");
    }
    
    @Test
    public void testLoginUser_FailedLogin_ReturnsFalse() {
        // First register a user
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        login.registerUser(username, password, phone, firstName, lastName);
        
        // Test login with incorrect password
        boolean result = login.loginUser(username, "wrongpassword");
        
        assertFalse(result, "Login should fail with incorrect credentials");
    }
    
    @Test
    public void testReturnLoginStatus_SuccessfulLogin() {
        // First register a user
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        login.registerUser(username, password, phone, firstName, lastName);
        
        // Test login status message
        String result = login.returnLoginStatus(username, password);
        String expectedMessage = "Welcome Kyle, Smith it is great to see you again.";
        
        assertEquals(expectedMessage, result);
    }
    
    @Test
    public void testReturnLoginStatus_FailedLogin() {
        // First register a user
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        String phone = "+27838968976";
        String firstName = "Kyle";
        String lastName = "Smith";
        
        login.registerUser(username, password, phone, firstName, lastName);
        
        // Test login status message with wrong password
        String result = login.returnLoginStatus(username, "wrongpassword");
        String expectedMessage = "Username or password incorrect, please try again.";
        
        assertEquals(expectedMessage, result);
    }
}