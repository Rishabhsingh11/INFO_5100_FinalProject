
package Model.UserAccount;

import Model.Consumer.Consumer;
import Model.Consumer.ShoppingBasket;
import Model.Role.ConsumerRole;
import Model.WorkQueue.OrderRequest;
import Model.WorkQueue.WorkRequest;
import Model.WorkQueue.WorkRequest.StatusEnum;
import java.math.BigDecimal;

/**
 *
 * @author sohamdeshpande
 */
public class ConsumerAccount extends UserAccount {
    
    private Consumer consumer;
    private ShoppingBasket basket;

    public ConsumerAccount(String username, String password, Consumer customer) {
        super(username, password, new ConsumerRole());
        this.consumer = customer;
        this.basket = new ShoppingBasket();
    }

    public Consumer getConsumer() {
        return this.consumer;
    }

    public ShoppingBasket getBasket() {
        return this.basket;
    }

    public double getTotalPurchased() {
        double result = 0;
        if (!this.getWorkQueue().getWorkRequestDirectory().isEmpty()) {
            for (WorkRequest wr : this.getWorkQueue().getWorkRequestDirectory()) {
                OrderRequest or = (OrderRequest) wr;
                if (or.getStatus().equals(StatusEnum.Completed)) {
                    result = result + or.getAmount();
                }
            }
        }
        BigDecimal bd = new BigDecimal(result);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
