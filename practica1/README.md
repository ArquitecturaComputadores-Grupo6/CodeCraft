# codecrafteam
## NAND2TETRIS

En la ejecución de la primera práctica, se optó por la herramienta nand2tetris. En un principio, se llevó a cabo una exploración minuciosa de su sitio web y de los recursos disponibles. Se dedicó tiempo a reflexionar acerca de la valiosa utilidad y el material educativo que esta plataforma ofrecía. Esto permitió comprender cómo nand2tetris se convierte en un recurso enriquecedor para adquirir conocimientos adicionales en el ámbito de la arquitectura de computadoras.

## Compuerta Not 


La compuerta NOT se crea a partir de una Compuerta NAND con una única entrada, pero debido a la solicitud del programa de dos entradas, se suministra la misma entrada dos veces, generando así la tabla de verdad que es equivalente a la de la compuerta NOT.

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/not.png)

<pre>
    CHIP Not {
    IN in;
    OUT out;

    PARTS:
    Nand(a=in, b=in, out=out);
    }
</pre>


## Compuerta AND 

La compuerta AND se construye mediante el uso de dos compuertas NAND. La primera genera la función AND inversa, mientras que la segunda funciona como un inversor para obtener la salida de AND en su forma normal. La segunda compuerta NAND puede ser reemplazada por una compuerta NOT sin que la tabla de verdad resultante experimente cambios.

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/and.png)

<pre>
CHIP And {
    IN a, b;
    OUT out;

    PARTS:
    Nand(a=a, b=b, out=nad1);
    Nand(a=nad1, b=nad1, out=out);
}
</pre>


## Compuerta OR

La compuerta OR se obtiene mediante una compuerta NAND que toma como entradas las salidas de un inversor o de una compuerta NAND individual que posee entradas individuales.

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/or.png)

<pre>
CHIP Or {
    IN a, b;
    OUT out;

    PARTS:
    Nand(a=a, b=a, out=nad1);
    Nand(a=b, b=b, out=nad2);
    Nand(a=nad1, b=nad2, out=out);
}
</pre>

## Compuerta XOR


La compuerta XOR, también conocida como “OR exclusiva”, se le denomina la compuerta de “algunos pero no todos”, su expresión Booleana es una suma binaria de un dígito cada uno y el resultado obtenido será la salida. La salida tiene un estado activo “1” al tener las entradas en estados diferentes

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/xor1.png)

<pre>
CHIP Xor {
    IN a, b;
    OUT out;

    PARTS:
    	Nand(a=a,b=b,out=nad1);
    	Nand(a=a,b=nad1,out=nad2);
   	Nand(a=nad1,b=b,out=nad3);
	Nand(a=nad2,b=nad3,out=out);
}
</pre>

## Compuerta MUX

Un multiplexor o también conocido como MUX o MPX es un dispositivo electrónico que sirve para convertir datos de diferentes señales en una sola salida.


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/mux.png)

<pre>
CHIP Mux {
    IN a, b, sel;
    OUT out;

    PARTS:
    	Not(in=sel,out=nsel);
    	And(a=a,b=nsel,out=c);
	And(a=b,b=sel,out=d);
	Or(a=c,b=d,out=out);
}

</pre>

## Compuerta DMUX

Un demultiplexor o también conocido como DMUX es un dispositivo electrónico que sirve para direccionar una sola señal de entrada hacia una de varias salidas posibles.


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/dmux.png)

<pre>
CHIP DMux {
    IN in, sel;
    OUT a, b;

    PARTS:
    Not(in=sel, out=not1);
    And(a=not1, b=in, out=a);
    And(a=sel, b=in, out=b);
}

</pre>

## Compuerta NOT16

La compuerta NOT16 está conformada por 16 entradas y 16 salidas de la compuerta Not 

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/not16.jpeg)

<pre>
CHIP Not16 {
    IN in[16];
    OUT out[16];

    PARTS:
    Not(in=in[0],out=out[0]);
    Not(in=in[1],out=out[1]);
    Not(in=in[2],out=out[2]);
    Not(in=in[3],out=out[3]);
    Not(in=in[4],out=out[4]);
    Not(in=in[5],out=out[5]);
    Not(in=in[6],out=out[6]);
    Not(in=in[7],out=out[7]);
    Not(in=in[8],out=out[8]);
    Not(in=in[9],out=out[9]);
    Not(in=in[10],out=out[10]);
    Not(in=in[11],out=out[11]);
    Not(in=in[12],out=out[12]);
    Not(in=in[13],out=out[13]);
    Not(in=in[14],out=out[14]);
    Not(in=in[15],out=out[15]);
}
</pre>

## Compuerta AND16

Esta compuerta se puede describir como una lista de 16 salidas, por ende tiene 32 entradas en total

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/and16.png)

<pre>
CHIP And16 {
    IN a[16], b[16];
    OUT out[16];

    PARTS:
	
    Nand(a=a[0], b=b[0], out=nad0);
    Nand(a=nad0, b=nad0, out=out[0]);
    Nand(a=a[1], b=b[1], out=nad1);
    Nand(a=nad1, b=nad1, out=out[1]);
    Nand(a=a[2], b=b[2], out=nad2);
    Nand(a=nad2, b=nad2, out=out[2]);
    Nand(a=a[3], b=b[3], out=nad3);
    Nand(a=nad3, b=nad3, out=out[3]);
    Nand(a=a[4], b=b[4], out=nad4);
    Nand(a=nad4, b=nad4, out=out[4]);
    Nand(a=a[5], b=b[5], out=nad5);
    Nand(a=nad5, b=nad5, out=out[5]);
    Nand(a=a[6], b=b[6], out=nad6);
    Nand(a=nad6, b=nad6, out=out[6]);
    Nand(a=a[7], b=b[7], out=nad7);
    Nand(a=nad7, b=nad7, out=out[7]);
    Nand(a=a[8], b=b[8], out=nad8);
    Nand(a=nad8, b=nad8, out=out[8]);
    Nand(a=a[9], b=b[9], out=nad9);
    Nand(a=nad9, b=nad9, out=out[9]);
    Nand(a=a[10], b=b[10], out=nad10);
    Nand(a=nad10, b=nad10, out=out[10]);
    Nand(a=a[11], b=b[11], out=nad11);
    Nand(a=nad11, b=nad11, out=out[11]);
    Nand(a=a[12], b=b[12], out=nad12);
    Nand(a=nad12, b=nad12, out=out[12]);
    Nand(a=a[13], b=b[13], out=nad13);
    Nand(a=nad13, b=nad13, out=out[13]);
    Nand(a=a[14], b=b[14], out=nad14);
    Nand(a=nad14, b=nad14, out=out[14]);
    Nand(a=a[15], b=b[15], out=nad15);
    Nand(a=nad15, b=nad15, out=out[15]);
}
</pre>

## Compuerta Or16

Para esta compuerta se realiza una operación OR bit a bit en dos entradas de 16 bits, a y b. La salida out consta de 16 bits, donde cada bit de salida out[i] se calcula como el resultado de aplicar una operación OR lógica al bit correspondiente en a[i] y b[i]. Se implementa utilizando 16 compuertas lógicas OR individuales para obtener la salida deseada.


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/or16.png)

<pre>
CHIP Or16 {
    IN a[16], b[16];
    OUT out[16];

    PARTS:
    
    Or(a=a[0], b=b[0], out=out[0]);
    Or(a=a[1], b=b[1], out=out[1]);
    Or(a=a[2], b=b[2], out=out[2]);
    Or(a=a[3], b=b[3], out=out[3]);
    Or(a=a[4], b=b[4], out=out[4]);
    Or(a=a[5], b=b[5], out=out[5]);
    Or(a=a[6], b=b[6], out=out[6]);
    Or(a=a[7], b=b[7], out=out[7]);
    Or(a=a[8], b=b[8], out=out[8]);
    Or(a=a[9], b=b[9], out=out[9]);
    Or(a=a[10], b=b[10], out=out[10]);
    Or(a=a[11], b=b[11], out=out[11]);
    Or(a=a[12], b=b[12], out=out[12]);
    Or(a=a[13], b=b[13], out=out[13]);
    Or(a=a[14], b=b[14], out=out[14]);
    Or(a=a[15], b=b[15], out=out[15]);
}

</pre>


## Compuerta MUX16

Usando la Compuerta Mux antes realizada se genera 16 compuertas MUX

![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/mux16.png)

<pre>
CHIP Mux16 {
    IN a[16], b[16], sel;
    OUT out[16];

    PARTS:
    Mux(a=a[0],b=b[0],sel=sel,out=out[0]);
    Mux(a=a[1],b=b[1],sel=sel,out=out[1]);
    Mux(a=a[2],b=b[2],sel=sel,out=out[2]);
    Mux(a=a[3],b=b[3],sel=sel,out=out[3]);
    Mux(a=a[4],b=b[4],sel=sel,out=out[4]);
    Mux(a=a[5],b=b[5],sel=sel,out=out[5]);
    Mux(a=a[6],b=b[6],sel=sel,out=out[6]);
    Mux(a=a[7],b=b[7],sel=sel,out=out[7]);
    Mux(a=a[8],b=b[8],sel=sel,out=out[8]);
    Mux(a=a[9],b=b[9],sel=sel,out=out[9]);
    Mux(a=a[10],b=b[10],sel=sel,out=out[10]);
    Mux(a=a[11],b=b[11],sel=sel,out=out[11]);
    Mux(a=a[12],b=b[12],sel=sel,out=out[12]);
    Mux(a=a[13],b=b[13],sel=sel,out=out[13]);
    Mux(a=a[14],b=b[14],sel=sel,out=out[14]);
    Mux(a=a[15],b=b[15],sel=sel,out=out[15]);
}

</pre>



## Compuerta OR8WAY

Para esta compuerta se tiene 8 entradas y una salida, la salida es verdadera si al menos una de las 8 entradas es verdadera, se puede construir con la compuerta lógica OR.


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/or8way.png)

<pre>
CHIP Or8Way {
    IN in[8];
    OUT out;

    PARTS:
    Or(a=in[0],b=in[1],out=or1);
    Or(a=or1,b=in[2],out=or2);
    Or(a=or2,b=in[3],out=or3);
    Or(a=or3,b=in[4],out=or4);
    Or(a=or4,b=in[5],out=or5);
    Or(a=or5,b=in[6],out=or6);
    Or(a=or6,b=in[7],out=out);
}

</pre>

## Compuerta MUX4WAY16

Para esta compuerta se tiene 16 entradas y 2 entradas de selección, la salida es verdadera si la entrada de selección es verdadera, se puede construir con las compuertas lógicas and, not y or, pero por flexibilidad se emplea la antes vista MUX16


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/mux8way16.png)

<pre>
CHIP Mux4Way16 {
    IN a[16], b[16], c[16], d[16], sel[2];
    OUT out[16];

    PARTS:
    Mux16(a=a,b=b,sel=sel[0],out=mux1); 
    Mux16(a=c,b=d,sel=sel[0],out=mux2);
    Mux16(a=mux1,b=mux2,sel=sel[1],out=out);
}

</pre>

## Compuerta MUX8WAY16

Para esta compuerta se tiene 16 entradas y 3 entradas, la salida es verdadera si la entrada de selección es verdadera, se puede construir utilizando las compuertas lógicas and, not y or, pero por flexibilidad se emplea la antes vista mux16 Y mux4way16


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/mux8way16.png)

<pre>
CHIP Mux8Way16 {
    IN a[16], b[16], c[16], d[16],
       e[16], f[16], g[16], h[16],
       sel[3];
    OUT out[16];

    PARTS:
    Mux4Way16(a=a, b=b, c=c, d=d, sel=sel[0..1], out=mux1);
    Mux4Way16(a=e, b=f, c=g, d=h, sel=sel[0..1], out=mux2);
    Mux16(a=mux1, b=mux2, sel=sel[2], out=out);
}

</pre>


## Compuerta DMUX4WAY

Este dispositivo lógico es un demultiplexor (DMux) de 4 vías que toma una entrada (in) y dos señales de selección (sel[0] y sel[1]) y dirige la entrada a una de las cuatro salidas posibles (a, b, c ó d) según el valor de las señales de selección.


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/dmux4way.png)

<pre>
CHIP DMux4Way {
    IN in, sel[2];
    OUT a, b, c, d;

    PARTS:
    DMux(in=in,sel=sel[1],a=dmux1,b=dmux2);
    DMux(in=dmux1,sel=sel[0],a=a,b=b);
    DMux(in=dmux2,sel=sel[0],a=c,b=d);
}

</pre>

## Compuerta DMUX8WAY

Este dispositivo ‘demultiplexa’ una única entrada en ocho salidas posibles (a, b, c, d, e, f, g o h) utilizando tres señales de selección. Esto se obtiene con dos etapas: primero, con un DMux que divide en dos caminos, y luego con dos DMux4Way que dividen cada uno de esos caminos en cuatro salidas finales según los valores de las señales de selección.


![Image text](https://github.com/Arquitectura-Grupo-6/codecrafteam/blob/main/lab1/images/dmux8way.png)

<pre>
CHIP DMux8Way {
    IN in, sel[3];
    OUT a, b, c, d, e, f, g, h;

    PARTS:
    DMux(in=in, sel=sel[2], a=dmux1, b=dmux2);
    DMux4Way(in=dmux1, sel[0]=sel[0], sel[1]=sel[1], a=a, b=b, c=c, d=d);
    DMux4Way(in=dmux2, sel[0]=sel[0], sel[1]=sel[1], a=e, b=f, c=g, d=h);
}

</pre>

## Referencias

Mano, M. M. (1982). Lógica digital y diseño de computadores. México: Prentice-Hall Hispanoamericana. pag 137. Tomado de https://www.google.com.co/books/edition/L%C3%B3gica_digital_y_dise%C3%B1o_de_computadore/Jadk9JigJs4C?hl=es-419&gbpv=1

https://wilaebaelectronica.blogspot.com/2019/10/compuertas-logicas.html

https://www.mecatronicalatam.com/es/tutoriales/electronica/compuertas-logicas/compuerta-xor/

https://d1lvwzdke54ywg.cloudfront.net/computersystem-1/
