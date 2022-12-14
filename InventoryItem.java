/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise.Inventory;

import Model.Enterprise.Commodity;

/**
 *
 * @author risha
 */
public class InventoryItem extends Commodity
{
  private Inventory inventory;
//    private String name;
//    private double price;

    public InventoryItem(Inventory inventory, String name, double price) {
        super(name, price);
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }  
}
