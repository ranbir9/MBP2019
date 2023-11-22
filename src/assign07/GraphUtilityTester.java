package assign07;
import assign07.GraphUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GraphUtility class and it's methods
 * 
 * @author Anuvesha Chilwal & Ranbir Singh
 * @version March 3, 2022
 */
public class GraphUtilityTester {

    private List<String> sources;
    private List<String> destinations;
    private List<String> disconnectedSources;
    private List<String> disconnectedDestination;
    private List<Integer> numSources; 
    private List<Integer> numDestinations; 
    private List<Integer> array2To5; 


    @BeforeEach
    public void setUp() {
        sources = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "D", "E", "F"));
        destinations = new ArrayList<>(Arrays.asList("B", "C", "C", "D", "E", "F", "G"));

        disconnectedSources = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "D", "E", "F"));
        disconnectedDestination = new ArrayList<>(Arrays.asList("B", "C", "F", "D", "E", "F", "G"));

        numSources = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 5, 6));
        numDestinations = new ArrayList<>(Arrays.asList(2, 3, 3, 4, 5, 6, 7));

        array2To5 = new ArrayList<>(Arrays.asList(2, 3, 4, 5));


    }

    @Test
    public void testAreConnected() {
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "D"));
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "G"));
    }

    @Test
    public void testShortestPath() {
        List<String> expectedPath = Arrays.asList("A", "C", "D");
        assertEquals(expectedPath, GraphUtility.shortestPath(sources, destinations, "A", "D"));
    }
    // @Test
    // public void testShortestPath2To5() {
    //     List<Integer> expectedPath = Arrays.asList(2, 3, 4, 5);

    //     assertEquals(expectedPath, GraphUtility.shortestPath(sources, destinations, 2, 5));
    // }

    @Test
    public void testSort() {
        List<String> sortedOrder = GraphUtility.sort(sources, destinations);
        assertTrue(isValidTopologicalOrder(sortedOrder));
    }

    private boolean isValidTopologicalOrder(List<String> order) {
        for (int i = 0; i < order.size(); i++) {
            for (int j = i + 1; j < order.size(); j++) {
                if (isEdge(order.get(j), order.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
    

    private boolean isEdge(String src, String dest) {
        for (int i = 0; i < sources.size(); i++) {
            if (sources.get(i).equals(src) && destinations.get(i).equals(dest)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testBAndCNodes(){
        //Graph
        Graph graph = new Graph();

        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i));
        }
        assertTrue(GraphUtility.areConnected(sources, destinations, "B", "E")); 
    }

    @Test
    public void testGraph2(){
        //Graph
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "C");
        graph.addEdge("C", "G");
        graph.addEdge("D", "F");
        graph.addEdge("D", "E");


        

        System.out.println(graph.toString());
        System.out.println();
        System.out.println(graph.generateDot());
    }

    @Test
    public void testDisconnectedGraph2(){
        //B node cannot reach E
        //B node is connected to F, which is after E

        Graph graph = new Graph();

        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(disconnectedSources.get(i), disconnectedDestination.get(i));
        }
        System.out.println(graph.generateDot());
        assertFalse(GraphUtility.areConnected(disconnectedSources, disconnectedDestination, "B", "E"));

    }

    @Test
public void testAreConnectedDisconnectedNodes() {
    var actualMessage = ""; 
    String expectedMessage = "The graph does not contain the provided srcData and/or dstData."; 
    try {
        GraphUtility.areConnected(disconnectedSources, disconnectedDestination, "A", "H");

    } catch (IllegalArgumentException e) {
        actualMessage = e.getMessage();
    }

    //compare actual message with expected message
    assertEquals(true, actualMessage.contains(expectedMessage));
}

@Test
public void testAreConnectedSameNode() {
    assertTrue(GraphUtility.areConnected(sources, destinations, "A", "A"));
    assertTrue(GraphUtility.areConnected(disconnectedSources, disconnectedDestination, "A", "A"));
}

@Test
public void testShortestPathDisconnectedNodes() {

    //Method should return IllegalArgumentException
    var actualMessage = ""; 
    String expectedMessage = "src or dst not found"; 
    try {
        GraphUtility.shortestPath(disconnectedSources, disconnectedDestination, "A", "H");

    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println(e.toString());
        actualMessage = e.getMessage();
    }

    assertEquals(true, actualMessage.contains(expectedMessage));

}

@Test
public void testShortestPathSameNode() {
    List<String> expectedPath = Arrays.asList("A");
    assertEquals(expectedPath, GraphUtility.shortestPath(sources, destinations, "A", "A"));
}

@Test
public void testSortDisconnectedGraph() {
    List<String> sortedOrderDisconnected = GraphUtility.sort(disconnectedSources, disconnectedDestination);
    assertTrue(isValidTopologicalOrder(sortedOrderDisconnected));
}

@Test
public void testSortSingleNodeGraph() {
    List<String> singleNodeSources = Arrays.asList("A");
    List<String> singleNodeDestinations = Arrays.asList("B");
    List<String> sortedOrderSingleNode = GraphUtility.sort(singleNodeSources, singleNodeDestinations);
    assertTrue(isValidTopologicalOrder(sortedOrderSingleNode));
}

@Test
public void testAreConnectedNumInput() {
    assertTrue(GraphUtility.areConnected(numSources, numDestinations, 1, 4));
    assertTrue(GraphUtility.areConnected(numSources, numDestinations, 1, 7));
}

@Test
public void testShortestPathNumInput1() {

    List<Integer> expectedPath = Arrays.asList(3, 4, 5);
    assertEquals(expectedPath, GraphUtility.shortestPath(numSources, numDestinations, 3, 5));
}

@Test
public void testShortestPathNumInput2() {

    List<Integer> expectedPath = Arrays.asList(1, 3);
    assertEquals(expectedPath, GraphUtility.shortestPath(numSources, numDestinations, 1, 3));
}

}
