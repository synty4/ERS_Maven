/**
 * This class implements an Earthquake 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
package be.ucl.ingi.lingi2252.disaster;
import java.util.List;
import be.ucl.ingi.lingi2252.ers.GPSCoordinates;
import be.ucl.ingi.lingi2252.instruction.Instruction;
import be.ucl.ingi.lingi2252.utils.Haversine;

public class Earthquake extends ConcreteDisaster {
	private GPSCoordinates epicenter;
	private double severity;
	private double radius;
	private List<Instruction> instructionList;
	//private static final int EARTH_RADIUS = 6371000; // Earth radius in meter

    /**
     * Constructor
     * @param distName
     * @param active
     * @param epicenter
     * @param severity
     * @param radius
     */
	
	public Earthquake( String distName, Boolean active, GPSCoordinates epicenter, 
			           double severity, double radius, List<Instruction> instructionList) {
		super( distName, active, "Earthquake");
		this.epicenter       = epicenter;
		this.severity        = severity;
		this.radius          = radius;
		this.instructionList = instructionList;
	}
	
	/**
	 * return epicenter
	 **/
	
	public GPSCoordinates getEpicenter() {
		return epicenter;
	}
	
	/**
	 * return radius
	 **/
	public Double getRadius() {
		return radius;
	}
	
	/**
	 * return severity
	 * **/
	public Double getSeverity() {
		return severity;
	}
	
	/**
	 * set epicenter to epi_center
	 * **/
	public void setEpicenter(GPSCoordinates epi_center) {
		this.epicenter = epi_center;
	}
	
	/**
	 * set radius to rad_ius
	 * **/
	public void setRadius(Double rad_ius) {
		this.radius = rad_ius;
	}
	
	/**
	 * set severity to seve_rity
	 * **/
	public void setSeverity(Double seve_rity) {
		this.severity = seve_rity;
	}
			
   /**
	* This method check if the current position is in a safe area or not
	* @param position
    * @return true if in an affected area
	*/

   @Override
   public boolean contains(GPSCoordinates position) {
	   Haversine haversine = new Haversine();
	   Double distance = haversine.getDistance(position, epicenter);
       return distance < this.radius;
   }
	
   @Override
    /**
	 * display earthquake specific instructions
	 * @return s
	 */
	public String displayInstructions(){
	   String s = "Earthquake Safety Instructions:\n";
		for( Instruction instruction : this.getInstructionList()){
			s += instruction.toString() + "\n"; 
		}
		return s;
	}
	
	@Override
	public String toString() {
		
		return "\n Earthquake: "
			 + "\n ----------"
			 + "\n Name:"       + this.getDisasterName() 
			 + "\n Active: "    + this.isActive()
			 + "\n Epicenter: " + this.getEpicenter() 
			 + "\n Severity: "  + this.getSeverity() 
			 + "\n Radius: "    + this.getRadius()  
		     + "\n";
	}
}
