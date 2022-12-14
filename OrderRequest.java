
package Model.WorkQueue;

import Model.Consumer.CommodityOrder;
import Model.Enterprise.Delivery.ShipmentCompany;
import Model.Enterprise.Enterprise;
import Model.UserAccount.UserAccount;
import java.util.ArrayList;


/**
 *
 * @author sohamdeshpande
 */
public class OrderRequest extends WorkRequest {
    
    private String Orderrequest_id;
    private StatusEnum status;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryPhone;
    private double amount;
    private ShipmentCompany company;
    private ArrayList<CommodityOrder> menu;
    private ReviewRequest review;

    // generate order number
    public static String genId() {
        String orderId
                = (System.currentTimeMillis() + "").substring(1)
                + (System.nanoTime() + "").substring(7, 10);
        return orderId;
    }

    public OrderRequest(Enterprise enterprise, UserAccount account, ArrayList<CommodityOrder> menu) {
        super(enterprise, account);
        this.menu = menu;
        this.Orderrequest_id = genId();
        this.review = null;
    }

    public enum ReviewStatus {

        NA("N/A"),
        reviewed("Reviewed"),
        not("Not Reviewed");

        private String value;

        private ReviewStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public String getOrderrequest_id() {
        return this.Orderrequest_id;
    }

    public String getDeliveryName() {
        return this.deliveryName;
    }

    public void setDeliveryName(String name) {
        this.deliveryName = name;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String address) {
        this.deliveryAddress = address;
    }

    public String getDeliveryPhone() {
        return this.deliveryPhone;
    }

    public void setDeliveryPhone(String phone) {
        this.deliveryPhone = phone;
    }

    public ArrayList<CommodityOrder> getItems() {
        return this.menu;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return this.amount;
    }

    public ShipmentCompany getCompany() {
        return this.company;
    }

    public void setCompany(ShipmentCompany company) {
        this.company = company;
    }

    public boolean isReviewed() {
        if (this.review == null) {
            return false;
        } else {
            if (this.review.getRating() == -1) {
                return false;
            }
        }
        return true;
    }

    public void setReview(ReviewRequest review) {
        this.review = review;
    }

    public ReviewRequest getReview() {
        return this.review;
    }

    public boolean eligableToBeReviewed() {
        if (this.review != null) {
            if (this.review.getRating() == -1) {
                return true;
            }
        }
        return false;
    }
    
    public ReviewStatus getReviewStatus() {
        if(eligableToBeReviewed()) {
            return ReviewStatus.not;
        }
        if (isReviewed()) {
            return ReviewStatus.reviewed;
        } else {
            return ReviewStatus.NA;
        }
    }
}
