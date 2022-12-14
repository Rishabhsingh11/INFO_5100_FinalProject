/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise;

import Model.Enterprise.Delivery.ShipmentCompany;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Enterprise.Mart.Mart;

/**
 *
 * @author risha
 */
public class EnterpriseType {
    
    public static Enterprise createEnterprise(String name, String address, String phone, String description, String type) {
        if (type.equalsIgnoreCase("Restaurant")) {
            Restaurant r = new Restaurant(name, address, phone);
            r.setOut_description(description);
            r.createOrganizations();
            return r;
        }
        if (type.equalsIgnoreCase("Store")) {
            Mart s = new Mart(name, address, phone);
            s.setOut_description(description);
            s.createOrganizations();
            return s;
        }
        if (type.equalsIgnoreCase("Delivery Company")) {
            ShipmentCompany d = new ShipmentCompany(name, address, phone);
            d.setShipcom_description(description);
            d.createOrganizations();
            return d;
        }
        return null;
    }
}
