<h1 align="center">Practica 3: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)

El lenguaje máquina puede considerarse tanto una herramienta de programación como una parte integral de la plataforma de hardware. De hecho, al igual que decimos que el lenguaje máquina está diseñado para explotar una plataforma de hardware específica, también podemos decir que la plataforma de hardware está diseñada para obtener, interpretar y ejecutar instrucciones escritas en el lenguaje máquina dado. 
Aunque la mayoría de las personas nunca escribirán programas directamente en lenguaje máquina, el estudio de la programación de bajo nivel es un requisito previo para comprender completamente las arquitecturas de computadoras. Además, es bastante fascinante darse cuenta de que los sistemas de software más sofisticados son, en última instancia, largas series de instrucciones elementales, cada una especificando una operación muy simple y primitiva en el hardware subyacente. Como siempre, esta comprensión se logra mejor de manera constructiva, escribiendo código de bajo nivel y ejecutándolo directamente en la plataforma de hardware

Un programa en lenguaje máquina es una serie de instrucciones codificadas. Por ejemplo, una instrucción típica en una computadora de 16 bits podría ser 1010001100011001. Para entender lo que esta instrucción significa, debemos conocer las reglas del juego, es decir, el conjunto de instrucciones de la plataforma de hardware subyacente. 

<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/lenguajemaquina.PNG" width="700" height="250"/></p>

Dado que los códigos binarios suelen ser bastante crípticos, los lenguajes máquina generalmente se especifican utilizando códigos binarios y mnemotécnicos simbólicos (un mnemotécnico es una etiqueta simbólica cuyo nombre da una pista de lo que representa, en nuestro caso elementos de hardware y operaciones binarias), se pueden especificar instrucciones de lenguaje máquina ya sea directamente, como 1010001100011001, o de manera simbólica, como, por ejemplo, "ADD R3, R1, R9".

La notación simbólica se llama lenguaje ensamblador o simplemente ensamblaje, y el programa que realiza la traducción de ensamblaje a binario se llama ensamblador.

<h2 align="center">¿Por qué el lenguaje de máquina es importante para definir la arquitectura computacional? </h2>

El lenguaje de máquina desempeña un papel crítico en la definición de la arquitectura computacional debido a su función como interfaz directa entre el hardware y el software de una computadora. Actúa como el medio mediante el cual los programadores pueden comunicarse directamente con la CPU y otros componentes de hardware para dar instrucciones precisas y controlar eficazmente los recursos. Además, el lenguaje de máquina influye en el diseño de hardware, ya que los ingenieros consideran las instrucciones necesarias para su ejecución eficiente al diseñar la CPU y otros componentes. Comprender el lenguaje de máquina es esencial para la compatibilidad de software con una arquitectura específica y para una comprensión profunda de la arquitectura subyacente de una computadora, lo que es esencial para el desarrollo y la solución de problemas tanto de hardware como de software.


<h2 align="center">Tabla de contenidos:</h2>
<p align="center"> 
  <ol>
    <li>
      <a>Proyecto 4</a>
      <ul>
        <li><a href="#mult">Mult</a></li>
        <li><a href="#fill">Fill</a></li>
      </ul>
    </li>
    <li>
      <a>Proyecto 5</a>
      <ul>
        <li><a href="#memoria">Memoria</a></li>
        <li><a href="#cpu">CPU</a></li>
        <li><a href="#computador">Computador</a></li>
      </ul>
    </li>
  </ol> 
  </p>
  
<h2 align="center">Proyecto 4</h2>

### Mult

<p><img align="right" src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/mult.PNG" width="300" height="300" />
(Ejemplo de una tarea aritmética): Las entradas de este programa son los valores almacenados en R0 y R1 (RAM[0] y RAM[1]). El programa calcula el producto R0 * R1 y almacena el resultado en R2 (RAM[2]). Supon que R0 ≥ 0, R1 ≥ 0, y R0 * R1 < 32768 (tu programa no necesita verificar estas condiciones). El script Multi.test proporcionado y el archivo de comparación Mult.cmp están diseñados para probar tu programa en algunos valores representativos.</p> 

#### Proceso: 
Se almacena en la ram 2 el resultado en las iteraciones, y durante cada iteracion va sumando el valor de R0 y el numero de iteraciones será el valor de R1, y da como resultado la multiplicacion

<pre>
(BEGIN)
	 //El valor de la posicion 2 de la ram lo establecemos como 0
	@R2
	M=0
(LOOP)
	// Condicion de salida del ciclo, si R1 <= 0, termina el ciclo
	@R1
	D=M
	@END
	D;JLE
	// Se suma el valor de R0 a R2
	@R0
	D=M
	@R2
	M=M+D
	// Decrecemos el valor de R1
	@R1
	M=M-1
	//Se indica que se vuelve a iniciar el ciclo
	@LOOP
	0;JMP
(END)
	// Termina la ejecucion
	@END
	0;JMP
  
    
</pre>


### Fill

<p><img align="right" src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/fill.PNG" width="300" height="300" />
(Ejemplo de una tarea de entrada/salida): Este programa ejecuta un bucle infinito que escucha el teclado. Cuando se presiona una tecla (cualquier tecla), el programa oscurece toda la pantalla escribiendo "negro" en cada píxel. Cuando no se presiona ninguna tecla, el programa borra la pantalla escribiendo "blanco" en cada píxel. Puedes elegir oscurecer y borrar la pantalla en cualquier patrón espacial, siempre y cuando presionar una tecla continuamente durante el tiempo suficiente resulte en una pantalla completamente oscurecida, y no presionar ninguna tecla durante el tiempo suficiente resulte en una pantalla borrada. Este programa tiene un script de prueba (Fill.tst) pero no un archivo de comparación; debe verificarse inspeccionando visualmente la pantalla simulada en el emulador de CPU.
</p> 

#### Proceso: 
En el código la sección "KBDCHECK," verifica si se han presionado teclas en el teclado, si no se presionan teclas, continúa llenando la pantalla con píxeles blancos y negros alternados bajo las secciones "BLACK" y "WHITE." La sección "CHANGE" se encarga de este llenado, y el programa puede reiniciarse en "RESTART" al finalizar.

<pre>
    (RESTART)
    @SCREEN
    D=A
    @0
    // Colocamos la dirección de inicio de la pantalla en la posición de memoria RAM 0
    M=D

(KBDCHECK)
    // Salta si alguna tecla del teclado está presionada y salta a WHITE si no hay teclas presionadas
    @KBD
    D=M
    @BLACK
    D;JGT
    @WHITE
    D;JEQ

    // Vuelve a KBDCHECK si ninguna condición se cumple
    @KBDCHECK
    0;JMP

// Se establece el valor que se usará para llenar la pantalla (negativo, equivalente a 16 bits de unos)
(BLACK)
    @1
    M=-1

    // Salta a CHANGE
    @CHANGE
    0;JMP

(WHITE)
    @1
    // Establece el valor que se usará para llenar la pantalla (cero)
    M=0

    // Salta a CHANGE
    @CHANGE
    0;JMP

// Carga el valor a utilizar para llenar la pantalla en D (BLACK o WHITE)
(CHANGE)
    @1
    D=M

    // Obtiene la dirección de la pantalla para llenar un píxel y llena el píxel con el valor en D (BLACK o WHITE)
    @0
    A=M
    M=D

    @0
    // Incrementa la dirección de la pantalla para el siguiente píxel
    D=M+1
    @KBD
    // Calcula la diferencia entre KBD y la dirección de la pantalla
    D=A-D
    @0
    // Incrementa la dirección de la pantalla para el siguiente píxel
    M=M+1
    A=M

    @CHANGE
    // Si A=0, sale del bucle ya que toda la pantalla se ha llenado
    D;JGT

    // se reinicia
    @RESTART
    0;JMP

</pre>


<h2 align="center">Proyecto 5</h2>

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/vonnewman.png" width="600" height="300" /></p> 

### Memoria

la "memoria" se refiere específicamente a la memoria principal o RAM (Random Access Memory). En el modelo de von Neumann, la memoria principal es donde se almacenan los datos y programas que la unidad central de procesamiento (CPU) utiliza para realizar cálculos y ejecutar programas. La memoria en este contexto es fundamental porque permite almacenar temporalmente los datos y programas necesarios para el funcionamiento de la computadora. La CPU puede leer y escribir en esta memoria principal para acceder a las instrucciones y los datos que necesita para llevar a cabo las operaciones de procesamiento. Sin la memoria principal, la CPU no tendría acceso rápido a los datos y programas, lo que haría que la ejecución de instrucciones y la operación de la computadora sean extremadamente lentas o incluso imposibles. 

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/memoria.PNG" width="500" height="400" /></p> 

#### Proceso: 
El chip toma entradas de datos in, señal de carga load, y dirección address, y produce una salida out. Si load está activado, carga el valor in en la dirección especificada en address. Si no, simplemente devuelve el valor almacenado en esa dirección. La memoria para la computadora Hack, que es un ejemplo de una máquina basada en el modelo de von Neumann. En este código, la memoria se organiza en tres partes principales: RAM principal, memoria de pantalla y memoria de teclado, y se accede a ellas según la dirección especificada en address.

<pre>
    CHIP Memory {
    IN in[16], load, address[15];
    OUT out[16];

    PARTS:
    DMux4Way(in=load, sel=address[13..14], a=ramA, b=ramB, c=ScreenIn, d=null);
    Or(a=ramA, b=ramB, out=ramIn);

    RAM16K(in=in, load=ramIn, address=address[0..13], out=ramOut);
    Screen(in=in, load=ScreenIn, address=address[0..12], out=screenOut);
    Keyboard(out=kbdOut);

    Mux4Way16(a=ramOut, b=ramOut, c=screenOut, d=kbdOut, sel=address[13..14], out=out);
}
</pre>

### CPU

CPU (Central Processing Unit) suele ser un único chip de circuito integrado, pero también puede estar formada por varios chips. Es el procesador más importante de una computadora determinada. Su circuito electrónico ejecuta instrucciones de un programa de computadora, como operaciones aritméticas, lógicas, de control y de entrada/salida. 
<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/cpu.png" width="500" height="300" /></p> 

#### Proceso: 
La CPU se compone de dos partes principales: la unidad de control (CU) y la unidad aritmética lógica (ALU). Hay algunos chips que sabemos que necesitaremos para la implementación de nuestra CPU. 
Aquí hay una lista de estos componentes principales:  A Register: Para almacenar valores en nuestro registro A, D Register: Para almacenar valores en nuestro registro D, ALU: Para realizar cálculos, Program Counter (PC): Para realizar un seguimiento del control de flujo.

<pre>
    CHIP CPU {

    IN  inM[16],         // M value input  (M = contents of RAM[A])
        instruction[16], // Instruction for execution
        reset;           // Signals whether to re-start the current
                         // program (reset==1) or continue executing
                         // the current program (reset==0).

    OUT outM[16],        // M value output
        writeM,          // Write to M? 
        addressM[15],    // Address in data memory (of M)
        pc[15];          // address of next instruction

    PARTS:
    // Put your code here:
	
Mux16(	a=false, 
	b=instruction,
	sel=instruction[15],
	out[0]=cJGT,
	out[1]=cJEQ,
	out[2]=cJLT,
	out[3]=cDestM,
	out[3]=writeM,
	out[4]=cDestD,
	out[5]=cDestA,
	out[6]=cAluNo,
	out[7]=cAluF,
	out[8]=cAluNy,
	out[9]=cAluZy,
	out[10]=cAluNx,	
	out[11]=cAluZx,
	out[12]=cAOrM,
	out[15]=cType	
);

ALU(	x=xIn, y=yIn, 
	zx=cAluZx, 
	nx=cAluNx, 
	zy=cAluZy, 
	ny=cAluNy, 
	f=cAluF, 
	no=cAluNo, 
	out=aluOut, 
	out=outM,
	zr=zerop, 
	ng=negp
);

Or(a=zerop, b=negp, out=lteq);
Not(in=lteq=, out=posp);

Mux16(a=instruction, b=aluOut, sel=cType, out=aMuxOut);
Mux16(a=aRegOut, b=inM, sel=cAOrM, out=yIn);

Not(in=cType, out=notCType);
Or(a=notCType, b=cDestA, out=loadA);
ARegister(in=aMuxOut, load=loadA, out=aRegOut, out[0..14]=addressM);

DRegister(in=aluOut, load=cDestD, out=xIn);

And(a=cJEQ, b=zerop, out=JEQ);
And(a=cJLT, b=negp, out=JLT);
And(a=cJGT, b=posp, out=JGT);
Or(a=JEQ, b=JLT, out=JLE);
Or(a=JLE, b=JGT, out=jump);

PC(in=aRegOut, load=jump, inc=true, reset=reset, out[0..14==pc, out[15]=false);

}
</pre>

### Computador

La computadora que se realiza en este proyecto es lo más simple posible, basada en un hardware mínimo y compacto. Sin embargo, esta configuración es lo suficientemente potente para ejecutar programas escritos en un lenguaje de programación similar a java, ofreciendo un rendimiento razonable y una experiencia de usuario satisfactoria.

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/computer.PNG" width="500" height="300" /></p> 

#### Proceso: 
La computadora incluye CPU, ROM y RAM. 
Cuando el reset es 0, se ejecuta el programa almacenado en la ROM del computador, en cambio cuando el reset es 1, se reinicia la ejecución del programa.
Teniendo en cuenta lo anterior, para iniciar la ejecuación de un programa hay que pulsar reset "arriba" (1) y "abajo" (0). Dependiendo del código del programa, la pantalla puede mostrar alguna salida y el usuario puede interactuar con el computador a través del teclado.

<pre>
    CHIP Computer {

    IN reset;

    PARTS:
    CPU(inM=loadFromMem,
	instruction=romOut,
	reset=reset,
	outM=storeMem,
	writeM=writeMem,
	adressM=adressMem,
	pc=pcOut);

    Memory(in=storeMem,
	   load=writeMem,
	   adress=adressMem,
	   out=loadFromMem);

    ROM32K(adress=pcOut, out=romOut);
}
</pre>

<h2 align="center"> Referencias</h2>

[1] https://www.nand2tetris.org/course

[2] Nisan, N., & Schocken, S. (2021). The elements of computing systems: building a modern computer from first principles. MIT press.

[3] https://youtu.be/oDrLvsnxIwA?si=TdnhLKZvK7S4rTSx

[4] Building a CPU - From NAND To Tetris, Part 6 (https://youtu.be/kERT8zt61b8?si=iqBnIHcy2-_UJA12)

[5] CPU (https://courses.cs.washington.edu/courses/cse390b/21sp/readings/hack_cpu_chips.html)

[6] Definicion CPU (https://en.wikipedia.org/wiki/Central_processing_unit)

[7] https://www.youtube.com/watch?v=xHh2GdJl4Cs
