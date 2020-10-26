/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.ProductStock;
import co.admis.model.ProductStockRecord;
import co.admis.model.PurchaseRecord;
import co.admis.model.RfidTag;
import co.admis.model.RfidTagBackup;
import co.admis.model.SellRecord;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
public class ProductDaoImpl implements ProductDao{

    @Override
    public PurchaseRecord addPurchaseRecord(PurchaseRecord purchaseRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(purchaseRecord);
            session.getTransaction().commit();
            session.close();
            return purchaseRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public PurchaseRecord updatePurchaseRecord(PurchaseRecord purchaseRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(purchaseRecord);
            session.getTransaction().commit();
            session.close();
            return purchaseRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByCode(String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.productCode = ?0");
            q.setString(0, productCode);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByCode(String adminUsername, String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.productCode = ?0 and obj.adminUsername = ?1");
            q.setString(0, productCode);
            q.setString(1, adminUsername);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByFacilityId(int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.facilityId = ?0 order by obj.id DESC");
            q.setInteger(0, facilityId);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByVendorId(String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.vendorId = ?0 order by obj.id DESC");
            q.setString(0, vendorId);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByVendorId(String adminUsername, String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.adminUsername = ?0 and obj.vendorId = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setString(1, vendorId);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.adminUsername = ?0 order by obj.id DESC");
            q.setString(0, adminUsername);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.adminUsername = ?0 and obj.status = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<PurchaseRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removePurchaseRecord(PurchaseRecord purchaseRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(purchaseRecord);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ProductStockRecord addProductStockRecord(ProductStockRecord productStockRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(productStockRecord);
            session.getTransaction().commit();
            session.close();
            return productStockRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ProductStockRecord updateProductStockRecord(ProductStockRecord productStockRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(productStockRecord);
            session.getTransaction().commit();
            session.close();
            return productStockRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByCode(String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.productCode = ?0");
            q.setString(0, productCode);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByCode(String adminUsername, String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.productCode = ?0 and obj.adminUsername = ?1");
            q.setString(0, productCode);
            q.setString(1, adminUsername);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public List<ProductStockRecord> getProductStockRecordByFacilityId(int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.facilityId = ?0 order by obj.id DESC");
            q.setInteger(0, facilityId);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByVendorId(String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.vendorId = ?0 order by obj.id DESC");
            q.setString(0, vendorId);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByVendorId(String adminUsername, String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.adminUsername = ?0 and obj.vendorId = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setString(1, vendorId);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.adminUsername = ?0 order by obj.id DESC");
            q.setString(0, adminUsername);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.adminUsername = ?0 and obj.status = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<ProductStockRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeProductStockRecord(ProductStockRecord productStockRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(productStockRecord);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public ProductStockRecord getProductStockRecordById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            ProductStockRecord data = (ProductStockRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ProductStockRecord getProductStockRecordById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStockRecord obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            ProductStockRecord data = (ProductStockRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTag addRfidTag(RfidTag rfidTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(rfidTag);
            session.getTransaction().commit();
            session.close();
            return rfidTag;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTag updateRfidTag(RfidTag rfidTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(rfidTag);
            session.getTransaction().commit();
            session.close();
            return rfidTag;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public boolean removeRfidTag(RfidTag rfidTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(rfidTag);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTag getRfidTagByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.epc = ?0");
            q.setString(0, code);
            q.setMaxResults(1);
            RfidTag data = (RfidTag)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTag getRfidTagByCode(String adminUsername, String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.epc = ?0 and obj.adminUsername = ?1");
            q.setString(0, code);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            RfidTag data = (RfidTag)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTag getRfidTagByBarcode(String barcode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.barcode = ?0");
            q.setString(0, barcode);
            q.setMaxResults(1);
            RfidTag data = (RfidTag)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTag getRfidTagByBarcode(String adminUsername, String barcode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.barcode = ?0 and obj.adminUsername = ?1");
            q.setString(0, barcode);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            RfidTag data = (RfidTag)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<RfidTag> getRfidTagsByProductCode(String adminUsername, int productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.adminUsername = ?0 and obj.productCode = ?1");
            q.setString(0, adminUsername);
            q.setInteger(1, productCode);
            List<RfidTag> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<RfidTag> getRfidTagsByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<RfidTag> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public SellRecord addSellRecord(SellRecord sellRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(sellRecord);
            session.getTransaction().commit();
            session.close();
            return sellRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public SellRecord updateSellRecord(SellRecord sellRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(sellRecord);
            session.getTransaction().commit();
            session.close();
            return sellRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByCode(String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.productCode = ?0");
            q.setString(0, productCode);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByCode(String adminUsername, String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.productCode = ?0 and obj.adminUsername = ?1");
            q.setString(0, productCode);
            q.setString(1, adminUsername);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public List<SellRecord> getSellRecordByFacilityId(int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.facilityId = ?0 order by obj.id DESC");
            q.setInteger(0, facilityId);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByVendorId(String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.vendorId = ?0 order by obj.id DESC");
            q.setString(0, vendorId);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByVendorId(String adminUsername, String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.adminUsername = ?0 and obj.vendorId = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setString(1, vendorId);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.adminUsername = ?0 order by obj.id DESC");
            q.setString(0, adminUsername);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<SellRecord> getSellRecordByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.adminUsername = ?0 and obj.status = ?1 order by obj.id DESC");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<SellRecord> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public boolean removeSellRecord(SellRecord sellRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(sellRecord);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public PurchaseRecord getPurchaseRecordById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            PurchaseRecord data = (PurchaseRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public PurchaseRecord getPurchaseRecordById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM PurchaseRecord obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            PurchaseRecord data = (PurchaseRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public SellRecord getSellRecordById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            SellRecord data = (SellRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public SellRecord getSellRecordById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM SellRecord obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            SellRecord data = (SellRecord)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean addRfidTag(String[] rfidTagIds, int productId, String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            for(int i=0; i<rfidTagIds.length;i++){
                session.save(new RfidTag(rfidTagIds[i], rfidTagIds[i], productId, facilityId, adminUsername, "active"));
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public ProductStock addProductStock(ProductStock productStockRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(productStockRecord);
            session.getTransaction().commit();
            session.close();
            return productStockRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ProductStock updateProductStock(ProductStock productStockRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(productStockRecord);
            session.getTransaction().commit();
            session.close();
            return productStockRecord;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByCode(String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.productCode = ?0");
            q.setString(0, productCode);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByCode(String adminUsername, String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.productCode = ?0 and obj.adminUsername = ?1");
            q.setString(0, productCode);
            q.setString(1, adminUsername);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public List<ProductStock> getProductStockByFacilityId(int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.facilityId = ?0");
            q.setInteger(0, facilityId);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByVendorId(String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.vendorId = ?0");
            q.setString(0, vendorId);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByVendorId(String adminUsername, String vendorId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.adminUsername = ?0 and obj.vendorId = ?1");
            q.setString(0, adminUsername);
            q.setString(1, vendorId);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<ProductStock> getProductStockByAdminAndStatus(String adminUsername, String status) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.adminUsername = ?0 and obj.status = ?1");
            q.setString(0, adminUsername);
            q.setString(1, status);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeProductStock(ProductStock productStockRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(productStockRecord);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public ProductStock getProductStockById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.id = ?0");
            q.setInteger(0, id);
            q.setMaxResults(1);
            ProductStock data = (ProductStock)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public ProductStock getProductStockById(String adminUsername, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.id = ?0 and obj.adminUsername = ?1");
            q.setInteger(0, id);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            ProductStock data = (ProductStock)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public ProductStock getProductStockByCode(String adminUsername, int facilityId, String productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.facilityId = ?0 and obj.adminUsername = ?1 and productCode = ?2");
            q.setInteger(0, facilityId);
            q.setString(1, adminUsername);
            q.setString(2, productCode);
            q.setMaxResults(1);
            ProductStock data = (ProductStock)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public List<ProductStock> getProductStockByName(String adminUsername, int facilityId, String productName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM ProductStock obj WHERE obj.facilityId = ?0 and obj.adminUsername = ?1 and productName = ?2");
            q.setInteger(0, facilityId);
            q.setString(1, adminUsername);
            q.setString(2, productName);
            q.setMaxResults(1);
            List<ProductStock> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public int getRfidTagsSize(String adminUsername, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RfidTag> getRfidTagsByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTag obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1 and obj.status = ?2");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            q.setString(2, "active");
            List<RfidTag> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTagBackup addRfidTagBackup(RfidTagBackup rfidTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(rfidTag);
            session.getTransaction().commit();
            session.close();
            return rfidTag;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTagBackup updateRfidTagBackup(RfidTagBackup rfidTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(rfidTag);
            session.getTransaction().commit();
            session.close();
            return rfidTag;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public boolean removeRfidTagBackup(RfidTagBackup rfidTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(rfidTag);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTagBackup getRfidTagBackupByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.epc = ?0");
            q.setString(0, code);
            q.setMaxResults(1);
            RfidTagBackup data = (RfidTagBackup)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTagBackup getRfidTagBackupByCode(String adminUsername, String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.epc = ?0 and obj.adminUsername = ?1");
            q.setString(0, code);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            RfidTagBackup data = (RfidTagBackup)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTagBackup getRfidTagBackupByBarcode(String barcode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.barcode = ?0");
            q.setString(0, barcode);
            q.setMaxResults(1);
            RfidTagBackup data = (RfidTagBackup)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public RfidTagBackup getRfidTagBackupByBarcode(String adminUsername, String barcode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.barcode = ?0 and obj.adminUsername = ?1");
            q.setString(0, barcode);
            q.setString(1, adminUsername);
            q.setMaxResults(1);
            RfidTagBackup data = (RfidTagBackup)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<RfidTagBackup> getRfidTagBackupsByProductCode(String adminUsername, int productCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.adminUsername = ?0 and obj.productCode = ?1");
            q.setString(0, adminUsername);
            q.setInteger(1, productCode);
            List<RfidTagBackup> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<RfidTagBackup> getRfidTagBackupsByAdmin(String adminUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.adminUsername = ?0");
            q.setString(0, adminUsername);
            List<RfidTagBackup> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public List<RfidTagBackup> getRfidTagBackupsByFacilityId(String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM RfidTagBackup obj WHERE obj.adminUsername = ?0 and obj.facilityId = ?1 and obj.status = ?2");
            q.setString(0, adminUsername);
            q.setInteger(1, facilityId);
            q.setString(2, "active");
            List<RfidTagBackup> data = q.list();
            session.getTransaction().commit();
            session.close();
            return data;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean addRfidTagBackup(String[] rfidTagIds, int productId, String adminUsername, int facilityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            for(int i=0; i<rfidTagIds.length;i++){
                session.save(new RfidTagBackup(rfidTagIds[i], rfidTagIds[i], productId, facilityId, adminUsername, "active"));
                if (i % 50 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public int getRfidTagBackupsSize(String adminUsername, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
