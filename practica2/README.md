<h1 align="center">Practica 2: Boolean Arithmetic y Memoria</h1>

Descripcion

<p align="center"> Logo e imagen o gif de la interfaz principal de la herramienta</p>
<p align="center"><img src="https://www.webdevelopersnotes.com/wp-content/uploads/create-a-simple-home-page.png"/></p> 

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
        <li><a href="#alu-nostat">ALU-nostat</a></li>
        <li><a href="#alu">ALU</a></li>
      </ul>
    </li>
    <li>
      <a>Proyecto 3</a>
      <ul>
        <li><a href="#built-with">HalfAdder</a></li>
        <li><a href="#usage">FullAdder</a></li>
        <li><a href="#roadmap">Add16</a></li>
        <li><a href="#contributing">Inc16</a></li>
        <li><a href="#license">ALU-nostat</a></li>
        <li><a href="#contact">ALU</a></li>
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
<img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/fulladdr.PNG" width="400" height="250" /></p> 

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

### Add[16]
Los chips de memoria y registro representan números enteros mediante patrones de n bits, n es 16, 32, 64, etc., según la plataforma informática. El chip cuyo trabajo es sumar dichos números se llama sumador multibit, o simplemente sumador. un sumador de 16 bits, teniendo en cuenta que la misma lógica y especificaciones se amplían como está a cualquier sumador de n bits.
<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica2/images/adder16.png" width="400" height="250" /></p> 
#### Nota: 
Para la realizacion del .hdl se emplearon 16 FullAdder en los cuales 
<pre>
	/**
 * Adds two 16-bit values.
 * The most significant carry bit is ignored.
 */

CHIP Add16 {
    IN a[16], b[16];
    OUT out[16];

    PARTS:
    FullAdder(a=a[0], b=b[0], c=false, sum=out[0], carry=carry1);
    FullAdder(a=a[1], b=b[1], c=carry1, sum=out[1], carry=carry2);
    FullAdder(a=a[2], b=b[2], c=carry2, sum=out[2], carry=carry3);
    FullAdder(a=a[3], b=b[3], c=carry3, sum=out[3], carry=carry4);
    FullAdder(a=a[4], b=b[4], c=carry4, sum=out[4], carry=carry5);
    FullAdder(a=a[5], b=b[5], c=carry5, sum=out[5], carry=carry6);
    FullAdder(a=a[6], b=b[6], c=carry6, sum=out[6], carry=carry7);
    FullAdder(a=a[7], b=b[7], c=carry7, sum=out[7], carry=carry8);
    FullAdder(a=a[8], b=b[8], c=carry8, sum=out[8], carry=carry9);
    FullAdder(a=a[9], b=b[9], c=carry9, sum=out[9], carry=carry10);
    FullAdder(a=a[10], b=b[10], c=carry10, sum=out[10], carry=carry11);
    FullAdder(a=a[11], b=b[11], c=carry11, sum=out[11], carry=carry12);
    FullAdder(a=a[12], b=b[12], c=carry12, sum=out[12], carry=carry13);
    FullAdder(a=a[13], b=b[13], c=carry13, sum=out[13], carry=carry14);
    FullAdder(a=a[14], b=b[14], c=carry14, sum=out[14], carry=carry15);
    FullAdder(a=a[15], b=b[15], c=carry15, sum=out[15], carry=null);

}
</pre>
<h2 align="center"> Referencias</h2>

[1] https://www.geeksforgeeks.org/half-adder-in-digital-logic/

[2] https://www.geeksforgeeks.org/full-adder-in-digital-logic/

[3]
