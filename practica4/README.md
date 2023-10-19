<h1 align="center">Practica 4: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)

Como hemos visto en los anteriores proyectos, la cpu solo "entiende" lenguaje de maquina osea bits (1 y 0) por lo cual se dificulata el manejo a la hora de trabajar con los dispositivos es por esto que surge el lenguaje ensamblador como una solucion al problema de manejar el complicado y extenso lenguaje maquina, el cual cumple la funcion de traductor de un lenguaje de alto nivel a codigo maquina, hay que entender tambien que hay deferentes reglas a seguir dependiento del ensamblador y las funciones que se quieran desempeñar.
 
Los programadores rara vez escriben programas directamente en lenguaje de máquina. En cambio, los programadores que desarrollan programas de alto rendimiento (por ejemplo, software de sistema, aplicaciones críticas y software para sistemas integrados) a menudo inspeccionan el código de ensamblaje generado por los compiladores. Lo hacen para comprender cómo su código de alto nivel se implementa realmente en el hardware y cómo ese código puede optimizarse para obtener un mejor rendimiento. Uno de los actores en este proceso es el programa que traduce el código escrito en un lenguaje simbólico de máquina en código escrito en lenguaje binario de máquina. Este programa se llama ensamblador.

<img align="center" src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica4/images/ensamblador.PNG" width="300" height="300" />
<h2 align="center">Teniendo en cuenta las características del ensamblador, ¿Cuál es la principal limitante que observan? Justifique su respuesta. </h2>


### Add.asm: 
Suma las constantes 2 y 3 y coloca el resultado en R0.

### Max.asm: 
Calcula max(R0, R1) y coloca el resultado en R2.

### Rect.asm: 
Dibuja un rectángulo en la esquina superior izquierda de la pantalla. El rectángulo tiene 16 píxeles de ancho y R0 píxeles de alto. Antes de ejecutar este programa, coloca un valor no negativo en R0.

### Pong.asm: 
Un clásico juego arcade para un solo jugador. Una pelota rebota repetidamente en las "paredes" de la pantalla. El jugador intenta golpear la pelota con una paleta, presionando las teclas de flecha izquierda y derecha. Por cada golpe exitoso, el jugador gana un punto y la paleta se reduce un poco, para hacer el juego más desafiante. Si el jugador no golpea la pelota, el juego termina. Para salir del juego, presiona ESC. Nota: El programa Pong se desarrolló utilizando herramientas presentadas en la Parte II del curso y en el libro. En particular, el software del juego se escribió en el lenguaje Jack de alto nivel y se tradujo al archivo Pong.asm dado por el compilador Jack. Aunque el programa Pong de alto nivel tiene solo unas 300 líneas de código, la aplicación Pong ejecutable tiene alrededor de 20,000 líneas de código binario, la mayoría de las cuales es el sistema operativo Jack. Antes de ejecutar el código, selecciona 'No animation' en el menú 'Animation' (es decir, sin resaltado de código). Puedes controlar la velocidad de ejecución del código usando el control deslizante de velocidad. El juego comenzará después de unos segundos, durante los cuales el sistema operativo se inicializa.


<p><img align="right" src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica4/images/mult.PNG" width="300" height="300" />

<h2 align="center"> Referencias</h2>

[1] https://www.nand2tetris.org/course

[2] Nisan, N., & Schocken, S. (2021). The elements of computing systems: building a modern computer from first principles. MIT press.

[3] https://youtu.be/oDrLvsnxIwA?si=TdnhLKZvK7S4rTSx

[4] Building a CPU - From NAND To Tetris, Part 6 (https://youtu.be/kERT8zt61b8?si=iqBnIHcy2-_UJA12)
