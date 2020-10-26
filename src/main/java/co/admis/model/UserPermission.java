/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
@Entity
@Table(name = "\"permission\"")
public class UserPermission implements Serializable{
    
    @Id
    @OneToOne
    @JoinColumn(name="user_id", nullable=false, updatable = false)
    @JsonBackReference
    private User user;
    
    @Column(name = "view_sale", nullable = false)
    private String viewSale;
    
    @Column(name = "add_sale", nullable = false)
    private String addSale;
    
    @Column(name = "update_sale", nullable = false)
    private String updateSale;

    @Column(name = "view_purchase", nullable = false)
    private String viewPurchase;
    
    @Column(name = "add_purchase", nullable = false)
    private String addPurchase;
    
    @Column(name = "update_purchase", nullable = false)
    private String updatePurchase;
    
    @Column(name = "view_brand_group", nullable = false)
    private String viewbrandGroup;
    
    @Column(name = "update_brand_group", nullable = false)
    private String updateBrandGroup;
    
    @Column(name = "view_size_chart", nullable = false)
    private String viewSizeChart;
    
    @Column(name = "update_size_chart", nullable = false)
    private String updateSizeChart;
    
    @Column(name = "view_manufacturer", nullable = false)
    private String viewManufacturer;
    
    @Column(name = "update_manufacturer", nullable = false)
    private String updateManufacturer;

    public UserPermission() {
    }

    public UserPermission(User user, String viewSale, String addSale, String updateSale, String viewPurchase, String addPurchase, String updatePurchase, String removePurchase, String viewbrandGroup, String updateBrandGroup, String viewSizeChart, String updateSizeChart, String viewManufacturer, String updateManufacturer) {
        this.user = user;
        this.viewSale = viewSale;
        this.addSale = addSale;
        this.updateSale = updateSale;
        this.viewPurchase = viewPurchase;
        this.addPurchase = addPurchase;
        this.updatePurchase = updatePurchase;
        this.viewbrandGroup = viewbrandGroup;
        this.updateBrandGroup = updateBrandGroup;
        this.viewSizeChart = viewSizeChart;
        this.updateSizeChart = updateSizeChart;
        this.viewManufacturer = viewManufacturer;
        this.updateManufacturer = updateManufacturer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getViewSale() {
        return viewSale;
    }

    public void setViewSale(String viewSale) {
        this.viewSale = viewSale;
    }

    public String getAddSale() {
        return addSale;
    }

    public void setAddSale(String addSale) {
        this.addSale = addSale;
    }

    public String getUpdateSale() {
        return updateSale;
    }

    public void setUpdateSale(String updateSale) {
        this.updateSale = updateSale;
    }

    public String getViewPurchase() {
        return viewPurchase;
    }

    public void setViewPurchase(String viewPurchase) {
        this.viewPurchase = viewPurchase;
    }

    public String getAddPurchase() {
        return addPurchase;
    }

    public void setAddPurchase(String addPurchase) {
        this.addPurchase = addPurchase;
    }

    public String getUpdatePurchase() {
        return updatePurchase;
    }

    public void setUpdatePurchase(String updatePurchase) {
        this.updatePurchase = updatePurchase;
    }

    public String getViewbrandGroup() {
        return viewbrandGroup;
    }

    public void setViewbrandGroup(String viewbrandGroup) {
        this.viewbrandGroup = viewbrandGroup;
    }

    public String getUpdateBrandGroup() {
        return updateBrandGroup;
    }

    public void setUpdateBrandGroup(String updateBrandGroup) {
        this.updateBrandGroup = updateBrandGroup;
    }

    public String getViewSizeChart() {
        return viewSizeChart;
    }

    public void setViewSizeChart(String viewSizeChart) {
        this.viewSizeChart = viewSizeChart;
    }

    public String getUpdateSizeChart() {
        return updateSizeChart;
    }

    public void setUpdateSizeChart(String updateSizeChart) {
        this.updateSizeChart = updateSizeChart;
    }

    public String getViewManufacturer() {
        return viewManufacturer;
    }

    public void setViewManufacturer(String viewManufacturer) {
        this.viewManufacturer = viewManufacturer;
    }

    public String getUpdateManufacturer() {
        return updateManufacturer;
    }

    public void setUpdateManufacturer(String updateManufacturer) {
        this.updateManufacturer = updateManufacturer;
    }

    @Override
    public String toString() {
        return "UserPermission{" + "user=" + user + ", viewSale=" + viewSale + ", addSale=" + addSale + ", updateSale=" + updateSale + ", viewPurchase=" + viewPurchase + ", addPurchase=" + addPurchase + ", updatePurchase=" + updatePurchase + ", viewbrandGroup=" + viewbrandGroup + ", updateBrandGroup=" + updateBrandGroup + ", viewSizeChart=" + viewSizeChart + ", updateSizeChart=" + updateSizeChart + ", viewManufacturer=" + viewManufacturer + ", updateManufacturer=" + updateManufacturer + '}';
    }
}
