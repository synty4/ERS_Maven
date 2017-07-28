package be.ucl.ingi.lingi2252.ers;
import java.util.ArrayList;
import java.util.List;

public class Flood extends Disaster {
	
	private List<Area> dangerousAreaList;	
	
	/**
	 * Constructor 
	 * @param disaterName
	 * @param active
	 * @param affectedAreaList 
	 * @param dangerousAreaList
	 * **/
	public Flood( String disasterName, 
				  Boolean active,
				  List <Area> affectedArea, 
				  List<Area> dangerousArea ){	
		super(disasterName, active);
		super.setAffectedAreaList(affectedArea);
		this.dangerousAreaList = dangerousArea;  
	}
	
	/**
	 * get dangerous area(s) list
	 * @return dangerousArea List
	 */
	public List<Area> getDangerousAreaList() {
		return dangerousAreaList;
	}
	/**
	 * add a dangerous area
	 * @param area
	 */
	public void addDangerousArea(Area area) { 
		this.dangerousAreaList.add(area);
	}
	
	public void removeDangerousArea(Area area) {
		this.dangerousAreaList.remove(area);
	}
	
	/**
	 * Check whether the current position is in dangerous area 
	 * dangerous areas surround affected areas
	 *  
	 * @param position
	 * @return  true if it is in false otherwise
	 */
    public boolean contains(GPSCoordinates pos) { 
    	
    	List<Area> areas = new ArrayList<Area>(super.getAffectdAreaList());
    	//join affected area(s) list and dangerous area(s) list
    	areas.addAll(dangerousAreaList);
    	
        for (Area area : areas) {
			if(contains(pos, area.getConvexArea()))
				return true;		
		}
        return false;
    }
    /**
     * check if pos is in the convex area
     * @param pos
     * @param list
     * @return true or false 
     */
    private boolean contains(GPSCoordinates pos, List<GPSCoordinates> list){
    	int i;
        int j;
        boolean result = false;
        for (i = 0, j = list.size() - 1; i < list.size(); j = i++) {
            if ((list.get(i).getLongitude() > pos.getLongitude()) != (list.get(j).getLongitude() > pos.getLongitude())
                    && (pos.getLatitude() < (list.get(j).getLatitude() - list.get(i).getLatitude()) * (pos.getLongitude() - list.get(i).getLongitude())
                    / (list.get(j).getLongitude() - list.get(i).getLongitude()) + list.get(i).getLatitude())) {
                result = true;
            }
        }
    	return result;
    }
    
    @Override
	public String toString() {	
		String s = "[Flood: " + this.getDisasterName() + ", Active: "+ this.isActive() + ", List of Affected Areas: [";
		for (Area area : this.getAffectdAreaList()) {
			s += area.getConvexArea();
		}
		
		s += "], List of dangerousArea: [";
		
		for (Area area : dangerousAreaList) {
			s += area.getConvexArea();
		}
		s += "]]";
		return s;
	}

}
