/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import Model.Enterprise.Commodity;
import Model.Enterprise.Outlet;
import Model.Enterprise.Outlet.OutletType;
import java.math.BigDecimal;

/**
 *
 * @author rutu
 */

public abstract class CommodityOrder {
    private Commodity item;
    private int quantity;
    private double totalPrice;
    private Outlet outletmodel;
    
    public CommodityOrder(Outlet shopmodel, Commodity item, int quantity) {
        this.outletmodel = shopmodel;
        this.item = item;
        this.quantity = quantity;
    }

    public Commodity getItem() {
        return this.item;
    }

    public void setDash(Commodity item) {
        this.item = item;
    }

    public abstract Outlet getShopModel();

    public void setRestaurant(Outlet shopmodel) {
        this.outletmodel = shopmodel;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int q) {
        this.quantity = q;
        BigDecimal bd = new BigDecimal(item.getCom_price() * q);
        this.totalPrice = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getTotalPrice() {
        BigDecimal bd = new BigDecimal(item.getCom_price() * quantity);
        this.totalPrice = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return this.totalPrice;
    }

    @Override
    public String toString() {
        return this.item.getCom_name();
    }
}
