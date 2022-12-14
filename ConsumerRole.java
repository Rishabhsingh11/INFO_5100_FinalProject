
package Model.Role;

import Model.Business.EcoSystem;
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Organization.Organization;
import Model.UserAccount.UserAccount;

import UserInterface.Consumer.ConsumerMainJPanel;

import UserInterface.Consumer.ConsumerMainJPanel;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rutu
 */
public class ConsumerRole extends Role {
    
    public ConsumerRole() {
        super(Role.RoleType.Consumer);
    }

    @Override
    public void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame) {

        ConsumerMainJPanel cp = new ConsumerMainJPanel(system, container, userAccount, frame);
        container.add(cp);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.next(container);

    }

    @Override
    public String toString() {
        return Role.RoleType.Consumer.getValue();
    }
}
