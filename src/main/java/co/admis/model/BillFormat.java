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
 * @author lenovo
 */
@Entity
@Table(name="\"bill_format\"")
public class BillFormat implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name = "due_days")
    private int dueDays;
    
    @Column(name = "gst_number")
    private String gstNumber;
    
    @Column(name = "pan_number")
    private String panNumber;
    
    @Column(name = "cin")
    private String cin;
    
    @Column(name = "sac")
    private String sac;
    
    @Column(name = "heading")
    private String heading;
    
    @Column(name = "sub_heading")
    private String subHeading;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "whatsapp")
    private String whatsapp;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "invoice_number_prefix")
    private String invoiceNumberPrefix;
    
    @Column(name = "invoice_count_start")
    private int invoiceCountStart;
    
    @Column(name = "term1")
    private String term1;
    
    @Column(name = "term2")
    private String term2;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public BillFormat() {
    }

    public BillFormat(int id, int dueDays, String gstNumber, String panNumber, String cin, String sac, String heading, String subHeading, String number, String whatsapp, String email, String invoiceNumberPrefix, int invoiceCountStart, String term1, String term2, String adminUsername) {
        this.id = id;
        this.dueDays = dueDays;
        this.gstNumber = gstNumber;
        this.panNumber = panNumber;
        this.cin = cin;
        this.sac = sac;
        this.heading = heading;
        this.subHeading = subHeading;
        this.number = number;
        this.whatsapp = whatsapp;
        this.email = email;
        this.invoiceNumberPrefix = invoiceNumberPrefix;
        this.invoiceCountStart = invoiceCountStart;
        this.term1 = term1;
        this.term2 = term2;
        this.adminUsername = adminUsername;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDueDays() {
        return dueDays;
    }

    public void setDueDays(int dueDays) {
        this.dueDays = dueDays;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvoiceNumberPrefix() {
        return invoiceNumberPrefix;
    }

    public void setInvoiceNumberPrefix(String invoiceNumberPrefix) {
        this.invoiceNumberPrefix = invoiceNumberPrefix;
    }

    public int getInvoiceCountStart() {
        return invoiceCountStart;
    }

    public void setInvoiceCountStart(int invoiceCountStart) {
        this.invoiceCountStart = invoiceCountStart;
    }

    public String getTerm1() {
        return term1;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public String getTerm2() {
        return term2;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getSac() {
        return sac;
    }

    public void setSac(String sac) {
        this.sac = sac;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Override
    public String toString() {
        return "BillFormat{" + "id=" + id + ", dueDays=" + dueDays + ", gstNumber=" + gstNumber + ", panNumber=" + panNumber + ", cin=" + cin + ", sac=" + sac + ", heading=" + heading + ", subHeading=" + subHeading + ", number=" + number + ", whatsapp=" + whatsapp + ", email=" + email + ", invoiceNumberPrefix=" + invoiceNumberPrefix + ", invoiceCountStart=" + invoiceCountStart + ", term1=" + term1 + ", term2=" + term2 + ", adminUsername=" + adminUsername + '}';
    }
    
}
