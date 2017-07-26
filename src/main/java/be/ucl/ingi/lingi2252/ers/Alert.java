package be.ucl.ingi.lingi2252.ers;
import java.util.Date;

/**
 * This class represent an alert
 * @author Syntyche Shimbi & Zigabe Jos
 *
 */
public class Alert extends ERS{
	private Date timer;

	public Alert(Date time) {
		super();
		this.timer = time;
	}
	
	/**
	 * set the timer
	 * @param time
	 */
	public void setTimer(Date time){
		timer = time;
	}
	
	/**
	 * get the timer
	 * @return timer
	 */
	public Date getTimer(){
		return timer;
	}
	/**
	 * TODO
	 * @return
	 */
	public Disaster disasterAlertLoop(){
		return null;
	}
	/**
	 * TODO
	 * @return
	 */
	public Disaster dangerousZoneAlertLoop(){
		return null;
	}

}
