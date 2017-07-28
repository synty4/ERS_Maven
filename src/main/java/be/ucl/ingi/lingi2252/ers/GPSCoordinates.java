package be.ucl.ingi.lingi2252.ers;
public class GPSCoordinates {
	
	private Double latitude;
	private Double longitude;
	private Boolean affected;
	

	/**
	 * Constructor
	 * @param latitude
	 * @param longitude
	 * 
	 * **/
	public GPSCoordinates(Double latitude, Double longitude){
		this.latitude  = latitude;
		this.longitude = longitude; 
	}
	
	/**
	 * set GPSCoordinates latitude
	 * @param  latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * set GPSCoordinates longitude
	 * @param  longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * set GPSCoordinates latitude
	 * @param  latitude
	 */
	public Double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * get GPSCoordinates longitude
	 * @return  longitude
	 */
	public Double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Check if GPSCoordinates g is equal to this object
	 * **/
	public Boolean isEqual(GPSCoordinates g) {
		return(( Double.compare(this.latitude, g.latitude)== 0 )
				&&  (Double.compare(this.longitude, g.longitude) == 0));	
	}
	
	public void setAffected(Boolean af) {
		this.affected = af;
	}
	public  Boolean isAffected() {
		return affected;
	}
	
	@Override
	public String toString() {
	
		return "(" + this.latitude + " , " + this.longitude + ")" ;
	}
}