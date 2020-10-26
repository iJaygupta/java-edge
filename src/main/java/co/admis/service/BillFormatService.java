/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.BillFormat;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public interface BillFormatService {
    public BillFormat addBillFormat(BillFormat billFormat);
    public BillFormat updateBillFormat(BillFormat billFormat);
    public boolean removeBillFormat(BillFormat billFormat);
    public BillFormat getBillFormatByAdminUsername(String adminUsername);
}
