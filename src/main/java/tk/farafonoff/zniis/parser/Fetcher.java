package tk.farafonoff.zniis.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Fetcher {
    String baseUrl = "http://www.zniis.ru/bdpn/check";
    public Fetcher() {
        // TODO Auto-generated constructor stub
    }
    
    public Fetcher(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Document fetchPage(String number, String r, String captcha) throws IOException {
        String args = "";
        if (number!=null&&r!=null&&captcha!=null) {
            args = String.format("?num=%s&r=%s&number=%s", number,r,captcha);
        }
        String url = baseUrl+args;
        System.out.println(url);
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
    
    public static String getR(Document doc) {
        Elements elements = doc.select("input[type=hidden][name=r]"); 
        return elements.get(0).attr("value");
    }
    
    public static String getCaptcha(Document doc) {
        Elements elements = doc.select("form > pre");
        return elements.get(0).textNodes().get(0).getWholeText();
    }
    public static String getOpLine(Document doc) throws InvalidCaptcha {
        Elements elements = doc.select(".moduletable > p > b");
        if (elements.size()==0) {
            throw new InvalidCaptcha();
        }
        return elements.get(0).text();
    }
}
