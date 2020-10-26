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
public class BillingDetails {
    private String companyName;
    private String companyAddress;
    private String companyCity;
    private String companyState;
    private String companyCountry;
    private String companyPin;
    private String customerName;
    private String customerId;
    private String customerAddress;
    private String customerCity;
    private String customerState;
    private String customerCountry;
    private String customerPin;
    private String invoiceId;
    private String issuedDate;
    private String paymentDueDate;
    private String paymentStatus;

    public BillingDetails() {
    }

    public BillingDetails(String companyName, String companyAddress, String companyCity, String companyState, String companyCountry, String companyPin, String customerName, String customerId, String customerAddress, String customerCity, String customerState, String customerCountry, String customerPin, String invoiceId, String issuedDate, String paymentDueDate, String paymentStatus) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyCity = companyCity;
        this.companyState = companyState;
        this.companyCountry = companyCountry;
        this.companyPin = companyPin;
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerCountry = customerCountry;
        this.customerPin = customerPin;
        this.invoiceId = invoiceId;
        this.issuedDate = issuedDate;
        this.paymentDueDate = paymentDueDate;
        this.paymentStatus = paymentStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyPin() {
        return companyPin;
    }

    public void setCompanyPin(String companyPin) {
        this.companyPin = companyPin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerPin() {
        return customerPin;
    }

    public void setCustomerPin(String customerPin) {
        this.customerPin = customerPin;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "BillingDetails{" + "companyName=" + companyName + ", companyAddress=" + companyAddress + ", companyCity=" + companyCity + ", companyState=" + companyState + ", companyCountry=" + companyCountry + ", companyPin=" + companyPin + ", customerName=" + customerName + ", customerId=" + customerId + ", customerAddress=" + customerAddress + ", customerCity=" + customerCity + ", customerState=" + customerState + ", customerCountry=" + customerCountry + ", customerPin=" + customerPin + ", invoiceId=" + invoiceId + ", issuedDate=" + issuedDate + ", paymentDueDate=" + paymentDueDate + ", paymentStatus=" + paymentStatus + '}';
    }

   
}
