/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import static co.admis.config.ServerConfiguration.MASTER_USERNAME;
import co.admis.model.Admin;
import co.admis.model.AdminPermission;
import co.admis.model.BillFormat;
import co.admis.model.Login;
import co.admis.model.Master;
import co.admis.model.MessageBody;
import co.admis.model.Organization;
import co.admis.pattern.PatternVerify;
import co.admis.service.AdminService;
import co.admis.service.AmazonS3Service;
import co.admis.service.AuthenticationService;
import co.admis.service.BillFormatService;
import co.admis.service.CommonMethodsService;
import co.admis.service.FirebaseUserService;
import co.admis.service.MasterService;
import co.admis.service.OrganizationService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import co.admis.service.mail.MailjetService;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import java.security.KeyPair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Controller
public class MasterController {
    @Autowired
    OrganizationService organizationService;
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    AmazonS3Service amazonS3Service;
    
    @Autowired
    SmsService smsService;        
    
    @Autowired
    BillFormatService billFormatService; 
    
    @Autowired
    MasterService masterService;
    
    @Autowired
    AuthenticationService authenticationService;
    
    @Autowired
    FirebaseUserService firebaseUserService;
    
    @Autowired
    MailjetService mailjetService;
    
    MessageBody messageBody = null;
    
    @RequestMapping(value = {"/master/masterLogin"}, method = RequestMethod.POST)
    public @ResponseBody
        String masterLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("master-login") Login login) {
        try{
            if(authenticationService.masterAuthentication(login).equals(login.getUsername())){
                Master master = masterService.getMasterByUsername(login.getUsername());
                HttpSession session = request.getSession();
                String otp = commonMethodsService.randomSixDigitNumber();
                session.setAttribute("otp",otp);
                new Thread() {
                    @Override
                    public void run() {
                        smsService.sendMessage(master.getNumber(),otp);
                    }
                }.start();
                return commonMethodsService.convertObjectIntoJson("success");
            }
            return commonMethodsService.convertObjectIntoJson("Username or password is incorrect");
        }catch(Exception e){
            return commonMethodsService.convertObjectIntoJson(e.getMessage());
        }
    }
    
    @RequestMapping(value = {"/master/verifyOTP"}, method = RequestMethod.POST)
    public @ResponseBody
        String verifyOTP(HttpServletRequest request, HttpServletResponse response) {
        try{    
            HttpSession session = request.getSession();
            String otp = request.getParameter("otp");
            if(session!=null && session.getAttribute("otp")!=null){
                if(otp.equalsIgnoreCase(session.getAttribute("otp").toString())){
                    session.removeAttribute("otp");
                    session.setAttribute("master", MASTER_USERNAME);
                    String key = commonMethodsService.generateRendomStrongPassword(12);
                    Master master = masterService.getMasterByUsername(MASTER_USERNAME);
                    master.setSecretKey(key);
                    masterService.updateMaster(master);
                    session.setAttribute("master", MASTER_USERNAME);
                    session.setAttribute("key", key);
                    return commonMethodsService.convertObjectIntoJson("success");
                }else{
                    session.removeAttribute("otp");
                    session.invalidate();
                    return commonMethodsService.convertObjectIntoJson("Session out");
                }
            }
            return commonMethodsService.convertObjectIntoJson("One time password is not correct");
        }catch(Exception e){
            return commonMethodsService.convertObjectIntoJson(e.getMessage());
        }
    }
    
     @RequestMapping(value = {"/masterLogout"}, method = RequestMethod.GET)
    public String masterLogout(HttpServletRequest request, HttpServletResponse response) {
         HttpSession session = request.getSession();
            if(session!=null){
                if(session.getAttribute("master")!=null){
                    session.removeAttribute("master");
                    session.removeAttribute("key");
                    session.invalidate();
                }
            }
            return "redirect:/index";
    }
    
    
    @RequestMapping(value = {"/master/addNewOrganization"}, method = RequestMethod.POST)
    public @ResponseBody
        String addNewOrganization(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("organization") Organization organization) {
        try{    
            if(masterService.masterSecurityCheck(request)){
                organization.setS3FolderName(organization.getName());
                organization.setRegisterDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
                organizationService.addOrganization(organization);
                return commonMethodsService.convertObjectIntoJson("added");
            }
            return commonMethodsService.convertObjectIntoJson("Session out");
        }catch(Exception e){
            return commonMethodsService.convertObjectIntoJson(e.getMessage());
        }
    }
        
    @RequestMapping(value = {"/master/updateOrganization"}, method = RequestMethod.POST)
    public @ResponseBody
        String updateOrganization(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("organization") Organization organization) {
        try{
            if(masterService.masterSecurityCheck(request)){
                Organization o = organizationService.getOrganizationById(organization.getId());
                organization.setLogo(o.getLogo());
                organizationService.updateOrganization(organization);
                return commonMethodsService.convertObjectIntoJson("updated");
            }
            return commonMethodsService.convertObjectIntoJson("Session out");
        }catch(Exception e){
            System.out.println();
            return commonMethodsService.convertObjectIntoJson(e.getMessage());
        }    
    }
    
    @RequestMapping(value = {"/master/addNewAdmin"}, method = RequestMethod.POST)
    public @ResponseBody
        String addNewAdmin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("admin") Admin admin) {
        try{
            if(masterService.masterSecurityCheck(request)){
                if(adminService.getAdminByUsername(admin.getUsername())!=null){
                    return commonMethodsService.convertObjectIntoJson("Username already registred");
                }
                if(!PatternVerify.checkEmailPattern(admin.getEmail())){
                    return commonMethodsService.convertObjectIntoJson("Enter correct email address");
                }
                if(!PatternVerify.checkNumberPattern(admin.getNumber())){
                    return commonMethodsService.convertObjectIntoJson("Enter correct number");
                }
                if(!PatternVerify.checkNamePattern(admin.getName())){
                    return commonMethodsService.convertObjectIntoJson("Enter correct name");
                }
                admin.setNumber("+91"+admin.getNumber());
                String password = commonMethodsService.generateRendomStrongPassword(9);
                //Add Admin in firebase
                CreateRequest createRequest = new CreateRequest()
                    .setEmail(admin.getEmail())
                    .setEmailVerified(true)
                    .setPassword(password)
                    .setPhoneNumber(admin.getNumber())
                    .setDisplayName(admin.getName())
                    .setDisabled(false);
                firebaseUserService.addUserRecord(createRequest);
                //Add Admin in database
                Organization organization = organizationService.getOrganizationById(admin.getOrganizationId());
                KeyPair keyPair = securityService.generateKeyPair();
                amazonS3Service.updateS3Object(amazonS3Service.getS3BucketName(), organization.getS3FolderName()+"/security key/public.txt", securityService.savePublicKey(keyPair.getPublic()), "public", "txt");
                amazonS3Service.updateS3Object(amazonS3Service.getS3BucketName(), organization.getS3FolderName()+"/security key/private.txt", securityService.savePrivateKey(keyPair.getPrivate()), "private", "txt");
                String pin = commonMethodsService.randomSixDigitNumber();
                admin.setPassword(securityService.encrypt(pin, keyPair.getPublic()));
                admin.setCount(0);
                admin.setStatus("active");
                admin.setRegisterDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
                admin.setNewAccount(0);
                if(adminService.addAdmin(admin)!=null){
                    AdminPermission ap = new AdminPermission(admin, "N");
                    adminService.addAdminPermission(ap);
                    BillFormat billFormat = new BillFormat();
                    billFormat.setAdminUsername(admin.getUsername());
                    billFormatService.addBillFormat(billFormat);
                    //Send message
                    smsService.sendMessage(admin.getNumber(),"Please use these details to log in your account. Email: "+admin.getEmail()+", Password:"+password);
                    //send pin on email
                    mailjetService.sendEmail(admin.getEmail(), "DoNotReply", "Do not share your login pin with anyone. "+pin);
                }
                return commonMethodsService.convertObjectIntoJson("added");
            }
            return commonMethodsService.convertObjectIntoJson("Session out");
        }catch(Exception e){
            System.out.println(e);
            return commonMethodsService.convertObjectIntoJson(e.getMessage());
        }    
    }
    
    @RequestMapping(value = {"/master/updateAdmin"}, method = RequestMethod.POST)
    public @ResponseBody
        String updateAdmin(HttpServletRequest request, HttpServletResponse response) {
        try{
            if(masterService.masterSecurityCheck(request)){
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String number = request.getParameter("number").substring(request.getParameter("number").length() - 10);
                String status = request.getParameter("status");
                String username = request.getParameter("username"); 
                if(!PatternVerify.checkEmailPattern(email)){
                    return commonMethodsService.convertObjectIntoJson("Enter correct email address");
                }
                if(!PatternVerify.checkNumberPattern(number)){
                    return commonMethodsService.convertObjectIntoJson("Enter correct number");
                }
                if(!PatternVerify.checkNamePattern(name)){
                    return commonMethodsService.convertObjectIntoJson("Enter correct name");
                }
                number = "+91"+number;
                Admin admin = adminService.getAdminByUsername(username);
                String uid = firebaseUserService.getUserRecordByNumber(number).getUid();
                //Add Admin in database
                admin.setName(name);
                admin.setEmail(email);
                admin.setNumber(number);
                admin.setStatus(status);
                adminService.updateAdmin(admin);
                //Add Admin in firebase
                UpdateRequest createRequest = new UpdateRequest(uid)
                    .setEmail(email)
                    .setEmailVerified(true)
                    .setPhoneNumber(number)
                    .setDisplayName(name);
                firebaseUserService.updateUserRecord(createRequest);
                
                return commonMethodsService.convertObjectIntoJson("updated");
            }
            return commonMethodsService.convertObjectIntoJson("Session out");
        }catch(Exception e){
            return commonMethodsService.convertObjectIntoJson(e.getMessage());
        }
    }
}
