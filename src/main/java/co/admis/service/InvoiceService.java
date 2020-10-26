/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.Invoice;
import java.util.List;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public interface InvoiceService {
//  Invoice
    public Invoice addInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public boolean removeInvoice(Invoice invoice);
    public Invoice getInvoiceById(int id);
    public Invoice getInvoiceForAdmin(int id, String adminUsername);
    public Invoice getInvoiceForUser(int id, int userId);
    public List<Invoice> getInvoiceForAdminByCustomerAndFacilityId(int customerId, int facilityId, String adminUsername);
    public List<Invoice> getListofInvoiceByCustomerId(int customerId, String adminUsername);
    public List<Invoice> getListofInvoiceForAdmin(String adminUsername);
    public List<Invoice> getListofInvoiceForFacility(String adminUsername, int facilityId);
    public Invoice getLastInvoiceIdByAdminUsername(String adminUsername);
    public Invoice getLastInvoiceIdByFacility(String adminUsername, int facilityId);
    public boolean removeInvoiceByCustomerId(int customerId, String adminUsername);  
}
