package kp.smb_int2017_pr1_s17407_kazimierzpietka;

public class Task {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int done;

    public Task() {}

    public Task(int id, String name, int price, int quantity, int done) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.done = done;
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
    public Integer getDone() { return done; }

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
    public void setDone() { this.price = 0; this.quantity = 0; }

    @Override
    public String toString() {
        return name + " - " + price + " - " + quantity;
    }
}
