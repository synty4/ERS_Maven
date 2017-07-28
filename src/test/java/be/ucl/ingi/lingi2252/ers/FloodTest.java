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
    //affected zones polygon vertices
	GPSCoordinates A1 = new GPSCoordinates(40.0, 10.0);
	GPSCoordinates B1 = new GPSCoordinates(10.0, 40.0);
	GPSCoordinates C1 = new GPSCoordinates(10.0, 60.0);
	GPSCoordinates D1 = new GPSCoordinates(30.0, 70.0);
	GPSCoordinates E1 = new GPSCoordinates(40.0, 50.0);
	GPSCoordinates F1 = new GPSCoordinates(60.0, 30.0);
		
	//dangerous zones1 polygon vertices
	GPSCoordinates A2 = new GPSCoordinates(40.0,-10.0);
	GPSCoordinates B2 = new GPSCoordinates(0.0, 30.0);
	GPSCoordinates C2 = new GPSCoordinates(0.0, 60.0);
	GPSCoordinates D2 = new GPSCoordinates(30.0, 100.0);
	GPSCoordinates E2 = new GPSCoordinates(70.0, 90.0);
	GPSCoordinates F2 = new GPSCoordinates(90.0, 40.0);
		
	//dangerous zones2 polygon vertices
	GPSCoordinates A3 = new GPSCoordinates(40.0,-20.0);
	GPSCoordinates B3 = new GPSCoordinates(-10.0, 30.0);
	GPSCoordinates C3 = new GPSCoordinates(-10.0, 60.0);
	GPSCoordinates D3 = new GPSCoordinates(30.0, 110.0);
	GPSCoordinates E3 = new GPSCoordinates(70.0, 100.0);
	GPSCoordinates F3 = new GPSCoordinates(100.0, 40.0);
		
	Area affectedArea = new Area(Arrays.asList(A1, B1, C1, D1, E1, F1), AreaType.Affected);
	Area dangerousArea1 = new Area(Arrays.asList(A2, B2, C2, D2, E2, F2), AreaType.Dangerous);
	Area dangerousArea2 = new Area(Arrays.asList(A3, B3, C3, D3, E3, F3), AreaType.Dangerous);
		
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
