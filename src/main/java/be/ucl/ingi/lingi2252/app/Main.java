package be.ucl.ingi.lingi2252.app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

import be.ucl.ingi.lingi2252.ers.Alert;
import be.ucl.ingi.lingi2252.ers.Area;
import be.ucl.ingi.lingi2252.ers.AreaType;
import be.ucl.ingi.lingi2252.ers.Disaster;
import be.ucl.ingi.lingi2252.ers.ERS;
import be.ucl.ingi.lingi2252.ers.Earthquake;
import be.ucl.ingi.lingi2252.ers.Flood;
import be.ucl.ingi.lingi2252.ers.GPSCoordinates;
import be.ucl.ingi.lingi2252.ers.Instruction;
import be.ucl.ingi.lingi2252.ers.PlaceType;
import be.ucl.ingi.lingi2252.ers.SafePlace;
import be.ucl.ingi.lingi2252.ers.SpecificInstruction;



public class Main {

	public static void main(String[] args) {
		ERS ers = new ERS();
		//Safe place
		SafePlace spA = new SafePlace("Bruxelles sud", new GPSCoordinates(50.4167, 4.4333), PlaceType.Airoport);
		SafePlace spB = new SafePlace("Hopital Saint Luc", new GPSCoordinates(50.8466, 4.3528), PlaceType.Hospital);
		SafePlace spC = new SafePlace("Blocry", new GPSCoordinates(50.7057, 4.7484), PlaceType.Gym);
		//add safe place
		ers.addSafePlace(spA);
		ers.addSafePlace(spB);
		ers.addSafePlace(spC);
		//Instructions
		Instruction iA = new SpecificInstruction("Call your mom");
		Instruction iB = new SpecificInstruction("Just run");
		Instruction iC = new SpecificInstruction("I already told u! just run!");
		//add instruction
		ers.addInstruction(iA);
		ers.addInstruction(iB);
		ers.addInstruction(iC);
		//disaster
		Area affectedArea = new Area(Arrays.asList(new GPSCoordinates(60.0,6.0), 
						new GPSCoordinates(66.0,6.0), 
						new GPSCoordinates(60.0,12.0), 
						new GPSCoordinates(66.0,12.0)), AreaType.Affected);
		Area dangerousArea = new Area(Arrays.asList(new GPSCoordinates(60.0,7.0), 
						new GPSCoordinates(60.0,8.0), 
						new GPSCoordinates(60.0,9.0), 
						new GPSCoordinates(60.0,10.0)), AreaType.Dangerous);
		List<Area>affectedAreaList = Arrays.asList(affectedArea);
		List<Area>dangerousAreaList = Arrays.asList(dangerousArea);
		Disaster dA = new Earthquake("Cathrina", true, new GPSCoordinates(50.6412, 5.5718), 4.0, 2.0);
		Disaster dB = new Flood("Tsunami", true, affectedAreaList, dangerousAreaList);
		//add disaster
		ers.addDisaster(dA);
		ers.addDisaster(dB);	
		//Alert
		Timer timer = new Timer();
		Alert alert = new Alert(ers, timer);
		timer.scheduleAtFixedRate(alert, 1000, 30000);
		//read command
		Scanner opt = new Scanner(System.in);
		String cmd = "";
		while(!cmd.equals("0")){
			System.out.println("Enter a command :"+"\n"
					+ "1 : To start a disaster \n"
					+ "2 : See situation\n" 
					+ "3 : Check safe places\n" 
					+ "5 : Find nearest safe place \n"
					+ "6 : Check if this zone is dangerous \n" 
					+ "7 : Display instructions\n"
					+ "0 : Quit\n");

			cmd = opt.nextLine();
			int choice = -1;
			try {
				choice=Integer.parseInt(cmd); 
			} catch (Exception e){}
			
			switch (choice){
			case 0:
				System.out.println("Stay safe !\n");
				opt.close();
				break;
			case 1:
				System.out.println("Start:\n-1:Flood\n-2:Earthquake\n");
				String activeDisaster = opt.nextLine();
				int disaster=Integer.parseInt(activeDisaster);
				if(disaster==1){
					ers.addDisaster(dB);
					System.out.println(dB.toString()+"h\n has been enclenched\n");
					break;}
				else if (disaster==2){
					ers.addDisaster(dA);
					System.out.println(dA.toString() + "\n has been enclenched\n");
					break;}
			case 2:
				System.out.println(ers);
				break;
			case 3:
				List<SafePlace> safePlaces = ers.getSafePlaces();
				System.out.println("Safe place(s):");
				for (SafePlace safePlace : safePlaces) {
					System.out.println("- " + safePlace.toString());
				}
				break;
			case 4:
				System.out.println("Insert position in this format: 55.02 5.02\n"); 
				String inputPos = opt.nextLine(); 
				String[] coords = inputPos.split(" "); 
				GPSCoordinates newPos = new GPSCoordinates(Double.valueOf(coords[0]), Double.valueOf(coords[1]));
				ers.getUser().setUserCurrentPossition(newPos);
				if(ers.isInSafe(ers.getUser().getUserCurrentPosition()) == null){
					synchronized(timer){
			            timer.notify();
			        }
				}
				break;
			case 5:
				System.out.println("Nearest safe place:\n- " + ers.getClosestSafePlace());
				break;
			case 6:
				Disaster disaster2 = ers.isInSafe(ers.getUser().getUserCurrentPosition());
				if( disaster2 == null) {
					System.out.println("You are safe here:\n- " + ers.getUser().getUserCurrentPosition());
				} else {
					System.out.println("You're not safe here:\n- " + disaster2);
				}
				break;
			case 7:
				System.out.println(ers.displayInstructions());
				break;
			default:
				System.out.println("Wrong option, try again \n");
				break;
			}
		}
	}
}
