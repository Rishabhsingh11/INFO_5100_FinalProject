/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Business;

import Model.Consumer.ConsumerDirectory;
import Model.Enterprise.Enterprise;
import Model.Network.Network;
import Model.Organization.Organization;
import Model.Role.Role;
import Model.Role.SystemAdminRole;
import Model.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author sohamdeshpande
 */
public class EcoSystem extends Organization{
    
    private static EcoSystem ecosys;
    private ArrayList<Network> networkDirectory;
    private ConsumerDirectory consumer;

    public static EcoSystem getInstance() {
        if (ecosys == null) {
            ecosys = new EcoSystem();
        }
        return ecosys;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new SystemAdminRole());
        return roleList;
    }

    private EcoSystem() {
        super(null);
        networkDirectory = new ArrayList<>();
        consumer = new ConsumerDirectory();
    }
    
    public boolean isCityAvabliable(String city) {
        for (Network net : this.networkDirectory) {
            if (net.getCity().equalsIgnoreCase(city)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Network> getNetworkDirectory() {
        return networkDirectory;
    }

    public void setNetworkDirectory(ArrayList<Network> networkDirectory) {
        this.networkDirectory = networkDirectory;
    }

    public Network createNetwork(String state) {
        Network network = new Network(state);
        this.networkDirectory.add(network);
        return network;
    }

    public Network getNetworkByCity(String city) {
        for (Network net : this.networkDirectory) {
            if (net.getCity().equals(city)) {
                return net;
            }
        }
        return null;
    }

    public ConsumerDirectory getConsumer() {
        return this.consumer;
    }
    
    public UserAccount getCustomerAccountByUsername(String username) {
        for(UserAccount account:this.getUserAccountDirectory().getUserAccountDirectory()) {
            if(account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }
        return null;
    }
    
    public Enterprise getEnterpriseById(String id) {
        for (Network net : this.networkDirectory) {
            for (Enterprise en : net.getEnterpriseDirectory().getEnterpriseDirectory()) {
                if(en.getShipcom_id().equals(id)) {
                    return en;
                }
            }
        }
        return null;
    }

    public boolean isUserNameAvaliable(String username) {
        if (this.getUserAccountDirectory().isUsernameValid(username)) {
            for (Network net : this.networkDirectory) {
                for (Enterprise en : net.getEnterpriseDirectory().getEnterpriseDirectory()) {
                    if (en.getUserAccountDirectory().isUsernameValid(username)) {
                        for (Organization or : en.getOrganizationDirectory().getOrganizationDirectory()) {
                            if (!or.getUserAccountDirectory().isUsernameValid(username)) {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
    
    public void removeNetwork(Network net) {
        Network result = null;
        if (!this.networkDirectory.isEmpty()) {
            for (Network n : networkDirectory) {
                if (net.getNetwork_id().equalsIgnoreCase(n.getNetwork_id())) {
                    result = n;
                }
            }
            networkDirectory.remove(result);
        }
    }
}
