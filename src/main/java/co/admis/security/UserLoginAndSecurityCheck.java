/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tuserlate file, choose Tools | Tuserlates
 * and open the tuserlate in the editor.
 */
package co.admis.security;

import static co.admis.config.ServerConfiguration.IP_WHITE_LIST;
import co.admis.controller.HibernateUtil;
import co.admis.dao.FirebaseUserDaoImpl;
import co.admis.model.AccountIpWhiteList;
import co.admis.model.Admin;
import co.admis.model.Facility;
import co.admis.model.IpList;
import co.admis.model.MessageBody;
import co.admis.model.Organization;
import co.admis.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.SessionCookieOptions;
import com.google.firebase.auth.UserRecord;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class UserLoginAndSecurityCheck {
    
    public boolean checkLoginSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session!=null && (session.getAttribute("user")!=null || session.getAttribute("admin")!=null);
    }
    
    public boolean checkLoginSessionWithIp(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session!=null && (session.getAttribute("user")!=null || session.getAttribute("admin")!=null)){
            if(checkIp(request.getRemoteAddr())){
                return true;
            }
        }
        return false;
    }
    
    public int userAuthentication(HttpServletRequest request){
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) { 
         for (Cookie cookie : cookies) {
             if(cookie.getName().equalsIgnoreCase("accessToken")){
                 token = cookie.getValue();
             }
          }
        }
        HttpSession session = request.getSession();
        if(!token.equalsIgnoreCase("")){
            // Get Firebase 
            String uid = getUserUidByIdToken(token, true);
            //Get user record
            UserRecord userRecord = getUserRecordByUid(uid);
            String number = userRecord.getPhoneNumber();
            User user = getUserByNumber(number);
            if(user!=null && user.getStatus().equalsIgnoreCase("active")){
                Admin admin = getAdminByUsername(user.getAdminUsername());
                if(admin!=null && admin.getStatus().equalsIgnoreCase("active")){
                    Organization organization = getOrganizationById(admin.getOrganizationId());
                    if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), admin.getUsername())){
                        if(session!=null && session.getAttribute("user")!=null){
                            if(session.getAttribute("user").toString().equalsIgnoreCase(number)){
                                return 1;
                            }
                        }else{
                            return 0;
                        }
                    }
                }
                
            }
            revokeUserSession(uid);
        }
        session.removeAttribute("user");
        if(session.getAttribute("role")!=null){
            session.removeAttribute("role");
        }
        session.invalidate();
        return -1;
    }
    
    public String adminAuthentication(HttpServletRequest request){
        String error = "";
         // Get Firebase 
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) { 
         for (Cookie cookie : cookies) {
             if(cookie.getName().equalsIgnoreCase("accessToken")){
                 token = cookie.getValue();
             }
          }
        }else{
            error = "User is not signed in";
        }
        HttpSession session = request.getSession();
        if(!token.equalsIgnoreCase("")){
            //Get user record
            String uid = getUserUidByIdToken(token, true);
            //Get user record
            UserRecord userRecord = getUserRecordByUid(uid);
            if(userRecord!=null){
                String number = userRecord.getPhoneNumber();
                Admin admin = getAdminByNumber(number);
                if(admin!=null){
                    if(admin.getStatus().equalsIgnoreCase("active")){
                        Organization organization = getOrganizationById(admin.getOrganizationId());
                        if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), admin.getUsername())){
                            if(session!=null && session.getAttribute("admin")!=null){            
                                if(session.getAttribute("admin").toString().equals(admin.getUsername())){
                                    return "passed";
                                }
                            }
                            return "session out";
                        }else{
                            error = "Ip is not whitelisted";
                        }
                    }else{
                        error = "Account is "+admin.getStatus();
                    }
                }
            }else{
                error = "User is not found/verified";
            }
            revokeUserSession(uid);
        }else{
            error = "User is not signed in";
        }
        session.removeAttribute("user");
        if(session.getAttribute("role")!=null){
            session.removeAttribute("role");
        }
        session.invalidate();
        return error;
    }
    
    public String authentication(HttpServletRequest request){
        String error = "";
         // Get Firebase 
        String token = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) { 
         for (Cookie cookie : cookies) {
             if(cookie.getName().equalsIgnoreCase("accessToken")){
                 token = cookie.getValue();
             }
          }
        }else{
            error = "User is not signed in";
        }
        HttpSession session = request.getSession();
        if(!token.equalsIgnoreCase("")){
            //Get user record
            String uid = getUserUidByIdToken(token, true);
            //Get user record
            UserRecord userRecord = getUserRecordByUid(uid);
            if(userRecord!=null){
                String number = userRecord.getPhoneNumber();
                Admin admin = getAdminByNumber(number);
                if(admin!=null){
                    if(admin.getStatus().equalsIgnoreCase("active")){
                        Organization organization = getOrganizationById(admin.getOrganizationId());
                        if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), admin.getUsername())){
                            if(session!=null && session.getAttribute("admin")!=null){            
                                if(session.getAttribute("admin").toString().equals(admin.getUsername())){
                                    return "passed";
                                }
                            }
                            return "session out";
                        }else{
                            error = "Ip is not whitelisted";
                        }
                    }else{
                        error = "Account is "+admin.getStatus();
                    }
                }else{
                    User user = getUserByNumber(number);
                    if(user!=null){
                        if(user.getStatus().equalsIgnoreCase("active")){
                            Admin a = getAdminByUsername(user.getAdminUsername());
                            if(a!=null && a.getStatus().equalsIgnoreCase("active")){
                                Organization organization = getOrganizationById(a.getOrganizationId());
                                if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), a.getUsername())){
                                    if(session!=null && session.getAttribute("user")!=null){
                                        if(session.getAttribute("user").toString().equalsIgnoreCase(number)){
                                            return "passed";
                                        }else{
                                            return "session out";
                                        }
                                    }else{
                                        return "session out";  
                                    }
                                }else{
                                    error = "Ip is not whitelisted";
                                }
                            }else{
                                error = "Accounts are blocked by company";
                            }
                        }else{
                            error = "Account is "+user.getStatus();
                        }
                    }else{
                        error = "Account is not available";
                    }
                }
            }else{
                error = "User is not found/verified";
            }
            revokeUserSession(uid);
        }else{
            error = "User is not signed in";
        }
        session.removeAttribute("user");
        if(session.getAttribute("role")!=null){
            session.removeAttribute("role");
        }
        session.invalidate();
        return error;
    }
    
    public boolean refreshIdToken(FirebaseToken firebaseToken, HttpServletResponse response) throws FirebaseAuthException{
        String idToken = FirebaseAuth.getInstance().createCustomToken(firebaseToken.getUid());
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken, true);
        System.out.println(decodedToken.getUid());
        long authTimeMillis = TimeUnit.SECONDS.toMillis(
            (long) decodedToken.getClaims().get("auth_time"));

        // Only process if the user signed in within the last 5 minutes.
        if (System.currentTimeMillis() - authTimeMillis < TimeUnit.MINUTES.toMillis(5)) {
            long expiresIn = TimeUnit.DAYS.toMillis(13);
            SessionCookieOptions options = SessionCookieOptions.builder()
                .setExpiresIn(expiresIn)
                .build();
            String sessionCookie = FirebaseAuth.getInstance().createSessionCookie(idToken, options);
            // Set cookie policy parameters as required.
            Cookie ck=new Cookie("session", sessionCookie);//creating cookie object  
            ck.setHttpOnly(true); 
            response.addCookie(ck);//adding cookie in the response  
            return true;
        }
        return false;
    }
        
    public String authenticationWithPermission(HttpServletRequest request, int facilityId, String sessionCookie, String permission){
        String error = "";
        try {
        if(sessionCookie==null || sessionCookie.equalsIgnoreCase("")){
            return "Session is invalid or expired";
        }    
        final boolean checkRevoked = true;
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifySessionCookie(
        sessionCookie, checkRevoked);    
        HttpSession session = request.getSession();
            String uid = decodedToken.getUid();
            //Get user record
            if(uid!=null && !uid.equalsIgnoreCase("")){
                UserRecord userRecord = getUserRecordByUid(uid);
                if(userRecord!=null){
                    String number = userRecord.getPhoneNumber();
                    Admin admin = getAdminByNumber(number);
                    if(admin!=null){
                        if(admin.getStatus().equalsIgnoreCase("active")){
                            if(facilityId>0){
                                Facility facility = getFacilityById(facilityId, admin.getUsername());
                                if(facility==null || !facility.getFacilityStatus().equalsIgnoreCase("active")){
                                    return "Facility is not available";
                                }
                            }
                            Organization organization = getOrganizationById(admin.getOrganizationId());
                            if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), admin.getUsername())){
                                if(session!=null && session.getAttribute("admin")!=null){            
                                    if(session.getAttribute("admin").toString().equals(admin.getUsername())){
                                        return "passed";
                                    }
                                }
                                return "session out";
                            }else{
                                error = "Ip is not whitelisted";
                            }
                        }else if(admin.getStatus().equalsIgnoreCase("block")){
                            error = "block";
                        }else{
                            error = "Accounts are blocked by company";
                        }
                    }else if(permission!=null && !permission.equalsIgnoreCase("")){
                        User user = getUserByNumber(number);
                        //Check operation facility
                        if(facilityId>0){
                            Facility facility = getFacilityById(facilityId, user.getAdminUsername());
                            if(facility==null || facility.getId()!=user.getFacilityId() || !facility.getFacilityStatus().equalsIgnoreCase("active")){
                                return "Facility is not available";
                            }
                        }
                        if(user!=null && user.getStatus().equalsIgnoreCase("active")){
                        Facility facility = getFacilityById(user.getFacilityId(), user.getAdminUsername());
                            if(facility!=null && facility.getFacilityStatus().equalsIgnoreCase("active")){
                                Admin a = getAdminByUsername(user.getAdminUsername());
                                if(a!=null && !a.getStatus().equalsIgnoreCase("disabled")){
                                    Organization organization = getOrganizationById(a.getOrganizationId());
                                    if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), a.getUsername())){
                                        if(session!=null && session.getAttribute("user")!=null){
                                            if(session.getAttribute("user").toString().equalsIgnoreCase(number)){
                                                if(permission.equalsIgnoreCase("user") || checkUserPermission(user,permission).equalsIgnoreCase("Y")){
                                                    return "passed";
                                                }else{
                                                    return "User is not authorized to access this page";
                                                }
                                            }else{
                                                return "session out";
                                            }
                                        }else{
                                            return "session out";  
                                        }
                                    }else{
                                        error = "Ip is not whitelisted";
                                    }
                                }else{
                                    error = "Accounts are blocked by company";
                                }
                            }else{
                                error = "Facility is blocked by admin";
                            }
                        }else if(user!=null && user.getStatus().equalsIgnoreCase("block")){
                            error = "block";
                        }else{
                            error = "Account is blocked by admin";
                        }    
                    }else{
                        error = "User is not authorized to access this page";
                    }
                }else{
                    error = "User is not found/verified";
                }
            }else{
                error = "User is not found/verified";
            }    
        revokeUserSession(uid);
        session.removeAttribute("user");
        if(session.getAttribute("role")!=null){
            session.removeAttribute("role");
        }
        session.invalidate();
        return error;
        }catch (FirebaseAuthException e) {
            // Session cookie is unavailable, invalid or revoked. Force user to login.
            System.out.println(e);
            return "Please log in again";
        }
    }
    
    public String authenticationForPin(HttpServletRequest request, String sessionCookie){
        String error = "";
        try {
        if(sessionCookie==null || sessionCookie.equalsIgnoreCase("")){
            return "Session is invalid or expired";
        }    
        final boolean checkRevoked = true;
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifySessionCookie(
        sessionCookie, checkRevoked);    
        HttpSession session = request.getSession();
            String uid = decodedToken.getUid();
            //Get user record
            if(uid!=null && !uid.equalsIgnoreCase("")){
                UserRecord userRecord = getUserRecordByUid(uid);
                if(userRecord!=null){
                    String number = userRecord.getPhoneNumber();
                    Admin admin = getAdminByNumber(number);
                    if(admin!=null){
                        if(admin.getStatus().equalsIgnoreCase("active")){
                            Organization organization = getOrganizationById(admin.getOrganizationId());
                            if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), admin.getUsername())){
                                if(session!=null && session.getAttribute("admin")!=null){            
                                    if(session.getAttribute("admin").toString().equals(admin.getUsername())){
                                        return "passed";
                                    }
                                }
                                return "session out";
                            }else{
                                error = "Ip is not whitelisted";
                            }
                        }else if(admin.getStatus().equalsIgnoreCase("block")){
                            return "block";
                        }else{
                            error = "Accounts are blocked by company";
                        }
                    }else{
                        User user = getUserByNumber(number);
                        //Check operation facility
                        if(user!=null && user.getStatus().equalsIgnoreCase("active")){
                        Facility facility = getFacilityById(user.getFacilityId(), user.getAdminUsername());
                            if(facility!=null && facility.getFacilityStatus().equalsIgnoreCase("active")){
                                Admin a = getAdminByUsername(user.getAdminUsername());
                                if(a!=null && !a.getStatus().equalsIgnoreCase("disabled")){
                                    Organization organization = getOrganizationById(a.getOrganizationId());
                                    if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), a.getUsername())){
                                        if(session!=null && session.getAttribute("user")!=null){
                                            if(session.getAttribute("user").toString().equalsIgnoreCase(number)){
                                                return "passed";
                                            }else{
                                                return "session out";
                                            }
                                        }else{
                                            return "session out";  
                                        }
                                    }else{
                                        error = "Ip is not whitelisted";
                                    }
                                }else{
                                    error = "Accounts are blocked by company";
                                }
                            }else{
                                error = "Facility is blocked by admin";
                            }
                        }else if(user!=null && user.getStatus().equalsIgnoreCase("block")){
                            return "block";
                        }else{
                            error = "Account is blocked by admin";
                        }    
                    }
                }else{
                    error = "User is not found/verified";
                }
            }else{
                error = "User is not found/verified";
            }    
        revokeUserSession(uid);
        session.removeAttribute("user");
        if(session.getAttribute("role")!=null){
            session.removeAttribute("role");
        }
        session.invalidate();
        return error;
        }catch (FirebaseAuthException e) {
            // Session cookie is unavailable, invalid or revoked. Force user to login.
            System.out.println(e);
            return "Please log in again";
        }
    }
    
    public String checkUserPermission(User user, String operation) {
        try{
            if(operation.equalsIgnoreCase("view_sale")){
                return user.getUserPermission().getViewSale();
            }else if(operation.equalsIgnoreCase("add_sale")){
                return user.getUserPermission().getAddSale();
            }else if(operation.equalsIgnoreCase("update_sale")){
                return user.getUserPermission().getUpdateSale();
            }else if(operation.equalsIgnoreCase("view_purchase")){
                return user.getUserPermission().getViewPurchase();
            }else if(operation.equalsIgnoreCase("add_purchase")){
                return user.getUserPermission().getAddPurchase();
            }else if(operation.equalsIgnoreCase("update_purchase")){
                return user.getUserPermission().getUpdatePurchase();
            }else if(operation.equalsIgnoreCase("view_brand_group")){
                return user.getUserPermission().getViewbrandGroup();
            }else if(operation.equalsIgnoreCase("update_brand_group")){
                return user.getUserPermission().getUpdateBrandGroup();
            }else if(operation.equalsIgnoreCase("view_size_chart")){
                return user.getUserPermission().getViewSizeChart();
            }else if(operation.equalsIgnoreCase("update_size_chart")){
                return user.getUserPermission().getUpdateSizeChart();
            }else if(operation.equalsIgnoreCase("view_manufacturer")){
                return user.getUserPermission().getViewManufacturer();
            }else if(operation.equalsIgnoreCase("update_manufacturer")){
                return user.getUserPermission().getUpdateManufacturer();
            }else{
                return "N";
            }
        }catch(Exception e){
            System.out.println(e);
            return "N";
        }
    }
    
    public boolean userAuth(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("user")!=null){
            // Collect Logs
//            Log log = new Log(0, null, null, IP_WHITE_LIST, deviceData, ip, UUID, IMSI, MAC, ICCID, IMEI, location, timestamp);
            User user = getUserByNumber(session.getAttribute("user").toString());
            if(user!=null){
                Admin admin = getAdminByUsername(user.getAdminUsername());
                if(admin!=null){
                    Organization organization = getOrganizationById(admin.getOrganizationId());
                    if(organization.getIpEnable()==0 || checkAdminIpWhitelist(request.getRemoteAddr(), admin.getUsername())){
                        if(admin.getStatus().equalsIgnoreCase("active")){
                            if(user.getStatus().equalsIgnoreCase("active")){
                                Facility facility = getFacilityById(user.getFacilityId(), user.getAdminUsername());
                                if(facility.getFacilityStatus().equalsIgnoreCase("active")){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            session.removeAttribute("user");
            if(session.getAttribute("role")!=null){
                session.removeAttribute("role");
            }
            session.invalidate();
        }
        return false;
    }
    
    public boolean adminAuth(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("admin")!=null){            
            // COllect Logs
            //Log log = new Log(0, null, null, IP_WHITE_LIST, deviceData, ip, UUID, IMSI, MAC, ICCID, IMEI, location, timestamp);
            if(checkIp(request.getRemoteAddr())){
                Admin admin = getAdminByUsername(session.getAttribute("admin").toString());
                if(admin!=null){
                    if(admin.getStatus().equalsIgnoreCase("active")){
                        return true;
                    }
                }
            }
            session.removeAttribute("admin");
            session.invalidate();
        }
        return false;
    }
    
    public boolean checkIp(HttpServletRequest request){
        return checkIp(request.getRemoteAddr());
    }
    
    public boolean checkAdminIpWhitelist(String ip, String adminUsername){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM AccountIpWhiteList ip WHERE ip.ip = ?0 and ip.adminUsername = ?1");
            q.setString(0, ip);
            q.setString(0, adminUsername);
            q.setMaxResults(1);
            AccountIpWhiteList data = (AccountIpWhiteList)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data!=null;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Tuserlates.
        }
    }
    
    public boolean checkIp(String ip){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM IpList ip where ip.ip = ?0");
            q.setString(0, ip);
            q.setMaxResults(1);
            IpList data = (IpList)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data!=null || IP_WHITE_LIST==0;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Tuserlates.
        }
    }
    
    public User getUserByNumber(String userNumber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM User user WHERE user.number = ?0");
            q.setString(0, userNumber);
            q.setMaxResults(1);
            User data = (User)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Tuserlates.
        }
    }
    
    public Admin getAdminByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Admin admin WHERE admin.username = ?0");
            q.setString(0, username);
            q.setMaxResults(1);
            Admin data = (Admin)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data; 
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Tuserlates.
        }
    }
    
    public Admin getAdminByNumber(String number) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Admin admin WHERE admin.number = ?0");
            q.setString(0, number);
            q.setMaxResults(1);
            Admin data = (Admin)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data; 
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Tuserlates.
        }
    }
    
    public Facility getFacilityById(int facilityId, String adminUsername) {
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
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Tuserlates.
        }
    }
    
    public Organization getOrganizationById(int id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Organization organization WHERE organization.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Organization data = (Organization)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public String getUserUidByIdToken(String idToken, boolean checkRevoked) {
        try {
            return FirebaseAuth.getInstance().verifyIdToken(idToken).getUid();
        } catch (FirebaseAuthException ex) {
            System.out.println(ex);
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public UserRecord getUserRecordByUid(String uid) {
        try {
            return FirebaseAuth.getInstance().getUser(uid);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean revokeUserSession(String uid) {
        try {
            FirebaseAuth.getInstance().revokeRefreshTokens(uid);
            UserRecord user = FirebaseAuth.getInstance().getUser(uid);
            // Convert to seconds as the auth_time in the token claims is in seconds too.
            long revocationSecond = user.getTokensValidAfterTimestamp() / 1000;
            System.out.println("Tokens revoked at: " + revocationSecond);
            return true;
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}

