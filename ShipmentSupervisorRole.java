/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Role;

import Model.Business.EcoSystem;
import Model.Enterprise.Delivery.ShipmentCompany;
import Model.Enterprise.Enterprise;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Enterprise.Mart.Mart;
import Model.Network.Network;
import Model.UserAccount.UserAccount;
import UserInterface.ShipmentCompany.Supervisor.ShipmentCompanySupervisorMainJPanel;
import UserInterface.RestaurantSupervisor.RestaurantSupervisorMainJPanel;
import UserInterface.MartSupervisor.MartSupervisorMainJPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Model.Enterprise.Outlet.OutletType;
import Model.Validation.Validation;

/**
 *
 * @author rutu
 */
public class ShipmentSupervisorRole extends Role {

    public ShipmentSupervisorRole() {
        super(Role.RoleType.ShipmentSupervisor);
    }

    @Override
    public String toString() {
        return Role.RoleType.ShipmentSupervisor.getValue();
    }

    @Override
    public void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame) {

        if (en instanceof ShipmentCompany) {
            ShipmentCompanySupervisorMainJPanel cp = new ShipmentCompanySupervisorMainJPanel(system, container, net, en, userAccount, frame, this);
            container.add(cp);
        }

        CardLayout layout = (CardLayout) container.getLayout();
        layout.next(container);
    }
}
