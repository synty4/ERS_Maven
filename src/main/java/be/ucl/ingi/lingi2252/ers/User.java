package be.ucl.ingi.lingi2252.ers;
/**
 * This class represent a user
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class User {
	
	private String userName;
	private GPSCoordinates userPosition;
	
	public User(String name){
		this.userName = name;
	}
	public User(){
		this.userName = "John Doe";
		this.userPosition = new GPSCoordinates(0.0, 0.0);
	}
	
	/**
	 * set the name of the user
	 * @param name
	 */
	public void setUserName(String name){
		userName = name;
	}
	/**
	 * get the name of the user
	 * @return name
	 */
	public String getUserName(){
		return userName;
	}
	/**
	 * set the current position of the user
	 * @param position
	 */
	public void setUserCurrentPossition(GPSCoordinates position){
		userPosition = position;
	}
	/**
	 * get the current position of the user 
	 * @return userPosition
	 */
	public GPSCoordinates getUserCurrentPosition(){
		return userPosition;
	}
}
