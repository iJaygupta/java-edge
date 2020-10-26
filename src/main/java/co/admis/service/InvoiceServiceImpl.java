/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.InvoiceDao;
import co.admis.model.Invoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    InvoiceDao invoiceDao;;
    
    @Override
    public Invoice addInvoice(Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return invoiceDao.updateInvoice(invoice);
    }

    @Override
    public boolean removeInvoice(Invoice invoice) {
        return invoiceDao.removeInvoice(invoice);
    }

    @Override
    public Invoice getInvoiceById(int id) {
        return invoiceDao.getInvoiceById(id);
    }

    @Override
    public Invoice getInvoiceForAdmin(int id, String adminUsername) {
        return invoiceDao.getInvoiceForAdmin(id, adminUsername);
    }

    @Override
    public Invoice getInvoiceForUser(int id, int userId) {
        return invoiceDao.getInvoiceForUser(id, userId);
    }

    @Override
    public List<Invoice> getListofInvoiceByCustomerId(int patientId, String adminUsername) {
        return invoiceDao.getListofInvoiceByCustomerId(patientId, adminUsername);
    }

    @Override
    public List<Invoice> getListofInvoiceForAdmin(String adminUsername) {
        return invoiceDao.getListofInvoiceForAdmin(adminUsername);
    }

    @Override
    public Invoice getLastInvoiceIdByAdminUsername(String adminUsername) {
        return invoiceDao.getLastInvoiceIdByAdminUsername(adminUsername);
    }

    @Override
    public boolean removeInvoiceByCustomerId(int patientId, String adminUsername) {
        return invoiceDao.removeInvoiceByCustomerId(patientId, adminUsername);
    }

    @Override
    public List<Invoice> getInvoiceForAdminByCustomerAndFacilityId(int customerId, int facilityId, String adminUsername) {
        return invoiceDao.getInvoiceForAdminByCustomerAndFacilityId(customerId, facilityId, adminUsername);
    }

    @Override
    public Invoice getLastInvoiceIdByFacility(String adminUsername, int facilityId) {
        return invoiceDao.getLastInvoiceIdByFacility(adminUsername, facilityId);
    }

    @Override
    public List<Invoice> getListofInvoiceForFacility(String adminUsername, int facilityId) {
        return invoiceDao.getListofInvoiceForFacility(adminUsername, facilityId);
    }

}
