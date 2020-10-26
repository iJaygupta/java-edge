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
@Table(name = "\"size_chart\"")
public class SizeChart implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name="size", nullable=false)
    private String size;
    
    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;

    public SizeChart() {
    }

    public SizeChart(int id, String size, String date, String status, String adminUsername) {
        this.id = id;
        this.size = size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
        return "SizeChart{" + "id=" + id + ", size=" + size + ", date=" + date + ", status=" + status + ", adminUsername=" + adminUsername + '}';
    }
    
}
