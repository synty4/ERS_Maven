package be.ucl.ingi.lingi2252.ers;
import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import be.ucl.ingi.lingi2252.disaster.AreaType;
import be.ucl.ingi.lingi2252.disaster.ConcreteDisaster;
import be.ucl.ingi.lingi2252.disaster.Earthquake;
import be.ucl.ingi.lingi2252.disaster.Flood;
import be.ucl.ingi.lingi2252.instruction.Instruction;

public class ERSTest {

	//disaster
	Area affectedArea = new Area(Arrays.asList(new GPSCoordinates(60.0,6.0), 
			new GPSCoordinates(66.0,6.0), 
			new GPSCoordinates(60.0,12.0), 
			new GPSCoordinates(66.0,12.0)), AreaType.Affected);
	Area dangerousArea = new Area(Arrays.asList(new GPSCoordinates(60.0,7.0), 
			new GPSCoordinates(60.0,8.0), 
			new GPSCoordinates(60.0,9.0), 
			new GPSCoordinates(60.0,10.0)), AreaType.Dangerous);
	
	Instruction iA 				      = new Instruction("Move immediately to higher ground or stay on high ground.");
	Instruction iB 					  = new Instruction("Move away from windows and unsecured tall furniture");
	ArrayList<Instruction> floodInstrList 	  = new ArrayList<Instruction>(Arrays.asList(iB));
	ArrayList<Instruction> earthquakeInstrList = new ArrayList<Instruction>(Arrays.asList(iA));
	//flood = 
	ConcreteDisaster dA 			  = new Earthquake("Cathrina", true, new GPSCoordinates(50.6412, 5.5718), 4.0, 2.0, earthquakeInstrList);
	ConcreteDisaster dB 			  = new Flood("Tsunami", true, Arrays.asList(affectedArea), Arrays.asList(dangerousArea), floodInstrList);
	SafePlace spA 					  = new SafePlace("Hopital Saint Luc", new GPSCoordinates(50.4541, 3.9523), PlaceType.Hospital); //mons
	SafePlace spB 					  = new SafePlace("Blocry", new GPSCoordinates(50.7057, 4.7484), PlaceType.Gym);//ottignie

	
	
	@Test
	public void add_remove_SafePlaceTest(){	
		ERS ers=new ERS();
		//add a safe place
		ers.addSafePlace(spA);
		assertEquals(1, ers.getSafePlaces().size());
		//remove a safe place
		ers.removeSafePlace(spA);
		assertEquals(0, ers.getSafePlaces().size());
	}

	@Test
	public void add_remove_DisasterTest(){
		ERS ers=new ERS();
		//add disaster
		ers.addDisaster(dA);
		assertEquals(1, ers.getDisasters().size());
		//remove disaster
		ers.removeDisaster(dA);
		assertEquals(0, ers.getDisasters().size());
	}

	@Test
	public void add_remove_InstructionTest(){
		ERS ers = new ERS();
		ers.addGeneralInstruction(iA);
		assertEquals(1, ers.getGeneralInstructions().size());
		ers.removeGeneralInstruction(iA);
		assertEquals(0, ers.getGeneralInstructions().size());
	}

	@Test 
	public void displayInstructionTest(){
		
		assertTrue("Instruction must be Call your mom", iA.getDetails().equals("Call your mom"));
		assertTrue("Test flood related instructions", ("Flood Safety Instructions:\n"+iA.getDetails()).equals(
				 dA.displayInstructions()));
	}

	@Test
	public void getActiveDisasterTest(){
		ERS ers=new ERS();
		assertEquals(0,ers.countDisasters());
		ers.addDisaster(dA);
		ers.addDisaster(dB);
		assertEquals(2,ers.countDisasters());
	}
	@Test
	public void getClosestSafePlace() {
		ERS ers=new ERS();
		ers.addSafePlace(spA);//mons
		ers.addSafePlace(spB);//ottignie
		GPSCoordinates  currentPosition = new GPSCoordinates(50.8466,4.3528); //1000 bruxelles 
		ers.getUser().setUserCurrentPossition(currentPosition);
		assertEquals(ers.getClosestSafePlace().getPlacePosition(), spB.getPlacePosition());
	}

	@Test
	public void isInSafeTest(){
		ERS ers=new ERS();
		//add disaster
		ers.addDisaster(dA);
		ers.addDisaster(dB);
		ers.getUser().setUserCurrentPossition(new GPSCoordinates(0.0, 0.0));
		assertEquals(null, ers.isUnSafe(ers.getUser().getUserCurrentPosition()));
		ers.getUser().setUserCurrentPossition(new GPSCoordinates(50.6412, 5.5718));
		assertEquals(true, (ers.isUnSafe(ers.getUser().getUserCurrentPosition())!=null));
	}
}
