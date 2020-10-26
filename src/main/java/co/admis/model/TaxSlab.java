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
@Table(name = "\"tax_slab\"")
public class TaxSlab implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;
    
    @Column(name = "price", nullable = false)
    private int price;
    
    @Column(name = "tax_percentage", nullable = false)
    private int tax;
    
    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public TaxSlab() {
    }

    public TaxSlab(int id, int price, int tax, String date, String adminUsername) {
        this.id = id;
        this.price = price;
        this.tax = tax;
        this.date = date;
        this.adminUsername = adminUsername;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    @Override
    public String toString() {
        return "TaxSlab{" + "id=" + id + ", price=" + price + ", tax=" + tax + ", date=" + date + ", adminUsername=" + adminUsername + '}';
    }
}
