/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import static co.admis.config.ServerConfiguration.S3_BUCKET;
import co.admis.model.Customer;
import co.admis.model.Facility;
import co.admis.model.Invoice;
import co.admis.model.Organization;
import co.admis.pattern.PatternVerify;
import co.admis.security.UserLoginAndSecurityCheck; 
import co.admis.service.AmazonS3Service;
import co.admis.service.CommonMethodsService;
import co.admis.service.CustomerService;
import co.admis.service.FacilityService;
import co.admis.service.InvoiceService;
import co.admis.service.OrganizationService;
import co.admis.service.mail.MailjetService;
import java.io.File;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
 * @author dell
 */
@Controller
public class CustomerController {
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    InvoiceService invoiceService;
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    MailjetService mailjetService;
    
    @Autowired
    AmazonS3Service amazonS3Service;
    
    @Autowired
    OrganizationService organizationService;
    
    @Autowired
    FacilityService facilityService;
    
    @RequestMapping(value = {"/customer/getCustomerByNumber"}, method = RequestMethod.POST)
        public @ResponseBody
        String addNewPurchaseRecord(HttpServletRequest request, HttpServletResponse response) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),"add_purchase");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        String number = request.getParameter("number");
                        Customer customer = customerService.getCustomerByNumber(adminUsername, number);
                        if(customer!=null){
                            return commonMethodsService.convertObjectIntoJson(customer);
                        }else{
                            return commonMethodsService.convertObjectIntoJson("Not Available");
                        }
                        //Update Facility Name
                    }else{
                        //For user 
                        return commonMethodsService.convertObjectIntoJson("testing");
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
    
    @RequestMapping(value = {"/customer/sendInvoiceEmail"}, method = RequestMethod.POST)
        public @ResponseBody
        String sendInvoiceEmail(HttpServletRequest request, HttpServletResponse response) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),"add_purchase");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        String email = request.getParameter("email");
                        if(!PatternVerify.checkEmailPattern(email)){
                            return commonMethodsService.convertObjectIntoJson("Enter correct email");
                        }
                        int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
                        Invoice invoice = invoiceService.getInvoiceForAdmin(invoiceId, adminUsername);
                        if(invoice!=null){
                            Organization organization = organizationService.getOrganizationByAdminUsername(adminUsername);
                            String key = amazonS3Service.getListofDocumentsofUser(organization.getS3FolderName()+"/invoice/"+invoice.getId()+"/").get(0).getDocumentName();
                            File file = amazonS3Service.getFileFromS3(S3_BUCKET, key, "invoice"+invoice.getId()+".pdf");
                            DataSource source = new FileDataSource(file);
                            Facility facility = facilityService.getFacilityById(invoice.getFacilityId(), adminUsername);
                            new Thread() {
                                @Override
                                public void run() {
                                    mailjetService.sendEmailWithAttachment(email, "Invoice", "Thank You for visiting "+facility.getFacilityName()+". Please download your invoice below.", source, "invoice"+invoice.getId()+".pdf");
                                }
                            }.start();
                            
                            return commonMethodsService.convertObjectIntoJson("sent");
                        }
                        return commonMethodsService.convertObjectIntoJson("Incorrect invoice selected");
                    }else{
                        //For user 
                        return commonMethodsService.convertObjectIntoJson("testing");
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
        
    @RequestMapping(value = {"/customer/downloadInvoice"}, method = RequestMethod.POST)
        public @ResponseBody
        String downloadInvoice(HttpServletRequest request, HttpServletResponse response) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
                String security = check.authenticationWithPermission(request,0,commonMethodsService.getCookieValueByName(request, "session"),"add_purchase");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
                        Invoice invoice = invoiceService.getInvoiceForAdmin(invoiceId, adminUsername);
                        if(invoice!=null){
                            Organization organization = organizationService.getOrganizationByAdminUsername(adminUsername);
                            String key = amazonS3Service.getListofDocumentsofUser(organization.getS3FolderName()+"/invoice/"+invoice.getId()+"/").get(0).getDocumentName();
                            String url = amazonS3Service.getS3ObjectUrl(S3_BUCKET, key);
                            return commonMethodsService.convertObjectIntoJson(url);
                        }
                        return commonMethodsService.convertObjectIntoJson("Incorrect invoice selected");
                    }else{
                        //For user 
                        return commonMethodsService.convertObjectIntoJson("testing");
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
}
