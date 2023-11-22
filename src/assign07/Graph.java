package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is not generic and assumes that a string name is stored at each vertex.
 * 
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Graph<Type> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<Type, Vertex<Type>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(Type name1, Type name2) {

		//node1: assigns or creates new node exists
		Vertex vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex(name1);
			vertices.put(name1, vertex1);
		}

		//node2: assigns or creates new node exists
		Vertex vertex2;
		if(vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
		}

		//adds vertex to adjaceny list, that is stored in vertex
		//
		vertex1.addEdge(vertex2);
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex v : vertices.values()) {
			// for every edge
			Iterator<Edge> edges = v.edges();
			while(edges.hasNext()) 
				dot.append("\t\"" + v.getName() + "\" -> \"" + edges.next() + "\"\n");
			
		}
		
		return dot.toString() + "}";
	}
	
	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(Vertex v : vertices.values()) 
			result.append(v + "\n");
		
		return result.toString();
	}

	
	/** Return if vertex exists in graph 
	 * @param v
	 * @return
	 */
	public boolean vertexExists(Type v){
		return vertices.containsKey(v); 
	}

	public HashMap<Type, Vertex<Type>> vertices(){
		return vertices; 
	}

	/**
     * Retrieves a list of neighbors for a given vertex in the graph.
     *
     * @param vertexData - the data associated with the vertex whose neighbors should be retrieved
     * @return a list of neighbors for the specified vertex
     * @throws IllegalArgumentException if the vertex is not present in the graph
     */
    public List<Type> getNeighbors(Type v) throws IllegalArgumentException {
        // Check if the vertex exists in the graph
        if (!vertices.containsKey(v)) {
            throw new IllegalArgumentException("Invalid Vertex in graph");
        }

        // Retrieve the vertex object associated with the provided data
        Vertex<Type> vertex = vertices.get(v);

        // Initialize a list to store the neighbors
        List<Type> neighbors = new ArrayList<>();

        // Iterate through the adj list of the vertex and collect the neighbors
        Iterator<Edge<Type>> edges = vertex.edges();
        while (edges.hasNext()) {
            neighbors.add(edges.next().getOtherVertex().getName());
        }

        return neighbors;
    }
}