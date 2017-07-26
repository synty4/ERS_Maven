package be.ucl.ingi.lingi2252.utils;

import be.ucl.ingi.lingi2252.ers.GPSCoordinates;

/**
 * class that provides approximate distance between
 * two points using the Haversine formula
 *
 */
public class Location {

	private static final int EARTH_RADIUS = 6371; // Earth radius in KM

	/**
	 * 
	 * @param currentLat
	 * @param currentLong
	 * @param endLat
	 * @param endLong
	 * @return
	 */
	public static double distance(double currentLat, double currentLong,
			double endLat, double endLong) {

		double dLat  = Math.toRadians((endLat - currentLat));
		double dLong = Math.toRadians((endLong - currentLong));

		currentLat = Math.toRadians(currentLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(currentLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		double distance = EARTH_RADIUS * c;
		
		return distance; 
	}
	
	public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
	
	/**
	 * this method check if the current positon is in a safe area or not
	 * @param position
	 * @return true if in an affected or dangerous area otherwise false
	 */
	public boolean hasCoor(GPSCoordinates position){
		//TODO 
		return false;
	}

}
