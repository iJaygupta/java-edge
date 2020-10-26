/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.service.AdminService;
import co.admis.service.AmazonS3Service;
import co.admis.service.OrganizationService;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.gif.GifImageParser;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;
import org.apache.commons.imaging.formats.png.PngImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.wbmp.WbmpImageParser;
import org.apache.commons.imaging.formats.xbm.XbmImageParser;
import org.apache.commons.imaging.formats.xpm.XpmImageParser;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class SecurityDaoImpl implements SecurityDao{
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    AmazonS3Service amazonS3Service;
    
    @Autowired
    OrganizationService organizationService;
    @Override
    public boolean isImageSafe(File f) {
        boolean safeState = false;
        boolean fallbackOnApacheCommonsImaging;
        try {
            if ((f != null) && f.exists() && f.canRead() && f.canWrite()) {
                //Get the image format
                String formatName;
                try (ImageInputStream iis = ImageIO.createImageInputStream(f)) {
                    Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReaders(iis);
                    //If there not ImageReader instance found so it's means that the current format is not supported by the Java built-in API
                    if (!imageReaderIterator.hasNext()) {
                        ImageInfo imageInfo = Imaging.getImageInfo(f);
                        if (imageInfo != null && imageInfo.getFormat() != null && imageInfo.getFormat().getName() != null) {
                            formatName = imageInfo.getFormat().getName();
                            fallbackOnApacheCommonsImaging = true;
                        } else {
                            throw new IOException("Format of the original image is not supported for read operation !");
                        }
                    } else {
                        ImageReader reader = imageReaderIterator.next();
                        formatName = reader.getFormatName();
                        fallbackOnApacheCommonsImaging = false;
                    }
                }

                // Load the image
                BufferedImage originalImage;
                if (!fallbackOnApacheCommonsImaging) {
                    originalImage = ImageIO.read(f);
                } else {
                    originalImage = Imaging.getBufferedImage(f);
                }

                // Check that image has been successfully loaded
                if (originalImage == null) {
                    throw new IOException("Cannot load the original image !");
                }

                // Get current Width and Height of the image
                int originalWidth = originalImage.getWidth(null);
                int originalHeight = originalImage.getHeight(null);


                // Resize the image by removing 1px on Width and Height
                Image resizedImage = originalImage.getScaledInstance(originalWidth - 1, originalHeight - 1, Image.SCALE_SMOOTH);

                // Resize the resized image by adding 1px on Width and Height - In fact set image to is initial size
                Image initialSizedImage = resizedImage.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);

                // Save image by overwriting the provided source file content
                BufferedImage sanitizedImage = new BufferedImage(initialSizedImage.getWidth(null), initialSizedImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics bg = sanitizedImage.getGraphics();
                bg.drawImage(initialSizedImage, 0, 0, null);
                bg.dispose();
                try (OutputStream fos = Files.newOutputStream(f.toPath(), StandardOpenOption.WRITE)) {
                    if (!fallbackOnApacheCommonsImaging) {
                        ImageIO.write(sanitizedImage, formatName, fos);
                    } else {
                        ImageParser imageParser;
                        //Handle only formats for which Apache Commons Imaging can successfully write (YES in Write column of the reference link) the image format
                        //See reference link in the class header
                        switch (formatName) {
                            case "TIFF": {
                                imageParser = new TiffImageParser();
                                break;
                            }
                            case "PCX": {
                                imageParser = new PcxImageParser();
                                break;
                            }
                            case "DCX": {
                                imageParser = new PcxImageParser();
                                break;
                            }
                            case "BMP": {
                                imageParser = new BmpImageParser();
                                break;
                            }
                            case "GIF": {
                                imageParser = new GifImageParser();
                                break;
                            }
                            case "PNG": {
                                imageParser = new PngImageParser();
                                break;
                            }
                            case "JPEG": {
                                imageParser = new JpegImageParser();
                                break;
                            }
                            case "JPG": {
                                imageParser = new JpegImageParser();
                                break;
                            }
                            case "WBMP": {
                                imageParser = new WbmpImageParser();
                                break;
                            }
                            case "XBM": {
                                imageParser = new XbmImageParser();
                                break;
                            }
                            case "XPM": {
                                imageParser = new XpmImageParser();
                                break;
                            }
                            default: {
                                throw new IOException("Format of the original image is not supported for write operation !");
                            }

                        }
                        imageParser.writeImage(sanitizedImage, fos, (Map)new HashMap<>());
                    }

                }

                // Set state flag
                safeState = true;
            }
        } catch (Exception e) {
            safeState = false;
            System.out.println(e);
        }

        return safeState;
    }

    @Override
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

    @Override
    public String decrypt(byte[] text, PrivateKey privateKey) {
        try {
            //Creating a Cipher object
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            
            //Initializing the same cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            
            //Decrypting the text
            byte[] decipheredText = cipher.doFinal(text);
            return new String(decipheredText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(SecurityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
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

    @Override
    public PrivateKey loadPrivateKey(String key64) {
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

    @Override
    public PublicKey loadPublicKey(String stored) {
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

    @Override
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

    @Override
    public String savePublicKey(PublicKey publ) {
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

    @Override
    public PublicKey getPublicKeyByAdminUsername(String adminUsername) {
        return loadPublicKey(amazonS3Service.readTextFileFromS3(amazonS3Service.getS3BucketName(), organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(adminUsername)).getS3FolderName()+"/security key/public.txt"));
    }

    @Override
    public PrivateKey getPrivateKeyByAdminUsername(String adminUsername) {
        return loadPrivateKey(amazonS3Service.readTextFileFromS3(amazonS3Service.getS3BucketName(), organizationService.getOrganizationById(organizationService.getOrganizationIdByAdminUsername(adminUsername)).getS3FolderName()+"/security key/private.txt"));
    }
    
    @Override
    public PublicKey getPublicKeyForMaster() {
        return loadPublicKey(amazonS3Service.readTextFileFromS3(amazonS3Service.getS3BucketName(), "master/security key/public.txt"));
    }

    @Override
    public PrivateKey getPrivateKeyForMaster() {
        return loadPrivateKey(amazonS3Service.readTextFileFromS3(amazonS3Service.getS3BucketName(), "master/security key/private.txt"));
    }
    
    @Override
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

       return "";
    }
    
    public String parseJWT(String jwt) {
 
        return "";
    }
}
