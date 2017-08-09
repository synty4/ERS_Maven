package be.ucl.ingi.lingi2252.ers;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import be.ucl.ingi.lingi2252.disaster.AreaType;
import be.ucl.ingi.lingi2252.disaster.ConcreteDisaster;
import be.ucl.ingi.lingi2252.disaster.Earthquake;
import be.ucl.ingi.lingi2252.disaster.Flood;
import be.ucl.ingi.lingi2252.ers.Area;
import be.ucl.ingi.lingi2252.ers.ERS;
import be.ucl.ingi.lingi2252.ers.GPSCoordinates;
import be.ucl.ingi.lingi2252.ers.PlaceType;
import be.ucl.ingi.lingi2252.ers.SafePlace;
import be.ucl.ingi.lingi2252.instruction.Instruction;

public class MainHelper {

     public static void printScreen(String title, String content) {
    	 System.out.println("-------------------------------------------------");
    	 System.out.println(title);
    	 System.out.println("-------------------------------------------------");
    	 System.out.println(content);
    	 System.out.println("-------------------------------------------------");
    	 System.out.println("       Your safety is our goal number 1!");
    	 System.out.println("-------------------------------------------------");
     } 
  public static void printHome() {
		
	  printScreen("Emergency Response System (ERS)", "Select an option :" +"\n"
				+ "1 : General Safety Instructions \n"
				+ "2 : Trigger a Disaster\n" 
				+ "3 : Check if in danger\n"
				+ "4 : Display Safe Places\n"
				+ "5 : ERS Current Sate\n"
				+ "0 : Quit\n");
	}
	
   
   public static  String exit() {
	   System.exit(1);
	   return "Exit ERS !\n";	
   }
	public static void triggerFlood(ConcreteDisaster dB, ERS ers, Scanner opt){
		dB.setActive(true);
		ers.addDisaster(dB);
		printScreen("Triggered Disaster: \n", dB.toString());
	}
	
	public static void triggerEarthquake(ConcreteDisaster dA, ERS ers){
		dA.setActive(true);
		ers.addDisaster(dA);
		printScreen("Triggered Disaster: \n", dA.toString());
	}
	public static void triggerDisasters(ConcreteDisaster dA, ConcreteDisaster dB, ERS ers, int disaster, Scanner opt){
		switch (disaster) {
		case 1: triggerFlood(dB, ers, opt);
				break;
		case 2: triggerEarthquake(dA, ers);
				break;
		}
	}

	
	public static String printTextSafePlaces(ERS ers) {
		return ers.toStringSafePlaces();
	}
	
	public static ConcreteDisaster getposition(String inputPos, ERS ers) {
		String[] coords = inputPos.split(" "); 
		ers.getUser().setUserCurrentPossition(new GPSCoordinates(Double.valueOf(coords[0]), Double.valueOf(coords[1])));
		return ers.isUnSafe(ers.getUser().getUserCurrentPosition());
	}
	
	public static String guideToSafePlace(Scanner opt, ERS ers) {
		printScreen("Directions to nearest Safe place", "\n-1:Text\n-2:Voice\n");
		String str = " ";
		int e =Integer.parseInt(opt.nextLine());
		switch(e) {
			case 1: str = ers.guideToSafePlace();
					break;
			case 2: str = "Directions to Safe Place Audio";
					ers.getVoice(ers.guideToSafePlace());
					break;
		}
			return str;
	}
	
	public static String printSpecificInstructions(ConcreteDisaster disaster2, ERS ers, Scanner opt) {
		
		printScreen("Disaster Specific Instructions", "\n-1:Text\n-2:Voice\n-3: Home\n");
		String str = " ";
		int f =Integer.parseInt(opt.nextLine());
		switch(f) {
		
			case 1: str = disaster2.displayInstructions();
					break;
			case 2: str = "Specific Instruction for disaster"+disaster2.getDisasterName();
					ers.getVoice(disaster2.displayInstructions());
					break;
		}
		return str;
	}
	public static String printguidance(ConcreteDisaster disaster2, ERS ers, Scanner opt) {
		
		printScreen("Disaster Assistance", "\n-1:Directions to the nearest safe place"
				  + "\n-2:Disater specific instructions\n-3: Home\n");
		String str = " ";
		int d = Integer.parseInt( opt.nextLine());
		switch(d) {
			case 1: str = guideToSafePlace(opt, ers);
					break;
			case 2: str = printSpecificInstructions(disaster2, ers, opt);
					break;
		}
		return str;
	}
	
	public static void checkSafe(ConcreteDisaster disaster2, ERS ers, Scanner opt) {
		String s= "";
		if( disaster2 == null) {
			printScreen("Position status", "\n You are safe in this current position: " 
		                +"\n" + ers.getUser().getUserCurrentPosition() +"\n");
		} else {
			printScreen("Position status","\n You are in danger in this current position " 
					   +"\n" + ers.getUser().getUserCurrentPosition() +"\n");
		    printguidance(disaster2, ers, opt); 
		}
	}
	
}
