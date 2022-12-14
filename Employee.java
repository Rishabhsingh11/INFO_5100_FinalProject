/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import Model.Role.Role;

/**
 *
 * @author risha
 */
public class Employee {
    private int empid;
    private String firstname;
    private String lastname;
    private String fullname;
    private String mobile_no;
    private String email_id;
    private static int empidcounter = 0;

    public Employee(String firstName, String lastName, String phone, String email) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.fullname = firstName + " " + lastName;
        this.mobile_no = phone;
        this.email_id = email;
        empid = empidcounter;
        empidcounter++;
    }

    public int getEmpid() {
        return empid;
    }

    public void setName(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getMobile_no() {
        return this.mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail_id() {
        return this.email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
    
    @Override 
    public String toString() {
        return this.fullname;
    }
}
