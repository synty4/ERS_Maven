/**
 * 
 */
package be.ucl.ingi.lingi2252.ers;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.TestSuite;

public class MyTestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(TestSuite.class);
 for(Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    
  }
}