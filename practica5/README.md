<h1 align="center">Practica 5: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)


<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/lenguajemaquina.PNG" width="700" height="250"/></p>

En la practica 5 veremos que este capítulo describe los primeros pasos hacia la construcción de un compilador para un lenguaje de alto nivel basado en objetos típico. Abordaremos esta importante tarea en dos etapas, cada una que abarca dos capítulos De Nand a Tetris (Nand2tetris) Proyecto 7 y Proyecto 8, el proyecto 7 se refiere a Máquina Virtual I: Aritmética de Pila, la aritmética de pila se usa en muchas máquinas virtuales, incluyendo la máquina virtual Java, La aritmética de pila es un método eficiente para realizar operaciones aritméticas en una máquina virtual. Es un concepto fundamental que es importante entender para comprender el funcionamiento de muchas máquinas virtuales. El proyecto 7 introdujo la noción de máquina virtual (VM) y terminó con la construcción de una implementación básica de VM sobre la plataforma Hack. En este capítulo continuamos desarrollando la abstracción, el lenguaje y la implementación de VM. En particular, diseñamos mecanismos basados en pilas para manejar llamadas de subrutinas anidadas (procedimientos, funciones, métodos) de lenguajes procedimentales u orientados a objetos. Como el a medida que avanza el capítulo, ampliamos la implementación básica de VM previamente construida, terminando con un traductor de VM a gran escala. El proyecto 8 se refiere a Máquina Virtual II: Control del Programa, en la clase de Máquina Virtual II, se estudió el control del programa, un conjunto de instrucciones que permiten controlar el flujo de ejecución de un programa, el control del programa es un conjunto de instrucciones que permiten controlar el flujo de ejecución de un programa. Es un concepto fundamental que es importante entender para comprender el funcionamiento de muchas máquinas virtuales. 

<h2 align="center">Teniendo en cuenta el marco de estas dos prácticas que son las máquinas virtuales. ¿Cuál cree que es el futuro de las máquinas virtuales? </h2>
El futuro de las máquinas virtuales es brillante. Las máquinas virtuales ofrecen una serie de ventajas, como la portabilidad, la seguridad y la eficiencia, que las hacen ideales para una amplia gama de aplicaciones. Además, creo que veremos avances en la tecnología de máquinas virtuales que las harán aún más eficientes y fáciles de usar. Por ejemplo, las máquinas virtuales basadas en contenedores están ganando popularidad porque son más ligeras y eficientes que las máquinas virtuales tradicionales.
  
<h2 align="center">Proyecto 7</h2>


<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/pila.PNG" width="600" height="300" /></p> 

Para el traductor VM se implementaran 9 comandos aritmeticos = ["add", "sub", "neg", "eq", "gt", lt", "and, "or", "not"], Tambien contiene los comandos de accesso a memoria que interactuan con la pila  ["push", "pop"] y 8 segmentos de memoria ["constant", "static", "local", "argument", "pointer", "temp", "this", "that"], donde constatnt no es un segmento de memoria de acceso directo, solo es el valor que puede enviar a la pila pero devuelve nada, Statics va de 16 a 255 en los segmentos, local es una variable local, el puntero es designado por el registro, argumento es la funcion a la cual dirige el puntero, temp valores temporales. 

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/vmtrad.PNG" width="800" height="300" /></p> 

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/diagrama.PNG" width="2000" height="800" /></p> 

VMParser: Divide el comando de cada línea en operación, segmento de memoria y desplazamientos (o valor constante), luego llama a las funciones de escritura de código ensamblador en ASMWriter.
```ruby
    import os

COMMENT = '//'
class Parser(object):
    def __init__(self, vm_filename):
        self.vm_filename = vm_filename
        self.vm = open(vm_filename, 'r')
        self.commands = self.commands_dict()
        self.curr_instruction = None
        self.initialize_file()

    def advance(self):
        self.curr_instruction = self.next_instruction
        self.load_next_instruction()

    @property
    def has_more_commands(self):
        return bool(self.next_instruction)

    @property
    def command_type(self):
        return self.commands.get(self.curr_instruction[0].lower())

    @property
    def arg1(self):
        '''Math operation if C_ARITHMETIC'''
        if self.command_type == 'C_ARITHMETIC':
            return self.argn(0)
        return self.argn(1)

    @property
    def arg2(self):
        '''Only return if C_PUSH, C_POP, C_FUNCTION, C_CALL'''
        return self.argn(2)


    def initialize_file(self):
        self.vm.seek(0)
        line = self.vm.readline().strip()
        while not self.is_instruction(line):
            line = self.vm.readline().strip()
        self.load_next_instruction(line)

    def load_next_instruction(self, line=None):
        line = line if line is not None else self.vm.readline().strip()
        self.next_instruction = line.split(COMMENT)[0].strip().split()

    def is_instruction(self, line):
        return line and line[:2] != COMMENT

    def argn(self, n):
        if len(self.curr_instruction) >= n+1:
            return self.curr_instruction[n]
        return None

    def commands_dict(self):
        return {
            'add': 'C_ARITHMETIC',
            'sub': 'C_ARITHMETIC',
            'neg': 'C_ARITHMETIC',
             'eq': 'C_ARITHMETIC',
             'gt': 'C_ARITHMETIC',
             'lt': 'C_ARITHMETIC',
            'and': 'C_ARITHMETIC',
             'or': 'C_ARITHMETIC',
            'not': 'C_ARITHMETIC',
           'push': 'C_PUSH',
            'pop': 'C_POP',
          'label': 'C_LABEL',
           'goto': 'C_GOTO',
        'if-goto': 'C_IF',
       'function': 'C_FUNCTION',
         'return': 'C_RETURN',
           'call': 'C_CALL'
        }
```

ASMWriter: genera el código ensamblador basado en la función llamada por VMParser y los parámetros pasados.

```ruby
    class CodeWriter(object):
    def __init__(self, asm_filename):
        self.asm = open(asm_filename, 'w')
        self.curr_file = None
        self.bool_count = 0 # Number of boolean comparisons so far
        self.addresses = self.address_dict()

    def set_file_name(self, vm_filename):
        '''Reset pointers'''
        self.curr_file = vm_filename.replace('.vm', '').split('/')[-1]
        # self.curr_file = vm_filename.replace('.vm', '')

    def write_arithmetic(self, operation):
        '''Apply operation to top of stack'''
        if operation not in ['neg', 'not']: # Binary operator
            self.pop_stack_to_D()
        self.decrement_SP()
        self.set_A_to_stack()

        if operation == 'add': # Arithmetic operators
            self.write('M=M+D')
        elif operation == 'sub':
            self.write('M=M-D')
        elif operation == 'and':
            self.write('M=M&D')
        elif operation == 'or':
            self.write('M=M|D')
        elif operation == 'neg':
            self.write('M=-M')
        elif operation == 'not':
            self.write('M=!M')
        elif operation in ['eq', 'gt', 'lt']: # Boolean operators
            self.write('D=M-D')
            self.write('@BOOL{}'.format(self.bool_count))

            if operation == 'eq':
                self.write('D;JEQ') # if x == y, x - y == 0
            elif operation == 'gt':
                self.write('D;JGT') # if x > y, x - y > 0
            elif operation == 'lt':
                self.write('D;JLT') # if x < y, x - y < 0

            self.set_A_to_stack()
            self.write('M=0') # False
            self.write('@ENDBOOL{}'.format(self.bool_count))
            self.write('0;JMP')

            self.write('(BOOL{})'.format(self.bool_count))
            self.set_A_to_stack()
            self.write('M=-1') # True

            self.write('(ENDBOOL{})'.format(self.bool_count))
            self.bool_count += 1
        else:
            self.raise_unknown(operation)
        self.increment_SP()

    def write_push_pop(self, command, segment, index):
        self.resolve_address(segment, index)
        if command == 'C_PUSH': 
            if segment == 'constant':
                self.write('D=A')
            else:
                self.write('D=M')
            self.push_D_to_stack()
        elif command == 'C_POP': 
            self.write('D=A')
            self.write('@R13') 
            self.write('M=D')
            self.pop_stack_to_D()
            self.write('@R13')
            self.write('A=M')
            self.write('M=D')
        else:
            self.raise_unknown(command)

    def close(self):
        self.asm.close()

    def write(self, command):
        self.asm.write(command + '\n')

    def raise_unknown(self, argument):
        raise ValueError('{} is an invalid argument'.format(argument))

    def resolve_address(self, segment, index):
        '''Resolve address to A register'''
        address = self.addresses.get(segment)
        if segment == 'constant':
            self.write('@' + str(index))
        elif segment == 'static':
            self.write('@' + self.curr_file + '.' + str(index))
        elif segment in ['pointer', 'temp']:
            self.write('@R' + str(address + int(index))) 
        elif segment in ['local', 'argument', 'this', 'that']:
            self.write('@' + address)
            self.write('D=M')
            self.write('@' + str(index))
            self.write('A=D+A')
        else:
            self.raise_unknown(segment)

    def address_dict(self):
        return {
            'local': 'LCL', 
            'argument': 'ARG',
            'this': 'THIS', 
            'that': 'THAT', 
            'pointer': 3, 
            'temp': 5, 
            'static': 16, 
        }

    def push_D_to_stack(self):
        '''Push from D onto top of stack, increment @SP'''
        self.write('@SP') 
        self.write('A=M') 
        self.write('M=D') 
        self.write('@SP') 
        self.write('M=M+1')

    def pop_stack_to_D(self):
        '''Decrement @SP, pop from top of stack onto D'''
        self.write('@SP')
        self.write('M=M-1')
        self.write('A=M') 
        self.write('D=M') 

    def decrement_SP(self):
        self.write('@SP')
        self.write('M=M-1')

    def increment_SP(self):
        self.write('@SP')
        self.write('M=M+1')

    def set_A_to_stack(self):
        self.write('@SP')
        self.write('A=M')
```

Main: lee el archivo .vm línea por línea, escribe el código de ensamblador traducido en el archivo .asm.

```ruby
    class Main(object):
    def __init__(self, file_path):
        self.parse_files(file_path)
        self.cw = CodeWriter(self.asm_file)
        for vm_file in self.vm_files:
            self.translate(vm_file)

    def parse_files(self, file_path):
        if '.vm' in file_path:
            self.asm_file = file_path.replace('.vm', '.asm')
            self.vm_files = [file_path]
        else:
            file_path = file_path[:-1] if file_path[-1] == '/' else file_path
            path_elements = file_path.split('/')
            path = '/'.join(path_elements)
            self.asm_file = path + '/' + path_elements[-1] + '.asm'
            dirpath, dirnames, filenames = next(os.walk(file_path), [[],[],[]])
            vm_files = filter(lambda x: '.vm' in x, filenames)
            self.vm_files = [path + '/' +  vm_file for vm_file in vm_files]

    def translate(self, vm_file):
        parser = Parser(vm_file)
        self.cw.set_file_name(vm_file)
        while parser.has_more_commands:
            parser.advance()
            self.cw.write('// ' + ' '.join(parser.curr_instruction))
            if parser.command_type == 'C_PUSH':
                self.cw.write_push_pop('C_PUSH', parser.arg1, parser.arg2)
            elif parser.command_type == 'C_POP':
                self.cw.write_push_pop('C_POP', parser.arg1, parser.arg2)
            elif parser.command_type == 'C_ARITHMETIC':
                self.cw.write_arithmetic(parser.arg1)
```
<h2 align="center">Proyecto 8</h2>

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/pila.PNG" width="600" height="300" /></p> 

### Código full-scale VMTranslator (JAVA)
```ruby
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

class Parser {
    private BufferedReader vmFile;
    private String currentCommand;

    public Parser(String pathToVmFile) throws IOException {
        vmFile = new BufferedReader(new FileReader(pathToVmFile));
    }

    public boolean hasMoreCommands() throws IOException {
        return vmFile.ready();
    }

    public void advance() throws IOException {
        currentCommand = vmFile.readLine().replaceAll("/\\.+|\n|\r", "");
    }

    public String getCommandType() {
        String[] splitCommand = currentCommand.split(" ");
        return splitCommand[0];
    }

    public String getArg1() {
        String[] splitCommand = currentCommand.split(" ");
        return splitCommand[1];
    }

    public int getArg2() {
        String[] splitCommand = currentCommand.split(" ");
        return Integer.parseInt(splitCommand[2]);
    }

    public void close() throws IOException {
        vmFile.close();
    }
}

class CodeWriter {
    private FileWriter asmFile;
    private int labelCount;
    private String currentFunction;

    public CodeWriter(String pathToAsmFile) throws IOException {
        asmFile = new FileWriter(pathToAsmFile);
        labelCount = 0;
        currentFunction = "";
    }

    public void setFileName(String fileName) {
        currentFunction = fileName;
    }

    public void writeArithmetic(String command) throws IOException {
        switch (command) {
            case "add":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nM=D+M\n");
                break;
            case "sub":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nM=M-D\n");
                break;
            case "neg":
                asmFile.write("@SP\nA=M-1\nM=-M\n");
                break;
            case "eq":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nD=M-D\n@LABEL" + labelCount + "\nD;JEQ\n@SP\nA=M-1\nM=0\n@END" + labelCount + "\n0;JMP\n(LABEL" + labelCount + ")\n@SP\nA=M-1\nM=-1\n(END" + labelCount + ")\n");
                labelCount++;
                break;
            case "gt":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nD=M-D\n@LABEL" + labelCount + "\nD;JGT\n@SP\nA=M-1\nM=0\n@END" + labelCount + "\n0;JMP\n(LABEL" + labelCount + ")\n@SP\nA=M-1\nM=-1\n(END" + labelCount + ")\n");
                labelCount++;
                break;
            case "lt":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nD=M-D\n@LABEL" + labelCount + "\nD;JLT\n@SP\nA=M-1\nM=0\n@END" + labelCount + "\n0;JMP\n(LABEL" + labelCount + ")\n@SP\nA=M-1\nM=-1\n(END" + labelCount + ")\n");
                labelCount++;
                break;
            case "and":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nM=D&M\n");
                break;
            case "or":
                asmFile.write("@SP\nAM=M-1\nD=M\nA=A-1\nM=D|M\n");
                break;
            case "not":
                asmFile.write("@SP\nA=M-1\nM=!M\n");
                break;
        }
    }

    public void writePushPop(String command, String segment, int index) throws IOException {
        switch (command) {
            case "push":
                switch (segment) {
                    case "constant":
                        asmFile.write("@" + index + "\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                    case "local":
                        asmFile.write("@" + index + "\nD=A\n@LCL\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                    case "argument":
                        asmFile.write("@" + index + "\nD=A\n@ARG\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                    case "this":
                        asmFile.write("@" + index + "\nD=A\n@THIS\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                    case "that":
                        asmFile.write("@" + index + "\nD=A\n@THAT\nA=M+D\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                    case "temp":
                        asmFile.write("@" + (5 + index) + "\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                    case "pointer":
                        if (index == 0) {
                            asmFile.write("@THIS\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        } else {
                            asmFile.write("@THAT\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        }
                        break;
                    case "static":
                        asmFile.write("@" + currentFunction + "." + index + "\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
                        break;
                }
                break;
            case "pop":
                switch (segment) {
                    case "local":
                        asmFile.write("@" + index + "\nD=A\n@LCL\nD=M+D\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n");
                        break;
                    case "argument":
                        asmFile.write("@" + index + "\nD=A\n@ARG\nD=M+D\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n");
                        break;
                    case "this":
                        asmFile.write("@" + index + "\nD=A\n@THIS\nD=M+D\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n");
                        break;
                    case "that":
                        asmFile.write("@" + index + "\nD=A\n@THAT\nD=M+D\n@R13\nM=D\n@SP\nAM=M-1\nD=M\n@R13\nA=M\nM=D\n");
                        break;
                    case "temp":
                        asmFile.write("@SP\nAM=M-1\nD=M\n@" + (5 + index) + "\nM=D\n");
                        break;
                    case "pointer":
                        if (index == 0) {
                            asmFile.write("@SP\nAM=M-1\nD=M\n@THIS\nM=D\n");
                        } else {
                            asmFile.write("@SP\nAM=M-1\nD=M\n@THAT\nM=D\n");
                        }
                        break;
                    case "static":
                        asmFile.write("@SP\nAM=M-1\nD=M\n@" + currentFunction + "." + index + "\nM=D\n");
                        break;
                }
                break;
        }
    }

    public void writeInit() throws IOException {
        asmFile.write("@256\nD=A\n@SP\nM=D\n");
        writeCall("Sys.init", 0);
    }

    public void writeLabel(String label) throws IOException {
        asmFile.write("(" + currentFunction + "$" + label + ")\n");
    }

    public void writeGoto(String label) throws IOException {
        asmFile.write("@" + currentFunction + "$" + label + "\n0;JMP\n");
    }

    public void writeIf(String label) throws IOException {
        asmFile.write("@SP\nAM=M-1\nD=M\n@" + currentFunction + "$" + label + "\nD;JNE\n");
    }

    public void writeCall(String functionName, int numArgs) throws IOException {
        String returnLabel = "RETURN_LABEL" + labelCount;
        labelCount++;
        asmFile.write("@" + returnLabel + "\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        asmFile.write("@LCL\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        asmFile.write("@ARG\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        asmFile.write("@THIS\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        asmFile.write("@THAT\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");
        asmFile.write("@SP\nD=M\n@" + (numArgs + 5) + "\nD=D-A\n@ARG\nM=D\n");
        asmFile.write("@SP\nD=M\n@LCL\nM=D\n");
        asmFile.write("@" + functionName + "\n0;JMP\n");
        asmFile.write("(" + returnLabel + ")\n");
    }

    public void writeReturn() throws IOException {
        asmFile.write("@LCL\nD=M\n@R13\nM=D\n");
        asmFile.write("@5\nA=D-A\nD=M\n@R14\nM=D\n");
        asmFile.write("@SP\nAM=M-1\nD=M\n@ARG\nA=M\nM=D\n");
        asmFile.write("D=A+1\n@SP\nM=D\n");
        asmFile.write("@R13\nAM=M-1\nD=M\n@THAT\nM=D\n");
        asmFile.write("@R13\nAM=M-1\nD=M\n@THIS\nM=D\n");
        asmFile.write("@R13\nAM=M-1\nD=M\n@ARG\nM=D\n");
        asmFile.write("@R13\nAM=M-1\nD=M\n@LCL\nM=D\n");
        asmFile.write("@R14\nA=M\n0;JMP\n");
    }

    public void writeFunction(String functionName, int numLocals) throws IOException {
        currentFunction = functionName;
        asmFile.write("(" + functionName + ")\n");
        for (int i = 0; i < numLocals; i++) {
            asmFile.write("@SP\nA=M\nM=0\n@SP\nM=M+1\n");
        }
    }

    public void close() throws IOException {
        asmFile.close();
    }
}

public class VMTranslator {
    public static void main(String[] args) throws IOException {
        String vmPath = args[0];
        if (vmPath.endsWith(".vm")) {
            String asmPath = vmPath.substring(0, vmPath.length() - 3) + ".asm";
            translate(vmPath, asmPath);
        } else {
            String asmPath = vmPath + "/" + vmPath.substring(vmPath.lastIndexOf("/") + 1) + ".asm";
            translateAll(vmPath, asmPath);
        }
    }

    private static void translate(String vmPath, String asmPath) throws IOException {
        Parser parser = new Parser(vmPath);
        CodeWriter writer = new CodeWriter(asmPath);
        writer.setFileName(vmPath.substring(vmPath.lastIndexOf("/") + 1, vmPath.length() - 3));
        while (parser.hasMoreCommands()) {
            parser.advance();
            String commandType = parser.getCommandType();
            if (commandType.equals("arithmetic")) {
                writer.writeArithmetic(parser.getArg1());
            } else if (commandType.equals("push") || commandType.equals("pop")) {
                writer.writePushPop(commandType, parser.getArg1(), parser.getArg2());
            }
        }
        parser.close();
        writer.close();
    }

    private static void translateAll(String vmPath, String asmPath) throws IOException {
        CodeWriter writer = new CodeWriter(asmPath);
        writer.writeInit();
        for (String filePath : getVmFiles(vmPath)) {
            translate(filePath, asmPath);
        }
        writer.close();
    }

    private static String[] getVmFiles(String vmPath) {
        File folder = new File(vmPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".vm"));
        String[] filePaths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            filePaths[i] = files[i].getPath();
        }
        return filePaths;
    }
}
```


<h2 align="center"> Referencias</h2>

[1] https://www.nand2tetris.org/course

[2] Nisan, N., & Schocken, S. (2021). The elements of computing systems: building a modern computer from first principles. MIT press.

[3] https://www.youtube.com/watch?v=vj1veGsRdbw

[4] https://github.com/kronosapiens/nand2tetris/tree/master
