package tk.farafonoff.zniis.parser;

import java.io.IOException;

import org.asteriskjava.fastagi.DefaultAgiServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        NumberUpdater updater = new NumberUpdater();
        updater.initiateUpdate("89128588538");
        updater.initiateUpdate("79090561017");
        updater.initiateUpdate("9043131513");
        updater.initiateUpdate("50025");
        updater.initiateUpdate("9226811234");
        DefaultAgiServer agiServer = new DefaultAgiServer(new TryUpdateAgiScript());
        agiServer.setPort(6660);
        agiServer.startup();
    }
}
