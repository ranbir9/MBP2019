package assign04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindKthLargestSortMethodTimer {
	public static void main(String[] args) {
		{
			Random randomNumberGenerator = new Random();

			// Do 10000 lookups and use the average running time
			int timesToLoop = 100;

			// For each problem size n . . .
			for (int n = 200000; n <= 2000000; n += 200000) {
				// genreate an array of maximum elements

				Integer[] maxElements = new Integer[n];
				for (int i = 0; i < n; i++)
					maxElements[i] = 100 + i;

				// Randomly shuffle the array
				for (int i = 0; i < n; i++) {
					int randomIndex = randomNumberGenerator.nextInt(n);
					{
						Integer temp = maxElements[i];
						maxElements[i] = maxElements[randomIndex];
						maxElements[randomIndex] = temp;
					}
				}

				// Generate a new "random" SimplePriorityQueue of n elements
				// NOTE: elements information are meaningless and unimportant

				List<Integer[]> randomList = new ArrayList<>();

				for (int i = 0; i < n; i++)
					randomList.add(new Integer[] { i, i + 1, i + 2 });
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
					LargestNumberSolver.findKthLargest(randomList, i);

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
