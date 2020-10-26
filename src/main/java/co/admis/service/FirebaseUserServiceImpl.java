/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import co.admis.dao.FirebaseUserDao;
import com.google.firebase.auth.UserRecord.CreateRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admis
 */
public class FirebaseUserServiceImpl implements FirebaseUserService{

    @Autowired
    FirebaseUserDao firebaseUserDao;
    
    @Override
    public UserRecord getUserRecordByUid(String uid) {
        return firebaseUserDao.getUserRecordByUid(uid);
    }

    @Override
    public UserRecord getUserRecordByEmail(String email) {
        return firebaseUserDao.getUserRecordByEmail(email);
    }

    @Override
    public UserRecord getUserRecordByNumber(String number) {
        return firebaseUserDao.getUserRecordByNumber(number);
    }

    @Override
    public UserRecord updateUserRecord(UserRecord.UpdateRequest request) {
        return firebaseUserDao.updateUserRecord(request);
    }

    @Override
    public boolean removeUserRecord(String uid) {
        return firebaseUserDao.removeUserRecord(uid);
    }

    @Override
    public ListUsersPage getAllUserRecords() {
        return firebaseUserDao.getAllUserRecords();
    }

    @Override
    public String getUserUidByIdToken(String idToken, boolean checkRevoked) {
        return firebaseUserDao.getUserUidByIdToken(idToken, checkRevoked);
    }

    @Override
    public boolean revokeUserSession(String uid) {
        return firebaseUserDao.revokeUserSession(uid);
    }

    @Override
    public UserRecord addUserRecord(CreateRequest request) {
        return firebaseUserDao.addUserRecord(request);
    }
    
}
