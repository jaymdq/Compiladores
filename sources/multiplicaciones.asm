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
_cortitos	DW 10 DUP ( 0 )
_largos		DD 10 DUP ( 0 )
_@0		db "Terminar", 0
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
_@E3 		db "Overflow en producto (Fuera de Rango)!!!", 0

.code
start:

MOV BX, 1
MOVSX EBX, BX
CMP EBX, 1
JGE label0
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label0:
CMP EBX, 10
JLE label1
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label1:
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 4
MOV EBX, 3200
MOV [ _largos + EAX ], EBX

MOV BX, 1
MOVSX EBX, BX
CMP EBX, 1
JGE label2
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label2:
CMP EBX, 10
JLE label3
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label3:
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 4
MOV EAX, [ _largos + EAX ]
MOV EDX, 0
MOV EBX, 32000
MUL EBX
JNO label4
INVOKE MessageBox, NULL, addr _@E3, addr _@E3, MB_OK
INVOKE ExitProcess, 0
label4:
MOV _b, EAX

MOV EAX, 1
MOV EDX, 0
MOV EBX, 1
MUL EBX
JNO label5
INVOKE MessageBox, NULL, addr _@E3, addr _@E3, MB_OK
INVOKE ExitProcess, 0
label5:
MOV _b, EAX

MOV EAX, 1
MOV EDX, 0
MOV EBX, -1
MUL EBX
JNO label6
INVOKE MessageBox, NULL, addr _@E3, addr _@E3, MB_OK
INVOKE ExitProcess, 0
label6:
MOV _b, EAX

MOV EAX, -1
MOV EDX, 0
MOV EBX, -1
MUL EBX
JNO label7
INVOKE MessageBox, NULL, addr _@E3, addr _@E3, MB_OK
INVOKE ExitProcess, 0
label7:
MOV _b, EAX

INVOKE MessageBox, NULL, addr _@0, addr _@0, MB_OK

INVOKE ExitProcess, 0
end start