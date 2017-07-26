package be.ucl.ingi.lingi2252.ers;
/*
 * This class implements a generic instruction
 * valuable in any situation
 */

public class Instruction {
	String details;
	/**
	 * Constructor
	 * @param details
	 * **/
	public Instruction(String details) {
		this.details = details;
	}
	
	/**
	 * set details to det_ails
	 * @param details
	 */
	public void setDetails(String det_ails) {
		this.details = det_ails;
	}
	
	
	/**
	 * @return details
	 * **/
	public String getDetails() {
		return details;
	}
	
	@Override
	public String toString() {
		
		return "Instructions: "+ details;
	}
}
