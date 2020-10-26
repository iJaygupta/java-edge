/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.MasterDao;
import co.admis.model.Master;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dell
 */
public class MasterServiceImpl implements MasterService{
    @Autowired
    MasterDao masterDao;
    
    @Override
    public Master getMasterByUsername(String username) {
        return masterDao.getMasterByUsername(username);
    }

    @Override
    public Master updateMaster(Master master) {
        return masterDao.updateMaster(master);
    }

    @Override
    public boolean masterSecurityCheck(HttpServletRequest request) {
        return masterDao.masterSecurityCheck(request);
    }
    
}
