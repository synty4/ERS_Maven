package be.ucl.ingi.lingi2252.ers;
public class GPSCoordinates {
	
	private Double latitude;
	private Double longitude;
	private Boolean affected;
	
	public GPSCoordinates(Double lat, Double lon){
		latitude = lat;
		longitude = lon; 
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	
	public Boolean isEqual(GPSCoordinates g) {
		if(this.latitude == g.latitude && this.longitude == g.longitude) {
			return true;
		}
		else {
			return false;
		}	
	}
	public void setAffected(Boolean af) {
		this.affected = af;
	}
	public  Boolean isAffected() {
		return affected;
	}
}