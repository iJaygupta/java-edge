/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.UserDao;
import co.admis.model.Log;
import co.admis.model.User;
import co.admis.model.UserPermission;
import co.admis.model.UserRole;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean removeUser(User user) {
        return userDao.removeUser(user);
    }
    
    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User getUserByNumber(String userNumber) {
        return userDao.getUserByNumber(userNumber);
    }

    @Override
    public User getUserForAdminById(int userId, String adminUsername) {
        return userDao.getUserForAdminById(userId, adminUsername);
    }

    @Override
    public List<User> getListOfAdminUsers(String username) {
        return userDao.getListOfAdminUsers(username);
    }

    @Override
    public List<User> getListOfOrganizationUsers(String username) {
        return userDao.getListOfOrganizationUsers(username);
    }

    @Override
    public List<UserRole> getListOfRoles() {
        return userDao.getListOfRoles();
    }

    @Override
    public String getRoleById(int id) {
        return userDao.getRoleById(id);
    }

    @Override
    public ModelAndView userNotAuthorised(User user, HttpSession session) {
        return userDao.userNotAuthorised(user, session);
    }

    @Override
    public User getUserForAdminByNumber(String userNumber, String adminUsername) {
        return userDao.getUserForAdminByNumber(userNumber, adminUsername);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public UserPermission addUserPermission(UserPermission userPermission) {
        return userDao.addUserPermission(userPermission);
    }

    @Override
    public UserPermission updateUserPermission(UserPermission userPermission) {
        return userDao.updateUserPermission(userPermission);
    }

    @Override
    public Log addLog(Log log) {
        return userDao.addLog(log);
    }

    @Override
    public List<User> getListOfUsersByRole(int role, String adminUsername) {
        return userDao.getListOfUsersByRole(role, adminUsername);
    }

}
