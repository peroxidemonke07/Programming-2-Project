package  EcommPackage;

import java.util.*;

public abstract class Seller {
	// id is passed in by the class that instantiates sub-type of seller
	
	
	// ID of seller. 
	public String getID() { return myID;}
	
	// Platform it is being added to.
	// Should in turn add itself to the Platform (with addSeller)
	public abstract void addPlatform(Platform thePlatform);
	
	// Seller to return listing of Products of specified Category
	public abstract ArrayList<Product> findProducts(Globals.Category whichOne);
	// User wants to buy specified quantity of productID
	// Return true if transaction succeeds, false otherwise. 
	// Transaction fails if incorrect productID or quantity exceeds available inventory
	public abstract boolean buyProduct(String productID, int quantity);
	
	protected String myID;
}
