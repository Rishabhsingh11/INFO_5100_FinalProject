
package Model.Enterprise;

import java.util.ArrayList;

/**
 *
 * @author risha
 */
public abstract class Outlet extends Enterprise {
    
    private String out_name;
    private String out_address;
    private String out_phone;
    private String out_description;
    private OutletType out_type;
    private ArrayList<Commodity> commodity;

    public enum OutletType {

        Restaurant("Restaurant"),
        Mart("Store"),
        Inventory("Inventory");

        private String value;

        private OutletType(String value) {
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

    public Outlet(String name, String address, String phone) {
        super(name);
        this.out_name = name;
        this.out_address = address;
        this.out_phone = phone;
        this.commodity = new ArrayList<>();
    }
    
    public abstract String getCategoryString();
    
    @Override
    public abstract void createOrganizations();

    public String getOut_address() {
        return this.out_address;
    }

    public void setOut_address(String addr) {
        this.out_address = addr;
    }

    public String getOut_phone() {
        return this.out_phone;
    }

    public void setOut_phone(String out_phone) {
        this.out_phone = out_phone;
    }

    public String getOut_description() {
        return this.out_description;
    }

    public void setOut_description(String des) {
        this.out_description = des;
    }

    public void setOut_type(OutletType out_type) {
        this.out_type = out_type;
    }

    public abstract OutletType getOut_type();

    public ArrayList<Commodity> getCommodity() {
        return this.commodity;
    }
    
    public void setCommodity(ArrayList<Commodity> list) {
        this.commodity = list;
    }

    @Override
    public String toString() {
        return this.out_name;
    }
    
    public abstract double getRate();
    
}
