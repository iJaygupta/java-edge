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
 * @author admis
 */
@Entity
@Table(name = "\"invoice\"")
public class Invoice implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name = "invoice_id", nullable = false, updatable = false)
    private int invoiceId;
    
    @Column(name = "invoice_id_prefix", nullable = false, updatable = false)
    private String invoiceIdPrefix;
    
    @Column(name = "balance", nullable = false)
    private double balance;
    
    @Column(name = "paid", nullable = false)
    private double paid;
    
    @Column(name = "total", nullable = false)
    private double total;
    
    @Column(name = "tax", nullable = false)
    private double tax;
    
    @Column(name = "generate_date", nullable = false, updatable = false)
    private String registerDate;

    @Column(name = "update_date")
    private String updateDate;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "customer_id", nullable = false, updatable = false)
    private int customerId;
    
    @Column(name = "user_id", nullable = false, updatable = false)
    private int userId;
    
    @Column(name = "update_by", nullable = false, updatable = false)
    private int updateBy;
    
    @Column(name = "facility_id", nullable = false, updatable = false)
    private int facilityId;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public Invoice() {
    }

    public Invoice(int id, int invoiceId, String invoiceIdPrefix, double balance, double paid, double total, double tax, String registerDate, String updateDate, String status, int customerId, int userId, int updateBy, int facilityId, String adminUsername) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.invoiceIdPrefix = invoiceIdPrefix;
        this.balance = balance;
        this.paid = paid;
        this.total = total;
        this.tax = tax;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.status = status;
        this.customerId = customerId;
        this.userId = userId;
        this.updateBy = updateBy;
        this.facilityId = facilityId;
        this.adminUsername = adminUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
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

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getInvoiceIdPrefix() {
        return invoiceIdPrefix;
    }

    public void setInvoiceIdPrefix(String invoiceIdPrefix) {
        this.invoiceIdPrefix = invoiceIdPrefix;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", invoiceId=" + invoiceId + ", invoiceIdPrefix=" + invoiceIdPrefix + ", balance=" + balance + ", paid=" + paid + ", total=" + total + ", tax=" + tax + ", registerDate=" + registerDate + ", updateDate=" + updateDate + ", status=" + status + ", customerId=" + customerId + ", userId=" + userId + ", updateBy=" + updateBy + ", facilityId=" + facilityId + ", adminUsername=" + adminUsername + '}';
    }
    
}
