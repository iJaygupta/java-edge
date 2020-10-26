/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.startup;

import static co.admis.config.ServerConfiguration.AWS_KEY;
import static co.admis.config.ServerConfiguration.AWS_VALUE;
import static co.admis.config.ServerConfiguration.MASTER_CONTACT_NUMBER;
import static co.admis.config.ServerConfiguration.MASTER_IP_ADDRESS;
import static co.admis.config.ServerConfiguration.MASTER_PUBLIC_KEY_ADDRESS;
import static co.admis.config.ServerConfiguration.SENDER_ID;
import static co.admis.config.ServerConfiguration.MASTER_USERNAME;
import static co.admis.config.ServerConfiguration.MSG91_AUTH_KEY;
import static co.admis.config.ServerConfiguration.ROUTE;
import static co.admis.config.ServerConfiguration.S3_BUCKET;
import co.admis.controller.HibernateUtil;
import co.admis.dao.AmazonS3DaoImpl;
import co.admis.dao.SecurityDaoImpl;
import co.admis.model.Master;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author dell
 */
@WebListener
public class StartupService  implements ServletContextListener {
    
   @Override
   public void contextInitialized(ServletContextEvent contextEvent) {
       System.out.println("Started Service Multiechannel");    
    try{
            // EXECUTE YOUR CODE HERE 
            new Thread() {
                @Override
                public void run() {
                    //check Master Account
                    System.out.println("Multiechannel1");
                    Master m = getMaster();
                    if(m==null){
                        //Create Security Key
                        KeyPair keyPair = generateKeyPair();
                        updateS3Object(S3_BUCKET, "master/security key/public.txt", savePublicKey(keyPair.getPublic()), "public", "txt");
                        updateS3Object(S3_BUCKET, "master/security key/private.txt", savePrivateKey(keyPair.getPrivate()), "private", "txt");
                        String password = generateRendomStrongPassword(12);
                        //Create Master Account
                        m = new Master(MASTER_USERNAME, MASTER_CONTACT_NUMBER, MASTER_IP_ADDRESS, "active", encrypt(password, keyPair.getPublic()), 0,null);
                        if(addMaster(m)!=null){
                            //Send Password On Number
                            sendMessage(m.getNumber(), password);
                        }
                    }else{
                        if(m.getPassword()==null || m.getPassword().length<2){
                            //Create Security Key And Password
                            String password = generateRendomStrongPassword(12);
                            //Create Master Account 
                            m.setPassword(encrypt(password, loadPublicKey(readTextFileFromS3(S3_BUCKET, MASTER_PUBLIC_KEY_ADDRESS))));
                            if(updateMaster(m)!=null){
                                //Send Password On Number
                                sendMessage(m.getNumber(), password);
                            }
                        }
                    }
                } 
            }.start();
    }catch(Exception ex ){
        System.out.println(ex);
    }
        
   }

   @Override
   public void contextDestroyed(ServletContextEvent contextEvent) {
       System.out.println("Closed");
   }
   
   public Master getMaster() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Master master1");
            q.setMaxResults(1);
            Master data = (Master)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            System.out.println(data);
            return data; 
        }catch(Exception e){
            System.out.println(e);
            session.close();
            return null;
        }
    }
   
    public Master addMaster(Master master) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.save(master);
            session.getTransaction().commit();
            session.close();
            return master;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    public Master updateMaster(Master master) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.update(master);
            session.getTransaction().commit();
            session.close();
            return master;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    public KeyPair generateKeyPair() {
        try {
            //Creating KeyPair generator object
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            //Initializing the key pair generator
            keyPairGen.initialize(2048);
            //Generate the pair of keys
            KeyPair pair = keyPairGen.generateKeyPair();
            return pair;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateS3Object(String bucketName, String key, String data, String fileName, String fileFormat) {
        AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(
				AWS_KEY, 
				AWS_VALUE));
        File file   = createSampleFile(fileName, fileFormat, data);
        s3.putObject(new PutObjectRequest(bucketName, key, file));
        file.delete();
        return 1;
    }
    
    public File createSampleFile(String fileName, String fileFormat, String data) {
        File file = null;
        try {
            file = File.createTempFile(fileName, fileFormat);
            file.deleteOnExit();
            
            Writer writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(data);
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          return file;
    }
    
    public String savePrivateKey(PrivateKey priv) {
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

    public String savePublicKey(PublicKey publ) {
        try {
            KeyFactory fact = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec spec = fact.getKeySpec(publ,
                    X509EncodedKeySpec.class);
            return  Base64.getEncoder().encodeToString(spec.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String generateRendomStrongPassword(int length) {
        SecureRandom random = new SecureRandom();
        final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
        final String NUMERIC = "0123456789";
        final String SPECIAL_CHARS = "@#$";
        
        String result = "";
        String dic = ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC;
        for (int i = 0; i < length; i++) {
        int index = random.nextInt(dic.length());
        result += dic.charAt(index);
        }
        return result;
    }
    
    public byte[] encrypt(String text, PublicKey publicKey) {
        try {
            //Creating a Cipher object
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            
            //Initializing a Cipher object
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            
            //Add data to the cipher
            byte[] input = text.getBytes();
            cipher.update(input);
            
            //encrypting the data
            byte[] cipherText = cipher.doFinal();
            return cipherText;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    public boolean sendMessage(String number, String message) {
        //Your authentication key
            String authkey = MSG91_AUTH_KEY;
            //Multiple mobiles numbers separated by comma
            String mobiles = number;
            //Sender ID,While using route4 sender id should be 6 characters long.
            String senderId = SENDER_ID;
            //Your message to send, Add URL encoding here.
            String text = message;
            //define route
            String route= ROUTE;

            //Prepare Url
            URLConnection myURLConnection=null;
            URL myURL=null;
            BufferedReader reader=null;

            //encoding message
            String encoded_message=URLEncoder.encode(text);

            //Send SMS API
            String mainUrl="http://api.msg91.com/api/sendhttp.php?";

            //Prepare parameter string
            StringBuilder sbPostData= new StringBuilder(mainUrl);
            sbPostData.append("authkey="+authkey);
            sbPostData.append("&mobiles="+mobiles);
            sbPostData.append("&message="+encoded_message);
            sbPostData.append("&route="+route);
            sbPostData.append("&sender="+senderId);

            //final string
            mainUrl = sbPostData.toString();
            try
            {
                //prepare connection
                myURL = new URL(mainUrl);
                myURLConnection = myURL.openConnection();
                myURLConnection.connect();
                reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                //reading response
                String response;
                while ((response = reader.readLine()) != null)
                //print response

                //finally close connection
                reader.close();
            }
            catch (Exception e)
            {
                    System.out.println(e);
                    return false;
            }
        
        return  true;	
    }
    
    public PublicKey loadPublicKey(String stored) {
        try {
            byte[] data = Base64.getDecoder().decode(stored);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return fact.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public String readTextFileFromS3(String bucketName, String key) {
            AmazonS3 s3client = new AmazonS3Client(new BasicAWSCredentials(
				AWS_KEY, 
				AWS_VALUE));
            // upload file to folder and set it to public
            String fileName = key;
            S3Object o = s3client.getObject(bucketName, fileName);
            S3ObjectInputStream s3is = o.getObjectContent();
            String data = getStringFromInputStream(s3is);
        try {
            s3is.close();
        } catch (IOException ex) {
            Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            return data;
    }
    
     public String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
    }
}
