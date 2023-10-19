import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        String nombreArchivoASM = args[0];
        Ensamblador ensamblador = new Ensamblador(new AnalizadorSintactico(), new TablaSimbolos(), new Codigo());
        ensamblador.ensamblar(nombreArchivoASM);
    }
}
