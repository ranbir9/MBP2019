package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimerClassSortMethod {

    public static void main(String[] args) {
        Random rand = new Random(); 
		long startTime, midpointTime, endTime;
        //List<List<Integer>> graph = new ArrayList<>();
        
		// create a list of the source

		List<Integer> sourceList = new ArrayList<>();
		List<Integer> destinationList = new ArrayList<>();

		//String[] s = { "E", "B", "C", "J", "D", "F", "G", "H", "I", "A" };
		int timesToLoop = 100;
		for (int n = 100; n <= 1000; n += 100) {
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }
        
            // Now, run the test
            startTime = System.nanoTime();
        
            for (int i = 0; i < timesToLoop; i++) {
                Graph<Integer> graph = generateRandomSortedGraph(n);
            }
        
            midpointTime = System.nanoTime();
        
            // Run a loop to capture the cost of running the "timesToLoop" loop
            for (int i = 0; i < timesToLoop; i++) {
                // empty block
            }
        
            endTime = System.nanoTime();
        
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and doing the lookups
            // Average it over the number of runs
            double averageTime = ((midpointTime - startTime) - (endTime - midpointTime)) / (double) timesToLoop;
            // double time = ((midpointTime - startTime) - (stopTime - midpointTime));
        
            System.out.println(n + "\t" + averageTime);
        }

        

            
    }
    public static Graph<Integer> generateRandomSortedGraph(int numVertices) {
        Random random = new Random();
        ArrayList<Integer> sourceList = new ArrayList<>(); 
        ArrayList<Integer> destinationList = new ArrayList<>(); 
    
        for(int i = 0; i < numVertices; i++){
            sourceList.add((Integer)random.nextInt(3));
            destinationList.add((Integer)random.nextInt(3)); 
        }
            
        List<Integer> sortedVertices = GraphUtility.sort(sourceList, destinationList); 
        Graph<Integer> sortedGraph = new Graph<>(); 
        for (int i = 0; i < sortedVertices.size(); i++) {
            sortedGraph.addEdge(sourceList.get(i), destinationList.get(i));
        }
        return sortedGraph;
    }
        

}
