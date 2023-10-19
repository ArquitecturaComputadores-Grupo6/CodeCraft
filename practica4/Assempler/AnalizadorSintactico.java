import java.io.*;
import java.util.*;
class AnalizadorSintactico {
    private BufferedReader asm;
    private String instruccionActual;
    private String simbolo;
    private String destino;
    private String comp;
    private String salto;
    private String tipoComando;
    private int numeroInstruccion;

    public void cargarArchivo(String nombreArchivoASM) throws IOException, FileNotFoundException {
        asm = new BufferedReader(new FileReader(nombreArchivoASM));
        reiniciarArchivo();
        simbolo = null;
        destino = null;
        comp = null;
        salto = null;
        tipoComando = null;
    }

    public void reiniciarArchivo() throws IOException {
        asm.mark(0);
        String linea = asm.readLine().trim();
        while (noEsInstruccion(linea)) {
            linea = asm.readLine().trim();
        }
        instruccionActual = linea;
        numeroInstruccion = -1;
    }

    public void cerrarASM() throws IOException {
        asm.close();
    }

    public boolean tieneMasComandos() {
        return instruccionActual != null && !instruccionActual.isEmpty();
    }

    public void obtenerSiguienteInstruccion() throws IOException {
        String linea = asm.readLine().trim().split("//")[0].trim();
        instruccionActual = linea;
    }

    public void avanzar() throws IOException {
        String ci = instruccionActual;
        if (ci.charAt(0) == '@') {
            analizarA(ci);
            numeroInstruccion++;
        } else if (ci.charAt(0) == '(') {
            analizarL(ci);
        } else {
            analizarC(ci);
            numeroInstruccion++;
        }
        obtenerSiguienteInstruccion();
    }

    private void analizarA(String instruccion) {
        simbolo = instruccion.substring(1);
        tipoComando = "A_COMMAND";
    }

    private void analizarL(String instruccion) {
        simbolo = instruccion.substring(1, instruccion.length() - 1);
        tipoComando = "L_COMMAND";
    }

    private void analizarC(String instruccion) {
        destino = null;
        comp = null;
        salto = null;
        String[] partes = instruccion.split(";");
        String resto = partes[0];
        if (partes.length == 2) {
            salto = partes[1];
        }
        partes = resto.split("=");
        if (partes.length == 2) {
            destino = partes[0];
            comp = partes[1];
        } else {
            comp = partes[0];
        }
        tipoComando = "C_COMMAND";
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getDestino() {
        return destino;
    }

    public String getComp() {
        return comp;
    }

    public String getSalto() {
        return salto;
    }

    public String tipoComando() {
        return tipoComando;
    }

    public int getNumeroInstruccion() {
        return numeroInstruccion;
    }

    private boolean noEsInstruccion(String linea) {
        return linea.isEmpty() || linea.startsWith("//");
    }
}