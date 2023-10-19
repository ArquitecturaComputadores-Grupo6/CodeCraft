import java.util.Map;
import java.util.TreeMap;
public class TablaSimbolos {
    private static Map<String, String> simbolo;
    public TablaSimbolos()
    {
        simbolo = new TreeMap<String,String>();
        simbolo.put("SP", "0");
        simbolo.put("LCL", "1");
        simbolo.put("ARG", "2");
        simbolo.put("THIS", "3");
        simbolo.put("THAT", "4");
        simbolo.put("R0", "0");
        simbolo.put("R1", "1");
        simbolo.put("R2", "2");
        simbolo.put("R3", "3");
        simbolo.put("R4", "4");
        simbolo.put("R5", "5");
        simbolo.put("R6", "6");
        simbolo.put("R7", "7");
        simbolo.put("R8", "8");
        simbolo.put("R9", "9");
        simbolo.put("R10", "10");
        simbolo.put("R11", "11");
        simbolo.put("R12", "12");
        simbolo.put("R13", "13");
        simbolo.put("R14", "14");
        simbolo.put("R15", "15");
        simbolo.put("SCREEN", "16384");
        simbolo.put("KBD", "24576");
    }

    public void addEntry(String key, String value) {
        simbolo.put(key, value);	
    }

    public boolean containKey(String key){
        return simbolo.containsKey(key);
    }

    public String getValue(String val){
        return simbolo.get(val);	
    }

}