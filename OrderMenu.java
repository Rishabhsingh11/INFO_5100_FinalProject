/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import Model.Enterprise.Commodity;
import Model.Enterprise.Restaurant.Menu;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Enterprise.Outlet;
import java.math.BigDecimal;

/**
 *
 * @author rutu
 */
public class OrderMenu extends CommodityOrder{
     private Restaurant restaurant;

    public OrderMenu(Outlet shopmodel, Commodity item, int quantity) {
        super(shopmodel, item, quantity);
        this.restaurant = (Restaurant) shopmodel;
    }

    public Restaurant getRestaurant() {
        return (Restaurant) this.getShopModel();
    }

    @Override
    public Outlet getShopModel() {
        return this.restaurant;
    }
}
