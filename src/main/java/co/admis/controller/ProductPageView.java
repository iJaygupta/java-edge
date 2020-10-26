/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.model.Facility;
import co.admis.model.ProductStock;
import co.admis.model.ProductStockRecord;
import co.admis.model.User;
import co.admis.security.UserLoginAndSecurityCheck;
import co.admis.service.AdminService;
import co.admis.service.AmazonS3Service;
import co.admis.service.BillFormatService;
import co.admis.service.CommonMethodsService;
import co.admis.service.FacilityService;
import co.admis.service.FirebaseUserService;
import co.admis.service.InvoiceService;
import co.admis.service.OrganizationService;
import co.admis.service.ProductService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import co.admis.service.UserService;
import co.admis.service.mail.MailjetService;
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
 * @author dell
 */
@Controller
public class ProductPageView {
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
    
    @Autowired
    ProductService productService;  
    
    @Autowired
    InvoiceService invoiceService;  
    
    @RequestMapping(value = {"/product/product-record-list"}, method = RequestMethod.GET)
    public ModelAndView productList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"view_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    mav = new ModelAndView("/product/product-record-list");
                    String adminUsername = session.getAttribute("admin").toString();
                    mav.addObject("facilities",facilityService.getListofActiveFacility(adminUsername));
                    if(request.getParameter("facilityId")!=null && !request.getParameter("facilityId").equalsIgnoreCase("")){
                        mav.addObject("productList", productService.getProductStockRecordByFacilityId(adminUsername, Integer.parseInt(request.getParameter("facilityId"))));
                    }else{
                        mav.addObject("productList", productService.getProductStockRecordByAdmin(adminUsername));
                    }
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }else{
                    mav = new ModelAndView("/product/product-record-list");
                    User user = userService.getUserByNumber(session.getAttribute("admin").toString());
                    String adminUsername = user.getAdminUsername();
                    mav.addObject("productList", productService.getProductStockRecordByFacilityId(adminUsername,user.getFacilityId()));
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }
                
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
    
    @RequestMapping(value = {"/product/product-record-add"}, method = RequestMethod.GET)
    public ModelAndView productAdd(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"view_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    //product Record
                    mav = new ModelAndView("/product/product-record-add");
                    String adminUsername = session.getAttribute("admin").toString();
                    mav.addObject("facilities",facilityService.getListofActiveFacility(adminUsername));
                    mav.addObject("productList", productService.getProductStockByAdmin(adminUsername));
                    mav.addObject("manufacturers", adminService.getManufacturerByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("vendors",adminService.getVendorByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("sizes",adminService.getSizeChartByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("brands",adminService.getBrandGroupByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("hsn",adminService.getTaxType());
                    mav.addObject("colors",adminService.getColorSelectionByStatus("active"));
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }else{
                    mav = new ModelAndView("/product/product-record-add");
                    
                    return mav;
                }
                
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
    
    @RequestMapping(value = {"/product/product-stock-edit"}, method = RequestMethod.GET)
    public ModelAndView productEdit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"update_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    //product Record
                    mav = new ModelAndView("/product/product-stock-edit");
                    int productId = Integer.parseInt(request.getParameter("id"));
                    String adminUsername = session.getAttribute("admin").toString();
                    ProductStock productStock = productService.getProductStockById(adminUsername, productId);
                    mav.addObject("product", productStock);
                    mav.addObject("facility",facilityService.getFacilityById(productStock.getFacilityId(),adminUsername));
                    mav.addObject("manufacturers", adminService.getManufacturerByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("vendors",adminService.getVendorByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("sizes",adminService.getSizeChartByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("brands",adminService.getBrandGroupByAdminAndStatus(adminUsername, "active"));
                    mav.addObject("hsn",adminService.getTaxType());
                    mav.addObject("colors",adminService.getColorSelectionByStatus("active"));
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }else{
                    mav = new ModelAndView("/product/product-stock-edit");
                    
                    return mav;
                }
                
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
    
    @RequestMapping(value = {"/product/product-stock-list"}, method = RequestMethod.GET)
    public ModelAndView stockList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"view_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    mav = new ModelAndView("/product/product-stock-list");
                    String adminUsername = session.getAttribute("admin").toString();
                    mav.addObject("facilities",facilityService.getListofActiveFacility(adminUsername));
                    if(request.getParameter("facilityId")!=null && !request.getParameter("facilityId").equalsIgnoreCase("")){
                        mav.addObject("productList", productService.getProductStockByFacilityId(adminUsername, Integer.parseInt(request.getParameter("facilityId"))));
                    }else{
                        mav.addObject("productList", productService.getProductStockByAdmin(adminUsername));
                    }
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }else{
                    mav = new ModelAndView("/product/product-stock-list");
                    User user = userService.getUserByNumber(session.getAttribute("admin").toString());
                    String adminUsername = user.getAdminUsername();
                    mav.addObject("productList", productService.getProductStockByAdmin(adminUsername));
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }
                
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
    
    @RequestMapping(value = {"/product/sell-product"}, method = RequestMethod.GET)
    public ModelAndView sell(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"view_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    mav = new ModelAndView("/product/sell-product");
                    String adminUsername = session.getAttribute("admin").toString();
                    mav.addObject("facilities",facilityService.getListofActiveFacility(adminUsername));
                    if(request.getParameter("facilityId")!=null && !request.getParameter("facilityId").equalsIgnoreCase("")){
                        mav.addObject("productList", productService.getProductStockByFacilityId(adminUsername, Integer.parseInt(request.getParameter("facilityId"))));
                        mav.addObject("epcs",productService.getRfidTagsByFacilityId(adminUsername, Integer.parseInt(request.getParameter("facilityId"))));
                    }else{
                        List<Facility> facility = facilityService.getListofActiveFacility(adminUsername);
                        if(facility.size() == 1){
                            mav.addObject("productList", productService.getProductStockByFacilityId(adminUsername, facility.get(0).getId()));
                            mav.addObject("epcs",productService.getRfidTagsByFacilityId(adminUsername, facility.get(0).getId()));
                        }
                    }
                    mav.addObject("taxTypes",adminService.getTaxTypeByStatus("active"));
                    mav.addObject("role",session.getAttribute("role").toString());
                    return mav;
                }else{
                    mav = new ModelAndView("/product/sell-product");
                    User user = userService.getUserByNumber(session.getAttribute("admin").toString());
                    String adminUsername = user.getAdminUsername();
                    mav.addObject("productList", productService.getProductStockByFacilityId(adminUsername,user.getFacilityId()));
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }
                
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
    
    @RequestMapping(value = {"/product/sell-record-list"}, method = RequestMethod.GET)
    public ModelAndView sellRecordList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"view_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    mav = new ModelAndView("/product/sell-record-list");
                    String adminUsername = session.getAttribute("admin").toString();
                    mav.addObject("facilities",facilityService.getListofActiveFacility(adminUsername));
                    if(request.getParameter("facilityId")!=null && !request.getParameter("facilityId").equalsIgnoreCase("")){
                        mav.addObject("productList", productService.getSellRecordByFacilityId(adminUsername, Integer.parseInt(request.getParameter("facilityId"))));
                    }else{
                        List<Facility> facility = facilityService.getListofActiveFacility(adminUsername);
                        if(facility.size() == 1){
                            mav.addObject("productList", productService.getSellRecordByFacilityId(adminUsername, facility.get(0).getId()));
                        }
                    }
                    mav.addObject("role",session.getAttribute("role").toString());
                    return mav;
                }else{
                    mav = new ModelAndView("/product/sell-record-list");
                    User user = userService.getUserByNumber(session.getAttribute("admin").toString());
                    String adminUsername = user.getAdminUsername();
                    mav.addObject("productList", productService.getSellRecordByFacilityId(adminUsername,user.getFacilityId()));
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }
                
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
    
    @RequestMapping(value = {"/product/invoice-record-list"}, method = RequestMethod.GET)
    public ModelAndView invoiceRecord(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mav = null;
        try{ 
            UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();
            String security = check.authenticationWithPermission(request, 0,commonMethodsService.getCookieValueByName(request, "session"),"view_purchase");
            if(security.equals("passed")){
                if(session.getAttribute("role").equals("admin")){
                    mav = new ModelAndView("/product/invoice-record-list");
                    String adminUsername = session.getAttribute("admin").toString();
                    mav.addObject("facilities",facilityService.getListofActiveFacility(adminUsername));
                    if(request.getParameter("facilityId")!=null && !request.getParameter("facilityId").equalsIgnoreCase("")){
                        mav.addObject("invoices", invoiceService.getListofInvoiceForFacility(adminUsername, Integer.parseInt(request.getParameter("facilityId"))));
                    }else{
                        List<Facility> facility = facilityService.getListofActiveFacility(adminUsername);
                        if(facility.size() == 1){
                            mav.addObject("invoices", invoiceService.getListofInvoiceForFacility(adminUsername, facility.get(0).getId()));
                        }
                    }
                    mav.addObject("role",session.getAttribute("role").toString());
                    return mav;
                }else{
                    mav = new ModelAndView("/product/invoice-record-list");
                    User user = userService.getUserByNumber(session.getAttribute("admin").toString());
                    String adminUsername = user.getAdminUsername();
                    mav.addObject("role",session.getAttribute("role"));
                    return mav;
                }
                
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
}
