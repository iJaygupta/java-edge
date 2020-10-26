/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author dell
 */
public class VisitUpdateJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        // Task 
    }
    
    public boolean compareTimeForHours(String time, int hour) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMMM-yyyy hh:mm:ss");
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(getCurrentDate()+" "+getCurrentTime());
            return date2.getTime()>=date1.getTime()+(hour*60*60000); // Return true if time is passed
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public String getCurrentDate() {
        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String month = d.getMonth()+"";
        return String.format("%02d", d.getDayOfMonth())+"-"+month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase()+"-"+d.getYear();
    }
    
    public String getCurrentTime() {
        ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String[] date = d.toString().split("T");
        String[] time = date[1].split(("\\."));
        return time[0];
    }
}
