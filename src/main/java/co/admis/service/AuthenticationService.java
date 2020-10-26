/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.Login;
import co.admis.model.UserLoginData;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public interface AuthenticationService {
    public String adminAuthentication(Login login);
    public String userAuthentication(Login login);
    public String masterAuthentication(Login login);
    public UserLoginData addUserLoginData(HttpServletRequest request, String username);
    public String renewUserSession();
    public String renewAdminSession();
}
