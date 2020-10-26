/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.FacilityDao;
import co.admis.model.Facility;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class FacilityServiceImpl implements FacilityService{
    @Autowired
    FacilityDao facilityDao;
    @Override
    public Facility addFacility(Facility facility) {
        return facilityDao.addFacility(facility);
    }

    @Override
    public Facility updateFacility(Facility facility) {
        return facilityDao.updateFacility(facility);
    }

    @Override
    public Facility getFacilityById(int facility, String adminUsername) {
        return facilityDao.getFacilityById(facility, adminUsername);
    }

    @Override
    public List<Facility> getListofFacility(String username) {
        return facilityDao.getListofFacility(username);
    }
    
    @Override
    public List<Facility> getListofActiveFacility(String adminUsername){
        return facilityDao.getListofActiveFacility(adminUsername);
    }
    
    @Override
    public boolean removeFacilityById(Facility facility) {
        return facilityDao.removeFacilityById(facility);
    }
    
}
