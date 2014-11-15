.386
.model flat, stdcall
option casemap :none
include D:\Proyectos\Compiladores\masm32\include\windows.inc
include D:\Proyectos\Compiladores\masm32\include\kernel32.inc
include D:\Proyectos\Compiladores\masm32\include\user32.inc
includelib D:\Proyectos\Compiladores\masm32\lib\kernel32.lib
includelib D:\Proyectos\Compiladores\masm32\lib\user32.lib
.data
_cortos		DW 10 DUP ( 0 )
_@0		db "bien pibe, bien", 0
_@1		db "mal rocha, mal", 0
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
_@E3 		db "Overflow en producto (Fuera de Rango)!!!", 0
@aux0		DD 0

.code
start:

MOV BX, 1
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 1
MOV [ _cortos + AX ], BX

MOV BX, 2
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 1
MOV [ _cortos + AX ], BX

MOV BX, 4
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 1
MOV [ _cortos + AX ], BX

MOV BX, 3
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 2
SUB EBX, 1
MOV ECX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 1
SUB EBX, 1
MOV EDX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV DX, [ _cortos + DX ]
MOV EBX, EAX
MOV AX, DX
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
MOV BX, 4
SUB EBX, 1
MOV EDX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV EBX, EAX
MOV AX, DX
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
MOV BX, 2
SUB EBX, 1
MOV EDX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 1
SUB EBX, 1
MOV @aux0, ECX
MOV ECX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV CX, [ _cortos + CX ]
MOV EBX, EAX
MOV AX, CX
MOV ECX, EDX
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
ADD CX, AX
MOV BX, 4
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 1
SUB EBX, 1
MOV EDX, EAX
MOV EAX, EBX
IMUL EAX, 2
MOV DX, [ _cortos + DX ]
MOV EBX, EAX
MOV AX, DX
MOV DX, 0
CWD
IMUL [ _cortos + BX ]
ADD CX, AX
MOV EBX, @aux0
MOV [ _cortos + BX ], CX

MOV BX, 3
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV AX, [ _cortos + AX ]
CMP AX, 3
JNE label0
INVOKE MessageBox, NULL, addr _@0, addr _@0, MB_OK
JMP label1
label0:
INVOKE MessageBox, NULL, addr _@1, addr _@1, MB_OK
label1:

INVOKE ExitProcess, 0
end start