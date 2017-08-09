package be.ucl.ingi.lingi2252.utils;
import be.ucl.ingi.lingi2252.ers.*;

/**
 * This class provides approximate distance between
 * two points using the Haversine formula
 * which calculates the shortest distance over the earth’s surface 
 * giving an ‘as-the-crow-flies’ distance between the points 
 * (ignoring any hills they fly over)
 * @source: http://www.movable-type.co.uk/scripts/latlong.html
 * 
 * a = sin^2((lat2-lat1).toRadians()/2) + ( lat1.toRadians() * lat2.toRadians()* sin^2((lon2-lon1).toRadians()/2))
 * distance = earth_radius*2.atan2(sqrt(a), sqrt(1-a))
 *
 */
public  class Haversine {
	
	private static final int EARTH_RADIUS = 6371000; // Earth radius in meter
	

	/**
	 * @param value
	 * @return (sin(value/2))^2
	 * **/
	private static  double getSinSquare(Double value){
		return Math.pow(Math.sin(value/2), 2);

     }
	/**
	 * @param GpsCoordinates point1
	 * @param GpsCoordinates point2
	 * @return a = sin^2((lat2-lat1).toRadians()/2) + ( lat1.toRadians() * lat2.toRadians()* sin^2((lon2-lon1).toRadians()/2))
	 ***/
	private static double getHaversinePart(GPSCoordinates point1, GPSCoordinates point2) {
		return  getSinSquare ( Math.toRadians( point2.getLatitude() - point1.getLatitude() ) ) +
				( Math.cos( Math.toRadians(point1.getLatitude()) * Math.cos( Math.toRadians(point2.getLatitude())
						* getSinSquare ( Math.toRadians( point2.getLongitude() - point1.getLongitude() ) ) ) ) );		
	}
	
	/**
	 * this method that provides approximate distance between
	 * two points using the Haversine formula
	 * 
	 * 
	 * @param GPSCoordinates point1
	 * @param GPSCoordinates point2
	 * @return harversine distance between two points:
	 * 		   earth_radius*2.atan2(sqrt(a), sqrt(1-a))
	 * **/
	public static double getDistance(GPSCoordinates point1, GPSCoordinates point2) {
		
		return EARTH_RADIUS * 2 * Math.atan2( Math.sqrt( getHaversinePart(point1, point2)), Math.sqrt( getHaversinePart(point1, point2) ) );
	}
}
