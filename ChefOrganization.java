/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Organization;

import Model.Role.ChefRole;
import Model.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author sohamdeshpande
 */
public class ChefOrganization extends Organization {
    public ChefOrganization() {
        super(Organization.Type.Chef.getValue());
    }
    @Override 
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new ChefRole());
        return roles;
    }
}
