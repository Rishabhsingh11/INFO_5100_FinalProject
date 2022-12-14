
package Model.Organization;

import Model.Role.ShipmentSupervisorRole;
import Model.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author rutu
 */
public class ShipmentSupervisorOrganization extends Organization {

    public ShipmentSupervisorOrganization() {
        super(Organization.Type.ShipmentSupervisor.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new ShipmentSupervisorRole());
        return roles;
    }
}
