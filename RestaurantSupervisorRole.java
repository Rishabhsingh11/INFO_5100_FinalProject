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

import UserInterface.RestaurantSupervisor.RestaurantSupervisorMainJPanel;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rutu
 */
public class RestaurantSupervisorRole extends Role{
    
    public RestaurantSupervisorRole() {
        super(Role.RoleType.RestaurantSupervisor);
    }

    @Override
    public String toString() {
        return Role.RoleType.RestaurantSupervisor.getValue();
    }

    @Override
    public void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame) {



        if (en instanceof Restaurant) {
            RestaurantSupervisorMainJPanel cp = new RestaurantSupervisorMainJPanel(system, container, net, en, userAccount, frame, this);
            container.add(cp);
        }
        

        CardLayout layout = (CardLayout) container.getLayout();
        layout.next(container);
    }
}
