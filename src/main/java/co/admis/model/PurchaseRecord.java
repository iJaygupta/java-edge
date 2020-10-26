/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "\"purchase_record\"")
public class PurchaseRecord implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name = "product_code", updatable = false, nullable = false)
    private String productCode;
    
    @Column(name="product_image_url")
    private String productImageUrl;
    
    @Column(name = "product_name", nullable = false)
    private String productName;
    
    @Column(name="packing")
    private String packing;
    
    @Column(name="manufacturer")
    private String manufacturer;
    
    @Column(name="scheduled_drug")
    private String scheduledDrug;
    
    @Column(name="purchase_mrp", nullable = false)
    private double purchaseMrp;
    
    @Column(name="purchase_price", nullable = false)
    private double purchasePrice;
    
    @Column(name="purchase_tax", nullable = false)
    private double purchaseTax;
    
    @Column(name="final_purchase_price", nullable = false)
    private double finalPurchasePrice;
    
    @Column(name="quantity", nullable = false)
    private int quantity;
    
    @Column(name="size")
    private String size;
    
    @Column(name="color")
    private String color;
    
    @Column(name="brand")
    private String brand;
    
    @Column(name="hsn")
    private String hsn;
    
    @Column(name="date", nullable = false, updatable = false)
    private String date;
    
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;
    
    @Column(name="facility_id")
    private int facilityId;
    
    @Column(name="user_id", nullable=false, updatable = false)
    private int userId;
    
    @Column(name="vendor_name")
    private String vendorName;

    public PurchaseRecord() {
    }

    public PurchaseRecord(int id, String productCode, String productImageUrl, String productName, String packing, String manufacturer, String scheduledDrug, double purchaseMrp, double purchasePrice, double purchaseTax, double finalPurchasePrice, int quantity, String size, String color, String brand, String hsn, String date, String adminUsername, int facilityId, int userId, String vendorName) {
        this.id = id;
        this.productCode = productCode;
        this.productImageUrl = productImageUrl;
        this.productName = productName;
        this.packing = packing;
        this.manufacturer = manufacturer;
        this.scheduledDrug = scheduledDrug;
        this.purchaseMrp = purchaseMrp;
        this.purchasePrice = purchasePrice;
        this.purchaseTax = purchaseTax;
        this.finalPurchasePrice = finalPurchasePrice;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.hsn = hsn;
        this.date = date;
        this.adminUsername = adminUsername;
        this.facilityId = facilityId;
        this.userId = userId;
        this.vendorName = vendorName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getScheduledDrug() {
        return scheduledDrug;
    }

    public void setScheduledDrug(String scheduledDrug) {
        this.scheduledDrug = scheduledDrug;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPurchaseMrp() {
        return purchaseMrp;
    }

    public void setPurchaseMrp(double purchaseMrp) {
        this.purchaseMrp = purchaseMrp;
    }

    public double getPurchaseTax() {
        return purchaseTax;
    }

    public void setPurchaseTax(double purchaseTax) {
        this.purchaseTax = purchaseTax;
    }

    public double getFinalPurchasePrice() {
        return finalPurchasePrice;
    }

    public void setFinalPurchasePrice(double finalPurchasePrice) {
        this.finalPurchasePrice = finalPurchasePrice;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    @Override
    public String toString() {
        return "PurchaseRecord{" + "id=" + id + ", productCode=" + productCode + ", productImageUrl=" + productImageUrl + ", productName=" + productName + ", packing=" + packing + ", manufacturer=" + manufacturer + ", scheduledDrug=" + scheduledDrug + ", purchaseMrp=" + purchaseMrp + ", purchasePrice=" + purchasePrice + ", purchaseTax=" + purchaseTax + ", finalPurchasePrice=" + finalPurchasePrice + ", quantity=" + quantity + ", size=" + size + ", color=" + color + ", brand=" + brand + ", hsn=" + hsn + ", date=" + date + ", adminUsername=" + adminUsername + ", facilityId=" + facilityId + ", userId=" + userId + ", vendorName=" + vendorName + '}';
    }
    
}
