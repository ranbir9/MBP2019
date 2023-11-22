package assign03;

import java.util.Random;

public class FindMaxMethodTimer {
	public static void main(String[] args) {
		{
		Random randomNumberGenerator = new Random();
		 

			// Do 10000 lookups and use the average running time
			@SuppressWarnings("rawtypes")
			SimplePriorityQueue<Integer> randomArray;
			int timesToLoop = 100000;

			//Insert elements in array first
			// for (int i = 0; i < timesToLoop; i++){
			// 	int randomNum = randomNumberGenerator.nextInt(10, 1000000);
			// 	randomArray.insert(randomNum);
			// }

			// For each problem size n . . .
			for (int n = 100000; n <= 2000000; n += 100000) {
				randomArray = new SimplePriorityQueue();

				//insert n elements
				for (int i = 0; i < n; i++){
					int randomNum = randomNumberGenerator.nextInt(10, 1000000);
					randomArray.insert(randomNum);
				}

					
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by
				// This allows this thread to stabilize
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000) { // empty block
				}

				// Now, run the test
				startTime = System.nanoTime();

				for (int i = 0; i < timesToLoop; i++)
					randomArray.findMax();

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
	
}
