
package Model.Organization;

import Model.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author risha
 */
public class OrganizationDirectory {
    private ArrayList<Organization> organizationDirectory;
    
    public OrganizationDirectory() {
        this.organizationDirectory = new ArrayList<>();
    }
    
    public ArrayList<Organization> getOrganizationDirectory() {
        return this.organizationDirectory;
    }
    
    public Organization getTypicalOrganization(Type type) {
        if (type.equals(Type.Courier)) {
            for (Organization or:this.organizationDirectory) {
                if (or.getOrg_name().equalsIgnoreCase(Type.Courier.getValue())) {
                    return or;
                }
            }
        }
        if (type.equals(Type.MartSupervisor)) {
            for (Organization or:this.organizationDirectory) {
                if (or.getOrg_name().equalsIgnoreCase(Type.MartSupervisor.getValue())) {
                    return or;
                }
            }
        }
        if (type.equals(Type.RestaurantSupervisor)) {
            for (Organization or:this.organizationDirectory) {
                if (or.getOrg_name().equalsIgnoreCase(Type.RestaurantSupervisor.getValue())) {
                    return or;
                }
            }
        }
        if (type.equals(Type.ShipmentSupervisor)) {
            for (Organization or:this.organizationDirectory) {
                if (or.getOrg_name().equalsIgnoreCase(Type.ShipmentSupervisor.getValue())) {
                    return or;
                }
            }
        }
        if (type.equals(Type.Chef)) {
            for (Organization or:this.organizationDirectory) {
                if (or.getOrg_name().equalsIgnoreCase(Type.Chef.getValue())) {
                    return or;
                }
            }
        }
        return null;
    }
}
