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
@Table(name = "\"customer\"")
public class Customer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;
    
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    
    @Column(name="customer_age")
    private String customerAge;
    
    @Column(name = "customer_sex")
    private String customerSex;
    
    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;
    
    @Column(name = "customer_email")
    private String customerEmail;
    
    @Column(name = "customer_address")
    private String customerAddress;
    
    @Column(name = "customer_city")
    private String customerCity;
    
    @Column(name = "customer_state")
    private String customerState;
    
    @Column(name = "customer_country")
    private String customerCountry;
    
    @Column(name = "customer_pin")
    private String customerPin;
    
    @Column(name = "customer_rfid")
    private String customerRfid;
    
    @Column(name = "gst")
    private String gst;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public Customer() {
    }

    public Customer(int id, String customerName, String customerAge, String customerSex, String customerNumber, String customerEmail, String customerAddress, String customerCity, String customerState, String customerCountry, String customerPin, String customerRfid, String gst, String adminUsername) {
        this.id = id;
        this.customerName = customerName;
        this.customerAge = customerAge;
        this.customerSex = customerSex;
        this.customerNumber = customerNumber;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerCountry = customerCountry;
        this.customerPin = customerPin;
        this.customerRfid = customerRfid;
        this.gst = gst;
        this.adminUsername = adminUsername;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerPin() {
        return customerPin;
    }

    public void setCustomerPin(String customerPin) {
        this.customerPin = customerPin;
    }

    public String getCustomerRfid() {
        return customerRfid;
    }

    public void setCustomerRfid(String customerRfid) {
        this.customerRfid = customerRfid;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", customerName=" + customerName + ", customerAge=" + customerAge + ", customerSex=" + customerSex + ", customerNumber=" + customerNumber + ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress + ", customerCity=" + customerCity + ", customerState=" + customerState + ", customerCountry=" + customerCountry + ", customerPin=" + customerPin + ", customerRfid=" + customerRfid + ", gst=" + gst + ", adminUsername=" + adminUsername + '}';
    }
    
}
