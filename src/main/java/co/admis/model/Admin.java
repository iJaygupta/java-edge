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
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Entity
@Table(name = "\"admin\"")
public class Admin implements Serializable{
    @Id
    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String username;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    
    @Column(name = "password", nullable = false)
    private byte[] password;
    
    @Column(name = "count", nullable = false)
    private int count;
    
    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "register_date", nullable = false, updatable = false)
    private String registerDate;
    
    @Column(name = "organization_id", nullable = false, updatable = false, unique = true)
    private int organizationId;
    
    @Column(name = "new", nullable = false)
    private int newAccount;
    
    @JsonManagedReference
    @OneToOne(mappedBy="admin", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private AdminPermission adminPermission;

    public Admin() {
    }

    public Admin(String username, String name, String email, String number, byte[] password, int count, String status, String registerDate, int organizationId, int newAccount, AdminPermission adminPermission) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
        this.count = count;
        this.status = status;
        this.registerDate = registerDate;
        this.organizationId = organizationId;
        this.newAccount = newAccount;
        this.adminPermission = adminPermission;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AdminPermission getAdminPermission() {
        return adminPermission;
    }

    public void setAdminPermission(AdminPermission adminPermission) {
        this.adminPermission = adminPermission;
    }

    public int getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(int newAccount) {
        this.newAccount = newAccount;
    }

    @Override
    public String toString() {
        return "Admin{" + "username=" + username + ", name=" + name + ", email=" + email + ", number=" + number + ", password=" + password + ", count=" + count + ", status=" + status + ", registerDate=" + registerDate + ", organizationId=" + organizationId + ", newAccount=" + newAccount + ", adminPermission=" + adminPermission + '}';
    }
    
}
