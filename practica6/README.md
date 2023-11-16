<h1 align="center">Practica 6: </h1>

Make everything as simple as possible, but not simpler.
—Albert Einstein (1879–1955)

El Proyecto 9 de "Nand to Tetris" se centra en la creación de un compilador de alto nivel para un lenguaje de programación simple llamado Jack. El objetivo principal es diseñar un programa que traduzca el código escrito en Jack a código de máquina entendible por la plataforma Hack Assembly Language, que es el lenguaje de bajo nivel específico de la arquitectura Jack. El proceso de compilación completo implica una serie de pasos que van desde la traducción del código Jack hasta la generación de código de máquina ejecutable. Se trabajo en cada etapa del compilador, implementando algoritmos y estructuras de datos adecuadas para lograr la transformación correcta y eficiente del código. Al finalizar el Proyecto 9, se tiene un compilador funcional que puede convertir código escrito en Jack a código de máquina Hack Assembly Language, lo que brinda una comprensión más profunda de los conceptos detrás de la compilación y el funcionamiento interno de los lenguajes de programación.

<h2 align="center">Desarrolle más el concepto de lenguaje de alto nivel, teniendo en cuenta la diferencia entre lenguajes de programación propiamente dichos e interpretadores.</h2>

Un lenguaje de alto nivel es aquel que está diseñado para ser más comprensible y cercano al lenguaje humano, facilitando la escritura de programas complejos con estructuras y abstracciones más cercanas a la forma en que los humanos piensan y razonan. Estos lenguajes se caracterizan por utilizar un nivel de abstracción más alejado del hardware subyacente, lo que permite a los programadores concentrarse en la lógica del programa en lugar de en los detalles de bajo nivel de la máquina.

<h2 align="center">¿Qué se debe considerar para proponer un nuevo y buen lenguaje de programación, teniendo en cuenta la arquitectura de computador completa?</h2>

Proponer un nuevo lenguaje de programación es un desafío complejo que requiere considerar varios aspectos, especialmente teniendo en cuenta la arquitectura completa de la computadora. Al considerar todos estos aspectos, los diseñadores de lenguajes pueden intentar crear un nuevo lenguaje de programación que se adapte bien a la arquitectura de computadora completa y sea útil, eficiente y fácil de usar para los programadores.

<h2 align="center">Proyecto 9</h2>


<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica6/images/pila.PNG" width="600" height="300" /></p> 



<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica6/images/vmtrad.PNG" width="800" height="300" /></p> 

<p align="center"><img src="https://arquitecturacomputadores-grupo6.github.io/CodeCraft/practica6/images/diagrama.PNG" width="2000" height="800" /></p> 

```ruby
class Ball {
    field int x, y, xSpeed, ySpeed;

    constructor Ball new(int startX, int startY, int startXSpeed, int startYSpeed) {
        let x = startX;
        let y = startY;
        let xSpeed = startXSpeed;
        let ySpeed = startYSpeed;
        return this;
    }

    method void setDestination(int destX, int destY) {
        let xSpeed = (destX - x) / 10;
        let ySpeed = (destY - y) / 10;
    }

    method int getLeft() {
        return x;
    }

    method int getRight() {
        return x + 10;  // Assuming the ball has a width of 10
    }

    method void move() {
        let x = x + xSpeed;
        let y = y + ySpeed;

        // Logic for bouncing off walls
        if (x < 0 || x > 511) {
            let xSpeed = -xSpeed;  // Reverse direction on hitting left or right wall
        }

        if (y < 0 || y > 239) {
            let ySpeed = -ySpeed;  // Reverse direction on hitting top or bottom wall
        }
    }

    method void bounce(int direction) {
        // Logic for bouncing off bat
        // For simplicity, assuming direction is -1 for left and 1 for right
        let xSpeed = direction * Math.abs(xSpeed);
        let ySpeed = -ySpeed;  // Reverse the ySpeed

        // Adjust x and y coordinates based on direction
        let x = x + xSpeed;
        let y = y + ySpeed;
    }

}
```

```ruby
lass PongGame {
    static PongGame instance;
    field Bat bat;
    field Ball ball;
    field boolean exit;
    field int score;
    field int batWidth;

    constructor PongGame new() {
        let batWidth = 50;
        let bat = Bat.new(230, 229, batWidth, 7);
        let ball = Ball.new(253, 222, 0, 511, 0, 229);
        do ball.setDestination(400, 0);

        let exit = false;
        let score = 0;

        return this;
    }

    method void dispose() {
        do bat.dispose();
        do ball.dispose();
        do Memory.deAlloc(this);
        return;
    }

    function void newInstance() {
        let instance = PongGame.new();
        return;
    }

    function PongGame getInstance() {
        return instance;
    }

    method void run() {
        var char key;

        while (~exit) {
            let key = Keyboard.keyPressed();

            if (key = 130) {
                do bat.setDirection(1);
            } else {
                if (key = 132) {
                    do bat.setDirection(2);
                } else {
                    if (key = 140) {
                        let exit = true;
                    }
                }
            }

            do bat.move();
            do moveBall();

            do Sys.wait(16);  // Adjust game speed
        }

        if (exit) {
            do Output.moveCursor(10, 27);
            do Output.printString("Game Over");
        }

        return;
    }

    method void moveBall() {
        var int bouncingDirection, batLeft, batRight, ballLeft, ballRight;

        let wall = ball.move();

        if ((wall > 0) & (wall != lastWall)) {
            let lastWall = wall;
            let bouncingDirection = 0;
            let batLeft = bat.getLeft();
            let batRight = bat.getRight();
            let ballLeft = ball.getLeft();
            let ballRight = ball.getRight();

            if (wall = 4) {
                let exit = (batLeft > ballRight) | (batRight < ballLeft);
                if (~exit) {
                    if (ballRight < (batLeft + 10)) {
                        let bouncingDirection = -1;
                    } else {
                        if (ballLeft > (batRight - 10)) {
                            let bouncingDirection = 1;
                        }
                    }

                    let batWidth = batWidth - 2;
                    do bat.setWidth(batWidth);
                    let score = score + 1;
                    do Output.moveCursor(22, 7);
                    do Output.printInt(score);
                }
            }

            do ball.bounce(bouncingDirection);
        }

        return;
    }
}
```
```ruby
class Main {
    function void main() {
        var PongGame game;
        let game = PongGame.new();
        do game.run();
        return;
    }
}
```
