<h1 align="center">Practica 5: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)


<p align="center"> <img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/lenguajemaquina.PNG" width="700" height="250"/></p>

<h2 align="center">Teniendo en cuenta el marco de estas dos prácticas que son las máquinas virtuales. ¿Cuál cree que es el futuro de las máquinas virtuales? </h2>

<h2 align="center">Tabla de contenidos:</h2>
<p align="center"> 
  <ol>
    <li>
      <a>Proyecto 7</a>
      <ul>
        <li><a href="#mult">Mult</a></li>
        <li><a href="#fill">Fill</a></li>
      </ul>
    </li>
    <li>
      <a>Proyecto 8</a>
      <ul>
        <li><a href="#memoria">Memoria</a></li>
        <li><a href="#cpu">CPU</a></li>
        <li><a href="#computador">Computador</a></li>
      </ul>
    </li>
  </ol> 
  </p>
  
<h2 align="center">Proyecto 7</h2>

### ...
```ruby
    input code
```
#### Proceso: 

Para el traductor VM se implementaran 9 comandos aritmeticos = ["add", "sub", "neg", "eq", "gt", lt", "and, "or", "not"], Tambien contiene los comandos de accesso a memoria que interactuan con la pila  ["push", "pop"] y 8 segmentos de memoria ["constant", "static", "local", "argument", "pointer", "temp", "this", "that"], donde constatnt no es un segmento de memoria de acceso directo, solo es el valor que puede enviar a la pila pero devuelve nada, Statics va de 16 a 255 en los segmentos, local es una variable local, el puntero es designado por el registro, argumento es la funcion a la cual dirige el puntero, temp valores temporales. 

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/pila.PNG" width="600" height="300" /></p> 



<h2 align="center">Proyecto 8</h2>

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica5/images/pila.PNG" width="600" height="300" /></p> 

### ...

<h2 align="center"> Referencias</h2>

[1] https://www.nand2tetris.org/course

[2] Nisan, N., & Schocken, S. (2021). The elements of computing systems: building a modern computer from first principles. MIT press.

[3] https://youtu.be/oDrLvsnxIwA?si=TdnhLKZvK7S4rTSx

[4] Building a CPU - From NAND To Tetris, Part 6 (https://youtu.be/kERT8zt61b8?si=iqBnIHcy2-_UJA12)

[5] CPU (https://courses.cs.washington.edu/courses/cse390b/21sp/readings/hack_cpu_chips.html)
