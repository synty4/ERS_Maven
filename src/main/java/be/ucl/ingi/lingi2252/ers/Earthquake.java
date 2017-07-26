package be.ucl.ingi.lingi2252.ers;
public class Earthquake extends Disaster {
	private GPSCoordinates epicenter;
	private double severity;
	private double radius;
	

    /**
     * Constructor
     * @param distName
     * @param occ
     * @param epicenter
     * @param severity
     * @param radius
     */
	public Earthquake( String distName, Boolean occ, GPSCoordinates epicenter, double severity, double radius) {
		super( distName, occ);
		this.epicenter = epicenter;
		this.severity = severity;
		this.radius = radius;
	}
	/**
	 * return epicenter
	 * **/
	public GPSCoordinates getEpicenter() {
		return epicenter;
	}
	/**
	 * return radius
	 * **/
	public Double getRadius() {
		return radius;
	}
	/**
	 * return severity
	 * **/
	public Double getSeverity() {
		return severity;
	}
	
	/**
	 * set epicenter to epi_center
	 * **/
	public void setEpicenter(GPSCoordinates epi_center) {
		this.epicenter = epi_center;
	}
	/**
	 * set radius to rad_ius
	 * **/
	public void setRadius(Double rad_ius) {
		this.radius = rad_ius;
	}
	/**
	 * set severity to seve_rity
	 * **/
	public void setSeverity(Double seve_rity) {
		this.severity = seve_rity;
	}
	

   /**
	* This method check if the current position is in a safe area or not
	* @param position
    * @return true if in an affected area
	*/

   @Override
   public boolean contains(GPSCoordinates position) {
       Double dist = 6371 * Math.acos(
               Math.cos(Math.toRadians(position.getLatitude()))
               * Math.cos(Math.toRadians(epicenter.getLatitude()))
               * Math.cos(Math.toRadians(epicenter.getLongitude()) - Math.toRadians(position.getLongitude()))
               + Math.sin(Math.toRadians(position.getLatitude()))
               * Math.sin(Math.toRadians(epicenter.getLatitude())));
       return dist < radius;
   }
	
	
	@Override
	public String toString() {
			
		return "Earthquake" + this.getDisasterName() + "Occuring: "+ this.isActive() +"Epicenter: " + this.getEpicenter() + "Severity: " + this.getSeverity() + "Radius: "+ this.getRadius();   
	}
}
