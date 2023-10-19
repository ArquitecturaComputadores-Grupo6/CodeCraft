import java.io.*;
import java.util.*;
class TablaSimbolos {
    private Map<String, String> tablaSimbolos;
    private int posicionRAM;

    public TablaSimbolos() {
        this.tablaSimbolos = tablaBase();
        this.posicionRAM = 16;
    }

    public String obtenerDireccion(String simbolo) {
        return tablaSimbolos.get(simbolo);
    }

    public boolean contiene(String simbolo) {
        return tablaSimbolos.containsKey(simbolo);
    }

    public void agregarEntrada(String simbolo, String direccion) {
        tablaSimbolos.put(simbolo, direccion);
    }

    private Map<String, String> tablaBase() {
        Map<String, String> tabla = new HashMap<>();
        tabla.put("SP", "000000000000000");
        tabla.put("LCL", "000000000000001");
        tabla.put("ARG", "000000000000010");
        tabla.put("THIS", "000000000000011");
        tabla.put("THAT", "000000000000100");
        tabla.put("R0", "000000000000000");
        tabla.put("R1", "000000000000001");
        tabla.put("R2", "000000000000010");
        tabla.put("R3", "000000000000011");
        tabla.put("R4", "000000000000100");
        tabla.put("R5", "000000000000101");
        tabla.put("R6", "000000000000110");
        tabla.put("R7", "000000000000111");
        tabla.put("R8", "000000000001000");
        tabla.put("R9", "000000000001001");
        tabla.put("R10", "000000000001010");
        tabla.put("R11", "000000000001011");
        tabla.put("R12", "000000000001100");
        tabla.put("R13", "000000000001101");
        tabla.put("R14", "000000000001110");
        tabla.put("R15", "000000000001111");
        tabla.put("SCREEN", "100000000000000");
        tabla.put("KBD", "110000000000000");
        return tabla;
    }
}