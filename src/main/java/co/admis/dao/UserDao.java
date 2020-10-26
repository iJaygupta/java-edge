/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.Log;
import co.admis.model.User;
import co.admis.model.UserPermission;
import co.admis.model.UserRole;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JAY
 */
public interface UserDao {
    public User addUser(User user);
    public boolean removeUser(User user);
    public User updateUser(User user);
    public User getUserByNumber(String userNumber);  //user number by session variable only
    public User getUserById(int id);
    public User getUserForAdminById(int userId, String adminUsername); //admin username by session variable only
    public User getUserForAdminByNumber(String userNumber, String adminUsername);
    public List<User> getListOfAdminUsers(String username); //admin username by session variable only
    public List<User> getListOfOrganizationUsers(String username); //organization username by session variable only
    public List<User> getListOfUsersByRole(int role, String adminUsername);
    
    //    User Permission
    public UserPermission addUserPermission(UserPermission userPermission);
    public UserPermission updateUserPermission(UserPermission userPermission);
    
    public List<UserRole> getListOfRoles();
    public String getRoleById(int id);
    
    public ModelAndView userNotAuthorised(User user, HttpSession session);
    
    //    add logs
    public Log addLog(Log log);
}
