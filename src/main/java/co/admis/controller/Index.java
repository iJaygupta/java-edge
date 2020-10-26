/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import static co.admis.config.ServerConfiguration.SUPPORT_EMAIL;
import co.admis.model.Register;
import co.admis.pattern.PatternVerify;
import co.admis.security.UserLoginAndSecurityCheck;
import co.admis.service.AdminService;
import co.admis.service.CommonMethodsService;
import co.admis.service.mail.MailjetService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Controller
public class Index {
    
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    MailjetService mailjetService;
    
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();    
        String cookie = commonMethodsService.getCookieValueByName(request, "session");
        String security = check.authenticationWithPermission(request,0,cookie,null);
        HttpSession session = request.getSession();
        if(security.equalsIgnoreCase("passed")){
            if(session.getAttribute("role").equals("admin")){
                return new ModelAndView("redirect:/admin");
            }else{
                return new ModelAndView("redirect:/user");
            }
        }else if(security.equalsIgnoreCase("session out")){
            return new ModelAndView("/pin");
        }
        return new ModelAndView("login");
    }
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();    
        String cookie = commonMethodsService.getCookieValueByName(request, "session");
        String security = check.authenticationWithPermission(request,0,cookie,null);
        HttpSession session = request.getSession();
        System.out.println(security);
        if(security.equalsIgnoreCase("passed")){
            if(session.getAttribute("role").equals("admin")){
                return new ModelAndView("redirect:/admin");
            }else{
                return new ModelAndView("redirect:/user");
            }
        }else if(security.equalsIgnoreCase("session out")){
            return new ModelAndView("/pin");
        }
        return new ModelAndView("login");
    }
    
    @RequestMapping(value = {"/database-error","/user/database-error","/admin/database-error"}, method = RequestMethod.GET)
    public ModelAndView databaseErrorPage(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("database-error");
    }
    
    @RequestMapping(value = {"/admin-password-reset"}, method = RequestMethod.GET)
    public ModelAndView adminPasswordReset(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("admin-password-reset");
        return mav;
    }
    
    @RequestMapping(value = {"/admin-verify-otp"}, method = RequestMethod.GET)
    public ModelAndView adminVerifyOTP(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("number")!=null){
            ModelAndView mav = new ModelAndView("admin-verify-otp");
            mav.addObject("number",session.getAttribute("number").toString());
            return mav;
        }
        return new ModelAndView("admin-password-reset");
    }
    
    @RequestMapping(value = {"/user-password-reset"}, method = RequestMethod.GET)
    public ModelAndView userPasswordReset(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("user-password-reset");
        return mav;
    }
    
    @RequestMapping(value = {"/user-verify-otp"}, method = RequestMethod.GET)
    public ModelAndView userVerifyOTP(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("number")!=null){
            ModelAndView mav = new ModelAndView("user-verify-otp");
            mav.addObject("number",session.getAttribute("number").toString());
            return mav;
        }
        return new ModelAndView("user-password-reset");
    }
    
    @RequestMapping(value = {"/pin","/adminLoginPage","/adminLogin","/userLogin","/userLoginPage"}, method = RequestMethod.GET)
    public ModelAndView pin(HttpServletRequest request, HttpServletResponse response) {  
        UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();    
        String cookie = commonMethodsService.getCookieValueByName(request, "session");
        String security = check.authenticationForPin(request,cookie);
        HttpSession session = request.getSession();
        if(security.equalsIgnoreCase("passed")){
            if(session.getAttribute("role").equals("admin")){
                return new ModelAndView("redirect:/admin");
            }else{
                return new ModelAndView("redirect:/user");
            }
        }else if(security.equalsIgnoreCase("session out")){
            return new ModelAndView("/pin");
        }else if(security.equalsIgnoreCase("block")){
            ModelAndView mav = new ModelAndView("pin");
            mav.addObject("error","Your account is blocked. Please reset your pin.");
            return mav;
        }
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("error",security);
        return mav;
    }
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response){ 
        System.out.println("StartPage");
        return new ModelAndView("register");
    }
    
    @RequestMapping(value = {"/privacy-policy"}, method = RequestMethod.GET)
    public ModelAndView privacy(HttpServletRequest request, HttpServletResponse response){ 
        return new ModelAndView("privacy-policy");
    }
    
    @RequestMapping(value = {"/terms-condition"}, method = RequestMethod.GET)
    public ModelAndView termsCondition(HttpServletRequest request, HttpServletResponse response){ 
        return new ModelAndView("terms-condition");
    }
    
    @RequestMapping(value = {"/registerCustomer"}, method = RequestMethod.POST)
    public @ResponseBody
    String registerCustomer(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("register") Register register){ 
        System.out.println("Tomistart");
        try{
            //Pattern match
            if(!PatternVerify.checkEmailPattern(register.getEmail())){
                return commonMethodsService.convertObjectIntoJson("Email is not correct");
            }
            if(!PatternVerify.checkNumberPattern(register.getPhone())){
                return commonMethodsService.convertObjectIntoJson("Phone number is not correct");
            }
            
            register.setDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
            register.setStatus("pending");
            System.out.println(register);
            Register r = adminService.addRegister(register);
            System.out.println(r);
            System.out.println("working");
            return commonMethodsService.convertObjectIntoJson("added");
        }catch(Exception e){
            System.out.println(e);
            return commonMethodsService.convertObjectIntoJson(e.getMessage()); 
        }
    }
    
    @RequestMapping(value = {"/sendQuery"}, method = RequestMethod.POST)
    public @ResponseBody
        String sendQuery(HttpServletRequest request, HttpServletResponse response){ 
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        new Thread() {
            @Override
            public void run() {
                mailjetService.sendEmail(SUPPORT_EMAIL, subject, "Name: "+name+"\nEmail: "+email+"\n"+message);
            }
        }.start();
        return commonMethodsService.convertObjectIntoJson("sent");
    }
}
