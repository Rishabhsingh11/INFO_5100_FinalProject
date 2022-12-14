/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Network;

import Model.Enterprise.Delivery.ShipmentCompany;
import Model.Enterprise.Enterprise;
import Model.Enterprise.EnterpriseDirectory;
import Model.Enterprise.Inventory.Inventory;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Enterprise.Mart.Mart;
import java.util.ArrayList;

/**
 *
 * @author rutu
 */

public class Network {
    private String network_id;
    private EnterpriseDirectory enterpriseDirectory;
    private String city;
    
    private static int counter = 0;
    
    public Network(String city) {
        enterpriseDirectory = new EnterpriseDirectory();
        this.city = city;
        this.network_id = "Network" + counter;
        counter++;
    }
    
    public String getNetwork_id(){
        return this.network_id;
    }
    
    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public EnterpriseDirectory getEnterpriseDirectory() {
        return enterpriseDirectory;
    }
     
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public ShipmentCompany createDeliveryCompany(String name, String address, String phone) {
        ShipmentCompany enter = new ShipmentCompany(name, address, phone);
        enter.createOrganizations();
        this.enterpriseDirectory.getEnterpriseDirectory().add(enter);
        return enter;
    }
    
    public Restaurant createRestaurant(String name, String address, String phone) {
        Restaurant res = new Restaurant(name, address, phone);
        res.createOrganizations();
        this.enterpriseDirectory.getEnterpriseDirectory().add(res);
        return res;
    }
    
    public Inventory createInventory(String name, String address, String phone) {
        Inventory inventory = new Inventory(name, address, phone);
        inventory.createOrganizations();
        this.enterpriseDirectory.getEnterpriseDirectory().add(inventory);        
        return inventory;
    }
    
    public Mart createMart(String name, String address, String phone) {
        Mart store = new Mart(name, address, phone);
        store.createOrganizations();
        this.enterpriseDirectory.getEnterpriseDirectory().add(store);
        return store;
    }
    
    public ArrayList<Mart> getStoreList() {
        ArrayList<Mart> result = new ArrayList<>();
        for (Enterprise en:this.enterpriseDirectory.getEnterpriseDirectory()) {
            if (en instanceof Mart) {
                Mart store = (Mart) en;
                result.add(store);
            }
        }
        return result;
    }
    
    public ArrayList<Restaurant> getRestaurantList() {
        ArrayList<Restaurant> result = new ArrayList<>();
        for (Enterprise en:this.enterpriseDirectory.getEnterpriseDirectory()) {
            if (en instanceof Restaurant) {
                Restaurant res = (Restaurant) en;
                result.add(res);
            }
        }
        return result;
    }
    
    public ArrayList<ShipmentCompany> getDeliveryCompanyList() {
        ArrayList<ShipmentCompany> result = new ArrayList<>();
        for (Enterprise en:this.enterpriseDirectory.getEnterpriseDirectory()) {
            if (en instanceof ShipmentCompany) {
                ShipmentCompany del = (ShipmentCompany) en;
                result.add(del);
            }
        }
        return result;
    }
    
    public ArrayList<Inventory> getInventoryList() {
        ArrayList<Inventory> result = new ArrayList<>();
        for (Enterprise en:this.enterpriseDirectory.getEnterpriseDirectory()) {
            if (en instanceof Inventory) {
                Inventory del = (Inventory) en;
                result.add(del);
            }
        }
        return result;
    }
    
}
