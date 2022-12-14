
package Model.UserAccount;

import Model.Role.Role;
import Model.WorkQueue.WorkQueue;

/**
 *
 * @author sohamdeshpande
 */
public abstract class UserAccount {
    
    private int useracc_id;
    private String username;
    private String password;
    private Role role;
    private WorkQueue workQ;
    private static int userid_counter = 0;

    public UserAccount(String username, String password, Role role) {
        this.useracc_id = userid_counter;
        userid_counter++;
        this.username = username;
        this.password = password;
        this.role = role;
        this.workQ = new WorkQueue();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WorkQueue getWorkQueue() {
        return this.workQ;
    }
    
    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return this.username;
    }
}
