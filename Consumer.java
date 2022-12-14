/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Consumer;

import Model.WorkQueue.OrderRequest;
import java.util.ArrayList;

/**
 *
 * @author rutu
 */
public class Consumer {
    private int uid;
    private String firstname;
    private String lastname;
    private String fullname;
    private String address;
    private String mobile_no;
    private String email;
    private static int uidcounter = 0;

    public Consumer(String firstName, String lastName, String email, String phone) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.fullname = firstName + " " + lastName;
        this.address = "";
        this.email = email;
        this.mobile_no = phone;
        uid = uidcounter;
        uidcounter++;
    }

    public int getUid() {
        return uid;
    }

    public void setName(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
            
    public String getFullname() {
        return this.firstname + " " + this.lastname;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String addr) {
        this.address = addr;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile_no() {
        return this.mobile_no;
    }
    
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }
}
