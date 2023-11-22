package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 * 
 * @author Anuvesha Chilwal & Ranbir Singh
 * @version March 3, 2022
 */
public class GraphUtility<Type> {

	/**
	 * @param <Type>
	 * @param sources
	 * @param destinations
	 * @param srcData
	 * @param dstData
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) throws IllegalArgumentException {
		Graph<Type> graph = new Graph<>();

		// add source and destination, create edge between two given verticies
		for (int i = 0; i < sources.size(); i++) {
			Type currentSource = sources.get(i);
			Type currentDesination = destinations.get(i);
			// create edge between two verticies
			graph.addEdge(currentSource, currentDesination);
		}

		// Does newly formed graph have the src and destination
		if (graph.vertexExists(srcData) == false || graph.vertexExists(dstData) == false) {
			throw new IllegalArgumentException("The graph does not contain the provided srcData and/or dstData.");
		}

		return dfs(graph, srcData, dstData, new HashSet<Type>());
	}

	/**
	 * 
	 * @param <Type>
	 * @param adjacencyList
	 * @param visited,      starts off as empty, no nodes have been visited yet
	 * @param current
	 * @param target
	 * @return
	 */
	private static <Type> boolean dfs(Graph<Type> graph, Type current, Type dstData, Set<Type> visited) {
		// if current is dstData return true
		if (current.equals(dstData)) {
			return true;
		}

		// add current to visited list
		visited.add(current);

		// Iterate through the adjacent vertices
		for (Type neighbor : graph.getNeighbors(current)) {
			// If the neighbor vertex has not been visited, perform a depth-first search
			// from it
			if (!visited.contains(neighbor)) {
				if (dfs(graph, neighbor, dstData, visited)) {
					return true;
				}
			}
		}
		// path is not found
		return false;
	}

	/**
	 * @param sources
	 * @param destinations
	 * @param srcData
	 * @param dstData
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData,
			Type dstData) throws IllegalArgumentException {

		// Use a queue for BFS
		LinkedList<Type> queue = new LinkedList<>();
		HashMap<Type, Type> prev = new HashMap<>();
		HashSet<Type> visited = new HashSet<>();

		// Create a graph from the sources and destinations lists
		Graph<Type> graph = new Graph<>();
		for (int i = 0; i < sources.size(); i++) {
			Type currentSource = sources.get(i);
			Type currentDesination = destinations.get(i);
			// create edge between two verticies
			graph.addEdge(currentSource, currentDesination);
		}
		if (graph.vertexExists(srcData) == false || graph.vertexExists(dstData) == false) {
			throw new IllegalArgumentException("The graph does not contain the provided srcData and/or dstData.");
		}

		queue.add(srcData);
		visited.add(srcData);

		while (!queue.isEmpty()) {
			Type current = queue.poll();
			if (current.equals(dstData)) {
				List<Type> path = new LinkedList<>();
				Type step = dstData;

				// Build the path
				while (step != null) {
					path.add(0, step);
					step = prev.get(step);
				}
				return path;
			}

			Vertex<Type> currentVertex = graph.vertices().get(current);
			if (currentVertex != null) {
				for (Edge<Type> edge : currentVertex.getEdges()) {
					Type neighbor = edge.getOtherVertex().getName();
					if (!visited.contains(neighbor)) {
						visited.add(neighbor);
						prev.put(neighbor, current);
						queue.add(neighbor);
					}
				}
			}
		}

		throw new IllegalArgumentException("No path exists between src and dst");
	}

	/**
	 * @param <Type>
	 * @param sources
	 * @param destinations
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		// create graph using the sources and destinations
		Graph<Type> graph = new Graph<>();
		for (int i = 0; i < sources.size(); i++) {
			graph.addEdge(sources.get(i), destinations.get(i));
		}

		// store indegree of each vertex
		Map<Type, Integer> inDegree = new HashMap<>();
		for (Vertex<Type> vertex : graph.vertices().values()) {
			inDegree.putIfAbsent(vertex.getName(), 0);
			for (Edge<Type> edge : vertex.getEdges()) {
				Type neighbor = edge.getOtherVertex().getName();
				inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
			}
		}

		// vertices with 0 indegree will be stored in a queue
		Queue<Type> queue = new LinkedList<>();
		for (Map.Entry<Type, Integer> entry : inDegree.entrySet()) {
			if (entry.getValue() == 0) {
				queue.add(entry.getKey());
			}
		}

		List<Type> sortedOrder = new ArrayList<>();
		while (!queue.isEmpty()) {
			Type current = queue.poll();
			sortedOrder.add(current);

			// Update indegree values and enqueue vertices with in-degree of 0
			Vertex<Type> currentVertex = graph.vertices().get(current);
			for (Edge<Type> edge : currentVertex.getEdges()) {
				Type neighbor = edge.getOtherVertex().getName();
				inDegree.put(neighbor, inDegree.get(neighbor) - 1);
				if (inDegree.get(neighbor) == 0) {
					queue.add(neighbor);
				}
			}
		}

		if (sortedOrder.size() != inDegree.size()) {
			throw new IllegalArgumentException("The graph contains a cycle");
		}

		return sortedOrder;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments
	 * --accepts one edge per line or edges terminated with ;
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename     - name of the DOT file
	 * @param sources      - empty ArrayList, when method returns it is a valid
	 *                     "sources" list that can be passed to the public methods
	 *                     in this
	 *                     class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *                     "destinations" list that can be passed to the public
	 *                     methods in
	 *                     this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while (scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if (edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while (scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}

}
