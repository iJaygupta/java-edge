/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author JAY
 */
@Entity
@Table(name = "\"user\"")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private byte[] password;
    
    @Column(name = "count", nullable = false)
    private int count;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "employee_id")
    private String employeeId;
    
    @Column(name = "user_role", nullable = false)
    private String role;
    
    @Column(name = "register_date", nullable = false, updatable = false)
    private String registerDate;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;
    
    @Column(name = "facility_id", nullable = false, updatable = false)
    private int facilityId;
    
    @Column(name = "new", nullable = false)
    private int newAccount;
    
    @JsonManagedReference
    @OneToOne(mappedBy="user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private UserPermission userPermission;

    public User() {
    }

    public User(int id, String number, String name, String email, byte[] password, int count, String status, String address, String employeeId, String role, String registerDate, String adminUsername, int facilityId, int newAccount, UserPermission userPermission) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.email = email;
        this.password = password;
        this.count = count;
        this.status = status;
        this.address = address;
        this.employeeId = employeeId;
        this.role = role;
        this.registerDate = registerDate;
        this.adminUsername = adminUsername;
        this.facilityId = facilityId;
        this.newAccount = newAccount;
        this.userPermission = userPermission;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserPermission getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(UserPermission userPermission) {
        this.userPermission = userPermission;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(int newAccount) {
        this.newAccount = newAccount;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", number=" + number + ", name=" + name + ", email=" + email + ", password=" + password + ", count=" + count + ", status=" + status + ", address=" + address + ", employeeId=" + employeeId + ", role=" + role + ", registerDate=" + registerDate + ", adminUsername=" + adminUsername + ", facilityId=" + facilityId + ", newAccount=" + newAccount + ", userPermission=" + userPermission + '}';
    }
    
}
