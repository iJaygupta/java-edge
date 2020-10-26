/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.pdf.model;

/**
 *
 * @author dell
 */
public class InvoiceRowData {
    private int xLengthRectangle;
    private String text;

    public InvoiceRowData() {
    }

    public InvoiceRowData(int xLengthRectangle, String text) {
        this.xLengthRectangle = xLengthRectangle;
        this.text = text;
    }

    public int getxLengthRectangle() {
        return xLengthRectangle;
    }

    public void setxLengthRectangle(int xLengthRectangle) {
        this.xLengthRectangle = xLengthRectangle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "InvoiceRowData{" + "xLengthRectangle=" + xLengthRectangle + ", text=" + text + '}';
    }
}
