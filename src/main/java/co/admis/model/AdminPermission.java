/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "\"admin_permission\"")
public class AdminPermission implements Serializable{
    @Id
    @OneToOne
    @JoinColumn(name="admin_username", nullable=false, updatable = false)
    @JsonBackReference
    private Admin admin;
    
    @Column(name = "barcode", nullable = false)
    private String barcode;

    public AdminPermission() {
    }

    public AdminPermission(Admin admin, String barcode) {
        this.admin = admin;
        this.barcode = barcode;
    }
    
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "AdminPermission{" + "admin=" + admin.getUsername() + ", barcode=" + barcode + '}';
    }
}
