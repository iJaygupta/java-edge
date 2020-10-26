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
 * @author dell
 */
@Entity
@Table(name="\"color_selection\"")
public class ColorSelection implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    
    @Column(name = "color_name", nullable = false)
    private String colorName;
    
    @Column(name = "color_hax")
    private String colorHax;
    
    @Column(name = "date", nullable = false)
    private String date;
    
    @Column(name = "status", nullable = false)
    private String status;

    public ColorSelection() {
    }

    public ColorSelection(int id, String colorName, String colorHax, String date, String status) {
        this.id = id;
        this.colorName = colorName;
        this.colorHax = colorHax;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorHax() {
        return colorHax;
    }

    public void setColorHax(String colorHax) {
        this.colorHax = colorHax;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "ColorSelection{" + "id=" + id + ", colorName=" + colorName + ", colorHax=" + colorHax + ", date=" + date + ", status=" + status + '}';
    }
}
