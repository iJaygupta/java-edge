///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.admis.payment.ccavenue;
//
//import co.admis.model.CcavenueTransactionRecord;
//import co.admis.model.PatientData;
//import co.admis.service.CcAvenueService;
//import co.admis.service.CommonMethodsService;
//import com.ccavenue.security.AesCryptUtil;
//import com.google.firebase.auth.UserRecord;
//import co.admis.service.FirebaseUserService;
//import java.net.URLDecoder;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.StringTokenizer;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @author admis
// */
//@Controller
//public class CcAvenueController {
//    
//    @Autowired
//    CommonMethodsService commonMethodsService;
//    
//    @Autowired
//    FirebaseUserService firebaseUserService;
//    
//    @Autowired
//    CcAvenueService ccAvenueService;
//    
//    @RequestMapping(value = {"/payment/ccavenue/ccavRequestHandler"}, method = RequestMethod.GET)
//    public ModelAndView ccavRequestHandler(HttpServletRequest request, HttpServletResponse response) {
//        
//        
//        ModelAndView mav = null;
//        try{
//            mav = new ModelAndView("/payment/ccavenue/ccavRequestHandler");
//            //Get user token
//            String token = request.getParameter("token");
//
//            //Get user uid by token
//            String uid = firebaseUserService.getUserUidByIdToken(token, true);
//            if(uid!=null){
//                //Get user record
//                UserRecord userRecord = firebaseUserService.getUserRecordByUid(uid);
//                String number = userRecord.getPhoneNumber();
//                if(number!=null){
//                    PatientData pd = patientService.getPatientByNumber(number.substring(3));
//                    if(pd!=null){
//                        String orderId = commonMethodsService.getOrderId(18);
//                        String amount = request.getParameter("amount")+".00";
//                        CcavenueTransactionRecord transaction = new CcavenueTransactionRecord(orderId, null, null, null, null, null, null, null, "INR", amount, "Pending", null, pd.getId(),pd.getAdminUsername());
//                        ccAvenueService.addCcAvenueTransaction(transaction);
//                        mav.addObject("orderId",orderId);
//                        mav.addObject("amount",amount);
//                        mav.addObject("number",pd.getPhone());
//                        return mav;
//                    }
//                }
//            }
//            mav = new ModelAndView("/payment/ccavenue/errorHandler");
//            mav.addObject("error","Session expire");
//            return mav;
//        }catch(Exception e){
//            System.out.println(e);
//            mav = new ModelAndView("payment/errorHandler");
//            mav.addObject("error",e.getMessage());
//            return mav;
//        }
//    }
//    
//    @RequestMapping(value = {"/payment/ccavenue/errorHandler"}, method = RequestMethod.GET)
//    public ModelAndView errorHandler(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mav = null;
//        try{
//            mav = new ModelAndView("/payment/ccavenue/errorHandler");
//            return mav;
//        }catch(Exception e){
//            System.out.println(e);
//            mav = new ModelAndView("error");
//            mav.addObject("error",e.getMessage());
//            return mav;
//        }
//    }
//    
//    @RequestMapping(value = {"/payment/ccavenue/ccavResponseHandler"}, method = RequestMethod.POST)
//    public ModelAndView ccavResponseHandler(HttpServletRequest request, HttpServletResponse response) {
//        
//        
//        ModelAndView mav = null;
//        try{
//            mav = new ModelAndView("/payment/ccavenue/ccavResponseHandler");
//            CcavenueTransactionRecord transaction = new CcavenueTransactionRecord();
//            double amount = 0;
//            String status = "";
//            String workingKey = "4624190E6327A9232DC88AF25E4B4D7C";		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
//            String encResp= request.getParameter("encResp");
//            AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
//            String decResp = aesUtil.decrypt(encResp);
//            StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
//            Hashtable hs=new Hashtable();
//            String pair=null, pname=null, pvalue=null;
//            while (tokenizer.hasMoreTokens()) {
//                    pair = (String)tokenizer.nextToken();
//                    if(pair!=null) {
//                            StringTokenizer strTok=new StringTokenizer(pair, "=");
//                            pname=""; pvalue="";
//                            if(strTok.hasMoreTokens()) {
//                                    pname=(String)strTok.nextToken();
//                                    if(strTok.hasMoreTokens())
//                                            pvalue=(String)strTok.nextToken();
//                                    hs.put(pname, URLDecoder.decode(pvalue));
//                            }
//                    }
//            }
//            Enumeration enumeration = hs.keys();
//            while(enumeration.hasMoreElements()) {
//                    pname=""+enumeration.nextElement();
//                    pvalue=""+ hs.get(pname);
//                    switch(pname){
//                        case "order_id":
//                            transaction.setOrder_id(pvalue);
//                            break;
//
//                        case "tracking_id":
//                            transaction.setTracking_id(pvalue);
//                            break;
//
//                        case "bank_ref_no":
//                            transaction.setBank_ref_no(pvalue);
//                            break;
//
//                        case "order_status":
//                            status = pvalue;
//                            transaction.setOrder_status(pvalue);
//                            break;
//
//                        case "failure_message":
//                            transaction.setFailure_message(pvalue);
//                            break;
//
//                        case "payment_mode":
//                            transaction.setPayment_mode(pvalue);
//                            break;
//
//                        case "card_name":
//                            transaction.setCard_name(pvalue);
//                            break;
//
//                        case "status_code":
//                            transaction.setStatus_code(pvalue);
//                            break;
//
//                        case "status_message":
//                            transaction.setStatus_message(pvalue);
//                            break;
//
//                        case "currency":
//                            transaction.setCurrency(pvalue);
//                            break;
//
//                        case "amount":
//                            amount = Double.parseDouble(pvalue);
//                            transaction.setAmount(pvalue);
//                            break;
//
//                    }
//            }
//            ccAvenueService.updateCcAvenueTransaction(transaction);
//            mav.addObject("transection",transaction);
//            return mav;
//        }catch(Exception e){
//            mav = new ModelAndView("payment/errorHandler");
//            mav.addObject("error",commonMethodsService.convertObjectIntoJson(e));
//            return mav;
//        }
//    } 
//    
//    @RequestMapping(value = {"/payment/ccavenue/ccavResponseHandler"}, method = RequestMethod.GET)
//    public ModelAndView response(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mav = new ModelAndView("/payment/ccavenue/ccavResponseHandler");
//        return mav;
//    }
//}
