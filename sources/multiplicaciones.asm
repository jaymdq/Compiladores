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
MOV BX, 2
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
IMUL EAX, 2
MOV AX, [ _cortos + AX ]
MOV DX, 0
MOV BX, 5
CWD
IMUL BX
MOV _a, AX

MOV BX, 9
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
MOV EBX, EAX
MOV AX, 8
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
MOV _a, AX

MOV BX, 4
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
MOV EBX, EAX
MOV AX, _a
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
MOV _a, AX

MOV BX, 11
MOVSX EBX, BX
CMP EBX, 1
JGE label6
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label6:
CMP EBX, 10
JLE label7
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label7:
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 12
MOVSX EBX, BX
CMP EBX, 1
JGE label8
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label8:
CMP EBX, 10
JLE label9
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label9:
SUB EBX, 1
MOV ECX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV CX, [ _cortos + CX ]
MOV EBX, EAX
MOV AX, CX
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
MOV _a, AX


INVOKE ExitProcess, 0
end start