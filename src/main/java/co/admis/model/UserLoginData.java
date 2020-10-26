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
 * @author Adeep My IT Solution Private Limited
 */
@Entity
@Table(name = "\"user_login_data\"")
public class UserLoginData implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    
    @Column(name = "username", nullable = false, updatable = false)
    private String username;
    
    @Column(name = "ip", nullable = false, updatable = false)
    private String ip;
    
    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Column(name = "time", nullable = false, updatable = false)
    private String time;
    
    @Column(name = "status", nullable = false, updatable = false)
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserLoginData{" + "id=" + id + ", username=" + username + ", ip=" + ip + ", date=" + date + ", time=" + time + ", status=" + status + '}';
    }
    
}
