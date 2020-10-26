/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public interface SecurityDao {
    public boolean isImageSafe(File file);
    public byte[] encrypt(String text, PublicKey publicKey);
    public String decrypt(byte[] text, PrivateKey privateKey);
    public PublicKey getPublicKeyByAdminUsername(String adminUsername);
    public PrivateKey getPrivateKeyByAdminUsername(String adminUsername);
    public PublicKey getPublicKeyForMaster();
    public PrivateKey getPrivateKeyForMaster();
    public KeyPair generateKeyPair();
    public PrivateKey loadPrivateKey(String key64);
    public PublicKey loadPublicKey(String stored);
    public String savePrivateKey(PrivateKey priv);
    public String savePublicKey(PublicKey publ);
    
    //JWT
    public String createJWT(String id, String issuer, String subject, long ttlMillis);
    public String parseJWT(String jwt);
}
