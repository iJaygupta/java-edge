/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import static co.admis.config.ServerConfiguration.AWS_KEY;
import static co.admis.config.ServerConfiguration.AWS_VALUE;
import static co.admis.config.ServerConfiguration.S3_BUCKET;
import static co.admis.config.ServerConfiguration.S3_PUBLIC_BUCKET;
import static co.admis.config.ServerConfiguration.S3_PUBLI_URL_PREFIX;
import co.admis.model.UserDocumentsList;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JAY
 */
public class AmazonS3DaoImpl implements AmazonS3Dao{
    
    @Override
    public boolean setImageFromS3(String path, String key, HttpServletResponse response) {
        File tmp = null;
        try {
            // create a client connection based on credentials
            AmazonS3 s3client = new AmazonS3Client(getAWSCredentials());
            
            String bucketName = getS3BucketName();
            String fileName = "";
            // upload file to folder and set it to public
            if(key.equalsIgnoreCase("")){
                fileName = path;
            }else{
                fileName = path +"/" + key;
            }
            System.out.println("Testing");
            System.out.println(fileName);
            S3Object o = s3client.getObject(bucketName, fileName);
            S3ObjectInputStream s3is = o.getObjectContent();
            tmp = File.createTempFile("s3test", ".png");
            Files.copy(s3is, tmp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            
            try {
              BufferedImage image = ImageIO.read(tmp);
              ImageIO.write(image, "png", jpegOutputStream);
            } catch (IllegalArgumentException e) {
              response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            tmp.delete();
            byte[] imgByte = jpegOutputStream.toByteArray();
            
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/png");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(imgByte);
            responseOutputStream.flush();
            responseOutputStream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public AWSCredentials getAWSCredentials() {
        return new BasicAWSCredentials(
				AWS_KEY, 
				AWS_VALUE);
    }

    @Override
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

    @Override
    public String getS3BucketName() {
        return S3_BUCKET;
    }
    
    @Override
    public String getS3PublicBucketName() {
        return S3_PUBLIC_BUCKET;
    }

    @Override
    public String getS3PublicUrlPrefix(){
        return S3_PUBLI_URL_PREFIX;
    }
    
    @Override
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
    

    @Override
    public int updateS3Object(String bucketName, String key, String data, String fileName, String fileFormat) {
        AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
        File file   = createSampleFile(fileName, fileFormat, data);
        s3.putObject(new PutObjectRequest(bucketName, key, file));
        file.delete();
        return 1;
    }

    @Override
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

    @Override
    public String uploadS3Object(String bucketName, String key, File uploadObject) {
        AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
        s3.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).disableChunkedEncoding().build());
        s3.putObject(new PutObjectRequest(bucketName, key, uploadObject));
        return "Uploaded Successful";
    }

    @Override
    public boolean checkS3ObjectExist(String bucketName, String key) {
        AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
        return s3.doesObjectExist(bucketName, key);
    }

    @Override
    public String getS3ObjectUrl(String bucketName, String objectKey) {
        Regions clientRegion = Regions.AP_SOUTH_1;
        try {
            AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
            s3.setRegion(Region.getRegion(clientRegion));
            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);

            // Generate the presigned URL.
            System.out.println("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, objectKey)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);

            System.out.println("Pre-Signed URL: " + url.toString());
            return url.toString();
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
            e.printStackTrace();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public List<UserDocumentsList> getListofDocumentsofUser(String key) {
        List<UserDocumentsList> list = new ArrayList<>();
       
        AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
    
            // maxKeys is set to 2 to demonstrate the use of
            // ListObjectsV2Result.getNextContinuationToken()
            ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(getS3BucketName());
            ListObjectsV2Result result;

            do {
                result = s3.listObjectsV2(req);
    
                for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                    
                    if(objectSummary.getKey().contains(key) && !objectSummary.getKey().equalsIgnoreCase(key)){
                        UserDocumentsList documentsList = new UserDocumentsList();
                        documentsList.setDocumentName(objectSummary.getKey());
                        documentsList.setDocumentSize(objectSummary.getSize());
                        documentsList.setLastModifiedDate(objectSummary.getLastModified());
                        list.add(documentsList);
                    }
                }
                // If there are more than maxKeys keys in the bucket, get a continuation token
                // and list the next objects.
                String token = result.getNextContinuationToken();
                System.out.println("Next Continuation Token: " + token);
                req.setContinuationToken(token);
            } while (result.isTruncated());
            return list;
    }

    @Override
    public File getFileFromS3(String bucketName, String key, String fileName) {
        try {
            String name = fileName.split("\\.")[0];
            String format = fileName.split("\\.")[1];
            AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
            S3Object o = s3.getObject(bucketName, key);
            S3ObjectInputStream s3is = o.getObjectContent();
            final File file = File.createTempFile(name, "."+format);
            Files.copy(s3is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return file;
        } catch (IOException ex) {
            Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteS3Object(String bucketName, String key) {
        try{
            AmazonS3 s3 = new AmazonS3Client(getAWSCredentials());
            s3.deleteObject(new DeleteObjectRequest(bucketName, key));
            return true; 
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
