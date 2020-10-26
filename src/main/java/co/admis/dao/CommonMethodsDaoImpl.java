/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.controller.HibernateUtil;
import co.admis.model.InvoiceData;
import static com.amazonaws.services.s3.AmazonS3EncryptionClient.USER_AGENT;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JAY
 */
public class CommonMethodsDaoImpl implements CommonMethodsDao{

    @Override
    public String convertTime12to24(String time) {
        DateFormat outputformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
       //Date/time pattern of desired output date
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
       Date date = null;
       String output = null;
        try {
            //Conversion of input String to date
            date= df.parse(time);
            System.out.println(date);
        } catch (ParseException ex) {
            Logger.getLogger(CommonMethodsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       //old date format to new date format
       output = outputformat.format(date);
        System.out.println(output);
       return output;
    }

     @Override
    public String convertTime24to12(String input) {
         //Input date in String format
       //Date/time pattern of input date
       DateFormat df = new SimpleDateFormat("HH:mm");
       //Date/time pattern of desired output date
       DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
       Date date = null;
       String output = null;
        try {
            //Conversion of input String to date
            date= df.parse(input);
        } catch (ParseException ex) {
            Logger.getLogger(CommonMethodsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       //old date format to new date format
       output = outputformat.format(date);
       return output;
    }

    @Override
    public String getCurrentDate() {
        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String month = d.getMonth()+"";
        return String.format("%02d", d.getDayOfMonth())+"-"+month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase()+"-"+d.getYear();
    }

    @Override
    public String getCurrentTime() {
        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String[] date = d.toString().split("T");
        String[] time = date[1].split(("\\."));
        System.out.println(time);
        return time[0];
    }
    
    @Override
    public String getTimeInMilis() {
        Date date = new Date();
        return String.valueOf(date.getTime());
    }

    @Override
    public String randomTenDigitNumber() {
          Random rand = new Random();
        // Generate random integers in range 0 to 999
        String text = Integer.toString(rand.nextInt(1000000));
        return text;
    }


    @Override
    public String randomSixDigitNumber() {
        Random rand = new Random();
        // Generate random integers in range 0 to 999999
        String text = String.format("%06d", rand.nextInt(1000000));
        return text;
    }

    @Override
    public long convertTimeIntoMilisecond(String time) {
        String[] t1 = time.split(":");
        long hours = getStringTimeIntoLong(t1[0])*60*60*1000;
        long minutes = getStringTimeIntoLong(t1[1])*60*1000;
        return minutes+hours;
    }
    
    private long getStringTimeIntoLong(String minutes){
        if(!minutes.equalsIgnoreCase("00")){
        if(minutes.charAt(0) == '0'){
                return Character.getNumericValue(minutes.charAt(1));
            }else{
                return Long.parseLong(minutes);
            }
        }
        return 0;
    }

    @Override
    public String convertObjectIntoJson(Object obj) {
       Gson gson = new GsonBuilder().serializeNulls().create();
       return gson.toJson(obj);
    }
    
    @Override
    public File convertMultipartFileIntoFile(MultipartFile file) {
        File convFile = null;
        try {
            convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(AmazonS3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return convFile;
    }

    @Override
    public Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    @Override
    public String randomFourDigitNumber() {
        Random rand = new Random();
        // Generate random integers in range 0 to 9999
        String text = String.format("%04d", rand.nextInt(10000));
        return text;
    }
    
    @Override
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
    
    @Override
    public String getOrderId(int length){
        SecureRandom random = new SecureRandom();
        final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
        final String NUMERIC = "0123456789";
        
        String result = "";
        String dic = ALPHA_CAPS + ALPHA + NUMERIC;
        for (int i = 0; i < length; i++) {
        int index = random.nextInt(dic.length());
        result += dic.charAt(index);
        }
        return result;
    }

    @Override
    public String sendGet(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                // print result
                System.out.println(response.toString());
                return response.toString();
            } else {
                System.out.println("GET request not worked");
                return "GET request not worked";
            }
        } catch (MalformedURLException ex) {
            return ex.toString();
        } catch (ProtocolException ex) {
            return ex.toString();
        } catch (IOException ex) {
            return ex.toString();
        }
    }

    @Override
    public String sendPost(String url, String param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public String getReferenceId(int length) {
        SecureRandom random = new SecureRandom();
        final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String NUMERIC = "0123456789";
        
        String result = "";
        String dic = ALPHA_CAPS + NUMERIC;
        for (int i = 0; i < length; i++) {
        int index = random.nextInt(dic.length());
        result += dic.charAt(index);
        }
        return result;
    }
    
    @Override
    public boolean compareTimeForMinutes(String time, int minutes) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy hh:mm:ss");
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(getCurrentDate()+" "+getCurrentTime());
            return date2.getTime()<=date1.getTime()+(minutes*60000);
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean compareTimeForHours(String time, int hour) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy hh:mm:ss");
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(getCurrentDate()+" "+getCurrentTime());
            return date2.getTime()<=date1.getTime()+(hour*60*60000);
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public String getInvoiceNumbreFormat(String prefix, int invoiceNumber, String date) {
        try {
            String s1 = prefix;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy hh:mm:ss");
            Date d = sdf.parse(date);
            int year = 0;
            if(d.getMonth()<3){
                year = d.getYear()+1900-1;
            }else{
                year = d.getYear()+1900;
            }
            String s2 = year+"";
            String s3 = String.format("%03d", invoiceNumber);
            
            return s1+s2+s3;
        } catch (ParseException ex) {
            throw new UnsupportedOperationException(ex.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public int getInvoiceNumberByDate(int invoiceId, String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy hh:mm:ss");
            Date lastDate = sdf.parse(date);
            Date today = sdf.parse(getCurrentDate()+" "+getCurrentTime());
            if(lastDate.getMonth()==2 && today.getMonth()==3){
                return 1;
            }else{
                return invoiceId+1;
            }
        } catch (ParseException ex) {
            throw new UnsupportedOperationException(ex.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public String convertDateFormat(String date) {
        ZonedDateTime d = ZonedDateTime.parse(date+"T10:15:30+01:00[Asia/Kolkata]");
        String month = d.getMonth()+"";
        if(d.getYear()<1800){
            throw new UnsupportedOperationException("Please enter valid date"); //To change body of generated methods, choose Tools | Templates.
        }
        String s =  String.format("%02d", d.getDayOfMonth())+"-"+month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase()+"-"+d.getYear();
        return s;
    }
    
    @Override
    public String capitalize(String str){
        try{
            if(str.equalsIgnoreCase("")){
                return "";
            }else if(str.length()<2){
                return str.substring(0, 1).toUpperCase();
            }
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }catch(Exception e){
            return "";
        }
    }
    
    @Override
    public String capitalizeAll(String str){
        try{
            if(str.equalsIgnoreCase("")){
                return "";
            }else if(str.length()<2){
                return str.substring(0, 1).toUpperCase();
            }else{
                String words[]=str.split("\\s");  
                String capitalizeWord="";  
                for(String w:words){  
                    String first=w.substring(0,1);  
                    String afterfirst=w.substring(1);  
                    capitalizeWord+=first.toUpperCase()+afterfirst+" ";  
                }  
                return capitalizeWord.trim(); 
            }
        }catch(Exception e){
            return "";
        }
    }
    
    @Override
    public String calculateAge(String dob){
        try{
            ZonedDateTime d = ZonedDateTime.parse(dob+"T10:15:30+01:00[Asia/Kolkata]");
            LocalDate birthdate = new LocalDate (d.getYear(), d.getMonthValue(), d.getDayOfMonth());      //Birth date
            LocalDate now = new LocalDate();  
            Period pDays = new Period(birthdate, now, PeriodType.days());
            Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
            String age = "";
            if(pDays.getDays()<0 || period.getYears()>150){
                throw new UnsupportedOperationException("Please check the date of birth"); //To change body of generated methods, choose Tools | Templates.
            }
            if(period.getYears()>0){
                if(period.getYears()==1){
                    age = period.getYears()+" Year";
                }else{
                    age = period.getYears()+" Years";
                }
            }else if(period.getYears()==0 && period.getMonths()>0){
                if(period.getMonths()==1){
                    age = period.getMonths()+" Month";
                }else{
                    age = period.getMonths()+" Months";
                }
            }else if(period.getYears()==0 && period.getMonths()==0){
                if(period.getDays()==1){
                    age = period.getDays()+" Day";
                }else{
                    age = period.getDays()+" Days";
                }
            }
            return age;
        }catch(UnsupportedOperationException e){
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public int calculateAgeByFilter(String dob, String filter){
        try{
            String date = convertDateFormatToDefault(dob);
            ZonedDateTime d = ZonedDateTime.parse(date+"T10:15:30+01:00[Asia/Kolkata]");
            LocalDate birthdate = new LocalDate (d.getYear(), d.getMonthValue(), d.getDayOfMonth());      //Birth date
            LocalDate now = new LocalDate();  
            Period period = null;
            int data = 0;
            switch(filter){
                case "day":
                    period = new Period(birthdate, now, PeriodType.days());
                    data = period.getDays();
                    break;
                case "month":
                    period = new Period(birthdate, now, PeriodType.months());
                    data = period.getMonths();
                    break;
                case "year":
                    period = new Period(birthdate, now, PeriodType.years());
                    data = period.getYears();
                    break;
            }
            return data;
        }catch(UnsupportedOperationException e){
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public Period getPeriodByPatientDob(String dob){
        try{
            String date = convertDateFormatToDefault(dob);
            ZonedDateTime d = ZonedDateTime.parse(date+"T10:15:30+01:00[Asia/Kolkata]");
            LocalDate birthdate = new LocalDate (d.getYear(), d.getMonthValue(), d.getDayOfMonth());      //Birth date
            LocalDate now = new LocalDate();  
            Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
            int age = period.getYears();
            if(age<0 || age >150){
                throw new UnsupportedOperationException("Please check the date of birth"); //To change body of generated methods, choose Tools | Templates.
            }
            return period;
        }catch(UnsupportedOperationException e){
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public Period getPeriodByDate(String date){
        try{
            ZonedDateTime d = ZonedDateTime.parse(date+"T10:15:30+01:00[Asia/Kolkata]");
            LocalDate birthdate = new LocalDate (d.getYear(), d.getMonthValue(), d.getDayOfMonth());      //Birth date
            LocalDate now = new LocalDate();  
            Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
            int age = period.getYears();
            if(age<0 || age >150){
                throw new UnsupportedOperationException("Please check the date of birth"); //To change body of generated methods, choose Tools | Templates.
            }
            return period;
        }catch(UnsupportedOperationException e){
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public boolean isNumeric(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    @Override
    public int ageYearConvert(int age, String convert) { 
        switch(convert){
            case "day":
                return 30*12*age;
            case "month":
                return 12*age;
            case "year":
                return age;
        }
        return age;
    }
    
    @Override
    public Number countRows(String tableName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query q = session.createQuery("SELECT COUNT(*) from "+tableName+" u");
            Number count = (Number) q.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return count;
        }catch(Exception e){
            session.close();
            throw new UnsupportedOperationException(e.getLocalizedMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public String getCookieValueByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
         for (Cookie c : cookies) {
           if (c.getName().equals(name)) {
             //do something
               return c.getValue();
             //value can be retrieved using #cookie.getValue()
            }
          }
        }
        return null;
    }

    @Override
    public Cookie getCookieByName(HttpServletRequest request, String name) {
         Cookie[] cookies = request.getCookies();
        if (cookies != null) {
         for (Cookie c : cookies) {
           if (c.getName().equals(name)) {
             //do something
               return c;
             //value can be retrieved using #cookie.getValue()
            }
          }
        }
        return null;
    }
    
    @Override
    public String convertDateFormatToDefault(String date) {
        try {
            String str = date.split("\\s+")[0];
            String[] d = str.split("-");
            String day = d[0];
            Date d1 = new SimpleDateFormat("MMMM").parse(d[1]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d1);
            int m = cal.get(Calendar.MONTH)+1;
            String month = m+"";
            if(m<10){
                month = "0"+m;
            }
            String year = d[2];
            return year+"-"+month+"-"+day;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.getMessage()); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public InvoiceData getInvoiceIdByDate(String date, String lastDate, int lastInvoiceId, String invoiceNumberPrefix){
        //Current Date
        String currectDate = convertDateFormatToDefault(date);
        ZonedDateTime zd = ZonedDateTime.parse(currectDate+"T10:15:30+01:00[Asia/Kolkata]");
        // Financial year already over
        int year = 0;
        if(zd.getMonthValue()<4){
            year = zd.getYear()-1;
        }else{
            year = zd.getYear();
        }
        
        if(lastDate==null){
            return new InvoiceData(invoiceNumberPrefix+year+String.format("%03d", lastInvoiceId), lastInvoiceId);
        }
        //Last Invoice Date
        String dLast = convertDateFormatToDefault(lastDate);
        ZonedDateTime zl = ZonedDateTime.parse(dLast+"T10:15:30+01:00[Asia/Kolkata]");
        System.out.println(zd.getMonthValue()+" "+zl.getMonthValue());
        if((zd.getYear() > zl.getYear()+1) || (zd.getYear()==zl.getYear() && zd.getMonthValue()>3 && zl.getMonthValue() < 4) || (zd.getYear()>zl.getYear() && zd.getMonthValue()>3 && zl.getMonthValue()>3) || (zd.getYear()>zl.getYear() && zd.getMonthValue()<4)){
            String s2 = String.format("%03d", 1);
            String s = invoiceNumberPrefix+year+s2;
            return new InvoiceData(s, 1);
        }else{
            String s2 = String.format("%03d", lastInvoiceId+1);
            String s = invoiceNumberPrefix+year+s2;
            return new InvoiceData(s, lastInvoiceId+1);
        }
    }
}
