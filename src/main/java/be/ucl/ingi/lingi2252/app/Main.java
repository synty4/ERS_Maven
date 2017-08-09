package be.ucl.ingi.lingi2252.app;

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
import be.ucl.ingi.lingi2252.ers.MainHelper;
import be.ucl.ingi.lingi2252.ers.PlaceType;
import be.ucl.ingi.lingi2252.ers.SafePlace;
import be.ucl.ingi.lingi2252.instruction.Instruction;

public class Main {
		
	public static void main(String[] args) {
		ERS ers = new ERS();
		ers.addSafePlace( new SafePlace("Bruxelles sud", new GPSCoordinates(50.4167, 4.4333), PlaceType.Airoport));
		ers.addSafePlace( new SafePlace("Hopital Saint Luc", new GPSCoordinates(50.8466, 4.3528), PlaceType.Hospital));
		ers.addSafePlace( new SafePlace("Blocry", new GPSCoordinates(50.7057, 4.7484), PlaceType.Gym));
		
		List<Area>affectedAreaList = Arrays.asList(new Area(Arrays.asList(new GPSCoordinates(60.0,6.0), new GPSCoordinates(66.0,6.0), 
									 new GPSCoordinates(60.0,12.0), new GPSCoordinates(66.0,12.0)), AreaType.Affected));
		List<Area>dangerousAreaList = Arrays.asList( new Area(Arrays.asList(new GPSCoordinates(60.0,7.0), new GPSCoordinates(60.0,8.0), 
									  new GPSCoordinates(60.0,9.0), new GPSCoordinates(60.0,10.0)), AreaType.Dangerous));
		ers.addGeneralInstruction(new Instruction("Call Emergency Number 100, 101, 103, 107, 110 and 112" ));
		
		List <Instruction> floodInstrList = Arrays.asList(new Instruction("-Move immediately to higher ground or stay on high ground.\n" + 
				"-Evacuate if directed.\n" + 
				"-Avoid walking or driving through flood waters.\n" +
				"-Turn Around, Don't Drown!\n"
				+ "-Just 6 inches of moving water can knock you down and\n "
				+ "-one foot of moving water can sweep your vehicle away."));
		List <Instruction> earthquakeInstrList = Arrays.asList(new Instruction("-Move away from windows and unsecured tall furniture.\n"+ 
			     "-Drop, cover and hold on under a desk, a table or along an interior wall \n."
			    +"-Protect your head, neck and face. Stay under cover until the shaking \n"
			    +"-stops and debris settles."));


		ConcreteDisaster dA = new Earthquake("Cathrina", false, new GPSCoordinates(50.6412, 5.5718), 4.0, 2.0, earthquakeInstrList);
		ConcreteDisaster dB = new Flood("Tsunami", false, affectedAreaList, dangerousAreaList,  floodInstrList);
		Scanner opt = new Scanner(System.in);
		String cmd = "";
		while(!cmd.equals("0")){
			
			MainHelper.printHome();
	    	 
			cmd = opt.nextLine();
			int choice = -1;
			try {
				choice=Integer.parseInt(cmd); 
			} catch (Exception e){}
			
			switch (choice){
			case 0:
				System.out.println("Exit ERS!\n");
				opt.close();
				break;
			case 1:
				MainHelper.printScreen("ERS Safety General Information", ers.displayGeneralInstructions());
				break;
			case 2:
				MainHelper.printScreen("Trigger a Disaster", "\n-1:Flood\n\n-2:Earthquake\n");
				String option = opt.nextLine();
				int disaster = Integer.parseInt(option);
				 MainHelper.triggerDisasters(dA, dB, ers, disaster, opt);
				break;
			case 3: 
				MainHelper.printScreen("Current Position", "Insert position in this format: 789.02 12.89\n"); 
				String inputPos = opt.nextLine(); 
				MainHelper.checkSafe(MainHelper.getposition(inputPos, ers), ers, opt);
				break;
			case 4: 
				MainHelper.printScreen("Safe Places: \n", MainHelper.printTextSafePlaces(ers));
				break;
			case 5: 
				 MainHelper.printScreen("ERS Sate: \n", ers.toString());
				 break;
			default:
				MainHelper.printScreen("Invalid Input", "Invalid option\n");
				break;
			}
		}
	}
}          
			