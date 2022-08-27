import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Airport {
	public static void main(String[] args) {
		Random rand = new Random();
		
		// Create a blocking queue to store runways.
		// The size is fixed to 1 as there will only be 1 runway.
		BlockingQueue<Runway> runways = new ArrayBlockingQueue<Runway>(1);

		// The size is fixed to 4 as there will only be 4 gates.
		BlockingQueue<Gate> gates = new ArrayBlockingQueue<>(4);
		
		// Create a thread pool for the aircraft objects.
		// The size is fixed to 1 as there cannot be more than 1 aircrafts
		// executing at the same time as it is limited to the number of runway objects.
		ExecutorService pool  = Executors.newFixedThreadPool(1);
		
		// Generate 1 runway objects and add it to the runway queue.


		for (int i = 1; i <= 1; i++) {
			runways.add(new Runway(i));
		}
		for (int g = 1; g <= 4; g++) {
			gates.add(new Gate(g));
		}


		
		// Generate 10 aircrafts at random intervals.
		for(int i = 1; i <= 10; i++) {
			try {
				// Wait between 0 to 5 seconds to generate a new aircraft.
				Thread.sleep(rand.nextInt(5001));
				// Add generated aircraft into queue.
				// Pass a random boolean to decide if the aircraft wants to land or depart.
				pool.submit(new Aircraft(i, rand.nextBoolean(), runways,gates));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}



		pool.shutdown();
		//print the counts or results after termination
		try {
			if (pool.awaitTermination(100, TimeUnit.SECONDS)) {
				for (int i = 1; i <= 3; i++) {
					runways.take().printReport();
				}
			}
		} catch (InterruptedException e) {
		}
	}
}
