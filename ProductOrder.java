/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import Model.Enterprise.Commodity;
import Model.Enterprise.Outlet;
import Model.Enterprise.Mart.Mart;

/**
 *
 * @author rutu
 */
public class ProductOrder extends CommodityOrder {
    private Mart mart;

    public ProductOrder(Outlet shopmodel, Commodity item, int quantity) {
        super(shopmodel, item, quantity);
        this.mart = (Mart) shopmodel;
    }

    public Mart getMart() {
        return this.mart;
    }

    @Override
    public Outlet getShopModel() {
        return this.mart;
    }
}
