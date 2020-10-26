/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.service;

import co.admis.model.Organization;
import co.admis.model.OrganizationRole;
import java.util.List;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public interface OrganizationService {
    public Organization addOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public boolean removeOrganization(Organization organization);
    public Organization getOrganizationById(int id);
    public List<Organization> getOrganizationsList(); 
    public int getOrganizationIdByAdminUsername(String adminUsername);
    public Organization getOrganizationByAdminUsername(String adminUsername);
    
    //Organiaztion Roles
    public List<OrganizationRole> getOrganizationRoles();
    public OrganizationRole addOrganizationRole(OrganizationRole organizationRole);
    public OrganizationRole updateOrganizationRole(OrganizationRole organizationRole);
    public boolean removeOrganizationRole(OrganizationRole organizationRole);
    public OrganizationRole getOrganizationRoleById(int id);
}
