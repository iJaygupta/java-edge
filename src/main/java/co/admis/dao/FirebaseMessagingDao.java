/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import com.google.firebase.messaging.Notification;

/**
 *
 * @author admis
 */
public interface FirebaseMessagingDao {
    String sendPushNotification(String registerToken, Notification notification);
}
