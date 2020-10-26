/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.ProductStock;
import co.admis.model.ProductStockRecord;
import co.admis.model.PurchaseRecord;
import co.admis.model.RfidTag;
import co.admis.model.RfidTagBackup;
import co.admis.model.SellRecord;
import java.util.List;

/**
 *
 * @author dell
 */
public interface ProductDao {
    // PurchaseRecord
    public PurchaseRecord addPurchaseRecord(PurchaseRecord purchaseRecord);
    public PurchaseRecord updatePurchaseRecord(PurchaseRecord purchaseRecord);
    public PurchaseRecord getPurchaseRecordById(int id);
    public PurchaseRecord getPurchaseRecordById(String adminUsername, int id);
    public List<PurchaseRecord> getPurchaseRecordByCode(String productCode);
    public List<PurchaseRecord> getPurchaseRecordByCode(String adminUsername, String productCode);
    public List<PurchaseRecord> getPurchaseRecordByFacilityId(int facilityId);
    public List<PurchaseRecord> getPurchaseRecordByFacilityId(String adminUsername, int facilityId);
    public List<PurchaseRecord> getPurchaseRecordByVendorId(String vendorId);
    public List<PurchaseRecord> getPurchaseRecordByVendorId(String adminUsername, String vendorId);
    public boolean removePurchaseRecord(PurchaseRecord purchaseRecord);
    public List<PurchaseRecord> getPurchaseRecordByAdmin(String adminUsername);
    public List<PurchaseRecord> getPurchaseRecordByAdminAndStatus(String adminUsername, String status);
    
    // ProductStockRecord
    public ProductStockRecord addProductStockRecord(ProductStockRecord productStockRecord);
    public ProductStockRecord updateProductStockRecord(ProductStockRecord productStockRecord);
    public ProductStockRecord getProductStockRecordById(int id);
    public ProductStockRecord getProductStockRecordById(String adminUsername, int id);
    public List<ProductStockRecord> getProductStockRecordByCode(String productCode);
    public List<ProductStockRecord> getProductStockRecordByCode(String adminUsername, String productCode);
    public ProductStock getProductStockByCode(String adminUsername, int facilityId, String productCode);
    public List<ProductStockRecord> getProductStockRecordByFacilityId(int facilityId);
    public List<ProductStockRecord> getProductStockRecordByFacilityId(String adminUsername, int facilityId);
    public List<ProductStockRecord> getProductStockRecordByVendorId(String vendorId);
    public List<ProductStockRecord> getProductStockRecordByVendorId(String adminUsername, String vendorId);
    public boolean removeProductStockRecord(ProductStockRecord productStockRecord);
    public List<ProductStockRecord> getProductStockRecordByAdmin(String adminUsername);
    public List<ProductStockRecord> getProductStockRecordByAdminAndStatus(String adminUsername, String status);
    
        // ProductStock
    public ProductStock addProductStock(ProductStock productStockRecord);
    public ProductStock updateProductStock(ProductStock productStockRecord);
    public ProductStock getProductStockById(int id);
    public ProductStock getProductStockById(String adminUsername, int id);
    public List<ProductStock> getProductStockByCode(String productCode);
    public List<ProductStock> getProductStockByName(String adminUsername, int facilityId, String productName);
    public List<ProductStock> getProductStockByCode(String adminUsername, String productCode);
    public List<ProductStock> getProductStockByFacilityId(int facilityId);
    public List<ProductStock> getProductStockByFacilityId(String adminUsername, int facilityId);
    public List<ProductStock> getProductStockByVendorId(String vendorId);
    public List<ProductStock> getProductStockByVendorId(String adminUsername, String vendorId);
    public boolean removeProductStock(ProductStock productStockRecord);
    public List<ProductStock> getProductStockByAdmin(String adminUsername);
    public List<ProductStock> getProductStockByAdminAndStatus(String adminUsername, String status);
    
    //RFID Tag
    public RfidTag addRfidTag(RfidTag rfidTag);
    public boolean addRfidTag(String[] rfidTagIds, int productId, String adminUsername, int facilityId);
    public RfidTag updateRfidTag(RfidTag rfidTag);
    public boolean removeRfidTag(RfidTag rfidTag);
    public RfidTag getRfidTagByCode(String code);
    public RfidTag getRfidTagByCode(String adminUsername, String code);
    public int getRfidTagsSize(String adminUsername, int id);
    public RfidTag getRfidTagByBarcode(String barcode);
    public RfidTag getRfidTagByBarcode(String adminUsername, String barcode);
    public List<RfidTag> getRfidTagsByProductCode(String adminUsername, int productCode);
    public List<RfidTag> getRfidTagsByAdmin(String adminUsername);
    public List<RfidTag> getRfidTagsByFacilityId(String adminUsername, int facilityId);
    
    //RFID Tag backup
    public RfidTagBackup addRfidTagBackup(RfidTagBackup rfidTag);
    public boolean addRfidTagBackup(String[] rfidTagIds, int productId, String adminUsername, int facilityId);
    public RfidTagBackup updateRfidTagBackup(RfidTagBackup rfidTag);
    public boolean removeRfidTagBackup(RfidTagBackup rfidTag);
    public RfidTagBackup getRfidTagBackupByCode(String code);
    public RfidTagBackup getRfidTagBackupByCode(String adminUsername, String code);
    public RfidTagBackup getRfidTagBackupByBarcode(String barcode);
    public RfidTagBackup getRfidTagBackupByBarcode(String adminUsername, String barcode);
    public List<RfidTagBackup> getRfidTagBackupsByProductCode(String adminUsername, int productCode);
    public int getRfidTagBackupsSize(String adminUsername, int id);
    public List<RfidTagBackup> getRfidTagBackupsByAdmin(String adminUsername);
    public List<RfidTagBackup> getRfidTagBackupsByFacilityId(String adminUsername, int facilityId);
    
    //Sell Record
    public SellRecord addSellRecord(SellRecord sellRecord);
    public SellRecord updateSellRecord(SellRecord sellRecord);
    public SellRecord getSellRecordById(int id);
    public SellRecord getSellRecordById(String adminUsername, int id);
    public List<SellRecord> getSellRecordByCode(String productCode);
    public List<SellRecord> getSellRecordByCode(String adminUsername, String productCode);
    public List<SellRecord> getSellRecordByFacilityId(int facilityId);
    public List<SellRecord> getSellRecordByFacilityId(String adminUsername, int facilityId);
    public List<SellRecord> getSellRecordByVendorId(String vendorId);
    public List<SellRecord> getSellRecordByVendorId(String adminUsername, String vendorId);
    public boolean removeSellRecord(SellRecord sellRecord);
    public List<SellRecord> getSellRecordByAdmin(String adminUsername);
    public List<SellRecord> getSellRecordByAdminAndStatus(String adminUsername, String status);
}
