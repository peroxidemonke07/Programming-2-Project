package DemoPackage;

import EcommPackage.Globals;
import EcommPackage.Product;


//derived class mobile
public class Mobile extends Product{
    //counter to generate IDs
    private static int MobileIDCounter =1;
    //attributes
    private int Quantity;
    private float Price;
    private String Name,ProductID;
    //constructor
    public Mobile(String name, float price, int quantity){
        this.Name = name;
        this.Quantity = quantity;
        this.Price = price;
        this.ProductID = "Mobile_" + Integer.toString(MobileIDCounter++);
    }
    //method to get category
    public Globals.Category getCategory(){
        return Globals.Category.values()[0];
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
    //method to get productID
    public String getProductID(){
        return this.ProductID;
    }
    //method to subtract quantity by q
    public void subtractQuantity(int q){
        this.Quantity -= q;
    }

}
