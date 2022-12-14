
package Model.Enterprise;

import Model.Employee.Employee;
import Model.Organization.Organization;
import Model.Organization.OrganizationDirectory;
import Model.Role.Role;
import Model.UserAccount.EmployeeAccount;
import Model.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author risha
 */
public abstract class Enterprise extends Organization {
    
    private OrganizationDirectory org;
    private static int entidcounter = 0;

    public Enterprise(String name) {
        super(name);
        this.org = new OrganizationDirectory();
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return org;
    }

    public void removeEmployeeAccount(UserAccount account) {
        EmployeeAccount ac = (EmployeeAccount) account;
        Employee em = ac.getEmployee();

        UserAccount ac1 = null;
        for (UserAccount ua : this.getUserAccountDirectory().getUserAccountDirectory()) {
            if (ua.getUsername().equalsIgnoreCase(account.getUsername())) {
                ac1 = ua;
            }
        }
        if (ac1 != null) {
            this.getUserAccountDirectory().getUserAccountDirectory().remove(ac1);
            return;
        }
        for (Organization or : org.getOrganizationDirectory()) {
            for (UserAccount ua : or.getUserAccountDirectory().getUserAccountDirectory()) {
                if (ua.getUsername().equalsIgnoreCase(ac.getUsername())) {
                    ac1 = ua;
                }
            }
            if (ac1 != null) {
                or.getUserAccountDirectory().getUserAccountDirectory().remove(ac1);
                return;
            }
        }
    }

    public void removeEmployee(Employee employee) {

        Employee em = null;
        for (Employee e : this.getEmployeeDirectory().getEmployeeDirectory()) {
            if (e.getEmpid() == employee.getEmpid()) {
                em = e;
            }
        }
        if (em != null) {
            this.getEmployeeDirectory().getEmployeeDirectory().remove(em);
            return;
        }
        for (Organization or : org.getOrganizationDirectory()) {
            for (Employee e : or.getEmployeeDirectory().getEmployeeDirectory()) {
                if (e.getEmpid() == employee.getEmpid()) {
                    em = e;
                }
            }
            if (em != null) {
                or.getEmployeeDirectory().getEmployeeDirectory().remove(em);
                return;
            }
        }
    }

    public abstract String getShipcom_id();
    
    public abstract void setShipcom_id(String id);

    public abstract void createOrganizations();

    @Override
    public ArrayList<Role> getSupportedRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return this.getOrg_name();
    }
}
