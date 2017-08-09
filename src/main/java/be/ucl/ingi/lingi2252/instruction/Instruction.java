package be.ucl.ingi.lingi2252.instruction;
/**
 * This class represents an Instruction
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */

public class Instruction {
	String details;
	String type = null;
	
	/**
	 * Constructor
	 * @param details
	 * @param type: general, flood or earthquake
	 * 
	 * **/
	public Instruction(String d) {
		details = d;
	}
	
	/**
	 * set instruction content
	 * @param  details
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * get instruction content
	 * @return details
	 */
	
	public String getDetails() {
		return this.details;
	}
	
	@Override
	public String toString() {
		
		String s =  this.details;
		return s;
	}

}
