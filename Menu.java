/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise.Restaurant;

import Model.Enterprise.Commodity;

/**
 *
 * @author sohamdeshpande
 */
public class Menu extends Commodity {
    
    private Restaurant restaurant;

    public Menu(Restaurant restaurant, String name, double price) {
        super(name, price);
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }
}
