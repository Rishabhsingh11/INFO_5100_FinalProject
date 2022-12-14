/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import Model.Enterprise.Restaurant.Restaurant;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author rutu
 */
public class ShoppingBasket {
    
    private ArrayList<CommodityOrder> commodityDirectory;
    private double totalPrice;

    public ShoppingBasket() {
        this.commodityDirectory = new ArrayList<>();
        BigDecimal bd = new BigDecimal(getTotalPrice());
        this.totalPrice = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public ArrayList<CommodityOrder> getCommodityDirectory() {
        return this.commodityDirectory;
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for (CommodityOrder d : commodityDirectory) {
            totalPrice = totalPrice + d.getTotalPrice();
        }
        return totalPrice;
    }
    
    public void addToBasket(CommodityOrder order) {
        this.commodityDirectory.add(order);
    }
    
    public boolean isBasketEmpty() {
        return commodityDirectory.isEmpty();
    }
    
    public void clearBasket(){
        this.commodityDirectory = new ArrayList<>();
    }
    
}
