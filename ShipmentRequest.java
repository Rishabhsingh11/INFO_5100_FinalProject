/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.WorkQueue;

import Model.Enterprise.Enterprise;
import Model.UserAccount.UserAccount;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sohamdeshpande
 */
public class ShipmentRequest extends WorkRequest {
    
    private StatusEnum status;
    private OrderRequest order;
    private InventoryItemRequest request;
    private Date preparedTime;
    private Date pickupTime;
    private Date deliveredTime;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ShipmentRequest(Enterprise enterprise, UserAccount account, OrderRequest order) {
        super(enterprise, account);
        this.order = order;
    }
    
    public ShipmentRequest(Enterprise enterprise, UserAccount account, InventoryItemRequest request) {
        super(enterprise, account);
        this.request = request;
    }

    public String getPreparedTime() {
        return this.format.format(preparedTime);
    }

    public String getPickupTime() {
        return this.format.format(pickupTime);
    }

    public String getDeliveredTime() {
        return this.format.format(deliveredTime);
    }

    public void setPreparedTime(Date date) {
        this.preparedTime = date;
    }

    public void setPickupTime(Date date) {
        this.pickupTime = date;
    }

    public void setDeliveredTime(Date date) {
        this.deliveredTime = date;
    }

    public String getOrderId() {
        return this.order.getOrderrequest_id();
    }

    public OrderRequest getOrder() {
        return this.order;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.order.getRequestDate();
    }
}
