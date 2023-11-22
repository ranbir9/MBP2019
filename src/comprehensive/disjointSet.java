package comprehensive;

/**
 * This interface represents a map of keys to values. It cannot contain
 * duplicate keys, and each key can map to at most one value.
 * 
 * @author Anuvesha Chilwal & Ranbir Singh
 * @version April 25, 2022
 *
 * @param <T> - placeholder for type 
 * 
 */
public interface disjointSet<T> {
    /*
     * Creates a new singleton Set of element 
     */
    void makeSet(T element);
    
    /**
	 * This will return the representative the element is part of 
	 * 
	 * @param Element
	 * @return The representative for the given set that the element is part of 
	 */
    T getRepresentative(T element);
    
    /**
	 * This will merge the the two elements together into a single set, 
     * if they already are not in the same set 
	 * @param e1,e2
	 * 
	 */
    void union(T e1, T e2);
}
