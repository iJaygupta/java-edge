/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.CcavenueTransactionRecord;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class CcAvenueDaoImpl implements CcAvenueDao{

    @Override
    public CcavenueTransactionRecord addCcAvenueTransaction(CcavenueTransactionRecord transaction) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(transaction);
            session.getTransaction().commit();
            session.close();
            return transaction;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public CcavenueTransactionRecord updateCcAvenueTransaction(CcavenueTransactionRecord transaction) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(transaction);
            session.getTransaction().commit();
            session.close();
            return transaction;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean removeCcAvenueTransaction(CcavenueTransactionRecord transaction) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(transaction);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public CcavenueTransactionRecord getCcAvenueTransactionByOrderId(String orderId) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM CcavenueTransactionRecord cc WHERE cc.order_id = ?0");
            q.setString(0, orderId);
            q.setMaxResults(1);
            CcavenueTransactionRecord data = (CcavenueTransactionRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<CcavenueTransactionRecord> getListOfCcAvenueTransactionByPatientId(int patientId) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from CcavenueTransactionRecord cc where cc.patientId = ?0");
            query.setInteger(0, patientId);
            List<CcavenueTransactionRecord> data = query.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<CcavenueTransactionRecord> getAllCcAvenueTransactions() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from CcavenueTransactionRecord cc");
            List<CcavenueTransactionRecord> data = query.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
}
