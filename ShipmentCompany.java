/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise.Delivery;

import Model.Enterprise.Enterprise;
import Model.Organization.CourierOrganization;
import Model.Organization.ShipmentSupervisorOrganization;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sohamdeshpande
 */
public class ShipmentCompany extends Enterprise  {
    
    private int photoId;
    private String shipcom_address;
    private String shipcom_phone;
    private static int counter = 0;
    private String photoPath;
    private String shipcom_description;
    private String shipcom_id;
    
    public ShipmentCompany(String name,String address, String phone) {
        super(name);
        this.photoId = counter;
        this.shipcom_id = "Delivery" + counter;
        counter++;
        this.shipcom_address = address;
        this.shipcom_phone = phone;
        
        String path = "Images/DeliveryCompanyCut/default.png";
        String fileName = "default.png";

        File f = new File("Images/DeliveryCompanyCut");
        if (f.isDirectory()) {
            File[] F1 = f.listFiles();
            for (File f2 : F1) {
                if (f2.getName().equalsIgnoreCase(this.photoId + ".png")) {
                    fileName = this.photoId + ".png";
                    path = "Images/DeliveryCompanyCut/" + fileName;
                }
            }
        }
        this.photoPath = path;
    }
    
    public int getPhotoId() {
        return this.photoId;
    }

    public String getShipcom_address() {
        return this.shipcom_address;
    }

    public void setShipcom_address(String addr) {
        this.shipcom_address = addr;
    }

    public String getShipcom_phone() {
        return this.shipcom_phone;
    }

    public void setShipcom_phone(String shipcom_phone) {
        this.shipcom_phone = shipcom_phone;
    }

    public String getShipcom_description() {
        return this.shipcom_description;
    }

    public void setShipcom_description(String des) {
        this.shipcom_description = des;
    }
    
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
            newPath = "Images/DeliveryCompanyCut/" + fileName;
            ImageIO.write(buffImg, "png", new File(newPath));
        } catch (IOException e) {
            
        }
        this.photoPath = newPath;
    }

    @Override
    public void createOrganizations() {
        this.getOrganizationDirectory().getOrganizationDirectory().add(new CourierOrganization());
        this.getOrganizationDirectory().getOrganizationDirectory().add(new ShipmentSupervisorOrganization());
    }

    @Override
    public String getShipcom_id() {
        return this.shipcom_id;
    }

    @Override
    public void setShipcom_id(String shipcom_id) {
        this.shipcom_id = shipcom_id;
    }
    
}
