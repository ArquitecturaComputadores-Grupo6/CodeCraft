import java.io.*;
import java.util.*;
class Codigo {
    public String destino(String mnemonico) {
        String[] binario = {"0", "0", "0"};
        if (mnemonico == null) {
            return String.join("", binario);
        }
        if (mnemonico.contains("A")) {
            binario[0] = "1";
        }
        if (mnemonico.contains("D")) {
            binario[1] = "1";
        }
        if (mnemonico.contains("M")) {
            binario[2] = "1";
        }
        return String.join("", binario);
    }

    public String comp(String mnemonico) {
        Map<String, String> compDict = new HashMap<>();
        compDict.put("0", "101010");
        compDict.put("1", "111111");
        compDict.put("-1", "111010");
        compDict.put("D", "001100");
        compDict.put("A", "110000");
        compDict.put("!D", "001101");
        compDict.put("!A", "110001");
        compDict.put("-D", "001111");
        compDict.put("-A", "110011");
        compDict.put("D+1", "011111");
        compDict.put("A+1", "110111");
        compDict.put("D-1", "001110");
        compDict.put("A-1", "110010");
        compDict.put("D+A", "000010");
        compDict.put("D-A", "010011");
        compDict.put("A-D", "000111");
        compDict.put("D&A", "000000");
        compDict.put("D|A", "010101");

        String bitA = "0";
        if (mnemonico.contains("M")) {
            bitA = "1";
            mnemonico = mnemonico.replace("M", "A");
        }
        String bitC = compDict.getOrDefault(mnemonico, "000000");
        return bitA + bitC;
    }

    public String salto(String mnemonico) {
        Map<String, String> saltoDict = new HashMap<>();
        saltoDict.put("JGT", "001");
        saltoDict.put("JEQ", "010");
        saltoDict.put("JGE", "011");
        saltoDict.put("JLT", "100");
        saltoDict.put("JNE", "101");
        saltoDict.put("JLE", "110");
        saltoDict.put("JMP", "111");
        return saltoDict.getOrDefault(mnemonico, "000");
    }
}