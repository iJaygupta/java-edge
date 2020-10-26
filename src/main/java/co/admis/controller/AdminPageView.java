/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.model.Admin;
import co.admis.model.BillFormat;
import co.admis.model.Facility;
import co.admis.model.Organization;
import co.admis.security.UserLoginAndSecurityCheck;
import co.admis.service.AdminService;
import co.admis.service.BillFormatService;
import co.admis.service.CommonMethodsService;
import co.admis.service.FacilityService;
import co.admis.service.OrganizationService;
import co.admis.service.UserService;
import java.util.List;
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
public class AdminPageView {
     
    @Autowired
    AdminService adminService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    BillFormatService billFormatService;
    
    @Autowired
    OrganizationService organizationService; 
    
    @Autowired
    FacilityService facilityService;
    
    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView adminDashboard(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),null);
            if(security.equals("passed")){ 
                HttpSession session = request.getSession();
                mav = new ModelAndView("/admin/dashboard");
                Number users = commonMethodsService.countRows("User");
                Number facilities = commonMethodsService.countRows("Facility");
                
                mav.addObject("users",users);
                mav.addObject("facilities",facilities);
                mav.addObject("organization",organizationService.getOrganizationByAdminUsername(session.getAttribute("admin").toString()));
                return mav;
            }else if(security.equalsIgnoreCase("session out")){
                return new ModelAndView("/pin");
            }
        }catch(Exception e){
            System.out.println(e);
            mav = new ModelAndView("adminLogin");
            return mav;
        }
        mav = new ModelAndView("adminLogin");
        return mav;
    }
        
    @RequestMapping(value = {"/admin/facility/facility-add"}, method = RequestMethod.GET)
    public ModelAndView adminAddFacility(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),null);
            if(security.equals("passed")){ 
                mav = new ModelAndView("/admin/facility/facility-add");
                return mav;
            }else if(security.equalsIgnoreCase("session out")){
                return new ModelAndView("/pin");
            }
        }catch(Exception e){
            System.out.println(e);
            mav = new ModelAndView("adminLogin");
            return mav;
        }
        mav = new ModelAndView("adminLogin");
        return mav;
    }
    
    @RequestMapping(value = {"/admin/facility/facility-list"}, method = RequestMethod.GET)
    public ModelAndView adminFacilityList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),null);
            if(security.equals("passed")){ 
            List<Facility> facilities = facilityService.getListofFacility(session.getAttribute("admin").toString());
            mav = new ModelAndView("/admin/facility/facility-list");
            mav.addObject("facilities", facilities);
            return mav;
        }else if(security.equalsIgnoreCase("session out")){
                return new ModelAndView("/pin");
            }
        }catch(Exception e){
            System.out.println(e);
            mav = new ModelAndView("adminLogin");
            return mav;
        }
        mav = new ModelAndView("adminLogin");
        return mav;
    }
    
    @RequestMapping(value = {"/admin/facility/facility-edit"}, method = RequestMethod.GET)
    public ModelAndView adminFacilityEdit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            int facilityId = Integer.parseInt(request.getParameter("facilityId"));
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, facilityId,commonMethodsService.getCookieValueByName(request, "session"),null);
            if(security.equals("passed")){ 
            Facility facility = facilityService.getFacilityById(facilityId,session.getAttribute("admin").toString());
            mav = new ModelAndView("/admin/facility/facility-edit");
            System.out.println(facility);
            mav.addObject("facility", facility);
            return mav;
        }else if(security.equalsIgnoreCase("session out")){
                return new ModelAndView("/pin");
            }
        }catch(Exception e){
            System.out.println(e);
            mav = new ModelAndView("adminLogin");
            return mav;
        }
        mav = new ModelAndView("adminLogin");
        return mav;
    }
   
//    @RequestMapping(value = {"/admin/user/user-list"}, method = RequestMethod.GET)
//    public ModelAndView adminUserPage(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        ModelAndView mav = null;
//        try{ 
//            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
//            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),null);
//            if(security.equals("passed")){ 
//            List<User> users = userService.getListOfAdminUsers(session.getAttribute("admin").toString());
//            List<UserRole> roles = userService.getListOfRoles();
//            mav = new ModelAndView("/admin/user/user-list");
//            mav.addObject("users", users);
//            mav.addObject("roles",roles);
//            mav.addObject("admin",adminService.getAdminByUsername(session.getAttribute("admin").toString()));
//            mav.addObject("facilities",facilityService.getListofActiveFacility(session.getAttribute("admin").toString()));
//            mav.addObject("facilitiesall",facilityService.getListofFacility(session.getAttribute("admin").toString()));
//            return mav;
//        }else if(security.equalsIgnoreCase("session out")){
//                return new ModelAndView("/pin");
//            }
//        }catch(Exception e){
//            System.out.println(e);
//            mav = new ModelAndView("adminLogin");
//            return mav;
//        }
//        mav = new ModelAndView("adminLogin");
//        return mav;
//    }
//    
//    @RequestMapping(value = {"/admin/user/user-edit"}, method = RequestMethod.GET)
//        public ModelAndView getEditUser(HttpServletRequest request, HttpServletResponse response) {
//            ModelAndView mav = null;
//            int userId = Integer.parseInt(request.getParameter("id"));
//            HttpSession session = request.getSession();
//            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();        
//            if(check.adminAuth(request)){
//                mav = new ModelAndView("/admin/user/user-edit");
//                User u = userService.getUserForAdminById(userId, session.getAttribute("admin").toString());
//                if(u!=null){
//                    u.setAdminUsername(null);
//                    u.setPassword(null);
//                    mav.addObject("user", u);
//                    mav.addObject("admin",adminService.getAdminByUsername(session.getAttribute("admin").toString())); 
//                }
//                return mav;
//            }
//            mav = new ModelAndView("adminLogin");
//            return mav;
//        }
    
    @RequestMapping(value = {"/admin/bill/bill-format"}, method = RequestMethod.GET)
    public ModelAndView adminBillPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),null);
            if(security.equals("passed")){ 
                mav = new ModelAndView("/admin/bill/bill-format");
                Admin admin = adminService.getAdminByUsername(session.getAttribute("admin").toString());
                BillFormat billFormat = billFormatService.getBillFormatByAdminUsername(admin.getUsername());
                Organization organization = organizationService.getOrganizationById(admin.getOrganizationId());
                mav.addObject("format",billFormat);
                mav.addObject("logo",organization.getLogo());
                return mav;
            }else if(security.equalsIgnoreCase("session out")){
                return new ModelAndView("/pin");
            }
        }catch(Exception e){
            System.out.println(e);
            mav = new ModelAndView("adminLogin");
            return mav;
        }
        mav = new ModelAndView("adminLogin");
        return mav;
    }
    
    @RequestMapping(value = {"/admin/profile"}, method = RequestMethod.GET)
    public ModelAndView adminProfilePage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();        
        if(check.adminAuth(request)){
            mav = new ModelAndView("/admin/profile");
            Admin admin = adminService.getAdminByUsername(session.getAttribute("admin").toString());
            Organization organization = organizationService.getOrganizationById(admin.getOrganizationId());
            mav.addObject("admin",admin);
            mav.addObject("organization",organization);
            return mav;
        }
        mav = new ModelAndView("adminLogin");
        return mav;
    }
}
