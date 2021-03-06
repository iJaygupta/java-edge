/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Admin;
import co.admis.model.Facility;
import co.admis.model.Login;
import co.admis.model.Master;
import co.admis.model.MessageBody;
import co.admis.model.User;
import co.admis.model.UserLoginData;
import co.admis.service.AdminService;
import co.admis.service.CommonMethodsService;
import co.admis.service.MasterService;
import co.admis.service.SecurityService;
import co.admis.service.SmsService;
import co.admis.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class AuthenticationDaoImpl implements AuthenticationDao{

    @Autowired
    AdminService adminService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    SecurityService securityService;
    
    @Autowired
    CommonMethodsService commonMethodsService;
    
    @Autowired
    MasterService masterService;
    
    @Autowired
    SmsService smsService;
    
    private MessageBody messageBody = null;
    @Override
    public String adminAuthentication(Login login) {
        try{
            Admin admin = adminService.getAdminByUsername(login.getUsername());
            if(admin!=null){
                if(admin.getStatus().equalsIgnoreCase("active")){
                    if(securityService.decrypt(admin.getPassword(), securityService.getPrivateKeyByAdminUsername(admin.getUsername())).equals(login.getPassword())){
                        admin.setCount(0);
                        adminService.updateAdmin(admin);
                        return admin.getUsername();
                    }else{
                        if(admin.getCount()>4){
                            //block admin
                            admin.setStatus("block");
                            adminService.updateAdmin(admin);
                        }else{
                            //update count
                            admin.setCount(admin.getCount()+1);
                            adminService.updateAdmin(admin);
                        }
                    }
                }else if(admin.getStatus().equalsIgnoreCase("block")){
                    return "User is blocked, Please unlock it";
                }else{
                    return "User is not active contact developers";
                }
            return "Username or password is incorrect";
            }
            return "Username or password is incorrect";
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String userAuthentication(Login login) {
        try{
            User user = userService.getUserByNumber(login.getUsername());
            if(user!=null){
                Facility facility = getFacilityById(user.getFacilityId(), user.getAdminUsername());
                if(user.getStatus().equalsIgnoreCase("active") && facility.getFacilityStatus().equalsIgnoreCase("active")){
                    if(securityService.decrypt(user.getPassword(), securityService.getPrivateKeyByAdminUsername(user.getAdminUsername())).equals(login.getPassword())){
                        user.setCount(0);
                        userService.updateUser(user);
                        return user.getNumber();
                    }else{
                        if(user.getCount()>4){
                            //Notify user
                            new Thread() {
                                @Override
                                public void run() {
                                    smsService.sendMessage(user.getNumber(),"This is autogenerated message from adeep lab information system to inform you that we have blocked your account cause of multiple wrong login attempts. Please use forget password or contact us.");
                                }
                            }.start();
                            
                            //block admin
                            user.setStatus("block");
                            userService.updateUser(user);
                        }else{
                            //update count
                            user.setCount(user.getCount()+1);
                            userService.updateUser(user);
                        }
                    }
                }else if(user.getStatus().equalsIgnoreCase("block")){
                    return "User is blocked, Please unlock it by forget password";
                }else{
                    return "User is not active contact admin";
                }
            return "Username or password is incorrect";
            }
            return "Username or password is incorrect";
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    @Override
    public String masterAuthentication(Login login) {
        try{
            Master master = masterService.getMasterByUsername(login.getUsername());
            if(master!=null){
                if(master.getStatus().equalsIgnoreCase("active")){
                    if(securityService.decrypt(master.getPassword(), securityService.getPrivateKeyForMaster()).equals(login.getPassword())){
                        master.setCount(0);
                        masterService.updateMaster(master);
                        return master.getUsername();
                    }else{
                        if(master.getCount()>4){
                            //block admin
                            master.setStatus("block");
                            masterService.updateMaster(master);
                        }else{
                            //update count
                            master.setCount(master.getCount()+1);
                            masterService.updateMaster(master);
                        }
                    }
                }else if(master.getStatus().equalsIgnoreCase("block")){
                    return "User is blocked, Please unlock it";
                }else{
                    return "User is not active contact developers";
                }
            return "Username or password is incorrect";
            }
            return "Username or password is incorrect";
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public UserLoginData addUserLoginData(HttpServletRequest request, String username) {
        UserLoginData userLoginData = new UserLoginData();
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        userLoginData.setUsername(username);
        userLoginData.setIp(remoteAddr);
        userLoginData.setDate(commonMethodsService.getCurrentDate());
        userLoginData.setTime(commonMethodsService.getCurrentTime());
        userLoginData.setStatus("Logged In");
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(userLoginData);
            session.getTransaction().commit();
            session.close();
            return userLoginData;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String renewUserSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String renewAdminSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Facility getFacilityById(int facilityId, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Facility f WHERE f.id = ?0 and f.adminUsername = ?1");
            q.setInteger(0, facilityId);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Facility data = (Facility)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
