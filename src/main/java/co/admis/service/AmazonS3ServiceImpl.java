/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import com.amazonaws.auth.AWSCredentials;
import co.admis.dao.AmazonS3Dao;
import co.admis.model.UserDocumentsList;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author JAY
 */
public class AmazonS3ServiceImpl implements AmazonS3Service{
    
    @Autowired
    public AmazonS3Dao amazonS3Dao;
    
    @Override
    public boolean setImageFromS3(String path, String key, HttpServletResponse response) {
        return amazonS3Dao.setImageFromS3(path, key, response);
    }

    @Override
    public AWSCredentials getAWSCredentials() {
        return amazonS3Dao.getAWSCredentials();
    }

    @Override
    public String getStringFromInputStream(InputStream is) {
        return amazonS3Dao.getStringFromInputStream(is);
    }

    @Override
    public String getS3BucketName() {
        return amazonS3Dao.getS3BucketName();
    }
    
    @Override
    public String getS3PublicBucketName(){
        return amazonS3Dao.getS3PublicBucketName();
    }

    @Override
    public String getS3PublicUrlPrefix(){
        return amazonS3Dao.getS3PublicUrlPrefix();
    }
    
    @Override
    public String readTextFileFromS3(String bucketName, String key) {
        return amazonS3Dao.readTextFileFromS3(bucketName, key);
    }

    @Override
    public int updateS3Object(String bucketName, String key, String data, String fileName, String fileFormat) {
        return amazonS3Dao.updateS3Object(bucketName, key, data, fileName, fileFormat);
    }

    @Override
    public File createSampleFile(String fileName, String fileFormat, String data) {
        return amazonS3Dao.createSampleFile(fileName, fileFormat, data);
    }

    @Override
    public String uploadS3Object(String bucketName, String key, File uploadObject) {
        return amazonS3Dao.uploadS3Object(bucketName, key, uploadObject);
    }

    @Override
    public boolean checkS3ObjectExist(String bucketName, String key) {
        return amazonS3Dao.checkS3ObjectExist(bucketName, key);
    }

    @Override
    public String getS3ObjectUrl(String bucketName, String key) {
        return amazonS3Dao.getS3ObjectUrl(bucketName,key);
    }

    @Override
    public List<UserDocumentsList> getListofDocumentsofUser(String key) {
        return amazonS3Dao.getListofDocumentsofUser(key);
    }

    @Override
    public File getFileFromS3(String bucketName, String key, String fileName) {
        return amazonS3Dao.getFileFromS3(bucketName,key, fileName);
    }

    @Override
    public boolean deleteS3Object(String bucketName, String key) {
        return amazonS3Dao.deleteS3Object(bucketName, key);
    }
}
