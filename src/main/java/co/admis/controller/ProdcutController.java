/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.model.BillFormat;
import co.admis.model.Customer;
import co.admis.model.Invoice;
import co.admis.model.InvoiceData;
import co.admis.model.Organization;
import co.admis.model.ProductStock;
import co.admis.model.ProductStockRecord;
import co.admis.model.RfidTag;
import co.admis.model.SellRecord;
import co.admis.model.TaxType;
import co.admis.pattern.PatternVerify;
import co.admis.pdf.InvoicePdf;
import co.admis.security.UserLoginAndSecurityCheck;
import co.admis.service.AdminService;
import co.admis.service.AmazonS3Service;
import co.admis.service.BillFormatService;
import co.admis.service.CommonMethodsService;
import co.admis.service.CustomerService;
import co.admis.service.FacilityService;
import co.admis.service.FirebaseUserService;
import co.admis.service.InvoiceService;
import co.admis.service.OrganizationService;
import co.admis.service.ProductService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import co.admis.service.UserService;
import co.admis.service.mail.MailjetService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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
 * @author dell
 */
@Controller
public class ProdcutController {
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
    CustomerService customerService;  
    
    @Autowired
    InvoiceService invoiceService;  
    
//        Purchase Record
        @RequestMapping(value = {"/product/addNewProductStockRecord"}, method = RequestMethod.POST)
        public @ResponseBody
        String addNewPurchaseRecord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("product") ProductStockRecord productStockRecord) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
                String security = check.authenticationWithPermission(request,productStockRecord.getFacilityId(),commonMethodsService.getCookieValueByName(request, "session"),"add_purchase");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        //Update Facility Name
                        productStockRecord.setFacilityName(facilityService.getFacilityById(productStockRecord.getFacilityId(), adminUsername).getFacilityName());
                        
                        //Check hsn code is valid
                        TaxType taxType = adminService.getTaxTypeById(Integer.parseInt(productStockRecord.getHsn()));
                        if(taxType==null){
                            return commonMethodsService.convertObjectIntoJson("Select correct HSN code");
                        }
                                
                        // EPC Codes (check quantity)
                        String epcs[] = request.getParameterValues("multipleepc");
                        if(epcs==null || productStockRecord.getQuantity()<1 || productStockRecord.getQuantity()!=epcs.length){
                            return commonMethodsService.convertObjectIntoJson("Quantity is not correct");
                        }
                        
                        for(int i=0; i<epcs.length;i++){
                            if(productService.getRfidTagByCode(epcs[i])!=null){
                                return commonMethodsService.convertObjectIntoJson("One or more products already registred "+epcs[i]);
                            }
                        }
                        
                        //Set Tax and final price for purchase
                        if(taxType.getLimit()!=0 && productStockRecord.getPurchasePrice() > taxType.getLimit()){
                            productStockRecord.setPurchaseTax(taxType.getIgstAfterLimit());
                            double tax = productStockRecord.getPurchasePrice()*taxType.getIgstAfterLimit()*0.01;
                            productStockRecord.setFinalPurchasePrice(productStockRecord.getPurchasePrice()+tax);
                        }else{
                            productStockRecord.setPurchaseTax(taxType.getIgst());
                            double tax = productStockRecord.getPurchasePrice()*taxType.getIgst()*0.01;
                            productStockRecord.setFinalPurchasePrice(productStockRecord.getPurchasePrice()+tax);
                        }
                        String date = commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime();
                        productStockRecord.setAdminUsername(adminUsername);
                        productStockRecord.setDate(date);
                        //Update stock 
                        Double price = Double.parseDouble(request.getParameter("price"));
                        Double discount = Double.parseDouble(request.getParameter("discount"));
                        double dis = discount*price*0.01;
                        price = price - dis;
                        
                        //Set Tax and final price for selling
                        if(taxType.getLimit()!=0 && price > taxType.getLimit()){
                            productStockRecord.setTax(taxType.getIgstAfterLimit());
                            double tax = price*taxType.getIgstAfterLimit()*0.01;
                            productStockRecord.setFinalPrice(price+tax);
                        }else{
                            productStockRecord.setTax(taxType.getIgst());
                            double tax = price*taxType.getIgst()*0.01;
                            productStockRecord.setFinalPrice(price+tax);
                        }
                        ProductStockRecord psr =  productService.addProductStockRecord(productStockRecord);
                        ProductStock product = new ProductStock(0, productStockRecord, productStockRecord.getQuantity());
                        //Product Stock
                        if(productStockRecord.getProductCode()!=null && !productStockRecord.getProductCode().equalsIgnoreCase("")){
                            ProductStock ps = productService.getProductStockByCode(adminUsername, productStockRecord.getFacilityId(), productStockRecord.getProductCode());
                            if(ps!=null){
                                int qty = ps.getQuantity()+productStockRecord.getQuantity();
                                ProductStock pStock = new ProductStock(ps.getId(), productStockRecord, qty);
                                productService.updateProductStock(pStock);
                                productService.addRfidTag(epcs, ps.getId(), adminUsername,productStockRecord.getFacilityId());
                                return commonMethodsService.convertObjectIntoJson("added"); 
                            }
                        }else{
                            List<ProductStock> ps = productService.getProductStockByName(adminUsername, productStockRecord.getFacilityId(), productStockRecord.getProductName());
                            for(ProductStock p: ps){
                                if(p.equals(product)){
                                    int qty = p.getQuantity()+productStockRecord.getQuantity();
                                    p.setQuantity(qty);
                                    productService.updateProductStock(p);
                                    productService.addRfidTag(epcs, p.getId(), adminUsername,productStockRecord.getFacilityId());
                                    return commonMethodsService.convertObjectIntoJson("added"); 
                                }
                            }
                        }
                        
                        productService.addProductStock(product);
                        productService.addRfidTag(epcs, product.getId(), adminUsername, productStockRecord.getFacilityId());
                        return commonMethodsService.convertObjectIntoJson("added");
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
        
//        Product Stock
        @RequestMapping(value = {"/product/updateProductStock"}, method = RequestMethod.POST)
        public @ResponseBody
        String updateProductStock(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("product") ProductStock productStock) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
                if(productStock.getFacilityId()<1){
                    return commonMethodsService.convertObjectIntoJson("Please select facility"); 
                }
                String security = check.authenticationWithPermission(request,productStock.getFacilityId(),commonMethodsService.getCookieValueByName(request, "session"),"add_purchase");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        //Check hsn code is valid
                        TaxType taxType = adminService.getTaxTypeById(Integer.parseInt(productStock.getHsn()));
                        if(taxType==null){
                            return commonMethodsService.convertObjectIntoJson("Select correct HSN code");
                        }
                                
                        //Set Tax and final price
                        if(taxType.getLimit()!=0 && productStock.getPurchasePrice() > taxType.getLimit()){
                            productStock.setPurchaseTax(taxType.getIgstAfterLimit());
                            double tax = productStock.getPurchasePrice()*taxType.getIgstAfterLimit()*0.01;
                            productStock.setFinalPurchasePrice(productStock.getPurchasePrice()+tax);
                        }else{
                            productStock.setPurchaseTax(taxType.getIgst());
                            double tax = productStock.getPurchasePrice()*taxType.getIgst()*0.01;
                            productStock.setFinalPurchasePrice(productStock.getPurchasePrice()+tax);
                        }
                        //Update stock 
                        Double price = Double.parseDouble(request.getParameter("price"));
                        Double discount = Double.parseDouble(request.getParameter("discount"));
                        //Set Tax and final price
                        if(taxType.getLimit()!=0 && price > taxType.getLimit()){
                            productStock.setTax(taxType.getIgstAfterLimit());
                            double tax = price*taxType.getIgstAfterLimit()*0.01;
                            productStock.setFinalPrice(price+tax);
                        }else{
                            productStock.setTax(taxType.getIgst());
                            double tax = price*taxType.getIgst()*0.01;
                            double dis = discount*price*0.01;
                            productStock.setFinalPrice(price+tax-dis);
                        }
                        
                        productStock.setQuantity(productService.getRfidTagsByProductCode(adminUsername, productStock.getId()).size());
//                        productStock.setAdminUsername(adminUsername);
                        productService.updateProductStock(productStock);
                        
                        return commonMethodsService.convertObjectIntoJson("updated");
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
    //        Purchase Record
        @RequestMapping(value = {"/product/editProductStockRecord"}, method = RequestMethod.POST)
        public @ResponseBody
        String editProductStockRecord(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("product") ProductStock productStock) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();   
                if(productStock.getFacilityId()<1){
                    return commonMethodsService.convertObjectIntoJson("Please select facility"); 
                }
                String security = check.authenticationWithPermission(request,productStock.getFacilityId(),commonMethodsService.getCookieValueByName(request, "session"),"add_purchase");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        //Check hsn code is valid
                        TaxType taxType = adminService.getTaxTypeById(Integer.parseInt(productStock.getHsn()));
                        if(taxType==null){
                            return commonMethodsService.convertObjectIntoJson("Select correct HSN code");
                        }
                                
                        //Set Tax and final price
                        if(taxType.getLimit()!=0 && productStock.getPurchasePrice() > taxType.getLimit()){
                            productStock.setPurchaseTax(taxType.getIgstAfterLimit());
                            double tax = productStock.getPurchasePrice()*taxType.getIgstAfterLimit()*0.01;
                            productStock.setFinalPurchasePrice(productStock.getPurchasePrice()+tax);
                        }else{
                            productStock.setPurchaseTax(taxType.getIgst());
                            double tax = productStock.getPurchasePrice()*taxType.getIgst()*0.01;
                            productStock.setFinalPurchasePrice(productStock.getPurchasePrice()+tax);
                        }
                        //Update stock 
                        Double price = Double.parseDouble(request.getParameter("price"));
                        Double discount = Double.parseDouble(request.getParameter("discount"));
                        //Set Tax and final price
                        if(taxType.getLimit()!=0 && price > taxType.getLimit()){
                            productStock.setTax(taxType.getIgstAfterLimit());
                            double tax = price*taxType.getIgstAfterLimit()*0.01;
                            productStock.setFinalPrice(price+tax);
                        }else{
                            productStock.setTax(taxType.getIgst());
                            double tax = price*taxType.getIgst()*0.01;
                            double dis = discount*price*0.01;
                            productStock.setFinalPrice(price+tax-dis);
                        }
                        ProductStock ps = productService.getProductStockById(adminUsername, productStock.getId());
                        productStock.setQuantity(ps.getQuantity());
                        productService.addProductStock(productStock);
                        return commonMethodsService.convertObjectIntoJson("added");
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
        
    //        Purchase Record
        @RequestMapping(value = {"/product/generateInvoice"}, method = RequestMethod.POST)
        public @ResponseBody
        String generateInvoice(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("customer") Customer customer) {
            try{
                HttpSession session = request.getSession();
                UserLoginAndSecurityCheck check = new UserLoginAndSecurityCheck();  
                int facilityId = Integer.parseInt(request.getParameter("facilityId"));
                if(facilityId<1){
                    return commonMethodsService.convertObjectIntoJson("Please select facility"); 
                }
                if(!PatternVerify.checkNumberPattern(customer.getCustomerNumber())){
                    return commonMethodsService.convertObjectIntoJson("Enter correct number"); 
                }
                if(!PatternVerify.checkNamePattern(customer.getCustomerName())){
                    return commonMethodsService.convertObjectIntoJson("Enter correct name"); 
                }
                if(customer.getCustomerEmail()!=null && !customer.getCustomerEmail().equalsIgnoreCase("")){
                    if(!PatternVerify.checkEmailPattern(customer.getCustomerEmail())){
                        return commonMethodsService.convertObjectIntoJson("Enter correct email"); 
                    }
                }else{
                    customer.setCustomerEmail(null);
                }
                if(customer.getGst()!=null && !customer.getGst().equalsIgnoreCase("")){
                    if(!PatternVerify.checkGSTPattern(customer.getGst())){
                        return commonMethodsService.convertObjectIntoJson("Enter correct gst number"); 
                    }
                }
                String security = check.authenticationWithPermission(request,facilityId,commonMethodsService.getCookieValueByName(request, "session"),"add_sale");
                if(security.equalsIgnoreCase("passed")){
                    if(session.getAttribute("role").equals("admin")){
                        String adminUsername = session.getAttribute("admin").toString();
                        //Check hsn code is valid
                        customer.setAdminUsername(session.getAttribute("admin").toString());
                        Customer c = customerService.getCustomerByNumber(customer.getCustomerNumber());
                        if(c!=null){
                            customer.setAdminUsername(c.getAdminUsername());
                            customer.setId(c.getId());
                            customerService.updateCustomer(customer);
                        }else{
                            customerService.addCustomer(customer);
                        }
                        BillFormat billFormat = billFormatService.getBillFormatByAdminUsername(adminUsername);
                        Invoice lastInvoice = null;
                        Organization organization = organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(adminUsername));
                        if(organization.getCombineFacilityInvoice()==0){
                            lastInvoice = invoiceService.getLastInvoiceIdByFacility(adminUsername, facilityId);
                        }else{
                            lastInvoice = invoiceService.getLastInvoiceIdByAdminUsername(adminUsername);
                        }
                        InvoiceData invoiceData = null;
                        if(lastInvoice!=null){
                            invoiceData = commonMethodsService.getInvoiceIdByDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime(), lastInvoice.getRegisterDate(), lastInvoice.getInvoiceId(), billFormat.getInvoiceNumberPrefix());
                        }else if(billFormat.getInvoiceCountStart()!=0){
                            invoiceData = commonMethodsService.getInvoiceIdByDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime(), null, billFormat.getInvoiceCountStart(), billFormat.getInvoiceNumberPrefix());
                        }else{
                            invoiceData = commonMethodsService.getInvoiceIdByDate(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime(), null, 1, billFormat.getInvoiceNumberPrefix());
                        }
                        String date = commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime();
                        String[] epcs = request.getParameter("epcs").split("\\,");
                        List<ProductStock> productStocks = new ArrayList<>();
                        double total = 0.0;
                        double tax = 0.0;
                        Invoice iv = new Invoice(0, invoiceData.getInvoiceId(), invoiceData.getInvoiceIdPrefix(),0, 0, 0, 0,date, null, "Billed", customer.getId(), 0, 0, facilityId, adminUsername);
                        Invoice invoice = invoiceService.addInvoice(iv);
                        for(int i=0; i<epcs.length; i++){
                            //Get tag
                            RfidTag rfidTag = productService.getRfidTagByCode(adminUsername, epcs[i]);
                            //update stock
                            if(rfidTag.getStatus().equalsIgnoreCase("active")){
                                ProductStock productStock = productService.getProductStockById(adminUsername, rfidTag.getProductCode());
                                if(productStock!=null){
                                    ProductStock updateStock = new ProductStock(productStock);
                                    if(updateStock.getQuantity()>0){
                                        if(!productStocks.stream().filter(o -> o.getId() == updateStock.getId()).findFirst().isPresent()){
                                            //check discount
                                            double discount = Double.parseDouble(request.getParameter(epcs[i]));
                                            if(discount!=updateStock.getDiscount()){
                                                updateStock.setDiscount(discount);
                                                double newDiscount = updateStock.getPrice()*discount*0.01;
                                                double newPrice = updateStock.getPrice()-newDiscount;
                                                TaxType taxType = adminService.getTaxTypeById(Integer.parseInt(updateStock.getHsn()));
                                                //Set Tax and final price for selling
                                                if(taxType.getLimit()!=0 && newPrice > taxType.getLimit()){
                                                    updateStock.setTax(taxType.getIgstAfterLimit());
                                                    double newTax = newPrice*taxType.getIgstAfterLimit()*0.01;
                                                    updateStock.setFinalPrice(newPrice+newTax);
                                                }else{
                                                    updateStock.setTax(taxType.getIgst());
                                                    double newTax = newPrice*taxType.getIgst()*0.01;
                                                    updateStock.setFinalPrice(newPrice+newTax);
                                                }        
                                            }
                                            updateStock.setQuantity(1);
                                            productStocks.add(updateStock);
                                        }else{
                                            for(ProductStock ps: productStocks){
                                                if(ps.getId() == productStock.getId()){
                                                    ps.setQuantity(ps.getQuantity()+1);
                                                    break;
                                                }
                                            }
                                        }
                                        // Generate Invoice
                                        total = total + updateStock.getFinalPrice();
                                        tax = tax + updateStock.getTax();
                                        productStock.setQuantity(productStock.getQuantity()-1);
                                        productService.updateProductStock(productStock);
//                                        remove/update tag
                                        if(organization.getTagReuse()==1){
                                            productService.removeRfidTag(rfidTag);
                                        }else{
                                            rfidTag.setStatus("sold");
                                            rfidTag.setInvoiceId(invoice.getId());
                                            productService.updateRfidTag(rfidTag);
                                        }
                                    }else{
                                        return commonMethodsService.convertObjectIntoJson("Product out of quantity "+productStock.getProductName());
                                    }
                                }else{
                                    return commonMethodsService.convertObjectIntoJson("Product not found");
                                }
                            }else{
                                return commonMethodsService.convertObjectIntoJson("Product already sold "+rfidTag.getEpc());
                            }
                        }
                        // Invoice
                        BigDecimal bd1 = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
                        BigDecimal bd2 = new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP);
                        double subTotal = bd1.doubleValue();
                        double subTax = bd2.doubleValue();
                        invoice.setPaid(subTotal);
                        invoice.setTotal(new BigDecimal(subTotal+subTax).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        invoice.setTax(subTax);
                        invoiceService.updateInvoice(invoice);
                        for(ProductStock ps: productStocks){
                            SellRecord sellRecord = new SellRecord(date, adminUsername, 0, facilityId, invoice.getId(), ps.getFacilityName(), ps.getQuantity(), ps);
                            productService.addSellRecord(sellRecord);
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                InvoicePdf ip = new InvoicePdf();
                                ip.makeInvoice(adminUsername, facilityId, invoice.getId(), customer.getId());
                            }
                        }.start();
                        return commonMethodsService.convertObjectIntoJson(invoice.getId());
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
