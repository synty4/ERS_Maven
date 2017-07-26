package be.ucl.ingi.lingi2252.ers;

public class Earthquake extends Disaster {
	private GPSCoordinates epicenter;
	private double severity;
	private double radius;
	
	
	public Earthquake(String distr_name, Boolean occ, GPSCoordinates epicenter, double severity, double radius) {
		super(distr_name, occ);
		this.epicenter = epicenter;
		this.severity = severity;
		this.radius = radius;
	}
	
	public GPSCoordinates getEpicenter() {
		return epicenter;
	}
	public Double getRadius() {
		return radius;
	}
	public Double getSeverity() {
		return severity;
	}
	public void setEpicenter(GPSCoordinates epicenter) {
		this.epicenter = epicenter;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public void setSeverity(Double severity) {
		this.severity = severity;
	}
	
	/**
    *
    *
    * @param pos
    * @return
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
		// TODO Auto-generated method stub
		return super.toString();
	}
}
