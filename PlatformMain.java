import java.util.Scanner;

import DemoPackage.DemoPlatform;
import DemoPackage.SellerPack.SellerA;
import DemoPackage.SellerPack.SellerM;
import EcommPackage.Seller;
//main class
public class PlatformMain {

	public static void main(String[] args) {
		//demo platform 
		DemoPlatform pf = new DemoPlatform();  // replace with appropriate derived class
		
		// create a number of sellers (of different sub-types of Seller)
		// Assign a name (sellerID) to each of them.
		
		// replace each of the XYZ below with the derived class name of one of the
		// team members.
		boolean keepReading = true;
		Scanner PlatformIn = new Scanner(System.in);
		pf.createFile();
		//Sellers
		Seller s1 = new SellerM("Seller1"); 
		s1.addPlatform(pf);
		Seller s2 = new SellerA("Seller2");
		s2.addPlatform(pf);
		/* 
		Seller s3 = new SellerXYZ2("Seller3");
		s1.addPlatform(pf);
 		*/
		
		
		// keep reading from System.in
		// If "Check" typed in
		// invoke 
		while(keepReading){
			String NaString = PlatformIn.nextLine();
			//process requests if "Check" is entered
			if(NaString.compareTo("Check") == 0){
				pf.processRequests();
			}
		}
		PlatformIn.close();	
	}

}
