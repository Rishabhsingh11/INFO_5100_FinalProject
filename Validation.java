/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Validation;

import java.util.regex.Pattern;

/**
 *
 * @author rutu
 */
public class Validation {
    
    public static boolean isValidEmail(String email)
    {
        String emailRegex = Constants.EmailRegex;
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
     public static boolean isValidMobileNumber(String phone_no)
    {
        String mobileRegex = Constants.mobileRegex;
                              
        Pattern pat = Pattern.compile(mobileRegex);
        if (phone_no == null)
            return false;
        return pat.matcher(phone_no).matches();
    }
    
     public static boolean isValidUsername(String username)
    {
                              
        Pattern pat = Pattern.compile(Constants.usernameRegex);
        if (username == null)
            return false;
        return pat.matcher(username).matches();
    }
    
    public static boolean isValidPassword(String password)
    {
                              
        Pattern pat = Pattern.compile(Constants.passwordRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();
    }
}
