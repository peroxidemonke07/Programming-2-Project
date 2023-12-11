package DemoPackage;
import EcommPackage.Globals;
import EcommPackage.Product;

//derived class book 
public class Book extends Product{
    //attributes
    private int Quantity;
    private float Price;
    private String Name,ProductID;
    //counter to generate product IDs
    private static int BookIDCounter = 1;
    //constructor
    public Book(String name, float price, int quantity){
        this.ProductID = "Book_" + Integer.toString(BookIDCounter++);
        this.Name = name;
        this.Quantity = quantity;
        this.Price = price;
    }
    //method to get category
    public Globals.Category getCategory(){
        return Globals.Category.values()[1];
    }
    //method to get price
    public float getPrice(){
        return this.Price;
    }
    //method to get quantity
    public int getQuantity(){
        return this.Quantity;
    }
    //method to get name
    public String getName(){
        return this.Name;
    }
    //method to get ID
    public String getProductID(){
        return this.ProductID;
    }
    //method to subtract quantity by q
    public void subtractQuantity(int q){
        this.Quantity -= q;
    }


}
