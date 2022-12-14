/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.WorkQueue;

import Model.Enterprise.Enterprise;
import Model.UserAccount.UserAccount;

/**
 *
 * @author sohamdeshpande
 */
public class ReviewRequest extends WorkRequest {
    
    private int rating;

    public ReviewRequest(Enterprise enterprise, UserAccount account) {
        super(enterprise, account);
        this.rating = -1;
    }
    
    public int getRating() {
        return this.rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
}
