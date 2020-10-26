/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.BillFormat;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class BillFormatDaoImpl implements BillFormatDao{

    @Override
    public BillFormat addBillFormat(BillFormat billFormat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(billFormat);
            session.getTransaction().commit();
            session.close();
            return billFormat;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public BillFormat updateBillFormat(BillFormat billFormat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(billFormat);
            session.getTransaction().commit();
            session.close();
            return billFormat;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeBillFormat(BillFormat billFormat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(billFormat);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public BillFormat getBillFormatByAdminUsername(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from BillFormat billFormat where billFormat.adminUsername = ?0");
            query.setString(0, adminUsername);
            query.setMaxResults(1);
            BillFormat billFormat = (BillFormat) query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return billFormat;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
