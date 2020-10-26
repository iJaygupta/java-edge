/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.controller;

import co.admis.dao.AmazonS3DaoImpl;
import co.admis.model.Admin;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class Test {
    
    public static void main(String agrs[]) throws JsonProcessingException, IOException, ParseException{
//        MessageBody messageBody = new MessageBody("+919791492742", "sample %0a yes");
//        //Your authentication key
//             String authkey = MSG91_AUTH_KEY;
//            //Multiple mobiles numbers separated by comma
//            String mobiles = messageBody.getNumber();
//            //Sender ID,While using route4 sender id should be 6 characters long.
//            String senderId = MASTER_SENDER_ID;
//            //Your message to send, Add URL encoding here.
//            String text = messageBody.getMessage();
//            //define route
//            String route= ROUTE;
//
//            //Prepare Url
//            URLConnection myURLConnection=null;
//            URL myURL=null;
//            BufferedReader reader=null;
//
//            //encoding message
//            String encoded_message=URLEncoder.encode(text);
//
//            //Send SMS API
//            String mainUrl="http://api.msg91.com/api/sendhttp.php?";
//
//            //Prepare parameter string
//            StringBuilder sbPostData= new StringBuilder(mainUrl);
//            sbPostData.append("authkey="+authkey);
//            sbPostData.append("&mobiles="+mobiles);
//            sbPostData.append("&message="+encoded_message);
//            sbPostData.append("&route="+route);
//            sbPostData.append("&sender="+senderId);
//
//            //final string
//            mainUrl = sbPostData.toString();
//            try
//            {
//                //prepare connection
//                myURL = new URL(mainUrl);
//                myURLConnection = myURL.openConnection();
//                myURLConnection.connect();
//                reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
//                //reading response
//                String response;
//                while ((response = reader.readLine()) != null)
//                //print response
//
//                //finally close connection
//                reader.close();
//            }
//            catch (Exception e)
//            {
//                    System.out.println(e);
//            }	
//        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("FROM Admin master1");
            q.setMaxResults(1);
            Admin data = (Admin)q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            System.out.println(data);
            System.out.println(data); 
        }catch(Exception e){
            System.out.println(e);
            session.close();
        }
    }
    public String getS3Data(){
        updateS3Object("adlis", "sample/security key/sample.txt", "sample data", "sample", "txt");
        return readTextFileFromS3("adlis", "adeep/security key/public.txt");
    }
    
    public AWSCredentials getAWSCredentials() {
        return new BasicAWSCredentials(
				"", 
				"");
    }
    
    public String readTextFileFromS3(String bucketName, String key) {
            AmazonS3 s3client = new AmazonS3Client(getAWSCredentials());
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
public int updateS3Object(String bucketName, String key, String data, String fileName, String fileFormat) {
        AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
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
}
