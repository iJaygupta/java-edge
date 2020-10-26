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
@Table(name = "\"tax_type\"")
public class TaxType implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;
    
    @Column(name = "cgst", nullable = false)
    private double cgst;
    
    @Column(name = "sgst", nullable = false)
    private double sgst;
    
    @Column(name = "igst", nullable = false)
    private double igst;
    
    @Column(name = "limit", nullable = false)
    private double limit;
    
    @Column(name = "cgst_after_limit", nullable = false)
    private double cgstAfterLimit;
    
    @Column(name = "sgst_after_limit", nullable = false)
    private double sgstAfterLimit;
    
    @Column(name = "igst_after_limit", nullable = false)
    private double igstAfterLimit;
    
    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "description")
    private String description;

    public TaxType() {
    }

    public TaxType(int id, double cgst, double sgst, double igst, double limit, double cgstAfterLimit, double sgstAfterLimit, double igstAfterLimit, String date, String status, String description) {
        this.id = id;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;
        this.limit = limit;
        this.cgstAfterLimit = cgstAfterLimit;
        this.sgstAfterLimit = sgstAfterLimit;
        this.igstAfterLimit = igstAfterLimit;
        this.date = date;
        this.status = status;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCgst() {
        return cgst;
    }

    public void setCgst(double cgst) {
        this.cgst = cgst;
    }

    public double getSgst() {
        return sgst;
    }

    public void setSgst(double sgst) {
        this.sgst = sgst;
    }

    public double getIgst() {
        return igst;
    }

    public void setIgst(double igst) {
        this.igst = igst;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getCgstAfterLimit() {
        return cgstAfterLimit;
    }

    public void setCgstAfterLimit(double cgstAfterLimit) {
        this.cgstAfterLimit = cgstAfterLimit;
    }

    public double getSgstAfterLimit() {
        return sgstAfterLimit;
    }

    public void setSgstAfterLimit(double sgstAfterLimit) {
        this.sgstAfterLimit = sgstAfterLimit;
    }

    public double getIgstAfterLimit() {
        return igstAfterLimit;
    }

    public void setIgstAfterLimit(double igstAfterLimit) {
        this.igstAfterLimit = igstAfterLimit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaxType{" + "id=" + id + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", limit=" + limit + ", cgstAfterLimit=" + cgstAfterLimit + ", sgstAfterLimit=" + sgstAfterLimit + ", igstAfterLimit=" + igstAfterLimit + ", date=" + date + ", status=" + status + ", description=" + description + '}';
    }
    
}
