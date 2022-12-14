/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Organization;

import Model.Role.MartSupervisorRole;
import Model.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author rutu
 */
public class MartSupervisorOrganization extends Organization {
    
    public MartSupervisorOrganization() {
        super(Organization.Type.MartSupervisor.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new MartSupervisorRole());
        return roles;
    }
    
}
