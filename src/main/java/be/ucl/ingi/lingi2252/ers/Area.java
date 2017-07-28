package be.ucl.ingi.lingi2252.ers;

import java.util.List;

/**
 * This class represent a convex area 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class Area {
	private List<GPSCoordinates> convexArea;
	private AreaType type;
	
	public Area(List<GPSCoordinates> area, AreaType type){
		this.convexArea = area;
		this.type = type;
	}
	/**
	 * get convex area
	 * @return convexArea
	 */
	public List<GPSCoordinates> getConvexArea(){
		return convexArea;
	}
	/**
	 * set convex area
	 * @param area
	 */
	public void setConvexArea(List<GPSCoordinates> area){
		convexArea = area;
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
