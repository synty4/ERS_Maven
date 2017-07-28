package be.ucl.ingi.lingi2252.ers;
import java.util.List;

/**
 * This class implements a disaster 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */

public abstract class Disaster {
	
	private String disasterName;
	private Boolean active;
	private List<Area> affectdAreaList;
	
	/**
	 * Constructor
	 * @param disasterName
	 * @param active
	 * 
	 * **/
	public Disaster(String disasterName, Boolean active) {
		this.disasterName = disasterName;
		this.active       = active;
	}
	
	/**
	 * Get the disater's name
	 * @return disasterName
	 */
	
	public String getDisasterName() {
		return disasterName;
	}
	
	/**
	 * set the disaster
	 * @param disasterName
	 */
	public void setDisasterName(String disasterName) {
		this.disasterName = disasterName;
	}
	
	/**
	 * Check if the disaster is active
	 * @return active
	 */
	
	public Boolean isActive() {
		return active;
	}
	
	/**
	 * set a disaster
	 * @param active
	 */
	public void setActive(Boolean occ) {
		this.active = occ;
	}
	
	/**
	 * get affected area list
	 * */
	public List<Area> getAffectdAreaList() {
		return affectdAreaList;
	}
	/**
	 * set affected area list
	 * @param area
	 */
	public void setAffectedAreaList(List<Area> area){
		affectdAreaList = area;
	}
	
	/**
	 * add an affected area
	 * @param area
	 */
	public void addAffectedArea(Area area) {
		this.getAffectdAreaList().add(area);
	}
	/**
	 * remove an affected area
	 * @param area
	 */
	public void removeAffectedArea(Area area) {
		this.getAffectdAreaList().remove(area);
	}
	/**
	 * this method check if the current positon is in a safe area or not
	 * @param position
	 * @return true if in an affected if in affected area
	 */
	public abstract boolean contains(GPSCoordinates pos);
}
