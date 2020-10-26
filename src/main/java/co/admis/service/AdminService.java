/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

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

/**
 *
 * @author JAY
 */
public interface AdminService {
     
    //Admin
    public Admin addAdmin(Admin admin);
    public Admin updateAdmin(Admin admin);
    public boolean removeAdmin(Admin admin);
    public Admin getAdminByUsername(String username);
    public Admin getAdminByNumber(String number);
    public Admin verifyAdminData(String number, String email, String username);
    public List<Admin> getListOfAdmin();
    
    //Admin Permission
    public AdminPermission addAdminPermission(AdminPermission adminPermission);
    public AdminPermission updateAdminPermission(AdminPermission adminPermission);
    
    //Account Ip White List
    public AccountIpWhiteList addAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList);
    public AccountIpWhiteList updateAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList);
    public boolean removeAccountIpWhiteList(AccountIpWhiteList accountIpWhiteList);
    public AccountIpWhiteList getAccountIpWhiteListByIp(String ip, String adminUsername);
    public List<AccountIpWhiteList> getAccountIpWhiteList();
    public List<AccountIpWhiteList> getAccountIpWhiteListByAdmin(String adminUsername);
    
    // Size
    public SizeChart addSizeChart(SizeChart sizeChart);
    public SizeChart updateSizeChart(SizeChart sizeChart);
    public SizeChart getSizeChartById(int id);
    public SizeChart getSizeChartById(String adminUsername, int id);
//    public SizeChart getSizeChartByName(String adminUsername, String name);
    public boolean removeSizeChart(SizeChart sizeChart);
    public List<SizeChart> getSizeChartByAdmin(String adminUsername);
    public List<SizeChart> getSizeChartByAdminAndStatus(String adminUsername, String status);
    
    // Brand Group
    public BrandGroup addBrandGroup(BrandGroup brandGroup);
    public BrandGroup updateBrandGroup(BrandGroup brandGroup);
    public BrandGroup getBrandGroupById(int id);
    public BrandGroup getBrandGroupById(String adminUsername, int id);
    public boolean removeBrandGroup(BrandGroup brandGroup);
    public List<BrandGroup> getBrandGroupByAdmin(String adminUsername);
    public List<BrandGroup> getBrandGroupByAdminAndStatus(String adminUsername, String status);
    
    // Manufacturer
    public Manufacturer addManufacturer(Manufacturer manufacturer);
    public Manufacturer updateManufacturer(Manufacturer manufacturer);
    public Manufacturer getManufacturerById(int id);
    public Manufacturer getManufacturerById(String adminUsername, int id);
    public boolean removeManufacturer(Manufacturer manufacturer);
    public List<Manufacturer> getManufacturerByAdmin(String adminUsername);
    public List<Manufacturer> getManufacturerByAdminAndStatus(String adminUsername, String status);
    
    // Tax Slab
    public TaxSlab addTaxSlab(TaxSlab taxSlab);
    public TaxSlab updateTaxSlab(TaxSlab taxSlab);
    public TaxSlab getTaxSlabById(int id);
    public TaxSlab getTaxSlabById(String adminUsername, int id);
    public boolean removeTaxSlab(TaxSlab taxSlab);
    public List<TaxSlab> getTaxSlabByAdmin(String adminUsername);
    public List<TaxSlab> getTaxSlabByAdminAndStatus(String adminUsername, String status);
    
    // Tax Type
    public TaxType addTaxType(TaxType taxType);
    public TaxType updateTaxType(TaxType taxType);
    public TaxType getTaxTypeById(int id);
    public boolean removeTaxType(TaxType taxType);
    public List<TaxType> getTaxType();
    public List<TaxType> getTaxTypeByStatus(String status);
    
    // User Role
    public UserRole addUserRole(UserRole userRole);
    public UserRole updateUserRole(UserRole userRole);
    public UserRole getUserRoleById(int id);
    public UserRole getUserRoleById(String adminUsername, int id);
    public boolean removeUserRole(UserRole userRole);
    public List<UserRole> getUserRoleByAdmin(String adminUsername);
    public List<UserRole> getUserRoleByAdminAndStatus(String adminUsername, String status);
    
    //Vendor
    public Vendor addVendor(Vendor vendor);
    public Vendor updateVendor(Vendor vendor);
    public Vendor getVendorById(int id);
    public Vendor getVendorById(String adminUsername, int id);
    public boolean removeVendor(Vendor vendor);
    public List<Vendor> getVendorByAdmin(String adminUsername);
    public List<Vendor> getVendorByAdminAndStatus(String adminUsername, String status);
    
    //GlobalOffer
    public GlobalOffer addGlobalOffer(GlobalOffer globalOffer);
    public GlobalOffer updateGlobalOffer(GlobalOffer globalOffer);
    public GlobalOffer getGlobalOfferById(int id);
    public GlobalOffer getGlobalOfferById(String adminUsername, int id);
    public GlobalOffer getGlobalOffer(String startDate, String endDate, String adminUsername, String status);
    public boolean removeGlobalOffer(GlobalOffer globalOffer);
    public List<GlobalOffer> getGlobalOfferByAdmin(String adminUsername);
    public List<GlobalOffer> getGlobalOfferByAdminAndStatus(String adminUsername, String status);
    
    //Color Selection
    public ColorSelection addColorSelection(ColorSelection colorSelection);
    public ColorSelection updateColorSelection(ColorSelection colorSelection);
    public ColorSelection getColorSelectionById(int id);
    public boolean removeColorSelection(ColorSelection colorSelection);
    public List<ColorSelection> getColorSelection();
    public List<ColorSelection> getColorSelectionByStatus(String status);
    
    //Register
    public Register addRegister(Register register);
    public Register updateRegister(Register register);
    public Register getRegisterById(int id);
    public boolean removeRegister(Register register);
    public List<Register> getRegister();
    public List<Register> getRegisterByStatus(String status);
}
