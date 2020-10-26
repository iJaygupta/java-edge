/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.SmsDao;
import co.admis.model.AdminOTP;
import co.admis.model.MessageBody;
import co.admis.model.UserOTP;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class SmsServiceImpl implements SmsService{

    @Autowired
    SmsDao smsDao;
    
    @Override
    public MessageBody addMessage(MessageBody messageBody) {
        return smsDao.addMessage(messageBody);
    }

    @Override
    public MessageBody updateMessage(MessageBody messageBody) {
        return smsDao.updateMessage(messageBody);
    }

    @Override
    public boolean removeMessage(MessageBody messageBody) {
        return smsDao.removeMessage(messageBody);
    }

    @Override
    public MessageBody getMessageByNumber(String number) {
        return smsDao.getMessageByNumber(number);
    }

    @Override
    public MessageBody getMessageByNumberForAdmin(String number, String adminusername) {
        return smsDao.getMessageByNumberForAdmin(number, adminusername);
    }

    @Override
    public List<MessageBody> getListofMessagesForAdmin(String adminUsername) {
        return smsDao.getListofMessagesForAdmin(adminUsername);
    }

    @Override
    public boolean sendMessage(String number, String message){
        return smsDao.sendMessage(number,message);
    }

    @Override
    public AdminOTP addAdminOTP(AdminOTP adminOTP) {
        return smsDao.addAdminOTP(adminOTP);
    }

    @Override
    public AdminOTP updateAdminOTP(AdminOTP adminOTP) {
        return smsDao.updateAdminOTP(adminOTP);
    }

    @Override
    public boolean removeAdminOTP(AdminOTP adminOTP) {
        return smsDao.removeAdminOTP(adminOTP);
    }

    @Override
    public AdminOTP getAdminOTP(String number) {
        return smsDao.getAdminOTP(number);
    }

    @Override
    public AdminOTP generateAdminOTP(String number) {
        return smsDao.generateAdminOTP(number);
    }

    @Override
    public UserOTP addUserOTP(UserOTP userOTP) {
        return smsDao.addUserOTP(userOTP);
    }

    @Override
    public UserOTP updateUserOTP(UserOTP userOTP) {
        return smsDao.updateUserOTP(userOTP);
    }

    @Override
    public boolean removeUserOTP(UserOTP userOTP) {
        return smsDao.removeUserOTP(userOTP);
    }

    @Override
    public UserOTP getUserOTP(String number) {
        return smsDao.getUserOTP(number);
    }

    @Override
    public UserOTP generateUserOTP(String number) {
        return smsDao.generateUserOTP(number);
    }

    @Override
    public boolean compareTimeForMinutes(String time, int minutes) {
        return smsDao.compareTimeForMinutes(time, minutes);
    }
    
    
}
