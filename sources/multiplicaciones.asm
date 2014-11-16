.386
.model flat, stdcall
option casemap :none
include C:\Users\Ariel\Desktop\Export\masm32\include\windows.inc
include C:\Users\Ariel\Desktop\Export\masm32\include\kernel32.inc
include C:\Users\Ariel\Desktop\Export\masm32\include\user32.inc
includelib C:\Users\Ariel\Desktop\Export\masm32\lib\kernel32.lib
includelib C:\Users\Ariel\Desktop\Export\masm32\lib\user32.lib
.data
_a		DW ?
_c		DW ?
_b		DD ?
_cortos		DW 10 DUP ( 0 )
_cortitos	DW 10 DUP ( 0 )
_largos		DD 10 DUP ( 0 )
_@1		db "Excelente", 0
_@0		db "GENIO CAPO", 0
_@E1 		db "Índice del arreglo es menor al límite inferior!!!", 0
_@E2 		db "Índice del arreglo es mayor al límite superior!!!", 0
_@E3 		db "Overflow en producto (Fuera de Rango)!!!", 0
@aux0		DD 0

.code
start:

PUSH EAX ; Se guarda el contexto 
PUSH EBX ; Se guarda el contexto 
PUSH ECX ; Se guarda el contexto 
PUSH EDX ; Se guarda el contexto 
MOV EAX, 0 ; Inicialización 
MOV EBX, 0 ; Inicialización 
MOV ECX, 0 ; Inicialización 
MOV EDX, 0 ; Inicialización 

MOV BX, 2
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 2
MOV [ _cortos + AX ], BX

MOV BX, 1
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 2
MOV [ _cortos + AX ], BX

MOV BX, 4
SUB EBX, 1
MOV EAX, EBX
IMUL EAX, 2
MOV BX, 4
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
CMP AX, 28
JNE label0
INVOKE MessageBox, NULL, addr _@0, addr _@0, MB_OK
label0:
MOV AX, 1
MOV DX, 0
MOV BX, 2
CWD
IMUL BX
MOV DX, 0
MOV BX, 2
CWD
IMUL BX
MOV DX, 0
MOV BX, 1
CWD
IMUL BX
MOV EBX, EAX
MOV AX, 1
MOV DX, 0
MOV CX, 3
CWD
IMUL CX
MOV DX, 0
MOV CX, 3
CWD
IMUL CX
MOV DX, 0
MOV CX, 1
CWD
IMUL CX
ADD BX, AX
MOV AX, 2
MOV DX, 0
MOV CX, 4
CWD
IMUL CX
MOV DX, 0
MOV CX, 4
CWD
IMUL CX
MOV DX, 0
MOV CX, 3
CWD
IMUL CX
ADD BX, AX
MOV _a, BX

MOV BX, _a
CMP BX, 109
JNE label1
INVOKE MessageBox, NULL, addr _@1, addr _@1, MB_OK
label1:

POP EDX ; Se restaura el contexto 
POP ECX ; Se restaura el contexto 
POP EBX ; Se restaura el contexto 
POP EAX ; Se restaura el contexto 
INVOKE ExitProcess, 0
end start