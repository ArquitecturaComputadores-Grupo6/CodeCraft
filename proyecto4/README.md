<h1 align="center">Practica 3: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)


<h2 align="center">¿Por qué el lenguaje de máquina es importante para definir la arquitectura computacional? </h2>

### Mult

<p><img align="right" src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica3/images/mult.PNG" width="300" height="300" />
(Ejemplo de una tarea aritmética): Las entradas de este programa son los valores almacenados en R0 y R1 (RAM[0] y RAM[1]). El programa calcula el producto R0 * R1 y almacena el resultado en R2 (RAM[2]). Supon que R0 ≥ 0, R1 ≥ 0, y R0 * R1 < 32768 (tu programa no necesita verificar estas condiciones). El script Multi.test proporcionado y el archivo de comparación Mult.cmp están diseñados para probar tu programa en algunos valores representativos.</p> 

#### Proceso: 
Se almacena en la ram 2 el resultado en las iteraciones, y durante cada iteracion va sumando el valor de R0 y el numero de iteraciones será el valor de R1, y da como resultado la multiplicacion

<pre>

    
</pre>


<h2 align="center"> Referencias</h2>

[1] https://www.nand2tetris.org/course

[2] Nisan, N., & Schocken, S. (2021). The elements of computing systems: building a modern computer from first principles. MIT press.

[3] https://youtu.be/oDrLvsnxIwA?si=TdnhLKZvK7S4rTSx

[4] Building a CPU - From NAND To Tetris, Part 6 (https://youtu.be/kERT8zt61b8?si=iqBnIHcy2-_UJA12)
