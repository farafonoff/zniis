package tk.farafonoff.zniis.parser;

import java.util.Arrays;
import java.util.List;

public class Operator {
    String matchText;
    
    String name;
    
    int mncId;
    

    
    public Operator(String matchText, String name, int mncId) {
        super();
        this.matchText = matchText;
        this.name = name;
        this.mncId = mncId;
    }
    
    

    @Override
    public String toString() {
        return "Operator [matchText=" + matchText + ", name=" + name + ", mncId=" + mncId + "]";
    }

    public static List<Operator> operators = Arrays.asList(
            new Operator("Мобильные ТелеСистемы", "MTS", 99), 
            new Operator("ВымпелКом", "BEELINE", 1),
            new Operator("TELE2","TELE2",20),
			new Operator("T2","TELE2",20),
			new Operator("Т2 Мобайл","TELE2",20),
            new Operator("МегаФон","MEGAFON",2));
    
    public static Operator getByOpline(String request) {
        for(Operator op: operators) {
            if (request.contains(op.matchText)) {
                return op;
            }
        }
        return null;
    }

}
