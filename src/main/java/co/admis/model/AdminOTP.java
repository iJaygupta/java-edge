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
 * @author Adeep My IT Solution Private Limited
 */
@Entity
@Table(name = "\"admin_otp\"")
public class AdminOTP implements Serializable{
    @Id
    @Column(name = "number", nullable = false, updatable = false, unique = true)
    private String number;
    
    @Column(name = "otp", nullable = false)
    private String otp;
    
    @Column(name = "count", nullable = false)
    private int count;
    
    @Column(name = "timestamp", nullable = false)
    private String timestamp;
    
    @Column(name = "otp_key", nullable = false)
    private String otpKey;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOtpKey() {
        return otpKey;
    }

    public void setOtpKey(String otpKey) {
        this.otpKey = otpKey;
    }

    @Override
    public String toString() {
        return "AdminOTP{" + "number=" + number + ", otp=" + otp + ", count=" + count + ", timestamp=" + timestamp + ", otpKey=" + otpKey + '}';
    }
    
}
