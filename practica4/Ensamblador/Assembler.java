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