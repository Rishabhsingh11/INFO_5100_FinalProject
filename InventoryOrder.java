/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import Model.Enterprise.Commodity;
import Model.Enterprise.Inventory.Inventory;
import Model.Enterprise.Outlet;
import java.math.BigDecimal;

/**
 *
 * @author risha
 */
public class InventoryOrder extends CommodityOrder
{
  
    private Inventory inventory;
    
    public InventoryOrder( Outlet outlet, Commodity commodity, int quantity) 
    {
        super(outlet,commodity,quantity);
        this.inventory = (Inventory) outlet;
    }
     
    public Inventory getInventory()
    {
        return (Inventory) this.getShopModel();
    }

    @Override
    public Outlet getShopModel() {
        return this.inventory;
    }
    
}
