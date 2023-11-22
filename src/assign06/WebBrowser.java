package assign06;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import assign06.LinkedListStack;
import assign06.Node;
import assign06.SinglyLinkedList;
import assign06.Stack;

/**
 * 
 * 
 * @author Anuvesha Chilwal & Ranbir Singh
 * @version March 2, 2022
 */
public class WebBrowser {

    private Stack<URL> backStack;
    private Stack<URL> forwardStack;
    private URL current;

    /**Constructor for WebBrowser object, this will create two new linkedLists
     * To Track back and forward
     * 
     */
    public WebBrowser() {
        backStack = new LinkedListStack<>();
        forwardStack = new LinkedListStack<>();
        current = null;
    }

    /** This constructor creates a new web browser with a preloaded history of visited webpages
     * @param history
     */
    public WebBrowser(SinglyLinkedList<URL> history) {
        backStack = new LinkedListStack<>();
        forwardStack = new LinkedListStack<>();
        current = history.getFirst();
        Node<URL> currentNode = history.head.next;
        while (currentNode != null) {
            backStack.push(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    /** Return current page 
     * @return
     */
    public URL getCurrent(){
        return current; 
    }

    /** Visit a URL 
     * @param webpage
     */
    public void visit(URL webpage) {
        backStack.push(current);
        current = webpage;
        forwardStack.clear();
    }

    /** This will go back a page like a back button 
     * @return
     * @throws NoSuchElementException
     */
    public URL back() throws NoSuchElementException {
        if (backStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        forwardStack.push(current);
        current = backStack.pop();
        return current;
    }

    /** This will go forward a page simulating a forward button 
     * @return
     * @throws NoSuchElementException
     */
    public URL forward() throws NoSuchElementException {
        if (forwardStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        backStack.push(current);
        current = forwardStack.pop();
        return current;
    }

    /** generates a history of URLs visited, as a list of URL objects 
     * ordered from most recently visited to least recently visited 
     * @return history 
     */
    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        Stack<URL> tempStack = new LinkedListStack<>();
    
        // Move all URLs from backStack to tempStack -- stack to stack reverse order
        while (!backStack.isEmpty()) {
            tempStack.push(backStack.pop());
        }
    
        // Move all URLs from tempStack to history list
        while (!tempStack.isEmpty()) {
            URL url = tempStack.pop();
            history.insertLast(url);
            backStack.push(url);
        }
    
        // Add current URL to history list if it's not null
        if (current != null) {
            history.insertLast(current);
        }
    
        // Reverse the order of URLs in history list
        Node<URL> prev = null;
        Node<URL> current = history.head;
        Node<URL> next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        history.head = prev;
        
        return history;
    }
    



    public static void main(String[] args) throws MalformedURLException {
        WebBrowser browser = new WebBrowser();
        URL link1 = new URL("https://www.nba.com");
        URL link2 = new URL("https://www.nfl.com");
        URL link3 = new URL("https://www.wowUtah.com");
        browser.visit(link1);
        browser.visit(link2);
        browser.visit(link3);
        System.out.println(browser.history().toString());
        //{ https://www.nba.com -> https://www.nfl.com -> https://www.wowUtah.com } 
    }
}
