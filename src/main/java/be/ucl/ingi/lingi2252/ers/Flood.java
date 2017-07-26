package be.ucl.ingi.lingi2252.ers;
import java.util.List;

public class Flood extends Disaster {
	
	private List<GPSCoordinates> dangerousArea;
	private List <GPSCoordinates> affectedArea;	
	
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
		this.affectedArea = affectedArea;
		this.dangerousArea = dangerousArea;  
	}
	
	/**
	 * Set dangerousArea to dangerous_area
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
	
	
	
	public void displayAffectedFloodAreas() {
		
		for(int i=0; i < affectedArea.size(); i++) {
			//displayonMap
		}
	
	
	}	
	
	/**
	 * this method check if the current position is in a safe area or not
	 * @param position
	 * @return true if in an affected or dangerous area otherwise false
	 */

    @Override
    public boolean contains(GPSCoordinates pos) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = affectedArea.size() - 1; i < affectedArea.size(); j = i++) {
            if ((affectedArea.get(i).getLongitude() > pos.getLongitude()) != (affectedArea.get(j).getLongitude() > pos.getLongitude())
                    && (pos.getLatitude() < (affectedArea.get(j).getLatitude() - affectedArea.get(i).getLatitude()) * (pos.getLongitude() - affectedArea.get(i).getLongitude())
                    / (affectedArea.get(j).getLongitude() - affectedArea.get(i).getLongitude()) + affectedArea.get(i).getLatitude())) {
                result = !result;
            }
        }
        return result;
    }
    
    @Override
	public String toString() {
			
		return "Flood: " + this.getDisasterName() + "[" + " Active: "+ this.isActive() +"|"+ " List of Affected Areas: "+ this.getAffectdArea()  +"|"+  " List of dangerousArea : " + this.getDangerousArea() + "]";   
	}

}
