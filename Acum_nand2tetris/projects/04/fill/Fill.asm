// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

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