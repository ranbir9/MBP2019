package assign03;

import java.util.Random;

public class InsertMethodTimer {
	public static void main(String[] args) {
		Random randomNumberGenerator = new Random();

		int randomNum = randomNumberGenerator.nextInt(10, 2000); 

		// Do 10000 lookups and use the average running time
		int timesToLoop = 100000;

		// For each problem size n . . .
		for (int n = 100000; n <= 2000000; n += 100000) {


			@SuppressWarnings({ "rawtypes", "unchecked" })
			SimplePriorityQueue<Integer> randomArray = new SimplePriorityQueue();
			for (int i = 0; i < n; i++)
				randomArray.insert((int)Math.random() + 2000);
			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by
			// This allows this thread to stabilize
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++)
				// Lookup a student with a uNID that does not exist,
				// so method will not return early
				randomArray.insert(randomNum);

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop
			for (int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups
			// Average it over the number of runs
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			// double time = ((midpointTime - startTime) - (stopTime - midpointTime));

			System.out.println(n + "\t" + averageTime);

		}

	}
}
