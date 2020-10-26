/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.config;

/**
 *
 * @author dell
 */
public class ServerConfiguration {
    
//    AWS S3 (Create buckets if not created)
    public static final String S3_BUCKET = "";
    public static final String S3_PUBLIC_BUCKET = "";
    public static final String S3_PUBLI_URL_PREFIX = "";
    public static final String AWS_KEY = "";
    public static final String AWS_VALUE = "";
    
//    Mailjet mail Server (Create Account if not created)
    public static final String MAILJET_USERNAME = "";//change accordingly
    public static final String MAILJET_FROM = "";//change accordingly
    public static final String MAILJET_PASSWORD = "";//change accordingly
    public static final String MAILJET_HOST = "";
    public static final String MAILJET_PORT = "";
    
//    Firebase => change the configuration file(serviceAccountKey.xml) from resources (Create account for details)
    public static final String FIREBASE_DATABASE_URL = "";
    
//    Master
    public static final String MASTER_USERNAME = "admis";
    public static final String MASTER_CONTACT_NUMBER = "";
    public static final String MASTER_IP_ADDRESS = "";
    public static final String MASTER_NAME = "";
    public static final String MASTER_EMAIL = "";
    public static final String MASTER_PUBLIC_KEY_ADDRESS = ""; 
    public static final String MASTER_PRIVATE_KEY_ADDRESS = ""; 
    
//    MSG91 (Create account if not created)
    public static final String MSG91_AUTH_KEY = "";
    public static final String SENDER_ID = "";
    public static final String ROUTE = "4";
    
//    CCAVENUE (Create cc avanue account for details)
    public static final String CC_MERCHANT_ID = "";
    public static final String CC_CURRENCY = "";
    public static final String CC_REDIRECT_URL = "";        
    public static final String CC_CANCEL_URL = "";
    public static final String CC_LANGUAGE = "EN";    
    public static final String CC_INTEGRATION_TYPE = ""; 
    public static final String CC_ACCESS_CODE = "";
    public static final String CC_WORKING_KEY = "";
    public static final String CC_VAULT = "Y";
    
//    TPC port for receiveing data
    public static final int TCP_PORT = 4341;
    
//    Company Details
    public static String COMPANY_NAME = "";
    public static String COMPANY_SHORT_NAME = "";
    public static String COMPANY_STATE = "";
    public static String LOGO_IMAGE = "";
    public static String LOGO_IMAGE_BLOB = "";
    public static String SUPPORT_EMAIL = "";
    public static String CIN = "";
    public static String MAIN_ADDRESS = "";
    public static String SUPPORT_NUMBER = "";
    public static String IMPORTANT_INSTRUCTIONS = "";
    public static String SIGNATURE_IMAGE_BLOB = "";
    
//    LocalHost
    public static String LOCAL_URL = "";
    public static int THUMBNAIL_SIZE = 200;
    public static int MAX_IMAGE_SIZE = 1200;        
    
//    Ip white list (0 mean no, 1 mean yes)
    public static int IP_WHITE_LIST = 0;
    public static int IP_WHITE_LIST_TCP = 0;
}
