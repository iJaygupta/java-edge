/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.InvoiceData;
import java.awt.Dimension;
import java.io.File;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.Period;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JAY
 */
public interface CommonMethodsDao {

    public String convertTime12to24(String time);

    public String convertTime24to12(String time);

    public String getCurrentDate();
    
    public String getCurrentTime();
    
    public String getTimeInMilis();
    
    public String randomTenDigitNumber();
    
    public String randomSixDigitNumber();

    public long convertTimeIntoMilisecond(String time);

    public String convertObjectIntoJson(Object obj);
    
    public File convertMultipartFileIntoFile(MultipartFile file);
    
    public Dimension getScaledDimension(Dimension imgSize, Dimension boundary);

    public String randomFourDigitNumber();
    
    public String generateRendomStrongPassword(int length);
    
    public String getOrderId(int length);
    
    public String getReferenceId(int length);
    
    public String sendGet(String url);
    
    public String sendPost(String url, String param);
    
    public double round(double value, int places);
    
    public boolean compareTimeForMinutes(String time, int minutes);
    
    public boolean compareTimeForHours(String time, int hour);
    
    public int getInvoiceNumberByDate(int invoiceId, String date);
    
    public String getInvoiceNumbreFormat(String prefix, int invoiceNumber, String date);
    
    public String convertDateFormat(String date);
    
    public String capitalize(String str);
    
    public String capitalizeAll(String str);
    
    public String calculateAge(String dob);
    
    public Period getPeriodByDate(String date);
    
    public Period getPeriodByPatientDob(String dob);
    
    public String convertDateFormatToDefault(String date);
    
    public int calculateAgeByFilter(String dob, String filter);
    
    public boolean isNumeric(String str);
    
    public int ageYearConvert(int age, String convert);
    
    public Number countRows(String tableName);
    
    public String getCookieValueByName(HttpServletRequest request, String name);
    
    public Cookie getCookieByName (HttpServletRequest request, String name);
    
    public InvoiceData getInvoiceIdByDate(String date, String lastDate, int lastInvoiceId, String invoiceNumberPrefix);
}
