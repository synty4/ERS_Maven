package be.ucl.ingi.lingi2252.disaster;

import be.ucl.ingi.lingi2252.ers.*;
import java.util.List;
import be.ucl.ingi.lingi2252.instruction.Instruction;

/**
 * Interface for a disaster 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */

public interface Disaster {
	
	/**
	 * Get the disater's name
	 * @return disasterName
	 */
	
	public String getDisasterName();
	
	/**
	 * set the disaster
	 * @param disasterName
	 */
	public void setDisasterName(String disasterName); 
	
	/**
	 * Check if the disaster is active
	 * @return active
	 */
	
	public Boolean isActive();
	
	/**
	 * set a disaster
	 * @param active
	 */
	public void setActive(Boolean occ);

	/**
	 * Get the disater's type
	 * @return disasterType
	 */
	
	public String getType();
	
	/**
	 * set the disasterType
	 * @param disasterType
	 */
	public void setType(String disasterType); 

	/**
	 * get affected area list
	 * */
	public List<Area> getAffectdAreaList();
	/**
	 * set affected area list
	 * @param area
	 */
	public void setAffectedAreaList(List<Area> area);
	
	/**
	 * add an affected area
	 * @param area
	 */
	public void addAffectedArea(Area area);
	/**
	 * remove an affected area
	 * @param area
	 */
	public void removeAffectedArea(Area area);
	
	/**
	 * set instructions list
	 * @param List of instructions
	 */
	public void setInstructionList(List<Instruction> instructionList);
	
	/**
	 * get instructions list
	 * @param List of instructions
	 */
	public List<Instruction> getInstructionList();
	
	/**
	 * add flood safety instructions 
	 * @param instructions
	 */
	public void addInstruction(Instruction instruction);
	/**
	 * remove flood safety instructions
	 * @param area
	 */
	public void removeFloodInstruction(Instruction instruction);
	
	/**
	 * display all the instructions
	 * @return instructions in String format
	 */
	public abstract String displayInstructions();
	/**
	 * this method check if the current position is in a safe area or not
	 * @param position
	 * @return true if in an affected if in affected area
	 */
	public abstract boolean contains(GPSCoordinates pos);
	
	
}
