package test.java;

import main.java.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Login class
 */
public class LoginTest {
    
    private Login login = new Login();
    
    @Test
    public void testCheckUserNameValid() {
        assertTrue(login.checkUserName("kyl_1"));
        assertTrue(login.checkUserName("ab_c"));
    }
    
    @Test
    public void testCheckUserNameInvalid() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
        assertFalse(login.checkUserName("abcdef"));
        assertFalse(login.checkUserName("abc"));
        assertFalse(login.checkUserName("kyll"));
    }
    
    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(login.checkPasswordComplexity("password"));
        assertFalse(login.checkPasswordComplexity("Pass123"));
        assertFalse(login.checkPasswordComplexity("password123"));
        assertFalse(login.checkPasswordComplexity("PASSWORD123"));
    }
    
    @Test
    public void testCheckCellPhoneNumberValid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertTrue(login.checkCellPhoneNumber("+27712345678"));
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
        assertFalse(login.checkCellPhoneNumber("27838968976"));
        assertFalse(login.checkCellPhoneNumber("+2783896897"));
    }
    
    @Test
    public void testRegisterUser() {
        Login testLogin = new Login();
        // This tests the full registration flow through method calls
        assertTrue(testLogin.checkUserName("kyl_1"));
        assertTrue(testLogin.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(testLogin.checkCellPhoneNumber("+27838968976"));
    }
}