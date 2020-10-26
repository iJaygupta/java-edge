/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.pattern;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author admis
 */
public class PatternVerify {
    
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{5,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$])(?=\\S+$).{8,20}$";
    private static final String NAME_PATTERN = "^[\\p{L} .'-]+$";
    private static final String EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"; 
    private static final String ADDRESS_PATTERN = "";
    private static final String NUMBER_PATTERN = "[0-9]{10}";
    private static final String OTP_PATTERN = "[0-9]{6}";
    private static final String PINCODE_PATTERN = "[0-9]{6}";
    private static final String GPS_LOCATION_PATTERN = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$";
    private static final String GST_PATTERN = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
    private static final String PAN_PATTERN = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$";
    private static final String CIN_PATTERN = "^([L|U]{1})([0-9]{5})([A-Za-z]{2})([0-9]{4})([A-Za-z]{3})([0-9]{6})$";
    
    private static Pattern pattern;
    private static Matcher matcher;
    
    public static boolean checkUsernamePattern(String username){
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
	return matcher.matches();
    }
    
    public static boolean checkPasswordPattern(String password){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
	return matcher.matches();
    }
    
    public static boolean checkNumberPattern(String number){
        pattern = Pattern.compile(NUMBER_PATTERN);
        matcher = pattern.matcher(number);
	return matcher.matches();
    }
    
    public static boolean checkEmailPattern(String email){
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
	return matcher.matches();
    }
    
    public static boolean checkNamePattern(String name){
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(name);
	return matcher.matches();
    }
    
    public static boolean checkOtpPattern(String otp){
        pattern = Pattern.compile(OTP_PATTERN);
        matcher = pattern.matcher(otp);
	return matcher.matches();
    }
    
    public static boolean checkPincodePattern(String pincode){
        pattern = Pattern.compile(PINCODE_PATTERN);
        matcher = pattern.matcher(pincode);
	return matcher.matches();
    }
    
    public static boolean checkGPSLocationPattern(String location){
        pattern = Pattern.compile(GPS_LOCATION_PATTERN);
        matcher = pattern.matcher(location);
	return matcher.matches();
    }
    
    public static boolean checkGSTPattern(String gst){
        pattern = Pattern.compile(GST_PATTERN);
        matcher = pattern.matcher(gst);
	return matcher.matches();
    }
    
    public static boolean checkPanPattern(String pan){
        pattern = Pattern.compile(PAN_PATTERN);
        matcher = pattern.matcher(pan);
	return matcher.matches();
    }
    
    public static boolean checkCINPattern(String cin){
        pattern = Pattern.compile(CIN_PATTERN);
        matcher = pattern.matcher(cin);
	return matcher.matches();
    }
}
