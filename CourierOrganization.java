/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Organization;

import Model.Role.DeliveryManRole;
import Model.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author risha
 */
public class CourierOrganization extends Organization {
    
    public CourierOrganization() {
        super(Organization.Type.Courier.getValue());
    }

    @Override 
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new DeliveryManRole());
        return roles;
    }
    
}
