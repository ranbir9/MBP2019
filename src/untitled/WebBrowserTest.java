package untitled; 
import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class WebBrowserTest {
    @Test
    public void testVisit() throws Exception {
        WebBrowser webBrowser = new WebBrowser();
        URL googleURL = new URL("https://www.google.com/");
        URL facebookURL = new URL("https://www.facebook.com/");

        webBrowser.visit(googleURL);
        assertEquals(googleURL, webBrowser.getCurrent());

        webBrowser.visit(facebookURL);
        assertEquals(facebookURL, webBrowser.getCurrent());
    }

    @Test
    public void testBack() throws Exception {
        WebBrowser webBrowser = new WebBrowser();
        URL googleURL = new URL("https://www.google.com/");
        URL facebookURL = new URL("https://www.facebook.com/");
        URL youtubeURL = new URL("https://www.youtube.com/");

        webBrowser.visit(googleURL);
        System.out.println(webBrowser.getCurrent());
        webBrowser.visit(facebookURL);
        System.out.println(webBrowser.getCurrent());
        webBrowser.visit(youtubeURL);
        assertEquals(youtubeURL, webBrowser.getCurrent());

        URL backURL = webBrowser.back();
        assertEquals(facebookURL, backURL);
        assertEquals(facebookURL, webBrowser.getCurrent());

        backURL = webBrowser.back();
        assertEquals(googleURL, backURL);
        assertEquals(googleURL, webBrowser.getCurrent());

        assertThrows(NoSuchElementException.class, () -> {
            webBrowser.back();
        });
    }

    @Test
    public void testForward() throws Exception {
        WebBrowser webBrowser = new WebBrowser();
        URL googleURL = new URL("https://www.google.com/");
        URL facebookURL = new URL("https://www.facebook.com/");
        URL youtubeURL = new URL("https://www.youtube.com/");

        webBrowser.visit(googleURL);
        webBrowser.visit(facebookURL);
        webBrowser.visit(youtubeURL);
        webBrowser.back();
        webBrowser.back();
        assertEquals(googleURL, webBrowser.getCurrent());

        URL forwardURL = webBrowser.forward();
        assertEquals(facebookURL, forwardURL);
        assertEquals(facebookURL, webBrowser.getCurrent());

        forwardURL = webBrowser.forward();
        assertEquals(youtubeURL, forwardURL);
        assertEquals(youtubeURL, webBrowser.getCurrent());

        assertThrows(NoSuchElementException.class, () -> {
            webBrowser.forward();
        });
    }

    @Test
    public void testHistory() throws Exception {
        WebBrowser webBrowser = new WebBrowser();
        URL googleURL = new URL("https://www.google.com/");
        URL facebookURL = new URL("https://www.facebook.com/");

        webBrowser.visit(googleURL);
        webBrowser.visit(facebookURL);
        SinglyLinkedList<URL> historyList = webBrowser.history();
        assertEquals(facebookURL, historyList.get(0));
    }

}