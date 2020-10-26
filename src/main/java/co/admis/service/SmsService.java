/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.AdminOTP;
import co.admis.model.MessageBody;
import co.admis.model.UserOTP;
import java.util.List;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public interface SmsService {
    public MessageBody addMessage(MessageBody messageBody); 
    public MessageBody updateMessage(MessageBody messageBody); 
    public boolean removeMessage(MessageBody messageBody); 
    public MessageBody getMessageByNumber(String number);
    public MessageBody getMessageByNumberForAdmin(String number, String adminusername);
    public List<MessageBody> getListofMessagesForAdmin(String adminUsername);
    public boolean sendMessage(String number, String message);
    public AdminOTP addAdminOTP(AdminOTP adminOTP);
    public AdminOTP updateAdminOTP(AdminOTP adminOTP);
    public boolean removeAdminOTP(AdminOTP adminOTP);
    public AdminOTP getAdminOTP(String number);
    public AdminOTP generateAdminOTP(String number);
    public UserOTP addUserOTP(UserOTP userOTP);
    public UserOTP updateUserOTP(UserOTP userOTP);
    public boolean removeUserOTP(UserOTP userOTP);
    public UserOTP getUserOTP(String number);
    public UserOTP generateUserOTP(String number);
    public boolean compareTimeForMinutes(String time, int minutes);
}
