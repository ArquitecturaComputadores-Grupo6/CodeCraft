<h1 align="center">Practica 6: </h1>

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
public void class main(String[] args){}
```
