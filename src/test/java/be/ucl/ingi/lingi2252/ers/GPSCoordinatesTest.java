/**
 * 
 */
package be.ucl.ingi.lingi2252.ers;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author synty4
 *
 */
public class GPSCoordinatesTest {
	
	
	@Test 
	public void get_set_Latitude_Longitude_Test() {
		
		GPSCoordinates  x = new GPSCoordinates(-80.9, 100.89);
		assertTrue("Latitude must be -80.9",   x.getLatitude()  == -80.9);
		assertTrue("Longitude must be 100.89", x.getLongitude() == 100.89);
		x.setLatitude(60.0);
		x.setLongitude(-56.23);
		assertTrue("Latitude must be -60.0",   x.getLatitude()  ==  60.0);
		assertTrue("Longitude must be -56.23", x.getLongitude() == -56.23);
		
	}
	
	@Test
	public void setAffected_isAffected() {
		GPSCoordinates  x = new GPSCoordinates(-80.9, 100.89);
		x.setAffected(true);
		assertTrue("Position must be affected", x.isAffected());
	}
	
	@Test
	public void isEqualTest() {
		
		GPSCoordinates  x = new GPSCoordinates(-80.9, 100.89);
		GPSCoordinates  y = new GPSCoordinates(-80.9, 100.89);
		GPSCoordinates  z = new GPSCoordinates(-80.9, -0.89);
		GPSCoordinates  w = new GPSCoordinates(56.12, 100.89);
		GPSCoordinates  a = new GPSCoordinates(56.12, 100.89);
		
		assertTrue ("pos"+x.toString()+" must be equal to pos "+y.toString(),   x.isEqual(y));
		assertFalse("Must be false",  x.isEqual(z));
		assertFalse("Must be false",  x.isEqual(w));
		assertFalse("Must be false",  x.isEqual(a));	
	}

}
