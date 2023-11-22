package assign07;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * now generic and can store any type of data.
 *
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Vertex<T> {

    // used to id the Vertex
    private T name;

    // adjacency list
    private LinkedList<Edge<T>> adj;

    /**
     * Creates a new Vertex object, using the given name.
     *
     * @param name - T data used to identify this Vertex
     */
    public Vertex(T name) {
        this.name = name;
        this.adj = new LinkedList<Edge<T>>();
    }

    /**
     * @return the data used to identify this Vertex
     */
    public T getName() {
        return name;
    }

    /**
     * Adds a directed edge from this Vertex to another.
     *
     * @param otherVertex - the Vertex object that is the destination of the edge
     */
    public void addEdge(Vertex<T> otherVertex) {
        adj.add(new Edge<T>(otherVertex));
    }

    /**
     * @return an iterator for accessing the edges for which this Vertex is the source
     */
    public Iterator<Edge<T>> edges() {
        return adj.iterator();
    }

    /**
     * Generates and returns a textual representation of this Vertex.
     */
    public String toString() {
        String s = "Vertex " + name + " adjacent to vertices ";
        Iterator<Edge<T>> itr = adj.iterator();
        while (itr.hasNext())
            s += itr.next() + "  ";
        return s;
    }

    public List<Edge<T>> getEdges() {
        return adj;
    }
}
