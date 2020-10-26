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
@Table(name = "\"account_ip_white_list\"")
public class AccountIpWhiteList implements Serializable{
    @Id
    @Column(name="admin_username", nullable=false, updatable = false)
    private String adminUsername;
    
    @Column(name = "ip", nullable = false)
    private String ip;
    
    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AccountIpWhiteList{" + "adminUsername=" + adminUsername + ", ip=" + ip + ", timestamp=" + timestamp + '}';
    }
    
}
