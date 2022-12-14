
package Model.Role;

import Model.Business.EcoSystem;
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.UserAccount.UserAccount;
import UserInterface.SystemAdmin.SystemAdminMainJPanel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author rutu
 */
public class SystemAdminRole extends Role {
    
    public SystemAdminRole() {
        super(Role.RoleType.SystemAdmin);
    }

    @Override
    public void createWorkArea(EcoSystem system, JPanel container, UserAccount userAccount, Network net, Enterprise en, JFrame frame) {
        SystemAdminMainJPanel cp = new SystemAdminMainJPanel(system, container, userAccount, frame);
        container.add(cp);
        CardLayout layout = (CardLayout) container.getLayout();
        layout.next(container);
    }

    @Override
    public String toString() {
        return Role.RoleType.SystemAdmin.getValue();
    }
}
