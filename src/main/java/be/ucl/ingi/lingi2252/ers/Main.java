package be.ucl.ingi.lingi2252.ers;

import java.util.Arrays;
import java.util.Scanner;

import be.ucl.ingi.lingi2252.ers.*;


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
		Disaster dA = new Earthquake("Cathrina", true, new GPSCoordinates(50.6412, 5.5718), 4.0, 2.0);
		Disaster dB = new Flood("Tsunami", true, 
				Arrays.asList(new GPSCoordinates(60.0,6.0), 
						new GPSCoordinates(66.0,6.0), 
						new GPSCoordinates(60.0,12.0), 
						new GPSCoordinates(66.0,12.0)),
				Arrays.asList(new GPSCoordinates(60.0,7.0), 
						new GPSCoordinates(60.0,8.0), 
						new GPSCoordinates(60.0,9.0), 
						new GPSCoordinates(60.0,10.0)));
		//add disaster
		ers.addDisaster(dA);
		ers.addDisaster(dB);

		//read command
		Scanner opt = new Scanner(System.in);

		String cmd = "";
		String cmd2 = "";
		while(!cmd.equals("0")){
			System.out.println("Enter a command :"+"\n"
					+ "1 : To start a disaster \n"
					+ "2 : See situation\n" 
					+ "3 : Check safe places\n"
					+ "4 : Set your position \n" 
					+ "5 : Find nearest safe place \n"
					+ "6 : Check if this zone is dangerous \n" 
					+ "7 : Display instructions\n"
					+ "0 : Quit ");

			cmd=opt.nextLine();

			int choice = -1;
			try {
				choice=Integer.parseInt(cmd); 
			} catch (Exception e){          
			}

			switch (choice){
			case 0:
				System.out.println("Stay safe !");
				opt.close();
				break;
			case 1:
				System.out.println("Start:\n-1:Flood\n-2:Earthquake");
				cmd2=opt.nextLine();
				int disaster=Integer.parseInt(cmd2);
				if(disaster==1){
					ers.addDisaster(dB);
					System.out.println(dB.toString()+"h\n has been enclenched");
					break;}
				else if (disaster==2){
					ers.addDisaster(dA);
					System.out.println(dA.toString() + "\n has been enclenched");
					break;}
			case 2:
				System.out.println(ers);
				break;
			case 3:
				System.out.println(ers.getSafePlaces());
				break;
			case 4:
				System.out.println("Insert position in this format: 55.02 5.02\n"); 
				String inputPos = opt.nextLine(); 
				String[] coords = inputPos.split(" "); //Assuming valid input...
				GPSCoordinates newPos = new GPSCoordinates(Double.valueOf(coords[0]), Double.valueOf(coords[1]));
				ers.getUser().setUserCurrentPossition(newPos);
				break;
			case 5:
				System.out.println(ers.getClosestSafePlace());
				break;
			case 6:
				if(ers.isInSafe(ers.getUser().getUserCurrentPosition())) {
					System.out.println("You are safe");
				} else {
					System.out.println("You're not safe here");
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
