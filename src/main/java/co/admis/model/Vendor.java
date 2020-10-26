/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "\"vendor\"")
public class Vendor implements Serializable{
    
    @Id
    @Column(name = "vendor_id", updatable = false, nullable = false, unique = true)
    private String vendorId;
    
    @Column(name="vendor_name", nullable = false)
    private String vendorName;
    
    @Column(name = "vendor_type")
    private String vendorType;
    
    @Column(name = "contact_person")
    private String contactPerson;
    
    @Column(name = "number")
    private String number;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "gst")
    private String gst;
    
    @Column(name = "cst")
    private String cst;
    
    @Column(name = "dl20b")
    private String dl20b;
    
    @Column(name = "dl21b")
    private String dl21b;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "date", nullable = false)
    private String date;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public Vendor() {
    }

    public Vendor(String vendorId, String vendorName, String vendorType, String contactPerson, String number, String address, String city, String email, String gst, String cst, String dl20b, String dl21b, String status, String date, String adminUsername) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorType = vendorType;
        this.contactPerson = contactPerson;
        this.number = number;
        this.address = address;
        this.city = city;
        this.email = email;
        this.gst = gst;
        this.cst = cst;
        this.dl20b = dl20b;
        this.dl21b = dl21b;
        this.status = status;
        this.date = date;
        this.adminUsername = adminUsername;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getDl20b() {
        return dl20b;
    }

    public void setDl20b(String dl20b) {
        this.dl20b = dl20b;
    }

    public String getDl21b() {
        return dl21b;
    }

    public void setDl21b(String dl21b) {
        this.dl21b = dl21b;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vendor{" + "vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorType=" + vendorType + ", contactPerson=" + contactPerson + ", number=" + number + ", address=" + address + ", city=" + city + ", email=" + email + ", gst=" + gst + ", cst=" + cst + ", dl20b=" + dl20b + ", dl21b=" + dl21b + ", status=" + status + ", date=" + date + ", adminUsername=" + adminUsername + '}';
    }
    
}
