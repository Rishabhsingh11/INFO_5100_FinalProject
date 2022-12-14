/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Business;

import Model.Consumer.Consumer;
import Model.Employee.Employee;
import Model.Enterprise.Delivery.ShipmentCompany;
import Model.Enterprise.Inventory.Inventory;
import Model.Enterprise.Inventory.InventoryItem;
import Model.Network.Network;
import Model.Organization.RestaurantSupervisorOrganization;
import Model.Organization.Organization;
import Model.Enterprise.Restaurant.Menu;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Enterprise.Mart.Product;
import Model.Enterprise.Mart.Mart;
import Model.Organization.CourierOrganization;
import Model.Organization.InventorySupervisorOrganization;
import Model.Organization.MartSupervisorOrganization;
import Model.Organization.ShipmentSupervisorOrganization;
import Model.Role.BossRole;
import Model.Role.DeliveryManRole;
import Model.Role.InventorySupervisorRole;
import Model.Role.MartSupervisorRole;
import Model.Role.RestaurantSupervisorRole;
import Model.Role.ShipmentSupervisorRole;
import Model.Role.SystemAdminRole;
import Model.UserAccount.UserAccount;

/**
 *
 * @author sohamdeshpande
 */
public class SystemConfiguration {
    public static EcoSystem configure() {

        EcoSystem system = EcoSystem.getInstance();

        //Create a network
        //create an enterprise
        //initialize some organizations
        //have some employees 
        //create user account
        // System manager, belongs to SYSTEM
        Employee emp1 = system.getEmployeeDirectory().createEmployee("Rishabh", "Singh", "8576938587", "rishabhsingh1894@gmail.com");
        UserAccount user1 = system.getUserAccountDirectory().createEmployeeAccount("sysadmin", "sysadmin", new SystemAdminRole(), emp1);

        // Consumer, belongs to SYSTEM
        Consumer c1 = system.getConsumer().createCustomer("Rutuja", "More", "rutu@gmail.com", "7838832333");
        UserAccount user2 = system.getUserAccountDirectory().createConsumerAccount("rutu", "12345", c1);

        Consumer c2 = system.getConsumer().createCustomer("Soham", "Deshpande", "Sohamdesh@hotmail.com", "9838332334");
        UserAccount user3 = system.getUserAccountDirectory().createConsumerAccount("soham", "12345", c2);

        // MA NETWORK 
        Network network1 = system.createNetwork("Boston");
        network1.setNetwork_id("Boston");
        Network network2 = system.createNetwork("Worchester");
        network2.setNetwork_id("Worchester");

        // BOSTON Enterprise with organiztions created
        ShipmentCompany sc1 = network1.createDeliveryCompany("Boston Delivery Company", "110 Allstate, Boston, MA 02125", "(617) 553-5900");
        sc1.setShipcom_description("This is delivery company located at Boston.");
        sc1.setShipcom_id("Delivery");
        sc1.setPath("Images/DeliveryCompanyCut/default.png");
        Employee b1 = sc1.getEmployeeDirectory().createEmployee("Rishabh", "Singh", "773383812", "rsingh@gmail.com");
        UserAccount user4 = sc1.getUserAccountDirectory().createEmployeeAccount("risha", "12345", new BossRole(), b1);
        
        
        
        // BOSTON Delivery Company Organization
        ShipmentSupervisorOrganization sup1 = (ShipmentSupervisorOrganization) sc1.getOrganizationDirectory().getTypicalOrganization(Organization.Type.ShipmentSupervisor);
        
        Employee emp2 = sup1.getEmployeeDirectory().createEmployee("Manager1", "Manager2", "1234567890", "manager1@demo.com");
        UserAccount user5 = sup1.getUserAccountDirectory().createEmployeeAccount("m", "m", new ShipmentSupervisorRole(), emp2);

        
        CourierOrganization cou1 = (CourierOrganization) sc1.getOrganizationDirectory().getTypicalOrganization(Organization.Type.Courier);
        Employee emp3 = sup1.getEmployeeDirectory().createEmployee("Delivery", "Man", "1111", "deliveryman@demo.com");
        UserAccount user6 = sup1.getUserAccountDirectory().createEmployeeAccount("d", "d", new DeliveryManRole(), emp3);

        Employee emp4 = cou1.getEmployeeDirectory().createEmployee("Delivery", "Man", "1111", "deliveryman1@demo.com");
        UserAccount user7 = cou1.getUserAccountDirectory().createEmployeeAccount("dd", "dd", new DeliveryManRole(), emp4);

        
        
        // BOSTON Restaurant List
        Restaurant restaurant1 = network1.createRestaurant("Row 34", "383 Congress St, Boston, MA 02210", "(617) 553-5900");
        restaurant1.setType(Restaurant.RestaurantCategory.American);
        restaurant1.setShipcom_id("Row");
        restaurant1.setPath("Images/RestaurantCut/default.png");
        restaurant1.setOut_description("This stylish brick-&-wood eatery serves an extensive oyster menu plus fish entrees & craft beers.");
        Menu menu1 = new Menu(restaurant1, "Dash1", 20);
        Menu menu2 = new Menu(restaurant1, "Dash2", 30);
        Menu menu3 = new Menu(restaurant1, "Dash3", 27.6);
        restaurant1.addToMenu(menu1);
              
        restaurant1.addToMenu(menu2);
        restaurant1.addToMenu(menu3);
        
        Employee emp5 = restaurant1.getEmployeeDirectory().createEmployee("Row34", "Boss", "12344", "boss@row34.com");
        UserAccount user8 = restaurant1.getUserAccountDirectory().createEmployeeAccount("row34", "row34", new BossRole(), emp5);
        
        RestaurantSupervisorOrganization sup2 = (RestaurantSupervisorOrganization) restaurant1.getOrganizationDirectory().getTypicalOrganization(Organization.Type.RestaurantSupervisor);
        Employee emp6 = sup2.getEmployeeDirectory().createEmployee("Manager", "Manager", "111", "manager@demo.com");
        UserAccount user9 = sup2.getUserAccountDirectory().createEmployeeAccount("rm", "rm", new RestaurantSupervisorRole(), emp6);

        Restaurant restaurant2 = network1.createRestaurant("Legal Harborside", "270 Northern Ave, Boston, MA 02210", "(617) 477-2900");
        restaurant2.setType(Restaurant.RestaurantCategory.American);
        restaurant2.setShipcom_id("legal");
        restaurant2.setPath("Images/RestaurantCut/default.png");
        restaurant2.setOut_description("Specializing in upmarket seafood, this contemporary chain also serves steaks & cocktails.");
        Menu menu4 = new Menu(restaurant2, "D1", 10);
        Menu menu5 = new Menu(restaurant2, "D2", 14);
        Menu menu6 = new Menu(restaurant2, "D3", 11.99);
        restaurant2.addToMenu(menu4);
        restaurant2.addToMenu(menu5);
        restaurant2.addToMenu(menu6);
        
        Employee emp7 = restaurant2.getEmployeeDirectory().createEmployee("Legal", "Boss", "222", "boss@demo.com");
        UserAccount user10 = restaurant2.getUserAccountDirectory().createEmployeeAccount("legal", "legal", new BossRole(), emp7);
        
        RestaurantSupervisorOrganization sup3 = (RestaurantSupervisorOrganization) restaurant2.getOrganizationDirectory().getTypicalOrganization(Organization.Type.RestaurantSupervisor);
        Employee emp8 = sup3.getEmployeeDirectory().createEmployee("Manager", "Manager", "222", "manager@demo.com");
        UserAccount user11 = sup3.getUserAccountDirectory().createEmployeeAccount("lm", "lm", new RestaurantSupervisorRole(), emp8);
        
        
        
        
        // Boston Mart List

        Mart mart1 = network1.createMart("Trader Joe", "834 Boylston St, Boston, MA 02115", "(617) 375-1010");
        mart1.setShipcom_id("tarder");

        mart1.setPath("Images/StoreCut/default.png");
        mart1.setType(Mart.MartType.GroceryStore);
        mart1.setOut_description("Trader Joe's is an American chain of grocery stores headquartered in Monrovia, California. The chain has over 530 stores across the United States");
        Product p1 = new Product(mart1, "Milk", 3.45);
        Product p2 = new Product(mart1, "Eggs", 4.49);
        Product p3 = new Product(mart1, "Chicken", 5.87);
        Product p4 = new Product(mart1, "Bananas", 0.13);
        Product p5 = new Product(mart1, "Cabbage", 1.0);
        Product p6 = new Product(mart1, "Tomatos", 0.39);
        Product p7 = new Product(mart1, "Onions", 0.56);
        Product p8 = new Product(mart1, "Yoghurt", 4.69);
        
        mart1.addProductToList(p1);
        mart1.addProductToList(p2);
        mart1.addProductToList(p3);
        mart1.addProductToList(p4);
        mart1.addProductToList(p5);
        mart1.addProductToList(p6);
        mart1.addProductToList(p7);
        mart1.addProductToList(p8);
        
        Employee emp9 = mart1.getEmployeeDirectory().createEmployee("Hugo", "Lorris", "3889398398", "hugo@trader.com");
        UserAccount user12 = mart1.getUserAccountDirectory().createEmployeeAccount("hugo", "hugo123", new BossRole(), emp9);
        
        MartSupervisorOrganization sup4 = (MartSupervisorOrganization) mart1.getOrganizationDirectory().getTypicalOrganization(Organization.Type.MartSupervisor);
        Employee emp10 = sup4.getEmployeeDirectory().createEmployee("Lacy", "Myers", "7488442332", "lacy@trader.com");
        UserAccount user13 = sup4.getUserAccountDirectory().createEmployeeAccount("lacy", "lacy123", new MartSupervisorRole(), emp10);
        
        
        Inventory inv1 = network1.createInventory("Aditi", "856 Boylstyon Ave, Boston, MA 02210", "(617) 477-2984");
        inv1.setInventype(Inventory.InventoryType.Rawgoods);
        inv1.setShipcom_id("Aditi");
        inv1.setPath("Images/RestaurantCut/default.png");
        inv1.setOut_description("Specializing in upmarket seafood, this contemporary chain also serves steaks & cocktails.");
        InventoryItem i1 = new InventoryItem(inv1, "I1", 10);
        InventoryItem i2 = new InventoryItem(inv1, "I1", 10);
        InventoryItem i3 = new InventoryItem(inv1, "I1", 10);
        inv1.addInventoryItem(i1);
        inv1.addInventoryItem(i2);
        inv1.addInventoryItem(i3);
       
        
        // Worchester Restaurant List
        Restaurant restaurant3 = network2.createRestaurant("Deadhorse hill", "281 Main St, Worchester, MA 01608", "7744207107");
        restaurant3.setType(Restaurant.RestaurantCategory.American);
        restaurant3.setShipcom_id("deadhorse");
        restaurant3.setPath("Images/RestaurantCut/default.png");
        restaurant3.setOut_description("A hilltop hotel houses a rustic-chic New American dining room with daytime cafe and coffee bar.");
        Menu menu7 = new Menu(restaurant3, "Grilled Cheese", 9);
        Menu menu8 = new Menu(restaurant3, "Smoked turkey Breast", 12);
        Menu menu9 = new Menu(restaurant3, "French Dip", 14);
        restaurant3.addToMenu(menu7);
        restaurant3.addToMenu(menu8);
        restaurant3.addToMenu(menu9);
        
        
        
        Employee emp11 = restaurant3.getEmployeeDirectory().createEmployee("Sam", "Alfred", "8398983923", "sam@deadhorse.com");
        UserAccount user14 = restaurant3.getUserAccountDirectory().createEmployeeAccount("sam", "sam123", new BossRole(), emp11);
        
        RestaurantSupervisorOrganization sup5 = (RestaurantSupervisorOrganization) restaurant3.getOrganizationDirectory().getTypicalOrganization(Organization.Type.RestaurantSupervisor);
        Employee emp12 = sup5.getEmployeeDirectory().createEmployee("Rachel", "Green", "8988399333", "rachel@deadhorse.com");
        UserAccount user15 = sup5.getUserAccountDirectory().createEmployeeAccount("rachel", "rachel123", new RestaurantSupervisorRole(), emp12);
        return system;
    }

}
