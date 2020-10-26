/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import static co.admis.config.ServerConfiguration.SENDER_ID;
import static co.admis.config.ServerConfiguration.MSG91_AUTH_KEY;
import static co.admis.config.ServerConfiguration.ROUTE;
import co.admis.controller.HibernateUtil;
import co.admis.model.MessageBody;
import co.admis.model.AdminOTP;
import co.admis.model.UserOTP;
import co.admis.service.CommonMethodsService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class SmsDaoImpl implements SmsDao{

    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Override
    public MessageBody addMessage(MessageBody messageBody) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(messageBody);
            session.getTransaction().commit();
            session.close();
            return messageBody;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public MessageBody updateMessage(MessageBody messageBody) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(messageBody);
            session.getTransaction().commit();
            session.close();
            return messageBody;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean removeMessage(MessageBody messageBody) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(messageBody);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public MessageBody getMessageByNumber(String number) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM MessageBody messageBody WHERE messageBody.number = ?0");
            q.setString(0, number);
            q.setMaxResults(1);
            MessageBody data = (MessageBody)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public MessageBody getMessageByNumberForAdmin(String number, String adminUsername) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM MessageBody messageBody WHERE messageBody.number = ?0 and messageBody.adminUsername = ?1");
            q.setString(0, number);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            MessageBody data = (MessageBody)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<MessageBody> getListofMessagesForAdmin(String adminUsername) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM MessageBody messageBody WHERE messageBody.adminUsername = ?0");
            q.setString(1, adminUsername);
            List<MessageBody> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean sendMessage(String number, String message) {
        //Your authentication key
             String authkey = MSG91_AUTH_KEY;
            //Multiple mobiles numbers separated by comma
            String mobiles = number;
            //Sender ID,While using route4 sender id should be 6 characters long.
            String senderId = SENDER_ID;
            //Your message to send, Add URL encoding here.
            String text = message;
            //define route
            String route= ROUTE;

            //Prepare Url
            URLConnection myURLConnection=null;
            URL myURL=null;
            BufferedReader reader=null;

            //encoding message
            String encoded_message=URLEncoder.encode(text);

            //Send SMS API
            String mainUrl="http://api.msg91.com/api/sendhttp.php?";

            //Prepare parameter string
            StringBuilder sbPostData= new StringBuilder(mainUrl);
            sbPostData.append("authkey="+authkey);
            sbPostData.append("&mobiles="+mobiles);
            sbPostData.append("&message="+encoded_message);
            sbPostData.append("&route="+route);
            sbPostData.append("&sender="+senderId);

            //final string
            mainUrl = sbPostData.toString();
            try
            {
                //prepare connection
                myURL = new URL(mainUrl);
                myURLConnection = myURL.openConnection();
                myURLConnection.connect();
                reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                //reading response
                String response;
                while ((response = reader.readLine()) != null)
                //print response

                //finally close connection
                reader.close();
            }
            catch (Exception e)
            {
                    System.out.println(e);
                    return false;
            }
        
        return  true;	
    }

    @Override
    public AdminOTP addAdminOTP(AdminOTP adminOTP) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(adminOTP);
            session.getTransaction().commit();
            session.close();
            return adminOTP;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public AdminOTP updateAdminOTP(AdminOTP adminOTP) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(adminOTP);
            session.getTransaction().commit();
            session.close();
            return adminOTP;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean removeAdminOTP(AdminOTP adminOTP) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(adminOTP);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public AdminOTP getAdminOTP(String number) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM AdminOTP adminOTP WHERE adminOTP.number = ?0");
            q.setString(0, number);
            q.setMaxResults(1);
            AdminOTP data = (AdminOTP)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public AdminOTP generateAdminOTP(String number) {
        AdminOTP oTP = new AdminOTP();
        oTP.setNumber(number);
        oTP.setCount(0);
        oTP.setOtp(commonMethodsService.randomSixDigitNumber());
        oTP.setTimestamp(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
        oTP.setOtpKey(commonMethodsService.generateRendomStrongPassword(12));
        return oTP;
    }
    
    @Override
    public UserOTP addUserOTP(UserOTP userOTP) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(userOTP);
            session.getTransaction().commit();
            session.close();
            return userOTP;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public UserOTP updateUserOTP(UserOTP userOTP) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(userOTP);
            session.getTransaction().commit();
            session.close();
            return userOTP;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean removeUserOTP(UserOTP userOTP) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(userOTP);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public UserOTP getUserOTP(String number) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserOTP userOTP WHERE userOTP.number = ?0");
            q.setString(0, number);
            q.setMaxResults(1);
            UserOTP data = (UserOTP)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public UserOTP generateUserOTP(String number) {
        UserOTP oTP = new UserOTP();
        oTP.setNumber(number);
        oTP.setCount(0);
        oTP.setOtp(commonMethodsService.randomSixDigitNumber());
        oTP.setTimestamp(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
        oTP.setOtpKey(commonMethodsService.generateRendomStrongPassword(12));
        return oTP;
    }

    @Override
    public boolean compareTimeForMinutes(String time, int minutes) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy hh:mm:ss");
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(commonMethodsService.getCurrentDate()+" "+commonMethodsService.getCurrentTime());
            return date2.getTime()<=date1.getTime()+(minutes*60000);
        } catch (ParseException ex) {
            Logger.getLogger(SmsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
