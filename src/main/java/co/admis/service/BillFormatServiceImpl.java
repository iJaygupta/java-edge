/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.BillFormatDao;
import co.admis.model.BillFormat;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class BillFormatServiceImpl implements BillFormatService{

    @Autowired
    BillFormatDao billFormatDao;
    
    @Override
    public BillFormat addBillFormat(BillFormat billFormat) {
        return billFormatDao.addBillFormat(billFormat);
    }

    @Override
    public BillFormat updateBillFormat(BillFormat billFormat) {
        return billFormatDao.updateBillFormat(billFormat);
    }

    @Override
    public boolean removeBillFormat(BillFormat billFormat) {
        return billFormatDao.removeBillFormat(billFormat);
    }

    @Override
    public BillFormat getBillFormatByAdminUsername(String adminUsername) {
        return billFormatDao.getBillFormatByAdminUsername(adminUsername);
    }
    
}
