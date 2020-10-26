/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.dao;

import co.admis.model.Master;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dell
 */
public interface MasterDao {
    public Master getMasterByUsername(String username);
    public Master updateMaster(Master master);

    public boolean masterSecurityCheck(HttpServletRequest request);
}
