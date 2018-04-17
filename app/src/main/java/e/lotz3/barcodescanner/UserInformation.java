package e.lotz3.barcodescanner;

public class UserInformation {

    public String Category;
    public String Model;
    public String Quantity;
    public String Key;


    public UserInformation(String Category, String Model, String Quantity, String Key) {
        this.Category = Category;
        this.Model = Model;
        this.Quantity = Quantity;
        this.Key = Key;

    }

    UserInformation() {
    }
}
