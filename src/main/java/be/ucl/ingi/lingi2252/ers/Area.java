package be.ucl.ingi.lingi2252.ers;

import java.util.List;

import be.ucl.ingi.lingi2252.disaster.AreaType;

/**
 * This class represent a convex area 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class Area {
	private List<GPSCoordinates> coordinates;
	private AreaType type;
	
	public Area(List<GPSCoordinates> area, AreaType type){
		this.coordinates = area;
		this.type = type;
	}
	/**
	 * get convex area
	 * @return coordinates
	 */
	public List<GPSCoordinates> getConvexArea(){
		return coordinates;
	}
	/**
	 * set convex area
	 * @param area
	 */
	public void setConvexArea(List<GPSCoordinates> area){
		coordinates = area;
	}
	/**
	 * get area type
	 * @return type
	 */
	public AreaType getAreaType(){
		return type;
	}
	/**
	 * set area type
	 * @param type
	 */
	public void setAreaType(AreaType type){
		this.type = type;
	}
}
