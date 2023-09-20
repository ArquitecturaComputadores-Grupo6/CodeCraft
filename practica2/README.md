<h1 align="center">Practica 2: Boolean Arithmetic y Memoria</h1>

Descripcion

<p align="center"> Logo e imagen o gif de la interfaz principal de la herramienta</p>
<p align="center"><img src="https://www.webdevelopersnotes.com/wp-content/uploads/create-a-simple-home-page.png"/></p> 

## Tabla de contenidos:
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

## Proyecto 2
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
## Referencias
[1] https://www.geeksforgeeks.org/half-adder-in-digital-logic/
[2] https://www.geeksforgeeks.org/full-adder-in-digital-logic/
[3]
