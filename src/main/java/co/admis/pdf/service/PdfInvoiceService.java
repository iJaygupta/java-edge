/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.pdf.service;

import co.admis.pdf.model.BillingDetails;
import co.admis.pdf.model.InvoiceHeader;
import co.admis.pdf.model.InvoiceRowData;
import java.util.List;

/**
 *
 * @author dell
 */
public interface PdfInvoiceService {
    public boolean addInvoiceHeader1(InvoiceHeader invoiceHeader);
    
    public boolean addBillingDetails1(BillingDetails billingDetails);
    
    public boolean addInvoiceRowHeader(List<InvoiceRowData> list);
    
    public boolean addInvoiceFooter1();
}
