package be.ucl.ingi.lingi2252.ers;

import java.util.ArrayList;
import java.util.List;
import be.ucl.ingi.lingi2252.utils.*;

/**
 * This class represent the emergency response system 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class ERS {
	private List<Disaster> disasters;
	private List<SafePlace> safePlaces;
	private List<Instruction> instructions;
	private User user;
	
	public ERS(List<Disaster> disasters, List<SafePlace> safePlaces, 
			List<Instruction> instructions, User user) {
		this.disasters = disasters;
		this.safePlaces = safePlaces;
		this.instructions = instructions;
		this.user = user;
	}
	public ERS() {
        this.disasters = new ArrayList<>();
        this.safePlaces = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.user = new User();
    }
	/**
	 * add a safe place to the safe place list
	 * @param safePlace
	 */
	public void addSafePlace(SafePlace safePlace){
		safePlaces.add(safePlace);
	}
	/**
	 * remove a safePlace from the safe place list 
	 * @param safePlace
	 */
	public void removeSafePlace(SafePlace safePlace){
		safePlaces.remove(safePlace);
	}
	/**
	 * get all the safe place from the list
	 * @return safePlaces
	 */
	public List<SafePlace> getSafePlaces(){
		return safePlaces;
	}
	/**
	 * get the closest safe place from the current position
	 * @return closestSafePlace
	 */
	public SafePlace getClosestSafePlace(){
		//check if the list is empty 
		if(safePlaces.isEmpty()){
			return null;
		}
		
		SafePlace closest = safePlaces.get(0);
		
		double userCurrentLat = user.getUserCurrentPosition().getLatitude();
		double userCurrentLong = user.getUserCurrentPosition().getLongitude();
		double destLat = safePlaces.get(0).getPlacePosition().getLatitude();
		double destLong = safePlaces.get(0).getPlacePosition().getLongitude();
		
		//get the distance
		double distance = Location.distance(userCurrentLat, userCurrentLong, destLat, destLong);
		
		for(SafePlace safePlace : safePlaces){
			destLat = safePlace.getPlacePosition().getLatitude();
			destLong = safePlace.getPlacePosition().getLongitude();
			double distance2 = Location.distance(userCurrentLat, userCurrentLong, destLat, destLong);
			if(distance2 < distance){
				distance = distance2;
				closest = safePlace;
			}
		}
		
		return closest;
	}
	/**
	 * add instruction to the instruction list
	 * @param instruction
	 */
	public void addInstruction(Instruction instruction){
		instructions.add(instruction);
	}
	/**
	 * remove instruction from the instruction list
	 * @param instruction
	 */
	public void removeInstruction(Instruction instruction){
		instructions.remove(instruction);
	}
	/**
	 * get all instruction
	 * @return instruction
	 */
	public List<Instruction> getInstructions(){
		return instructions;
	}
	/**
	 * display all the instructions
	 * @return s
	 */
	public String displayInstructions(){
		String s = "Instructions:\n";
		for(Instruction instruction : instructions){
			s += "- " + instruction.toString() + "\n"; 
		}
		return s;
	}
	/**
	 * add disaster to the disaster list
	 * @param disaster
	 */
	public void addDisaster(Disaster disaster){
		disasters.add(disaster);
	}
	/**
	 * remove disaster from the disaster list
	 * @param disaster
	 */
	public void removeDisaster(Disaster disaster){
		disasters.remove(disaster);
	}
	/**
	 * get all the disaster
	 * @return disasters
	 */
	public List<Disaster> getDisasters(){
		return disasters;
	}
	/**
	 * this method check if the user is in safe area
	 * @param position
	 * @return true if he is in a safe area otherwise false
	 */
	public Disaster isInSafe(GPSCoordinates position){
		//boolean safe = true;
		for(Disaster disaster : disasters){
			if(disaster.contains_affectedArea(user.getUserCurrentPosition())){
				return disaster;
			}
		}
		return null;
	}
	/**
	 * get the user
	 * @return user
	 */
	public User getUser(){
		return user;
	}
	/**
	 * this méthod display the path to the safe place
	 * @param safePlace
	 */
	public void displayPathToSafePlace(SafePlace safePlace){
		//TODO
	}
	/**
	 * this method display all the no safe area
	 * @param disasters
	 */
	public void displayNoSafeArea(List<Disaster> disasters){
		//TODO
	}
	/**
	 * this method count all active disasters
	 * @return count
	 */
	public int getActiveDisastersCount(){
		int count = 0; //by default
		for(Disaster disaster : disasters){
			if(disaster.isActive()){
				++count;
			}
		}	
		return count;
	}
	
	@Override
    public String toString() {
        String s = "Current active disaster(s):\n- " + getActiveDisastersCount() 
        			+ "\nUser Position:\n- " + user.getUserCurrentPosition() 
        			+ "\nDisasters:\n";
        for (Disaster disaster  : disasters) {
            s += "- " + disaster.toString() + "\n";
        }
        s = s.replaceAll(", $", "");
        s+= "SafePlaces:\n";
        for (SafePlace safePlace : safePlaces) {
            s += "- " + safePlace.toString()+"\n";
        }
        s = s.replaceAll(", $", "");
        s+= "Instructions:\n";
        for (Instruction instruction : instructions) {
            s += "- " + instruction.toString()+"\n";
        }  
        s = s.replaceAll(", $", "");
        return s;
    }

}
