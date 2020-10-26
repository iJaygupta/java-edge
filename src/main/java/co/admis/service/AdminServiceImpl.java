/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.AdminDao;
import co.admis.model.AccountIpWhiteList;
import co.admis.model.Admin;
import co.admis.model.AdminPermission;
import co.admis.model.BrandGroup;
import co.admis.model.ColorSelection;
import co.admis.model.GlobalOffer;
import co.admis.model.Manufacturer;
import co.admis.model.Register;
import co.admis.model.SizeChart;
import co.admis.model.TaxSlab;
import co.admis.model.TaxType;
import co.admis.model.UserRole;
import co.admis.model.Vendor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author JAY
 */
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminDao adminDao;
    
    @Override
    public Admin getAdminByUsername(String username) {
        return adminDao.getAdminByUsername(username);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }
    
    @Override
    public Admin addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }

    @Override
    public boolean removeAdmin(Admin admin) {
        return adminDao.removeAdmin(admin);
    }

    @Override
    public Admin getAdminByNumber(String number) {
        return adminDao.getAdminByNumber(number);
    }
    
    @Override
    public Admin verifyAdminData(String number, String email, String username){
        return adminDao.verifyAdminData(number, email, username);
    }

    @Override
    public List<Admin> getListOfAdmin() {
        return adminDao.getListOfAdmin();
    }

    @Override
    public AdminPermission addAdminPermission(AdminPermission adminPermission) {
        return adminDao.addAdminPermission(adminPermission);
    }

    @Override
    public AdminPermission updateAdminPermission(AdminPermission adminPermission) {
        return adminDao.updateAdminPermission(adminPermission);
    }

    @Override
    public AccountIpWhiteList addAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList) {
        return adminDao.addAccountIpWhiteList(accountIpWhiteList);
    }

    @Override
    public AccountIpWhiteList updateAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList) {
        return adminDao.updateAccountIpWhiteList(accountIpWhiteList);
    }

    @Override
    public boolean removeAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList) {
        return adminDao.removeAccountIpWhiteList(accountIpWhiteList);
    }

    @Override
    public List<AccountIpWhiteList> getAccountIpWhiteListByAdmin(String adminUsername) {
        return adminDao.getAccountIpWhiteListByAdmin(adminUsername);
    }

    @Override
    public List<AccountIpWhiteList> getAccountIpWhiteList() {
        return adminDao.getAccountIpWhiteList();
    }

    @Override
    public AccountIpWhiteList getAccountIpWhiteListByIp(String ip, String adminUsername) {
        return adminDao.getAccountIpWhiteListByIp(ip, adminUsername);
    }

    @Override
    public SizeChart addSizeChart(SizeChart sizeChart) {
        return adminDao.addSizeChart(sizeChart);
    }

    @Override
    public SizeChart updateSizeChart(SizeChart sizeChart) {
        return adminDao.updateSizeChart(sizeChart);
    }

    @Override
    public SizeChart getSizeChartById(int id) {
        return adminDao.getSizeChartById(id);
    }

    @Override
    public boolean removeSizeChart(SizeChart sizeChart) {
        return adminDao.removeSizeChart(sizeChart);
    }

    @Override
    public List<SizeChart> getSizeChartByAdmin(String adminUsername) {
        return adminDao.getSizeChartByAdmin(adminUsername);
    }

    @Override
    public List<SizeChart> getSizeChartByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getSizeChartByAdminAndStatus(adminUsername, status);
    }

    @Override
    public BrandGroup addBrandGroup(BrandGroup brandGroup) {
        return adminDao.addBrandGroup(brandGroup);
    }

    @Override
    public BrandGroup updateBrandGroup(BrandGroup brandGroup) {
        return adminDao.updateBrandGroup(brandGroup);
    }

    @Override
    public BrandGroup getBrandGroupById(int id) {
        return adminDao.getBrandGroupById(id);
    }

    @Override
    public boolean removeBrandGroup(BrandGroup brandGroup) {
        return adminDao.removeBrandGroup(brandGroup);
    }

    @Override
    public List<BrandGroup> getBrandGroupByAdmin(String adminUsername) {
        return adminDao.getBrandGroupByAdmin(adminUsername);
    }

    @Override
    public List<BrandGroup> getBrandGroupByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getBrandGroupByAdminAndStatus(adminUsername, status);
    }

    @Override
    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return adminDao.addManufacturer(manufacturer);
    }

    @Override
    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        return adminDao.updateManufacturer(manufacturer);
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        return adminDao.getManufacturerById(id);
    }

    @Override
    public boolean removeManufacturer(Manufacturer brandGroup) {
        return adminDao.removeManufacturer(brandGroup);
    }

    @Override
    public List<Manufacturer> getManufacturerByAdmin(String adminUsername) {
        return adminDao.getManufacturerByAdmin(adminUsername);
    }

    @Override
    public List<Manufacturer> getManufacturerByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getManufacturerByAdminAndStatus(adminUsername, status);
    }

    @Override
    public TaxSlab addTaxSlab(TaxSlab taxSlab) {
        return adminDao.addTaxSlab(taxSlab);
    }

    @Override
    public TaxSlab updateTaxSlab(TaxSlab taxSlab) {
        return adminDao.updateTaxSlab(taxSlab);
    }

    @Override
    public TaxSlab getTaxSlabById(int id) {
        return adminDao.getTaxSlabById(id);
    }

    @Override
    public boolean removeTaxSlab(TaxSlab taxSlab) {
        return adminDao.removeTaxSlab(taxSlab);
    }

    @Override
    public List<TaxSlab> getTaxSlabByAdmin(String adminUsername) {
        return adminDao.getTaxSlabByAdmin(adminUsername);
    }

    @Override
    public List<TaxSlab> getTaxSlabByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getTaxSlabByAdminAndStatus(adminUsername, status);
    }

    @Override
    public TaxType addTaxType(TaxType taxType) {
        return adminDao.addTaxType(taxType);
    }

    @Override
    public TaxType updateTaxType(TaxType taxType) {
        return adminDao.updateTaxType(taxType);
    }

    @Override
    public TaxType getTaxTypeById(int id) {
        return adminDao.getTaxTypeById(id);
    }

    @Override
    public boolean removeTaxType(TaxType taxType) {
        return adminDao.removeTaxType(taxType);
    }

    @Override
    public List<TaxType> getTaxType() {
        return adminDao.getTaxType();
    }

    @Override
    public List<TaxType> getTaxTypeByStatus(String status) {
        return adminDao.getTaxTypeByStatus(status);
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return adminDao.addUserRole(userRole);
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        return adminDao.updateUserRole(userRole);
    }

    @Override
    public UserRole getUserRoleById(int id) {
        return adminDao.getUserRoleById(id);
    }

    @Override
    public boolean removeUserRole(UserRole userRole) {
        return adminDao.removeUserRole(userRole);
    }

    @Override
    public List<UserRole> getUserRoleByAdmin(String adminUsername) {
        return adminDao.getUserRoleByAdmin(adminUsername);
    }

    @Override
    public List<UserRole> getUserRoleByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getUserRoleByAdminAndStatus(adminUsername, status);
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        return adminDao.addVendor(vendor);
    }

    @Override
    public Vendor updateVendor(Vendor vendor) {
        return adminDao.updateVendor(vendor);
    }

    @Override
    public Vendor getVendorById(int id) {
        return adminDao.getVendorById(id);
    }

    @Override
    public boolean removeVendor(Vendor userRole) {
        return adminDao.removeVendor(userRole);
    }

    @Override
    public List<Vendor> getVendorByAdmin(String adminUsername) {
        return adminDao.getVendorByAdmin(adminUsername);
    }

    @Override
    public List<Vendor> getVendorByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getVendorByAdminAndStatus(adminUsername, status);
    }

    @Override
    public GlobalOffer addGlobalOffer(GlobalOffer vendor) {
        return adminDao.addGlobalOffer(vendor);
    }

    @Override
    public GlobalOffer updateGlobalOffer(GlobalOffer vendor) {
        return adminDao.updateGlobalOffer(vendor);
    }

    @Override
    public GlobalOffer getGlobalOfferById(int id) {
        return adminDao.getGlobalOfferById(id);
    }

    @Override
    public boolean removeGlobalOffer(GlobalOffer userRole) {
        return adminDao.removeGlobalOffer(userRole);
    }

    @Override
    public List<GlobalOffer> getGlobalOfferByAdmin(String adminUsername) {
        return adminDao.getGlobalOfferByAdmin(adminUsername);
    }

    @Override
    public List<GlobalOffer> getGlobalOfferByAdminAndStatus(String adminUsername, String status) {
        return adminDao.getGlobalOfferByAdminAndStatus(adminUsername, status);
    }

    @Override
    public GlobalOffer getGlobalOffer(String startDate, String endDate, String adminUsername, String status) {
        return adminDao.getGlobalOffer(startDate, endDate, adminUsername, status);
    }

    @Override
    public SizeChart getSizeChartById(String adminUsername, int id) {
        return adminDao.getSizeChartById(adminUsername, id);
    }

    @Override
    public BrandGroup getBrandGroupById(String adminUsername, int id) {
        return adminDao.getBrandGroupById(adminUsername, id);
    }

    @Override
    public Manufacturer getManufacturerById(String adminUsername, int id) {
        return adminDao.getManufacturerById(adminUsername, id);
    }

    @Override
    public TaxSlab getTaxSlabById(String adminUsername, int id) {
        return adminDao.getTaxSlabById(adminUsername, id);
    }

    @Override
    public UserRole getUserRoleById(String adminUsername, int id) {
        return adminDao.getUserRoleById(adminUsername, id);
    }

    @Override
    public Vendor getVendorById(String adminUsername, int id) {
        return adminDao.getVendorById(adminUsername, id);
    }

    @Override
    public GlobalOffer getGlobalOfferById(String adminUsername, int id) {
        return adminDao.getGlobalOfferById(adminUsername, id);
    }

    @Override
    public ColorSelection addColorSelection(ColorSelection colorSelection) {
        return adminDao.addColorSelection(colorSelection);
    }

    @Override
    public ColorSelection updateColorSelection(ColorSelection colorSelection) {
        return adminDao.updateColorSelection(colorSelection);
    }

    @Override
    public ColorSelection getColorSelectionById(int id) {
        return adminDao.getColorSelectionById(id);
    }

    @Override
    public boolean removeColorSelection(ColorSelection colorSelection) {
        return adminDao.removeColorSelection(colorSelection);
    }

    @Override
    public List<ColorSelection> getColorSelection() {
        return adminDao.getColorSelection();
    }

    @Override
    public List<ColorSelection> getColorSelectionByStatus(String status) {
        return adminDao.getColorSelectionByStatus(status);
    }

    @Override
    public Register addRegister(Register register) {
        return adminDao.addRegister(register);
    }

    @Override
    public Register updateRegister(Register register) {
        return adminDao.updateRegister(register);
    }

    @Override
    public Register getRegisterById(int id) {
        return adminDao.getRegisterById(id);
    }

    @Override
    public boolean removeRegister(Register register) {
        return adminDao.removeRegister(register);
    }

    @Override
    public List<Register> getRegister() {
        return adminDao.getRegister();
    }

    @Override
    public List<Register> getRegisterByStatus(String status) {
        return adminDao.getRegisterByStatus(status);
    }
    
}
