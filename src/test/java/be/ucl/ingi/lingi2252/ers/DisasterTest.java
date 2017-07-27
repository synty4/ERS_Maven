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
	
	//dangerous zones polygon vertices
	GPSCoordinates A1 = new GPSCoordinates(40.0, 10.0);
	GPSCoordinates B1 = new GPSCoordinates(10.0, 40.0);
	GPSCoordinates C1 = new GPSCoordinates(10.0, 60.0);
	GPSCoordinates D1 = new GPSCoordinates(30.0, 70.0);
	GPSCoordinates E1 = new GPSCoordinates(40.0, 50.0);
	GPSCoordinates F1 = new GPSCoordinates(60.0, 30.0);
	
	//affected zones polygon vertices
	GPSCoordinates A2 = new GPSCoordinates(40.0,-10.0);
	GPSCoordinates B2 = new GPSCoordinates(0.0, 30.0);
	GPSCoordinates C2 = new GPSCoordinates(0.0, 60.0);
	GPSCoordinates D2 = new GPSCoordinates(30.0, 100.0);
	GPSCoordinates E2 = new GPSCoordinates(70.0, 90.0);
	GPSCoordinates F2 = new GPSCoordinates(90.0, 40.0);
	
	 /*
	  * GPS coordinates generated randomly with UCL as the center and a radius of 100 km
	  * with Random poitn online generator tool 
	  * http://www.geomidpoint.com/random/
	  */
	 
	
	GPSCoordinates UCL = new GPSCoordinates(50.66968749999999, 4.615590900000029); //epicenter
	GPSCoordinates nextUCL1 = new GPSCoordinates(51.2149331, 3.95192671);
	GPSCoordinates nextUCL2 = new GPSCoordinates(50.73462606, 4.79128389);
	
	
	Disaster floodNull = new Flood("void", false,null, null);
	
	Disaster flood = new Flood("Haute Loire flood", true, Arrays.asList(A1, B1, C1, D1, E1, F1), Arrays.asList(A2, B2, C2, D2, E2, F2));
	Disaster earthquake = new Earthquake("UCL earthquake", true, UCL, 10, 100);
	@Test
    public void disaster_isAcitveTest(){
		
        assertTrue("flood should be active",flood.isActive());
        flood.setActive(false);
        assertFalse("flood should not be active", flood.isActive());
        flood.setActive(true);
        assertTrue("flood should be active",flood.isActive());
        
    }

	@Test
	public void disaster_getAffectdAreaTest() {
		
		assertEquals("Flood affectedArea should be [(40.0, 10.0), (10.0, 40.0), (10.0, 60.0), (30.0, 70.0), (40.0, 50.0), (60.0, 30.0) ]",flood.getAffectdArea(), Arrays.asList(A1, B1, C1, D1, E1, F1));
		
	}

	
	@Test
	public void disaster_setAffectedArea(){
		
		assertEquals("Flood affectedArea should be [(40.0, 10.0), (10.0, 40.0), (10.0, 60.0), (30.0, 70.0), (40.0, 50.0), (60.0, 30.0) ]",flood.getAffectdArea(), Arrays.asList(A1, B1, C1, D1, E1, F1));
		flood.setAffectdArea(Arrays.asList(A2, B2, C2, D2, E2, F2));
		
		assertEquals("Flood affectedArea should be [(40.0,-10.0), (0.0, 30.0), (0.0, 60.0), (30.0, 100.0), (70.0, 90.0), (90.0, 40.0) ]",flood.getAffectdArea(), Arrays.asList(A2, B2, C2, D2, E2, F2));
		flood.setAffectdArea(Arrays.asList(A1, B1, C1, D1, E1, F1));
		assertEquals("Flood affectedArea should be [(40.0, 10.0), (10.0, 40.0), (10.0, 60.0), (30.0, 70.0), (40.0, 50.0), (60.0, 30.0) ]",flood.getAffectdArea(), Arrays.asList(A1, B1, C1, D1, E1, F1));
		
	}
	
	@Test
	public void disater_containsAffectedArea() {
		//x
		assertTrue("pos (30.0, 30.0) should be in the affected flooded zone", flood.contains_affectedArea(new GPSCoordinates(30.0, 30.0)));
		//Y
		assertTrue("pos (50.0, 30.0) should be in the affected flooded zone", flood.contains_affectedArea(new GPSCoordinates(50.0, 30.0)));
		//Z
		assertTrue("pos (20.0, 60.0) should be in the affected flooded zone", flood.contains_affectedArea(new GPSCoordinates(20.0, 60.0)));
		
		//I
		assertFalse("pos (40.0, 80.0) should not be in the affected flooded zone", flood.contains_affectedArea(new GPSCoordinates(40.0, 80.0)));
		//J
		assertFalse("pos (10.0, 10.0) should not be in the affected flooded zone", flood.contains_affectedArea(new GPSCoordinates(10.0, 10.0)));
		//assertTrue("pos (51.2149331, 3.95192671) should be in the affected earthquake zone", earthquake.contains_affectedArea(new GPSCoordinates(51.2149331, 3.95192671)));
		//assertTrue("pos (50.73462606, 50.73462606) should be in the affected earthquake zone", earthquake.contains_affectedArea(new GPSCoordinates(50.73462606, 50.73462606)));
		
	}
	

}
