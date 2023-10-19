<h1 align="center">Practica 4: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)

Como hemos visto en los anteriores proyectos, la cpu solo "entiende" lenguaje de maquina osea bits (1 y 0) por lo cual se dificulata el manejo a la hora de trabajar con los dispositivos es por esto que surge el lenguaje ensamblador como una solucion al problema de manejar el complicado y extenso lenguaje maquina, el cual cumple la funcion de traductor de un lenguaje de alto nivel a codigo maquina, hay que entender tambien que hay deferentes reglas a seguir dependiento del ensamblador y las funciones que se quieran desempeñar.
 
Los programadores rara vez escriben programas directamente en lenguaje de máquina. En cambio, los programadores que desarrollan programas de alto rendimiento (por ejemplo, software de sistema, aplicaciones críticas y software para sistemas integrados) a menudo inspeccionan el código de ensamblaje generado por los compiladores. Lo hacen para comprender cómo su código de alto nivel se implementa realmente en el hardware y cómo ese código puede optimizarse para obtener un mejor rendimiento. Uno de los actores en este proceso es el programa que traduce el código escrito en un lenguaje simbólico de máquina en código escrito en lenguaje binario de máquina. Este programa se llama ensamblador.

<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica4/images/ensamblador.PNG" width="800" height="600" /></p>

<h2 align="center">Teniendo en cuenta las características del ensamblador, ¿Cuál es la principal limitante que observan? Justifique su respuesta. </h2>

el Ensamblador enfrenta una serie de limitaciones notables en comparación con los lenguajes de alto nivel. La principal limitante que observan reside en su naturaleza que demanda un mayor tiempo de programación, dado que requiere un nivel de detalle y comprensión profunda de la arquitectura subyacente del hardware. Esta complejidad resulta en programas fuente extensos y complicados, lo que puede aumentar la posibilidad de errores y afectar los recursos del sistema de manera imprevista. Además, su estrecha relación con la arquitectura específica del hardware limita su portabilidad, lo que implica que el código escrito en ensamblador no es fácilmente transferible entre diferentes plataformas y arquitecturas.

Un ensamblador crea código objeto traduciendo instrucciones mnemónicas a códigos operativos, y resolviendo los nombres simbólicos para posiciones de memoria y otras entidades. El uso de referencias simbólicas es una característica básica de los ensambladores, evitando tediosos cálculos y direccionamiento manual luego de cada modificación del programa. La mayoría de los ensambladores también incluyen facilidades para crear macros, a fin de generar series de instrucciones cortas que se ejecutan en tiempo real, en lugar de utilizar subrutinas.

### ENSAMBLADOR

<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica4/images/comDestSim.PNG" width="600" height="600" /></p>

## Assembler:
La clase Assembler integra la lógica principal de su programa, porque define el método principal que es el punto de entrada. Esta clase es responsable de la creación de instancias de los objetos de clase TablaSymbolos, Code y Parser. Además, la carga de leer y manipular un archivo de código ensamblador está condicionada a realizar operaciones para escribir los resultados en un archivo de salida. Esta característica es un largo camino para abrir el botón de comando tipo A y C en el dispositivo.
```ruby
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Assembler {
    public static  int counter=0;
    public static int nextRam = 16;
    public static String compT,destT,jumpT; 
    public static void main(String[] args) {
        String nombre = args[0].substring(0, args[0].indexOf('.'));
        String outArchivo = nombre +".hack";
        TablaSimbolos st = new TablaSimbolos();
        Code ct = new Code(); 
        Parser newParser = new Parser(args[0]);
        File out = new File(outArchivo);
        FileWriter fw = null;
        try {
            fw = new FileWriter(out.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        while(newParser.masComandos()) {  
            if(newParser.ComandTipo == TipoComando.L_COMMAND) { 
                st.addEntry(newParser.simbolo(),Integer.toString(counter)) ;
            }else counter++;
            newParser.advance();
        }
        newParser.ContLinea = 0; 
        while(newParser.masComandos()){
            if(newParser.TipoCom()== TipoComando.A_COMMAND){
                if(newParser.lineas[newParser.ContLinea].startsWith("@")){
                    String tmp  = newParser.simbolo();
                    if(newParser.isNum(tmp)){
                        int xxx = Integer.parseInt(tmp);
                        tmp = Parser.decToBin(xxx);
                        tmp = newParser.addZero(tmp);
                        try {
                            bw.write(tmp + '\n');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else 
                    if(!st.containKey(tmp)){
                        st.addEntry(tmp,Integer.toString(nextRam));
                        nextRam++;
                    }
                    if(st.containKey(tmp)){
                        String tmp2 = st.getValue(tmp);
                        int xxx = Integer.parseInt(tmp2);
                        tmp2 = Parser.decToBin(xxx);
                        tmp2 = newParser.addZero(tmp2);
                        try {
                            bw.write(tmp2+'\n');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if(newParser.TipoCom()== TipoComando.C_COMMAND){
                if(newParser.lineas[newParser.ContLinea].contains("=")){
                    destT = ct.getDest(newParser.dest());
                    compT = ct.getComp(newParser.comp());
                    jumpT = ct.getJump("NULL");
                    try {
                        bw.write("111" + compT + destT + jumpT +'\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(newParser.lineas[newParser.ContLinea].contains(";")){
                    destT = ct.getDest("NULL"); 
                    compT = ct.getComp(newParser.comp());
                    jumpT = ct.getJump(newParser.jump());
                    try {
                        bw.write("111" + compT + destT + jumpT +'\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            newParser.advance();		
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Parser:
El Class Parser asume la tarea crucial de analizar un archivo de código de desmontaje y análisis. Además, realizar principalmente operaciones de lectura y manipulación de archivos, determinar la forma efectiva del tipo de comando actual (A, C o L) y la forma efectiva de extraer símbolos, destinos, cálculos y comandos. Tipo A, L o C. Además, se ofrecen las funciones auxiliares que se proponen en el documento del proyecto diseñadas para facilitar la manipulación de cadenas y la conversión de valores decimales a binarios.

```ruby
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
```

## Código:
La clase Code juega un papel fundamental al definir los mapeos esenciales utilizados para los campos de destino, cálculo y salto dentro de los comandos de tipo C. Proporciona métodos para obtener los códigos binarios correspondientes a estos campos, asegurando así la correcta interpretación de los comandos de tipo C dentro del contexto del lenguaje ensamblador.

```ruby
import java.util.Map;
import java.util.TreeMap;
public class Code {
    private Map<String, String> jump;
    private Map<String, String> comp;
    private Map<String, String> dest;

    public Code()
    {
        jump = new TreeMap<String,String>();
        jump.put("NULL", "000");
        jump.put("JGT", "001");
        jump.put("JEQ", "010");
        jump.put("JGE", "011");
        jump.put("JLT","100");
        jump.put("JNE","101");
        jump.put("JLE","110");
        jump.put("JMP", "111");

        comp = new TreeMap<String,String>();
        comp.put("0", "0101010");
        comp.put("1", "0111111");
        comp.put("-1","0111010");
        comp.put("D", "0001100");
        comp.put("A", "0110000");
        comp.put("!D", "0001101");
        comp.put("!A", "0110001");
        comp.put("-D", "0001111");
        comp.put("-A", "0110011");
        comp.put("D+1","0011111");
        comp.put("A+1","0110111");
        comp.put("D+A","0000010");
        comp.put("D-A","0010011");
        comp.put("A-D","0000111");
        comp.put("D&A","0000000");
        comp.put("D|A","0010101");	
        comp.put("M","1110000");
        comp.put("!M","1110001");
        comp.put("-M","1110011");
        comp.put("M+1","1110111");
        comp.put("M-1","1110010");
        comp.put("D+M","1000010");
        comp.put("D-M","1010011");
        comp.put("M-D","1000111");
        comp.put("D&M","1000000");
        comp.put("D|M","1010101");
        comp.put("D-1","0001110");
        comp.put("A-1","0110010");

        dest = new TreeMap<String, String>();
        dest.put("NULL","000");
        dest.put("M","001");
        dest.put("D","010");
        dest.put("MD","011");
        dest.put("A","100");
        dest.put("AM","101");
        dest.put("AD","110");
        dest.put("AMD","111");		

    }

    public String getDest(String key){
        return dest.get(key);
    }

    public String getComp(String key){
        return comp.get(key);
    }

    public String getJump(String key){
        return jump.get(key);
    }

}
```

## TablaSímbolos:
La clase TablaSymbolos gestiona el administrador de tablas de búsqueda de símbolos utilizado en el lenguaje ensamblador. Estas funciones incluyen la capacidad de nuevos ensambladores de símbolos de tabla, como verificar y recuperar valores seleccionados, para administrarlos en el contexto de símbolos comunitarios que brindan una estructura organizada y accesible.

```ruby
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
```

# Tipo de orden:
TipoComando es un tipo de enumeración que es un papel crucial para definir y especificar los diferentes tipos de comandos que puedes encontrar en el lenguaje ensamblador. Tipos de comando definidos A_COMMAND, L_COMMAND y C_COMMAND, para facilitar la identificación y gestión detallada de diferentes tipos de comandos para completar el código de análisis e identificación.

```ruby
public enum TipoComando{
    A_COMMAND,L_COMMAND,C_COMMAND
}
```

### Add.asm: 
Suma las constantes 2 y 3 y coloca el resultado en R0.

### Max.asm: 
Calcula max(R0, R1) y coloca el resultado en R2.

### Rect.asm: 
Dibuja un rectángulo en la esquina superior izquierda de la pantalla. El rectángulo tiene 16 píxeles de ancho y R0 píxeles de alto. Antes de ejecutar este programa, coloca un valor no negativo en R0.

### Pong.asm: 
Un clásico juego arcade para un solo jugador. Una pelota rebota repetidamente en las "paredes" de la pantalla. El jugador intenta golpear la pelota con una paleta, presionando las teclas de flecha izquierda y derecha. Por cada golpe exitoso, el jugador gana un punto y la paleta se reduce un poco, para hacer el juego más desafiante. Si el jugador no golpea la pelota, el juego termina. Para salir del juego, presiona ESC. Nota: El programa Pong se desarrolló utilizando herramientas presentadas en la Parte II del curso y en el libro. En particular, el software del juego se escribió en el lenguaje Jack de alto nivel y se tradujo al archivo Pong.asm dado por el compilador Jack. Aunque el programa Pong de alto nivel tiene solo unas 300 líneas de código, la aplicación Pong ejecutable tiene alrededor de 20,000 líneas de código binario, la mayoría de las cuales es el sistema operativo Jack. Antes de ejecutar el código, selecciona 'No animation' en el menú 'Animation' (es decir, sin resaltado de código). Puedes controlar la velocidad de ejecución del código usando el control deslizante de velocidad. El juego comenzará después de unos segundos, durante los cuales el sistema operativo se inicializa.


<p><img align="right" src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica4/images/mult.PNG" width="300" height="300" />

<h2 align="center"> Referencias</h2>

[1] https://www.nand2tetris.org/course

[2] https://html.rincondelvago.com/sistemas-operativos_62.html

[3] [https://youtu.be/oDrLvsnxIwA?si=TdnhLKZvK7S4rTSx](https://www.youtube.com/watch?v=JXpqdI-77LQ)

[4] https://github.com/sergey-korchagin/nand2tetris/blob/master/ex7/Assembler.java
