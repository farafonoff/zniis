package tk.farafonoff.zniis.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CaptchaParserTest {
    CaptchaParser sut;
    
    @Before
    public void init() throws IOException {
        sut = new CaptchaParser();
    }

    @Test
    public void testOnFile() throws IOException {
        List<String> results = Arrays.asList("f", "a4da9e48", "e7781376", "2c25550c","23f1fb0f", "3fb4bdd3");
        InputStream  testData = this.getClass().getResourceAsStream("/captchas");
        BufferedReader br = new BufferedReader(new InputStreamReader(testData));
        String line = br.readLine();
        StringBuilder captcha = new StringBuilder();
        int cindex = 0;
        while(line!=null) {
            System.out.println(line);
            if (!line.trim().isEmpty()) {
                captcha.append(line).append("\n");
            } else {
                String allcaptcha = captcha.toString();
                if (!allcaptcha.trim().isEmpty()) {
                    String result = sut.parseCaptcha(allcaptcha);
                    System.out.println(result);
                    Assert.assertEquals(results.get(cindex), result);
                    cindex++;
                }
                captcha.setLength(0);
            }
            line = br.readLine();
        }
    }
}
