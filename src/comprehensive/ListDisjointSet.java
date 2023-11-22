package comprehensive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a arraylist disjoint set 
 * 
 * @author Ranbir Singh & Anuvesha Chilwal
 * @version April 26, 2023
 *
 * @param <T> - placeholder for data type of arrayList 
 */
public class ListDisjointSet<T> implements disjointSet<T> {
    public Map<T, ArrayList<T>> listMap = new HashMap<>();

    /**
     * Creates a new singleton arraylist of given element, then added to set 
     * The element should not be already contained in any of the other sets.
     *
     * @param element the element added to set
     */
    @Override
    public void makeSet(T element) {
        if(element == null){ // element cannot be null 
            throw new IllegalArgumentException(); 
        }
        
        //add element and create new array list in single line
        ArrayList<T> l = new ArrayList<>(Arrays.asList(element)); 
        //l.add(element);
        listMap.put(element, l); 
    }

    /**
     * Retrieve the representative element for a given arraylist. It will return the first
     * element of the arraylist corresponding the to the key-value pair of the map
     *
     * @param element the element of the representative to be return
     * @return the representative element of the set containing the given element
     * @throws IllegalArgumentException if element is passed in is null
     */
    @Override
    public T getRepresentative(T element) {
        if(element == null){
            throw new IllegalArgumentException(); 
        }
        T result = listMap.get(element).get(0); //representative should be the first element in the list
        return result; 
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
    public void union(T e1, T e2) {
        //illegal argument if e1 or e2 are null
        if(e1 == null && e2 == null){
            throw new IllegalArgumentException(); 
        }

        ArrayList<T> list1 = listMap.get(e1); //e1 list 
        ArrayList<T> list2 = listMap.get(e2); //e2 list

        if(list1.size() < list2.size()){
            list1.addAll(list2); 
            for(T e : list2) {
                listMap.replace(e, list1); 
            }
        } else{
            list2.addAll(list1); 
            for(T e : list1) {
                listMap.replace(e, list2); 
            }
        }

    }
    @Override
    public String toString(){
        String result = "ForestLL: {\n";
        for (Map.Entry<T, ArrayList<T>> entry : listMap.entrySet()) {
            result += "  Element: " + entry.getKey() + ", Representative: " + getRepresentative(entry.getKey()); 
            result += "\n"; 
        }
        result += '}';
        return result;
    }

    //testing 
    public static void main(String[] args){
        ListDisjointSet<Integer> dj = new ListDisjointSet(); 
        dj.makeSet(10);
        dj.makeSet(1);
        System.out.println(dj.toString()); // two singleton sets, different representative
        
        dj.union(10, 1);
        System.out.println(dj.toString()); // after union, should have the same representative now 
        
        for (Map.Entry<Integer, ArrayList<Integer>> entry : dj.listMap.entrySet()) {
            System.out.println("leader of each list: " + entry.getValue().get(0));
        }

        for (Map.Entry<Integer, ArrayList<Integer>> entry : dj.listMap.entrySet()) {
            System.out.println("list: " + entry.getValue().toString());
        }

        

        //now create a merge 50 into new set 
        //dj.makeSet(50); 
        //System.out.println(dj.toString()); // now 3 elements, before 2nd union
        //dj.union(1, 50);
        //System.out.println(dj.toString()); // after 2nd union to string

        //get rep of element 1 which should be itself 1
    }

}
