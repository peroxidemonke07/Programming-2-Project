package DemoPackage.SellerPack;

import java.util.ArrayList;

import DemoPackage.*;
import EcommPackage.*;

//SellerM a derived class of Seller
//Implemented by Mayank Chamarthi(IMT2021073)
public class SellerM extends Seller{
    //Array List to store Porducts with the seller
    private ArrayList<Product> Products = new ArrayList<Product>();
    //constructor
    public SellerM (String ID){

        myID = ID;
        //creating product objects
        Mobile m1 = new Mobile("Samsung_M", 30000, 10);
        Mobile m2 = new Mobile("Apple_M", 50000, 8);
        Mobile m3 = new Mobile("Mi_M", 16000, 22);
        Mobile m4 = new Mobile("OnePlus_M", 35000, 12);
        Mobile m5 = new Mobile("Sony_M", 22000, 4);

        Book b1 = new Book("Akhanda",150,10);
        Book b2 = new Book("Dictator",200,10);
        Book b3 = new Book("Legend",700,10);
        Book b4 = new Book("DabbidiDibbide",650,10);
        Book b5 = new Book("BulBul",422,10);
        //adding products to Products array list
        Products.add(m1);
        Products.add(b1);
        Products.add(m2);
        Products.add(b2);
        Products.add(m3);
        Products.add(b3);
        Products.add(m4);
        Products.add(b4);
        Products.add(m5);
        Products.add(b5);

    }
    //method to attach seller to platform
    public void addPlatform(Platform thePlatform){
        thePlatform.addSeller(this);
    }
    //method to return products of a specific category
    public ArrayList<Product> findProducts( Globals.Category whichCategory){
        ArrayList <Product> OutputList = new ArrayList<Product>();
        for(Product p: Products){
            
            if(p.getCategory() == whichCategory){
                OutputList.add(p);
            }
        }
        return OutputList;
    }
    //method to buy a product
    public boolean buyProduct(String ProdID,int quantity){
        for(Product p : Products){
            //sucessfull only if ID matches and quantity bought is lesser than or equal to available quantity
            if(p.getProductID().compareTo(ProdID) == 0 ){
                if(p.getQuantity() >= quantity ) {
                    p.subtractQuantity(quantity);
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

} 