/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.CcavenueTransactionRecord;
import java.util.List;

/**
 *
 * @author dell
 */
public interface CcAvenueDao {
    //    transaction
    public CcavenueTransactionRecord addCcAvenueTransaction(CcavenueTransactionRecord transaction);
    public CcavenueTransactionRecord updateCcAvenueTransaction(CcavenueTransactionRecord transaction);
    public boolean removeCcAvenueTransaction(CcavenueTransactionRecord transaction);
    public CcavenueTransactionRecord getCcAvenueTransactionByOrderId(String orderId);
    public List<CcavenueTransactionRecord> getListOfCcAvenueTransactionByPatientId(int patientId);
    public List<CcavenueTransactionRecord> getAllCcAvenueTransactions();
}
