/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.ProductDao;
import co.admis.model.ProductStock;
import co.admis.model.ProductStockRecord;
import co.admis.model.PurchaseRecord;
import co.admis.model.RfidTag;
import co.admis.model.RfidTagBackup;
import co.admis.model.SellRecord;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao productDao;
    @Override
    public PurchaseRecord addPurchaseRecord(PurchaseRecord purchaseRecord) {
        return productDao.addPurchaseRecord(purchaseRecord);
    }

    @Override
    public PurchaseRecord updatePurchaseRecord(PurchaseRecord purchaseRecord) {
        return productDao.updatePurchaseRecord(purchaseRecord);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByCode(String purchaseCode) {
        return productDao.getPurchaseRecordByCode(purchaseCode);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByCode(String adminUsername, String purchaseCode) {
        return productDao.getPurchaseRecordByCode(adminUsername, purchaseCode);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByFacilityId(int facilityId) {
        return productDao.getPurchaseRecordByFacilityId(facilityId);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByFacilityId(String adminUsername, int facilityId) {
        return productDao.getPurchaseRecordByFacilityId(adminUsername, facilityId);
    }

    @Override
    public boolean removePurchaseRecord(PurchaseRecord configure) {
        return productDao.removePurchaseRecord(configure);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByAdmin(String adminUsername) {
        return productDao.getPurchaseRecordByAdmin(adminUsername);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByAdminAndStatus(String adminUsername, String status) {
        return productDao.getPurchaseRecordByAdminAndStatus(adminUsername, status);
    }

    @Override
    public ProductStockRecord addProductStockRecord(ProductStockRecord productStockRecord) {
        return productDao.addProductStockRecord(productStockRecord);
    }

    @Override
    public ProductStockRecord updateProductStockRecord(ProductStockRecord productStockRecord) {
        return productDao.updateProductStockRecord(productStockRecord);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByCode(String purchaseCode) {
        return productDao.getProductStockRecordByCode(purchaseCode);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByCode(String adminUsername, String purchaseCode) {
        return productDao.getProductStockRecordByCode(adminUsername, purchaseCode);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByFacilityId(int facilityId) {
        return productDao.getProductStockRecordByFacilityId(facilityId);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByFacilityId(String adminUsername, int facilityId) {
        return productDao.getProductStockRecordByFacilityId(adminUsername, facilityId);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByVendorId(String vendorId) {
        return productDao.getProductStockRecordByVendorId(vendorId);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByVendorId(String adminUsername, String vendorId) {
        return productDao.getProductStockRecordByVendorId(adminUsername, vendorId);
    }

    @Override
    public boolean removeProductStockRecord(ProductStockRecord productStockRecord) {
        return productDao.removeProductStockRecord(productStockRecord);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByAdmin(String adminUsername) {
        return productDao.getProductStockRecordByAdmin(adminUsername);
    }

    @Override
    public List<ProductStockRecord> getProductStockRecordByAdminAndStatus(String adminUsername, String status) {
        return productDao.getProductStockRecordByAdminAndStatus(adminUsername, status);
    }

    @Override
    public RfidTag addRfidTag(RfidTag rfidTag) {
        return productDao.addRfidTag(rfidTag);
    }

    @Override
    public RfidTag updateRfidTag(RfidTag rfidTag) {
        return productDao.updateRfidTag(rfidTag);
    }

    @Override
    public RfidTag getRfidTagByCode(String code) {
        return productDao.getRfidTagByCode(code);
    }

    @Override
    public RfidTag getRfidTagByCode(String adminUsername, String code) {
        return productDao.getRfidTagByCode(adminUsername, code);
    }

    @Override
    public RfidTag getRfidTagByBarcode(String barcode) {
        return productDao.getRfidTagByBarcode(barcode);
    }

    @Override
    public RfidTag getRfidTagByBarcode(String adminUsername, String barcode) {
        return productDao.getRfidTagByBarcode(adminUsername, barcode);
    }

    @Override
    public List<RfidTag> getRfidTagsByProductCode(String adminUsername, int productCode) {
        return productDao.getRfidTagsByProductCode(adminUsername, productCode);
    }
    
    @Override
    public int getRfidTagsSize(String adminUsername, int id) {
        return productDao.getRfidTagsSize(adminUsername, id);
    }

    @Override
    public List<RfidTag> getRfidTagsByAdmin(String adminUsername) {
        return productDao.getRfidTagsByAdmin(adminUsername);
    }

    @Override
    public SellRecord addSellRecord(SellRecord sellRecord) {
        return productDao.addSellRecord(sellRecord);
    }

    @Override
    public SellRecord updateSellRecord(SellRecord sellRecord) {
        return productDao.updateSellRecord(sellRecord);
    }

    @Override
    public List<SellRecord> getSellRecordByCode(String purchaseCode) {
        return productDao.getSellRecordByCode(purchaseCode);
    }

    @Override
    public List<SellRecord> getSellRecordByCode(String adminUsername, String purchaseCode) {
        return productDao.getSellRecordByCode(adminUsername, purchaseCode);
    }

    @Override
    public boolean removeSellRecord(SellRecord sellRecord) {
        return productDao.removeSellRecord(sellRecord);
    }

    @Override
    public List<SellRecord> getSellRecordByAdmin(String adminUsername) {
        return productDao.getSellRecordByAdmin(adminUsername);
    }

    @Override
    public List<SellRecord> getSellRecordByAdminAndStatus(String adminUsername, String status) {
        return productDao.getSellRecordByAdminAndStatus(adminUsername, status);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByVendorId(String vendorId) {
        return productDao.getPurchaseRecordByVendorId(vendorId);
    }

    @Override
    public List<PurchaseRecord> getPurchaseRecordByVendorId(String adminUsername, String vendorId) {
        return productDao.getPurchaseRecordByVendorId(adminUsername, vendorId);
    }

    @Override
    public List<SellRecord> getSellRecordByFacilityId(int facilityId) {
        return productDao.getSellRecordByFacilityId(facilityId);
    }

    @Override
    public List<SellRecord> getSellRecordByFacilityId(String adminUsername, int facilityId) {
        return productDao.getSellRecordByFacilityId(adminUsername, facilityId);
    }

    @Override
    public List<SellRecord> getSellRecordByVendorId(String vendorId) {
        return productDao.getSellRecordByVendorId(vendorId);
    }

    @Override
    public List<SellRecord> getSellRecordByVendorId(String adminUsername, String vendorId) {
        return productDao.getSellRecordByVendorId(adminUsername, vendorId);
    }

    @Override
    public boolean removeRfidTag(RfidTag rfidTag) {
        return productDao.removeRfidTag(rfidTag);
    }

    @Override
    public PurchaseRecord getPurchaseRecordById(int id) {
        return productDao.getPurchaseRecordById(id);
    }

    @Override
    public PurchaseRecord getPurchaseRecordById(String adminUsername, int id) {
        return productDao.getPurchaseRecordById(adminUsername, id);
    }

    @Override
    public ProductStockRecord getProductStockRecordById(int id) {
        return productDao.getProductStockRecordById(id);
    }

    @Override
    public ProductStockRecord getProductStockRecordById(String adminUsername, int id) {
        return productDao.getProductStockRecordById(adminUsername, id);
    }

    @Override
    public SellRecord getSellRecordById(int id) {
        return productDao.getSellRecordById(id);
    }

    @Override
    public SellRecord getSellRecordById(String adminUsername, int id) {
        return productDao.getSellRecordById(adminUsername, id);
    }

    @Override
    public boolean addRfidTag(String[] rfidTagIds, int productId, String adminUsername, int facilityId) {
        return productDao.addRfidTag(rfidTagIds,productId,adminUsername, facilityId);
    }

    @Override
    public ProductStock addProductStock(ProductStock productStockRecord) {
        return productDao.addProductStock(productStockRecord);
    }

    @Override
    public ProductStock updateProductStock(ProductStock productStockRecord) {
        return productDao.updateProductStock(productStockRecord);
    }

    @Override
    public ProductStock getProductStockById(int id) {
        return productDao.getProductStockById(id);
    }

    @Override
    public ProductStock getProductStockById(String adminUsername, int id) {
        return productDao.getProductStockById(adminUsername, id);
    }

    @Override
    public List<ProductStock> getProductStockByCode(String productCode) {
        return productDao.getProductStockByCode(productCode);
    }

    @Override
    public List<ProductStock> getProductStockByCode(String adminUsername, String productCode) {
        return productDao.getProductStockByCode(adminUsername, productCode);
    }
    
    @Override
    public ProductStock getProductStockByCode(String adminUsername, int facilityId,String productCode) {
        return productDao.getProductStockByCode(adminUsername, facilityId, productCode);
    }

    @Override
    public List<ProductStock> getProductStockByFacilityId(int facilityId) {
        return productDao.getProductStockByFacilityId(facilityId);
    }

    @Override
    public List<ProductStock> getProductStockByFacilityId(String adminUsername, int facilityId) {
        return productDao.getProductStockByFacilityId(adminUsername, facilityId);
    }

    @Override
    public List<ProductStock> getProductStockByVendorId(String vendorId) {
        return productDao.getProductStockByVendorId(vendorId);
    }

    @Override
    public List<ProductStock> getProductStockByVendorId(String adminUsername, String vendorId) {
        return productDao.getProductStockByVendorId(adminUsername, vendorId);
    }

    @Override
    public boolean removeProductStock(ProductStock productStockRecord) {
        return productDao.removeProductStock(productStockRecord);
    }

    @Override
    public List<ProductStock> getProductStockByAdmin(String adminUsername) {
        return productDao.getProductStockByAdmin(adminUsername);
    }

    @Override
    public List<ProductStock> getProductStockByAdminAndStatus(String adminUsername, String status) {
        return productDao.getProductStockByAdminAndStatus(adminUsername, status);
    }

    @Override
    public List<ProductStock> getProductStockByName(String adminUsername, int facilityId, String productName) {
        return productDao.getProductStockByName(adminUsername, facilityId, productName);
    }

    @Override
    public List<RfidTag> getRfidTagsByFacilityId(String adminUsername, int facilityId) {
        return productDao.getRfidTagsByFacilityId(adminUsername, facilityId);
    }

    @Override
    public RfidTagBackup addRfidTagBackup(RfidTagBackup rfidTag) {
        return productDao.addRfidTagBackup(rfidTag);
    }

    @Override
    public boolean addRfidTagBackup(String[] rfidTagIds, int productId, String adminUsername, int facilityId) {
        return productDao.addRfidTagBackup(rfidTagIds, productId, adminUsername, facilityId);
    }

    @Override
    public RfidTagBackup updateRfidTagBackup(RfidTagBackup rfidTag) {
        return productDao.updateRfidTagBackup(rfidTag);
    }

    @Override
    public boolean removeRfidTagBackup(RfidTagBackup rfidTag) {
        return productDao.removeRfidTagBackup(rfidTag);
    }

    @Override
    public RfidTagBackup getRfidTagBackupByCode(String code) {
        return productDao.getRfidTagBackupByCode(code);
    }

    @Override
    public RfidTagBackup getRfidTagBackupByCode(String adminUsername, String code) {
        return productDao.getRfidTagBackupByCode(adminUsername, code);
    }

    @Override
    public RfidTagBackup getRfidTagBackupByBarcode(String barcode) {
        return productDao.getRfidTagBackupByBarcode(barcode);
    }

    @Override
    public RfidTagBackup getRfidTagBackupByBarcode(String adminUsername, String barcode) {
        return productDao.getRfidTagBackupByBarcode(adminUsername, barcode);
    }

    @Override
    public List<RfidTagBackup> getRfidTagBackupsByProductCode(String adminUsername, int productCode) {
        return productDao.getRfidTagBackupsByProductCode(adminUsername, productCode);
    }

    @Override
    public int getRfidTagBackupsSize(String adminUsername, int id) {
        return productDao.getRfidTagBackupsSize(adminUsername, id);
    }

    @Override
    public List<RfidTagBackup> getRfidTagBackupsByAdmin(String adminUsername) {
        return productDao.getRfidTagBackupsByAdmin(adminUsername);
    }

    @Override
    public List<RfidTagBackup> getRfidTagBackupsByFacilityId(String adminUsername, int facilityId) {
        return productDao.getRfidTagBackupsByFacilityId(adminUsername, facilityId);
    }
    
}
