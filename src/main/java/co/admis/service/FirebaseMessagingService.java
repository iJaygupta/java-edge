/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import com.google.firebase.messaging.Notification;

/**
 *
 * @author admis
 */
public interface FirebaseMessagingService {
    String sendPushNotification(String registerToken, Notification notification);
}
