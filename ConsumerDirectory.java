/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import java.util.ArrayList;

/**
 *
 * @author rutu
 */
public class ConsumerDirectory {
    private ArrayList<Consumer> consumerDirectory;

    public ConsumerDirectory() {
        consumerDirectory = new ArrayList<>();
    }

    public ArrayList<Consumer> getConsumerDirectory() {
        return consumerDirectory;
    }
    
    public void addCustomer(String firstName, String lastName, String email, String phone, String address){
        Consumer customer = new Consumer(firstName, lastName, email, phone);
        customer.setName(firstName, lastName);
        customer.setEmail(email);
        customer.setMobile_no(phone);
        customer.setAddress(address);
        consumerDirectory.add(customer);
    }
    
    public Consumer createCustomer(String firstName, String lastName, String email, String phone) {
        Consumer customer = new Consumer(firstName, lastName, email, phone);
        this.consumerDirectory.add(customer);
        return customer;
    }
}
