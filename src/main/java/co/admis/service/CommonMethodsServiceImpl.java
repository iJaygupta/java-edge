/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.CommonMethodsDao;
import co.admis.model.InvoiceData;
import java.awt.Dimension;
import java.io.File;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JAY
 */
public class CommonMethodsServiceImpl implements CommonMethodsService{

    @Autowired
    CommonMethodsDao commonMethodsDao;
    
    @Override
    public String convertTime12to24(String time) {
        return commonMethodsDao.convertTime12to24(time);
    }

    @Override
    public String convertTime24to12(String time) {
        return commonMethodsDao.convertTime24to12(time);
    }

    @Override
    public String getCurrentDate() {
        return commonMethodsDao.getCurrentDate();
    }

    @Override
    public String getCurrentTime() {
        return commonMethodsDao.getCurrentTime();
    }
    
    @Override
    public String getTimeInMilis() {
        return commonMethodsDao.getTimeInMilis();
    }
    
    @Override
    public String randomTenDigitNumber() {
       return commonMethodsDao.randomTenDigitNumber();
    }
    
    @Override
    public String randomSixDigitNumber() {
       return commonMethodsDao.randomSixDigitNumber();
    }

    @Override
    public long convertTimeIntoMilisecond(String time) {
        return commonMethodsDao.convertTimeIntoMilisecond(time);
    }

    @Override
    public String convertObjectIntoJson(Object obj) {
        return commonMethodsDao.convertObjectIntoJson(obj);
    }

    @Override
    public File convertMultipartFileIntoFile(MultipartFile file) {
        return commonMethodsDao.convertMultipartFileIntoFile(file);
    }

    @Override
    public Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        return commonMethodsDao.getScaledDimension(imgSize, boundary);
    }

    @Override
    public String randomFourDigitNumber() {
        return commonMethodsDao.randomFourDigitNumber();
    }
    
    @Override
    public String getOrderId(int length) {
        return commonMethodsDao.getOrderId(length);
    }

    @Override
    public String sendGet(String url) {
        return commonMethodsDao.sendGet(url);
    }

    @Override
    public String sendPost(String url, String param) {
        return commonMethodsDao.sendPost(url, param);
    }

    @Override
    public double round(double value, int places) {
        return commonMethodsDao.round(value, places);
    }

    @Override
    public String getReferenceId(int length) {
        return commonMethodsDao.getReferenceId(length);
    }

    @Override
    public String generateRendomStrongPassword(int length) {
        return commonMethodsDao.generateRendomStrongPassword(length);
    }

    @Override
    public boolean compareTimeForMinutes(String time, int minutes) {
        return commonMethodsDao.compareTimeForMinutes(time, minutes);
    }

    @Override
    public boolean compareTimeForHours(String time, int hour) {
        return commonMethodsDao.compareTimeForHours(time, hour);
    }

    @Override
    public String getInvoiceNumbreFormat(String prefix, int invoiceNumber, String date) {
        return commonMethodsDao.getInvoiceNumbreFormat(prefix, invoiceNumber, date);
    }

    @Override
    public int getInvoiceNumberByDate(int invoiceId, String date) {
        return commonMethodsDao.getInvoiceNumberByDate(invoiceId, date);
    }
    
    @Override
    public String convertDateFormat(String date){
        return commonMethodsDao.convertDateFormat(date);
    }
    
    @Override
    public String capitalize(String str){
        return commonMethodsDao.capitalize(str);
    }
    
    @Override
    public String capitalizeAll(String str){
        return commonMethodsDao.capitalizeAll(str);
    }
    
    @Override
    public String calculateAge(String dob){
        return commonMethodsDao.calculateAge(dob);
    }

    @Override
    public Period getPeriodByDate(String date) {
        return commonMethodsDao.getPeriodByDate(date);
    }

    @Override
    public Period getPeriodByPatientDob(String dob) {
        return commonMethodsDao.getPeriodByPatientDob(dob);
    }

    @Override
    public String convertDateFormatToDefault(String date) {
        return commonMethodsDao.convertDateFormatToDefault(date);
    }
    
    @Override
    public int calculateAgeByFilter(String dob, String filter) {
        return commonMethodsDao.calculateAgeByFilter(dob, filter);
    }

    @Override
    public boolean isNumeric(String str) {
        return commonMethodsDao.isNumeric(str);
    }
    
    @Override
    public int ageYearConvert(int age, String convert){
        return commonMethodsDao.ageYearConvert(age, convert);
    }
    
    @Override
    public Number countRows(String tableName){
        return commonMethodsDao.countRows(tableName);
    }
    
    @Override
    public String getCookieValueByName(HttpServletRequest request, String name){
        return  commonMethodsDao.getCookieValueByName(request, name);
    }
    
    @Override
    public Cookie getCookieByName (HttpServletRequest request, String name){
        return commonMethodsDao.getCookieByName(request, name);
    }

    @Override
    public InvoiceData getInvoiceIdByDate(String date, String lastDate, int lastInvoiceId, String invoiceNumberPrefix) {
        return commonMethodsDao.getInvoiceIdByDate(date, lastDate, lastInvoiceId, invoiceNumberPrefix);
    }
}
