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
@Table(name = "\"master_panel\"")
public class Master implements Serializable{
    @Id
    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;
    
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    
    @Column(name = "ip")
    private String ip;
    
    @Column(name = "status", nullable = false, unique = true)
    private String status;
    
    @Column(name = "password", nullable = false)
    private byte[] password;
    
    @Column(name = "count", nullable = false)
    private int count;
    
    @Column(name = "secret_key")
    private String secretKey;

    public Master() {
    }

    public Master(String username, String number, String ip, String status, byte[] password, int count, String secretKey) {
        this.username = username;
        this.number = number;
        this.ip = ip;
        this.status = status;
        this.password = password;
        this.count = count;
        this.secretKey = secretKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "Master{" + "username=" + username + ", number=" + number + ", ip=" + ip + ", status=" + status + ", password=" + password + ", count=" + count + ", secretKey=" + secretKey + '}';
    }
    
}
