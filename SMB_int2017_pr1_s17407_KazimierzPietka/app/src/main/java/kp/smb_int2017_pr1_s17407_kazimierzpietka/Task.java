package kp.smb_int2017_pr1_s17407_kazimierzpietka;

public class Task {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public Task() {}

    public Task(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return Integer.toString(id);
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return Integer.toString(price);
    }
    public String getQuantity() {
        return Integer.toString(quantity);
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - " + price + " - " + quantity;
    }
}
