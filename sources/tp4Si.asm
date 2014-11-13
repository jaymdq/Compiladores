.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
_a		DW ?
_c		DW ?
_b		DD ?
_vec1		DD 10 DUP ( 0 )
_@1		db "Chan", 0
_@2		db "Fracaso", 0
_@0		db "Exito", 0
_@3		db "Termine", 0
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
.code
start:
MOV EBX, -1
MOV _b, EBX

MOV EBX, 1
SUB EBX, 2
MOV _b, EBX

MOV EBX, _b
ADD EBX, _b
ADD EBX, 8
MOV ECX, 1
CMP ECX, 1
JGE label0
INVOKE MessageBox, NULL, addr _@E1, addr _@E1, MB_OK
INVOKE ExitProcess, 0
label0:
CMP ECX, 10
JLE label1
INVOKE MessageBox, NULL, addr _@E2, addr _@E2, MB_OK
INVOKE ExitProcess, 0
label1:
SUB ECX, 1
MOV EAX, ECX
IMUL EAX, 4
MOV ECX, EAX
ADD EBX, [ _vec1 + ECX ]
MOV _b, EBX

MOV EBX, 2
MOV _c, BX

MOV EBX, _a
MOV ECX, 1
CMP EBX, ECX
JNE label2
MOV EBX, _c
MOV ECX, 3
CMP EBX, ECX
JNE label3
INVOKE MessageBox, NULL, addr _@0, addr _@0, MB_OK

label3:

label4:
MOV EBX, _a
ADD EBX, 1
MOV _a, BX

MOV EBX, _a
MOV ECX, 5
CMP EBX, ECX
JNE label4

MOV EBX, _a
MOV ECX, 5
CMP EBX, ECX
JNE label5
INVOKE MessageBox, NULL, addr _@1, addr _@1, MB_OK

label5:

JMP label6
label2:
INVOKE MessageBox, NULL, addr _@2, addr _@2, MB_OK

label6:

INVOKE MessageBox, NULL, addr _@3, addr _@3, MB_OK

end start