/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.SecurityDao;
import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */

public class SecurityServiceImpl implements SecurityService{

@Autowired
SecurityDao securityDao;

    @Override
    public boolean isImageSafe(File file) {
        return securityDao.isImageSafe(file);
    }

    @Override
    public byte[] encrypt(String text, PublicKey publicKey) {
        return securityDao.encrypt(text, publicKey);
    }

    @Override
    public String decrypt(byte[] text, PrivateKey privateKey) {
        return securityDao.decrypt(text, privateKey);
    }

    @Override
    public KeyPair generateKeyPair() {
        return securityDao.generateKeyPair();
    }

    @Override
    public PrivateKey loadPrivateKey(String key64) {
        return securityDao.loadPrivateKey(key64);
    }

    @Override
    public PublicKey loadPublicKey(String stored) {
        return securityDao.loadPublicKey(stored);
    }

    @Override
    public String savePrivateKey(PrivateKey priv) {
        return securityDao.savePrivateKey(priv);
    }

    @Override
    public String savePublicKey(PublicKey publ) {
        return securityDao.savePublicKey(publ);
    }

    @Override
    public PublicKey getPublicKeyByAdminUsername(String adminUsername) {
        return securityDao.getPublicKeyByAdminUsername(adminUsername);
    }

    @Override
    public PrivateKey getPrivateKeyByAdminUsername(String adminUsername) {
        return securityDao.getPrivateKeyByAdminUsername(adminUsername);
    }

    @Override
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String parseJWT(String jwt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PublicKey getPublicKeyForMaster() {
        return securityDao.getPublicKeyForMaster();
    }

    @Override
    public PrivateKey getPrivateKeyForMaster() {
        return securityDao.getPrivateKeyForMaster();
    }
    
}
