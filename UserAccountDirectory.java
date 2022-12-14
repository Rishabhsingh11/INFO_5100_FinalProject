
package Model.UserAccount;

import Model.Consumer.Consumer;
import Model.Employee.Employee;
import Model.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author sohamdeshpande
 */
public class UserAccountDirectory {
    
    private ArrayList<UserAccount> userAccountDirectory;

    public UserAccountDirectory() {
        this.userAccountDirectory = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountDirectory() {
        return this.userAccountDirectory;
    }

    public UserAccount authenticateUser(String username, String password) {
        UserAccount account = null;
        for (UserAccount ua : userAccountDirectory) {
            if (ua.getUsername().equalsIgnoreCase(username) && ua.getPassword().equals(password)) {
                account = ua;
            }
        }
        return account;
    }

    public boolean isUsernameValid(String username) {
        if (userAccountDirectory.isEmpty()) {
            return true;
        }
        for (UserAccount ua : userAccountDirectory) {
            if (ua.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    public EmployeeAccount createEmployeeAccount(String username, String password, Role role, Employee em) {
        EmployeeAccount ua = new EmployeeAccount(username, password, role, em);
        userAccountDirectory.add(ua);
        return ua;
    }

    public ConsumerAccount createConsumerAccount(String username, String password, Consumer customer) {
        ConsumerAccount ua = new ConsumerAccount(username, password, customer);
        userAccountDirectory.add(ua);
        return ua;
    }

    public void addAccount(UserAccount account) {
        userAccountDirectory.add(account);
    }

    public ArrayList<EmployeeAccount> toEmployeeAccounts() {
        ArrayList<EmployeeAccount> result = new ArrayList<>();
        for (UserAccount ua : this.userAccountDirectory) {
            EmployeeAccount ea = (EmployeeAccount) ua;
            result.add(ea);
        }
        return result;
    }

    public ArrayList<UserAccount> searchCustomerByOverall(String key) {
        ArrayList<UserAccount> result = new ArrayList<>();
        if (!userAccountDirectory.isEmpty()) {
            for (UserAccount u : this.userAccountDirectory) {
                if (u instanceof ConsumerAccount) {
                    ConsumerAccount c = (ConsumerAccount) u;
                    if (c.getUsername().toLowerCase().contains(key.toLowerCase())) {
                        if(!result.contains(c)) {
                            result.add(c);
                        }
                    }
                    if (c.getConsumer().getFullname().toLowerCase().contains(key.toLowerCase())) {
                        if(!result.contains(c)) {
                            result.add(c);
                        }
                    }
                    if (c.getConsumer().getMobile_no().toLowerCase().contains(key.toLowerCase())) {
                        if(!result.contains(c)) {
                            result.add(c);
                        }
                    }
                    if (c.getConsumer().getEmail().toLowerCase().contains(key.toLowerCase())) {
                        if(!result.contains(c)) {
                            result.add(c);
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public void removeAccount(UserAccount account) {
        if (this.userAccountDirectory.contains(account)) {
            this.userAccountDirectory.remove(account);
        }
    }
}
