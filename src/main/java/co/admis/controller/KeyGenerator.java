/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.dao.SecurityDaoImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admis
 */
public class KeyGenerator {

     public static void main(String []args) throws ParseException, NoSuchAlgorithmException, IOException{
        //Creating KeyPair generator object
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            //Initializing the key pair generator
            keyPairGen.initialize(2048);
            //Generate the pair of keys
            KeyPair pair = keyPairGen.generateKeyPair();
            File file1 = File.createTempFile("private", ".txt");
            System.out.println(file1.getAbsolutePath());
            File file2 = File.createTempFile("public", ".txt");
            FileWriter fr = new FileWriter(file1, true);
            fr.write(savePrivateKey(pair.getPrivate()));
            FileWriter fr1 = new FileWriter(file2, true);
            fr1.write(savePublicKey(pair.getPublic()));
            fr.close();
            fr1.close();
     }

    public static PrivateKey loadPrivateKey(String key64) {
        try {
            byte[] clear = Base64.getDecoder().decode(key64);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey priv = fact.generatePrivate(keySpec);
            Arrays.fill(clear, (byte) 0);
            return priv;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static PublicKey loadPublicKey(String stored) {
        try {
            byte[] data = Base64.getDecoder().decode(stored);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return fact.generatePublic(spec);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String savePrivateKey(PrivateKey priv) {
        try {
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec spec = fact.getKeySpec(priv,
                    PKCS8EncodedKeySpec.class);
            byte[] packed = spec.getEncoded();
            String key64 =  Base64.getEncoder().encodeToString(packed);
            
            Arrays.fill(packed, (byte) 0);
            return key64;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String savePublicKey(PublicKey publ) {
        try {
            KeyFactory fact = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec spec = fact.getKeySpec(publ,
                    X509EncodedKeySpec.class);
            return  Base64.getEncoder().encodeToString(spec.getEncoded());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
