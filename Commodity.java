/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise;

/**
 *
 * @author risha
 */
public class Commodity {
    private String com_name;
    private double com_price;

    public Commodity(String name, double price) {
        this.com_name = name;
        this.com_price = price;
    }

    public String getCom_name() {
        return this.com_name;
    }
    
    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public double getCom_price() {
        return this.com_price;
    }
    
    public void setCom_price(double com_price) {
        this.com_price = com_price;
    }
    
    @Override
    public String toString() {
        return this.com_name;
    }
}
