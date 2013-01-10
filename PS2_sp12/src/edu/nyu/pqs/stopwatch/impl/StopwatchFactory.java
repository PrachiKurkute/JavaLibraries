package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.nyu.pqs.stopwatch.Stopwatch;
import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * 
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
  private static List<IStopwatch> stopwatches = new ArrayList<IStopwatch>();
  private static Map<String,IStopwatch> stopwatchesMap =Collections.
      synchronizedMap(new HashMap<String,IStopwatch>());
  private static Object lock = new Object();
	
  /**
   * 
   * Creates and returns a new IStopwatch object
   * @param id The identifier of the new object
   * @return The new IStopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, 
   * or already taken
   * 
   */
  public static IStopwatch getStopwatch(String id) {
	synchronized(lock) {
	  if(id == null) {
		throw new IllegalArgumentException("Id cannot be null");
	  }		
	  IStopwatch stopwatch = stopwatchesMap.get(id);
  	  if(stopwatch == null) {
		//stopwatch with given id is not present in the list. 
		//So, create new stopwatch object and add it to the list
		Stopwatch sw = new Stopwatch(id);
		addStopwatch(sw);
		return sw;
	  }
	  else {
		throw new IllegalArgumentException("Invalid ID");
	  }
    }
  }

  /**
   * 
   * Returns a list of all created stopwatches
   * @return a List of all created IStopwatch objects.  Returns an empty
   * list if no IStopwatches have been created.
   * 
   */
  public List<IStopwatch> getStopwatches() {
	synchronized(lock) {
	  if(stopwatches != null) {
	    List<IStopwatch> unmodifiableWatches = Collections.
		    unmodifiableList(stopwatches);
		return unmodifiableWatches;
	  }
	  else {
	    return null;
	  }
	}
  }
	
  /**
   * 
   * Private method which adds new stopwatch object in a List
   * @param IStopwatch object which is stored in a List
   *  
   */
  private static void addStopwatch(IStopwatch stopwatch) {
	synchronized(lock) {
	  stopwatches.add(stopwatch);
	  /*Simultaneously store it in a HashMap to improve the efficiency of
   	  search */
	  stopwatchesMap.put(stopwatch.getId(), stopwatch);
	}
  }
  
}
