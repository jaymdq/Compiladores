.386
.model flat, stdcall
option casemap :none
include C:\Users\Ariel\workspace\Compiladores\masm32\include\windows.inc
include C:\Users\Ariel\workspace\Compiladores\masm32\include\kernel32.inc
include C:\Users\Ariel\workspace\Compiladores\masm32\include\user32.inc
includelib C:\Users\Ariel\workspace\Compiladores\masm32\lib\kernel32.lib
includelib C:\Users\Ariel\workspace\Compiladores\masm32\lib\user32.lib
.data
_a		DW ?
_c		DW ?
_b		DD ?
_cortos		DW 10 DUP ( 0 )
_largos		DD 10 DUP ( 0 )
_@0		db "Bien", 0
_@1		db "Mal che", 0
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
.code
start:
MOV AX, 8
MOV DX, 0
MOV BX, 3
IDIV BX
MOV _a, AX

MOV BX, 2
CMP BX, _a
JNE label0
INVOKE MessageBox, NULL, addr _@0, addr _@0, MB_OK
JMP label1
label0:
INVOKE MessageBox, NULL, addr _@1, addr _@1, MB_OK
label1:

INVOKE ExitProcess, 0
end start