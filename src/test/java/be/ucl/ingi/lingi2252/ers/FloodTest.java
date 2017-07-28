/**
 * 
 */

package be.ucl.ingi.lingi2252.ers;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
;

/**
 * @author synty4
 *
 */
public class FloodTest {
	
	Area affectedArea = new Area(Arrays.asList(new GPSCoordinates(40.0, 10.0), new GPSCoordinates(10.0, 40.0), 
											  new GPSCoordinates(10.0, 60.0), new GPSCoordinates(30.0, 70.0),
											  new GPSCoordinates(40.0, 50.0), new GPSCoordinates(60.0, 30.0)), AreaType.Affected);
	Area dangerousArea1 = new Area(Arrays.asList(new GPSCoordinates(40.0,-10.0), new GPSCoordinates(0.0, 30.0),
											  new GPSCoordinates(0.0, 60.0), new GPSCoordinates(30.0, 100.0), 
											  new GPSCoordinates(70.0, 90.0), new GPSCoordinates(90.0, 40.0)), AreaType.Dangerous);
	Area dangerousArea2 = new Area(Arrays.asList(new GPSCoordinates(40.0,-20.0), new GPSCoordinates(-10.0, 30.0), 
											  new GPSCoordinates(-10.0, 60.0), new GPSCoordinates(30.0, 110.0), 
											  new GPSCoordinates(70.0, 100.0), new GPSCoordinates(100.0, 40.0)), AreaType.Dangerous);
		
	List<Area> affectedAreaList = new ArrayList<Area>();
	List<Area> dangerousAreaList = new ArrayList<Area>();
	Flood flood = new Flood("Haute Loire flood", true, affectedAreaList, dangerousAreaList);

	@Test
	public void getDangerousAreaListTest(){
		dangerousAreaList.add(dangerousArea1);
		dangerousAreaList.add(dangerousArea2);
		
		flood.addDangerousArea(dangerousArea1);
		flood.addDangerousArea(dangerousArea2);
		assertEquals("Dangerous zones should be"+ dangerousAreaList, flood.getDangerousAreaList(),  dangerousAreaList);
		flood.removeDangerousArea(dangerousArea1);
		dangerousAreaList.remove(dangerousArea1);
		assertEquals("Dangerous zones should be"+ dangerousAreaList, flood.getDangerousAreaList(), dangerousAreaList);
	}
	@Test
	public void add_removeDangerousAreaTest(){

		//add an dangerous area
		flood.addDangerousArea(dangerousArea1);
		assertEquals(1, flood.getDangerousAreaList().size());
		//remove an affected area
		flood.removeDangerousArea(dangerousArea1);
		assertEquals(0, flood.getDangerousAreaList().size());
	}
	
	@Test
	public void containsTest() {
		flood.addAffectedArea(affectedArea);
		flood.addDangerousArea(dangerousArea1);
		assertTrue ("pos (10.0, 10.0) is in unsafe flood zones",      flood.contains(new GPSCoordinates(10.0, 10.0)));
		assertFalse("pos (100.0, 40.0) is not in unsafe flood zones", flood.contains(new GPSCoordinates(100.0, 40.0)));
		//assertFalse("pos (-10.0, 30.0) is not in unsafe flood zones", flood.contains(new GPSCoordinates(-10.0, 30.0)));
		
	}
	

}
