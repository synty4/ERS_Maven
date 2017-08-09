package be.ucl.ingi.lingi2252.ers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import be.ucl.ingi.lingi2252.disaster.*;
import be.ucl.ingi.lingi2252.disaster.Disaster;
import be.ucl.ingi.lingi2252.instruction.Instruction;
import be.ucl.ingi.lingi2252.utils.*;

/**
 * This class represent the emergency response system 
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class ERS {
	
	private List<ConcreteDisaster> disasters;
	private List<SafePlace> safePlaces;
	private List<Instruction> generalInstructions;
	private User user;
	
	public void clear() {
		this.disasters    =  null;
		this.safePlaces   = null;
		this.generalInstructions = null;
		this.user		  = null;
	}
	public ERS(List<ConcreteDisaster> disasters, List<SafePlace> safePlaces, 
			List<Instruction> generalInstructions, User user) {
		this.disasters = disasters;
		this.safePlaces = safePlaces;
		this.generalInstructions = generalInstructions;
		this.user = user;
	}
	public ERS() {
        this.disasters = new ArrayList<>();
        this.safePlaces = new ArrayList<>();
        this.generalInstructions = new ArrayList<>();
        this.user = new User();
    }
	
	/**
	 * get voice from text
	 * @param text
	 * */
	public void getVoice(String text) {
		TextToSpeechConverter voice = new TextToSpeechConverter(text);
		voice.speak();
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
		
		if(safePlaces.isEmpty()){
			return null;
		}
		return safePlaces;
	}
	
	/**
	 * get the distance between the user's current position and a given SafePlace
	 * @param user
	 * @param safePlace
	 * @return distance safe place and current position 
	 * */
	public Double getDistanceToSafePlace(User user, SafePlace safePlace){
		return Haversine.getDistance(user.getUserCurrentPosition(),  safePlace.getPlacePosition());
	}
	
	/**
	 * get the closest safe place from the current position
	 * @return closestSafePlace
	 */
	public SafePlace getClosestSafePlace(){
		SafePlace closest = getSafePlaces().get(0);
		double distance   = getDistanceToSafePlace(user, closest);
		
		for(SafePlace safePlace : getSafePlaces()){
			double distance2 = getDistanceToSafePlace(user, safePlace);
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
	 * 		  concreteDisaster ("null" for generic generalInstructions)
	 */
	public void addGeneralInstruction(Instruction instruction){
	
			generalInstructions.add(instruction);
		
	}
	/**
	 * remove instruction from the instruction list
	 * @param instruction
	 */
	public void removeGeneralInstruction(Instruction instruction){
		generalInstructions.remove(instruction);
	}
	/**
	 * get all instruction
	 * @return instruction
	 */
	public List<Instruction> getGeneralInstructions(){
		return generalInstructions;
	}
	/**
	 * display all the generalInstructions
	 * @return s
	 */
	public String displayGeneralInstructions(){
		String s = " ";
		for(Instruction instruction : generalInstructions){
			s += instruction.toString() + "\n"; 
		}
		return s;
	}
		
	/**
	 * add ConcreteDisaster to the disasters list
	 * @param ConcreteDisaster
	 */
	public void addDisaster(ConcreteDisaster ConcreteDisaster){
		disasters.add(ConcreteDisaster);
	}
	/**
	 * remove ConcreteDisaster from the disasters list
	 * @param ConcreteDisaster
	 */
	public void removeDisaster(ConcreteDisaster disaster){
		disasters.remove(disaster);
	}
	/**
	 * get all the disaster
	 * @return disasters
	 */
	public List<ConcreteDisaster> getDisasters(){
		return disasters;
	}
	/**
	 * this method check if the user is in unsafe area
	 * @param position
	 * @return returns null if he is in a safe place
	 * 			       a disaster otherwise 
	 */
	public ConcreteDisaster isUnSafe(GPSCoordinates position){

		for(ConcreteDisaster disaster : disasters){
			if( disaster.contains(user.getUserCurrentPosition())){
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
	 * 	@autor:  Louis Wasserman
	 *  @source: https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	 * **/
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * This method display directions to the safe place
	 * @param safePlace
	 */
	public String guideToSafePlace(){
		
		SafePlace safePlace = getClosestSafePlace();
		Double distance     = getDistanceToSafePlace(user, safePlace);
		
		return "Directions to the nearest safe place"  
				+"\n The nearest safe place is: " + safePlace.getPlaceName()
				+"\n Latitude:  "        		  + safePlace.getPlacePosition().getLatitude() 
				+"\n Longitude: "       		  + safePlace.getPlacePosition().getLongitude()
				+"\n You are "    				  + round( distance, 2) + " km away from this location ";
	}
	
	/**
	 * this method display the path to the safe place
	 * @param safePlace
	 */
	public void displayPathToSafePlace(SafePlace safePlace){
		//TODO
	}
	/**
	 * this method display all the no safe area
	 * @param disasters
	 */
	public List<GPSCoordinates> displayNotSafeArea(List<ConcreteDisaster> disasters){
		//TODO
		return null;
	}
	/**
	 * this method count all active disasters
	 * @return count
	 */
	public int countDisasters(){
		int c = 0; 
		for(ConcreteDisaster d : disasters){
			if(d.isActive()){
				++c;
			}
		}	
		return c;
	}
	public String toStringDisasters() {
		String str = "";
		for (ConcreteDisaster disaster  : disasters) {
	           str +=disaster.toString() + "\n";
	        }
		return str;
	}
	public String toStringSafePlaces(){
		String str = "";
		for (SafePlace safePlace : safePlaces) {
            str += safePlace.toString()+"\n";
        }
		return str;
	}

	@Override
    public String toString() {
      return "Occuring Disaster(s): "  + countDisasters() 
    		+"\n------------------"
    		+"\nUser Position:" + user.getUserCurrentPosition() 
            +"\n-------------"	 
            + "\nDisasters:"    
            + "\n----------"		
            +"\n" + toStringDisasters()
            + "\nSafe Places: "	 
            +"\n-----------"  
            +"\n" + toStringSafePlaces();
    }

}