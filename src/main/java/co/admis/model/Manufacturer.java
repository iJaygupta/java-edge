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
@Table(name = "\"manufacturer\"")
public class Manufacturer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name="manufacturer_name", nullable=false)
    private String manufacturerName;
    
    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;

    public Manufacturer() {
    }

    public Manufacturer(int id, String manufacturerName, String date, String status, String adminUsername) {
        this.id = id;
        this.manufacturerName = manufacturerName;
        this.date = date;
        this.status = status;
        this.adminUsername = adminUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    @Override
    public String toString() {
        return "Manufacturer{" + "id=" + id + ", manufacturerName=" + manufacturerName + ", date=" + date + ", status=" + status + ", adminUsername=" + adminUsername + '}';
    }
    
}
