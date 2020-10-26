/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.model.Admin;
import co.admis.model.AdminOTP;
import co.admis.model.Login;
import co.admis.model.Organization;
import co.admis.model.User;
import co.admis.model.UserOTP;
import co.admis.security.UserLoginAndSecurityCheck;
import co.admis.service.AdminService;
import co.admis.service.AuthenticationService;
import co.admis.service.CommonMethodsService;
import co.admis.service.FirebaseUserService;
import co.admis.service.OrganizationService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import co.admis.service.UserService;
import co.admis.service.mail.MailjetService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.SessionCookieOptions;
import com.google.firebase.auth.UserRecord;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Controller
public class Authentication {
    @Autowired
    AuthenticationService authenticationService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    SmsService smsService;
    
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    SecurityService securityService;       
    
    @Autowired
    FirebaseUserService firebaseUserService;   

    @Autowired
    OrganizationService organizationService; 
    
    @Autowired
    MailjetService mailjetService; 
        
        @RequestMapping(value = {"/admin/adminPasswordChange"}, method = RequestMethod.POST)
        public @ResponseBody
        String adminPasswordChange(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession();
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();        
            if(check.adminAuth(request)){
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                if(oldPassword.equalsIgnoreCase(newPassword)){
                    return commonMethodsService.convertObjectIntoJson("Please enter new password");
                }   
                Login login = new Login();
                login.setUsername(session.getAttribute("admin").toString());
                login.setPassword(oldPassword);
                String a = authenticationService.adminAuthentication(login);
                if(a.equals(login.getUsername())){
                    Admin admin = adminService.getAdminByUsername(a);
                    admin.setPassword(securityService.encrypt(newPassword ,securityService.getPublicKeyByAdminUsername(a)));
                    adminService.updateAdmin(admin);
                    return commonMethodsService.convertObjectIntoJson("Success");
                }else{
                    return commonMethodsService.convertObjectIntoJson("Old password didn't match");
                }
            }
            return commonMethodsService.convertObjectIntoJson("Session out");
        }
        
        @RequestMapping(value = {"/authenticateUser"}, method = RequestMethod.POST)
        public @ResponseBody
        String authenticateUser(HttpServletRequest request, HttpServletResponse response) {
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();        
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"), null);
            if(security.equalsIgnoreCase("passed")){
                return commonMethodsService.convertObjectIntoJson("passed");
            }else if(security.equalsIgnoreCase("session out")){
                //Pop up for security pin
                return commonMethodsService.convertObjectIntoJson("pin");
            }
            return commonMethodsService.convertObjectIntoJson(security);
        }
        
        @RequestMapping(value = {"/verifyPin"}, method = RequestMethod.POST)
        public @ResponseBody
        String verifyPin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String error = "";
            String pin = request.getParameter("pin");
            // Get Firebase 
            final boolean checkRevoked = true;
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifySessionCookie(
            commonMethodsService.getCookieValueByName(request, "session"), checkRevoked);    
            HttpSession session = request.getSession();
            String uid = decodedToken.getUid();
                //Get user record
                UserRecord userRecord = firebaseUserService.getUserRecordByUid(uid);
                if(userRecord!=null){
                    String number = userRecord.getPhoneNumber();
                    Admin admin = adminService.getAdminByNumber(number);
                    if(admin!=null){
                        if(admin.getStatus().equalsIgnoreCase("active")){
                            Organization organization = organizationService.getOrganizationById(admin.getOrganizationId());
                            if(organization.getIpEnable()==0 || adminService.getAccountIpWhiteListByIp(request.getRemoteAddr(), admin.getUsername())!=null){
                                if(session!=null && session.getAttribute("admin")!=null){            
                                    if(session.getAttribute("admin").toString().equals(admin.getUsername())){
                                        return commonMethodsService.convertObjectIntoJson("passed");
                                    }
                                }else{
                                    if(pin.equals(securityService.decrypt(admin.getPassword(), securityService.getPrivateKeyByAdminUsername(admin.getUsername())))){
                                        session.setAttribute("admin",admin.getUsername());
                                        session.setAttribute("role", "admin");
                                        session.removeAttribute("user");
                                        session.setMaxInactiveInterval(7200);
                                        //Update Admin
                                        admin.setCount(0);
                                        adminService.updateAdmin(admin);
                                        return commonMethodsService.convertObjectIntoJson("passed");
                                    }else{
                                        //Wrong attampt
                                        if(admin.getCount()>5){
                                            //Block Account
                                            admin.setStatus("block");
                                            admin.setCount(0);
                                            adminService.updateAdmin(admin);
                                            return commonMethodsService.convertObjectIntoJson("Account blocked");
                                        }else{
                                            admin.setCount(admin.getCount()+1);
                                            adminService.updateAdmin(admin);
                                            return commonMethodsService.convertObjectIntoJson("Pin is not correct");
                                        }
                                    }
                                }
                            }else{
                                error = "Ip is not whitelisted";
                            }
                        }else if(admin.getStatus().equalsIgnoreCase("block")){
                            return "Your account is block please reset your pin.";
                        }else{
                            error = "Accounts are blocked by company";
                        }
                    }else{
                        User user = userService.getUserByNumber(number);
                        if(user!=null){
                            if(user.getStatus().equalsIgnoreCase("active")){
                                Admin a = adminService.getAdminByUsername(user.getAdminUsername());
                                if(a!=null && !a.getStatus().equalsIgnoreCase("disabled")){
                                    Organization organization = organizationService.getOrganizationById(a.getOrganizationId());
                                    if(organization.getIpEnable()==0 || adminService.getAccountIpWhiteListByIp(request.getRemoteAddr(), a.getUsername())!=null){
                                        if(session!=null && session.getAttribute("user")!=null && session.getAttribute("user").toString().equalsIgnoreCase(number)){
                                            return commonMethodsService.convertObjectIntoJson("passed");
                                        }else{
                                            if(pin.equals(securityService.decrypt(user.getPassword(), securityService.getPrivateKeyByAdminUsername(user.getAdminUsername())))){
                                                session.setAttribute("user",user.getNumber());
                                                session.setAttribute("role", "user");
                                                session.removeAttribute("admin");
                                                session.setMaxInactiveInterval(7200);
                                                //Update Admin
                                                user.setCount(0);
                                                userService.updateUser(user);
                                                return commonMethodsService.convertObjectIntoJson("passed");
                                            }else{
                                                //Wrong attampt
                                                if(user.getCount()>5){
                                                    //Block Account
                                                    user.setStatus("block");
                                                    user.setCount(0);
                                                    userService.updateUser(user);
                                                    return commonMethodsService.convertObjectIntoJson("Account blocked");
                                                }else{
                                                    user.setCount(user.getCount()+1);
                                                    userService.updateUser(user);
                                                    return commonMethodsService.convertObjectIntoJson("Pin is not correct");
                                                }
                                            }
                                        }
                                    }else{
                                        error = "Ip is not whitelisted";
                                    }
                                }else{
                                    error = "Accounts are blocked by company";
                                }
                            }else if(user.getStatus().equalsIgnoreCase("block")){
                                return "Your account is blocked please reset your pin.";
                            }else{
                                error = "Account is blocked";
                            }
                        }else{
                            firebaseUserService.revokeUserSession(uid);
                            error = "User is not found/verified";
                        }
                    }
                }else{
                    error = "User is not found/verified";
                }
            
            session.removeAttribute("user");
            session.removeAttribute("admin");
            session.removeAttribute("role");
            session.invalidate();
            return commonMethodsService.convertObjectIntoJson(error);
        } catch (FirebaseAuthException ex) { 
            System.out.println(ex);
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            return commonMethodsService.convertObjectIntoJson(ex.getMessage());
        }
        }
        
        @RequestMapping(value = {"/sessionCookie"}, method = RequestMethod.POST)
        public @ResponseBody
        String sessionCookie(HttpServletRequest request, HttpServletResponse response) throws FirebaseAuthException {
            try{
                String idToken = request.getParameter("idToken");
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken, true);
                System.out.println(decodedToken.getUid());
                long authTimeMillis = TimeUnit.SECONDS.toMillis(
                    (long) decodedToken.getClaims().get("auth_time"));

                // Only process if the user signed in within the last 5 minutes.
                if (System.currentTimeMillis() - authTimeMillis < TimeUnit.MINUTES.toMillis(5)) {
                  long expiresIn = TimeUnit.DAYS.toMillis(13);
                  SessionCookieOptions options = SessionCookieOptions.builder()
                      .setExpiresIn(expiresIn)
                      .build();
                  String sessionCookie = FirebaseAuth.getInstance().createSessionCookie(idToken, options);
                  // Set cookie policy parameters as required.
                  Cookie ck=new Cookie("session", sessionCookie);//creating cookie object  
                  ck.setHttpOnly(true); 
                  response.addCookie(ck);//adding cookie in the response  
                  return commonMethodsService.convertObjectIntoJson("success");
                }
                // User did not sign in recently. To guard against ID token theft, require
                // re-authentication.
                firebaseUserService.revokeUserSession(decodedToken.getUid());
                return commonMethodsService.convertObjectIntoJson("failed");
            }catch(Exception e){
                System.out.println(e);
                return commonMethodsService.convertObjectIntoJson(e.getMessage());
            }
        }
        
        @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
        public String logout(HttpServletRequest request, HttpServletResponse response) {
            FirebaseToken decodedToken = null;
            try {    
                decodedToken = FirebaseAuth.getInstance().verifySessionCookie(
                        commonMethodsService.getCookieValueByName(request, "session"), true);
            } catch (FirebaseAuthException ex) {
                Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            }
            Cookie ck = commonMethodsService.getCookieByName(request, "session");
            if(ck!=null){
                ck.setMaxAge(-1);
                response.addCookie(ck);//adding cookie in the response  
            }
            if(decodedToken!=null){
                String uid = decodedToken.getUid();
                firebaseUserService.revokeUserSession(uid);
            }
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.removeAttribute("admin");
            session.removeAttribute("role");
            session.invalidate();
            return "redirect:/index";
        }
        
        @RequestMapping(value = {"/changePin"}, method = RequestMethod.POST)
        public @ResponseBody
        String changePin(HttpServletRequest request, HttpServletResponse response) {
            try {
            String error = "";
            String otp = request.getParameter("otp");
            // Get Firebase 
            final boolean checkRevoked = true;
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifySessionCookie(
            commonMethodsService.getCookieValueByName(request, "session"), checkRevoked);    
            HttpSession session = request.getSession();
            String uid = decodedToken.getUid();
                //Get user record
                UserRecord userRecord = firebaseUserService.getUserRecordByUid(uid);
                if(userRecord!=null){
                    String number = userRecord.getPhoneNumber();
                    Admin admin = adminService.getAdminByNumber(number);
                    if(admin!=null){
                        if(!admin.getStatus().equalsIgnoreCase("disabled")){
                            Organization organization = organizationService.getOrganizationById(admin.getOrganizationId());
                            if(organization.getIpEnable()==0 || adminService.getAccountIpWhiteListByIp(request.getRemoteAddr(), admin.getUsername())!=null){
                                AdminOTP adminOTP = smsService.getAdminOTP(number);
                                if(adminOTP!=null){
                                    if(session!=null && session.getAttribute("otpkey")!=null && session.getAttribute("otpkey").toString().equalsIgnoreCase(adminOTP.getOtpKey())){
                                        if(smsService.compareTimeForMinutes(adminOTP.getTimestamp(), 10)){
                                            //Update count, timestamp and OTP
                                            if(otp.equals(adminOTP.getOtp())){
                                                //Verified OTP, Generate New Password
                                                if(!admin.getStatus().equalsIgnoreCase("disabled")){
                                                    String password = commonMethodsService.randomSixDigitNumber();
                                                    admin.setPassword(securityService.encrypt(password, securityService.getPublicKeyByAdminUsername(admin.getUsername())));
                                                    new Thread() {
                                                        @Override
                                                        public void run() {
                                                            mailjetService.sendEmail(admin.getEmail(),"PIN","Do no share you secure pin. Use this pin to verify your identity "+password);
                                                        }
                                                    }.start();
                                                    admin.setStatus("active");
                                                    admin.setCount(0);
                                                    adminService.updateAdmin(admin);
                                                    smsService.removeAdminOTP(adminOTP);
                                                    session.removeAttribute("number");
                                                    return commonMethodsService.convertObjectIntoJson("verified");
                                                }
                                                return commonMethodsService.convertObjectIntoJson("Account is disabled contact developer");
                                            }else{
                                                if(adminOTP.getCount()>3){
                                                    session.removeAttribute("number");
                                                    return commonMethodsService.convertObjectIntoJson("Generate New OTP");
                                                }else{
                                                    adminOTP.setCount(adminOTP.getCount()+1);
                                                    smsService.updateAdminOTP(adminOTP);
                                                    return commonMethodsService.convertObjectIntoJson("Wrong OTP");
                                                }
                                            }
                                        }
                                        return commonMethodsService.convertObjectIntoJson("OTP Session Out");
                                    }else{
                                        //Redirect Forgat Password
                                        return commonMethodsService.convertObjectIntoJson("Generate New OTP");
                                    }
                                }else{
                                    //Redirect Forgat Password
                                    return commonMethodsService.convertObjectIntoJson("Generate New OTP");
                                }
                            }else{
                                error = "Ip is not whitelisted";
                            }
                        }else{
                            error = "Accounts are blocked by company";
                        }
                    }else{
                        User user = userService.getUserByNumber(number);
                        if(user!=null){
                            if(!user.getStatus().equalsIgnoreCase("disabled")){
                                Admin a = adminService.getAdminByUsername(user.getAdminUsername());
                                if(a!=null && !a.getStatus().equalsIgnoreCase("disabled")){
                                    Organization organization = organizationService.getOrganizationById(a.getOrganizationId());
                                    if(organization.getIpEnable()==0 || adminService.getAccountIpWhiteListByIp(request.getRemoteAddr(), a.getUsername())!=null){
                                        UserOTP userOTP  = smsService.getUserOTP(number);
                                        if(userOTP!=null){
                                            if(session!=null && session.getAttribute("otpkey")!=null && session.getAttribute("otpkey").toString().equalsIgnoreCase(userOTP.getOtpKey())){
                                                if(smsService.compareTimeForMinutes(userOTP.getTimestamp(), 10)){
                                                    //Update count, timestamp and OTP
                                                    if(otp.equals(userOTP.getOtp())){
                                                        //Verified OTP, Generate New Password
                                                        String password = commonMethodsService.randomSixDigitNumber();
                                                        user.setPassword(securityService.encrypt(password, securityService.getPublicKeyByAdminUsername(user.getAdminUsername())));
                                                        new Thread() {
                                                            @Override
                                                            public void run() {
                                                                mailjetService.sendEmail(user.getEmail(),"PIN","Do no share you secure pin. Use this pin to verify your identity "+password);
                                                            }
                                                        }.start();
                                                        user.setStatus("active");
                                                        user.setCount(0);
                                                        adminService.updateAdmin(admin);
                                                        smsService.removeUserOTP(userOTP);
                                                        session.removeAttribute("number");
                                                        return commonMethodsService.convertObjectIntoJson("verified");
                                                    }else{
                                                        if(userOTP.getCount()>3){
                                                            session.removeAttribute("number");
                                                            return commonMethodsService.convertObjectIntoJson("Generate New OTP");
                                                        }else{
                                                            userOTP.setCount(userOTP.getCount()+1);
                                                            smsService.updateUserOTP(userOTP);
                                                            return commonMethodsService.convertObjectIntoJson("Wrong OTP");
                                                        }
                                                    }
                                                }
                                                return commonMethodsService.convertObjectIntoJson("OTP Session Out");
                                            }else{
                                                //Redirect Forgat Password
                                                return commonMethodsService.convertObjectIntoJson("Generate New OTP");
                                            }
                                        }else{
                                            //Redirect Forgat Password
                                            return commonMethodsService.convertObjectIntoJson("Generate New OTP");
                                        }
                                    }else{
                                        error = "Ip is not whitelisted";
                                    }
                                }else{
                                    error = "Accounts are blocked by company";
                                }
                            }else{
                                error = "Account is blocked";
                            }
                        }else{
                            firebaseUserService.revokeUserSession(uid);
                            error = "User is not found/verified";
                        }
                    }
                }else{
                    error = "User is not found/verified";
                }
            
            session.removeAttribute("user");
            session.removeAttribute("admin");
            session.removeAttribute("role");
            session.invalidate();
            return commonMethodsService.convertObjectIntoJson(error);
        } catch (FirebaseAuthException ex) { 
            System.out.println(ex);
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            return commonMethodsService.convertObjectIntoJson(ex.getMessage());
        }
        }
        
        @RequestMapping(value = {"/sendPinResetOtp"}, method = RequestMethod.POST)
        public @ResponseBody
        String sendPinResetOtp(HttpServletRequest request, HttpServletResponse response) {
            try {
            String error = "";
            // Get Firebase 
            final boolean checkRevoked = true;
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifySessionCookie(
            commonMethodsService.getCookieValueByName(request, "session"), checkRevoked);    
            HttpSession session = request.getSession();
            String uid = decodedToken.getUid();
                //Get user record
                UserRecord userRecord = firebaseUserService.getUserRecordByUid(uid);
                if(userRecord!=null){
                    String number = userRecord.getPhoneNumber();
                    Admin admin = adminService.getAdminByNumber(number);
                    if(admin!=null){
                        if(!admin.getStatus().equalsIgnoreCase("disabled")){
                            Organization organization = organizationService.getOrganizationById(admin.getOrganizationId());
                            if(organization.getIpEnable()==0 || adminService.getAccountIpWhiteListByIp(request.getRemoteAddr(), admin.getUsername())!=null){
                                AdminOTP adminOTP = smsService.getAdminOTP(number);
                                if(adminOTP==null || !smsService.compareTimeForMinutes(adminOTP.getTimestamp(), 1)){
                                    adminOTP = smsService.generateAdminOTP(number);
                                    smsService.addAdminOTP(adminOTP);
                                    String otp = adminOTP.getOtp();
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            smsService.sendMessage(admin.getNumber(),"Do not share your otp with anyone "+otp);
                                        }
                                    }.start();
                                    session.setAttribute("otpkey",adminOTP.getOtpKey());
                                    session.setMaxInactiveInterval(10*60);
                                    return commonMethodsService.convertObjectIntoJson("sent");
                                }else{
                                    return commonMethodsService.convertObjectIntoJson("Please wait 60 seconds");
                                }
                            }else{
                                error = "Ip is not whitelisted";
                            }
                        }else{
                            error = "Accounts are blocked by company";
                        }
                    }else{
                        User user = userService.getUserByNumber(number);
                        if(user!=null){
                            if(!user.getStatus().equalsIgnoreCase("disabled")){
                                Admin a = adminService.getAdminByUsername(user.getAdminUsername());
                                if(a!=null && !a.getStatus().equalsIgnoreCase("disabled")){
                                    Organization organization = organizationService.getOrganizationById(a.getOrganizationId());
                                    if(organization.getIpEnable()==0 || adminService.getAccountIpWhiteListByIp(request.getRemoteAddr(), a.getUsername())!=null){
                                        UserOTP userOTP = smsService.getUserOTP(number);
                                        if(userOTP==null || !smsService.compareTimeForMinutes(userOTP.getTimestamp(), 1)){
                                            userOTP = smsService.generateUserOTP(number);
                                            smsService.addUserOTP(userOTP);
                                            String otp = userOTP.getOtp();
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    smsService.sendMessage(user.getNumber(),"Do not share your otp with anyone "+otp);
                                                }
                                            }.start();
                                            session.setAttribute("otpkey",userOTP.getOtpKey());
                                            session.setMaxInactiveInterval(10*60);
                                            return commonMethodsService.convertObjectIntoJson("sent");
                                        }else{
                                            return commonMethodsService.convertObjectIntoJson("Please wait 60 seconds");
                                        }
                                    }else{
                                        error = "Ip is not whitelisted";
                                    }
                                }else{
                                    error = "Accounts are blocked by company";
                                }
                            }else{
                                error = "Account is blocked";
                            }
                        }else{
                            firebaseUserService.revokeUserSession(uid);
                            error = "User is not found/verified";
                        }
                    }
                }else{
                    error = "User is not found/verified";
                }
            
            session.removeAttribute("user");
            session.removeAttribute("admin");
            session.removeAttribute("role");
            session.invalidate();
            return commonMethodsService.convertObjectIntoJson(error);
        } catch (FirebaseAuthException ex) { 
            System.out.println(ex);
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
            return commonMethodsService.convertObjectIntoJson(ex.getMessage());
        }
        }
}
