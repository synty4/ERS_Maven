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
	 * Set dangerousArea
	 * @pre: dangerous_area should not intercept with affected_area
	 */
	public void setDangerousArea(List<GPSCoordinates> dangerous_area) {
		this.dangerousArea = dangerous_area;
	}
	/**
	 * return dangerousArea List
	 */
	public List<GPSCoordinates> getDangerousArea() {
		return dangerousArea;
	}
	
	public void addDangerousArea(GPSCoordinates x) {
		this.dangerousArea.add(x);
	}
	
	public void removeDangerousArea(GPSCoordinates x) {
		this.dangerousArea.remove(x);
	}
	
	/**
	 * Check whether the current position is in dangerous area 
	 * dangerous areas surround affected areas
	 *  
	 * @param position
	 * @return  true if it is in false otherwise
	 */
    public boolean contains(GPSCoordinates pos) {    
        if(contains(pos, super.getAffectdArea()) || contains(pos, dangerousArea))
        	return true;
        else
        	return false;
    }
    private boolean contains(GPSCoordinates pos, List<GPSCoordinates> list){
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
