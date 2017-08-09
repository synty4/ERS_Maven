/**
 * 
 */
package be.ucl.ingi.lingi2252.ers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import be.ucl.ingi.lingi2252.disaster.AreaType;
import be.ucl.ingi.lingi2252.disaster.ConcreteDisaster;
import be.ucl.ingi.lingi2252.disaster.Earthquake;
import be.ucl.ingi.lingi2252.disaster.Flood;
import be.ucl.ingi.lingi2252.instruction.Instruction;

/**
 * @author synty4
 *
 */
public class DisasterTest {

	
	/*
	 * GPS coordinates generated randomly with UCL as the center and a radius of 100 km
	 * with Random point online generator tool 
	 * http://www.geomidpoint.com/random/
	 */
	GPSCoordinates UCL = new GPSCoordinates(50.66968749999999, 4.615590900000029); //epicenter
	GPSCoordinates nextUCL1 = new GPSCoordinates(51.2149331, 3.95192671);
	GPSCoordinates nextUCL2 = new GPSCoordinates(50.73462606, 4.79128389);
	
	Area affectedArea = new Area(Arrays.asList(new GPSCoordinates(40.0, 10.0), new GPSCoordinates(10.0, 40.0), 
			  new GPSCoordinates(10.0, 60.0), new GPSCoordinates(30.0, 70.0),
			  new GPSCoordinates(40.0, 50.0), new GPSCoordinates(60.0, 30.0)), AreaType.Affected);
	Area dangerousArea1 = new Area(Arrays.asList(new GPSCoordinates(40.0,-10.0), new GPSCoordinates(0.0, 30.0),
			  new GPSCoordinates(0.0, 60.0), new GPSCoordinates(30.0, 100.0), 
			  new GPSCoordinates(70.0, 90.0), new GPSCoordinates(90.0, 40.0)), AreaType.Dangerous);
	List<Area> affectedAreaList = new ArrayList<Area>();
	List<Area> dangerousAreaList = new ArrayList<Area>();
	
	ArrayList<Instruction> floodInstrList 	  = new ArrayList<Instruction>(Arrays.asList(new Instruction("Move immediately to higher ground or stay on high ground.\n")));
	ArrayList<Instruction> earthquakeInstrList 	  = new ArrayList<Instruction>(Arrays.asList(new Instruction("Move away from windows and unsecured tall furniture")));
	ConcreteDisaster flood = new Flood("Haute Loire flood", true, affectedAreaList, dangerousAreaList,  floodInstrList);

	@Test
	public void disaster_isAcitveTest(){
		assertTrue("flood should be active",flood.isActive());
		flood.setActive(false);	
		assertFalse("flood should not be active", flood.isActive());
		flood.setActive(true);
		assertTrue("flood should be active",flood.isActive());
		
	}

	@Test
	public void disaster_getAffectdAreaListTest() {
		assertEquals(flood.getAffectdAreaList(), Arrays.asList());
		//add an affected area
		flood.addAffectedArea(affectedArea);
		assertEquals(flood.getAffectdAreaList(), Arrays.asList(affectedArea));
	}


	@Test
	public void add_removeAffectedAreaTest(){
		//add an affected area
		flood.addAffectedArea(affectedArea);
		assertEquals(1, flood.getAffectdAreaList().size());
		//remove an affected area
		flood.removeAffectedArea(affectedArea);
		assertEquals(0, flood.getAffectdAreaList().size());
	}

	
	@Test
	public void disater_containsTest() {
		

		ConcreteDisaster earthquake = new Earthquake("UCL earthquake", true, UCL, 10, 100, earthquakeInstrList);
		
		assertFalse("pos (30.0, 30.0) should be not the affected flooded zone", flood.contains(new GPSCoordinates(30.0, 30.0)));
		
		//add an affected area
		flood.addAffectedArea(affectedArea);
		
		assertTrue("pos (50.0, 30.0) should be in the affected flooded zone", flood.contains(new GPSCoordinates(50.0, 30.0)));
		assertTrue("pos (20.0, 60.0) should be in the affected flooded zone", flood.contains(new GPSCoordinates(20.0, 60.0)));
		assertFalse("pos (40.0, 80.0) should not be in the affected flooded zone", flood.contains(new GPSCoordinates(40.0, 80.0)));
		assertTrue("pos (10.0, 10.0) should not be in the affected flooded zone", flood.contains(new GPSCoordinates(10.0, 10.0)));
		//assertTrue("pos (51.2149331, 3.95192671) should be in the affected earthquake zone", earthquake.contains(new GPSCoordinates(51.2149331, 3.95192671)));
		//assertTrue("pos (50.73462606, 50.73462606) should be in the affected earthquake zone", earthquake.contains(new GPSCoordinates(50.73462606, 50.73462606)));
	}
	
}
