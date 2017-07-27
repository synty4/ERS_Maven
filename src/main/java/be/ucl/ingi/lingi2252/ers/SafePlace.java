package be.ucl.ingi.lingi2252.ers;
/**
 * This class represent a safe place
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class SafePlace {
	private String placeName;
	private GPSCoordinates placePosition;
	private PlaceType placeType;
	
	public SafePlace(String name, GPSCoordinates position, PlaceType type) {
		this.placeName = name;
		this.placePosition = position;
		this.placeType = type;
	}
	
	/**
	 * set the name of the safe place
	 * @param name
	 */
	public void setPlaceName(String name){
		placeName = name;
	}
	/**
	 * get the name of the safe place
	 * @return placeName
	 */
	public String getPlaceName(){
		return placeName;
	}
	/**
	 * set the position of the safe place
	 * @param position
	 */
	public void setPlacePosition(GPSCoordinates position){
		placePosition = position;
	}
	/**
	 * get the position of the safe place
	 * @return
	 */
	public GPSCoordinates getPlacePosition(){
		return placePosition;
	}
	/**
	 * set the type of the safe place
	 * @param type
	 */
	public void setPlaceType(PlaceType type){
		placeType = type;
	}
	/**
	 * get the type of the safe place
	 * @return
	 */
	public PlaceType getPlaceType(){
		return placeType;
	}
	
	@Override
	public String toString() {
		
		String s = "[Name: " + this.getPlaceName() + ", Type: " + this.placeType + ", Postion: " + this.placePosition + "]";
		return s;

	}
}
