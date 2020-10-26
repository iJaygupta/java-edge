/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "\"product_stock_record\"")
public class ProductStockRecord implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name = "product_code")
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
    
    @Column(name="purchase_price", nullable = false)
    private double purchasePrice;
    
    @Column(name="purchase_mrp", nullable = false)
    private double purchaseMrp;
    
    @Column(name="purchase_tax", nullable = false)
    private double purchaseTax;
    
    @Column(name="final_purchase_price", nullable = false)
    private double finalPurchasePrice;
    
    @Column(name="quantity", nullable = false, unique = false)
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

    public ProductStockRecord() {
    }

    public ProductStockRecord(int id, String productCode, String productImageUrl, String productName, String packing, String manufacturer, String scheduledDrug, String date, double price, double tax, double discount, double finalPrice, double purchasePrice, double purchaseMrp, double purchaseTax, double finalPurchasePrice, int quantity, String size, String color, String brand, String hsn, String adminUsername, int facilityId, int userId, String vendorName, String facilityName) {
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
        this.purchasePrice = purchasePrice;
        this.purchaseMrp = purchaseMrp;
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public String toString() {
        return "ProductStockRecord{" + "id=" + id + ", productCode=" + productCode + ", productImageUrl=" + productImageUrl + ", productName=" + productName + ", packing=" + packing + ", manufacturer=" + manufacturer + ", scheduledDrug=" + scheduledDrug + ", date=" + date + ", price=" + price + ", tax=" + tax + ", discount=" + discount + ", finalPrice=" + finalPrice + ", purchasePrice=" + purchasePrice + ", purchaseMrp=" + purchaseMrp + ", purchaseTax=" + purchaseTax + ", finalPurchasePrice=" + finalPurchasePrice + ", quantity=" + quantity + ", size=" + size + ", color=" + color + ", brand=" + brand + ", hsn=" + hsn + ", adminUsername=" + adminUsername + ", facilityId=" + facilityId + ", userId=" + userId + ", vendorName=" + vendorName + ", facilityName=" + facilityName + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductStockRecord other = (ProductStockRecord) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tax) != Double.doubleToLongBits(other.tax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.discount) != Double.doubleToLongBits(other.discount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.finalPrice) != Double.doubleToLongBits(other.finalPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.purchasePrice) != Double.doubleToLongBits(other.purchasePrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.purchaseMrp) != Double.doubleToLongBits(other.purchaseMrp)) {
            return false;
        }
        if (Double.doubleToLongBits(this.purchaseTax) != Double.doubleToLongBits(other.purchaseTax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.finalPurchasePrice) != Double.doubleToLongBits(other.finalPurchasePrice)) {
            return false;
        }
        if (this.facilityId != other.facilityId) {
            return false;
        }
        if (!Objects.equals(this.productImageUrl, other.productImageUrl)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.packing, other.packing)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.scheduledDrug, other.scheduledDrug)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.hsn, other.hsn)) {
            return false;
        }
        if (!Objects.equals(this.adminUsername, other.adminUsername)) {
            return false;
        }
        if (!Objects.equals(this.vendorName, other.vendorName)) {
            return false;
        }
        return true;
    }
    
}
