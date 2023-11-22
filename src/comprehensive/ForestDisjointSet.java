package comprehensive;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a node
 * 
 * @author Ranbir Singh & Anuvesha Chilwal
 * @version April 26, 2023
 *
 * @param <T> - placeholder for value type
 */
class Node<T> {
    T value;
    Node<T> parent; // point to root
    int rank;

    public Node(T value) {
        this.value = value;
        this.parent = this;
        this.rank = 0;
    }
}

/**
 * This class represents a forest disjointSet
 * 
 * @author Ranbir Singh & Anuvesha Chilwal
 * @version April 26, 2023
 *
 * @param <T> - placeholder for Key type
 */
public class ForestDisjointSet<T> implements disjointSet<T> {
    private Map<T, Node<T>> nodeMap = new HashMap<>();

    /**
     * Creates a new singleton set of given element
     * The element should not be already contained in any of the other sets.
     *
     * @param element the element added to set
     */
    @Override
    public void makeSet(T element) {
        nodeMap.put(element, new Node<>(element));
    }

    /**
     * Retrieve the representative element for a given set that the element
     * parameter
     * is apart of. It will call the recursive private method to traverse until the
     * node is found
     *
     * @param element the element of the representative to be return
     * @return the representative element of the set containing the given element
     * @throws IllegalArgumentException if element is passed in is null
     */
    @Override
    public T getRepresentative(T element) {
        Node<T> node = nodeMap.get(element);
        if (node == null) {
            throw new IllegalArgumentException("Invalid element, cannot be null");
        }

        if (node.parent != node) {// if representative is not itself
            node.parent = getRepresentative(node.parent); // get the representative
        }
        return node.parent.value;
    }

    private Node<T> getRepresentative(Node<T> node) {
        if (node.parent != node) {
            node.parent = getRepresentative(node.parent);
        }
        return node.parent; // return when node.parent == node
    }

    /**
     * merge the two given elements if possible. either element cannot be null or part of the same set to create a new set
     * if the elements are of the same set, nothing is changed. 
     *
     * @param element1 element1 to be merged
     * @param element2 element2 to be merged
     * @throws IllegalArgumentException if either of the given elements is not found
     *                               
     */
    @Override
    public void union(T element1, T element2) {
        if (nodeMap.get(element1) == null || nodeMap.get(element2) == null) {
            throw new IllegalArgumentException();
        }

        Node<T> node1 = nodeMap.get(element1); // get node for element1
        Node<T> node2 = nodeMap.get(element2); // get node for element2
        Node<T> representative1 = getRepresentative(node1);
        Node<T> representative2 = getRepresentative(node2);

        if (representative1 != representative2) {
            if (representative1.rank > representative2.rank) {
                representative2.parent = representative1;
            } else if (representative1.rank < representative2.rank) {
                representative1.parent = representative2;
            } else {
                representative1.parent = representative2;
                representative2.rank++;
            }
        }
    }

    @Override
    public String toString() {
        String result = "ForestLL: {\n";
        for (Map.Entry<T, Node<T>> entry : nodeMap.entrySet()) {
            result += "  Element: " + entry.getKey() + ", Representative: " + getRepresentative(entry.getKey())
                    + ", Rank: " + entry.getValue().rank;
            result += "\n";
        }
        result += '}';
        return result;
    }

    public static void main(String[] args) {
        ForestDisjointSet<Integer> dj = new ForestDisjointSet<>();
        dj.makeSet(10);
        dj.makeSet(1);
        System.out.println(dj.toString()); // two singleton sets, different representative
        dj.union(10, 1);
        System.out.println(dj.toString()); // after union, should have the same representative now

        // now create a merge 50 into new set
        dj.makeSet(50);
        System.out.println(dj.toString()); // now 3 elements, before 2nd union
        dj.union(1, 50);
        System.out.println(dj.toString()); // after 2nd union to string
    }
}
