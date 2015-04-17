package tk.farafonoff.zniis.parser;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class Grabber {
    String r;
    String captcha;
    Fetcher fetcher;
    CaptchaParser parser;
    
    public Grabber() throws IOException {
        fetcher = new Fetcher();
        parser = new CaptchaParser();
    }
    
    void initiate(Document init) {
        captcha = parser.parseCaptcha(Fetcher.getCaptcha(init));
        r = Fetcher.getR(init);
    }
    
    public Operator getOperator(String number) throws IOException {
        return Operator.getByOpline(grab(number));
    }
    
    public String grab(String number) throws IOException {
        if (r==null||captcha==null) {
            initiate(fetcher.fetchPage(null, null, null));
        }
        Document response = fetcher.fetchPage(number, r, captcha);
        try {
            initiate(response);
            return Fetcher.getOpLine(response);
        } catch (InvalidCaptcha ic) {
            initiate(response);
            return grab(number);
        }
    }
}
