/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.CustomerDao;
import co.admis.model.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerDao customerDao;
    
    @Override
    public Customer addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customer getCustomerById(String adminUsername, int id) {
        return customerDao.getCustomerById(adminUsername, id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }

    @Override
    public Customer getCustomerByEmail(String adminUsername, String email) {
        return customerDao.getCustomerByEmail(adminUsername, email);
    }

    @Override
    public Customer getCustomerByNumber(String number) {
        return customerDao.getCustomerByNumber(number);
    }

    @Override
    public Customer getCustomerByNumber(String adminUsername, String number) {
        return customerDao.getCustomerByNumber(adminUsername, number);
    }

    @Override
    public Customer getCustomerByRfid(String tagId) {
        return customerDao.getCustomerByRfid(tagId);
    }

    @Override
    public Customer getCustomerByRfid(String adminUsername, String tagId) {
        return customerDao.getCustomerByRfid(adminUsername, tagId);
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        return customerDao.removeCustomer(customer);
    }

    @Override
    public List<Customer> getCustomerByAdmin(String adminUsername) {
        return customerDao.getCustomerByAdmin(adminUsername);
    }

    @Override
    public List<Customer> getCustomerByAdminAndStatus(String adminUsername, String status) {
        return customerDao.getCustomerByAdminAndStatus(adminUsername, status);
    }
    
}
