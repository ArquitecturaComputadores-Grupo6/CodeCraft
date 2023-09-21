<h1 align="center">Practica 2: Boolean Arithmetic y Memoria</h1>

## ¿Cuál es el objetivo de cada uno de esos proyectos con sus palabras y describa qué debe hacer para desarrollarlo?
Construir los chips descritos del proyecto 2, estos chips se construyen con los expuestos del proyecto 1, hasta llegar a la Unidad Lógica Aritmética(ALU). Construir los chips del proyecto 3, estos chips se construyen con los expuestos en los proyectos anteriores hasta llegar a una unidad de memoria de acceso aleatorio (RAM).

## Explique las principales diferencias entre la lógica aritmética y la lógica secuencial.
La lógica aritmética y la lógica secuencial  son dos tipos de lógica digital que se utilizan en el  diseño de circuitos electrónicos. La principal diferencia entre ambas es que la lógica aritmética se utiliza para realizar operaciones matemáticas, mientras la lógica secuencial almacena datos y genera secuencia de salida. 
La lógica aritmética se utiliza para realizar operaciones matemáticas básicas como lo son suma, resta, multiplicacion y division ya que los circuitos lógicos aritméticos están formados por compuertas lógicas, que son dispositivos electrónicos que realizan operaciones lógicas básicas como AND, OR Y NOT. Los circuitos aritméticos se utilizan en una amplia gama de aplicaciones como: calculadoras, computadoras, sistemas de control, procesadores de señales, entre otras.
La lógica secuencial se utiliza para almacenar datos y generar secuencias de salida, están formados por Flip-Flops que son dispositivos electrónicos que pueden almacenar un bit de información. Se utilizan en una amplia gama de aplicaciones como: memorias, contadores, registros de desplazamiento, controladores, entre otras. 

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/inicio.jpg" width="400" height="250"/></p> 

<h2 align="center">Tabla de contenidos:</h2>
---
  <ol>
    <li>
      <a>Proyecto 2</a>
      <ul>
        <li><a href="#halfadder">HalfAdder</a></li>
        <li><a href="#fulladder">FullAdder</a></li>
        <li><a href="#add16">Add16</a></li>
        <li><a href="#inc16">Inc16</a></li>
        <li><a href="#alu">ALU</a></li>
      </ul>
    </li>
    <li>
      <a>Proyecto 3</a>
      <ul>
        <li><a href="deff">DEFF</a></li>
        <li><a href="#bit">Bit</a></li>
        <li><a href="#registro">Registro</a></li>
        <li><a href="#ram8">Ram8</a></li>
        <li><a href="#ram64">Ram64</a></li>
        <li><a href="#ram512">Ram512</a></li>
	<li><a href="#ram4k">Ram4k</a></li>
	<li><a href="#ram16k">Ram16k</a></li>
	<li><a href="#pc">PC</a></li>
      </ul>
    </li>
  </ol>

<h2 align="center">Proyecto 2</h2>

### HalfAdder
---
Un HalfAdder es un circuito lógico digital que realiza la suma binaria de dos números binarios de un solo bit. Tiene dos entradas, A y B, y dos salidas, SUM y CARRY. La salida SUM es el bit menos significativo (LSB) del resultado, mientras que la salida CARRY es el bit más significativo (MSB) del resultado, lo que indica si hubo un arrastre de la suma de las dos entradas. El HalfAdder se puede implementar utilizando puertas básicas como las puertas XOR y AND.
<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/halfadder.PNG" width="400" height="250" />
<img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/halfaddr.PNG" width="400" height="250" /></p> 

#### Nota:
Para la realizacion del .hdl se uso una compuerta XOR codificada en la practica anterior donde ingresan dos entradas y se obtiene el LSB, y una compuerta AND con las dos entradas para obtener el MSB 

<pre>
  /**
 * Computes the sum of two bits.
 */
CHIP HalfAdder {
    IN a, b;    // 1-bit inputs
    OUT sum,    // Right bit of a + b 
        carry;  // Left bit of a + b

    PARTS:
    	Xor(a=a,b=b,out=sum);
	    And(a=a,b=b,out=carry);
}
</pre>
<p align="center">
  <img>
</p>

### FullAdder
---
FullAdder es el sumador que suma tres entradas y produce dos salidas. Las dos primeras entradas son A y B y la tercera entrada es una entrada transportada como C-IN. El acarreo de salida se designa como C-OUT y la salida normal se designa como S, que es SUM. El C-OUT también se conoce como detector de mayoría 1, cuya salida aumenta cuando más de una entrada está alta. 
<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/fulladder.PNG" width="400" height="250" />
<img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/fulladdr.PNG" width="600" height="250" /></p> 

El Full Adder es fundamental en la aritmética binaria y se utiliza para construir sumadores más grandes, como el sumador de 4 bits, el sumador de 8 bits y así sucesivamente. Estos componentes son esenciales en la construcción de circuitos digitales y computadoras para realizar operaciones matemáticas binarias como la adición.

#### Nota:
Para la realizacion del .hdl se implementaron 2 HalfAdder y una compuerta OR, El primer halfAdder tiene como entrada a y b, sus salida Sum y c van como entradas al segundo halfAdder donde se obtiene otra salida Sum (la del fullAdder) luego para obtener a Carry(del fullAdder) se pasa las salidas Carry de los dos HalfAdder por una compuerta OR.

<pre>
/**
 * Computes the sum of three bits.
 */

CHIP FullAdder {
    IN a, b, c;  // 1-bit inputs
    OUT sum,     // Right bit of a + b + c
        carry;   // Left bit of a + b + c

    PARTS:
  	HalfAdder(a=a,b=b,sum=sum1,carry=car1);
	HalfAdder(a=c,b=sum1,sum=sum,carry=car2);
	Or(a=car1,b=car2,out=carry);

}
</pre>

### Add16
Los chips de memoria y registro representan números enteros mediante patrones de n bits, n es 16, 32, 64, etc., según la plataforma informática. El chip cuyo trabajo es sumar dichos números se llama sumador multibit, o simplemente sumador. un sumador de 16 bits, teniendo en cuenta que la misma lógica y especificaciones se amplían como está a cualquier sumador de n bits.
<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/adder16.png" width="600" height="250" /></p> 

#### Nota:
Para la realizacion del .hdl se emplearon 16 FullAdder en los cuales el primero no tiene input c, y el carry pasa a ser la entrada c al siguiente FullAdder hasta el ultimo en el cual se anula el carry.

<pre>
/**
 * Adds two 16-bit values.
 * The most significant carry bit is ignored.
 */
CHIP Add16 {
    IN a[16], b[16];
    OUT out[16];
    PARTS:
    FullAdder(a=a[0], b=b[0], c=false, sum=out[0], carry=car1);
    FullAdder(a=a[1], b=b[1], c=car1, sum=out[1], carry=car2);
    FullAdder(a=a[2], b=b[2], c=car2, sum=out[2], carry=car3);
    FullAdder(a=a[3], b=b[3], c=car3, sum=out[3], carry=car4);
    FullAdder(a=a[4], b=b[4], c=car4, sum=out[4], carry=car5);
    FullAdder(a=a[5], b=b[5], c=car5, sum=out[5], carry=car6);
    FullAdder(a=a[6], b=b[6], c=car6, sum=out[6], carry=car7);
    FullAdder(a=a[7], b=b[7], c=car7, sum=out[7], carry=car8);
    FullAdder(a=a[8], b=b[8], c=car8, sum=out[8], carry=car9);
    FullAdder(a=a[9], b=b[9], c=car9, sum=out[9], carry=car10);
    FullAdder(a=a[10], b=b[10], c=car10, sum=out[10], carry=car11);
    FullAdder(a=a[11], b=b[11], c=car11, sum=out[11], carry=car12);
    FullAdder(a=a[12], b=b[12], c=car12, sum=out[12], carry=car13);
    FullAdder(a=a[13], b=b[13], c=car13, sum=out[13], carry=car14);
    FullAdder(a=a[14], b=b[14], c=car14, sum=out[14], carry=car15);
    FullAdder(a=a[15], b=b[15], c=car15, sum=out[15], carry=null);
}
</pre>

### Inc16
Es conveniente tener un chip especial dedicado a agregar el constante 1 a un número dado. Inc16 es un circuito incrementador de 16 bits que incrementa un número binario de 16 bits en 1. Tiene una entrada, A, y una salida, OUT. La salida OUT es el resultado de incrementar A en 1. 
<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/inc16.jpeg" width="400" height="250" /></p> 

#### Nota:
Este codigo se puede realizar con 16 HalfAdder, tomando a b[0] como true y el resto de valores b se les asigna la salida carry del anterior HalfAdder, pero Inc16 se puede implementar utilizando un circuito Add16 y una constante de 1 bit. La constante se establece en 1 (el primer valor de la entrada b es true y el resto los toma como false como valor por defecto) y se suma a A usando el circuito Add16. El resultado de la suma es la salida del circuito Inc16.

<pre>
 * 16-bit incrementer:
 * out = in + 1 (arithmetic addition)
 */

CHIP Inc16 {
    IN in[16];
    OUT out[16];

    PARTS:
    Add16(a=in,b[0]=true,out=out);
}	
</pre>

### ALU
La ALU contiene una variedad de conexiones eléctricas de entrada y salida, lo que llevó a transmitir las señales digitales entre la electrónica externa y la ALU. La entrada de ALU recibe señales de los circuitos externos y, en respuesta, la electrónica externa recibe señales de salida de ALU.

<h2 align="center">Proyecto 3</h2>

### DEFF

D Flip-Flop ( DFF ), también conocido como Data Flip-Flop (DFF) o Delay Flip-Flop (DFF), es un circuito electrónico digital que se utiliza para retrasar el cambio de estado de su señal de salida (Q) hasta el siguiente aumento. Se produce el flanco de una señal de entrada de temporización de reloj. DFF se emplea como parte de elementos de almacenamiento de memoria y también de procesadores de datos.
<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/dff.png" width="400" height="250" />
<img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/def.png" width="400" height="250" /></p> 

### Bit
En informática, bit es la unidad mínima de información. Se utiliza para representar la contraposición entre dos valores (apagado y encendido, falso y verdadero, abierto y cerrado). En telecomunicaciones e informática, los bits son normalmente calculados en conjunto. Así tenemos 8 bits, 16 bits, 32 bits, etc.
los procesadores que tienen 16, 32 y 64 bits, tienen 16, 32 y 64 registros y capacidad ALU (Unidad aritmético lógica) de 16, 32 y 64 bits, pudiendo por tanto procesar los datos en grupos de bits de idéntica cantidad o submúltiplos de esta.
<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/bit.PNG" width="400" height="250" /></p> 

#### Nota: 
Para 1 -bit se tiene en cuenta que la salida de un DEFF en cualquier momento t hará eco de su salida en el momento t 1, produciendo la función clásica esperada de una unidad de almacenamiento. y un Multiplexor antes ya que Están dotados de entradas de control capaces de seleccionar una, y solo una, de las entradas de datos para permitir su transmisión desde la entrada seleccionada hacia dicha salida.

<pre>
	/**
 * 1-bit register:
 * If load[t] == 1 then out[t+1] = in[t]
 *                 else out does not change (out[t+1] = out[t])
 */

CHIP Bit {
    IN in, load;
    OUT out;

    PARTS:
    Mux(a=def, b=in, sel=load, out=mux1);
    DFF(in=mux1, out=def, out=out);
}
</pre>

### Registro
Una vez que hayamos desarrollado el mecanismo básico para recordar un bit a lo largo del tiempo, podremos construir fácilmente registros arbitrariamente amplios. Esto se puede lograr formando una matriz de tantos registros de un solo bit como sea necesario, creando un registro que contenga valores de varios bits. El parámetro de diseño básico de dicho registro es su ancho (el número de bits que contiene), por ejemplo, 16, 32 o 64. El contenido de varios bits de dichos registros generalmente se denomina palabras.
<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/registro.PNG" width="400" height="250" /></p> 

<pre>
/**
 * 16-bit register:
 * If load[t] == 1 then out[t+1] = in[t]
 * else out does not change
 */

CHIP Register {
    IN in[16], load;
    OUT out[16];

    PARTS:
    Bit(in=in[15], load=load, out=out[15]);
    Bit(in=in[14], load=load, out=out[14]);
    Bit(in=in[13], load=load, out=out[13]);
    Bit(in=in[12], load=load, out=out[12]);
    Bit(in=in[11], load=load, out=out[11]);
    Bit(in=in[10], load=load, out=out[10]);
    Bit(in=in[9],  load=load, out=out[9]);
    Bit(in=in[8],  load=load, out=out[8]);
    Bit(in=in[7],  load=load, out=out[7]);
    Bit(in=in[6],  load=load, out=out[6]);
    Bit(in=in[5],  load=load, out=out[5]);
    Bit(in=in[4],  load=load, out=out[4]);
    Bit(in=in[3],  load=load, out=out[3]);
    Bit(in=in[2],  load=load, out=out[2]);
    Bit(in=in[1],  load=load, out=out[1]);
    Bit(in=in[0],  load=load, out=out[0]);
}
</pre>

### Ram8

<pre>
/**
 * Memory of 8 registers, each 16 bit-wide. Out holds the value
 * stored at the memory location specified by address. If load==1, then 
 * the in value is loaded into the memory location specified by address 
 * (the loaded value will be emitted to out from the next time step onward).
 */

CHIP RAM8 {
    IN in[16], load, address[3];
    OUT out[16];

    PARTS:
    DMux8Way(in=load, sel=address, a=mw1, b=mw2, c=mw3, d=mw4, e=mw5, f=mw6, g=mw7, h=mw8);
    Register(in=in, load=mw1, out=out1);
    Register(in=in, load=mw2, out=out2);
    Register(in=in, load=mw3, out=out3);
    Register(in=in, load=mw4, out=out4);
    Register(in=in, load=mw5, out=out5);
    Register(in=in, load=mw6, out=out6);
    Register(in=in, load=mw7, out=out7);
    Register(in=in, load=mw8, out=out8);
    Mux8Way16(a=out1, b=out2, c=out3, d=out4, e=out5, f=out6, g=out7, h=out8, sel=address, out=out);
}
</pre>

### Ram64

<pre>
 /**
 * Memory of 64 registers, each 16 bit-wide. Out holds the value
 * stored at the memory location specified by address. If load==1, then 
 * the in value is loaded into the memory location specified by address 
 * (the loaded value will be emitted to out from the next time step onward).
 */

CHIP RAM64 {
    IN in[16], load, address[6];
    OUT out[16];

    PARTS:
    DMux8Way(in=load, sel=address[3..5], a=mw1, b=mw2, c=mw3, d=mw4, e=mw5, f=mw6, g=mw7, h=mw8);
    RAM8(in=in,  load=mw1, address=address[0..2], out=ram1);
    RAM8(in=in,  load=mw2, address=address[0..2], out=ram2);
    RAM8(in=in,  load=mw3, address=address[0..2], out=ram3);
    RAM8(in=in,  load=mw4, address=address[0..2], out=ram4);
    RAM8(in=in,  load=mw5, address=address[0..2], out=ram5);
    RAM8(in=in,  load=mw6, address=address[0..2], out=ram6);
    RAM8(in=in,  load=mw7, address=address[0..2], out=ram7);
    RAM8(in=in,  load=mw8, address=address[0..2], out=ram8);
    Mux8Way16(a=ram1, b=ram2, c=ram3, d=ram4, e=ram5, f=ram6, g=ram7, h=ram8, sel=address[3..5], out=out);
}
</pre>

### Ram512

### Ram4k

### Ram16k

### PC

<h2 align="center"> Referencias</h2>

[1] https://www.geeksforgeeks.org/half-adder-in-digital-logic/

[2] https://www.geeksforgeeks.org/full-adder-in-digital-logic/

[3] https://tomorrow0w0.gitbooks.io/nand2tetris-homework/content/chapter2/Add16.html

[4] https://www.researchgate.net/figure/a-One-bit-ALU-slice-and-b-its-QCA-implementation_fig4_257321470

[5]https://fullforms.com/DFF/D-Flip-Flop/21395#images

[6] https://www.javatpoint.com/what-is-alu

[7] https://www.nand2tetris.org/_files/ugd/44046b_862828b3a3464a809cda6f44d9ad2ec9.pdf
