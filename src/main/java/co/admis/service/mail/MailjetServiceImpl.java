/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service.mail;

import co.admis.dao.mail.MailjetDao;
import javax.activation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class MailjetServiceImpl implements MailjetService{

    @Autowired
    MailjetDao mailjetDao;

    @Override
    public boolean sendEmail(String emailId, String subject, String message) {
        return mailjetDao.sendEmail(emailId, subject, message);
    }

    @Override
    public boolean sendEmailWithAttachment(String emailId, String subject, String message, DataSource source, String fileName) {
        return mailjetDao.sendEmailWithAttachment(emailId, subject, message, source, fileName);
    }
    
    
    
}
