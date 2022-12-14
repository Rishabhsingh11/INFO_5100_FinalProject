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
public class InventoryDeliveryRequest extends WorkRequest {
 
    private WorkRequest.StatusEnum status;
    private InventoryItemRequest invreq;
    private Date preparedTime;
    private Date pickupTime;
    private Date deliveredTime;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InventoryDeliveryRequest(Enterprise enterprise, UserAccount account, InventoryItemRequest order) {
        super(enterprise, account);
        this.invreq = order;
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

    public String getInventoryOrderId() {
        return this.invreq.getInventoryOrderId();
    }

    public InventoryItemRequest getInventoryOrder() {
        return this.invreq;
    }

    public WorkRequest.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(WorkRequest.StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.invreq.getRequestDate();
    }
    
}
