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

//#line 25 "Parser.java"




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
public final static short NEG=277;
public final static short INFERIOR_A_SINO=278;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    3,    6,    7,    6,    6,
    5,    5,    4,    4,    2,    2,   11,    8,   13,    8,
   15,    8,    8,    8,    9,    9,   17,   10,   10,   10,
   21,   20,   22,   20,   20,   20,   20,   20,   12,   12,
   24,   23,   23,   23,   14,   14,   26,   25,   27,   25,
   28,   25,   25,   25,   16,   16,   31,   31,   19,   19,
   32,   19,   19,   18,   33,   33,   33,   33,   33,   33,
   30,   30,   30,   34,   34,   34,   35,   35,   35,   35,
   36,   36,   36,   29,   29,
};
final static short yylen[] = {                            2,
    2,    2,    0,    3,   10,    1,    3,    0,    9,    8,
    1,    3,    1,    1,    1,    2,    0,    2,    0,    2,
    0,    2,    1,    1,    1,    1,    2,    6,    8,    1,
    0,    6,    0,    6,    6,    8,    6,    3,    5,    1,
    0,    5,    4,    5,    5,    1,    0,    5,    0,    5,
    0,    5,    5,    2,    4,    1,    4,    3,    1,    3,
    0,    6,    2,    3,    1,    1,    1,    1,    1,    1,
    3,    3,    1,    3,    3,    1,    1,    2,    2,    1,
    1,    1,    1,    1,    4,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    0,   13,   14,    0,    0,    2,    0,
    6,   15,    0,    0,    0,   23,   24,    0,   56,   27,
    0,    0,   26,   25,   16,    0,   11,    0,    0,   18,
   30,    0,   20,   40,    0,   22,   46,    0,    0,    0,
    0,   83,   80,    0,   81,    0,    0,   76,   77,   58,
    7,    4,    0,    0,    0,    0,    0,   59,    0,    0,
   54,    0,    0,    0,   82,    0,    8,    0,    0,   78,
   79,   85,    0,    0,    0,    0,   12,   38,    0,    0,
    0,    0,   63,    0,   61,    0,    0,    0,    0,    0,
   49,    0,   57,   55,    0,    0,    0,    0,   74,   75,
    0,    0,    0,   66,   68,   70,   65,   67,   69,    0,
    0,   60,    3,    0,    0,   43,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   44,   39,   42,   53,   45,   52,   50,   48,    0,
    0,    0,    0,   37,    0,   34,   32,    0,    0,   10,
    0,    0,    0,   62,    9,    0,   36,   29,    5,
};
final static short yydgoto[] = {                          1,
    2,    8,   23,   10,   28,   11,   95,   24,   58,   30,
   13,   33,   14,   36,   15,   16,   17,   80,   59,   31,
   56,  103,   34,   60,   37,   63,  121,  120,   18,   81,
   19,  113,  110,   47,   48,   49,
};
final static short yysindex[] = {                         0,
    0, -202,  -50,  -74,    0,    0, -227, -202,    0, -143,
    0,    0, -200, -193, -183,    0,    0, -186,    0,    0,
  -42,   68,    0,    0,    0,   69,    0,   -3,   -9,    0,
    0, -118,    0,    0,  -24,    0,    0,  -34,   37,   41,
 -134,    0,    0, -140,    0,    5,   -4,    0,    0,    0,
    0,    0, -115, -118,  -13,   51, -112,    0,   48, -125,
    0,  -27, -111,   91,    0,   35,    0,   51, -106,    0,
    0,    0,   51,   51,   51,   51,    0,    0,  114,  116,
   60,  117,    0,  -97,    0,   -6,  102,   51,  123,  126,
    0,  -84,    0,    0,  -96,   83,   -4,   -4,    0,    0,
  -75, -194,  -73,    0,    0,    0,    0,    0,    0,   51,
  -72,    0,    0,  119,  129,    0,  132,  133,  135,  136,
  137,  138,  -68, -203, -118, -118, -118, -118,   57, -118,
 -202,    0,    0,    0,    0,    0,    0,    0,    0, -150,
  144,  -66,  -58,    0,  -57,    0,    0,  -91,  148,    0,
 -150, -118, -118,    0,    0,  149,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0, -185,    0,  -65,    0,    0,    0,   23,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   54,    0,
    0,  -82,    0,    0,  -48,    0,    0,    0,    0,  -35,
    2,    0,    0,    0,    0,    0,  -41,    0,    0,    0,
    0,    0,    0, -185,    0,    0, -185,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -52,
    0,    0,    0, -185,    0,    0,    0,    0,    0,   69,
    0,    0,    0,    0,    0,    0,   24,   30,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -185, -185, -185, -185,   -8, -185,
 -185,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,   15,    0,    0, -185,    0,    0,
    0, -185, -185,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  105,  -20,   20,  -81,    0,    0,    0,   40,   22,    0,
    0,    0,    0,    0,    0,    0,    0,   45,  -18,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  125,   19,
    0,    0,    0,   63,   49,    0,
};
final static int YYTABLESIZE=333;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         73,
   35,   73,   44,   73,   57,   84,   84,   84,   20,   84,
   44,   84,   83,   91,   28,   62,   21,   73,   73,   73,
   73,    9,    1,   84,   84,   84,   84,  112,   22,   25,
   55,   44,   64,  154,   61,   78,   84,   75,   44,   46,
   53,   12,   76,   82,   82,   35,   82,   73,   82,   74,
   64,   73,  141,    3,    4,   52,   66,   84,  149,   28,
   29,  126,    5,    6,   71,  142,   71,  127,   71,  156,
   72,    7,   72,   32,   72,   17,   85,   73,   21,   74,
   35,   19,   71,   71,   71,   71,   46,   38,   72,   72,
   72,   72,   44,   94,   82,   44,   12,   72,   31,   73,
   82,   74,   73,   87,   74,   25,  143,  144,  145,  146,
  148,  147,   26,   27,    5,    6,   71,   70,   71,  108,
  109,  107,   72,   99,  100,   35,   50,   51,  129,   67,
  115,   68,  117,  157,  158,   97,   98,    3,    4,   28,
   69,   77,   88,    3,    4,   45,    5,    6,   92,   93,
    9,   96,    5,    6,  101,    7,  102,  111,    3,    4,
  116,    7,   45,  118,    3,    4,  119,    5,    6,   25,
   12,  122,  123,    5,    6,  124,    7,  132,   17,   45,
   45,   21,    7,   45,   19,   41,  125,  133,  128,  130,
  134,  135,   45,  136,  137,  138,  139,   45,   45,   45,
   45,  140,  150,  151,  152,  153,  155,  159,   84,   33,
   45,   47,   45,   39,   40,   41,   42,  131,    0,    0,
   73,   64,   40,   65,   42,    0,   84,    0,   89,   73,
   73,   73,   90,   43,   45,   84,   84,   84,    0,    0,
    0,   43,   79,   40,   65,   42,   54,    0,    0,  114,
   40,   65,   42,   64,    0,    0,   35,   35,   35,   35,
    0,   35,   43,    0,   35,   35,   35,   35,   35,   43,
   28,   28,   28,   28,   35,   28,   35,    0,   28,   28,
   28,   28,   28,   17,    0,   71,   21,    0,   28,   19,
   28,   72,    0,    0,   71,   71,   71,    0,    0,    0,
   72,   72,   72,    0,   40,   65,   42,   40,   65,   42,
   31,   31,   31,    0,    0,   86,    0,    0,    0,    0,
    0,    0,    0,   43,    0,    0,   43,    0,    0,   31,
  104,  105,  106,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
    0,   43,   45,   45,  123,   41,   42,   43,   59,   45,
   45,   47,  125,   41,    0,   40,   91,   59,   60,   61,
   62,    2,    0,   59,   60,   61,   62,  125,  256,    8,
   40,   45,   41,  125,   59,   54,   57,   42,   45,   21,
   44,    2,   47,   42,   43,   45,   45,   43,   47,   45,
   59,   93,  256,  256,  257,   59,   38,   93,  140,   45,
  261,  256,  265,  266,   41,  269,   43,  262,   45,  151,
   41,  274,   43,  267,   45,  261,   57,   43,  264,   45,
  264,  267,   59,   60,   61,   62,   68,  274,   59,   60,
   61,   62,   45,   59,   93,   45,   57,   93,   45,   43,
   56,   45,   43,   59,   45,   84,  125,  126,  127,  128,
  131,  130,  256,  257,  265,  266,   93,  258,  259,   60,
   61,   62,   93,   75,   76,  125,   59,   59,  110,   93,
   86,   91,   88,  152,  153,   73,   74,  256,  257,  125,
  275,  257,  268,  256,  257,   21,  265,  266,  260,   59,
  131,  258,  265,  266,   41,  274,   41,   41,  256,  257,
   59,  274,   38,   41,  256,  257,   41,  265,  266,  148,
  131,  256,  269,  265,  266,   93,  274,   59,  261,   55,
   56,  264,  274,   59,  267,  268,  262,   59,  262,  262,
   59,   59,   68,   59,   59,   59,   59,   73,   74,   75,
   76,  270,   59,  270,  263,  263,   59,   59,  274,  262,
   86,  260,   88,  256,  257,  258,  259,  113,   -1,   -1,
  262,  256,  257,  258,  259,   -1,  262,   -1,  256,  271,
  272,  273,  260,  276,  110,  271,  272,  273,   -1,   -1,
   -1,  276,  256,  257,  258,  259,  256,   -1,   -1,  256,
  257,  258,  259,  262,   -1,   -1,  256,  257,  258,  259,
   -1,  261,  276,   -1,  264,  265,  266,  267,  268,  276,
  256,  257,  258,  259,  274,  261,  276,   -1,  264,  265,
  266,  267,  268,  261,   -1,  262,  264,   -1,  274,  267,
  276,  262,   -1,   -1,  271,  272,  273,   -1,   -1,   -1,
  271,  272,  273,   -1,  257,  258,  259,  257,  258,  259,
  257,  258,  259,   -1,   -1,  268,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  276,   -1,   -1,  276,   -1,   -1,  276,
  271,  272,  273,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=278;
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
"NEG","INFERIOR_A_SINO",
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
"sentencia_valida : sentencia_invalida",
"sentencia : sentencia_valida",
"sentencia : declaracion",
"sentencia_invalida : error ';'",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque",
"seleccion : PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion : seleccion_invalida",
"$$5 :",
"seleccion_invalida : PR_SI $$5 condicion ')' PR_ENTONCES bloque",
"$$6 :",
"seleccion_invalida : PR_SI '(' condicion $$6 PR_ENTONCES bloque",
"seleccion_invalida : PR_SI '(' error ')' PR_ENTONCES bloque",
"seleccion_invalida : PR_SI '(' error ')' PR_ENTONCES bloque PR_SINO bloque",
"seleccion_invalida : PR_SI '(' condicion ')' error bloque",
"seleccion_invalida : PR_SI error bloque",
"iteracion : PR_ITERAR bloque PR_HASTA condicion ';'",
"iteracion : iteracion_invalida",
"$$7 :",
"iteracion_invalida : PR_ITERAR $$7 PR_HASTA condicion ';'",
"iteracion_invalida : PR_ITERAR bloque condicion ';'",
"iteracion_invalida : PR_ITERAR bloque PR_HASTA error ';'",
"impresion : PR_IMPRIMIR '(' CADENA_MULTILINEA ')' ';'",
"impresion : impresion_invalida",
"$$8 :",
"impresion_invalida : PR_IMPRIMIR $$8 CADENA_MULTILINEA error ';'",
"$$9 :",
"impresion_invalida : PR_IMPRIMIR '(' ')' $$9 ';'",
"$$10 :",
"impresion_invalida : PR_IMPRIMIR '(' CADENA_MULTILINEA $$10 ';'",
"impresion_invalida : PR_IMPRIMIR '(' error ')' ';'",
"impresion_invalida : PR_IMPRIMIR ';'",
"asignacion : asignable ASIGNACION e ';'",
"asignacion : asignacion_invalida",
"asignacion_invalida : asignable ASIGNACION error ';'",
"asignacion_invalida : ASIGNACION error ';'",
"bloque : sentencia",
"bloque : '{' sent_ejecutable '}'",
"$$11 :",
"bloque : '{' declaracion $$11 sent_declarativa sent_ejecutable '}'",
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

//#line 153 "gramatica.y"

/* Código */

private Proyecto proyecto;
private int errores = 0;

private void yyerror(String string) {
	//System.out.println(string);	
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
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) )
			t.setLexema("-"+t.getLexema());
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			proyecto.getTablaDeSimbolos().remove(t.getLexema());
		}
	}else{
		//Se crea otro token nuevo.
		t.disminuirContador();
		
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) )
			proyecto.getTablaDeSimbolos().add(tnuevo);
		else
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

public void chequearRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (Integer.parseInt(t.getLexema()) == 32768){
		t.setTipo(Token.TipoToken.ENTERO_LSS);
	}
	actualizarTablaDeSimbolos();
}

public void actualizarTablaDeSimbolos(){
	proyecto.getTablaDeSimbolos().setearCambios();
	proyecto.getTablaDeSimbolos().notifyObservers();
	for (Token tok : proyecto.getTablaDeSimbolos().getList()){
		proyecto.getTablaDeSimbolos().setearCambios();
		proyecto.getTablaDeSimbolos().notifyObservers(tok);
	}
}

public int getCantidadErrores(){
	return errores;
}
//#line 485 "Parser.java"
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
//#line 33 "gramatica.y"
{ indicarSentencia("Declaración de tipo básico"); }
break;
case 5:
//#line 34 "gramatica.y"
{ indicarSentencia("Declaración de tipo vector"); }
break;
case 7:
//#line 38 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo básico incorrecta."); }
break;
case 8:
//#line 39 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 10:
//#line 40 "gramatica.y"
{ escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
break;
case 17:
//#line 55 "gramatica.y"
{ indicarSentencia("Selección");}
break;
case 19:
//#line 56 "gramatica.y"
{ indicarSentencia("Iteración");}
break;
case 21:
//#line 57 "gramatica.y"
{ indicarSentencia("Impresión");}
break;
case 26:
//#line 63 "gramatica.y"
{ escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }
break;
case 27:
//#line 66 "gramatica.y"
{ escribirError("Sentencia inválida."); }
break;
case 31:
//#line 74 "gramatica.y"
{escribirError("Falta '(' en sentencia si.");}
break;
case 33:
//#line 75 "gramatica.y"
{escribirError("Falta ')' en sentencia si.");}
break;
case 35:
//#line 76 "gramatica.y"
{escribirError("Condición inválida en la sentencia si.");}
break;
case 36:
//#line 77 "gramatica.y"
{escribirError("Condición inválida en la sentencia si.");}
break;
case 37:
//#line 78 "gramatica.y"
{escribirError("Sentencia si inválida.");}
break;
case 38:
//#line 79 "gramatica.y"
{escribirError("Sentencia si inválida.");}
break;
case 41:
//#line 86 "gramatica.y"
{ escribirError("No se especificó un bloque a iterar."); }
break;
case 43:
//#line 87 "gramatica.y"
{ escribirError("No se especificó la palabra reservada hasta en la iteración."); }
break;
case 44:
//#line 88 "gramatica.y"
{ escribirError("Condición inválida en la sentencia iterar."); }
break;
case 47:
//#line 95 "gramatica.y"
{escribirError("Falta '('.");}
break;
case 49:
//#line 96 "gramatica.y"
{escribirError("No se especificó una cadena multilínea.");}
break;
case 51:
//#line 97 "gramatica.y"
{escribirError("Falta ')'.");}
break;
case 53:
//#line 98 "gramatica.y"
{escribirError("Impresión Inválida.");}
break;
case 54:
//#line 99 "gramatica.y"
{escribirError("Falta \"('Cadena_Multilinea')\" .");}
break;
case 55:
//#line 102 "gramatica.y"
{ indicarSentencia("Asignación");}
break;
case 57:
//#line 106 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 58:
//#line 107 "gramatica.y"
{ escribirError("Asignación inválida.");}
break;
case 61:
//#line 112 "gramatica.y"
{ escribirError("No se pueden declarar variables dentro de un bloque."); }
break;
case 63:
//#line 113 "gramatica.y"
{ escribirError("Bloque de sentencias vacío."); }
break;
case 78:
//#line 138 "gramatica.y"
{ chequearNegativo(); }
break;
case 79:
//#line 139 "gramatica.y"
{ escribirError("Constante negativa fuera de rango.");}
break;
case 80:
//#line 140 "gramatica.y"
{escribirError("Constante fuera de rango");}
break;
case 82:
//#line 144 "gramatica.y"
{ chequearRango(); }
break;
//#line 766 "Parser.java"
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
