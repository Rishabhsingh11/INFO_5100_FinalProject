
package Model.Role;

import Model.Role.MartSupervisorRole;
import Model.Role.Role.RoleType;

/**
 *
 * @author rutu
 */
public class RoleSelection {
    
    public static Role createRole(RoleType type) {
        if(type.equals(RoleType.Courier)) {
            return new DeliveryManRole();
        }
        if (type.equals(RoleType.MartSupervisor)) {
            return new MartSupervisorRole();
        }
        if (type.equals(RoleType.RestaurantSupervisor)) {
            return new RestaurantSupervisorRole();
        }
        if (type.equals(RoleType.ShipmentSupervisor)) {
            return new ShipmentSupervisorRole();
        }
        if(type.equals(RoleType.Boss)) {
            return new BossRole();
        }
        if(type.equals(RoleType.SystemAdmin)) {
            return new SystemAdminRole();
        }
        if(type.equals(RoleType.Chef)) {
            return new ChefRole();
        }
        return null;
    }
}
