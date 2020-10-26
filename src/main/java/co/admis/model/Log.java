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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adeep My IT Solution
 */
@Entity
@Table(name = "\"logs\"")
public class Log implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private int id;
    
    @Column(name = "log", nullable = false, updatable = false)
    private String log;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "device_data")
    private String deviceData;
    
    @Column(name = "ip")
    private String ip;
    
    @Column(name = "uuid")
    private String UUID;
    
    @Column(name = "imsi")
    private String IMSI;
    
    @Column(name = "mac")
    private String MAC;
    
    @Column(name = "iccid")
    private String ICCID;
    
    @Column(name = "imei")
    private String IMEI;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "gps")
    private String gps;
    
    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "app_version")
    private String appVersion;
    
    @Column(name = "timestamp")
    private String timestamp;

    public Log() {
    }

    public Log(int id, String log, String timestamp, int userId, HttpServletRequest request) {
        this.id = id;
        this.log = log;
        this.deviceData = request.getHeader("User-Agent");
        this.timestamp = timestamp;
        this.ip = request.getRemoteAddr();
        this.userId = userId;
    }
    
    public Log(int id, String log, String status, int userId, String transactionId, String timestamp, HttpServletRequest request) {
        this.id = id;
        this.log = log;
        this.status = status;
        this.userId = userId;
        this.ip = request.getRemoteAddr();
        this.ICCID = request.getParameter("ICCID");
        this.IMEI = request.getParameter("IMEI");
        this.IMSI = request.getParameter("IMSI");
        this.UUID = request.getParameter("UUID");
        this.MAC = request.getParameter("MAC");
        this.deviceData = request.getHeader("User-Agent");
        this.gps = request.getParameter("gps");
        this.transactionId = transactionId;
        this.appVersion = request.getParameter("appVersion");
        this.timestamp = timestamp;
    }

    public Log(int id, String log, String status, int userId, String deviceData, String ip, String UUID, String IMSI, String MAC, String ICCID, String IMEI, String location, String timestamp) {
        this.id = id;
        this.log = log;
        this.status = status;
        this.userId = userId;
        this.deviceData = deviceData;
        this.ip = ip;
        this.UUID = UUID;
        this.IMSI = IMSI;
        this.MAC = MAC;
        this.ICCID = ICCID;
        this.IMEI = IMEI;
        this.location = location;
        this.timestamp = timestamp;
    }
    
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getICCID() {
        return ICCID;
    }

    public void setICCID(String ICCID) {
        this.ICCID = ICCID;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(String deviceData) {
        this.deviceData = deviceData;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String toString() {
        return "Log{" + "id=" + id + ", log=" + log + ", status=" + status + ", userId=" + userId + ", deviceData=" + deviceData + ", ip=" + ip + ", UUID=" + UUID + ", IMSI=" + IMSI + ", MAC=" + MAC + ", ICCID=" + ICCID + ", IMEI=" + IMEI + ", location=" + location + ", gps=" + gps + ", transactionId=" + transactionId + ", appVersion=" + appVersion + ", timestamp=" + timestamp + '}';
    }
    
}
