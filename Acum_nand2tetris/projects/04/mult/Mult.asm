// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

(BEGIN)
	 //El valor de la posicion 2 de la ram lo establecemos como 0
	@R2
	M=0
(LOOP)
	// Condicion de salida del ciclo, si R1 <= 0, termina el ciclo
	@R1
	D=M
	@END
	D;JLE
	// Se suma el valor de R0 a R2
	@R0
	D=M
	@R2
	M=M+D
	// Decrecemos el valor de R1
	@R1
	M=M-1
	//Se indica que se vuelve a iniciar el ciclo
	@LOOP
	0;JMP
(END)
	// Termina la ejecucion
	@END
	0;JMP
  