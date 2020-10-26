/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.CcAvenueDao;
import co.admis.model.CcavenueTransactionRecord;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class CcAvenueServiceImpl implements CcAvenueService{
    @Autowired
    CcAvenueDao ccAvenueDao;
    
    @Override
    public CcavenueTransactionRecord addCcAvenueTransaction(CcavenueTransactionRecord transaction) {
        return ccAvenueDao.addCcAvenueTransaction(transaction);
    }

    @Override
    public CcavenueTransactionRecord updateCcAvenueTransaction(CcavenueTransactionRecord transaction) {
        return ccAvenueDao.updateCcAvenueTransaction(transaction);
    }

    @Override
    public boolean removeCcAvenueTransaction(CcavenueTransactionRecord transaction) {
        return ccAvenueDao.removeCcAvenueTransaction(transaction);
    }

    @Override
    public CcavenueTransactionRecord getCcAvenueTransactionByOrderId(String orderId) {
        return ccAvenueDao.getCcAvenueTransactionByOrderId(orderId);
    }

    @Override
    public List<CcavenueTransactionRecord> getListOfCcAvenueTransactionByPatientId(int patientId) {
        return ccAvenueDao.getListOfCcAvenueTransactionByPatientId(patientId);
    }

    @Override
    public List<CcavenueTransactionRecord> getAllCcAvenueTransactions() {
        return ccAvenueDao.getAllCcAvenueTransactions();
    }
    
}
