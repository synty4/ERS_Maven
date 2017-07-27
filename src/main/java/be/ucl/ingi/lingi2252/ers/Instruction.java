package be.ucl.ingi.lingi2252.ers;
/*
 * This class implements a non specific Instruction
 * such as Call  an Emergency number
 */

public class Instruction {
	String details;
	public Instruction(String d) {
		details = d;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getDetails() {
		return details;
	}
	
	@Override
	public String toString() {
		
		String s =  details;
		return s;
	}

}
