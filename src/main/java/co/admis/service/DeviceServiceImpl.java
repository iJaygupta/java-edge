/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.DeviceDao;
import co.admis.model.DeviceConfigure;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    DeviceDao deviceDao;
    @Override
    public DeviceConfigure addDeviceConfigure(DeviceConfigure configure) {
        return deviceDao.addDeviceConfigure(configure);
    }

    @Override
    public DeviceConfigure updateDeviceConfigure(DeviceConfigure configure) {
        return deviceDao.updateDeviceConfigure(configure);
    }

    @Override
    public DeviceConfigure getDeviceConfigureById(int id) {
        return deviceDao.getDeviceConfigureById(id);
    }

    @Override
    public DeviceConfigure getDeviceConfigureById(String adminUsername, int id) {
        return deviceDao.getDeviceConfigureById(adminUsername, id);
    }

    @Override
    public DeviceConfigure getDeviceConfigureBySecret(String secret) {
        return deviceDao.getDeviceConfigureBySecret(secret);
    }

    @Override
    public DeviceConfigure getDeviceConfigureBySecret(String adminUsername, String secret) {
        return deviceDao.getDeviceConfigureBySecret(adminUsername, secret);
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByFacilityId(int facilityId) {
        return deviceDao.getDeviceConfigureByFacilityId(facilityId);
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByFacilityId(String adminUsername, int facilityId) {
        return deviceDao.getDeviceConfigureByFacilityId(adminUsername, facilityId);
    }

    @Override
    public boolean removeDeviceConfigure(DeviceConfigure configure) {
        return deviceDao.removeDeviceConfigure(configure);
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByAdmin(String adminUsername) {
        return deviceDao.getDeviceConfigureByAdmin(adminUsername);
    }

    @Override
    public List<DeviceConfigure> getDeviceConfigureByAdminAndStatus(String adminUsername, String status) {
        return deviceDao.getDeviceConfigureByAdminAndStatus(adminUsername, status);
    }
    
}
