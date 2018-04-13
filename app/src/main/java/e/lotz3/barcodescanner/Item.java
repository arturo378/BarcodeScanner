package e.lotz3.barcodescanner;

public class Item {
    private String Quantity;
    private String Category;
    private String Model;

    public Item(String Quantity, String Category, String Model) {
        this.Category = Category;
        this.Quantity = Quantity;
        this.Model = Model;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }
}
