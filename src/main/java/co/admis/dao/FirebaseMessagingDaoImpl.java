/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

/**
 *
 * @author admis
 */
public class FirebaseMessagingDaoImpl implements FirebaseMessagingDao{

    @Override
    public String sendPushNotification(String registerToken, Notification notification) {
        try {
            Message message = Message.builder()
                    .setNotification(notification)
                    .setToken(registerToken)
                    .build();
            
            // Send a message to the device corresponding to the provided
            // registration token.
            String r = FirebaseMessaging.getInstance().send(message);
            // Response is a message ID string.
            System.out.println("Successfully sent message: " + r);
            return "success";
        } catch (FirebaseMessagingException ex) {
            return ex.getMessage();
        }
    }
    
}
