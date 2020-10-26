/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.service.AdminService;
import co.admis.service.AmazonS3Service;
import co.admis.service.BillFormatService;
import co.admis.service.CommonMethodsService;
import co.admis.service.MasterService;
import co.admis.service.OrganizationService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Controller
public class MasterPageView {
    
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
    
    @RequestMapping(value = {"/master/dashboard"}, method = RequestMethod.GET)
    public ModelAndView masterDeshboard(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            ModelAndView mav = new ModelAndView("master/master");
            mav.addObject("organizations",organizationService.getOrganizationsList());
            mav.addObject("facility",commonMethodsService.countRows("Facility"));
            mav.addObject("user",commonMethodsService.countRows("User"));
            mav.addObject("admin",commonMethodsService.countRows("Admin"));
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/organization/organization-add"}, method = RequestMethod.GET)
    public ModelAndView masterOrganizationAddPage(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            ModelAndView mav = new ModelAndView("master/organization/organization-add");
            mav.addObject("roles",organizationService.getOrganizationRoles());
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/organization/organization-list"}, method = RequestMethod.GET)
    public ModelAndView masterOrganizationPage(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            ModelAndView mav = new ModelAndView("master/organization/organization-list");
            mav.addObject("organizations",organizationService.getOrganizationsList());
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/organization/organization-edit"}, method = RequestMethod.GET)
    public ModelAndView masterOrganizationEditPage(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            int id = Integer.parseInt(request.getParameter("id"));
            ModelAndView mav = new ModelAndView("master/organization/organization-edit");
            mav.addObject("organization",organizationService.getOrganizationById(id));
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/admin/admin-list"}, method = RequestMethod.GET)
    public ModelAndView adminList(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            ModelAndView mav = new ModelAndView("master/admin/admin-list");
            mav.addObject("admins",adminService.getListOfAdmin());
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/admin/admin-add"}, method = RequestMethod.GET)
    public ModelAndView adminAdd(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            ModelAndView mav = new ModelAndView("master/admin/admin-add");
            mav.addObject("organizations",organizationService.getOrganizationsList());
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/admin/admin-edit"}, method = RequestMethod.GET)
    public ModelAndView adminEdit(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            String username = request.getParameter("username");
            ModelAndView mav = new ModelAndView("master/admin/admin-edit");
            mav.addObject("admin",adminService.getAdminByUsername(username));
            return mav;
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/bill-format"}, method = RequestMethod.GET)
    public ModelAndView masterBillFormat(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            return new ModelAndView("master/bill-format");
        }
        return new ModelAndView("master/master-login");
    }
    
    @RequestMapping(value = {"/master/master-login","/master/login","/master"}, method = RequestMethod.GET)
    public ModelAndView masterLoginPage(HttpServletRequest request, HttpServletResponse response) {
        if(masterService.masterSecurityCheck(request)){
            return new ModelAndView("redirect:master/dashboard");
        }else{
            return new ModelAndView("master/master-login");
        }
    }
    
    @RequestMapping(value = {"/master/verify-login"}, method = RequestMethod.GET)
    public ModelAndView verifyLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("otp")!=null){
            return new ModelAndView("master/verify-login");
        }
        return new ModelAndView("master/master-login");
    }
}
