/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Role;

import Model.Business.EcoSystem;
import Model.Enterprise.Enterprise;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Network.Network;
import Model.UserAccount.UserAccount;
import UserInterface.Chef.ChefMainJPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sohamdeshpande
 */
public class ChefRole extends Role{
    public ChefRole() {
        super(Role.RoleType.Chef);
    }

    @Override
    public String toString() {
        return Role.RoleType.Chef.getValue();
}

    @Override
    public void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame) {

            ChefMainJPanel cp = new ChefMainJPanel(system, container, net, en, userAccount, frame, this);
            container.add(cp);
            CardLayout layout = (CardLayout) container.getLayout();
            layout.next(container);
    }
}


