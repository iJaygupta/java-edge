/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.AccountIpWhiteList;
import co.admis.model.Admin;
import co.admis.model.AdminPermission;
import co.admis.model.BrandGroup;
import co.admis.model.ColorSelection;
import co.admis.model.GlobalOffer;
import co.admis.model.Manufacturer;
import co.admis.model.Register;
import co.admis.model.SizeChart;
import co.admis.model.TaxSlab;
import co.admis.model.TaxType;
import co.admis.model.UserRole;
import co.admis.model.Vendor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author JAY
 */
public class AdminDaoImpl implements AdminDao{
    
    
    @Override
    public Admin getAdminByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Admin admin WHERE admin.username = ?0");
            q.setString(0, username);
            q.setMaxResults(1);
            Admin data = (Admin)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data; 
        }catch(Exception e){
            session.close();
            return null;
        }
    }
    
    @Override
    public Admin getAdminByNumber(String number){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Admin admin WHERE admin.number = ?0");
            q.setString(0, number);
            q.setMaxResults(1);
            Admin data = (Admin)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(admin);
            session.getTransaction().commit();
            session.close();
            return admin;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Admin addAdmin(Admin admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(admin);
            session.getTransaction().commit();
            session.close();
            return admin;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeAdmin(Admin admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(admin);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Admin> getListOfAdmin() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from Admin admin");
            List<Admin> admin = query.list();
            session.getTransaction().commit();
            session.close();
            return admin;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public AdminPermission addAdminPermission(AdminPermission adminPermission) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(adminPermission);
            session.getTransaction().commit();
            session.close();
            return adminPermission;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public AdminPermission updateAdminPermission(AdminPermission adminPermission) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(adminPermission);
            session.getTransaction().commit();
            session.close();
            return adminPermission;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Admin verifyAdminData(String number, String email, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Admin admin WHERE admin.number = ?0 and admin.email = ?1 and admin.username = ?2");
            q.setString(0, number);
            q.setString(1, email);
            q.setString(2, username);
            q.setMaxResults(1);
            Admin data = (Admin)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public AccountIpWhiteList addAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(accountIpWhiteList);
            session.getTransaction().commit();
            session.close();
            return accountIpWhiteList;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public AccountIpWhiteList updateAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(accountIpWhiteList);
            session.getTransaction().commit();
            session.close();
            return accountIpWhiteList;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(accountIpWhiteList);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public AccountIpWhiteList getAccountIpWhiteListByIp(String ip, String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM AccountIpWhiteList ip WHERE ip.ip = ?0 and ip.adminUsername = ?1");
            q.setString(0, ip);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            AccountIpWhiteList data = (AccountIpWhiteList)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<AccountIpWhiteList> getAccountIpWhiteListByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM AccountIpWhiteList ip WHERE ip.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<AccountIpWhiteList> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }
    
    @Override
    public List<AccountIpWhiteList> getAccountIpWhiteList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM AccountIpWhiteList ip");
            List<AccountIpWhiteList> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public SizeChart addSizeChart(SizeChart sizeChart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(sizeChart);
            session.getTransaction().commit();
            session.close();
            return sizeChart;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public SizeChart updateSizeChart(SizeChart sizeChart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(sizeChart);
            session.getTransaction().commit();
            session.close();
            return sizeChart;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public SizeChart getSizeChartById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SizeChart obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            SizeChart data = (SizeChart)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public SizeChart getSizeChartById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SizeChart obj WHERE obj.adminUsername = ?0 and obj.id = ?1");
            q.setString(0, adminUsername);
            q.setInteger(1, id);
            q.setMaxResults(1);
            SizeChart data = (SizeChart)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeSizeChart(SizeChart sizeChart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(sizeChart);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SizeChart> getSizeChartByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SizeChart obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<SizeChart> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<SizeChart> getSizeChartByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SizeChart obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<SizeChart> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public BrandGroup addBrandGroup(BrandGroup brandGroup) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(brandGroup);
            session.getTransaction().commit();
            session.close();
            return brandGroup;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public BrandGroup updateBrandGroup(BrandGroup brandGroup) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(brandGroup);
            session.getTransaction().commit();
            session.close();
            return brandGroup;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public BrandGroup getBrandGroupById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SizeChart obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            BrandGroup data = (BrandGroup)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public BrandGroup getBrandGroupById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SizeChart obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            BrandGroup data = (BrandGroup)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeBrandGroup(BrandGroup brandGroup) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(brandGroup);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<BrandGroup> getBrandGroupByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxType obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<BrandGroup> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<BrandGroup> getBrandGroupByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM BrandGroup obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<BrandGroup> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
            session.close();
            return manufacturer;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(manufacturer);
            session.getTransaction().commit();
            session.close();
            return manufacturer;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Manufacturer obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Manufacturer data = (Manufacturer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public Manufacturer getManufacturerById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Manufacturer obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Manufacturer data = (Manufacturer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeManufacturer(Manufacturer manufacturer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(manufacturer);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Manufacturer> getManufacturerByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Manufacturer obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<Manufacturer> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<Manufacturer> getManufacturerByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Manufacturer obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<Manufacturer> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public TaxSlab addTaxSlab(TaxSlab taxSlab) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(taxSlab);
            session.getTransaction().commit();
            session.close();
            return taxSlab;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public TaxSlab updateTaxSlab(TaxSlab taxSlab) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(taxSlab);
            session.getTransaction().commit();
            session.close();
            return taxSlab;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public TaxSlab getTaxSlabById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxSlab obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            TaxSlab data = (TaxSlab)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public TaxSlab getTaxSlabById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxSlab obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            TaxSlab data = (TaxSlab)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeTaxSlab(TaxSlab taxSlab) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(taxSlab);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<TaxSlab> getTaxSlabByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxSlab obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<TaxSlab> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<TaxSlab> getTaxSlabByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxSlab obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<TaxSlab> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public TaxType addTaxType(TaxType taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(taxType);
            session.getTransaction().commit();
            session.close();
            return taxType;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public TaxType updateTaxType(TaxType taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(taxType);
            session.getTransaction().commit();
            session.close();
            return taxType;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public TaxType getTaxTypeById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxType obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            TaxType data = (TaxType)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeTaxType(TaxType taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(taxType);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<TaxType> getTaxType() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxType obj");
            List<TaxType> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<TaxType> getTaxTypeByStatus(String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM TaxType obj WHERE obj.status = ?0");
            q.setString(0, status);
            List<TaxType> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(userRole);
            session.getTransaction().commit();
            session.close();
            return userRole;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(userRole);
            session.getTransaction().commit();
            session.close();
            return userRole;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public UserRole getUserRoleById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserRole obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            UserRole data = (UserRole)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public UserRole getUserRoleById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserRole obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            UserRole data = (UserRole)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeUserRole(UserRole userRole) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(userRole);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<UserRole> getUserRoleByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserRole obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<UserRole> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<UserRole> getUserRoleByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM UserRole obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<UserRole> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(vendor);
            session.getTransaction().commit();
            session.close();
            return vendor;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Vendor updateVendor(Vendor vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(vendor);
            session.getTransaction().commit();
            session.close();
            return vendor;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Vendor getVendorById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Vendor obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Vendor data = (Vendor)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public Vendor getVendorById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Vendor obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            Vendor data = (Vendor)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeVendor(Vendor vendor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(vendor);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Vendor> getVendorByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Vendor obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<Vendor> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<Vendor> getVendorByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Vendor obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<Vendor> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public GlobalOffer addGlobalOffer(GlobalOffer globalOffer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(globalOffer);
            session.getTransaction().commit();
            session.close();
            return globalOffer;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public GlobalOffer updateGlobalOffer(GlobalOffer globalOffer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(globalOffer);
            session.getTransaction().commit();
            session.close();
            return globalOffer;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public GlobalOffer getGlobalOfferById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM GlobalOffer obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            GlobalOffer data = (GlobalOffer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public GlobalOffer getGlobalOfferById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM GlobalOffer obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            GlobalOffer data = (GlobalOffer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public GlobalOffer getGlobalOffer(String startDate, String endDate, String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM GlobalOffer obj WHERE obj.startDate >= ?0 and obj.endDate <= ?1 and obj.adminUsername = ?2 and obj.status = ?3");
            q.setString(0, startDate);
            q.setString(1, endDate);
            q.setString(2, adminUsername);
            q.setString(3, status);
            q.setMaxResults(1);
            GlobalOffer data = (GlobalOffer)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeGlobalOffer(GlobalOffer globalOffer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(globalOffer);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<GlobalOffer> getGlobalOfferByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM GlobalOffer obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<GlobalOffer> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<GlobalOffer> getGlobalOfferByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM GlobalOffer obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<GlobalOffer> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }
    
    @Override
    public ColorSelection addColorSelection(ColorSelection taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(taxType);
            session.getTransaction().commit();
            session.close();
            return taxType;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ColorSelection updateColorSelection(ColorSelection taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(taxType);
            session.getTransaction().commit();
            session.close();
            return taxType;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ColorSelection getColorSelectionById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ColorSelection obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            ColorSelection data = (ColorSelection)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeColorSelection(ColorSelection taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(taxType);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ColorSelection> getColorSelection() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ColorSelection obj");
            List<ColorSelection> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<ColorSelection> getColorSelectionByStatus(String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ColorSelection obj WHERE obj.status = ?0");
            q.setString(0, status);
            List<ColorSelection> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }
    
    @Override
    public Register addRegister(Register register) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(register);
            session.getTransaction().commit();
            session.close();
            return register;
        }catch(Exception e){
            session.close();
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Register updateRegister(Register register) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(register);
            session.getTransaction().commit();
            session.close();
            return register;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Register getRegisterById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Register obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            Register data = (Register)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public boolean removeRegister(Register taxType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(taxType);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<Register> getRegister() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Register obj");
            List<Register> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

    @Override
    public List<Register> getRegisterByStatus(String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Register obj WHERE obj.status = ?0");
            q.setString(0, status);
            List<Register> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            return null;
        }
    }

}
