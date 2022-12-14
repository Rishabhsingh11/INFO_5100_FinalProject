/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Employee;

import java.util.ArrayList;

/**
 *
 * @author risha
 */
public class EmployeeDirectory {
    
    private ArrayList<Employee> employeeDirectory;

    public EmployeeDirectory() {
        employeeDirectory = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployeeDirectory() {
        return employeeDirectory;
    }

    public boolean isEmployeeExist(Employee emp) {
        if (!this.employeeDirectory.isEmpty()) {
            for (Employee em : this.employeeDirectory) {
                if (em.getEmpid() == emp.getEmpid()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Employee createEmployee(String firstName, String lastName, String phone, String email) {
        Employee employee = new Employee(firstName, lastName, phone, email);
        employeeDirectory.add(employee);
        return employee;
    }

}
