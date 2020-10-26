/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.model.BillFormat; 
import co.admis.model.Facility;
import co.admis.model.Organization;
import co.admis.model.User;
import co.admis.model.UserPermission;
import co.admis.pattern.PatternVerify; 
import co.admis.security.UserLoginAndSecurityCheck;
import co.admis.service.AdminService;
import co.admis.service.AmazonS3Service;
import co.admis.service.BillFormatService;
import co.admis.service.CommonMethodsService;
import co.admis.service.FacilityService;
import co.admis.service.FirebaseUserService;
import co.admis.service.OrganizationService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import co.admis.service.UserService;
import co.admis.service.mail.MailjetService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.google.firebase.auth.UserRecord;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    BillFormatService billFormatService;
    
    @Autowired
    AmazonS3Service amazonS3Service;
    
    @Autowired
    SmsService smsService;
    
    @Autowired
    OrganizationService organizationService;   
    
    @Autowired
    FacilityService facilityService;
    
    @Autowired
    FirebaseUserService firebaseUserService;
    
    @Autowired
    MailjetService mailjetService;        
    
//        Facility
        @RequestMapping(value = {"/admin/facility/addNewFacility"}, method = RequestMethod.POST)
        public @ResponseBody
        String addFacility(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("facility") Facility facility) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    Organization organization = organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(session.getAttribute("admin").toString()));
                    Number facilities = commonMethodsService.countRows("Facility");
                    if(facilities.intValue()<organization.getFacilityLimit()){
                        //Check Pattern 
                        facility.setAdminUsername(session.getAttribute("admin").toString());
                        facility.setFacilityStatus("active");
                        facility.setDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
                        facility.setFacilitySecret(commonMethodsService.generateRendomStrongPassword(32));
                        facility.setShareData("N");
                        if(facilityService.addFacility(facility)!=null){
                            return commonMethodsService.convertObjectIntoJson("Facility added");
                        }else{
                            return commonMethodsService.convertObjectIntoJson("Something's not right contact developer");
                        }
                    }else{
                        return commonMethodsService.convertObjectIntoJson("Facility limit exceeded");
                    }
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }     
        }
        
        @RequestMapping(value = {"/admin/facility/updateFacility"}, method = RequestMethod.POST)
        public @ResponseBody
        String updateFacility(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("facility") Facility facility) {
            HttpSession session = request.getSession();
            try{
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,facility.getId(),commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    Facility f = facilityService.getFacilityById(facility.getId(),session.getAttribute("admin").toString());
                    System.out.println(f);
                    facility.setFacilitySecret(f.getFacilitySecret());
                    facility.setShareData(f.getShareData());
                    System.out.println(facility);
                    if(facilityService.updateFacility(facility)!=null){
                        return commonMethodsService.convertObjectIntoJson("updated");
                    }else{
                        return commonMethodsService.convertObjectIntoJson("Something's not right contact developer");
                    }
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
//        User
        @RequestMapping(value = {"/admin/addNewUser"}, method = RequestMethod.POST)
        public @ResponseBody
        String addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, @ModelAttribute("user-permission") UserPermission userPermission) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,user.getFacilityId(),commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    Organization organization = organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(session.getAttribute("admin").toString()));
                    int users = userService.getListOfAdminUsers(session.getAttribute("admin").toString()).size();
                    if(users<organization.getUserLimit()){
                        // Add user in firebase
                        user.setNumber("+91"+user.getNumber());
                        String password = commonMethodsService.generateRendomStrongPassword(9);
                        //Add Admin in firebase
                        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                            .setEmail(user.getEmail())
                            .setEmailVerified(true)
                            .setPassword(password)
                            .setPhoneNumber(user.getNumber())
                            .setDisplayName(user.getName())
                            .setDisabled(false);
                        firebaseUserService.addUserRecord(createRequest);
                        //Add user in database
                        if(adminService.getAdminByNumber("+91"+user.getNumber())!=null){
                            return commonMethodsService.convertObjectIntoJson("User number is already registered"); 
                        }
                        user.setRegisterDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
                        user.setAdminUsername(session.getAttribute("admin").toString());
                        String pin = commonMethodsService.randomSixDigitNumber();
                        user.setPassword(securityService.encrypt(pin, securityService.getPublicKeyByAdminUsername(user.getAdminUsername())));
                        user.setCount(0);
                        user.setStatus("disable");
                        User u = userService.addUser(user);
                        System.out.println(u);
                        if(u!=null){
                            userPermission.setUser(user);
                            userService.addUserPermission(userPermission);
                            //send pin on email
                            mailjetService.sendEmail(user.getEmail(), "DoNotReply", "Do not share your login pin with anyone. "+pin);
                            new Thread() {
                                @Override
                                public void run() {
                                    smsService.sendMessage(user.getNumber(),"Please use these details to log in your account. Email: "+user.getEmail()+", Password:"+password);
                                }
                            }.start();
                            return commonMethodsService.convertObjectIntoJson("User added");
                        }
                    }else{
                        return commonMethodsService.convertObjectIntoJson("Limit exceeded, You can't add more users.");
                    }

                    return commonMethodsService.convertObjectIntoJson("Something's not right contact developer");
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }     
        }
        
        
        @RequestMapping(value = {"/admin/userUpdate"}, method = RequestMethod.POST)
        public @ResponseBody
            String updateUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user, @ModelAttribute UserPermission userPermission) {
            try{
            int userId = user.getId(); 
            HttpSession session = request.getSession();
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
            String security = check.authenticationWithPermission(request,user.getFacilityId(),commonMethodsService.getCookieValueByName(request, "session"),null);
            if(security.equalsIgnoreCase("passed")){
                // Verify facility id with admin username
                User u = userService.getUserForAdminById(userId, session.getAttribute("admin").toString());
                if(u!=null){
                    userPermission.setUser(user);
                    user.setStatus(u.getStatus());
                    user.setCount(u.getCount());
                    user.setPassword(u.getPassword());
                    user.setUserPermission(userPermission);
                    userService.updateUser(user);
                    return commonMethodsService.convertObjectIntoJson("User updated");
                }
                return commonMethodsService.convertObjectIntoJson("User id is not correct");
            }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
        @RequestMapping(value = {"/admin/resetUserPassword"}, method = RequestMethod.GET)
        public @ResponseBody
            String resetUserPassword(HttpServletRequest request, HttpServletResponse response) {
            try{
                int userId = Integer.parseInt(request.getParameter("id"));
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    // Verify facility id with admin username
                    User u = userService.getUserForAdminById(userId, session.getAttribute("admin").toString());
                    if(u!=null){
                        String password = commonMethodsService.randomSixDigitNumber();
                        new Thread() {
                                @Override
                                public void run() {
                                    smsService.sendMessage(u.getNumber(),"Your password has been reset by admin. Your new password is "+password+" for mobile number "+u.getNumber()+". Please do not share your login details with others.");
                                }
                            }.start();
                       u.setPassword(securityService.encrypt(password, securityService.getPublicKeyByAdminUsername(session.getAttribute("admin").toString())));
                        userService.updateUser(u);
                        return commonMethodsService.convertObjectIntoJson("reset");
                    }
                    return commonMethodsService.convertObjectIntoJson("User id is not correct");
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
        @RequestMapping(value = {"/admin/checkMobileNumber"}, method = RequestMethod.GET)
        public @ResponseBody
        String checkMobileNumber(HttpServletRequest request, HttpServletResponse response) {
            String userNumber = request.getParameter("number");
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
            try{
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                User u = userService.getUserByNumber("+91"+userNumber);
                if(u!=null){
                    return  commonMethodsService.convertObjectIntoJson("Number is already registred");
                }
                return  commonMethodsService.convertObjectIntoJson("Number is available");
            }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
        @RequestMapping(value = {"/admin/changeUserStatus"}, method = RequestMethod.GET)
        public @ResponseBody
        String changeUserStatus(HttpServletRequest request, HttpServletResponse response) {
            try{
                HttpSession session = request.getSession();
                int userId = Integer.parseInt(request.getParameter("id"));
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    User u = userService.getUserForAdminById(userId, session.getAttribute("admin").toString());
                    if(u!=null){
                        if(u.getStatus().equalsIgnoreCase("active")){
                            u.setStatus("disable");
                        }else{
                            u.setStatus("active");
                        }
                        userService.updateUser(u);
                        return commonMethodsService.convertObjectIntoJson(u.getStatus());
                    }
                    return  commonMethodsService.convertObjectIntoJson("You are not authorised to use this function");
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
        @RequestMapping(value = {"/admin/uploadOrganizationLogo"}, method = RequestMethod.POST)
        public @ResponseBody
        String uploadOrganizationLogo(HttpServletRequest request, HttpServletResponse response){
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    String file = request.getParameter("image");
                    if(file.length()>50000){ 
                        return  commonMethodsService.convertObjectIntoJson("File is too large");
                    }
                    Organization org = organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(session.getAttribute("admin").toString()));
                    AmazonS3 s3 = new AmazonS3Client(amazonS3Service.getAWSCredentials());
                    s3.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).disableChunkedEncoding().build());
                    s3.putObject(amazonS3Service.getS3BucketName(), org.getS3FolderName()+"/logo/logo.txt", file);
                    return  commonMethodsService.convertObjectIntoJson("success");
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
        @RequestMapping(value = {"/admin/uploadOrganizationPublicLogo"}, method = RequestMethod.POST)
        public @ResponseBody
        String uploadOrganizationPublicLogo(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile){
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    if(multipartFile.isEmpty()){
                        return commonMethodsService.convertObjectIntoJson("Please select image first");
                    }  
                    if(multipartFile.getSize()>300000){
                        return commonMethodsService.convertObjectIntoJson("Size should be less then 300 kb"); 
                    }
                    Organization organization = organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(session.getAttribute("admin").toString()));
                    
                    // Upload File
                    AmazonS3 s3 = new AmazonS3Client(amazonS3Service.getAWSCredentials());
                    s3.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).disableChunkedEncoding().build());
                    String fileName = commonMethodsService.getTimeInMilis();
                    s3.putObject(amazonS3Service.getS3PublicBucketName(), organization.getS3FolderName()+"/logo/"+fileName+".jpg", multipartFile.getInputStream(), null);
                    
                    //Update Databse Link
                    organization.setLogo(amazonS3Service.getS3PublicUrlPrefix()+organization.getS3FolderName()+"/logo/"+fileName+".jpg");
                    organizationService.updateOrganization(organization);
                    
                    return commonMethodsService.convertObjectIntoJson("success");
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson("error");
            }   
        }
        
        @RequestMapping(value = {"/admin/updateBillFormat"}, method = RequestMethod.POST)
        public @ResponseBody
        String updateBillFormat(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("bill-format") BillFormat billFormat) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    if(!PatternVerify.checkCINPattern(billFormat.getCin()) || !PatternVerify.checkPanPattern(billFormat.getPanNumber()) || !PatternVerify.checkGSTPattern(billFormat.getGstNumber())){
                        return commonMethodsService.convertObjectIntoJson("Enter correct format");
                    }
                    BillFormat bf = billFormatService.getBillFormatByAdminUsername(session.getAttribute("admin").toString());
                    if(bf==null){
                        billFormatService.addBillFormat(billFormat);
                        return commonMethodsService.convertObjectIntoJson("updated");
                    }else{
                        if(bf.getId()==billFormat.getId()){
                            billFormatService.updateBillFormat(billFormat);
                            return commonMethodsService.convertObjectIntoJson("updated");
                        }
                        return commonMethodsService.convertObjectIntoJson("Incorrect bill id");
                    }
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson(e.getMessage());
            }   
        }
        
        @RequestMapping(value = {"/admin/checkLogoExist"}, method = RequestMethod.POST)
        public @ResponseBody 
        String checkLogoExist(HttpServletRequest request, HttpServletResponse response) {
            try{
                HttpSession session = request.getSession(false);
                String key = request.getParameter("key");
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                    Organization org = organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(session.getAttribute("admin").toString()));
                    String finalKey = org.getS3FolderName()+"/"+key;
                    boolean value = amazonS3Service.checkS3ObjectExist(amazonS3Service.getS3BucketName(), finalKey);
                    if(value){
                        return commonMethodsService.convertObjectIntoJson(finalKey);
                    }
                    return  commonMethodsService.convertObjectIntoJson("Logo Not Exist");
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson(e.getMessage());
            }   
        }
        
         @RequestMapping(value = {"/admin/getOrganizationLogo"}, method = RequestMethod.GET)
        public @ResponseBody
        String getOrganizationLogo(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try{
                String path = request.getParameter("path");
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();      
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),null);
                if(security.equalsIgnoreCase("passed")){
                     String url = amazonS3Service.readTextFileFromS3(amazonS3Service.getS3BucketName(), path);
                     return commonMethodsService.convertObjectIntoJson(url);
                }else if(security.equalsIgnoreCase("session out")){
                    //Pop up for security pin
                    return commonMethodsService.convertObjectIntoJson("pin");
                }
                return commonMethodsService.convertObjectIntoJson("Session out");
            }catch(Exception e){
                System.out.println(e);
                return  commonMethodsService.convertObjectIntoJson(e.getMessage());
            }   
        }
}
