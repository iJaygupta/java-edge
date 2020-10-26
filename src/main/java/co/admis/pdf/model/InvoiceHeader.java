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
public class InvoiceHeader {
    private String base64logo;
    private int base64logoXposition;
    private int base64logoYposition;
    private String title;
    private int titleXposition;
    private int titleYposition;
    private String subTitle;
    private int subTitleXposition;
    private int subTitleYposition;
    private String whatsapp;
    private int whatsappXposition;
    private int whatsappYposition;
    private String number;
    private int numberXposition;
    private int numberYposition;
    private String email;
    private int emailXposition;
    private int emailYposition;
    private String gst;
    private int gstXposition;
    private int gstYposition;

    public InvoiceHeader() {
    }

    public int getBase64logoXposition() {
        return base64logoXposition;
    }

    public void setBase64logoXposition(int base64logoXposition) {
        this.base64logoXposition = base64logoXposition;
    }

    public int getBase64logoYposition() {
        return base64logoYposition;
    }

    public void setBase64logoYposition(int base64logoYposition) {
        this.base64logoYposition = base64logoYposition;
    }

    public int getTitleXposition() {
        return titleXposition;
    }

    public void setTitleXposition(int titleXposition) {
        this.titleXposition = titleXposition;
    }

    public int getTitleYposition() {
        return titleYposition;
    }

    public void setTitleYposition(int titleYposition) {
        this.titleYposition = titleYposition;
    }

    public int getSubTitleXposition() {
        return subTitleXposition;
    }

    public void setSubTitleXposition(int subTitleXposition) {
        this.subTitleXposition = subTitleXposition;
    }

    public int getSubTitleYposition() {
        return subTitleYposition;
    }

    public void setSubTitleYposition(int subTitleYposition) {
        this.subTitleYposition = subTitleYposition;
    }

    public int getWhatsappXposition() {
        return whatsappXposition;
    }

    public void setWhatsappXposition(int whatsappXposition) {
        this.whatsappXposition = whatsappXposition;
    }

    public int getWhatsappYposition() {
        return whatsappYposition;
    }

    public void setWhatsappYposition(int whatsappYposition) {
        this.whatsappYposition = whatsappYposition;
    }

    public int getNumberXposition() {
        return numberXposition;
    }

    public void setNumberXposition(int numberXposition) {
        this.numberXposition = numberXposition;
    }

    public int getNumberYposition() {
        return numberYposition;
    }

    public void setNumberYposition(int numberYposition) {
        this.numberYposition = numberYposition;
    }

    public int getEmailXposition() {
        return emailXposition;
    }

    public void setEmailXposition(int emailXposition) {
        this.emailXposition = emailXposition;
    }

    public int getEmailYposition() {
        return emailYposition;
    }

    public void setEmailYposition(int emailYposition) {
        this.emailYposition = emailYposition;
    }

    public int getGstXposition() {
        return gstXposition;
    }

    public void setGstXposition(int gstXposition) {
        this.gstXposition = gstXposition;
    }

    public int getGstYposition() {
        return gstYposition;
    }

    public void setGstYposition(int gstYposition) {
        this.gstYposition = gstYposition;
    }

    
    public String getBase64logo() {
        return base64logo;
    }

    public void setBase64logo(String base64logo) {
        this.base64logo = base64logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }    

    @Override
    public String toString() {
        return "InvoiceHeader{" + "base64logo=" + base64logo + ", base64logoXposition=" + base64logoXposition + ", base64logoYposition=" + base64logoYposition + ", title=" + title + ", titleXposition=" + titleXposition + ", titleYposition=" + titleYposition + ", subTitle=" + subTitle + ", subTitleXposition=" + subTitleXposition + ", subTitleYposition=" + subTitleYposition + ", whatsapp=" + whatsapp + ", whatsappXposition=" + whatsappXposition + ", whatsappYposition=" + whatsappYposition + ", number=" + number + ", numberXposition=" + numberXposition + ", numberYposition=" + numberYposition + ", email=" + email + ", emailXposition=" + emailXposition + ", emailYposition=" + emailYposition + ", gst=" + gst + ", gstXposition=" + gstXposition + ", gstYposition=" + gstYposition + '}';
    }
}
