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
	private List<GPSCoordinates> affectdArea;
	
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
	public List<GPSCoordinates> getAffectdArea() {
		return affectdArea;
	}
	/**
	 * set affectedArea to affected_area
	 * @param affectdArea
	 */
	
	public void setAffectdArea(List<GPSCoordinates> affected_area) {
		this.affectdArea = affected_area;
	}
	
	
	/**
	 * this method check if the current positon is in a safe area or not
	 * @param position
	 * @return true if in an affected or dangerous area otherwise false
	 */
	public abstract boolean contains(GPSCoordinates pos);
	
	
}
