/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao.mail;

import javax.activation.DataSource;

/**
 *
 * @author dell
 */
public interface MailjetDao {
    public boolean sendEmail(String emailId, String subject, String message);
    public boolean sendEmailWithAttachment(String emailId, String subject, String message,DataSource source, String fileName);
}
