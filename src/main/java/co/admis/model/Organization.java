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
 * @author JAY
 */
@Entity
@Table(name = "\"organization\"")
public class Organization implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone", nullable = false)
    private String phone;
    
    @Column(name = "customer_id_prefix", nullable = false)
    private String customerIdPrefix;
    
    @Column(name = "s3_folder_name", nullable = false, updatable = false)
    private String s3FolderName;
    
    @Column(name = "logo")
    private String logo;
    
    @Column(name = "user_limit", nullable = false)
    private int userLimit;       
    
    @Column(name = "facility_limit", nullable = false)
    private int facilityLimit;   
    
    @Column(name = "register_date", nullable = false, updatable = false)
    private String registerDate;
    
    @Column(name = "ip_enable", nullable = false)
    private int ipEnable;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "tag_reuse")
    private int tagReuse;
    
    @Column(name = "share_facility")
    private int shareFacility;
    
    @Column(name = "combine_facility_invoice")
    private int combineFacilityInvoice;

    public Organization() {
    }

    public Organization(int id, String name, String email, String phone, String customerIdPrefix, String s3FolderName, String logo, byte[] logoBlob, int userLimit, int facilityLimit, String registerDate, int ipEnable, String role, int tagReuse, int shareFacility, int combineFacilityInvoice) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.customerIdPrefix = customerIdPrefix;
        this.s3FolderName = s3FolderName;
        this.logo = logo;
        this.userLimit = userLimit;
        this.facilityLimit = facilityLimit;
        this.registerDate = registerDate;
        this.ipEnable = ipEnable;
        this.role = role;
        this.tagReuse = tagReuse;
        this.shareFacility = shareFacility;
        this.combineFacilityInvoice = combineFacilityInvoice;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerIdPrefix() {
        return customerIdPrefix;
    }

    public void setCustomerIdPrefix(String customerIdPrefix) {
        this.customerIdPrefix = customerIdPrefix;
    }

    public String getS3FolderName() {
        return s3FolderName;
    }

    public void setS3FolderName(String s3FolderName) {
        this.s3FolderName = s3FolderName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    public int getFacilityLimit() {
        return facilityLimit;
    }

    public void setFacilityLimit(int facilityLimit) {
        this.facilityLimit = facilityLimit;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getIpEnable() {
        return ipEnable;
    }

    public void setIpEnable(int ipEnable) {
        this.ipEnable = ipEnable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTagReuse() {
        return tagReuse;
    }

    public void setTagReuse(int tagReuse) {
        this.tagReuse = tagReuse;
    }

    public int getShareFacility() {
        return shareFacility;
    }

    public void setShareFacility(int shareFacility) {
        this.shareFacility = shareFacility;
    }

    public int getCombineFacilityInvoice() {
        return combineFacilityInvoice;
    }

    public void setCombineFacilityInvoice(int combineFacilityInvoice) {
        this.combineFacilityInvoice = combineFacilityInvoice;
    }

    @Override
    public String toString() {
        return "Organization{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", customerIdPrefix=" + customerIdPrefix + ", s3FolderName=" + s3FolderName + ", logo=" + logo + ", userLimit=" + userLimit + ", facilityLimit=" + facilityLimit + ", registerDate=" + registerDate + ", ipEnable=" + ipEnable + ", role=" + role + ", tagReuse=" + tagReuse + ", shareFacility=" + shareFacility + ", combineFacilityInvoice=" + combineFacilityInvoice + '}';
    }
    
}
