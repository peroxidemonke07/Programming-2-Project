package DemoPackage;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//importing from ecomm package
import EcommPackage.Globals;
import EcommPackage.Platform;
import EcommPackage.Product;
import EcommPackage.Seller;

//derived class demoplatform
public class DemoPlatform extends Platform {
	//Array to store the Sellers
	private ArrayList<Seller> SellerList = new ArrayList<Seller>();
	//file object for PlatformToPortal.txt
	private File f1 = new File(Globals.fromPlatform);
	//file object for PortalToPlatform.txt
	private File f2 = new File(Globals.toPlatform);
	//next line to be read when reading PortalToPlatform.txt
	private int lineTobeReadFrom = 1;
	//current line in PortalToPlatform.txt
	private int currentLine;
	//method to add a seller to the platform
	@Override
	public void addSeller(Seller aSeller) {
		SellerList.add(aSeller);	
	}
	//method to process a Start request
	public void StartFunction(String[] requestStrings){
		try {
			//file writer to append PlatFormToPortal.txt             
			FileWriter fw = new FileWriter(f1, true);
			//appending the response
			fw.append(requestStrings[0]+" "+requestStrings[1]+" ");
			for(Globals.Category c : Globals.Category.values()){
				fw.append(Globals.getCategoryName(c) + " ");
			}
			fw.append("\n");
			fw.close();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	//method to process the List request
	public void ListFunction(String[] requestStrings){
		//category of the current list response
		Globals.Category myCategory = null;
		for(Globals.Category c : Globals.Category.values()){
			if(Globals.getCategoryName(c).compareTo(requestStrings[3]) == 0){
				myCategory = c;
			}
		}
		
		try {
			//file writer to write in the PlatformToPortal.txt
			FileWriter fw = new FileWriter(f1,true);
			//iterating through the seller list
			for(Seller mySeller : SellerList){
				//list of products of myCategory with the ith seller
				ArrayList<Product> pList = mySeller.findProducts(myCategory);
				//appending products of myCategory to PlatformToPortal.txt
				for(Product myProduct : pList){
					fw.append(requestStrings[0] + " " +requestStrings[1]+" "+myProduct.getProductID()+" "+
					myProduct.getName()+
					" "+Float.toString(myProduct.getPrice())+" "+Integer.toString(myProduct.getQuantity())+"\n");
				}
			}
			//appending end of list
			fw.append(requestStrings[0] + " " +requestStrings[1]+" EndOfList\n");
			fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	//method to process the Buy request 
	public void BuyFunction(String[] requestStrings){
		//is the transaction sucessfull?
		boolean isSuccesfull =false;
		//ID of the product bought
		String ProductID = requestStrings[3];
		//quantity of the product bought
		int quantity = Integer.valueOf(requestStrings[4]);
		for(Seller mySeller :SellerList){
			if(mySeller.buyProduct(ProductID, quantity)) isSuccesfull = true;
		}

		try {
			//file writer to write in the PlatformToPortal.txt
			FileWriter fw = new FileWriter(f1 , true);
			//transaction is successfull
			if(isSuccesfull){
				fw.append(requestStrings[0] + " "+ requestStrings[1]+" Success\n");
			}
			//transaction failed
			else{
				fw.append(requestStrings[0] + " "+ requestStrings[1]+" Failure\n");
			}
			fw.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	//methid to process the requests sent by portal
	//runs when "Check" is typed in the terminal where PlatformMain is running
	@Override
	public void processRequests() {
		//current line in file being read
		this.currentLine =0;
		Scanner s;
		try {
			//scanner to read the file
			s = new Scanner(f2);
			//reading the file
			while(s.hasNextLine()){
				currentLine++;
				String requestString = s.nextLine();
				//if we are at the line which has not yet been read and line is not empty, then we read it 
				if(this.currentLine == this.lineTobeReadFrom && requestString.compareTo("") != 0){	
					String[] requestStrings = requestString.split(" ");
					//if current line is start request
					if(requestStrings[2].compareTo("Start") == 0){
						this.StartFunction(requestStrings);
					}
					//if current line is list request
					else if(requestStrings[2].compareTo("List") == 0){
						this.ListFunction(requestStrings);
					}
					//if current line is buy request
					else if(requestStrings[2].compareTo("Buy") == 0){
						this.BuyFunction(requestStrings);
					}
					//inc next line to be read
					this.lineTobeReadFrom++;	
				}
			}

		}
		catch (FileNotFoundException e) {
			System.out.println("Exception: File does not exist");
			e.printStackTrace();
		}
		
	}
	//method to create platformtoportal.txt  and porttoplat.txt
	public void createFile(){
		try {
			this.f1.createNewFile();
			this.f2.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
