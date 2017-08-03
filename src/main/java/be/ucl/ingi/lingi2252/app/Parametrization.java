/**
 * source: https://www.mkyong.com/java/java-properties-file-examples/
 ***/
package be.ucl.ingi.lingi2252.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.Enumeration;

public class Parametrization {
  
  private static Queue<String> get_properties_set(Properties p){	  
	  Queue<String> features = new LinkedList<String>();
	  Enumeration<?> e       = p.propertyNames();
	    while (e.hasMoreElements()) {
	      features.add(p.getProperty((String) e.nextElement()));
	    }
	    return features;   
  }
  
  private static Properties get_ers_properties(String path) {
		Properties prop   = new Properties();
		InputStream input = null;		
		try {
			input = new FileInputStream(path);
			prop.load(input);	
		 }
		catch (IOException ex) {
			System.out.println(ex);
		} 
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return prop;
  }
  
  public static void main(String[] args) {

	Queue<String> features  = get_properties_set(get_ers_properties("src\\main\\java\\resources\\config.properties"));
	//for test
	for(int i = -1; i < features.size()+1 ;  i++) {
    	System.out.println(features.poll());
    }

	
  }
}