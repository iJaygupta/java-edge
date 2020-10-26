/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.DeviceConfigure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class DeviceDaoImpl implements DeviceDao{

    @Override
    public DeviceConfigure addDeviceConfigure(DeviceConfigure configure) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(configure);
            session.getTransaction().commit();
            session.close();
            return configure;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public DeviceConfigure updateDeviceConfigure(DeviceConfigure configure) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(configure);
            session.getTransaction().commit();
            session.close();
            return configure;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public DeviceConfigure getDeviceConfigureById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            DeviceConfigure data = (DeviceConfigure)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public DeviceConfigure getDeviceConfigureById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            DeviceConfigure data = (DeviceConfigure)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public DeviceConfigure getDeviceConfigureBySecret(String secret) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.facilitySecret = ?0");
            q.setString(0, secret);
            q.setMaxResults(1);
            DeviceConfigure data = (DeviceConfigure)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public DeviceConfigure getDeviceConfigureBySecret(String adminUsername, String secret) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.facilitySecret = ?0 and obj.adminUsername = ?1");
            q.setString(0, secret);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            DeviceConfigure data = (DeviceConfigure)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByFacilityId(int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.facilityId = ?0");
            q.setInteger(0, facilityId);
            List<DeviceConfigure> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            List<DeviceConfigure> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeDeviceConfigure(DeviceConfigure configure) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(configure);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<DeviceConfigure> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM DeviceConfigure obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<DeviceConfigure> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
}
