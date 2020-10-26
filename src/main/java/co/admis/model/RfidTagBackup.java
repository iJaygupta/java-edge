/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "\"rfid_tag_backup\"")
public class RfidTagBackup implements Serializable{
    
    @Id
    @Column(name = "epc", updatable = false, nullable = false, unique = true)
    private String epc;
    
    @Column(name="barcode", updatable = false, nullable = false, unique = true)
    private String barcode;
    
    @Column(name="product_stock_record_id", updatable = false, nullable = false)
    private int productCode;
    
    @Column(name="facility_id", updatable = false, nullable = false)
    private int facilityId;
    
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;
    
    @Column(name="status", nullable = false)
    private String status;

    public RfidTagBackup() {
    }

    public RfidTagBackup(String epc, String barcode, int productCode, int facilityId, String adminUsername, String status) {
        this.epc = epc;
        this.barcode = barcode;
        this.productCode = productCode;
        this.facilityId = facilityId;
        this.adminUsername = adminUsername;
        this.status = status;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RfidTag{" + "epc=" + epc + ", barcode=" + barcode + ", productCode=" + productCode + ", facilityId=" + facilityId + ", adminUsername=" + adminUsername + ", status=" + status + '}';
    }
    
}
