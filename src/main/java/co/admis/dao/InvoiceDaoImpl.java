/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Invoice;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class InvoiceDaoImpl implements InvoiceDao{

    @Override
    public Invoice addInvoice(Invoice invoice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(invoice);
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeInvoice(Invoice invoice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(invoice);
            session.getTransaction().commit();
            session.close();
            return false;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Invoice getInvoiceById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.id = ?0");
            query.setInteger(0, id);
            query.setMaxResults(1);
            Invoice invoice = (Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Invoice getInvoiceForAdmin(int id, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.id = ?0 and invoice.adminUsername = ?1");
            query.setInteger(0, id);
            query.setString(1, adminUsername);
            query.setMaxResults(1);
            Invoice invoice = (Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Invoice getInvoiceForUser(int id, int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.id = ?0 and invoice.userId = ?1");
            query.setInteger(0, id);
            query.setInteger(1, userId);
            query.setMaxResults(1);
            Invoice invoice = (Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return invoice;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Invoice> getListofInvoiceByCustomerId(int customerId, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.customerId = ?0 and invoice.adminUsername = ?1 order by invoice.id DESC");
            query.setInteger(0, customerId);
            query.setString(1, adminUsername);
            List<Invoice> invoices = query.list();
            session.getTransaction().commit();
            session.close();
            return invoices;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Invoice> getListofInvoiceForAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.adminUsername = ?0 order by invoice.id DESC");
            query.setString(0, adminUsername);
            List<Invoice> invoices = query.list();
            session.getTransaction().commit();
            session.close();
            return invoices;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Invoice getLastInvoiceIdByAdminUsername(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.adminUsername = ?0 order by invoice.id DESC");
            query.setString(0, adminUsername);
            query.setMaxResults(1);
            Invoice last = (Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return last;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeInvoiceByCustomerId(int customerId, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("delete from Invoice invoice where invoice.customerId = ?0 and invoice.adminUsername = ?1");
            query.setInteger(0, customerId);
            query.setString(1, adminUsername);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Invoice> getInvoiceForAdminByCustomerAndFacilityId(int customerId, int facilityId, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.customerId = ?0 and invoice.facilityId = ?1  and invoice.adminUsername = ?2 order by invoice.id DESC");
            query.setInteger(0, customerId);
            query.setInteger(1, facilityId);
            query.setString(2, adminUsername);
            List<Invoice> invoices = query.list();
            session.getTransaction().commit();
            session.close();
            return invoices;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Invoice getLastInvoiceIdByFacility(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.adminUsername = ?0 and invoice.facilityId = ?1 order by invoice.id DESC");
            query.setString(0, adminUsername);
            query.setInteger(1, facilityId);
            query.setMaxResults(1);
            Invoice last = (Invoice) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return last;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Invoice> getListofInvoiceForFacility(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Invoice invoice where invoice.adminUsername = ?0 and invoice.facilityId = ?1 order by invoice.id DESC");
            query.setString(0, adminUsername);
            query.setInteger(1, facilityId);
            List<Invoice> invoices = query.list();
            session.getTransaction().commit();
            session.close();
            return invoices;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    
}
