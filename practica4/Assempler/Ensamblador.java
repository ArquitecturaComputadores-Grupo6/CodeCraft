import java.io.*;
import java.util.*;
class Ensamblador {
    private AnalizadorSintactico analizador;
    private TablaSimbolos tablaSimbolos;
    private Codigo codigo;
    private PrintWriter hack;

    private int direccionRAM;

    public Ensamblador(AnalizadorSintactico analizador, TablaSimbolos tablaSimbolos, Codigo codigo) {
        this.analizador = analizador;
        this.tablaSimbolos = tablaSimbolos;
        this.codigo = codigo;
    }

    public void ensamblar(String nombreArchivoASM) throws IOException {
        prepararArchivos(nombreArchivoASM);
        AnalizadorSintactico analizador = this.analizador;

        while (analizador.tieneMasComandos()) {
            analizador.avanzar();
            if (analizador.tipoComando().equals("L_COMMAND")) {
                escribirL(analizador.getSimbolo());
            }
        }

        analizador.reiniciarArchivo();
        direccionRAM = 16;
        while (analizador.tieneMasComandos()) {
            analizador.avanzar();
            if (analizador.tipoComando().equals("A_COMMAND")) {
                escribirA(analizador.getSimbolo());
            } else if (analizador.tipoComando().equals("C_COMMAND")) {
                escribirC(analizador.getDestino(), analizador.getComp(), analizador.getSalto());
            }
        }

        analizador.cerrarASM();
        hack.close();
    }

    private void prepararArchivos(String nombreArchivoASM) throws FileNotFoundException {
        assert nombreArchivoASM.contains(".asm") : "¡Debe pasar un archivo .asm!";
        try{analizador.cargarArchivo(nombreArchivoASM);}
        catch (IOException ioe){ioe.printStackTrace();}
        String nombreArchivoHack = nombreArchivoASM.replace(".asm", ".hack");
        hack = new PrintWriter(nombreArchivoHack);
    }

    private String crearDireccion(String simbolo) {
        String direccion = Integer.toBinaryString(Integer.parseInt(simbolo));
        String base = "0".repeat(Math.max(0, 15 - direccion.length()));
        return base + direccion;
    }

    private void escribir(String instruccion) {
        hack.write(instruccion + "\n");
    }

    private void escribirA(String simbolo) {
        String instruccion = "0";
        try {
            Integer.parseInt(simbolo);
        } catch (NumberFormatException e) {
            if (!tablaSimbolos.contiene(simbolo)) {
                String direccion = crearDireccion(String.valueOf(direccionRAM));
                tablaSimbolos.agregarEntrada(simbolo, direccion);
                direccionRAM++;
            }
            instruccion += tablaSimbolos.obtenerDireccion(simbolo);
        }
        if (instruccion.equals("0")) {
            instruccion += crearDireccion(simbolo);
        }
        escribir(instruccion);
    }

    private void escribirL(String simbolo) {
        String direccion = crearDireccion(String.valueOf(analizador.getNumeroInstruccion() + 1));
        tablaSimbolos.agregarEntrada(simbolo, direccion);
    }

    private void escribirC(String destino, String comp, String salto) {
        String instruccion = "111";
        instruccion += codigo.comp(comp);
        instruccion += codigo.destino(destino);
        instruccion += codigo.salto(salto);
        escribir(instruccion);
    }
}