
package Model.WorkQueue;

import Model.Consumer.CommodityOrder;
import Model.Consumer.InventoryOrder;
import Model.Enterprise.Delivery.ShipmentCompany;
import Model.Enterprise.Enterprise;
import Model.UserAccount.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author risha
 */
public class InventoryItemRequest extends WorkRequest
{
    private String InventoryOrderId;
    private StatusEnum status;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryPhone;
    private double amount;
    private ShipmentCompany company;
    private ArrayList<CommodityOrder> inventoryorders;
    private ReviewRequest review;
    
    public static String genId() 
    {
        String orderId
                = (System.currentTimeMillis() + "").substring(1)
                + (System.nanoTime() + "").substring(7, 10);
        return orderId;
    }
    
    public enum ReviewStatus 
    {

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
    
    public InventoryItemRequest(Enterprise enterprise, UserAccount account, ArrayList<CommodityOrder> inventoryOrder) {
        super(enterprise, account);
        this.inventoryorders = inventoryOrder;
        this.InventoryOrderId = genId();
        this.review = null;
    }

    public String getInventoryOrderId() {
        return InventoryOrderId;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public ArrayList<CommodityOrder> getInventorOrder() {
        return inventoryorders;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ShipmentCompany getCompany() {
        return company;
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

    public ReviewRequest getReview() {
        return review;
    }

    public void setReview(ReviewRequest review) {
        this.review = review;
    }
    
    public boolean eligableToBeReviewed() {
        if (this.review != null) {
            if (this.review.getRating() == -1) {
                return true;
            }
        }
        return false;
    }
    
    public OrderRequest.ReviewStatus getReviewStatus() {
        if(eligableToBeReviewed()) {
            return OrderRequest.ReviewStatus.not;
        }
        if (isReviewed()) {
            return OrderRequest.ReviewStatus.reviewed;
        } else {
            return OrderRequest.ReviewStatus.NA;
        }
    }
    
    
    
    
}
