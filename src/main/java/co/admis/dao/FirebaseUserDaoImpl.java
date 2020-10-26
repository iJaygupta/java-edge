/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admis
 */
public class FirebaseUserDaoImpl implements FirebaseUserDao{

    @Override
    public UserRecord getUserRecordByUid(String uid) {
        try {
            return FirebaseAuth.getInstance().getUser(uid);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public UserRecord getUserRecordByEmail(String email) {
        try {
            return FirebaseAuth.getInstance().getUserByEmail(email);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public UserRecord getUserRecordByNumber(String number) {
        try {
            return FirebaseAuth.getInstance().getUserByPhoneNumber(number);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public UserRecord updateUserRecord(UserRecord.UpdateRequest request) {
        try {
            return FirebaseAuth.getInstance().updateUser(request);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException(ex.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public boolean removeUserRecord(String uid) {
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
            return true;
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ListUsersPage getAllUserRecords() {
        try {
            ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
            while (page != null) {
                for (ExportedUserRecord user : page.getValues()) {
                    System.out.println("User: " + user.getUid());
                }
                page = page.getNextPage();
            }
            
            // Iterate through all users. This will still retrieve users in batches,
            // buffering no more than 1000 users in memory at a time.
            return FirebaseAuth.getInstance().listUsers(null);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getUserUidByIdToken(String idToken, boolean checkRevoked) {
        try {
            return FirebaseAuth.getInstance().verifyIdToken(idToken).getUid();
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean revokeUserSession(String uid) {
        try {
            FirebaseAuth.getInstance().revokeRefreshTokens(uid);
            UserRecord user = FirebaseAuth.getInstance().getUser(uid);
            // Convert to seconds as the auth_time in the token claims is in seconds too.
            long revocationSecond = user.getTokensValidAfterTimestamp() / 1000;
            System.out.println("Tokens revoked at: " + revocationSecond);
            return true;
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public UserRecord addUserRecord(CreateRequest request) {
        try {
            return FirebaseAuth.getInstance().createUser(request);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(FirebaseUserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException(ex.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
