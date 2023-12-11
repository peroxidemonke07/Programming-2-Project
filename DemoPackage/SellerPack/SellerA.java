package DemoPackage.SellerPack;

import java.util.ArrayList;


import DemoPackage.*;
import EcommPackage.*;
//derived class of the Seller class
//done by Karthik Reddy 
public class SellerA extends Seller{
    //products list
    private ArrayList<Product> Products = new ArrayList<Product>();
    //constructor
    public SellerA (String ID){
        myID = ID;
        Mobile m1 = new Mobile("Tommy_A", 15000,  7);
        Mobile m2 = new Mobile("Gas_A",  55000,  20);
        Mobile m3 = new Mobile("Zara_A",  35000,  23);
        Mobile m4 = new Mobile("Polo_A",  45000,  28);
        Mobile m5 = new Mobile("Hrx_A",  65000,  38);
        Book b1 = new Book( "Woodland", 350, 5);
        Book b2 = new Book( "Puma", 750,5);
        Book b3 = new Book("Adidas", 850, 5);
        Book b4 = new Book("Nike", 950, 5);
        Book b5 = new Book( "asics", 650, 5);
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
    //method to add seller to platform
    public void addPlatform(Platform thePlatform){
        thePlatform.addSeller(this);
    }
    //method find products of a category
    public ArrayList<Product> findProducts(Globals.Category whichCategory){
        ArrayList <Product> OutList = new ArrayList<Product>();
        for(Product p: Products){
            if(p.getCategory() == whichCategory){
                OutList.add(p);
                
            }
        }
        return OutList;
    }
    //method to buy products
    public boolean buyProduct(String ProdID,int quantity ){
        for(Product p: Products){
            if(p.getProductID().compareTo(ProdID) == 0){
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