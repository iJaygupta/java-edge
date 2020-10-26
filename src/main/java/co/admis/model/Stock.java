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
@Table(name = "\"stock\"")
public class Stock implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name = "product_code", updatable = false, nullable = false, unique = true)
    private String productCode;   
    
    @Column(name = "quantity", nullable = false)
    private int quantity;
    
    @Column(name = "facility_id", nullable = false, updatable = false)
    private int facilityId;

    public Stock() {
    }

    public Stock(int id, String productCode, int quantity, int facilityId) {
        this.id = id;
        this.productCode = productCode;
        this.quantity = quantity;
        this.facilityId = facilityId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", productCode=" + productCode + ", quantity=" + quantity + ", facilityId=" + facilityId + '}';
    }
}
