package tk.farafonoff.zniis.parser;

import static org.junit.Assert.assertEquals;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;


public class FetcherTest {
    
    String htmlWithR = "<body><form><input type='hidden' name='r' value='111'><pre>  as  df  ";
    
    @Test
    public void testGetR() {
        Document testdoc = Jsoup.parse(htmlWithR);
        String r = Fetcher.getR(testdoc);
        assertEquals("111", r);
    }
    
    @Test
    public void testGetCaptcha() {
        Document testdoc = Jsoup.parse(htmlWithR);
        String r = Fetcher.getCaptcha(testdoc);
        assertEquals("  as  df  ", r);
    }

}
