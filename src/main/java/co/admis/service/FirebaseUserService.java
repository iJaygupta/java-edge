/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;

/**
 *
 * @author admis
 */
public interface FirebaseUserService {
    UserRecord getUserRecordByUid(String uid);
    UserRecord getUserRecordByEmail(String email);
    UserRecord getUserRecordByNumber(String number);
    UserRecord updateUserRecord(UpdateRequest request);
    boolean removeUserRecord(String uid);
    ListUsersPage getAllUserRecords();
    String getUserUidByIdToken(String idToken, boolean checkRevoked);
    boolean revokeUserSession(String uid);
    UserRecord addUserRecord(CreateRequest request);
}
