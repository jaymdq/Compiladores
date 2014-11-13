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
_largos		DD 10 DUP ( 0 )
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
.code
start:
MOV BX, 5
MOV _a, BX

MOV EBX, 8
MOV _b, EBX

MOV BX, _a
MOV _c, BX

MOV BX, 1
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 4
MOV EAX, [ _largos + EAX ]
MOV _b, EAX

MOV BX, _a
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 4
MOV EBX, _b
MOV [ _largos + EAX ], EBX

MOV BX, 7
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, _c
MOV [ _cortos + AX ], BX

MOV BX, _c
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 9000
MOV [ _cortos + AX ], BX

MOV BX, 2
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, _a
SUB BX, [ _cortos + AX ]
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, _c
SUB EBX, 1
MOV ECX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 3
ADD BX, 2
SUB EBX, 1
MOV EDX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV DX, [ _cortos + DX ]
ADD DX, [ _cortos + AX ]
MOV [ _cortos + CX ], DX

end start