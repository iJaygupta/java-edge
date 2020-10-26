/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Admin;
import co.admis.model.Facility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class FacilityDaoImpl implements FacilityDao{
    @Override
    public Facility addFacility(Facility facility) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(facility);
            session.getTransaction().commit();
            session.close();
            return facility;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Facility updateFacility(Facility facility) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(facility);
            session.getTransaction().commit();
            session.close();
            return facility;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
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
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Facility> getListofFacility(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Facility f WHERE f.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<Facility> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeFacilityById(Facility facility) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.remove(facility);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Facility> getListofActiveFacility(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Facility f WHERE f.adminUsername = ?0 and f.facilityStatus = ?1");
            q.setString(0, adminUsername);
            q.setString(1, "Active");
            List<Facility> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
