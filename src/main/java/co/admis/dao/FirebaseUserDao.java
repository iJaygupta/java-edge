/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;

/**
 *
 * @author admis
 */
public interface FirebaseUserDao {
    UserRecord getUserRecordByUid(String uid);
    UserRecord getUserRecordByEmail(String email);
    UserRecord getUserRecordByNumber(String number);
    UserRecord updateUserRecord(UserRecord.UpdateRequest request);
    boolean removeUserRecord(String uid);
    ListUsersPage getAllUserRecords();
    String getUserUidByIdToken(String idToken, boolean checkRevoked);
    boolean revokeUserSession(String uid);
    UserRecord addUserRecord(UserRecord.CreateRequest request);
}
