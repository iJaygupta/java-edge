/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.Facility;
import java.util.List;

/**
 *
 * @author dell
 */
public interface FacilityDao {
//    Facility
    public Facility addFacility(Facility facility);
    public Facility updateFacility(Facility facility);
    public Facility getFacilityById(int facilityId, String adminUsername);
    public List<Facility> getListofFacility(String adminUsername);
    public List<Facility> getListofActiveFacility(String adminUsername);
    public boolean removeFacilityById(Facility facility);
}
