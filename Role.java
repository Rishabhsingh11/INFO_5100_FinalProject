
package Model.Role;

import Model.Business.EcoSystem;
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Organization.Organization;
import Model.Organization.Organization.Type;
import Model.UserAccount.UserAccount;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rutu
 */
public abstract class Role {
    private RoleType type;
    
    public enum RoleType{
        Boss("Boss"),
        
        MartSupervisor("Mart Supervisor"),
        RestaurantSupervisor("Restaurant Supervisor"),
        ShipmentSupervisor("Shipment Supervisor"),
        Consumer("Consumer"),
        Courier("Courier"),
        SystemAdmin("System Admin"),
        Chef("Chef"),
        InventoryManager("Inventory Manager");
        
        private String value;
        
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        
        public Type getOrganizationType() {
            if (this.value.equals( ShipmentSupervisor.getValue())) {
                return Type. ShipmentSupervisor;
            }
             if (this.value.equals(RestaurantSupervisor.getValue())) {
                return Type.RestaurantSupervisor;
            }
              if (this.value.equals(MartSupervisor.getValue())) {
                return Type.MartSupervisor;
            }
            if (this.value.equals(Courier.getValue())) {
                return Type.Courier;
            }
            if (this.value.equals(InventoryManager.getValue())) {
                return Type.InventoryManager;
            }
            else {
                return null;
            }
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public Role(RoleType type) {
        this.type = type;
    }
    
    public RoleType getRoleType() {
        return this.type;
    }
    
    @Override
    public abstract String toString();
    
    public abstract void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame);
}
