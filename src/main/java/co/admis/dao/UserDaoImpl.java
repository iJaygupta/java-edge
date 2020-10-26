/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Log;
import co.admis.model.User;
import co.admis.model.UserPermission;
import co.admis.model.UserRole;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author JAY
 */
public class UserDaoImpl implements UserDao{

    @Override
    public User addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            return user;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public User updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
            return user;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
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
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public User getUserForAdminById(int userId, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM User user WHERE user.id = ?0 and user.adminUsername = ?1");
            q.setInteger(0, userId);
            q.setString(1, adminUsername);
            List<User> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data.get(0);
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public List<User> getListOfAdminUsers(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Criteria cr = session.createCriteria(User.class)
            .add(Restrictions.eq("adminUsername", username))
            .setProjection(Projections.projectionList()
              .add(Projections.property("id"), "id")
              .add(Projections.property("number"), "number")
              .add(Projections.property("email"), "email")
              .add(Projections.property("name"), "name")
              .add(Projections.property("status"), "status")
              .add(Projections.property("role"), "role")    
              .add(Projections.property("address"), "address")
              .add(Projections.property("employeeId"), "employeeId")            
              .add(Projections.property("registerDate"), "registerDate") 
              .add(Projections.property("facilityId"), "facilityId"))    
            .setResultTransformer(Transformers.aliasToBean(User.class));
            List<User> data = cr.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<User> getListOfOrganizationUsers(String username) {
        return null;
    }

    @Override
    public List<UserRole> getListOfRoles() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserRole user_role");
            List<UserRole> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public String getRoleById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserRole role WHERE role.id = ?0");
            q.setInteger(0, id);
            List<UserRole> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data.get(0).getRole();
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ModelAndView userNotAuthorised(User user, HttpSession session) {
        ModelAndView mav = new ModelAndView("/user/dashboard");
        mav.addObject("user",user);
        mav.addObject("role",session.getAttribute("role").toString());
        mav.addObject("message", "User is not authorized to access this page.");
        return mav;
    }

    @Override
    public User getUserForAdminByNumber(String userNumber, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM User user WHERE user.number = ?0 and user.adminUsername = ?1");
            q.setString(0, userNumber);
            q.setString(1, adminUsername);
            List<User> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data.get(0);
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public User getUserById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM User user WHERE user.id = ?0");
            q.setInteger(0, id);
            List<User> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data.get(0);
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public UserPermission addUserPermission(UserPermission userPermission) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(userPermission);
            session.getTransaction().commit();
            session.close();
            return userPermission;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public UserPermission updateUserPermission(UserPermission userPermission) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(userPermission);
            session.getTransaction().commit();
            session.close();
            return userPermission;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Log addLog(Log log) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
            session.close();
            return log;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<User> getListOfUsersByRole(int role, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Criteria cr = session.createCriteria(User.class)
            .add(Restrictions.eq("adminUsername", adminUsername))
            .add(Restrictions.eq("role", role))
            .setProjection(Projections.projectionList()
              .add(Projections.property("id"), "id")
              .add(Projections.property("number"), "number")
              .add(Projections.property("email"), "email")
              .add(Projections.property("name"), "name")
              .add(Projections.property("status"), "status")
              .add(Projections.property("role"), "role")    
              .add(Projections.property("address"), "address")
              .add(Projections.property("employeeId"), "employeeId")   
              .add(Projections.property("skypeId"), "skypeId")   
              .add(Projections.property("registerDate"), "registerDate"))         
            .setResultTransformer(Transformers.aliasToBean(User.class));
            List<User> data = cr.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
