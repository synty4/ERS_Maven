package be.ucl.ingi.lingi2252.ers;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;



public class Main {
	
	/**
	 * Display ERS home Screen
	 * 
	 **/
	public static void printHome() {
	
		String r = 
				"\n" + "1. General Instructions "+
		    	"\n" + "2. Disasters List" +
		    	"\n" + "3. Safe Places" +
		    	//"\n" + "D. Unsafe places" +
		    	"\n" + "4. Alert Management" +
		    	"\n" + "5. Exit" ;
	printScreen( "ERS HOME", r,  "Select OPTION 1|2|3|4|5");
	}
	
	
	/**
	 * This method is used to print screens on the console
	 **/
	public static void printScreen(String title, String content, String optionSelect) {

		System.out.println();
		System.out.println();
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		System.out.println("                                   " + title);
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");																		
		System.out.println();
		System.out.println();
		System.out.println(content);										
		System.out.println();
		System.out.println();
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		System.out.println("                            Select OPTION"+ optionSelect);
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		System.out.println();
		System.out.println();
		
	}

	public static void main(String[] args) {
		ERS ers = new ERS();
		User user = new User("John");
		user.setUserCurrentPossition( new GPSCoordinates(60.0, 6.0));
		String current_position = user.getUserCurrentPosition().toString();
		
		SafePlace safe_place_1 = new SafePlace("Bruxelles sud", new GPSCoordinates(50.4167, 4.4333), PlaceType.Airoport);
		SafePlace safe_place_2 = new SafePlace("Hopital Saint Luc", new GPSCoordinates(50.8466, 4.3528), PlaceType.Hospital);
		SafePlace safe_place_3 = new SafePlace("Blocry", new GPSCoordinates(50.7057, 4.7484), PlaceType.Gym);
		
		ers.addSafePlace(safe_place_1);
		ers.addSafePlace(safe_place_2);
		ers.addSafePlace(safe_place_3);
		
		Instruction Instruction_1 = new SpecificInstruction("Move your valuables to an upper floor, as well as any items "+
													"\n" + "that can be removed from the basement or ground floor");
		Instruction Instruction_2 = new SpecificInstruction("Place bags of sand and membranes in front of each entrance"+
                         									"\n" + "and opening");
		Instruction Instruction_3 = new SpecificInstruction("Shut off the power to prevent electrocution or fire hazards."+
                         									"\n" + "However if water is beginning to flood your home, do not"+
                         									"\n" + "shut off the power");
		Instruction generic_instruction = new Instruction("Call Emergency Number 112");
		
		ers.addInstruction(Instruction_1);
		ers.addInstruction(Instruction_2);
		ers.addInstruction(Instruction_3);
		
		Disaster earthquake = new Earthquake("Cathrina", true, new GPSCoordinates(50.6412, 5.5718), 4.0, 2.0);
		Disaster flood = new Flood("Tsunami", true, 
				Arrays.asList(new GPSCoordinates(60.0,6.0), 
						new GPSCoordinates(66.0,6.0), 
						new GPSCoordinates(60.0,12.0), 
						new GPSCoordinates(66.0,12.0)),
				Arrays.asList(new GPSCoordinates(60.0,7.0), 
						new GPSCoordinates(60.0,8.0), 
						new GPSCoordinates(60.0,9.0), 
						new GPSCoordinates(60.0,10.0)));
		
		ers.addDisaster(earthquake);
		ers.addDisaster(flood);	
	
		Timer timer = new Timer();
		Alert alert = new Alert(ers, timer);
		timer.scheduleAtFixedRate(alert, 1000, 30000);
		
		
		/**Reading from command line**/
		
		Scanner scanner = new Scanner(System.in);
		String cmd = "";

		while(!cmd.equals("0")){
			printHome();
			cmd = scanner.nextLine();
			int choice = -1;
			
			try 
			{
				
				choice = Integer.parseInt(cmd); 
				
			} catch (Exception e){	
				
				System.out.println("Could not read input from cmd");
			}
			
			if ( choice == 1 ) {
				printScreen( "GENERIC INSTRUCTIONS", generic_instruction.getDetails(),  "  1.Home 2.Exit" );
				
			}else if( choice == 2 ) {
					
				printScreen( "DISASTERS", "\n" + " 1.Home" + "\n" + "2. Earthquake" + "\n" + "3. Flood"+ "\n" +"4.Exit",  " Select OPTION A|B" );
				String disaster = scanner.nextLine();	
				
				if( disaster.equals("1")) {
					printHome();
					
				}
				else if( disaster.equals("2")) {
					
					printScreen( "EARTHQUAKE", earthquake.toString(),  "Together We Will Overcome" );
					
				}else if( disaster.equals("3") ) {
					
					printScreen( "FLOOD", flood.toString(),  " " );
					

				}else if( disaster.equals("4") ) {
					System.exit(1);
				}
				else {
					printHome();
				}
				
				//scanner.close();
			} else if( choice == 3) {
			
				printScreen( "EARTHQUAKE", ers.getSafePlaces().toString(),  "Together We Will Overcome" );
				//scanner.close();
			
			}else if ( choice == 4 ) {
		
				printScreen( "ALERT", "Aler Management" ,  "Stay Alert" );
				
				
			}else if ( choice ==  5) {
					System.exit(1);
			}
			else {
				printHome();
			}
		
				
		
		  }
		scanner.close();
	}
}
			/*case 0:
				System.out.println("Stay safe !\n");
				scanner.close();
				break;
			case 1:
				System.out.println("Start:\n-1:Flood\n-2:Earthquake\n");
				String activeDisaster = scanner.nextLine();
				int disaster=Integer.parseInt(activeDisaster);
				if(disaster==1){
					ers.addDisaster(flood);
					System.out.println(flood.toString()+"h\n has been enclenched\n");
					break;}
				else if (disaster==2){
					ers.addDisaster(earthquake);
					System.out.println(earthquake.toString() + "\n has been enclenched\n");
					break;}
			case 2:
				System.out.println(ers);
				break;
			case 3:
				System.out.println(ers.getSafePlaces());
				break;
			case 4:
				System.out.println("Insert position in this format: 55.02 5.02\n"); 
				String inputPos = scanner.nextLine(); 
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
				System.out.println(ers.getClosestSafePlace());
				break;
			case 6:
				if(ers.isInSafe(ers.getUser().getUserCurrentPosition()) == null) {
					System.out.println("You are safe\n");
				} else {
					System.out.println("You're not safe here\n");
				}
				break;
			case 7:
				System.out.println(ers.displayInstructions());
				break;
			default:
				System.out.println("Wrong scannerion, try again \n");
				break;
			}
			*/
		

