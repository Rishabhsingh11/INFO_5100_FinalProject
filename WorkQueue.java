/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.WorkQueue;

import java.util.ArrayList;

/**
 *
 * @author sohamdeshpande
 */
public class WorkQueue {
    
    private ArrayList<WorkRequest> workRequestDirectory;

    public WorkQueue() {
        workRequestDirectory = new ArrayList<>();
    }

    public ArrayList<WorkRequest> getWorkRequestDirectory() {
        return workRequestDirectory;
    }
    
    public OrderRequest getOderById(String id) {
        for (WorkRequest wr:this.workRequestDirectory) {
            OrderRequest or = (OrderRequest)wr;
            if(or.getOrderrequest_id().equals(id)) {
                return or;
            }
        }
        return null;
    }
    public InventoryItemRequest getinventoryOderById(String id) {
        for (WorkRequest wr:this.workRequestDirectory) {
            InventoryItemRequest or = (InventoryItemRequest)wr;
            if(or.getInventoryOrderId().equals(id)) {
                return or;
            }
        }
        return null;
    }
}
