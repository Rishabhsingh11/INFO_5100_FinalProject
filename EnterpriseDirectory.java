/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author risha
 */
public class EnterpriseDirectory {
    private ArrayList<Enterprise> enterpriseDirectory;

    public EnterpriseDirectory() {
        enterpriseDirectory = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public void removeEnterprise(Enterprise en) {
        Enterprise result = null;
        if (!enterpriseDirectory.isEmpty()) {
            for (Enterprise ent : enterpriseDirectory) {
                if (ent.getShipcom_id().equalsIgnoreCase(en.getShipcom_id())) {
                    result = ent;
                }
            }
            enterpriseDirectory.remove(result);
        } 
    }   
}
