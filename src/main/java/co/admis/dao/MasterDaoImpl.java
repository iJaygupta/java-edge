/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Master;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class MasterDaoImpl implements MasterDao{

    @Override
    public Master getMasterByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Master master WHERE master.username = ?0");
            q.setString(0, username);
            q.setMaxResults(1);
            Master data = (Master)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Master updateMaster(Master master) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(master);
            session.getTransaction().commit();
            session.close();
            return master;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean masterSecurityCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("master")!=null){
            Master m = getMasterByUsername(session.getAttribute("master").toString());
            String key = session.getAttribute("key").toString();
            if(m.getSecretKey().equalsIgnoreCase(key)){
                return true;
            }else{
                session.removeAttribute("master");
                session.removeAttribute("key");
                session.invalidate();
                return false;
            }
        }
        return false;
    }
    
}
