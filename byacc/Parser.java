//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package sintactico;



//#line 2 "gramatica.y"

/* Imports necesarios en la clase Parser */
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;
import proyecto.ElementoTS;
import java.util.Vector;
import arbol.sintactico.*;

//#line 28 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENTIFICADOR=257;
public final static short ENTERO=258;
public final static short ENTERO_LSS=259;
public final static short CADENA_MULTILINEA=260;
public final static short PR_SI=261;
public final static short PR_ENTONCES=262;
public final static short PR_SINO=263;
public final static short PR_IMPRIMIR=264;
public final static short PR_ENTERO=265;
public final static short PR_ENTERO_LSS=266;
public final static short PR_ITERAR=267;
public final static short PR_HASTA=268;
public final static short PR_VECTOR=269;
public final static short PR_DE=270;
public final static short COMP_MAYOR_IGUAL=271;
public final static short COMP_MENOR_IGUAL=272;
public final static short COMP_DISTINTO=273;
public final static short ASIGNACION=274;
public final static short PUNTO_PUNTO=275;
public final static short FUERA_RANGO=276;
public final static short INFERIOR_A_SINO=277;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    3,    6,    7,    6,    6,
    5,    5,    4,    4,    2,    2,   11,    8,   13,    8,
   15,    8,    8,    8,    8,    9,    9,   10,   19,   10,
   10,   21,   20,   22,   20,   23,   20,   20,   20,   12,
   12,   25,   24,   24,   24,   14,   14,   27,   26,   28,
   26,   29,   26,   26,   26,   16,   16,   32,   32,   17,
   17,   33,   17,   17,   18,   34,   34,   34,   34,   34,
   34,   31,   31,   31,   35,   35,   35,   36,   36,   36,
   36,   37,   37,   37,   30,   30,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,   10,    1,    3,    0,    9,    8,
    1,    3,    1,    1,    1,    2,    0,    2,    0,    2,
    0,    2,    1,    2,    2,    1,    1,    6,    0,    9,
    1,    0,    6,    0,    6,    0,    7,    6,    3,    5,
    1,    0,    5,    4,    5,    5,    1,    0,    5,    0,
    5,    0,    5,    5,    2,    4,    1,    4,    3,    1,
    3,    0,    6,    2,    3,    1,    1,    1,    1,    1,
    1,    3,    3,    1,    3,    3,    1,    1,    2,    2,
    1,    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,    0,   13,   14,    0,    0,    2,
    0,    6,   15,    0,    0,    0,   23,    0,   57,   25,
    0,    0,   27,   26,   60,   24,    0,   16,    0,   11,
    0,    0,   18,   31,    0,   20,   41,    0,   22,   47,
    0,    0,    0,    0,   84,   81,    0,   82,    0,    0,
   77,   78,   64,    0,   62,   59,    7,    4,    0,    0,
    0,    0,    0,    0,   55,    0,    0,    0,   83,    0,
    8,    0,    0,   79,   80,   86,    0,    0,    0,    0,
   61,    3,   12,   39,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   50,    0,   58,   56,    0,    0,    0,
    0,   75,   76,    0,   36,    0,    0,   67,   69,   71,
   66,   68,   70,    0,    0,    0,    0,   44,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   45,   40,   43,   54,   46,   53,   51,
   49,    0,    0,    0,   63,    0,   38,    0,   35,   33,
    0,   10,    0,   37,   29,    9,    0,    0,    5,   30,
};
final static short yydgoto[] = {                          1,
    2,    9,   23,   11,   31,   12,   98,   24,   25,   33,
   14,   36,   15,   39,   16,   17,   26,   86,  158,   34,
   62,  107,  128,   37,   64,   40,   67,  123,  122,   18,
   87,   19,   82,  114,   50,   51,   52,
};
final static short yysindex[] = {                         0,
    0, -155,  -34,  -30, -118,    0,    0, -199, -155,    0,
 -182,    0,    0, -196, -183, -173,    0, -177,    0,    0,
  -38, -114,    0,    0,    0,    0,   50,    0,   58,    0,
  -12,  -40,    0,    0, -118,    0,    0,  -22,    0,    0,
  -32,   31,   30, -139,    0,    0, -129,    0,   -1,   73,
    0,    0,    0,  -99,    0,    0,    0,    0, -117, -118,
   -5,   33,   21, -122,    0,  -17, -105,  102,    0,   69,
    0,   33,  -95,    0,    0,    0,   33,   33,   33,   33,
    0,    0,    0,    0,  127,  133,   34,  140,   28,  130,
   33,  149,  151,    0,  -63,    0,    0,  -75,  103,   73,
   73,    0,    0, -155,    0, -180,  -67,    0,    0,    0,
    0,    0,    0,   33,  -65,  139,  141,    0,  142,  143,
  144,  145,  146,  147,  -71, -213,  -87,  -55, -118, -118,
 -118,    7, -118,    0,    0,    0,    0,    0,    0,    0,
    0, -134,  150,  -62,    0, -118,    0,  -53,    0,    0,
  152,    0, -134,    0,    0,    0,  153, -118,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,  -76,    0,  -61,  -76,    0,    0,    0,    9,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -76,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   36,    0,    0,  -84,    0,    0,  -46,    0,    0,
    0,    0,  -39,   80,    0,    0,    0,    0,    0,  -31,
    0,    0,    0,  -76,    0,    0,    0,    0,    0,  -76,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -47,    0,    0,    0,    0,
    0,    0,  158,    0,    0,    0,    0,    0,    0,  -26,
   10,    0,    0,  -76,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -76,    0,  -76,  -76,
  -76,  -14,  -76,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -76,    0,    1,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -76,    0,    0,
};
final static short yygindex[] = {                         0,
  148,   -6,   61,  -93,    0,    0,    0,   78,   32,    0,
    0,    0,    0,    0,    0,    0,    4,   -4,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   27,
   72,    0,    0,    0,   76,   92,    0,
};
final static int YYTABLESIZE=312;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         61,
   28,   85,   85,   85,   22,   85,   47,   85,    1,   74,
   53,   74,   47,   74,   72,   54,   72,   66,   72,   85,
   85,   85,   85,   94,   20,   81,   65,   74,   74,   74,
   74,   59,   72,   72,   72,   72,   65,  145,   63,   47,
   28,   77,  143,   78,   65,   28,   58,   48,  151,   77,
   73,   78,   73,   85,   73,  144,   27,   88,   90,  157,
   21,   74,   10,   84,   32,   47,   72,   48,   73,   73,
   73,   73,   47,   29,   30,  129,   77,   47,   78,   13,
   32,  130,   55,   35,  117,   28,  119,   48,   48,   48,
   38,   76,   49,  112,  113,  111,   41,  127,   48,   13,
    3,    4,   73,   48,   48,   48,   48,    5,   56,    6,
    7,   77,   70,   78,   79,   48,   57,   48,    8,   80,
   72,   83,   83,   71,   83,   28,   83,   97,   74,   75,
    6,    7,  147,  148,  149,   73,  150,    3,    4,   83,
   48,    3,    4,   49,    5,   91,    6,    7,    5,  154,
    6,    7,  100,  101,   95,    8,    3,    4,   28,    8,
   96,  160,   99,    5,   10,    6,    7,  105,    3,    4,
  102,  103,   83,  106,    8,    5,   17,    6,    7,   21,
  115,   13,   19,   42,   17,  132,    8,   21,  118,  120,
   19,  121,  124,  125,  131,  126,  133,  134,  142,  135,
  136,  137,  138,  139,  140,  141,  146,  153,  152,  155,
  156,  159,   85,   48,   34,   60,   52,   42,   43,   44,
   45,    0,   85,   68,   43,   69,   45,    0,    0,  104,
   74,   85,   85,   85,    0,   72,    0,   46,   92,   74,
   74,   74,   93,   46,   72,   72,   72,   65,    0,    0,
   85,   43,   69,   45,    0,    0,   28,   28,   28,   28,
    0,   28,    0,    0,   28,   28,   28,   28,   28,   17,
   46,   73,   21,    0,   28,   19,   28,   43,   69,   45,
   73,   73,   73,  116,   43,   69,   45,    0,   89,   43,
   69,   45,   32,   32,   32,    0,   46,    0,    0,    0,
    0,    0,    0,   46,  108,  109,  110,    0,   46,    0,
    0,   32,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,   41,   42,   43,  123,   45,   45,   47,    0,   41,
  125,   43,   45,   45,   41,   22,   43,   40,   45,   59,
   60,   61,   62,   41,   59,  125,   41,   59,   60,   61,
   62,   44,   59,   60,   61,   62,   59,  125,   35,   45,
    9,   43,  256,   45,   59,   45,   59,   21,  142,   43,
   41,   45,   43,   93,   45,  269,  256,   62,   63,  153,
   91,   93,    2,   60,  261,   45,   93,   41,   59,   60,
   61,   62,   45,  256,  257,  256,   43,   45,   45,    2,
   45,  262,   22,  267,   89,   54,   91,   61,   62,   63,
  264,   93,   21,   60,   61,   62,  274,  104,   72,   22,
  256,  257,   93,   77,   78,   79,   80,  263,   59,  265,
  266,   43,   41,   45,   42,   89,   59,   91,  274,   47,
   91,   42,   43,   93,   45,  125,   47,   59,  258,  259,
  265,  266,  129,  130,  131,  275,  133,  256,  257,  257,
  114,  256,  257,   72,  263,  268,  265,  266,  263,  146,
  265,  266,   77,   78,  260,  274,  256,  257,  127,  274,
   59,  158,  258,  263,  104,  265,  266,   41,  256,  257,
   79,   80,   93,   41,  274,  263,  261,  265,  266,  264,
   41,  104,  267,  268,  261,  114,  274,  264,   59,   41,
  267,   41,  256,  269,  262,   93,  262,   59,  270,   59,
   59,   59,   59,   59,   59,   59,  262,  270,   59,  263,
   59,   59,  274,  260,  262,  256,   59,  256,  257,  258,
  259,   -1,  262,  256,  257,  258,  259,   -1,   -1,   82,
  262,  271,  272,  273,   -1,  262,   -1,  276,  256,  271,
  272,  273,  260,  276,  271,  272,  273,  262,   -1,   -1,
  256,  257,  258,  259,   -1,   -1,  256,  257,  258,  259,
   -1,  261,   -1,   -1,  264,  265,  266,  267,  268,  261,
  276,  262,  264,   -1,  274,  267,  276,  257,  258,  259,
  271,  272,  273,  256,  257,  258,  259,   -1,  268,  257,
  258,  259,  257,  258,  259,   -1,  276,   -1,   -1,   -1,
   -1,   -1,   -1,  276,  271,  272,  273,   -1,  276,   -1,
   -1,  276,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=277;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENTIFICADOR","ENTERO","ENTERO_LSS",
"CADENA_MULTILINEA","PR_SI","PR_ENTONCES","PR_SINO","PR_IMPRIMIR","PR_ENTERO",
"PR_ENTERO_LSS","PR_ITERAR","PR_HASTA","PR_VECTOR","PR_DE","COMP_MAYOR_IGUAL",
"COMP_MENOR_IGUAL","COMP_DISTINTO","ASIGNACION","PUNTO_PUNTO","FUERA_RANGO",
"INFERIOR_A_SINO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : sent_declarativa sent_ejecutable",
"sent_declarativa : sent_declarativa declaracion",
"sent_declarativa :",
"declaracion : tipo_dato lista_variables ';'",
"declaracion : IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';'",
"declaracion : declaracion_invalida",
"declaracion_invalida : tipo_dato error ';'",
"$$1 :",
"declaracion_invalida : IDENTIFICADOR '[' error ']' $$1 PR_VECTOR PR_DE tipo_dato ';'",
"declaracion_invalida : IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' error ';'",
"lista_variables : IDENTIFICADOR",
"lista_variables : lista_variables ',' IDENTIFICADOR",
"tipo_dato : PR_ENTERO",
"tipo_dato : PR_ENTERO_LSS",
"sent_ejecutable : sentencia_valida",
"sent_ejecutable : sent_ejecutable sentencia",
"$$2 :",
"sentencia_valida : $$2 seleccion",
"$$3 :",
"sentencia_valida : $$3 iteracion",
"$$4 :",
"sentencia_valida : $$4 impresion",
"sentencia_valida : asignacion",
"sentencia_valida : PR_SINO bloque",
"sentencia_valida : error ';'",
"sentencia : sentencia_valida",
"sentencia : declaracion",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"$$5 :",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO $$5 bloque",
"seleccion : seleccion_invalida",
"$$6 :",
"seleccion_invalida : PR_SI $$6 condicion ')' PR_ENTONCES bloque",
"$$7 :",
"seleccion_invalida : PR_SI '(' condicion $$7 PR_ENTONCES bloque",
"$$8 :",
"seleccion_invalida : PR_SI '(' error ')' $$8 PR_ENTONCES bloque",
"seleccion_invalida : PR_SI '(' condicion ')' error bloque",
"seleccion_invalida : PR_SI error bloque",
"iteracion : PR_ITERAR bloque PR_HASTA condicion ';'",
"iteracion : iteracion_invalida",
"$$9 :",
"iteracion_invalida : PR_ITERAR $$9 PR_HASTA condicion ';'",
"iteracion_invalida : PR_ITERAR bloque condicion ';'",
"iteracion_invalida : PR_ITERAR bloque PR_HASTA error ';'",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')' ';'",
"impresion : impresion_invalida",
"$$10 :",
"impresion_invalida : PR_IMPRIMIR $$10 CADENA_MULTILINEA error ';'",
"$$11 :",
"impresion_invalida : PR_IMPRIMIR '(' ')' $$11 ';'",
"$$12 :",
"impresion_invalida : PR_IMPRIMIR '(' CADENA_MULTILINEA $$12 ';'",
"impresion_invalida : PR_IMPRIMIR '(' error ')' ';'",
"impresion_invalida : PR_IMPRIMIR ';'",
"asignacion : asignable ASIGNACION e ';'",
"asignacion : asignacion_invalida",
"asignacion_invalida : asignable ASIGNACION error ';'",
"asignacion_invalida : ASIGNACION error ';'",
"bloque : sentencia",
"bloque : '{' sent_ejecutable '}'",
"$$13 :",
"bloque : '{' declaracion $$13 sent_declarativa sent_ejecutable '}'",
"bloque : '{' '}'",
"condicion : e comparador e",
"comparador : '>'",
"comparador : COMP_MAYOR_IGUAL",
"comparador : '<'",
"comparador : COMP_MENOR_IGUAL",
"comparador : '='",
"comparador : COMP_DISTINTO",
"e : e '+' t",
"e : e '-' t",
"e : t",
"t : t '*' f",
"t : t '/' f",
"t : f",
"f : valor",
"f : '-' ENTERO",
"f : '-' ENTERO_LSS",
"f : FUERA_RANGO",
"valor : asignable",
"valor : ENTERO",
"valor : ENTERO_LSS",
"asignable : IDENTIFICADOR",
"asignable : IDENTIFICADOR '[' e ']'",
};

//#line 147 "gramatica.y"

/* Código */

private Proyecto proyecto;
private int errores = 0;
private int erroresGenCod = 0;

private Vector<Token> declaracionesAux = new Vector<Token>();

private Integer nivelActual = 0;
private Vector<Integer>  pilaNivel = new Vector<Integer>();
private Vector<ArbolAbs> pilaCondiciones = new Vector<ArbolAbs>();
private Vector<ArbolAbs> pilaBloques = new Vector<ArbolAbs>();
private Vector<ArbolAbs> sentencias = new Vector<ArbolAbs>();

private ArbolAbs SentenciaAsignacion;
private ArbolAbs SentenciaImpresion;
private ArbolAbs SentenciaSeleccion;
private ArbolAbs SentenciaIteracion;
private ArbolAbs E;
private ArbolAbs T;
private ArbolAbs F;
private ArbolAbs HojaAux;
private ArbolAbs Condicion;
private ArbolAbs Cuerpo;
private ArbolAbs E1;
private ArbolAbs E2;
private ArbolAbs Bloque;
private ArbolAbs Bloque1;
private ArbolAbs Bloque2;

private String UltimoComparador = "";

private void yyerror(String string) {
	
}

private int yylex(){
	int salida = AnalizadorLexico.yylex();
	if (AnalizadorLexico.yylval != null){
		yylval = AnalizadorLexico.yylval;
		//AnalizadorLexico.yylval = null;
	}else{
		yylval = new ParserVal(); 
	}
	return salida;
}

private int lineaActual(){
	return proyecto.getLineaActual();
}

private void escribirError(String s){
	errores++;
	ConsolaManager.getInstance().escribirError("Sintáctico [Línea " + lineaActual() + "] "+ s);
}

private void escribirErrorDeGeneracion(String s){
	erroresGenCod++;
	ConsolaManager.getInstance().escribirError("Gen. Cod. Int [Línea " + lineaActual() + "] "+ s);
}

private void indicarSentencia(String s){
	proyecto.addSentenciaToList("Línea " +lineaActual()+ " -> " + s + ".");
}

public Parser(Proyecto p){
	this.proyecto = p;
}

private void chequearNegativo(){
	//Chequear valor del negativo
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if ( t.getContador() == 1){
		//Se pisa el token existente
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) ){
			t.setLexema("-"+t.getLexema());
			tratarConstante(yylval,"entero");
		}
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			proyecto.getTablaDeSimbolos().remove(t.getLexema());
		}
	}else{
		//Se crea otro token nuevo.
		t.disminuirContador();
		
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) ){
			proyecto.getTablaDeSimbolos().add(tnuevo);
			tratarConstante(new ParserVal(proyecto.getTablaDeSimbolos().getList().size()-1),"entero");
		}
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			tratarConstante(new ParserVal(proyecto.getTablaDeSimbolos().getPos(tnuevo.getLexema())),"entero");
		}
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

private void chequearRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (Integer.parseInt(t.getLexema()) == 32768){
		t.setTipo(Token.TipoToken.ENTERO_LSS);
		tratarConstante(yylval,"entero_lss");
	}else
		tratarConstante(yylval,"entero");
	
	actualizarTablaDeSimbolos();
}

private void actualizarTablaDeSimbolos(){
	proyecto.getTablaDeSimbolos().setearCambios();
	proyecto.getTablaDeSimbolos().notifyObservers();
	for (ElementoTS elemento : proyecto.getTablaDeSimbolos().getList()){
		proyecto.getTablaDeSimbolos().setearCambios();
		proyecto.getTablaDeSimbolos().notifyObservers(elemento);
	}
}

private void borrarFueraRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (t.getContador() > 1){
		t.disminuirContador();
	}else{
		proyecto.getTablaDeSimbolos().remove(t.getLexema());
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

public int getCantidadErrores(){
	return errores;
}

public int getCantidadErroresGenCod(){
	return erroresGenCod;
}
private void agregar(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	declaracionesAux.add(t);
}

private void agregar(String tipo){
	Token t = new Token();
	t.setLexema(tipo);
	declaracionesAux.add(t);
}

private void generarDeclaracionTipoBasico(){
ElementoTS.TIPOS tipo;
if (declaracionesAux.elementAt(0).getLexema().equals("entero"))
	tipo = ElementoTS.TIPOS.ENTERO;
else
	tipo = ElementoTS.TIPOS.ENTERO_LSS;
	
	for (int i = 1; i < declaracionesAux.size();i++){
		int pos = proyecto.getTablaDeSimbolos().getPos(declaracionesAux.elementAt(i).getLexema());
		ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos);
		elemento.setTipo(tipo);
		elemento.setUso(ElementoTS.USOS.VARIABLE);
	}
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

private void generarDeclaracionTipoVector(ParserVal iden, ParserVal limInf, ParserVal limSup){
ElementoTS.TIPOS tipo;
if (declaracionesAux.elementAt(0).getLexema().equals("entero"))
	tipo = ElementoTS.TIPOS.VECTOR_ENTERO;
else
	tipo = ElementoTS.TIPOS.VECTOR_ENTERO_LSS;
	
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(iden.ival);
	
	Integer lim_i = Integer.parseInt(proyecto.getTablaDeSimbolos().getElemento(limInf.ival).getToken().getLexema());
	
	Integer lim_s = Integer.parseInt(proyecto.getTablaDeSimbolos().getElemento(limSup.ival).getToken().getLexema());
	
	//Chequear rangos
	
	if (lim_i < 0)
		escribirErrorDeGeneracion("Límite Inferior menor a 0.");
	if (lim_s < 0)
		escribirErrorDeGeneracion("Límite Superior menor a 0.");
	if (lim_i > lim_s)
		escribirErrorDeGeneracion("El límite inferior es mayor al superior.");
		
	//----
	
	tratarConstante(limInf,"entero");
	tratarConstante(limSup,"entero");
	
	elemento.setLim_inf(lim_i);
	elemento.setLim_sup(lim_s);
	elemento.setTipo(tipo);
	elemento.setUso(ElementoTS.USOS.ARREGLO);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

private void tratarConstante(ParserVal pos,String tipoDado){
	ElementoTS.TIPOS tipo;
	if (tipoDado.equals("entero"))
		tipo = ElementoTS.TIPOS.ENTERO;
	else
		tipo = ElementoTS.TIPOS.ENTERO_LSS;
	
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	
	elemento.setTipo(tipo);
	
	elemento.setUso(ElementoTS.USOS.CONSTANTE);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

private void tratarCadenaMultilinea(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	
	elemento.setTipo(ElementoTS.TIPOS.CADENA_MULTILINEA);
	
	elemento.setUso(ElementoTS.USOS.CONSTANTE);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

private void tratarRedeclaraciones(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (elemento.getToken().getContador() > 1)
		escribirErrorDeGeneracion("Duplicación de identificador \"" + elemento.getToken().getLexema() + "\".");
}

private void tratarNodeclaraciones(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (elemento.getTipo() == null)
		escribirErrorDeGeneracion("Identificador \"" + elemento.getToken().getLexema() + "\" no declarado.");
}

private void tratarIndiceInvalido(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (E.getTipo() == null){
		escribirErrorDeGeneracion("Índice inválido en el vector \"" + elemento.getToken().getLexema() + "\", existen operaciones entre entero y entero_lss.");
	}
	if (E.getTipo() == ElementoTS.TIPOS.ENTERO_LSS){
		escribirErrorDeGeneracion("Índice inválido en el vector \"" + elemento.getToken().getLexema() + "\", el subíndice no puede ser de tipo entero_lss.");
	}
}
private ArbolAbs crear_hoja(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	ArbolAbs salida = new Hoja(elemento);
	return salida;
}

private ArbolAbs crear_nodo(String Operacion,ArbolAbs arb_izq,ArbolAbs arb_der){
	ArbolAbs salida = new Arbol(Operacion,arb_izq,arb_der);
	return salida;
}

private void addSentencia(ArbolAbs sentencia){
	//Vector de sentencias a devolver
	sentencias.add(sentencia);
}

public Vector<ArbolAbs> getSentencias(){
	//Vector de sentencias a devolver
	return sentencias;
}
private void agregarExpresion(ArbolAbs exp){
	if (E2 != null)
		E1 = E2.clone();
	else
		E1 = exp;
	E2 = exp;
}

private void agregarCondicion(ArbolAbs exp){
	pilaCondiciones.add(exp);
}

private ArbolAbs getUltimaCondicion(){
	ArbolAbs salida = pilaCondiciones.lastElement();
	pilaCondiciones.remove(pilaCondiciones.size() - 1);
	return salida;
}

private void aumentarNivel(){
	nivelActual++;
}

private void decrementarNivel(){
	//Borramos todo lo referido a ese nivel
	while( ! pilaNivel.isEmpty() && pilaNivel.lastElement() == nivelActual && ! pilaBloques.isEmpty()){
		pilaBloques.remove(pilaBloques.size() - 1);
		pilaNivel.remove(pilaNivel.size() - 1);
	}
	nivelActual--;
}

private void apilar(ArbolAbs exp){
	if (nivelActual == 0){
		//No estamos dentro de un bloque
		addSentencia(exp);
	}
	pilaBloques.add(exp);
	pilaNivel.add(nivelActual);
}

private ArbolAbs desapilar(){
	ArbolAbs salida;
	ArbolAbs intermedio;
	Vector<ArbolAbs> objetivos = new Vector<ArbolAbs>();
	
	//Obtenemos los arboles a componer
	for (int i = 0; i < pilaNivel.size(); i++){
		if (pilaNivel.elementAt(i).equals(nivelActual)){
			objetivos.add(pilaBloques.elementAt(i));
			pilaBloques.setElementAt(null,i);
		}
	}
	//Desapilamos esos arboles
	while( ! pilaBloques.isEmpty() && pilaBloques.lastElement() == null){
		pilaBloques.remove(pilaBloques.size() - 1);
		pilaNivel.remove(pilaNivel.size() - 1);
	}

	//Se crea la estructura compuesta
	salida = crear_nodo("Sentencia General",objetivos.firstElement(),null);
	intermedio = salida;
	objetivos.remove(0);
	for ( ArbolAbs arbol : objetivos ){
		ArbolAbs aux = crear_nodo("Sentencia General",arbol,null);
		((Arbol) ((Arbol) intermedio).dameMasDerecho()).setDerecho(aux);
		intermedio = arbol;
	}
	
	//Guardamos el arbol generado
	pilaBloques.add(salida);
	pilaNivel.add(nivelActual);
	
	return salida;
}

//#line 749 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 4:
//#line 30 "gramatica.y"
{ indicarSentencia("Declaración de tipo básico"); generarDeclaracionTipoBasico(); }
break;
case 5:
//#line 31 "gramatica.y"
{ indicarSentencia("Declaración de tipo vector"); generarDeclaracionTipoVector(val_peek(9),val_peek(7),val_peek(5)); tratarRedeclaraciones(val_peek(9));}
break;
case 7:
//#line 35 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo básico incorrecta."); }
break;
case 8:
//#line 36 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 10:
//#line 37 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 11:
//#line 40 "gramatica.y"
{ tratarRedeclaraciones(val_peek(0)); agregar();}
break;
case 12:
//#line 41 "gramatica.y"
{ tratarRedeclaraciones(val_peek(0)); agregar();}
break;
case 13:
//#line 44 "gramatica.y"
{ agregar("entero");}
break;
case 14:
//#line 45 "gramatica.y"
{ agregar("entero_lss");}
break;
case 17:
//#line 52 "gramatica.y"
{ indicarSentencia("Selección"); aumentarNivel(); }
break;
case 18:
//#line 52 "gramatica.y"
{ apilar(SentenciaSeleccion); }
break;
case 19:
//#line 53 "gramatica.y"
{ indicarSentencia("Iteración"); aumentarNivel(); }
break;
case 20:
//#line 53 "gramatica.y"
{ apilar(SentenciaIteracion); }
break;
case 21:
//#line 54 "gramatica.y"
{ indicarSentencia("Impresión"); }
break;
case 22:
//#line 54 "gramatica.y"
{ apilar(SentenciaImpresion); }
break;
case 23:
//#line 55 "gramatica.y"
{ apilar(SentenciaAsignacion); }
break;
case 24:
//#line 56 "gramatica.y"
{ escribirError("No se esperaba la palabra reservada \"sino\"."); }
break;
case 25:
//#line 57 "gramatica.y"
{ escribirError("Sentencia inválida."); }
break;
case 27:
//#line 61 "gramatica.y"
{ escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }
break;
case 28:
//#line 64 "gramatica.y"
{ Bloque = desapilar(); decrementarNivel(); Cuerpo = crear_nodo("Cuerpo",Bloque,null);    SentenciaSeleccion = crear_nodo("Selección",getUltimaCondicion(),Cuerpo); }
break;
case 29:
//#line 65 "gramatica.y"
{ aumentarNivel(); }
break;
case 30:
//#line 65 "gramatica.y"
{  Bloque2 = desapilar(); decrementarNivel(); Bloque1 = desapilar(); decrementarNivel(); Cuerpo = crear_nodo("Cuerpo",Bloque1,Bloque2); SentenciaSeleccion = crear_nodo("Selección",getUltimaCondicion(),Cuerpo); }
break;
case 32:
//#line 69 "gramatica.y"
{escribirError("Falta '(' en sentencia si.");}
break;
case 34:
//#line 70 "gramatica.y"
{escribirError("Falta ')' en sentencia si.");}
break;
case 36:
//#line 71 "gramatica.y"
{escribirError("Condición inválida en la sentencia si.");}
break;
case 38:
//#line 72 "gramatica.y"
{escribirError("Sentencia si inválida.");}
break;
case 39:
//#line 73 "gramatica.y"
{escribirError("Sentencia si inválida.");}
break;
case 40:
//#line 76 "gramatica.y"
{ Bloque = desapilar(); decrementarNivel(); SentenciaIteracion = crear_nodo("Iteración",Bloque,getUltimaCondicion()); }
break;
case 42:
//#line 80 "gramatica.y"
{ escribirError("No se especificó un bloque a iterar."); }
break;
case 44:
//#line 81 "gramatica.y"
{ escribirError("No se especificó la palabra reservada \"hasta\" en la iteración."); }
break;
case 45:
//#line 82 "gramatica.y"
{ escribirError("Condición inválida en la sentencia iterar."); }
break;
case 46:
//#line 85 "gramatica.y"
{ tratarCadenaMultilinea(val_peek(2)); SentenciaImpresion = crear_nodo("Impresión",crear_hoja(val_peek(2)),null); }
break;
case 48:
//#line 89 "gramatica.y"
{escribirError("Falta '(' en sentencia de impresión.");}
break;
case 50:
//#line 90 "gramatica.y"
{escribirError("No se especificó una cadena multilínea en sentencia de impresión.");}
break;
case 52:
//#line 91 "gramatica.y"
{escribirError("Falta ')' en sentencia de impresión.");}
break;
case 54:
//#line 92 "gramatica.y"
{escribirError("Impresión Inválida.");}
break;
case 55:
//#line 93 "gramatica.y"
{escribirError("Falta \"('Cadena_Multilinea')\" .");}
break;
case 56:
//#line 96 "gramatica.y"
{ indicarSentencia("Asignación"); SentenciaAsignacion = crear_nodo("Asignación",crear_hoja(val_peek(3)),E);}
break;
case 58:
//#line 100 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 59:
//#line 101 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 62:
//#line 106 "gramatica.y"
{ escribirError("No se pueden declarar variables dentro de un bloque."); }
break;
case 64:
//#line 107 "gramatica.y"
{ escribirError("Bloque de sentencias vacío."); }
break;
case 65:
//#line 110 "gramatica.y"
{ Condicion = crear_nodo(UltimoComparador,E1,E2); agregarCondicion(Condicion); }
break;
case 66:
//#line 113 "gramatica.y"
{ UltimoComparador = new String("Comparador \">\""); }
break;
case 67:
//#line 114 "gramatica.y"
{ UltimoComparador = new String("Comparador \">=\""); }
break;
case 68:
//#line 115 "gramatica.y"
{ UltimoComparador = new String("Comparador \"<\""); }
break;
case 69:
//#line 116 "gramatica.y"
{ UltimoComparador = new String("Comparador \"<=\""); }
break;
case 70:
//#line 117 "gramatica.y"
{ UltimoComparador = new String("Comparador \"=\""); }
break;
case 71:
//#line 118 "gramatica.y"
{ UltimoComparador = new String("Comparador \"^=\""); }
break;
case 72:
//#line 121 "gramatica.y"
{ E = crear_nodo("Suma \"+\"",E,T);  agregarExpresion(E); }
break;
case 73:
//#line 122 "gramatica.y"
{ E = crear_nodo("Resta \"-\"",E,T); agregarExpresion(E); }
break;
case 74:
//#line 123 "gramatica.y"
{ E = T; agregarExpresion(E); }
break;
case 75:
//#line 126 "gramatica.y"
{ T = crear_nodo("Multiplicación \"*\"",T,F); }
break;
case 76:
//#line 127 "gramatica.y"
{ T = crear_nodo("División \"/\"",T,F); }
break;
case 77:
//#line 128 "gramatica.y"
{ T = F; }
break;
case 78:
//#line 131 "gramatica.y"
{ F = HojaAux; }
break;
case 79:
//#line 132 "gramatica.y"
{ chequearNegativo(); }
break;
case 80:
//#line 133 "gramatica.y"
{ escribirError("Constante negativa fuera de rango."); borrarFueraRango(); }
break;
case 81:
//#line 134 "gramatica.y"
{ escribirError("Constante fuera de rango"); }
break;
case 83:
//#line 138 "gramatica.y"
{ chequearRango();					HojaAux = crear_hoja(val_peek(0)); }
break;
case 84:
//#line 139 "gramatica.y"
{ tratarConstante(val_peek(0),"entero_lss");	HojaAux = crear_hoja(val_peek(0));  }
break;
case 85:
//#line 142 "gramatica.y"
{ tratarNodeclaraciones(val_peek(0));	HojaAux = crear_hoja(val_peek(0));  }
break;
case 86:
//#line 143 "gramatica.y"
{ tratarNodeclaraciones(val_peek(3));	HojaAux = crear_hoja(val_peek(3)); tratarIndiceInvalido(val_peek(3)); }
break;
//#line 1150 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
