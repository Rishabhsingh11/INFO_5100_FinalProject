/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Enterprise.Restaurant;

import Model.Enterprise.Commodity;
import Model.Enterprise.Outlet;
import Model.Organization.ChefOrganization;
import Model.Organization.RestaurantSupervisorOrganization;
import Model.WorkQueue.OrderRequest;
import Model.WorkQueue.WorkRequest;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author sohamdeshpande
 */
public class Restaurant extends Outlet {
    
    private int photoId;
    private RestaurantCategory type;
    private String restaurant_id;
    private static int restaurantid_counter = 0;
    private String photoPath;

    @Override
    public OutletType getOut_type() {
        return OutletType.Restaurant;
    }

    public enum RestaurantCategory {

        Chinese("Chinese"),Japanese("Japanese"), Indian("Indian"),
        American("American"), Mexican("Mexican");

        private String value;

        private RestaurantCategory(String value) {
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

    public Restaurant(String name, String address, String phone) {
        super(name, address, phone);
        this.photoId = restaurantid_counter;
        this.restaurant_id = "Restaurant" + restaurantid_counter;
        restaurantid_counter++;
        this.setOut_type(OutletType.Restaurant);

        String path = "Images/RestaurantCut/default.png";
        String fileName = "default.png";

        File f = new File("Images/RestaurantCut");
        if (f.isDirectory()) {
            File[] F1 = f.listFiles();
            for (File f2 : F1) {
                if (f2.getName().equalsIgnoreCase(this.photoId + ".png")) {
                    fileName = this.photoId + ".png";
                    path = "Images/RestaurantCut/" + fileName;
                }
            }
        }
        this.photoPath = path;
    }

    @Override
    public String getCategoryString() {
        if (this.type != null) {
            return this.type.name();
        }
        return "";
    }

    @Override
    public void setShipcom_id(String id) {
        this.restaurant_id = id;
    }

    @Override
    public void createOrganizations() {
        this.getOrganizationDirectory().getOrganizationDirectory().add(new RestaurantSupervisorOrganization());
        this.getOrganizationDirectory().getOrganizationDirectory().add(new ChefOrganization());
    }

    public int getPhotoId() {
        return this.photoId;
    }

    public RestaurantCategory getType() {
        return this.type;
    }

    public void setType(RestaurantCategory cat) {
        this.type = cat;
    }

    public ArrayList<Menu> getMenu() {
        ArrayList<Menu> result = new ArrayList<>();
        for (Commodity item : this.getCommodity()) {
            Menu dash = (Menu) item;
            result.add(dash);
        }
        return result;
    }

    public void addToMenu(Menu dash) {
        this.getCommodity().add(dash);
    }

//    public ArrayList<OrderRequest> getOrders() {
//        return this.orders;
//    }
//
//    public ArrayList<ReviewRequest> getReviews() {
//        return this.reviews;
//    }
    @Override
    public double getRate() {
        double totalRate = 0;
        double num = 0;
        for (WorkRequest wr : this.getWorkQueue().getWorkRequestDirectory()) {
            OrderRequest order = (OrderRequest) wr;
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
        return this.restaurant_id;
    }
    
}
