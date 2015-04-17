package tk.farafonoff.zniis.parser;

import java.io.IOException;

/**
 * Created by Artem on 17.04.2015.
 */
public interface Resolver {
    Operator getOperator(String number) throws IOException;
}
