/**
	 * This class implements a disaster 
	 * @author Syntyche Shimbi & Zigabe Jos
	 *
	 */
package be.ucl.ingi.lingi2252.disaster;
import java.util.List;
import be.ucl.ingi.lingi2252.instruction.Instruction;
import be.ucl.ingi.lingi2252.ers.*;

public abstract class ConcreteDisaster implements Disaster{
	
		private String disasterName;
		private Boolean active;
		private String type;
		private List<Area> affectdAreaList;
		private List<Instruction> instructionList;
		
		/**
		 * Constructor
		 * @param disasterName
		 * @param active
		 * 
		 * **/
		public ConcreteDisaster(String disasterName, Boolean active, String type) {
			
			this.disasterName = disasterName;
			this.active       = active;
			this.type 		  = type;
		}
		public ConcreteDisaster(String type) {
			this.type 		  = type;
		}
		
		/**
		 * Get the disater's name
		 * @return disasterName
		 */
		
		public String getDisasterName() {
			return disasterName;
		}
		
		/**
		 * set the disaster
		 * @param disasterName
		 */
		public void setDisasterName(String disasterName) {
			this.disasterName = disasterName;
		}
		
		/**
		 * Check if the disaster is active
		 * @return active
		 */
		
		public Boolean isActive() {
			return active;
		}
		
		/**
		 * set a disaster
		 * @param active
		 */
		public void setActive(Boolean active) {
			this.active = active;
		}
		
		/**
		 * set the disasterType
		 * @param disasterType
		 */
		public String getType() {
			return type;
		}
		
		/**
		 * set the disasterType
		 * @param disasterType
		 */
		public void setType(String disasterType) {
			this.type = disasterType; 
		}
		
		/**
		 * Check if the disaster is active
		 * @return active
		 */
		
		
		/**
		 * get affected area list
		 * */
		public List<Area> getAffectdAreaList() {
			return affectdAreaList;
		}
		/**
		 * set affected area list
		 * @param area
		 */
		public void setAffectedAreaList(List<Area> area){
			affectdAreaList = area;
		}
		
		/**
		 * add an affected area
		 * @param area
		 */
		public void addAffectedArea(Area area) {
			this.getAffectdAreaList().add(area);
		}
		/**
		 * remove an affected area
		 * @param area
		 */
		public void removeAffectedArea(Area area) {
			this.getAffectdAreaList().remove(area);
		}
		
		@Override
		public void setInstructionList(List<Instruction> instrList) {
			instructionList =  instrList;
			
		}

		@Override
		public List<Instruction> getInstructionList() {
			return  instructionList;
		}

		@Override
		public void addInstruction(Instruction instruction) {
			 instructionList.add(instruction);
			
		}

		@Override
		public void removeFloodInstruction(Instruction instruction) {
			 instructionList.remove(instruction);
			
		}
		
		/**
		 * this method check if the current positon is in a safe area or not
		 * @param position
		 * @return true if in an affected if in affected area
		 */
		public abstract boolean contains(GPSCoordinates pos);


		/**
		 * display all the instructions
		 * @return instructions in String format
		 */
		public abstract String displayInstructions();
	
	}
	