package untitled; 
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {

    private Stack<URL> backStack;
    private Stack<URL> forwardStack;

    public URL getCurrent() {
        return current;
    }

    private URL current;

    public WebBrowser() {
        backStack = new LinkedListStack<>();
        forwardStack = new LinkedListStack<>();
        current = null;
    }

    public WebBrowser(SinglyLinkedList<URL> history) {
        backStack = new LinkedListStack<>();
        forwardStack = new LinkedListStack<>();
        if (history.isEmpty()) {
            current = null;
        } else {
            current = history.get(0);
            for (int i = 1; i < history.size(); i++) {
                backStack.push(history.get(i));
            }
        }
    }

    public void visit(URL webpage) {
        if (current != null) {
            backStack.push(current);
        }
        current = webpage;
        forwardStack.clear();
    }

    public URL back() throws NoSuchElementException {
        if (backStack.isEmpty()) {
            throw new NoSuchElementException("No previous webpage.");
        }
        forwardStack.push(current);
        current = backStack.pop();
        return current;
    }

    public URL forward() throws NoSuchElementException {
        if (forwardStack.isEmpty()) {
            throw new NoSuchElementException("No next webpage.");
        }
        backStack.push(current);
        current = forwardStack.pop();
        return current;
    }

    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
        historyList.insertFirst(current);
        Stack<URL> backStackCopy = new LinkedListStack<>();
        while (!backStack.isEmpty()) {
            backStackCopy.push(backStack.pop());
        }
        return historyList;
    }
    
    public static void main(String[] args) throws MalformedURLException {
        WebBrowser webBrowser = new WebBrowser();
        URL googleURL = new URL("https://www.google.com/");
        URL facebookURL = new URL("https://www.facebook.com/");
        URL youtubeURL = new URL("https://www.youtube.com/");

        System.out.println(webBrowser.history().toString());

        webBrowser.visit(googleURL);
        System.out.println(webBrowser.getCurrent());
        webBrowser.visit(facebookURL);
        System.out.println(webBrowser.getCurrent());
        webBrowser.visit(youtubeURL);

    }

}
