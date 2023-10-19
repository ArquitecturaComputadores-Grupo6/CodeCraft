import java.io.*;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class Parser {
    private String linea; 
    public String lineas[]; 
    public static String lineaActual;
    public int ContLinea;
    public BufferedReader br;
    public static TipoComando ComandTipo;
    public static int bitSimbol  = 16;

    Parser(String exFileName){
        ContLinea = 0;
        FileInputStream archivo = null; 
        try {
            archivo = new FileInputStream(exFileName);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        int contenido;
        try {
            while (( contenido = archivo.read()) != -1) {
                linea += (char) contenido;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream in = new DataInputStream(archivo);
        br = new BufferedReader(new InputStreamReader(in));

        linea =  eliminarComentarios(linea);
        lineas = linea.split("\n");
        for(int i=0; i < lineas.length; i++){
            lineas[i] =  lineas[i].trim();
        }
    }

    public boolean masComandos() {
        if(ContLinea != lineas.length){ return true;}
        return false;
    }

    public  void advance() {
        ContLinea++;
    }

    public TipoComando TipoCom(){
        if(lineas[ContLinea].startsWith("(")){
            return ComandTipo = TipoComando.L_COMMAND;
        }else if(lineas[ContLinea].startsWith("@")){
            return ComandTipo = TipoComando.A_COMMAND;
        }
        return TipoComando.C_COMMAND;
    }

    public String simbolo(){
        String etiqueta = "";
        if(lineas[ContLinea].startsWith("@")){
            etiqueta = lineas[ContLinea];
            etiqueta = etiqueta.replaceAll("@", "");
        }else if(lineas[ContLinea].startsWith("(")){
            etiqueta = lineas[ContLinea];
            etiqueta = etiqueta.replaceAll("\\((.*?)\\)", "$1");
        }
        return etiqueta;
    }

    public String dest() {
        if(lineas[ContLinea].contains("=")){
            String dest = lineas[ContLinea];
            int endIndex = dest.lastIndexOf("=");
            dest =  dest.substring(0,endIndex);
            return dest;
        }
        return null;
    }

    public String comp() {
        String comp = lineas[ContLinea]; 
        if(lineas[ContLinea].contains("=")){
            int endIndex = comp.lastIndexOf("=");
            comp =  comp.substring(endIndex+1,comp.length());
        }else if(lineas[ContLinea].contains(";")){
            //retComp =  retComp.substring(0,1) ;
            int endIndex = comp.lastIndexOf(";");
            comp =  comp.substring(0,endIndex);
        }
        return comp;
    }

    public String jump() {
        if(lineas[ContLinea].contains(";")){
            String jump = lineas[ContLinea]; 
            int endIndex = jump.lastIndexOf(";");
            return jump.substring(endIndex+1,jump.length());
        }
        return null;
    }

    public String eliminarComentarios(String archivo) {
        String newArchivo =  archivo.replaceAll( "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/|(?m)^[ \t]*\r?\n|null|\t", "" );
        newArchivo = newArchivo.replaceAll("(?m)^[ \t]*\r?\n", "");
        return newArchivo;
    }

    public static String decToBin(int value) {
        String binario = Integer.toBinaryString(value);
        return binario;
    }

    public boolean isNum(String num){
        NumberFormat formato = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formato.parse(num, pos);
        return  num.length() == pos.getIndex();
    }

    public String addZero(String num){
        StringBuilder sb = new StringBuilder();
        for (int i=16-num.length(); i>0; i--) {
            sb.append('0');
        }
        sb.append(num);
        String resultado = sb.toString();
        return resultado;
    }

}