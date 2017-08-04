package be.ucl.ingi.lingi2252.ers;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represent an alert
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class Alert extends TimerTask{
	private Timer timer;
	private ERS ers;

	public Alert(ERS ers, Timer timer) {
		
		super();
		this.ers = ers; 
		this.timer = timer;
	}

	@Override
	public void run() {
		Disaster disaster = ers.isInSafe(ers.getUser().getUserCurrentPosition());
		if(disaster != null){
			synchronized(timer){
	            try{
	            	System.out.println("ALERT : Your are in danger! You have to move from here...\n");
	    			System.out.println(disaster.toString()+"\n");
	    			System.out.println("Nearest safe place : "+ers.getClosestSafePlace()+"\n");
	                timer.wait();
	            }catch(InterruptedException e){
	                e.printStackTrace();
	            }
	        }
		} 
	}

	/**
	 * set the timer
	 * @param time
	 */
	public void setTimer(Timer time){
		timer = time;
	}

	/**
	 * get the timer
	 * @return timer
	 */
	public Timer getTimer(){
		return timer;
	}
}