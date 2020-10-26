/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.DeviceConfigure;
import java.util.List;

/**
 *
 * @author dell
 */
public interface DeviceDao {
    // DeviceConfigure
    public DeviceConfigure addDeviceConfigure(DeviceConfigure configure);
    public DeviceConfigure updateDeviceConfigure(DeviceConfigure configure);
    public DeviceConfigure getDeviceConfigureById(int id);
    public DeviceConfigure getDeviceConfigureById(String adminUsername, int id);
    public DeviceConfigure getDeviceConfigureBySecret(String secret);
    public DeviceConfigure getDeviceConfigureBySecret(String adminUsername, String secret);
    public List<DeviceConfigure> getDeviceConfigureByFacilityId(int facilityId);
    public List<DeviceConfigure> getDeviceConfigureByFacilityId(String adminUsername, int facilityId);
    public boolean removeDeviceConfigure(DeviceConfigure configure);
    public List<DeviceConfigure> getDeviceConfigureByAdmin(String adminUsername);
    public List<DeviceConfigure> getDeviceConfigureByAdminAndStatus(String adminUsername, String status);
}
