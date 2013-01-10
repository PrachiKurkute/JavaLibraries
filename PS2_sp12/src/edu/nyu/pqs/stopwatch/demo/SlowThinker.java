package edu.nyu.pqs.stopwatch.demo;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

/**
 * This is a simple program that demonstrates just some of
 * the functionality of the IStopwatch interface and StopwatchFactory class.
 * Just because this class runs successfully does not mean that the assignment is
 * complete.  It is up to you to implement the methods of IStopwatch and StopwatchFactory
 *
 */
public class SlowThinker {

	/** use a logger instead of System.out.println */
	private static final Logger logger = 
	    Logger.getLogger("edu.nyu.pqs.ps4.demo.SlowThinker");

	/**
	 * Run the SlowThinker demo application
	 * @param args a single argument specifying the number of threads
	 */
	public static void main(String[] args) {
		SlowThinker thinker = new SlowThinker();
		thinker.go();
	}

	/**
	 * Starts the slowthinker object
	 * It will get a stopwatch, set a number of lap times, stop the watch
	 * and then print out all the lap times
	 *
	 */
	private void go() {
		final IStopwatch stopwatch = StopwatchFactory.getStopwatch(
			    "ID " + Thread.currentThread().getId());
		Runnable runnable1 = new Runnable() {
			public void run() {
				//StopwatchFactory stopwatchFactory = new StopwatchFactory();
				//IStopwatch stopwatch = StopwatchFactory.getStopwatch(
				//    "ID " + Thread.currentThread().getId());
			  stopwatch.start();
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(i*100);
					} catch (InterruptedException ie) { /* safely ignore this */ }
					stopwatch.lap();
				}
				stopwatch.stop();
				List<Long> times = stopwatch.getLapTimes();
				logger.info(times.toString());
			}
		};
		Thread thinkerThread1 = new Thread(runnable1);
		thinkerThread1.start();
		
		Runnable runnable2 = new Runnable() {
			public void run() {
			  stopwatch.start();
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(i*100);
					} catch (InterruptedException ie) { /* safely ignore this */ }
					stopwatch.lap();
				}
				stopwatch.stop();
				List<Long> times = stopwatch.getLapTimes();
				logger.info(times.toString());
			}
		};
		Thread thinkerThread2 = new Thread(runnable2);
		thinkerThread2.start();
	}
}
