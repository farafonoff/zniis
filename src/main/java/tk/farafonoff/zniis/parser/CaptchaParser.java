package tk.farafonoff.zniis.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CaptchaParser {
    List<List<String>> digits;

    public CaptchaParser() throws IOException {
        InputStream testData = this.getClass().getResourceAsStream("/digits");
        BufferedReader br = new BufferedReader(new InputStreamReader(testData));
        digits = new ArrayList<List<String>>();
        for (int i = 0; i < 16; ++i) {
            digits.add(new ArrayList<String>());
        }
        for(int lno = 0; lno<7*16;++lno) {
            String line = br.readLine();
            int digit = lno / 7;
            digits.get(digit).add(line);
        }
    }

    List<List<String>> splitData;

    public String parseCaptcha(String source) {
        String[] lines = source.split("\n");
        List<String> rlines = new ArrayList<String>(10);
        int maxLine = 0;
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            if (line.length() > maxLine) {
                maxLine = line.length();
            }
            if (!line.trim().isEmpty()) {
                rlines.add(line);
            }
        }
        if (maxLine==0) return "";
        List<Integer> spaces = new ArrayList<Integer>(10);
        for (int i = 0; i < maxLine; ++i) {
            boolean empty = true;
            for (String str : rlines) {
                if (str.length() > i) {
                    if (str.charAt(i) != ' ') {
                        empty = false;
                    }
                }
            }
            if (empty) {
                spaces.add(i);
            }
        }
        if (spaces.get(0) > 0) {
            spaces.add(0, 0);
        }
        spaces.add(maxLine);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < spaces.size(); ++i) {
            int li = spaces.get(i - 1), ri = spaces.get(i);
            if (ri-li<3) {
                continue;
            }
            for (int digit = 0; digit < digits.size(); ++digit) {
                if (digitMatch(rlines, digit, li, ri)) {
                    result.append(digitToString(digit));
                }
            }
        }
        return result.toString();
    }

    boolean digitMatch(List<String> captcha, int digit, int li, int ri) {
        for (int line = 0; line < 7; ++line) {
            String cline = captcha.get(line).substring(li + 1, ri);            
            String dline = digits.get(digit).get(line);
            if (!cline.equals(dline)) {
                return false;
            }
        }
        return true;
    }
    
    public String digitToString(int digit) {
        switch (digit) {
            case 10: return "a";
            case 11: return "b";
            case 12: return "c";
            case 13: return "d";
            case 14: return "e";
            case 15: return "f";
            default: return String.valueOf(digit);
        }
    }
}
