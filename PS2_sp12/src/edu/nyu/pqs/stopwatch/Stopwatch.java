package edu.nyu.pqs.stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
*
* A thread-safe object that can be used for calculating timing laps.  
* The Stopwatch objects are created in the StopwatchFactory.  
* Different threads can
* share a single stopwatch object and safely call any of the stopwatch methods.
* This class implements IStopwatch interface
* 
* @author Prachi Kurkute
* 
*/
public class Stopwatch implements IStopwatch {
  private String id;	//id used as an unique identifier for stopwatch Object
  private long timer;	//the timer 
  private List<Long> lapTimes; 	//A list containing the lap times
	
  /**
   * 
   * Constructor for creating a {@code Stopwatch} 
   * @param id is the unique identifier for the Stopwatch object
   * 
   */
  public Stopwatch(String id) {
	synchronized(this) {
	  this.id = id;
	  timer = 0;
   	  lapTimes = new ArrayList<Long>();
	}
  }
	
  /**
   * 
   * Returns the id of this stopwatch
   * @return the id of this stopwatch.  Will never be empty or null.
   * 
   */
  @Override
  public String getId() {
  	synchronized(this) {
	  return id;
	}
  }

  /**
   * 
   * Starts the stopwatch.
   * @throws IllegalStateException if called when the stopwatch 
   * is already running
   * 
   */
  @Override
  public void start() {
	synchronized(this) {
	  if(timer == 0) {
		timer = System.currentTimeMillis();  
	  }
	  else {
		throw new IllegalStateException();  
	  }
	}
  }

  /**
   * 
   * Stores the time elapsed since the last time lap() was called
   * or since start() was called if this is the first lap.
   * @throws IllegalStateException if called when the stopwatch isn't running
   * 
   */
  @Override
  public void lap() {
    synchronized(this) {
	  if(timer != 0) {
		long currentTime = System.currentTimeMillis();
		long elpasedTime =  (long) ((currentTime - timer));
		lapTimes.add(elpasedTime);
		timer = currentTime;
	  }
	  else {
		throw new IllegalStateException(); 
	  }
	}
  }

  /**
   * 
   * Stops the stopwatch and records one final lap.
   * @throws IllegalStateException if called when the stopwatch isn't running
   * 
   */
  @Override
  public void stop() {
	synchronized(this) {
	  if(timer != 0) {
		long currentTime = System.currentTimeMillis();
		long elpasedTime =  (long) ((currentTime - timer));
		lapTimes.add(elpasedTime);
	  }
	  else {
		throw new IllegalStateException(); 
	  }
	}
  }

  /**
   * 
   * Resets the stopwatch. If the stopwatch is running, this method stops the
   * watch and resets it. This also clears all recorded laps.
   * 
   */
  @Override
  public void reset() {
	synchronized(this) {
	  //clear the list containing lap times
	  lapTimes.clear();
      //reinitialize timer of the stopwatch
	  timer = 0;
	}
  }

  /**
   * 
   * Returns a list of lap times (in milliseconds).  
   * This method can be called at any time and will not throw an exception.
   * @return a list of recorded lap times or an empty list 
   * if no times are recorded.
   * 
   */
  @Override
  public List<Long> getLapTimes() {
	synchronized(this) {
	  if(lapTimes != null) {
		List<Long> listLapTimes = Collections.
		unmodifiableList(lapTimes);
		return listLapTimes;
	  }
	  else {
		return null;
	  }
    }
  }

  /**
   * 
   * Returns the hash code value for this entry
   * 
   * @return the hash code value for this entry
   * 
   */
  @Override
  public int hashCode() {	
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
  }

  /**
   * 
   * Compares the specified object with this stopwatch for equality. 
   * Returns true if and only if the specified object is also a stopwatch 
   * and all members are {@code equal}.
   * 
   * @param o the object to be compared for equality with this stopwatch
   * @return true if the specified object is equal to this stopwatch
   * 
   */
  @Override
  public boolean equals(Object obj) {
	synchronized (this) {
	  if (this == obj) {
		return true;  
	  }
	  if (obj == null) {
		return false;  
	  }
	  if (!(obj instanceof Stopwatch)) {
		return false;  
	  }
	  Stopwatch other = (Stopwatch) obj;
	  if (id == null) {
		if (other.id != null) {
		  return false;	
		}
	  } 
	  return true;
	}
  }

  /**
   * 
   * Returns a String which is the Id of the given StopWatch
   * 
   * @return string representation of this stopwatch
   * 
   */
  @Override
  public String toString() {
	synchronized (this) {
	  return String.format("ID = (%d)", id);
	}
  }
	
}
