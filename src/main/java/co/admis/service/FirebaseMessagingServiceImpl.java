/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import com.google.firebase.messaging.Notification;
import co.admis.dao.FirebaseMessagingDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admis
 */
public class FirebaseMessagingServiceImpl implements FirebaseMessagingService{

    @Autowired
    FirebaseMessagingDao firebaseMessagingDao;
    @Override
    public String sendPushNotification(String registerToken, Notification notification) {
       return firebaseMessagingDao.sendPushNotification(registerToken,notification);
    }
    
}
