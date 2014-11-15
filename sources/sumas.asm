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
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0

.code
start:

MOV BX, 5
ADD BX, -60
MOV _a, BX

MOV EBX, _b
ADD EBX, _b
ADD EBX, 8
MOV _b, EBX

MOV BX, _a
ADD BX, _a
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
MOV EBX, _b
ADD EBX, 5
MOV [ _largos + EAX ], EBX

MOV BX, 7
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
IMUL EAX, 2
MOV BX, -2
ADD BX, _c
MOV [ _cortos + AX ], BX

MOV BX, _a
ADD BX, 1
MOVSX EBX, BX
CMP EBX, 1
JGE label4
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label4:
CMP EBX, 10
JLE label5
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label5:
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV AX, [ _cortitos + AX ]
ADD AX, 4
MOVSX EAX, AX
CMP EAX, 1
JGE label6
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label6:
CMP EAX, 10
JLE label7
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label7:
SUB EAX, 1
IMUL EAX, 2
MOV BX, _c
ADD BX, 0
MOV [ _cortos + AX ], BX


INVOKE ExitProcess, 0
end start