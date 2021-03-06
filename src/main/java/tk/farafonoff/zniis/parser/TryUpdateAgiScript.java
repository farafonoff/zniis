package tk.farafonoff.zniis.parser;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;

import java.io.IOException;

public class TryUpdateAgiScript extends BaseAgiScript {
    
    NumberUpdater updater;

    public TryUpdateAgiScript() throws IOException {
        updater = new NumberUpdater();
    }

    public void service(AgiRequest arg0, AgiChannel arg1) throws AgiException {
        String number = arg0.getParameter("num");
        if (number==null) {
            number = arg0.getCallerIdName();
        }
        System.out.println("INCOMING REQUEST "+number);        
        updater.initiateUpdate(number);
    }

}
