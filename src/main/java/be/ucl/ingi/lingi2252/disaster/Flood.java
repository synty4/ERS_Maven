package be.ucl.ingi.lingi2252.disaster;
import java.util.ArrayList;
import java.util.List;

import be.ucl.ingi.lingi2252.ers.Area;
import be.ucl.ingi.lingi2252.ers.GPSCoordinates;
import be.ucl.ingi.lingi2252.instruction.Instruction;

public class Flood extends ConcreteDisaster {
	
	private List<Area> dangerousAreaList;
	
	/**
	 * Constructor 
	 * @param disaterName
	 * @param active
	 * @param affectedAreaList 
	 * @param dangerousAreaList
	 * @param instructionList
	 * **/
	public Flood() {
		super(null, false, "flood");
		
	}
	public Flood( String disasterName, 
				  Boolean active,
				  List <Area> affectedArea, 
				  List<Area> dangerousArea,
				  List<Instruction> instructionList
				  ){	
		super(disasterName, active, "flood");
		super.setAffectedAreaList(affectedArea);
		super.setInstructionList(instructionList);
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
	/**
	 * remove a dangerous area
	 * @param area
	 */
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
    public boolean contains(GPSCoordinates position) { 
    	
    	List<Area> areas = new ArrayList<Area>(super.getAffectdAreaList());
    	//join affected area(s) list and dangerous area(s) list
    	areas.addAll(dangerousAreaList);
    	
        for (Area area : areas) {
			if(contains(position, area.getConvexArea()))
				return true;		
		}
        return false;
    }
    
    /**
     * check if position is in the convex area
     * @param pos
     * @param list
     * @return true or false 
     */
    private boolean contains(GPSCoordinates position, List<GPSCoordinates> list){
    	int k;
        int j;
        boolean contains = false;
        for (k = 0, j = list.size() - 1; k < list.size(); j = k++) {
            if ((list.get(k).getLongitude() > position.getLongitude()) != (list.get(j).getLongitude() > position.getLongitude())
                    && (position.getLatitude() < (list.get(j).getLatitude() - list.get(k).getLatitude()) * (position.getLongitude() - list.get(k).getLongitude())
                    / (list.get(j).getLongitude() - list.get(k).getLongitude()) + list.get(k).getLatitude())) {
            	contains = true;
            }
        }
    	return contains;
    }
    
   @Override
   /**
	 * display all the instructions
	 * @return s
	 */
	public String displayInstructions(){
		String s = "Flood Safety Instructions:\n";
		for(Instruction instruction : this.getInstructionList()){
			s += instruction.toString() + "\n"; 
		}
		return s;
	}
    
   public String printAffectedArea() {
	   String affectedarea = " ";
	   for (Area area : this.getAffectdAreaList()) {
	    	affectedarea += area.getConvexArea();
		 }
	   return affectedarea;
   }
   
   public String printDangerousArea() {
	   String dangerousarea = " ";
	   for (Area area : this.getDangerousAreaList()) {
		   dangerousarea += area.getConvexArea();
		 }
	   return dangerousarea;
   }
    
    @Override
	public String toString() {	
    return    "\n Flood "
   			 + "\n -----"
   			 + "\n Name:"       							  + this.getDisasterName() 
   			 + "\n Active: "   								  + this.isActive()
   			 + "\n List of Coordinates for Affected Areas : " 
   			 + "\n" + printAffectedArea()  
   			 + "\n List of Coordinates for Dangerous Areas: " 
   			 + "\n" +  printDangerousArea() 
   		     + "\n";
	}

}
