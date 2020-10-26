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
@Table(name = "\"facility\"")
public class Facility implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;
    
    @Column(name = "facility_id")
    private String facilityCode;
    
    @Column(name = "facility_name", nullable = false)
    private String facilityName;
    
    @Column(name = "facility_address")
    private String facilityAddress;
    
    @Column(name = "facility_city")
    private String facilityCity;
    
    @Column(name = "facility_state")
    private String facilityState;
    
    @Column(name = "facility_pin")
    private String facilityPin;
    
    @Column(name = "facility_lat")
    private String facilityLatitude;
    
    @Column(name = "facility_lon")
    private String facilityLongitude;
    
    @Column(name = "facility_supervisor_name")
    private String facilitySupervisorName;
    
    @Column(name = "facility_status", nullable = false)
    private String facilityStatus;
    
    @Column(name = "facility_number")
    private String number;
    
    @Column(name = "facility_email")
    private String email;
    
    @Column(name = "facility_whatsapp")
    private String whatsapp;
    
    @Column(name = "facility_website")
    private String facilityWebsite;
    
    @Column(name = "date", nullable = false, updatable = false, unique = true)
    private String date;
    
    @Column(name = "share_data", nullable = false)
    private String shareData;
    
    @Column(name = "facility_secret", nullable = false, unique = true)
    private String facilitySecret;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public Facility() {
    }

    public Facility(int id, String facilityCode, String facilityName, String facilityAddress, String facilityCity, String facilityState, String facilityPin, String facilityLatitude, String facilityLongitude, String facilitySupervisorName, String facilityStatus, String number, String email, String whatsapp, String facilityWebsite, String date, String shareData, String facilitySecret, String adminUsername) {
        this.id = id;
        this.facilityCode = facilityCode;
        this.facilityName = facilityName;
        this.facilityAddress = facilityAddress;
        this.facilityCity = facilityCity;
        this.facilityState = facilityState;
        this.facilityPin = facilityPin;
        this.facilityLatitude = facilityLatitude;
        this.facilityLongitude = facilityLongitude;
        this.facilitySupervisorName = facilitySupervisorName;
        this.facilityStatus = facilityStatus;
        this.number = number;
        this.email = email;
        this.whatsapp = whatsapp;
        this.facilityWebsite = facilityWebsite;
        this.date = date;
        this.shareData = shareData;
        this.facilitySecret = facilitySecret;
        this.adminUsername = adminUsername;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }
    
    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityAddress() {
        return facilityAddress;
    }

    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress;
    }

    public String getFacilityLatitude() {
        return facilityLatitude;
    }

    public void setFacilityLatitude(String facilityLatitude) {
        this.facilityLatitude = facilityLatitude;
    }

    public String getFacilityLongitude() {
        return facilityLongitude;
    }

    public void setFacilityLongitude(String facilityLongitude) {
        this.facilityLongitude = facilityLongitude;
    }

    public String getFacilitySupervisorName() {
        return facilitySupervisorName;
    }

    public void setFacilitySupervisorName(String facilitySupervisorName) {
        this.facilitySupervisorName = facilitySupervisorName;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFacilityStatus() {
        return facilityStatus;
    }

    public void setFacilityStatus(String facilityStatus) {
        this.facilityStatus = facilityStatus;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getShareData() {
        return shareData;
    }

    public void setShareData(String shareData) {
        this.shareData = shareData;
    }

    public String getFacilitySecret() {
        return facilitySecret;
    }

    public void setFacilitySecret(String facilitySecret) {
        this.facilitySecret = facilitySecret;
    }

    public String getFacilityCity() {
        return facilityCity;
    }

    public void setFacilityCity(String facilityCity) {
        this.facilityCity = facilityCity;
    }

    public String getFacilityState() {
        return facilityState;
    }

    public void setFacilityState(String facilityState) {
        this.facilityState = facilityState;
    }

    public String getFacilityPin() {
        return facilityPin;
    }

    public void setFacilityPin(String facilityPin) {
        this.facilityPin = facilityPin;
    }

    public String getFacilityWebsite() {
        return facilityWebsite;
    }

    public void setFacilityWebsite(String facilityWebsite) {
        this.facilityWebsite = facilityWebsite;
    }

    @Override
    public String toString() {
        return "Facility{" + "id=" + id + ", facilityCode=" + facilityCode + ", facilityName=" + facilityName + ", facilityAddress=" + facilityAddress + ", facilityCity=" + facilityCity + ", facilityState=" + facilityState + ", facilityPin=" + facilityPin + ", facilityLatitude=" + facilityLatitude + ", facilityLongitude=" + facilityLongitude + ", facilitySupervisorName=" + facilitySupervisorName + ", facilityStatus=" + facilityStatus + ", number=" + number + ", email=" + email + ", whatsapp=" + whatsapp + ", facilityWebsite=" + facilityWebsite + ", date=" + date + ", shareData=" + shareData + ", facilitySecret=" + facilitySecret + ", adminUsername=" + adminUsername + '}';
    }
    
}
