/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

/**
 *
 * @author dell
 */
public class InvoiceData {
    private String invoiceIdPrefix;
    private int invoiceId;

    public InvoiceData(String invoiceIdPrefix, int invoiceId) {
        this.invoiceIdPrefix = invoiceIdPrefix;
        this.invoiceId = invoiceId;
    }

    public InvoiceData() {
    }

    public String getInvoiceIdPrefix() {
        return invoiceIdPrefix;
    }

    public void setInvoiceIdPrefix(String invoiceIdPrefix) {
        this.invoiceIdPrefix = invoiceIdPrefix;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "InvoiceData{" + "invoiceIdPrefix=" + invoiceIdPrefix + ", invoiceId=" + invoiceId + '}';
    }
}
