/**
 * 
 */
package be.ucl.ingi.lingi2252.ers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author synty4
 *
 */
public class DisasterTest {

	//affected zones polygon vertices
	GPSCoordinates A1 = new GPSCoordinates(40.0, 10.0);
	GPSCoordinates B1 = new GPSCoordinates(10.0, 40.0);
	GPSCoordinates C1 = new GPSCoordinates(10.0, 60.0);
	GPSCoordinates D1 = new GPSCoordinates(30.0, 70.0);
	GPSCoordinates E1 = new GPSCoordinates(40.0, 50.0);
	GPSCoordinates F1 = new GPSCoordinates(60.0, 30.0);
	//dangerous zones polygon vertices
	GPSCoordinates A2 = new GPSCoordinates(40.0,-10.0);
	GPSCoordinates B2 = new GPSCoordinates(0.0, 30.0);
	GPSCoordinates C2 = new GPSCoordinates(0.0, 60.0);
	GPSCoordinates D2 = new GPSCoordinates(30.0, 100.0);
	GPSCoordinates E2 = new GPSCoordinates(70.0, 90.0);
	GPSCoordinates F2 = new GPSCoordinates(90.0, 40.0);

	/*
	 * GPS coordinates generated randomly with UCL as the center and a radius of 100 km
	 * with Random point online generator tool 
	 * http://www.geomidpoint.com/random/
	 */
	GPSCoordinates UCL = new GPSCoordinates(50.66968749999999, 4.615590900000029); //epicenter
	GPSCoordinates nextUCL1 = new GPSCoordinates(51.2149331, 3.95192671);
	GPSCoordinates nextUCL2 = new GPSCoordinates(50.73462606, 4.79128389);
	
	//disaster
	Area affectedArea = new Area(Arrays.asList(A1, B1, C1, D1, E1, F1), AreaType.Affected);
	Area dangerousArea = new Area(Arrays.asList(A2, B2, C2, D2, E2, F2), AreaType.Dangerous);
	List<Area> affectedAreaList = new ArrayList<Area>();
	List<Area> dangerousAreaList = new ArrayList<Area>();


	@Test
	public void disaster_isAcitveTest(){
		Disaster flood = new Flood("Haute Loire flood", true, affectedAreaList, dangerousAreaList);
		assertTrue("flood should be active",flood.isActive());
		flood.setActive(false);
		assertFalse("flood should not be active", flood.isActive());
		flood.setActive(true);
		assertTrue("flood should be active",flood.isActive());
	}

	@Test
	public void disaster_getAffectdAreaListTest() {
		Disaster flood = new Flood("Haute Loire flood", true, affectedAreaList, dangerousAreaList);
		assertEquals(flood.getAffectdAreaList(), Arrays.asList());
		//add an affected area
		flood.addAffectedArea(affectedArea);
		assertEquals(flood.getAffectdAreaList(), Arrays.asList(affectedArea));
	}


	@Test
	public void add_removeAffectedAreaTest(){
		Disaster flood = new Flood("Haute Loire flood", true, affectedAreaList, dangerousAreaList);
		//add an affected area
		flood.addAffectedArea(affectedArea);
		assertEquals(1, flood.getAffectdAreaList().size());
		//remove an affected area
		flood.removeAffectedArea(affectedArea);
		assertEquals(0, flood.getAffectdAreaList().size());
	}

	
	@Test
	public void disater_containsTest() {
		Disaster flood = new Flood("Haute Loire flood", true, affectedAreaList, dangerousAreaList);
		Disaster earthquake = new Earthquake("UCL earthquake", true, UCL, 10, 100);
		
		assertFalse("pos (30.0, 30.0) should be not the affected flooded zone", flood.contains(new GPSCoordinates(30.0, 30.0)));
		
		//add an affected area
		flood.addAffectedArea(affectedArea);
		
		assertTrue("pos (50.0, 30.0) should be in the affected flooded zone", flood.contains(new GPSCoordinates(50.0, 30.0)));
		assertTrue("pos (20.0, 60.0) should be in the affected flooded zone", flood.contains(new GPSCoordinates(20.0, 60.0)));
		assertFalse("pos (40.0, 80.0) should not be in the affected flooded zone", flood.contains(new GPSCoordinates(40.0, 80.0)));
		assertTrue("pos (10.0, 10.0) should not be in the affected flooded zone", flood.contains(new GPSCoordinates(10.0, 10.0)));
		assertTrue("pos (51.2149331, 3.95192671) should be in the affected earthquake zone", earthquake.contains(new GPSCoordinates(51.2149331, 3.95192671)));
		assertTrue("pos (50.73462606, 50.73462606) should be in the affected earthquake zone", earthquake.contains(new GPSCoordinates(50.66272788, 4.59574396)));
	}
}
