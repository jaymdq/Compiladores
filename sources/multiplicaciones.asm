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
_cortos		DW 10 DUP ( 0 )
_cortitos	DW 10 DUP ( 0 )
_largos		DD 10 DUP ( 0 )
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
.code
start:
MOV BX, 2
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV AX, [ _cortos + AX ]
MOV DX, 0
IMUL AX, 5
MOV _a, AX

end start