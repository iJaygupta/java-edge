/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Customer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class CustomerDaoImpl implements CustomerDao{

    @Override
    public Customer addCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
            session.close();
            return customer;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
            session.close();
            return customer;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.customerEmail = ?0");
            q.setString(0, email);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerByEmail(String adminUsername, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.customerEmail = ?0 and obj.adminUsername = ?1");
            q.setString(0, email);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerByNumber(String number) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.customerNumber = ?0");
            q.setString(0, number);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerByNumber(String adminUsername, String number) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.customerNumber = ?0 and obj.adminUsername = ?1");
            q.setString(0, number);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerByRfid(String tagId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.customerRfid = ?0");
            q.setString(0, tagId);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Customer getCustomerByRfid(String adminUsername, String tagId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.customerRfid = ?0 and obj.adminUsername = ?1");
            q.setString(0, tagId);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Customer data = (Customer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(customer);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Customer> getCustomerByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<Customer> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Customer> getCustomerByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Customer obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<Customer> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
