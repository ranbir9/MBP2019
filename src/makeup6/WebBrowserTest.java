package makeup6;

import static org.junit.jupiter.api.Assertions.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;


import org.junit.jupiter.api.Test;

public class WebBrowserTest {


    @Test
    public void testVisit() throws MalformedURLException {
        WebBrowser webBrowser = new WebBrowser();
        URL link1 = new URL("https://www.nba.com");
        URL link2 = new URL("https://www.nfl.com");
        URL link3 = new URL("https://www.wowUtah.com");
        webBrowser.visit(link1);
        webBrowser.visit(link2);
        webBrowser.visit(link3);

        assertEquals(link3, webBrowser.getCurrent()); 
    }

    @Test
    public void testBack() throws MalformedURLException {
        WebBrowser webBrowser = new WebBrowser();
        URL link1 = new URL("https://www.nba.com");
        URL link2 = new URL("https://www.nfl.com");
        URL link3 = new URL("https://www.wowUtah.com");
        webBrowser.visit(link1);
        webBrowser.visit(link2);
        webBrowser.visit(link3);
        webBrowser.back(); 

        assertEquals(link2, webBrowser.getCurrent()); 
    }

    @Test
    public void testForward() throws MalformedURLException {
        WebBrowser webBrowser = new WebBrowser();
        URL link1 = new URL("https://www.nba.com");
        URL link2 = new URL("https://www.nfl.com");
        URL link3 = new URL("https://www.wowUtah.com");
        webBrowser.visit(link1);
        webBrowser.visit(link2);
        webBrowser.visit(link3);
        webBrowser.back(); 
        webBrowser.forward(); 

        assertEquals(link3, webBrowser.getCurrent()); 
    }

    @Test
    public void testHistoryNotModifyCheck() throws MalformedURLException {
        WebBrowser webBrowser = new WebBrowser();
        URL link1 = new URL("https://www.nba.com");
        URL link2 = new URL("https://www.nfl.com");
        URL link3 = new URL("https://www.wowUtah.com");
        webBrowser.visit(link1);
        webBrowser.visit(link2);
        webBrowser.visit(link3);
        webBrowser.back(); 
        webBrowser.forward(); 
        webBrowser.history(); 

        assertEquals(link3, webBrowser.getCurrent()); 
    }

    @Test
    public void testFromForwardToBackToForward() throws MalformedURLException {
        WebBrowser webBrowser = new WebBrowser();
        URL link1 = new URL("https://www.nba.com");
        URL link2 = new URL("https://www.nfl.com");
        URL link3 = new URL("https://www.wowUtah.com");
        webBrowser.visit(link1);
        webBrowser.visit(link2);
        webBrowser.visit(link3);
        webBrowser.back(); 
        webBrowser.back(); 
        webBrowser.forward(); 
        webBrowser.forward(); 

        assertEquals(link3, webBrowser.getCurrent()); 
    }

    
}
