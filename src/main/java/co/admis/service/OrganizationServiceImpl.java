/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.dao.OrganizationDao;
import co.admis.model.Organization;
import co.admis.model.OrganizationRole;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    OrganizationDao organizationDao;
    
    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDao.addOrganization(organization);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public boolean removeOrganization(Organization organization) {
        return organizationDao.removeOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationDao.getOrganizationById(id);
    }

    @Override
    public List<Organization> getOrganizationsList() {
        return organizationDao.getOrganizationsList();
    }

    @Override
    public int getOrganizationIdByAdminUsername(String adminUsername) {
        return organizationDao.getOrganizationIdByAdminUsername(adminUsername);
    }

    @Override
    public List<OrganizationRole> getOrganizationRoles() {
        return organizationDao.getOrganizationRoles();
    }

    @Override
    public OrganizationRole addOrganizationRole(OrganizationRole organizationRole) {
        return organizationDao.addOrganizationRole(organizationRole);
    }

    @Override
    public OrganizationRole updateOrganizationRole(OrganizationRole organizationRole) {
        return organizationDao.updateOrganizationRole(organizationRole);
    }

    @Override
    public boolean removeOrganizationRole(OrganizationRole organizationRole) {
        return organizationDao.removeOrganizationRole(organizationRole);
    }

    @Override
    public OrganizationRole getOrganizationRoleById(int id) {
        return organizationDao.getOrganizationRoleById(id);
    }

    @Override
    public Organization getOrganizationByAdminUsername(String adminUsername) {
        return organizationDao.getOrganizationByAdminUsername(adminUsername);
    }
    
}
