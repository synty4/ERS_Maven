package be.ucl.ingi.lingi2252.ers;
public class GPSCoordinates {
	
	private Double latitude;
	private Double longitude;
	private Boolean affected;
	
	/**
	 * @param latitude
	 * @param longitude
	 * **/
	public GPSCoordinates(Double latitude, Double longitude){
		this.latitude  = latitude;
		this.longitude = longitude; 
	}
	
	/**
	 * set latitude to lati_tude
	 * **/
	public void setLatitude(Double lati_tude) {
		this.latitude = lati_tude;
	}
	
	/**
	 * set longitude to longi_tude
	 * **/
	public void setLongitude(Double longi_tude) {
		this.longitude = longi_tude;
	}
	
	/**
	 * return latitude
	 * **/
	public Double getLatitude() {
		return latitude;
	}
	
	/**
	 * return longitude
	 * **/
	public Double getLongitude() {
		return longitude;
	}
	
	/**
	 * Return  true if the GPS Coordinates g is equal 
	 * to this object
	 * 
	 * **/
	public Boolean isEqual(GPSCoordinates g) {
		if(this.latitude == g.latitude && this.longitude == g.longitude) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	/**
	 * set to affec_ted 
	 * **/
	public void setAffected(Boolean affec_ted) {
		this.affected = affec_ted;
	}
	
	/**
	 * Return  affected
	 * 
	 * **/
	public  Boolean isAffected() {
		return affected;
	}
	
	@Override
	public String toString() {
	
		return "( latitude: " + latitude + " | " + "longitude: " + longitude + " )" ;
	}
}