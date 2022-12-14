/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Role;

import Model.Business.EcoSystem;
import Model.Enterprise.Enterprise;
import Model.Enterprise.Inventory.Inventory;
import Model.Network.Network;
import Model.UserAccount.UserAccount;
import UserInterface.Inventory.InventoryManagerMainJPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author risha
 */
public class InventorySupervisorRole extends Role
{

    public InventorySupervisorRole() 
    {
        super(Role.RoleType.InventoryManager);
    }
    
    @Override
    public String toString() {
        return Role.RoleType.InventoryManager.getValue();
    }
    
    @Override
    public void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame) {
        if (en instanceof Inventory) {
            InventoryManagerMainJPanel cp = new InventoryManagerMainJPanel(system, container, net, en, userAccount, frame, this);
            container.add(cp);
        }
        CardLayout layout = (CardLayout) container.getLayout();
        layout.next(container);
    }
    
}
