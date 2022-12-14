
package Model.UserAccount;

import Model.Consumer.ShoppingBasket;
import Model.Employee.Employee;
import Model.Enterprise.Enterprise;
import Model.Role.Role;
import Model.WorkQueue.InventoryItemRequest;
import Model.WorkQueue.WorkRequest;
import java.math.BigDecimal;

/**
 *
 * @author sohamdeshpande
 */
public class EmployeeAccount extends UserAccount {
    
    private Employee employee;
    private ShoppingBasket Basket;

    public EmployeeAccount(String username, String password, Role role, Employee employee) {
        super(username, password, role);
        this.Basket = new ShoppingBasket();
        this.employee = employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public ShoppingBasket getBasket() {
        return this.Basket;
    }
    
    public double getTotalPurchased() {
        double result = 0;
        if (!this.getWorkQueue().getWorkRequestDirectory().isEmpty()) {
            for (WorkRequest wr : this.getWorkQueue().getWorkRequestDirectory()) {
                InventoryItemRequest ir = (InventoryItemRequest) wr;
                if (ir.getStatus().equals(WorkRequest.StatusEnum.Completed)) {
                    result = result + ir.getAmount();
                }
            }
        }
        BigDecimal bd = new BigDecimal(result);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
    public void setEmployee(Employee em) {
        this.employee = em;
    }
}
