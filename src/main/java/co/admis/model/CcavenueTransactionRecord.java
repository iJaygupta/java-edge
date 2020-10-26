/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "\"ccavenue_transaction_record\"")
public class CcavenueTransactionRecord implements Serializable {
    @Id
    @Column(name = "order_id", nullable = false, unique = true)
    private String order_id;
    
    @Column(name = "tracking_id")
    private String tracking_id;
    
    @Column(name = "bank_ref_no")
    private String bank_ref_no;
    
    @Column(name = "failure_message")
    private String failure_message;
    
    @Column(name = "payment_mode")
    private String payment_mode;
    
    @Column(name = "card_name")
    private String card_name;
    
    @Column(name = "status_code")
    private String status_code;
    
    @Column(name = "status_message")
    private String status_message;
    
    @Column(name = "currency")
    private String currency;
    
    @Column(name = "amount")
    private String amount;
    
    @Column(name = "order_status")
    private String order_status;
    
    @Column(name = "trans_date", updatable = false)
    private String trans_date;
    
    @Column(name="patient_id", nullable=false, updatable = false)
    private int patientId;
    
    @Column(name = "admin_username", nullable = false, updatable = false)
    private String adminUsername;

    public CcavenueTransactionRecord() {
    }

    public CcavenueTransactionRecord(String order_id, String tracking_id, String bank_ref_no, String failure_message, String payment_mode, String card_name, String status_code, String status_message, String currency, String amount, String order_status, String trans_date, int patientId, String adminUsername) {
        this.order_id = order_id;
        this.tracking_id = tracking_id;
        this.bank_ref_no = bank_ref_no;
        this.failure_message = failure_message;
        this.payment_mode = payment_mode;
        this.card_name = card_name;
        this.status_code = status_code;
        this.status_message = status_message;
        this.currency = currency;
        this.amount = amount;
        this.order_status = order_status;
        this.trans_date = trans_date;
        this.patientId = patientId;
        this.adminUsername = adminUsername;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    public String getBank_ref_no() {
        return bank_ref_no;
    }

    public void setBank_ref_no(String bank_ref_no) {
        this.bank_ref_no = bank_ref_no;
    }

    public String getFailure_message() {
        return failure_message;
    }

    public void setFailure_message(String failure_message) {
        this.failure_message = failure_message;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(String trans_date) {
        this.trans_date = trans_date;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    @Override
    public String toString() {
        return "CcavenueTransactionRecord{" + "order_id=" + order_id + ", tracking_id=" + tracking_id + ", bank_ref_no=" + bank_ref_no + ", failure_message=" + failure_message + ", payment_mode=" + payment_mode + ", card_name=" + card_name + ", status_code=" + status_code + ", status_message=" + status_message + ", currency=" + currency + ", amount=" + amount + ", order_status=" + order_status + ", trans_date=" + trans_date + ", patientId=" + patientId + ", adminUsername=" + adminUsername + '}';
    }
}
