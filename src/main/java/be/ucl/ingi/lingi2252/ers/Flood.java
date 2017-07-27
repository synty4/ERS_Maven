package be.ucl.ingi.lingi2252.ers;
import java.util.List;

public class Flood extends Disaster {
	
	private List<GPSCoordinates> dangerousArea;	
	
	/**
	 * Constructor 
	 * @param disaterName
	 * @param active
	 * @param affectedArea 
	 * @param dangerousArea
	 * **/
	public Flood( String disasterName, 
				  Boolean active,
				  List <GPSCoordinates> affectedArea, 
				  List<GPSCoordinates> dangerousArea ){	
		super(disasterName, active);
		super.setAffectdArea(affectedArea);
		this.dangerousArea = dangerousArea;  
	}
	
	/**
	 * Set dangerousArea to dangerous_area
	 * @pre: dangerous_area should not intercept with affected_area
	 * **/
	
	public void setDangerousArea(List<GPSCoordinates> dangerous_area) {
		this.dangerousArea = dangerous_area;
	}
	
	/**
	 * return dangerousArea List
	 * **/
	
	public List<GPSCoordinates> getDangerousArea() {
		return dangerousArea;
	}
	
	private void addAffectedArea(GPSCoordinates x) {
		this.getAffectdArea().add(x);
	}
	private void removeAffectedArea(GPSCoordinates x) {
		this.getAffectdArea().remove(x);
	}
	
	private void addDangerousArea(GPSCoordinates x) {
		this.dangerousArea.add(x);
	}
	private void removeDangerousArea(GPSCoordinates x) {
		this.dangerousArea.remove(x);
	}	
	public boolean inAffectedFloodAreas(GPSCoordinates position) {
	
		return false;
	}	
	
	
/*
    public boolean contains(GPSCoordinates position, ListGPSCoordinates list ) {
        int i;
        int j;
        boolean result = false;
        List<GPSCoordinates> affectedArea = super.getAffectdArea();
        for (i = 0, j = affectedArea.size() - 1; i < affectedArea.size(); j = i++) {
            if ((affectedArea.get(i).getLongitude() > position.getLongitude()) != (affectedArea.get(j).getLongitude() > position.getLongitude())
                    && (position.getLatitude() < (affectedArea.get(j).getLatitude() - affectedArea.get(i).getLatitude()) * (position.getLongitude() - affectedArea.get(i).getLongitude())
                    / (affectedArea.get(j).getLongitude() - affectedArea.get(i).getLongitude()) + list.get(i).getLatitude())) {
                result = !result;
            }
        }
        return result;
    }
*/	
	/**
	 * Check if the current position is in an affected area or not
	 * @param position
	 * @return true if in an affected area false otherwise
	 */
	@Override
	public boolean contains_affectedArea(GPSCoordinates pos) {
		return contains(pos, this.getAffectdArea());
	}

	/**
	 * Check whether the current position is in dangerous area 
	 * dangerous areas surround affected areas
	 * i.e a position found in dangerous areas cannot be in affected areas
	 *  
	 * @param position
	 * @return  true if it is in false otherwise
	 */
	public boolean contains_dangerousArea(GPSCoordinates pos) {
		return ( contains(pos, this.getDangerousArea()) && ! contains(pos, this.getAffectdArea()));
	}
	
	/**
	 * Check whether pos  is in the convex area formed by joining GPSCoordinates in list
	 * @param position
	 * @return true if it is in false otherwise
	 */
    public boolean contains(GPSCoordinates pos, List<GPSCoordinates> list) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = list.size() - 1; i < list.size(); j = i++) {
            if ((list.get(i).getLongitude() > pos.getLongitude()) != (list.get(j).getLongitude() > pos.getLongitude())
                    && (pos.getLatitude() < (list.get(j).getLatitude() - list.get(i).getLatitude()) * (pos.getLongitude() - list.get(i).getLongitude())
                    / (list.get(j).getLongitude() - list.get(i).getLongitude()) + list.get(i).getLatitude())) {
                result = !result;
            }
        }
        return result;
    }
    
    @Override
	public String toString() {
			
		return "[Flood: " + this.getDisasterName() + ", Active: "+ this.isActive() + ", List of Affected Areas: "+ this.getAffectdArea() + ", List of dangerousArea: " + this.getDangerousArea() + "]";   
	}

}
