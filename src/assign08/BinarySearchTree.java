package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a binary search tree and 
 * all methods required from the SortedSet interface
 * 
 * @author Anuvesha Chilwal and Ranbir Singh
 * @version March 17, 2022	
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    // Node class
    class Node {
        Type data;
        Node left, right;

        Node(Type data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    Node head = null;
    int size = 0;

    /**
	 * Adds item to BST
	 * @return boolean if item is added to BST
	 * @param item - item to add to the BST
	 */
    @Override
    public boolean add(Type item) {
        Node curr = head; // current tracker

        // LIST IS EMPTY, ADD AT HEAD
        if (head == null) { // means size is 0
            head = new Node(item);
            size++;
            curr = head;
            return true;
        }

        // traverse through, check if item exist, if not traverse left or right
        while (curr != null) {
            int compare = item.compareTo(curr.data); // compare item with current
            if (compare == 0) {
                return false; // item already exists
            } else if (compare < 0) {
                if (curr.left == null) { // insert value here :)
                    size++;
                    curr.left = new Node(item);
                    return true;
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.right == null) { // insert value here
                    size++;
                    curr.right = new Node(item);
                    return true;
                } else {
                    curr = curr.right;
                }
            }
        }
        return false;
    }

    /**
	 * Adds items of a collection to BST
	 * @return boolean if item is added to BST
	 * @param items - items to add to the BST
	 */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        if(items == null)
            return false; 
        
        for(Type item : items){
            add(item); 
        }
        return true; 
    }

    /**
	 * clears the existing BST
	 * 
	 * @param item - item to add to the BST
	 */
    @Override
    public void clear() {
        head = null;
    }

    /**
	 * Checks if item exists in the BST
	 * @return boolean if the item is part of the BST
	 * @param item - item to check if it exists in the BST
	 */
    @Override
    public boolean contains(Type item) {
        if (item == null)
            return false;

        Node curr = head;
        while (curr != null) {
            int compare = item.compareTo(curr.data); // compare item with current
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    return false;
                }
            } else {
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
	 * Check of all items in collection exist in BST
	 * @return boolean checks if all values in collection exists in the BST
	 * @param items - items check if they exist in BST
	 */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        Node curr = head; 
        //list is empty 
        if(curr == null){
            return false; 
        }

        //int initialSize = size;
        for(Type item : items){
            if(!contains(item)){
                return false; 
            }
        }
        //all items are found 
        return true; 
    }

    /**
	 * Returns object, that is the smallest node data in the BST aka the far most left node data
	 * @return Type the smallest element in the BST
	 * 
	 */
    @Override
    public Type first() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("There is no First!");
        }
        // traverse far left as possible and return
        Node curr = head;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr.data;
    }

    /**
	 * Checks if BST is empty aka is head null
	 * @return boolean if head is null
	 * 
	 */
    @Override
    public boolean isEmpty() {
        // if (head == null)
        //     return true;
        return head == null;
    }

    /**
	 * Returns the largest node data in BST aka most right node data
	 * @return Type the largest node data
	 * 
	 */
    @Override
    public Type last() throws NoSuchElementException {
        // if BST is empty
        if (head == null)
            throw new NoSuchElementException("There is no Last!");

        Node curr = head; // traverse far right and return
        while (curr != null && curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    /**
	 * Remove item from BTS if it exists
	 * @return boolean if item exists and is removed
	 * @param item - item to be removed from BTS
	 */
    @Override
    public boolean remove(Type item) {
        // Find the node to remove
        Node parent = null;
        Node curr = head;
        while (curr != null) {
            int cmp = item.compareTo(curr.data);
            if (cmp == 0) {
                break; // found the node to remove
            } else if (cmp < 0) {
                parent = curr;
                curr = curr.left;
            } else {
                parent = curr;
                curr = curr.right;
            }
        }
    
        // Node not found, return false
        if (curr == null) {
            return false;
        }
    
        // Remove the node
        //size--;
        if (curr.left == null && curr.right == null) {
            //curr is a leaf 
            if (parent == null) {
                head = null;
            } else if (parent.left == curr) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (curr.left != null && curr.right == null) {
            //curr only has left node
            if (parent == null) {
                head = curr.left;
            } else if (parent.left == curr) {
                parent.left = curr.left;
            } else {
                parent.right = curr.left;
            }
        } else if (curr.left == null && curr.right != null) {
            //curr only has right node
            if (parent == null) {
                head = curr.right;
            } else if (parent.left == curr) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        } else {
            //curr has left and right
            Node successorParent = curr;
            Node successor = curr.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            curr.data = successor.data;
            if (successorParent == curr) {
                // The successor is the right child of curr
                curr.right = successor.right;
            } else {
                // The successor is a left child of its parent
                successorParent.left = successor.right;
            }
        }
        size--; 
        return true;
    }

    /**
	 * Remove all items from a collection from the BTS 
	 * @return boolean BTS does not contains the items specified to be removed 
	 * @param items items that should not exist in BTS
	 */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean anyRemoved = false;
        boolean remove = false; 
        Node curr = head; 
        //list is empty 
        if(curr == null){
            return false; 
        }

        //int initialSize = size;
        for(Type item : items){
            remove = remove(item); 
            if(remove)
                anyRemoved = true; 
        }
        //if item is removed, size has changed, then bst has changed
        return anyRemoved;  
    }

    /**
	 * Return the size of the BTS
	 * @return int - the number of nodes in the BST
	 * 
	 */
    @Override
    public int size() {
        return size;
    }

    /**
	 * make a ArrayList of the BST, this should be ordered from smallest to largest by node.data
	 * @return ArrayList<Type> of sorted order
	 * 
	 */
    @Override
    public ArrayList<Type> toArrayList() {
        //HashSet<Type> visited = new HashSet<>(); 
        ArrayList<Type> sorted = new ArrayList<>();
        LinkedList<Node> stack = new LinkedList<>(); 
        Node curr = head; 

        //add left most nodes 
        while (curr != null || !stack.isEmpty()) {
            // push nodes onto the stack until we reach the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // process the leftmost node on the stack
            curr = stack.pop();
            sorted.add(curr.data);
            // set current to the right child
            curr = curr.right;
        }
        return sorted; 
    }

    /**
	 * Generate Dot String for visualization 
	 * @return String to visualizue using http://www.webgraphviz.com/
	 * 
	 */
    public String generateDot() {
        StringBuilder str = new StringBuilder("digraph d {\n");

        if (head != null) {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(head);

            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.left != null) {
                    str.append("\t\"" + node.data + "\" -> \"" + node.left.data + "\"\n"); 
                    queue.add(node.left);
                }

                if (node.right != null) {
                    str.append("\t\"" + node.data + "\" -> \"" + node.right.data + "\"\n"); 
                    queue.add(node.right);
                }
            }
        }
        str.append("}");
        return str.toString();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(10);
        bst.add(8);
        bst.add(20);
        bst.add(15);
        bst.add(21);
        bst.add(17);
        bst.add(18);
        bst.add(16);
        bst.add(5);
        // System.out.println("head: " + bst.head.data);

        System.out.println("BST contains 10: " + bst.contains(10));
        System.out.println("BST contains 18: " + bst.contains(18));
        System.out.println("BST contains 17: " + bst.contains(17));
        System.out.println("BST contains 16: " + bst.contains(16));
        System.out.println("BST contains 18: " + bst.contains(18));
        System.out.println("BST contains 8: " + bst.contains(8));
        System.out.println("BST contains 20: " + bst.contains(20));
        System.out.println("BST contains 11000: " + bst.contains(11000));
        //System.out.println("Remove 15: \n");
        //bst.remove(15);
        //System.out.println(bst.generateDot());
        //System.out.println(bst.toArrayList());

        System.out.println("First: " + bst.first());

        System.out.println("First(): " + bst.first());
        System.out.println("Last(): " + bst.last());

        System.out.println(bst.generateDot());

        //Remove 5, 15 
        ArrayList<Integer> remove2 = new ArrayList<>(); 
        remove2.add(15); 
        remove2.add(5); 
        bst.removeAll(remove2); 
        System.out.println(bst.generateDot());

        //check if bst containsAll 2 nodes that exist
        ArrayList<Integer> containsTrue = new ArrayList<>(); 
        containsTrue.add(17); 
        containsTrue.add(21); 

        //check if bst containsAll a node that doesn't exsit 
        ArrayList<Integer> containsFalse = new ArrayList<>(); 
        containsFalse.add(100); 
        containsFalse.add(21); 

        System.out.println("\nContainsAll() --- TRUE");
        System.out.println("BST contains 17 and 21 is: " + bst.containsAll(containsTrue));

        System.out.println("\nContainsAll() --- FALSE");
        System.out.println("BST contains 100 and 21 is: " + bst.containsAll(containsFalse));
    }
}
