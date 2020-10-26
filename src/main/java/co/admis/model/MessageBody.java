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
@Table(name = "\"message_body\"")
public class MessageBody implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    
    @Column(name = "number", nullable = false, updatable = false)
    private String number;
    
    @Column(name = "sender_id", nullable = false, updatable = false)
    private String senderId;
    
    @Column(name = "message", nullable = false, updatable = false)
    private String message;
    
    @Column(name = "timestamp", nullable = false, updatable = false)
    private String timestamp;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public MessageBody() {
    }

    public MessageBody(int id, String number, String senderId, String message, String timestamp, String adminUsername) {
        this.id = id;
        this.number = number;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = timestamp;
        this.adminUsername = adminUsername;
    }

    public MessageBody(String number, String message) {
        this.number = number;
        this.message = message;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
    
    @Override
    public String toString() {
        return "MessageBody{" + "id=" + id + ", number=" + number + ", senderId=" + senderId + ", message=" + message + ", timestamp=" + timestamp + ", adminUsername=" + adminUsername + '}';
    }
    
    
}
