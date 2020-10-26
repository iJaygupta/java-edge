/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.Customer;
import java.util.List;

/**
 *
 * @author dell
 */
public interface CustomerService {
    // Customer
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer getCustomerById(int id);
    public Customer getCustomerById(String adminUsername, int id);
    public Customer getCustomerByEmail(String email);
    public Customer getCustomerByEmail(String adminUsername, String email);
    public Customer getCustomerByNumber(String number);
    public Customer getCustomerByNumber(String adminUsername, String number);
    public Customer getCustomerByRfid(String tagId);
    public Customer getCustomerByRfid(String adminUsername, String tagId);
    public boolean removeCustomer(Customer customer);
    public List<Customer> getCustomerByAdmin(String adminUsername);
    public List<Customer> getCustomerByAdminAndStatus(String adminUsername, String status);
}
