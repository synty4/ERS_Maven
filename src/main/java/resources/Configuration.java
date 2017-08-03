/**
 * source: https://www.mkyong.com/java/java-properties-file-examples/
 ***/

package resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Configuration {
	
  public static void main(String[] args) {
	Properties prop = new Properties();
	OutputStream output = null;

	try {

		output = new FileOutputStream("src\\main\\java\\resources\\config.properties");
		System.out.println("file created");
		
		// set the properties value
		prop.setProperty("DisasterType", "flood");
		//prop.setProperty("DisasterType", "earthquake");
		//prop.setProperty("DisasterType", "none");
		
		prop.setProperty("Map", "current_position");
		
	/*  prop.setProperty("Map", "dangerous_zones");
		prop.setProperty("Map", "affected_zones");
		prop.setProperty("Map", "safe_places");
	*/	
		prop.setProperty("DetectDangerousZones", "General_information");
	/*	prop.setProperty("DetectDangerousZones", "DisplayNearestSafePlaces");
		prop.setProperty("DisplayNearestSafePlaces", "As_text");
		prop.setProperty("DisplayNearestSafePlaces", "On_map");
		prop.setProperty("DetectDangerousZones", "FloodInstructions");
		prop.setProperty("DetectDangerousZones", "EarthquakeInstructions");
	*/	

		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
  }
}