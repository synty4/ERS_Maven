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
	List<GPSCoordinates> affectdArea;
	
	public Disaster(String distr_name, Boolean occ) {
		disasterName = distr_name;
		active     = occ;
	}
	
	public String getDisasterName() {
		return disasterName;
	}

	public void setDisasterName(String disasterName) {
		this.disasterName = disasterName;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean occ) {
		this.active = occ;
	}
	public List<GPSCoordinates> getAffectdArea() {
		return affectdArea;
	}
	public void setAffectdArea(List<GPSCoordinates> affectdArea) {
		this.affectdArea = affectdArea;
	}
	
	/**
	 * this method check if the current positon is in a safe area or not
	 * @param position
	 * @return true if in an affected or dangerous area otherwise false
	 */
	public abstract boolean contains(GPSCoordinates pos);
}
