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
@Table(name = "\"sell_record\"")
public class SellRecord implements Serializable{
    
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
    
    @Column(name="date", nullable = false, updatable = false)
    private String date;
    
    @Column(name="price", nullable = false)
    private double price;
    
    @Column(name="tax", nullable = false)
    private double tax;
    
    @Column(name="discount", nullable = false)
    private double discount;
    
    @Column(name="final_price", nullable = false)
    private double finalPrice;
    
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
    
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;
    
    @Column(name="facility_id", nullable=false, updatable = false)
    private int facilityId;
    
    @Column(name="user_id", nullable=false, updatable = false)
    private int userId;
    
    @Column(name="vendor_name")
    private String vendorName;
    
    @Column(name="facility_name", nullable=false, updatable = false)
    private String facilityName;
    
    @Column(name="invoice_id", nullable=false, updatable = false)
    private int invoiceId;

    public SellRecord(String date, String adminUsername, int userId, int facilityId, int invoiceId, String facilityName, int quantity,ProductStock productStock) {
        this.productCode = productStock.getProductCode();
        this.productImageUrl = productStock.getProductImageUrl();
        this.productName = productStock.getProductName();
        this.packing = productStock.getPacking();
        this.manufacturer = productStock.getManufacturer();
        this.scheduledDrug = productStock.getScheduledDrug();
        this.date = date;
        this.price = productStock.getPrice();
        this.tax = productStock.getTax();
        this.discount = productStock.getDiscount();
        this.finalPrice = productStock.getFinalPrice();
        this.purchaseMrp = productStock.getPurchaseMrp();
        this.purchasePrice = productStock.getPurchasePrice();
        this.purchaseTax = productStock.getPurchaseTax();
        this.finalPurchasePrice = productStock.getFinalPurchasePrice();
        this.quantity = quantity;
        this.size = productStock.getSize();
        this.color = productStock.getColor();
        this.brand = productStock.getBrand();
        this.hsn = productStock.getHsn();
        this.adminUsername = adminUsername;
        this.facilityId = facilityId;
        this.invoiceId = invoiceId;
        this.userId = userId;
        this.vendorName = productStock.getVendorName();
        this.facilityName = facilityName;
    }

    public SellRecord(int id, String productCode, String productImageUrl, String productName, String packing, String manufacturer, String scheduledDrug, String date, double price, double tax, double discount, double finalPrice, double purchaseMrp, double purchasePrice, double purchaseTax, double finalPurchasePrice, int quantity, String size, String color, String brand, String hsn, String adminUsername, int facilityId, int userId, String vendorName, String facilityName, int invoiceId) {
        this.id = id;
        this.productCode = productCode;
        this.productImageUrl = productImageUrl;
        this.productName = productName;
        this.packing = packing;
        this.manufacturer = manufacturer;
        this.scheduledDrug = scheduledDrug;
        this.date = date;
        this.price = price;
        this.tax = tax;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.purchaseMrp = purchaseMrp;
        this.purchasePrice = purchasePrice;
        this.purchaseTax = purchaseTax;
        this.finalPurchasePrice = finalPurchasePrice;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.hsn = hsn;
        this.adminUsername = adminUsername;
        this.facilityId = facilityId;
        this.userId = userId;
        this.vendorName = vendorName;
        this.facilityName = facilityName;
        this.invoiceId = invoiceId;
    }

    public SellRecord() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
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
    
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "SellRecord{" + "id=" + id + ", productCode=" + productCode + ", productImageUrl=" + productImageUrl + ", productName=" + productName + ", packing=" + packing + ", manufacturer=" + manufacturer + ", scheduledDrug=" + scheduledDrug + ", date=" + date + ", price=" + price + ", tax=" + tax + ", discount=" + discount + ", finalPrice=" + finalPrice + ", purchaseMrp=" + purchaseMrp + ", purchasePrice=" + purchasePrice + ", purchaseTax=" + purchaseTax + ", finalPurchasePrice=" + finalPurchasePrice + ", quantity=" + quantity + ", size=" + size + ", color=" + color + ", brand=" + brand + ", hsn=" + hsn + ", adminUsername=" + adminUsername + ", facilityId=" + facilityId + ", userId=" + userId + ", vendorName=" + vendorName + ", facilityName=" + facilityName + ", invoiceId=" + invoiceId + '}';
    }
    
}
