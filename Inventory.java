/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise.Inventory;

import Model.Enterprise.Commodity;
import Model.Enterprise.Commodity;
import Model.Enterprise.Outlet;
import java.io.File;
import Model.Organization.InventorySupervisorOrganization;
import java.util.ArrayList;
import Model.WorkQueue.InventoryItemRequest;
import Model.WorkQueue.WorkRequest;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import javax.imageio.ImageIO;

/**
 *
 * @author risha
 */
public class Inventory extends Outlet
{
    private int photoId;
    private InventoryType inventype;
    private String id;
    private static int counter = 0;
    private String photoPath;
    
    @Override
    public Outlet.OutletType getOut_type() 
    {
        return Outlet.OutletType.Inventory;
    }
    
    public enum InventoryType 
    {

       Rawgoods("Raw Material"), Finishedgoods("Fineshed Good");

       private String value;

       private InventoryType(String value) 
       {
           this.value = value;
       }

       public String getValue() 
       {
           return value;
       }

       @Override
       public String toString() 
       {
           return value;
       }
    }

    public Inventory(String name, String address, String phone) 
    {
        super(name, address, phone);
        this.photoId = counter;
        this.id = "Inventory" + counter;
        counter++;
        
        String path = "Images/InventoryCut/default.JPEG";
        String fileName = "default.png";
        
        File f = new File("Images/InventoryCut");
        if (f.isDirectory()) {
            File[] F1 = f.listFiles();
            for (File f2 : F1) {
                if (f2.getName().equalsIgnoreCase(this.photoId + ".JPEG")) {
                    fileName = this.photoId + ".JPEG";
                    path = "Images/InventoryCut/" + fileName;
                }
            }
        }
        this.photoPath = path;
    }
    
    @Override
    public String getCategoryString() {
        if (this.inventype != null) {
            return this.inventype.name();
        }
        return "";
    }

    @Override
    public void setShipcom_id(String id) {
        this.id = id;
    }

    @Override
    public void createOrganizations() {
        this.getOrganizationDirectory().getOrganizationDirectory().add(new InventorySupervisorOrganization());
                
    }

    public int getPhotoId() {
        return this.photoId;
    }

    public InventoryType getInventype() {
        return inventype;
    }

    public void setInventype(InventoryType inventype) {
        this.inventype = inventype;
    }


    public ArrayList<InventoryItem> getInventoryItem() {
        ArrayList<InventoryItem> result = new ArrayList<>();
        for (Commodity commodity : this.getCommodity()) {
            InventoryItem inven = (InventoryItem) commodity;
            result.add(inven);
        }
        return result;
    }

    public void addInventoryItem(InventoryItem invitem) {
        this.getCommodity().add(invitem);
    }

    @Override
    public double getRate() {
        double totalRate = 0;
        double num = 0;
        for (WorkRequest wr : this.getWorkQueue().getWorkRequestDirectory()) {
            InventoryItemRequest order = (InventoryItemRequest) wr;
            if (order.isReviewed()) {
                totalRate = totalRate + order.getReview().getRating();
                num++;
            }
        }
        if (num == 0) {
            return -1;
        }
        BigDecimal bd = new BigDecimal(totalRate / num);
        return bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

//    public void updateRate() {
//        int sum = 0;
//        if (reviews.isEmpty()) {
//            this.rate = -1;
//        } else {
//            for (ReviewRequest review : reviews) {
//                sum = sum + review.getRate();
//            }
//            BigDecimal bd = new BigDecimal(sum / reviews.size());
//            this.rate = bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
//        }
//    }
    public String getPath() {
        return this.photoPath;
    }

    public void setPath(String path) {
        String newPath = "";
        try {
            BufferedImage image = ImageIO.read(new File(path));

            int radio = 0;
            if (image.getWidth() / 250 < image.getHeight() / 180) {
                radio = image.getWidth() / 250;
            } else {
                radio = image.getHeight() / 180;
            }
            int x = 0, y = 0, cutW = 250 * radio, cutH = 180 * radio;

            Rectangle rect = new Rectangle(x, y, cutW, cutH);
            BufferedImage areaImage = image.getSubimage(rect.x, rect.y, rect.width, rect.height);

            BufferedImage buffImg = new BufferedImage(cutW, cutH, BufferedImage.TYPE_INT_RGB);
            buffImg.getGraphics().drawImage(areaImage.getScaledInstance(cutW, cutH, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            String fileName = this.photoId + ".png";
            newPath = "Images/RestaurantCut/" + fileName;
            ImageIO.write(buffImg, "png", new File(newPath));
        } catch (IOException e) {

        }
        this.photoPath = newPath;
    }

    @Override
    public String getShipcom_id() {
        return this.id;
    }
    
}
