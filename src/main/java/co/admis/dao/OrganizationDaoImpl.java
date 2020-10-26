/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.Organization;
import co.admis.model.OrganizationRole;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class OrganizationDaoImpl implements OrganizationDao{

    @Override
    public Organization addOrganization(Organization organization) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(organization);
            session.getTransaction().commit();
            session.close();
            return organization;
        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(organization);
            session.getTransaction().commit();
            session.close();
            return organization;
        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeOrganization(Organization organization) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.remove(organization);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Organization getOrganizationById(int id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Organization organization WHERE organization.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Organization data = (Organization)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Organization> getOrganizationsList() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Organization organization");
            List<Organization> organization = query.list();
            session.getTransaction().commit();
            session.close();
            return organization;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int getOrganizationIdByAdminUsername(String adminUsername) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("select admin.organizationId FROM Admin admin WHERE admin.username = ?0");
            q.setString(0, adminUsername);
            q.setMaxResults(1);
            int id = (int)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return id;
        }catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }

    @Override
    public List<OrganizationRole> getOrganizationRoles() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from OrganizationRole o");
            List<OrganizationRole> organizationRoles = query.list();
            session.getTransaction().commit();
            session.close();
            return organizationRoles;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public OrganizationRole addOrganizationRole(OrganizationRole organizationRole) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(organizationRole);
            session.getTransaction().commit();
            session.close();
            return organizationRole;
        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public OrganizationRole updateOrganizationRole(OrganizationRole organizationRole) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(organizationRole);
            session.getTransaction().commit();
            session.close();
            return organizationRole;
        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeOrganizationRole(OrganizationRole organizationRole) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(organizationRole);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            System.out.println(e);
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public OrganizationRole getOrganizationRoleById(int id) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM OrganizationRole o WHERE o.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            OrganizationRole data = (OrganizationRole)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Organization getOrganizationByAdminUsername(String adminUsername) {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q1 = session.createQuery("select admin.organizationId FROM Admin admin WHERE admin.username = ?0");
            Query q2 = session.createQuery("FROM Organization organization WHERE organization.id = ?0");
            q1.setString(0, adminUsername);
            q1.setMaxResults(1);
            int id = (int)q1.uniqueResult();
            q2.setInteger(0, id);
            q2.setMaxResults(1);
            Organization data = (Organization)q2.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
}
