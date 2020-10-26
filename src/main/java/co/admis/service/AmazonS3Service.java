/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.UserDocumentsList;
import com.amazonaws.auth.AWSCredentials;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JAY
 */
public interface AmazonS3Service {
    public boolean setImageFromS3(String path, String key, HttpServletResponse response);
    
    public AWSCredentials getAWSCredentials();
    
    public String getStringFromInputStream(InputStream is);
    
    public String getS3BucketName();
    
    public String getS3PublicBucketName();
    
    public String getS3PublicUrlPrefix();
    
    public String readTextFileFromS3(String bucketName, String key);
    
    public int updateS3Object(String bucketName, String key, String data, String fileName, String fileFormat);
    
    public File createSampleFile(String fileName, String fileFormat, String data);
    
    public String uploadS3Object(String bucketName, String key, File uploadObject);
    
    public boolean checkS3ObjectExist(String bucketName, String key);
    
    public String getS3ObjectUrl(String bucketName, String key); 

    public List<UserDocumentsList> getListofDocumentsofUser(String key);
    
    public File getFileFromS3(String bucketName, String key, String fileName);
    
    public boolean deleteS3Object(String bucketName, String key);

}
