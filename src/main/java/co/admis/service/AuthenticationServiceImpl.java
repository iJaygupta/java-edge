/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.AuthenticationDao;
import co.admis.model.Login;
import co.admis.model.UserLoginData;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    AuthenticationDao authenticationDao;
    
    @Override
    public String adminAuthentication(Login login) {
        return authenticationDao.adminAuthentication(login);
    }

    @Override
    public String userAuthentication(Login login) {
        return authenticationDao.userAuthentication(login);
    }
    
    @Override
    public String masterAuthentication(Login login) {
        return authenticationDao.masterAuthentication(login);
    }

    @Override
    public UserLoginData addUserLoginData(HttpServletRequest request, String username) {
        return authenticationDao.addUserLoginData(request,username);
    }

    @Override
    public String renewUserSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String renewAdminSession() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
