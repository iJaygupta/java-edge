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
@Table(name = "\"device_configure\"")
public class DeviceConfigure implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name="port", nullable=false)
    private int port;
    
    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "private_key", nullable = false)
    private String privateKey;
    
    @Column(name = "public_key", nullable = false)
    private String publicKey;
    
    @Column(name = "facility_secret", nullable = false)
    private String facilitySecret;
    
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;
    
    @Column(name="facility_id", nullable=false, updatable = false)
    private int facilityId;

    public DeviceConfigure() {
    }

    public DeviceConfigure(int id, int port, String date, String status, String privateKey, String publicKey, String facilitySecret, String adminUsername, int facilityId) {
        this.id = id;
        this.port = port;
        this.date = date;
        this.status = status;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.facilitySecret = facilitySecret;
        this.adminUsername = adminUsername;
        this.facilityId = facilityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getFacilitySecret() {
        return facilitySecret;
    }

    public void setFacilitySecret(String facilitySecret) {
        this.facilitySecret = facilitySecret;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return "DeviceConfigure{" + "id=" + id + ", port=" + port + ", date=" + date + ", status=" + status + ", privateKey=" + privateKey + ", publicKey=" + publicKey + ", facilitySecret=" + facilitySecret + ", adminUsername=" + adminUsername + ", facilityId=" + facilityId + '}';
    }
    
}
